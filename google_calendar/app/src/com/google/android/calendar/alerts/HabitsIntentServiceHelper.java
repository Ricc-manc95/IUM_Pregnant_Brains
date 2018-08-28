// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.SimpleArrayMap;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.NewNotificationsFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.config.remote.UnifiedSyncAndStoreFeature;
import com.google.android.apps.calendar.timely.store.GrooveStore;
import com.google.android.apps.calendar.usernotificationsframework.common.UserNotificationActionHelper;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationState;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.LaunchInfoActivityUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.common.Feature;
import com.google.android.calendar.api.event.CpEventKey;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventFactory;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitIdTypeUtil;
import com.google.android.calendar.api.habit.HabitInstanceModifications;
import com.google.android.calendar.groove.GrooveSyncTracker;
import com.google.android.calendar.groove.GrooveUtils;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.habit.HabitInstancesUtil;
import com.google.android.calendar.utils.network.NetworkUtil;
import com.google.android.calendar.utils.snackbar.SnackbarFeedbackUtils;
import com.google.android.calendar.widget.WidgetUtils;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.alerts:
//            HabitsIntentService, HabitsIntentReceiver, HabitsNotificationManager, AlertUtils, 
//            HabitsAlerts

public class HabitsIntentServiceHelper
{
    final class PostCreationFeedbackRunnable
        implements Runnable
    {

        private EventKey eventKey;
        private long startTimeMillis;
        private final HabitsIntentServiceHelper this$0;

        public final void run()
        {
            Context context1 = context;
            EventKey eventkey = eventKey;
            long l = startTimeMillis;
            Intent intent = new Intent("com.google.android.calendar.intent.action.ACTION_SHOW_FEEDBACK");
            intent.putExtra("feedbackType", 1);
            intent.putExtra("eventKey", eventkey);
            intent.putExtra("timeMillis", l);
            LocalBroadcastManager.getInstance(context1).sendBroadcast(intent);
        }

        PostCreationFeedbackRunnable(EventKey eventkey, long l)
        {
            this$0 = HabitsIntentServiceHelper.this;
            super();
            eventKey = eventkey;
            startTimeMillis = l;
        }
    }

    static final class SnackbarFeedbackRunnable
        implements Runnable
    {

        private int action;
        private Context context;
        private EventKey eventKey;
        private String feedbackText;

        public final void run()
        {
            if (eventKey == null)
            {
                SnackbarFeedbackUtils.showSnackbarFeedback(context, feedbackText, false, null, 0);
                return;
            } else
            {
                SnackbarFeedbackUtils.showSnackbarFeedback(context, feedbackText, false, eventKey, action);
                return;
            }
        }

        SnackbarFeedbackRunnable(Context context1, int i)
        {
            this(context1, context1.getString(i), null, 0);
        }

        SnackbarFeedbackRunnable(Context context1, String s, EventKey eventkey, int i)
        {
            context = context1;
            feedbackText = s;
            eventKey = eventkey;
            action = i;
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/alerts/HabitsIntentServiceHelper);
    public final Context context;
    private Handler handler;

    public HabitsIntentServiceHelper(Context context1)
    {
        context = context1.getApplicationContext();
        handler = new Handler(Looper.getMainLooper());
    }

    static Intent createCompleteIntent(Context context1, HabitDescriptor habitdescriptor, EventKey eventkey, UserNotification usernotification, int i, boolean flag, String s)
    {
        context1 = (new Intent(context1, com/google/android/calendar/alerts/HabitsIntentService)).setAction("com.google.android.calendar.intent.action.ACTION_HABIT_COMPLETE").putExtra("requestCode", i).putExtra("completed", flag).putExtra("eventKey", eventkey).putExtra("descriptor", habitdescriptor).putExtra("clear", true).putExtra("account", habitdescriptor.calendar.account).putExtra("analytics_label", s);
        if (usernotification != null)
        {
            UserNotificationActionHelper.attachStateUpdateToUserAction(context1, usernotification, UserNotificationState.ACCEPTED);
        }
        return context1;
    }

    public static Intent createCompleteIntent(Context context1, HabitDescriptor habitdescriptor, EventKey eventkey, boolean flag, String s)
    {
        return createCompleteIntent(context1, habitdescriptor, eventkey, null, -1, flag, s);
    }

    static Intent createDeferIntent(Context context1, HabitDescriptor habitdescriptor, EventKey eventkey, UserNotification usernotification, int i, String s)
    {
        context1 = (new Intent(context1, com/google/android/calendar/alerts/HabitsIntentService)).setAction("com.google.android.calendar.intent.action.ACTION_HABIT_DEFER").putExtra("requestCode", i).putExtra("eventKey", eventkey).putExtra("descriptor", habitdescriptor).putExtra("clear", true).putExtra("analytics_label", s);
        if (usernotification != null)
        {
            UserNotificationActionHelper.attachStateUpdateToUserAction(context1, usernotification, UserNotificationState.ACCEPTED);
        }
        return context1;
    }

    public static Intent createDeferIntent(Context context1, HabitDescriptor habitdescriptor, EventKey eventkey, String s)
    {
        return createDeferIntent(context1, habitdescriptor, eventkey, null, -1, s);
    }

    public static Intent createDismissIntent(Context context1, HabitDescriptor habitdescriptor, EventKey eventkey)
    {
        return createDismissIntent(context1, habitdescriptor, eventkey, null, -1);
    }

    public static Intent createDismissIntent(Context context1, HabitDescriptor habitdescriptor, EventKey eventkey, UserNotification usernotification, int i)
    {
        context1 = (new Intent(context1, com/google/android/calendar/alerts/HabitsIntentService)).setAction("com.google.android.calendar.intent.action.ACTION_HABIT_DISMISS_NOTIFICATION").putExtra("requestCode", i).putExtra("eventKey", eventkey).putExtra("descriptor", habitdescriptor).putExtra("clear", true);
        if (usernotification != null)
        {
            UserNotificationActionHelper.attachStateUpdateToUserAction(context1, usernotification, UserNotificationState.DISMISSED);
        }
        return context1;
    }

    public static Intent createForceSyncIntent(Context context1, String s, HabitDescriptor habitdescriptor, String s1, boolean flag, int i)
    {
        Intent intent = new Intent(context1, com/google/android/calendar/alerts/HabitsIntentService);
        intent.setAction("com.google.android.calendar.intent.action.HABITS_FORCE_SYNC");
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            s = "groove://".concat(s);
        } else
        {
            s = new String("groove://");
        }
        intent.setData(Uri.parse(s));
        intent.setClass(context1, com/google/android/calendar/alerts/HabitsIntentReceiver);
        intent.putExtra("groove_operation", i);
        intent.putExtra("force_sync_log_time", flag);
        if (s1 != null)
        {
            intent.putExtra("force_sync_instance_tracking_id", s1);
        }
        if (habitdescriptor != null)
        {
            intent.putExtra("force_sync_tracking_groove_id", habitdescriptor.habitId);
            intent.putExtra("account", habitdescriptor.calendar.account);
            intent.putExtra("feed_internal", habitdescriptor.calendar.calendarId);
        }
        return intent;
    }

    public static Intent createPostBelongCheckNotificationIntent(Context context1, EventKey eventkey)
    {
        return (new Intent()).setClass(context1, com/google/android/calendar/alerts/HabitsIntentReceiver).setAction("com.google.android.calendar.intent.action.HABITS_POST_BELONG_CHECK_NOTIFICATION").putExtra("eventKey", eventkey);
    }

    public static Intent createRefreshNotificationsIntent(Context context1, HabitDescriptor habitdescriptor)
    {
        Account account = habitdescriptor.calendar.account;
        String s = habitdescriptor.calendar.calendarId;
        habitdescriptor = habitdescriptor.habitId;
        return (new Intent(context1, com/google/android/calendar/alerts/HabitsIntentService)).setAction("com.google.android.calendar.intent.action.REFRESH_GROOVE_NOTIFICATIONS").putExtra("account", account).putExtra("calendarId", s).putExtra("idsOfParentsAffected", new String[] {
            habitdescriptor
        });
    }

    public static Intent createViewIntent(Context context1, HabitDescriptor habitdescriptor, EventKey eventkey, UserNotification usernotification, int i)
    {
        context1 = (new Intent(context1, com/google/android/calendar/alerts/HabitsIntentService)).setAction("com.google.android.calendar.intent.action.ACTION_HABIT_VIEW").putExtra("requestCode", i).putExtra("eventKey", eventkey).putExtra("descriptor", habitdescriptor).putExtra("clear", true);
        if (usernotification != null)
        {
            UserNotificationActionHelper.attachStateUpdateToUserAction(context1, usernotification, UserNotificationState.ACCEPTED);
        }
        return context1;
    }

    static void forceNotifyChange(Context context1, Uri uri)
    {
        if (context1 != null)
        {
            context1.getContentResolver().notifyChange(uri, null, true);
            uri = WidgetUtils.getWidgetCallerIsSyncAdapterAction(context1);
            if (uri != null)
            {
                uri = (Intent)(new Intent(uri)).clone();
                uri.setPackage(context1.getPackageName());
                context1.sendBroadcast(uri);
                return;
            }
        }
    }

    static final void lambda$deferHabit$0$HabitsIntentServiceHelper(EventKey eventkey, Context context1, int i, Handler handler1, String s, HabitDescriptor habitdescriptor, Event event)
    {
        if (event == null)
        {
            LogUtils.e(TAG, "Could not load habit instance: %s.", new Object[] {
                eventkey
            });
        } else
        {
            if (eventkey.uri().isPresent())
            {
                forceNotifyChange(context1, (Uri)eventkey.uri().get());
            }
            long l;
            if (i == -1)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            eventkey = event.getSyncId();
            l = event.getStartMillis();
            if (context1 != null)
            {
                event = AnalyticsLoggerHolder.instance;
                if (event == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                ((AnalyticsLogger)event).trackEvent(context1, "groove", "deferred", s, null);
            }
            if (i != 0)
            {
                if (NetworkUtil.isConnectedToInternet(context1))
                {
                    i = 0x7f13024f;
                } else
                {
                    i = 0x7f130250;
                }
                handler1.post(new SnackbarFeedbackRunnable(context1, i));
            }
            if (NetworkUtil.isConnectedToInternet(context1))
            {
                if (GrooveSyncTracker.instance == null)
                {
                    GrooveSyncTracker.instance = new GrooveSyncTracker();
                }
                context1 = GrooveSyncTracker.instance;
                LatencyLoggerHolder.get().markAt(25, eventkey);
                ((GrooveSyncTracker) (context1)).deferredEvents.put(eventkey, Long.valueOf(l));
                if (eventkey == null)
                {
                    i = 4;
                } else
                {
                    i = 5;
                }
                context1 = new Bundle(i);
                context1.putBoolean("upload", true);
                context1.putString("feed_internal", habitdescriptor.calendar.calendarId);
                context1.putInt("groove_operation", 2);
                context1.putString("upsync_tracking_id", habitdescriptor.habitId);
                if (eventkey != null)
                {
                    context1.putString("upsync_instance_tracking_id", eventkey);
                }
                eventkey = habitdescriptor.calendar.account;
                handler1 = Features.instance;
                if (handler1 == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                } else
                {
                    ((FeatureConfig)handler1).fishfood_debug();
                    ContentResolver.requestSync(eventkey, "com.android.calendar", context1);
                    return;
                }
            }
        }
    }

    static final ListenableFuture lambda$updateHabitInstanceStatusAsync$1$HabitsIntentServiceHelper(int i, Event event)
        throws Exception
    {
        event = CalendarApi.EventFactory.modifyEvent(event);
        ((HabitInstanceModifications)event.getHabitInstanceModifications().getValue()).setStatus(i, false);
        return CalendarApi.Events.update(event, 0, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED);
    }

    private final void processRefreshNotificationsIntent(Bundle bundle)
    {
        String as[] = bundle.getStringArray("idsOfParentsAffected");
        if (as != null)
        {
            String s = bundle.getString("calendarId");
            bundle = (Account)bundle.getParcelable("account");
            int j = GrooveUtils.getDefaultReminderMinutes(context, bundle, s);
            for (int i = 0; i < as.length; i++)
            {
                HabitDescriptor habitdescriptor = new HabitDescriptor(CalendarDescriptor.createWithoutLocalId(bundle, s), as[i]);
                LogUtils.v(TAG, "\tProcessing notifications for habit %s on %s", new Object[] {
                    as[i], s
                });
                HabitsNotificationManager.updateNotificationsForHabit(context, habitdescriptor, j);
            }

        }
        bundle = context;
        AlarmManagerInterface alarmmanagerinterface = AlertUtils.createAlarmManager(context);
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        HabitsNotificationManager.scheduleAlarmForNextNotificationTriggerTime(bundle, alarmmanagerinterface, l);
    }

    private final void showNotification(Entity entity, boolean flag)
    {
        Object obj1;
        int k;
        long l1;
        k = entity.getEntityValues().getAsInteger("_id").intValue();
        l1 = entity.getEntityValues().getAsLong("eventId").longValue();
        obj1 = entity.getEntityValues().getAsString("habitParentSyncId");
        Object obj = context.getContentResolver().query(ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, l1), new String[] {
            "account_name", "ownerAccount", "visible", "dtstart", "dtend", "sync_data9"
        }, null, null, null);
        if (obj == null) goto _L2; else goto _L1
_L1:
        if (((Cursor) (obj)).getCount() != 0) goto _L3; else goto _L2
_L2:
        LogUtils.e(TAG, "Could not get event: %d", new Object[] {
            Long.valueOf(l1)
        });
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
_L5:
        return;
_L3:
        Account account;
        String s;
        int i;
        int l;
        long l2;
        long l3;
        ((Cursor) (obj)).moveToFirst();
        account = new Account(((Cursor) (obj)).getString(0), "com.google");
        s = ((Cursor) (obj)).getString(1);
        i = ((Cursor) (obj)).getInt(2);
        l2 = ((Cursor) (obj)).getLong(3);
        l3 = ((Cursor) (obj)).getLong(4);
        l = ((Cursor) (obj)).getInt(5);
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        if (HabitsNotificationManager.isInactive(l) || i == 0) goto _L5; else goto _L4
_L4:
        boolean flag1;
        obj = (com.google.android.calendar.api.habit.HabitClient.ReadResult)CalendarApi.Habits.read(new HabitDescriptor(CalendarDescriptor.createWithoutLocalId(account, s), ((String) (obj1)))).await(500L, TimeUnit.MILLISECONDS);
        int j;
        if (((com.google.android.calendar.api.habit.HabitClient.ReadResult) (obj)).getStatus().zzaEP <= 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L5; else goto _L6
_L6:
        obj = ((com.google.android.calendar.api.habit.HabitClient.ReadResult) (obj)).getHabit();
        obj1 = CpEventKey.newInstance(l1);
        j = entity.getEntityValues().getAsInteger("type").intValue();
        flag1 = false;
        j;
        JVM INSTR tableswitch 1 3: default 364
    //                   1 420
    //                   2 473
    //                   3 495;
           goto _L7 _L8 _L9 _L10
_L7:
        LogUtils.w(TAG, "Notification of unknown type: %s", new Object[] {
            entity
        });
        flag = flag1;
_L12:
        HabitsNotificationManager.markPastNotificationsAsDismissed(entity);
        if (flag) goto _L5; else goto _L11
_L11:
        HabitsNotificationManager.setDisplayState(entity, 1);
        return;
        entity;
        obj = null;
_L13:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        throw entity;
_L8:
        Context context1 = context;
        if (HabitsAlerts.areNewHabitNotificationsEnabled())
        {
            LogUtils.i(HabitsAlerts.TAG, "Showing pre-instance notification is ignored.", new Object[0]);
            flag = flag1;
        } else
        {
            HabitsAlerts.showPreinstanceNotification(context1, ((com.google.android.calendar.api.habit.Habit) (obj)), ((EventKey) (obj1)), l2, l3, null, k);
            flag = flag1;
        }
          goto _L12
_L9:
        HabitsNotificationManager.processRecommitNotificationTriggerLegacy(context, ((com.google.android.calendar.api.habit.Habit) (obj)), ((EventKey) (obj1)), l2, l3, k);
        flag = flag1;
          goto _L12
_L10:
        flag = HabitsNotificationManager.processFollowupNotificationTriggerLegacy(context, ((com.google.android.calendar.api.habit.Habit) (obj)), ((EventKey) (obj1)), k, flag);
          goto _L12
        entity;
          goto _L13
    }

    final void onHandle(Intent intent)
    {
        Object obj;
        Object obj1;
        int l;
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).new_notifications();
        if (RemoteFeatureConfig.NEW_NOTIFICATIONS.enabled() && RemoteFeatureConfig.NEW_NOTIFICATIONS.getSupportsHabits().booleanValue())
        {
            UserNotificationActionHelper.updateOnUserAction(intent);
        }
        obj1 = intent.getAction();
        obj = intent.getExtras();
        LogUtils.v(TAG, "Received intent %s", new Object[] {
            obj
        });
        if (obj == null)
        {
            obj = Bundle.EMPTY;
        }
        l = ((Bundle) (obj)).getInt("requestCode", -1);
        if (((Bundle) (obj)).containsKey("clear") && ((Bundle) (obj)).containsKey("descriptor"))
        {
            intent = (HabitDescriptor)((Bundle) (obj)).getParcelable("descriptor");
            HabitsAlerts.cancelLegacy(this.context, ((HabitDescriptor) (intent)).habitId);
            if (l != -1 && l != -2)
            {
                intent = GrooveStore.store;
                if (intent == null)
                {
                    throw new NullPointerException(String.valueOf("Not initialized"));
                }
                Cursor cursor = ((GrooveStore)intent).database.query("habitnotifications", GrooveStore.HABIT_NOTIFICATION_PROJECTION, "_id=?", new String[] {
                    String.valueOf(l)
                }, null, null, null);
                if (cursor.getCount() == 0)
                {
                    cursor.close();
                    intent = null;
                } else
                {
                    cursor.moveToFirst();
                    intent = new ContentValues(cursor.getColumnCount());
                    DatabaseUtils.cursorRowToContentValues(cursor, intent);
                    intent = new Entity(intent);
                    cursor.close();
                }
                if (intent != null)
                {
                    HabitsNotificationManager.setDisplayState(intent, 3);
                }
            }
        }
        if (!"com.google.android.calendar.intent.action.HABITS_POST_BELONG_CHECK_NOTIFICATION".equals(obj1)) goto _L2; else goto _L1
_L1:
        intent = (EventKey)((Bundle) (obj)).getParcelable("eventKey");
        if (intent instanceof CpEventKey)
        {
            intent = HabitsNotificationManager.getFollowupNotificationForInstanceId((CpEventKey)intent);
            if (intent != null)
            {
                showNotification(intent, true);
            }
        }
_L4:
        return;
_L2:
        if ("com.google.android.calendar.intent.action.ACTION_HABIT_VIEW".equals(obj1))
        {
            intent = this.context;
            obj = LaunchInfoActivityUtils.getLaunchFillInIntent(intent, (EventKey)((Bundle) (obj)).getParcelable("eventKey"));
            ((Intent) (obj)).setAction("android.intent.action.VIEW");
            ((Intent) (obj)).putExtra("intent_source", "notification");
            intent.startActivity(((Intent) (obj)));
            return;
        }
        if (!((Bundle) (obj)).containsKey("eventKey"))
        {
            break; /* Loop/switch isn't completed */
        }
        final EventKey eventKey = (EventKey)((Bundle) (obj)).getParcelable("eventKey");
        class .Lambda._cls1
            implements AsyncFunction
        {

            private final int arg$1;

            public final ListenableFuture apply(Object obj7)
            {
                return HabitsIntentServiceHelper.lambda$updateHabitInstanceStatusAsync$1$HabitsIntentServiceHelper(arg$1, (Event)obj7);
            }

            .Lambda._cls1(int i)
            {
                arg$1 = i;
            }
        }

        if ("com.google.android.calendar.intent.action.ACTION_HABIT_COMPLETE".equals(obj1) && ((Bundle) (obj)).containsKey("completed") && ((Bundle) (obj)).containsKey("account"))
        {
            final String label = ((Bundle) (obj)).getString("analytics_label");
            final Context context = this.context;
            final boolean done = ((Bundle) (obj)).getBoolean("completed", false);
            byte byte0;
            if (done)
            {
                byte0 = 3;
            } else
            {
                byte0 = 1;
            }
            intent = CalendarApi.Events.read(eventKey);
            if (intent instanceof FluentFuture)
            {
                intent = (FluentFuture)intent;
            } else
            {
                intent = new ForwardingFluentFuture(intent);
            }
            intent = (FluentFuture)AbstractTransformFuture.create(intent, new .Lambda._cls1(byte0), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            label = new _cls1();
            context = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.BACKGROUND);
            if (label == null)
            {
                throw new NullPointerException();
            }
            intent.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(intent, label), context);
        }
        if ("com.google.android.calendar.intent.action.ACTION_HABIT_DEFER".equals(obj1) && ((Bundle) (obj)).containsKey("descriptor"))
        {
            obj1 = (HabitDescriptor)((Bundle) (obj)).getParcelable("descriptor");
            obj = ((Bundle) (obj)).getString("analytics_label");
            Context context2 = this.context;
            Handler handler1 = handler;
            intent = CalendarApi.Events.read(eventKey);
            class .Lambda._cls0
                implements Consumer
            {

                private final EventKey arg$1;
                private final Context arg$2;
                private final int arg$3;
                private final Handler arg$4;
                private final String arg$5;
                private final HabitDescriptor arg$6;

                public final void accept(Object obj7)
                {
                    HabitsIntentServiceHelper.lambda$deferHabit$0$HabitsIntentServiceHelper(arg$1, arg$2, arg$3, arg$4, arg$5, arg$6, (Event)obj7);
                }

            .Lambda._cls0(EventKey eventkey, Context context1, int i, Handler handler1, String s, HabitDescriptor habitdescriptor)
            {
                arg$1 = eventkey;
                arg$2 = context1;
                arg$3 = i;
                arg$4 = handler1;
                arg$5 = s;
                arg$6 = habitdescriptor;
            }
            }

            if (intent instanceof FluentFuture)
            {
                intent = (FluentFuture)intent;
            } else
            {
                intent = new ForwardingFluentFuture(intent);
            }
            intent = (FluentFuture)AbstractTransformFuture.create(intent, new .Lambda._cls1(2), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            obj = LogUtils.newFailureLoggingCallback(new .Lambda._cls0(eventKey, context2, l, handler1, ((String) (obj)), ((HabitDescriptor) (obj1))), TAG, "Could not fully process deferral for habit instance %s.", new Object[] {
                eventKey
            });
            obj1 = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
            if (obj == null)
            {
                throw new NullPointerException();
            } else
            {
                intent.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(intent, ((FutureCallback) (obj))), ((java.util.concurrent.Executor) (obj1)));
                return;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
label0:
        {
label1:
            {
                if (!"com.google.android.calendar.intent.action.HABITS_NOTIFICATION".equals(obj1))
                {
                    break label0;
                }
                HabitsNotificationManager.dismissExpiredNotifications(this.context);
                long l1;
                if (((Bundle) (obj)).containsKey("habitNotificationTriggerTime"))
                {
                    long l7 = ((Bundle) (obj)).getLong("habitNotificationTriggerTime", 0L);
                    boolean flag;
                    if (Clock.mockedTimestamp > 0L)
                    {
                        l1 = Clock.mockedTimestamp;
                    } else
                    {
                        l1 = System.currentTimeMillis();
                    }
                    if (l7 < l1 - 0x5265c00L)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag)
                    {
                        break label1;
                    }
                }
                intent = this.context;
                obj = AlertUtils.createAlarmManager(this.context);
                if (Clock.mockedTimestamp > 0L)
                {
                    l1 = Clock.mockedTimestamp;
                } else
                {
                    l1 = System.currentTimeMillis();
                }
                HabitsNotificationManager.scheduleAlarmForNextNotificationTriggerTime(intent, ((AlarmManagerInterface) (obj)), l1);
                return;
            }
            long l2 = ((Bundle) (obj)).getLong("habitNotificationTriggerTime", 0L);
            intent = HabitsNotificationManager.getNotificationsAtTriggerTime(l2);
            if (intent != null)
            {
                int i = 0;
                while (i < intent.length) 
                {
                    obj = intent[i];
                    if (obj == null)
                    {
                        continue;
                    }
                    l = ((Entity) (obj)).getEntityValues().getAsInteger("displayState").intValue();
                    boolean flag1;
                    if (3 == l || 1 == l)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (!flag1)
                    {
                        showNotification(((Entity) (obj)), false);
                    }
                    i++;
                }
            }
            HabitsNotificationManager.scheduleAlarmForNextNotificationTriggerTime(this.context, AlertUtils.createAlarmManager(this.context), l2 + 1L);
            return;
        }
        if (!"com.google.android.calendar.intent.action.GROOVE_SYNCED".equals(obj1)) goto _L6; else goto _L5
_L5:
        if (((Bundle) (obj)).getBoolean("force_sync_log_time", false))
        {
            intent = this.context;
            int j = ((Bundle) (obj)).getInt("groove_operation", 0);
            String s;
            com.google.android.calendar.groove.GrooveSyncTracker.HabitSyncListener habitsynclistener;
            if (j == 1)
            {
                obj1 = ((Bundle) (obj)).getString("force_sync_tracking_groove_id");
                LatencyLoggerHolder.get().markAt(23, ((String) (obj1)));
                obj1 = String.format("success=%b,delay=%d", new Object[] {
                    Boolean.valueOf(((Bundle) (obj)).getBoolean("force_sync_create_success", false)), Integer.valueOf(3750)
                });
                long l3 = ((Bundle) (obj)).getLong("tracked_sync_duration", 0L);
                if (intent != null)
                {
                    AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
                    if (analyticslogger == null)
                    {
                        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                    }
                    ((AnalyticsLogger)analyticslogger).trackEvent(intent, "groove", "creation_force_sync", ((String) (obj1)), Long.valueOf(l3));
                }
            } else
            if (j == 2)
            {
                obj1 = ((Bundle) (obj)).getString("force_sync_instance_tracking_id");
                LatencyLoggerHolder.get().markAt(28, ((String) (obj1)));
                long l4;
                if (((Bundle) (obj)).getBoolean("force_sync_create_success", false))
                {
                    l4 = 1L;
                } else
                {
                    l4 = 0L;
                }
                if (intent != null)
                {
                    obj1 = AnalyticsLoggerHolder.instance;
                    if (obj1 == null)
                    {
                        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                    }
                    ((AnalyticsLogger)obj1).trackEvent(intent, "groove", "defer_force_sync", "10000", Long.valueOf(l4));
                }
            }
        }
        obj1 = ((Bundle) (obj)).getStringArray("parentIdsOfInstancesSynced");
        if (obj1 == null) goto _L8; else goto _L7
_L7:
        j = 0;
_L14:
        if (j >= obj1.length) goto _L8; else goto _L9
_L9:
        s = obj1[j];
        if (GrooveSyncTracker.instance == null)
        {
            GrooveSyncTracker.instance = new GrooveSyncTracker();
        }
        intent = GrooveSyncTracker.instance;
        Object obj3 = this.context;
        String s2 = obj1[j];
        if (((GrooveSyncTracker) (intent)).habitCreationListeners.containsKey(s2))
        {
            LatencyLoggerHolder.get().markAt(24, s2);
            habitsynclistener = (com.google.android.calendar.groove.GrooveSyncTracker.HabitSyncListener)((GrooveSyncTracker) (intent)).habitCreationListeners.remove(s2);
            ((GrooveSyncTracker) (intent)).habitDescriptors.remove(s2);
            ((GrooveSyncTracker) (intent)).habitInstancesSyncScheduled.remove(s2);
            GrooveSyncTracker.cancelForceInstancesSyncAlarm(((Context) (obj3)), s2, null, 1);
            habitsynclistener.onHabitInstancesSynced();
            flag1 = true;
        } else
        {
            if (((GrooveSyncTracker) (intent)).habitOperations.contains(s2))
            {
                ((GrooveSyncTracker) (intent)).habitOperations.remove(s2);
                GrooveSyncTracker.cancelForceInstancesSyncAlarm(((Context) (obj3)), s2, null, 0);
            }
            flag1 = false;
        }
        if (!flag1 || RemoteFeatureConfig.UNIFIED_SYNC_AND_STORE.getSupportsEvents()) goto _L11; else goto _L10
_L10:
        intent = this.context.getContentResolver();
        obj3 = android.provider.CalendarContract.Events.CONTENT_URI;
        s2 = HabitInstancesUtil.getSelectionArgs(s);
        intent = intent.query(((Uri) (obj3)), new String[] {
            "dtstart", "_id"
        }, "(sync_data8=? OR sync_data8 LIKE ?)", s2, "dtstart ASC LIMIT 1");
        if (intent.moveToFirst()) goto _L13; else goto _L12
_L12:
        LogUtils.e(TAG, "Could not get event for habit: %s", new Object[] {
            s
        });
        if (intent != null)
        {
            intent.close();
        }
_L11:
        j++;
          goto _L14
_L13:
        Context context1;
        long l5;
        long l8;
        l5 = intent.getLong(0);
        l8 = intent.getLong(1);
        context1 = this.context;
        if (context1 == null) goto _L16; else goto _L15
_L15:
        Object obj4 = AnalyticsLoggerHolder.instance;
        if (obj4 != null) goto _L18; else goto _L17
_L17:
        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        obj1;
        obj = intent;
        intent = ((Intent) (obj1));
_L56:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        throw intent;
_L18:
        ((AnalyticsLogger)obj4).trackEvent(context1, "groove", "creation_bottom_sheet_shown", "", null);
_L16:
        handler.post(new PostCreationFeedbackRunnable(CpEventKey.newInstance(l8), l5));
        if (intent != null)
        {
            intent.close();
        }
          goto _L11
_L8:
        String as[] = ((Bundle) (obj)).getStringArray("idsOfDeferredEvents");
        if (as == null) goto _L20; else goto _L19
_L19:
        flag1 = false;
_L24:
        if (flag1 >= as.length) goto _L20; else goto _L21
_L21:
        obj4 = as[flag1];
        obj1 = this.context.getContentResolver().query(android.provider.CalendarContract.Events.CONTENT_URI, new String[] {
            "dtstart", "sync_data8", "_id"
        }, "_sync_id=?", new String[] {
            obj4
        }, null);
        if (((Cursor) (obj1)).moveToFirst()) goto _L23; else goto _L22
_L22:
        LogUtils.e(TAG, "Could not get event: %s", new Object[] {
            obj4
        });
        if (obj1 != null)
        {
            ((Cursor) (obj1)).close();
        }
_L49:
        flag1++;
          goto _L24
_L23:
        l8 = ((Cursor) (obj1)).getLong(0);
        intent = HabitIdTypeUtil.parseHabitIdAndType(((Cursor) (obj1)).getString(1));
        if (intent == null) goto _L26; else goto _L25
_L25:
        if (intent.length != 0) goto _L27; else goto _L26
_L40:
        long l9 = ((Cursor) (obj1)).getLong(2);
        if (Clock.mockedTimestamp <= 0L) goto _L29; else goto _L28
_L28:
        l5 = Clock.mockedTimestamp;
_L41:
        Object obj5;
        Object obj6;
        Context context3;
        Long long1;
        obj6 = Utils.getDisplayedDatetime(l8, l8, l5, Utils.getTimeZoneId(this.context), false, false, this.context);
        if (GrooveSyncTracker.instance == null)
        {
            GrooveSyncTracker.instance = new GrooveSyncTracker();
        }
        obj5 = GrooveSyncTracker.instance;
        context3 = this.context;
        long1 = (Long)((GrooveSyncTracker) (obj5)).deferredEvents.get(obj4);
        if (long1 == null) goto _L31; else goto _L30
_L30:
        LatencyLoggerHolder.get().markAt(29, ((String) (obj4)));
        ((GrooveSyncTracker) (obj5)).deferredEvents.remove(obj4);
        GrooveSyncTracker.cancelForceInstancesSyncAlarm(context3, intent, ((String) (obj4)), 2);
        if (long1.longValue() != l8) goto _L33; else goto _L32
_L32:
        int k = android.support.v4.content.ModernAsyncTask.Status.UNCHANGED$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BR7E9NMUTJ55T3N4RRFEPIL6UBECDA74OB3DDIN4924CLJ6ASIICLPNAR3K7C______0;
_L42:
        if (k == android.support.v4.content.ModernAsyncTask.Status.NOT_TRACKED$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BR7E9NMUTJ55T3N4RRFEPIL6UBECDA74OB3DDIN4924CLJ6ASIICLPNAR3K7C______0) goto _L35; else goto _L34
_L34:
        intent = this.context;
        if (intent == null) goto _L37; else goto _L36
_L36:
        obj4 = AnalyticsLoggerHolder.instance;
        if (obj4 != null) goto _L39; else goto _L38
_L38:
        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        intent;
        obj = obj1;
_L55:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        throw intent;
_L27:
        intent = intent[0];
          goto _L40
_L29:
        l5 = System.currentTimeMillis();
          goto _L41
_L33:
        k = android.support.v4.content.ModernAsyncTask.Status.TIME_CHANGED$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BR7E9NMUTJ55T3N4RRFEPIL6UBECDA74OB3DDIN4924CLJ6ASIICLPNAR3K7C______0;
          goto _L42
_L31:
        k = android.support.v4.content.ModernAsyncTask.Status.NOT_TRACKED$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BR7E9NMUTJ55T3N4RRFEPIL6UBECDA74OB3DDIN4924CLJ6ASIICLPNAR3K7C______0;
          goto _L42
_L39:
        ((AnalyticsLogger)obj4).trackEvent(intent, "groove", "defer_view_link_shown", "", null);
_L37:
        obj4 = handler;
        obj5 = this.context;
        k - 1;
        JVM INSTR tableswitch 1 2: default 2892
    //                   1 2185
    //                   2 2211;
           goto _L43 _L44 _L45
_L50:
        obj6 = CpEventKey.newInstance(l9);
        k - 1;
        JVM INSTR tableswitch 1 2: default 2897
    //                   1 2287
    //                   2 2293;
           goto _L46 _L47 _L48
_L46:
        break MISSING_BLOCK_LABEL_2897;
_L51:
        ((Handler) (obj4)).post(new SnackbarFeedbackRunnable(((Context) (obj5)), intent, ((EventKey) (obj6)), k));
_L35:
        if (obj1 != null)
        {
            ((Cursor) (obj1)).close();
        }
          goto _L49
_L44:
        intent = this.context.getResources().getString(0x7f13024a, new Object[] {
            obj6
        });
          goto _L50
_L45:
        intent = this.context.getResources().getString(0x7f130251);
        String s4 = this.context.getResources().getString(0x7f130252);
        intent = (new StringBuilder(String.valueOf(intent).length() + 1 + String.valueOf(s4).length())).append(intent).append(" ").append(s4).toString();
          goto _L50
_L47:
        k = 1;
          goto _L51
_L48:
        k = 2;
          goto _L51
_L20:
        processRefreshNotificationsIntent(((Bundle) (obj)));
        return;
_L6:
        if ("com.google.android.calendar.intent.action.TRACKING_SYNC_INITIATED".equals(obj1))
        {
            if (((Bundle) (obj)).getInt("groove_operation", 0) == 1)
            {
                if (((Bundle) (obj)).containsKey("upsync_tracking_id"))
                {
                    LatencyLoggerHolder.get().markAt(19, ((Bundle) (obj)).getString("upsync_tracking_id"));
                    return;
                }
                if (((Bundle) (obj)).containsKey("force_sync_tracking_groove_id"))
                {
                    LatencyLoggerHolder.get().markAt(22, ((Bundle) (obj)).getString("force_sync_tracking_groove_id"));
                    return;
                }
            }
        } else
        {
label2:
            {
                if (!"com.google.android.calendar.intent.action.GROOVE_REQUEST_UPSYNCED".equals(obj1))
                {
                    break label2;
                }
                Object obj2 = ((Bundle) (obj)).getString("upsync_tracking_id");
                String s1 = ((Bundle) (obj)).getString("calendarId");
                Account account1 = (Account)((Bundle) (obj)).getParcelable("account");
                if (GrooveSyncTracker.instance == null)
                {
                    GrooveSyncTracker.instance = new GrooveSyncTracker();
                }
                obj1 = GrooveSyncTracker.instance;
                intent = this.context;
                obj2 = new HabitDescriptor(CalendarDescriptor.createWithoutLocalId(account1, s1), ((String) (obj2)));
                s1 = ((HabitDescriptor) (obj2)).habitId;
                k = ((Bundle) (obj)).getInt("groove_operation", 0);
                if (((GrooveSyncTracker) (obj1)).habitCreationListeners.get(s1) != null)
                {
                    long l6 = ((Bundle) (obj)).getLong("tracked_sync_duration", 0L);
                    if (intent != null)
                    {
                        obj = AnalyticsLoggerHolder.instance;
                        if (obj == null)
                        {
                            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                        }
                        ((AnalyticsLogger)obj).trackEvent(intent, "groove", "request_creation_sync", null, Long.valueOf(l6));
                    }
                    LatencyLoggerHolder.get().markAt(20, s1);
                    GrooveSyncTracker.scheduleForceInstancesSyncAlarm(intent, ((HabitDescriptor) (obj2)), 3750, k, null);
                    return;
                }
                if (((GrooveSyncTracker) (obj1)).habitOperations.contains(s1))
                {
                    GrooveSyncTracker.scheduleForceInstancesSyncAlarm(intent, ((HabitDescriptor) (obj2)), 10000, k, null);
                    return;
                }
                obj = ((Bundle) (obj)).getString("upsync_instance_tracking_id");
                if (obj != null && ((GrooveSyncTracker) (obj1)).deferredEvents.get(obj) != null)
                {
                    LatencyLoggerHolder.get().markAt(26, ((String) (obj)));
                    GrooveSyncTracker.scheduleForceInstancesSyncAlarm(intent, ((HabitDescriptor) (obj2)), 10000, k, ((String) (obj)));
                    return;
                }
            }
        }
          goto _L4
        if ("com.google.android.calendar.intent.action.REFRESH_GROOVE_NOTIFICATIONS".equals(obj1))
        {
            processRefreshNotificationsIntent(((Bundle) (obj)));
            return;
        }
        if (!"com.google.android.calendar.intent.action.HABITS_FORCE_SYNC".equals(obj1) || !((Bundle) (obj)).containsKey("account")) goto _L4; else goto _L52
_L52:
        boolean flag2;
        intent = ((Bundle) (obj)).getString("force_sync_tracking_groove_id");
        obj1 = ((Bundle) (obj)).getString("force_sync_instance_tracking_id");
        Account account = (Account)((Bundle) (obj)).getParcelable("account");
        Bundle bundle = new Bundle(3);
        bundle.putBoolean("force", true);
        bundle.putString("feed_internal", ((Bundle) (obj)).getString("feed_internal"));
        bundle.putString("force_sync_tracking_groove_id", intent);
        bundle.putString("force_sync_instance_tracking_id", ((String) (obj1)));
        flag2 = ((Bundle) (obj)).getBoolean("force_sync_log_time", false);
        bundle.putBoolean("force_sync_log_time", flag2);
        String s3 = android.provider.CalendarContract.Calendars.CONTENT_URI.getAuthority();
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).fishfood_debug();
        ContentResolver.requestSync(account, s3, bundle);
        k = ((Bundle) (obj)).getInt("groove_operation", 0);
        if (!flag2) goto _L4; else goto _L53
_L53:
        if (k == 2)
        {
            LatencyLoggerHolder.get().markAt(27, ((String) (obj1)));
            return;
        }
        if (k != 1) goto _L4; else goto _L54
_L54:
        LatencyLoggerHolder.get().markAt(21, intent);
        return;
        intent;
        obj = null;
          goto _L55
        intent;
        obj = null;
          goto _L56
_L26:
        intent = null;
          goto _L40
_L43:
        intent = null;
          goto _L50
        k = 0;
          goto _L51
    }


    private class _cls1
        implements FutureCallback
    {

        private final Context val$context;
        private final boolean val$done;
        private final EventKey val$eventKey;
        private final String val$label;

        public final void onFailure(Throwable throwable)
        {
            LogUtils.e(HabitsIntentServiceHelper.TAG, "Could not load habit instance: %s.", new Object[] {
                eventKey
            });
        }

        public final void onSuccess(Object obj)
        {
            if ((Event)obj == null)
            {
                LogUtils.e(HabitsIntentServiceHelper.TAG, "Could not mark habit instance as done: %s.", new Object[] {
                    eventKey
                });
            } else
            {
                if (eventKey.uri().isPresent())
                {
                    HabitsIntentServiceHelper.forceNotifyChange(context, (Uri)eventKey.uri().get());
                }
                Context context1;
                String s;
                if (done)
                {
                    obj = "marked_as_done";
                } else
                {
                    obj = "marked_as_not_done";
                }
                context1 = context;
                s = label;
                if (context1 != null)
                {
                    AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
                    if (analyticslogger == null)
                    {
                        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                    } else
                    {
                        ((AnalyticsLogger)analyticslogger).trackEvent(context1, "groove", ((String) (obj)), s, null);
                        return;
                    }
                }
            }
        }

        _cls1()
        {
            eventKey = eventkey;
            context = context1;
            done = flag;
            label = s;
            super();
        }
    }

}
