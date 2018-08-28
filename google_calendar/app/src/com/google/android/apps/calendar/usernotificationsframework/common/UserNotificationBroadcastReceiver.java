// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationCheckOrigin;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationState;
import com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog;
import com.google.android.calendar.utils.version.MncUtil;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.TrustedListenableFutureTask;
import java.io.Serializable;
import java.util.Iterator;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.common:
//            UserNotificationManager, UserNotificationProcessor

public class UserNotificationBroadcastReceiver extends BroadcastReceiver
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/usernotificationsframework/common/UserNotificationBroadcastReceiver);

    public UserNotificationBroadcastReceiver()
    {
    }

    static void attachStateUpdateToUserAction(Intent intent, UserNotification usernotification, UserNotificationState usernotificationstate, Optional optional)
    {
        intent.putExtra("userNotification", usernotification);
        intent.putExtra("userNotificationState", usernotificationstate.ordinal());
        if (optional.isPresent())
        {
            intent.putExtra("userNotificationActionCode", (Serializable)optional.get());
        }
    }

    static final ListenableFuture lambda$onReceive$0$UserNotificationBroadcastReceiver$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D66KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFAPNMIP1R55666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T66ISRKCLN62OJCCL37AT3LE9IJM___0(Context context, String s)
        throws Exception
    {
        Object obj = UserNotificationManager.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Call initialize method first."));
        }
        UserNotificationManager usernotificationmanager = (UserNotificationManager)obj;
        UserNotificationCheckOrigin usernotificationcheckorigin = UserNotificationCheckOrigin.EXPLICIT_CALL;
        UnmodifiableIterator unmodifiableiterator;
        if (true)
        {
            obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
        }
        for (unmodifiableiterator = (UnmodifiableIterator)((ImmutableSet)usernotificationmanager.notificationProcessorsMap.keySet()).iterator(); unmodifiableiterator.hasNext();)
        {
            obj = AbstractTransformFuture.create(((ListenableFuture) (obj)), new UserNotificationManager..Lambda._cls3(usernotificationmanager, context, (Integer)unmodifiableiterator.next(), usernotificationcheckorigin, s), UserNotificationProcessor.SERIAL_EXECUTOR);
        }

        return ((ListenableFuture) (obj));
    }

    static void scheduleCheckBroadcast(Context context, long l, int i, boolean flag)
    {
        Object obj1 = new Intent(context, com/google/android/apps/calendar/usernotificationsframework/common/UserNotificationBroadcastReceiver);
        Object obj;
        int j;
        if (flag)
        {
            obj = "com.google.android.calendar.intent.action.CHECK_NOTIFICATIONS";
        } else
        {
            obj = "com.google.android.calendar.intent.action.CHECK_NOTIFICATIONS_WHEN_AWAKE";
        }
        ((Intent) (obj1)).setAction(((String) (obj)));
        ((Intent) (obj1)).putExtra("entitySources", i);
        obj = PendingIntent.getBroadcast(context, i, ((Intent) (obj1)), 0x8000000);
        context = (AlarmManager)context.getSystemService("alarm");
        if (flag)
        {
            j = 0;
        } else
        {
            j = 1;
        }
        if (MncUtil.isMnc())
        {
            context.setExactAndAllowWhileIdle(j, l, ((PendingIntent) (obj)));
        } else
        {
            context.setExact(j, l, ((PendingIntent) (obj)));
        }
        obj = TAG;
        if (flag)
        {
            context = "waking";
        } else
        {
            context = "non-waking";
        }
        obj1 = NotificationLog.formatTime(l);
        NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog..Lambda._cls3(((String) (obj)), "Next %s check scheduled: plugin_id='%s', time='%s'.", new Object[] {
            context, Integer.valueOf(i), obj1
        }));
    }

    static ListenableFuture updateNotificationFromIntentAsync(Intent intent)
    {
        if (!intent.hasExtra("userNotification") || !intent.hasExtra("userNotificationState"))
        {
            intent = TAG;
            NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog..Lambda._cls3(intent, "Processing an intent that does not have necessary fields supplied.", new Object[0]));
            if (true)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
            }
        }
        Object obj = (UserNotification)intent.getParcelableExtra("userNotification");
        int i = intent.getIntExtra("userNotificationState", UserNotificationState.DISMISSED.ordinal());
        UserNotificationState usernotificationstate = UserNotificationState.values()[i];
        UserNotificationManager usernotificationmanager;
        if (intent.hasExtra("userNotificationActionCode"))
        {
            intent = Integer.valueOf(intent.getIntExtra("userNotificationActionCode", -1));
        } else
        {
            intent = null;
        }
        if (intent == null)
        {
            intent = Absent.INSTANCE;
        } else
        {
            intent = new Present(intent);
        }
        usernotificationmanager = UserNotificationManager.instance;
        if (usernotificationmanager == null)
        {
            throw new NullPointerException(String.valueOf("Call initialize method first."));
        } else
        {
            obj = new UserNotificationProcessor..Lambda._cls2((UserNotificationProcessor)((UserNotificationManager)usernotificationmanager).notificationProcessorsMap.get(Integer.valueOf(((UserNotification) (obj)).getPluginId())), ((UserNotification) (obj)), usernotificationstate, intent);
            intent = UserNotificationProcessor.SERIAL_EXECUTOR;
            obj = new TrustedListenableFutureTask(((com.google.common.util.concurrent.AsyncCallable) (obj)));
            intent.execute(((Runnable) (obj)));
            return ((ListenableFuture) (obj));
        }
    }

    public void onReceive(Context context, Intent intent)
    {
        final Object action;
        boolean flag;
        if (UserNotificationManager.instance != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            context = TAG;
            NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog..Lambda._cls3(context, "Managing the state when UserNotificationManager is not initialized.", new Object[0]));
            return;
        }
        action = intent.getAction();
        String s = TAG;
        NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog..Lambda._cls3(s, "Received an action: %s.", new Object[] {
            action
        }));
        ((String) (action)).hashCode();
        JVM INSTR lookupswitch 4: default 132
    //                   -861958830: 230
    //                   -485570974: 200
    //                   798292259: 246
    //                   1558041117: 215;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        byte byte0 = -1;
_L11:
        byte0;
        JVM INSTR tableswitch 0 3: default 168
    //                   0 262
    //                   1 262
    //                   2 361
    //                   3 369;
           goto _L6 _L7 _L7 _L8 _L9
_L6:
        context = TAG;
        NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog..Lambda._cls4(context, "Illegal action: %s.", new Object[] {
            action
        }));
        return;
_L3:
        if (!((String) (action)).equals("com.google.android.calendar.intent.action.CHECK_NOTIFICATIONS")) goto _L1; else goto _L10
_L10:
        byte0 = 0;
          goto _L11
_L5:
        if (!((String) (action)).equals("com.google.android.calendar.intent.action.CHECK_NOTIFICATIONS_WHEN_AWAKE")) goto _L1; else goto _L12
_L12:
        byte0 = 1;
          goto _L11
_L2:
        if (!((String) (action)).equals("com.google.android.calendar.intent.action.UPDATE_NOTIFICATION")) goto _L1; else goto _L13
_L13:
        byte0 = 2;
          goto _L11
_L4:
        if (!((String) (action)).equals("android.intent.action.BOOT_COMPLETED")) goto _L1; else goto _L14
_L14:
        byte0 = 3;
          goto _L11
_L7:
        int i = intent.getIntExtra("entitySources", -1);
        intent = UserNotificationManager.instance;
        if (intent == null)
        {
            throw new NullPointerException(String.valueOf("Call initialize method first."));
        }
        UserNotificationManager usernotificationmanager = (UserNotificationManager)intent;
        if (((String) (action)).equals("com.google.android.calendar.intent.action.CHECK_NOTIFICATIONS"))
        {
            intent = UserNotificationCheckOrigin.WAKING_BROADCAST;
        } else
        {
            intent = UserNotificationCheckOrigin.NON_WAKING_BROADCAST;
        }
        context = usernotificationmanager.checkInternal(context, Integer.valueOf(i), intent, ((String) (action)));
_L15:
        intent = new _cls1();
        action = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        class .Lambda._cls0
            implements AsyncFunction
        {

            private final Context arg$1;
            private final String arg$2;

            public final ListenableFuture apply(Object obj1)
            {
                return UserNotificationBroadcastReceiver.lambda$onReceive$0$UserNotificationBroadcastReceiver$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D66KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFAPNMIP1R55666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T66ISRKCLN62OJCCL37AT3LE9IJM___0(arg$1, arg$2);
            }

            .Lambda._cls0(Context context, String s)
            {
                arg$1 = context;
                arg$2 = s;
            }
        }

        Object obj;
        if (intent == null)
        {
            throw new NullPointerException();
        } else
        {
            context.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(context, intent), ((Executor) (action)));
            return;
        }
_L8:
        context = updateNotificationFromIntentAsync(intent);
          goto _L15
_L9:
        intent = UserNotificationManager.instance;
        if (intent == null)
        {
            throw new NullPointerException(String.valueOf("Call initialize method first."));
        }
        obj = new UserNotificationManager..Lambda._cls2((UserNotificationManager)intent);
        intent = UserNotificationProcessor.SERIAL_EXECUTOR;
        obj = new TrustedListenableFutureTask(((com.google.common.util.concurrent.AsyncCallable) (obj)));
        intent.execute(((Runnable) (obj)));
        context = AbstractTransformFuture.create(((ListenableFuture) (obj)), new .Lambda._cls0(context, ((String) (action))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
          goto _L15
    }


    private class _cls1
        implements FutureCallback
    {

        private final String val$action;
        private final android.content.BroadcastReceiver.PendingResult val$finishIntent;

        public final void onFailure(Throwable throwable)
        {
            String s = UserNotificationBroadcastReceiver.TAG;
            String s1 = action;
            NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog..Lambda._cls5(s, throwable, "Failed on action: %s.", new Object[] {
                s1
            }));
            finishIntent.finish();
        }

        public final void onSuccess(Object obj)
        {
            obj = UserNotificationBroadcastReceiver.TAG;
            String s = action;
            NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog..Lambda._cls3(((String) (obj)), "Processed the action: %s.", new Object[] {
                s
            }));
            finishIntent.finish();
        }

        _cls1()
        {
            action = s;
            finishIntent = pendingresult;
            super();
        }
    }

}
