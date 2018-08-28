// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotifications;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.NewNotificationsFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.usernotificationsframework.common.UserNotificationManager;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationCheckOrigin;
import com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog;
import com.google.android.apps.calendar.usernotificationsframework.storage.UserNotificationStore;
import com.google.android.apps.calendar.util.android.BroadcastReceivers;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.executors.ThrottlingExecutor;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.permission.PermissionsUtil;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.apps.calendar.usernotifications:
//            EventNotificationPlugin

public class NotificationsInitializer
{
    public static class NotificationsRelevantUpdatesReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            FeatureConfig featureconfig = Features.instance;
            if (featureconfig == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)featureconfig).new_notifications();
            if (!RemoteFeatureConfig.NEW_NOTIFICATIONS.enabled())
            {
                return;
            }
            intent = intent.getAction();
            if (!NotificationsInitializer.initialized)
            {
                LogUtils.wtf(NotificationsInitializer.TAG, "Initialize method should be called first.", new Object[0]);
                return;
            }
            boolean flag;
            if (!PermissionsUtil.canRequestPermissions())
            {
                flag = true;
            } else
            if (PermissionsUtil.checkSelfPermission(context, "android.permission.READ_CALENDAR") == 0 && PermissionsUtil.checkSelfPermission(context, "android.permission.WRITE_CALENDAR") == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                context = NotificationsInitializer.TAG;
                NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog..Lambda._cls3(context, "No calendar permissions.", new Object[0]));
                return;
            } else
            {
                String s = NotificationsInitializer.TAG;
                NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog..Lambda._cls3(s, "Received an action: %s.", new Object[] {
                    intent
                }));
                class .Lambda._cls0
                    implements Runnable
                {

                    private final Context arg$1;
                    private final String arg$2;

                    public final void run()
                    {
                        Context context1 = arg$1;
                        String s1 = arg$2;
                        UserNotificationManager usernotificationmanager = UserNotificationManager.instance;
                        if (usernotificationmanager == null)
                        {
                            throw new NullPointerException(String.valueOf("Call initialize method first."));
                        } else
                        {
                            ((UserNotificationManager)usernotificationmanager).check(context1, s1);
                            return;
                        }
                    }

                .Lambda._cls0(Context context, String s)
                {
                    arg$1 = context;
                    arg$2 = s;
                }
                }

                context = new .Lambda._cls0(context, intent);
                NotificationsInitializer.THROTTLING_EXECUTOR.execute(context);
                return;
            }
        }

        public NotificationsRelevantUpdatesReceiver()
        {
        }
    }


    private static final Subscription EMPTY_SUBSCRIPTION;
    private static final long NOTIFICATIONS_UPDATE_ON_DATA_CHANGE_DELAY;
    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/usernotifications/NotificationsInitializer);
    public static final ThrottlingExecutor THROTTLING_EXECUTOR;
    public static boolean initialized;

    public NotificationsInitializer()
    {
    }

    public static void initialize(Context context, EventNotificationPlugin.EventNotificationPresenter eventnotificationpresenter, EventNotificationPlugin.HabitNotificationPresenter habitnotificationpresenter, Runnable runnable)
    {
        Object obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).new_notifications();
        if (!RemoteFeatureConfig.NEW_NOTIFICATIONS.enabled())
        {
            class .Lambda._cls0
                implements Runnable
            {

                private final Context arg$1;
                private final Runnable arg$2;

                public final void run()
                {
                    NotificationsInitializer.lambda$initialize$1$NotificationsInitializer(arg$1, arg$2);
                }

            .Lambda._cls0(Context context, Runnable runnable)
            {
                arg$1 = context;
                arg$2 = runnable;
            }
            }

            UserNotificationManager.cleanup(context, new .Lambda._cls0(context, runnable));
            return;
        }
        eventnotificationpresenter = new EventNotificationPlugin(context, eventnotificationpresenter, habitnotificationpresenter);
        habitnotificationpresenter = ImmutableList.of(eventnotificationpresenter);
        class .Lambda._cls1
            implements Runnable
        {

            private final Context arg$1;

            public final void run()
            {
                NotificationsInitializer.lambda$initialize$2$NotificationsInitializer(arg$1);
            }

            .Lambda._cls1(Context context)
            {
                arg$1 = context;
            }
        }

        runnable = new .Lambda._cls1(context);
        class .Lambda._cls2
            implements Consumer
        {

            private final Context arg$1;
            private final EventNotificationPlugin arg$2;

            public final void accept(Object obj1)
            {
                NotificationsInitializer.lambda$initialize$3$NotificationsInitializer(arg$1, arg$2, (String)obj1);
            }

            .Lambda._cls2(Context context, EventNotificationPlugin eventnotificationplugin)
            {
                arg$1 = context;
                arg$2 = eventnotificationplugin;
            }
        }

        boolean flag;
        if (UserNotificationManager.instance != null)
        {
            LogUtils.wtf(UserNotificationManager.TAG, "Attempt to call init method twice.", new Object[0]);
            flag = false;
        } else
        {
            NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog..Lambda._cls0(context));
            long l;
            if (!UserNotificationManager.hasBeenUsed(context))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                UserNotificationManager.logOnFailureAsync(runnable, "Failed in initialization callback.", new Object[0]);
            }
            habitnotificationpresenter = new UserNotificationManager(context, habitnotificationpresenter);
            UserNotificationManager.instance = habitnotificationpresenter;
            habitnotificationpresenter = ((UserNotificationManager) (habitnotificationpresenter)).store;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            habitnotificationpresenter.deleteOutdatedNotifications(l, UserNotificationManager.BECOMES_OUTDATED_AFTER_INTERVAL_MILLIS);
        }
        eventnotificationpresenter = new .Lambda._cls2(context, eventnotificationpresenter);
        habitnotificationpresenter = Features.instance;
        if (habitnotificationpresenter == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)habitnotificationpresenter).uss();
        habitnotificationpresenter = EMPTY_SUBSCRIPTION;
        runnable = Features.instance;
        if (runnable == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)runnable).uss();
        runnable = EMPTY_SUBSCRIPTION;
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).uss();
        obj = EMPTY_SUBSCRIPTION;
        class .Lambda._cls3
            implements Runnable
        {

            private final Context arg$1;

            public final void run()
            {
                NotificationsInitializer.lambda$initialize$4$NotificationsInitializer(arg$1);
            }

            .Lambda._cls3(Context context)
            {
                arg$1 = context;
            }
        }

        if (RemoteFeatureConfig.NEW_NOTIFICATIONS.getSupportsHabits().booleanValue())
        {
            IntentFilter intentfilter = new IntentFilter();
            intentfilter.addAction("com.google.android.calendar.intent.action.GROOVE_SYNCED");
            intentfilter.addAction("com.google.android.calendar.intent.action.GROOVE_REQUEST_UPSYNCED");
            class .Lambda._cls7
                implements Runnable
            {

                private final Consumer arg$1;

                public final void run()
                {
                    Consumer consumer = arg$1;
                    class .Lambda._cls8
                        implements Runnable
                    {

                        private final Consumer arg$1;

                        public final void run()
                        {
                            NotificationsInitializer.lambda$maybeSubscribeToGrooveStoreHabitChanges$8$NotificationsInitializer(arg$1);
                        }

                            .Lambda._cls8(Consumer consumer)
                            {
                                arg$1 = consumer;
                            }
                    }

                    NotificationsInitializer.THROTTLING_EXECUTOR.execute(new .Lambda._cls8(consumer));
                }

            .Lambda._cls7(Consumer consumer)
            {
                arg$1 = consumer;
            }
            }

            eventnotificationpresenter = BroadcastReceivers.subscribeToBroadcast(context, intentfilter, new com.google.android.apps.calendar.util.function.Consumer..Lambda._cls0(new .Lambda._cls7(eventnotificationpresenter)));
        } else
        {
            eventnotificationpresenter = EMPTY_SUBSCRIPTION;
        }
        new com.google.android.apps.calendar.util.concurrent.Subscription..Lambda._cls0(Arrays.asList(new Subscription[] {
            habitnotificationpresenter, runnable, obj, eventnotificationpresenter
        }));
        if (flag)
        {
            THROTTLING_EXECUTOR.execute(new .Lambda._cls3(context));
        }
        initialized = true;
    }

    static final void lambda$initialize$1$NotificationsInitializer(Context context, Runnable runnable)
    {
        ((NotificationManager)context.getSystemService("notification")).cancelAll();
        runnable.run();
    }

    static final void lambda$initialize$2$NotificationsInitializer(Context context)
    {
        ((NotificationManager)context.getSystemService("notification")).cancelAll();
    }

    static final void lambda$initialize$3$NotificationsInitializer(Context context, EventNotificationPlugin eventnotificationplugin, String s)
    {
        eventnotificationplugin = UserNotificationManager.instance;
        if (eventnotificationplugin == null)
        {
            throw new NullPointerException(String.valueOf("Call initialize method first."));
        } else
        {
            eventnotificationplugin = (UserNotificationManager)eventnotificationplugin;
            Integer integer = Integer.valueOf(1);
            NotificationLog.logOnFailure(eventnotificationplugin.checkInternal(context, integer, UserNotificationCheckOrigin.EXPLICIT_CALL, s), UserNotificationManager.TAG, "Failed to check notifications for plugin='%s'.", new Object[] {
                integer
            });
            return;
        }
    }

    static final void lambda$initialize$4$NotificationsInitializer(Context context)
    {
        UserNotificationManager usernotificationmanager = UserNotificationManager.instance;
        if (usernotificationmanager == null)
        {
            throw new NullPointerException(String.valueOf("Call initialize method first."));
        } else
        {
            ((UserNotificationManager)usernotificationmanager).check(context, "FirstUpdate");
            return;
        }
    }

    static final void lambda$maybeSubscribeToGrooveStoreHabitChanges$8$NotificationsInitializer(Consumer consumer)
    {
        consumer.accept("GrooveSyncIntents");
    }

    static final void lambda$static$0$NotificationsInitializer$51D2ILG_0()
    {
    }

    static 
    {
        NOTIFICATIONS_UPDATE_ON_DATA_CHANGE_DELAY = TimeUnit.SECONDS.toMillis(3L);
        class .Lambda._cls9
            implements Subscription
        {

            public static final Subscription $instance = new .Lambda._cls9();

            public final void cancel(boolean flag)
            {
                NotificationsInitializer.lambda$static$0$NotificationsInitializer$51D2ILG_0();
            }


            private .Lambda._cls9()
            {
            }
        }

        EMPTY_SUBSCRIPTION = .Lambda._cls9..instance;
        THROTTLING_EXECUTOR = new ThrottlingExecutor(CalendarExecutor.BACKGROUND, NOTIFICATIONS_UPDATE_ON_DATA_CHANGE_DELAY);
    }
}
