// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationCheckOrigin;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationPlugin;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationState;
import com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog;
import com.google.android.apps.calendar.usernotificationsframework.storage.UserNotificationStore;
import com.google.android.calendar.time.clock.Clock;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.CombinedFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.TrustedListenableFutureTask;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.common:
//            UserNotificationProcessor

public class UserNotificationManager
{

    public static final long BECOMES_OUTDATED_AFTER_INTERVAL_MILLIS;
    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/usernotificationsframework/common/UserNotificationManager);
    public static UserNotificationManager instance;
    public final ImmutableMap notificationProcessorsMap;
    public final UserNotificationStore store;

    public UserNotificationManager(Context context, Collection collection)
    {
        store = new UserNotificationStore(context);
        context = new com.google.common.collect.ImmutableMap.Builder();
        UserNotificationPlugin usernotificationplugin;
        for (collection = collection.iterator(); collection.hasNext(); context.put(Integer.valueOf(usernotificationplugin.getId()), new UserNotificationProcessor(store, usernotificationplugin)))
        {
            usernotificationplugin = (UserNotificationPlugin)collection.next();
        }

        notificationProcessorsMap = context.build();
    }

    public static void cleanup(Context context, Runnable runnable)
    {
        if (!UserNotificationStore.exists(context))
        {
            return;
        } else
        {
            class .Lambda._cls4
                implements AsyncCallable
            {

                private final Runnable arg$1;

                public final ListenableFuture call()
                {
                    return UserNotificationManager.lambda$logOnFailureAsync$4$UserNotificationManager(arg$1);
                }

            .Lambda._cls4(Runnable runnable)
            {
                arg$1 = runnable;
            }
            }

            Object obj = new .Lambda._cls4(runnable);
            runnable = UserNotificationProcessor.SERIAL_EXECUTOR;
            obj = new TrustedListenableFutureTask(((AsyncCallable) (obj)));
            runnable.execute(((Runnable) (obj)));
            NotificationLog.logOnFailure(((ListenableFuture) (obj)), TAG, "Failed in cleanup callback.", new Object[0]);
            class .Lambda._cls0
                implements Runnable
            {

                private final Context arg$1;

                public final void run()
                {
                    UserNotificationManager.lambda$cleanup$0$UserNotificationManager(arg$1);
                }

            .Lambda._cls0(Context context)
            {
                arg$1 = context;
            }
            }

            obj = new .Lambda._cls4(new .Lambda._cls0(context));
            runnable = UserNotificationProcessor.SERIAL_EXECUTOR;
            obj = new TrustedListenableFutureTask(((AsyncCallable) (obj)));
            runnable.execute(((Runnable) (obj)));
            NotificationLog.logOnFailure(((ListenableFuture) (obj)), TAG, "Failed to remove database.", new Object[0]);
            class .Lambda._cls1
                implements Runnable
            {

                private final Context arg$1;

                public final void run()
                {
                    UserNotificationManager.lambda$cleanup$1$UserNotificationManager(arg$1);
                }

            .Lambda._cls1(Context context)
            {
                arg$1 = context;
            }
            }

            runnable = new .Lambda._cls4(new .Lambda._cls1(context));
            context = UserNotificationProcessor.SERIAL_EXECUTOR;
            runnable = new TrustedListenableFutureTask(runnable);
            context.execute(runnable);
            NotificationLog.logOnFailure(runnable, TAG, "Failed to remove notification log.", new Object[0]);
            return;
        }
    }

    public static boolean hasBeenUsed(Context context)
    {
        return UserNotificationStore.exists(context);
    }

    static final void lambda$cleanup$0$UserNotificationManager(Context context)
    {
        UserNotificationStore.cleanup(context);
    }

    static final void lambda$cleanup$1$UserNotificationManager(Context context)
    {
        NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog..Lambda._cls2(context));
    }

    static final ListenableFuture lambda$logOnFailureAsync$4$UserNotificationManager(Runnable runnable)
        throws Exception
    {
        runnable.run();
        if (true)
        {
            return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
        }
    }

    public static transient void logOnFailureAsync(Runnable runnable, String s, Object aobj[])
    {
        Object obj = new .Lambda._cls4(runnable);
        runnable = UserNotificationProcessor.SERIAL_EXECUTOR;
        obj = new TrustedListenableFutureTask(((AsyncCallable) (obj)));
        runnable.execute(((Runnable) (obj)));
        NotificationLog.logOnFailure(((ListenableFuture) (obj)), TAG, s, aobj);
    }

    public final void check(Context context, String s)
    {
        Integer integer;
        for (UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)((ImmutableSet)notificationProcessorsMap.keySet()).iterator(); unmodifiableiterator.hasNext(); NotificationLog.logOnFailure(checkInternal(context, integer, UserNotificationCheckOrigin.EXPLICIT_CALL, s), TAG, "Failed to check notifications for plugin='%s'.", new Object[] {
    integer
}))
        {
            integer = (Integer)unmodifiableiterator.next();
        }

    }

    public final ListenableFuture checkInternal(Context context, Integer integer, UserNotificationCheckOrigin usernotificationcheckorigin, String s)
    {
        if (notificationProcessorsMap.containsKey(integer))
        {
            integer = (UserNotificationProcessor)notificationProcessorsMap.get(integer);
            Object obj;
            Object obj1;
            int i;
            long l;
            long l1;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            obj = UserNotificationProcessor.TAG;
            i = ((UserNotificationProcessor) (integer)).userNotificationPlugin.getId();
            obj1 = NotificationLog.formatTime(l);
            NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog..Lambda._cls3(((String) (obj)), "Checking for updates: plugin_id='%s', time='%s', origin='%s', reason='%s'.", new Object[] {
                Integer.valueOf(i), obj1, usernotificationcheckorigin, s
            }));
            obj = new UserNotificationProcessor..Lambda._cls0(integer);
            s = UserNotificationProcessor.SERIAL_EXECUTOR;
            obj = new TrustedListenableFutureTask(((AsyncCallable) (obj)));
            s.execute(((Runnable) (obj)));
            l1 = UserNotificationProcessor.DEFAULT_NOTIFICATION_CHECK_WINDOW_MILLIS + l;
            obj1 = ((UserNotificationProcessor) (integer)).userNotificationPlugin.getRelevantNotifications(l, l1);
            s = new com.google.common.util.concurrent.Futures.FutureCombiner(true, ImmutableList.copyOf(new ListenableFuture[] {
                obj, obj1
            }));
            integer = new UserNotificationProcessor..Lambda._cls1(integer, context, l, usernotificationcheckorigin, ((ListenableFuture) (obj1)), ((ListenableFuture) (obj)), l1);
            usernotificationcheckorigin = UserNotificationProcessor.SERIAL_EXECUTOR;
            integer = new CombinedFuture(((com.google.common.util.concurrent.Futures.FutureCombiner) (s)).futures, ((com.google.common.util.concurrent.Futures.FutureCombiner) (s)).allMustSucceed, usernotificationcheckorigin, integer);
            context = new UserNotificationProcessor._cls1(context);
            usernotificationcheckorigin = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
            if (context == null)
            {
                throw new NullPointerException();
            } else
            {
                integer.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(integer, context), usernotificationcheckorigin);
                return integer;
            }
        }
        if (true)
        {
            return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
        }
    }

    final ListenableFuture lambda$markShownNotificationsAsFired$2$UserNotificationManager()
        throws Exception
    {
        UserNotificationStore usernotificationstore = store;
        try
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("notificationState", Integer.valueOf(UserNotificationState.FIRED.ordinal()));
            usernotificationstore.database.update("notificationinstances", contentvalues, "notificationState=?", new String[] {
                String.valueOf(UserNotificationState.SHOWN.ordinal())
            });
        }
        catch (Exception exception)
        {
            LogUtils.e(UserNotificationStore.TAG, exception, "Failed to mark shown notifications as fired.", new Object[0]);
        }
        if (true)
        {
            return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
        }
    }

    static 
    {
        BECOMES_OUTDATED_AFTER_INTERVAL_MILLIS = TimeUnit.DAYS.toMillis(1L);
    }
}
