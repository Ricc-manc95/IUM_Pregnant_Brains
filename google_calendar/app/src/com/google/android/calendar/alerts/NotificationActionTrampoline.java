// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.common.activity.CalendarSupportActivity;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.alerts:
//            AlertActionIntentBuilder, QuickResponseActivity, ContactNotificationImpl, ContactNotification, 
//            AlertServiceHelper

public class NotificationActionTrampoline extends CalendarSupportActivity
{
    static final class RefreshNotificationsTask extends AsyncTask
    {

        private final Context applicationContext;

        protected final Object doInBackground(Object aobj[])
        {
            AlertServiceHelper.updateAlertNotification(applicationContext);
            return null;
        }

        RefreshNotificationsTask(Context context)
        {
            applicationContext = context.getApplicationContext();
        }
    }


    private static final Set ACTIONS_REQUIRING_PERMISSIONS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] {
        "com.google.android.calendar.MAP", "com.google.android.calendar.MAIL", "com.google.android.calendar.CONTACT"
    })));
    private AlertActionIntentBuilder intentBuilder;

    public NotificationActionTrampoline()
    {
    }

    public NotificationActionTrampoline(AlertActionIntentBuilder alertactionintentbuilder)
    {
        intentBuilder = alertactionintentbuilder;
    }

    static final ListenableFuture lambda$dismissWarning$1$NotificationActionTrampoline(EventModifications eventmodifications)
        throws Exception
    {
        return CalendarApi.Events.update(eventmodifications, 0, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED);
    }

    private final boolean startMap(EventKey eventkey)
    {
        boolean flag;
        AlertActionIntentBuilder alertactionintentbuilder = intentBuilder;
        Intent intent = alertactionintentbuilder.createMapActivityIntentFromStructuredLocation(eventkey);
        if (intent != null)
        {
            eventkey = intent;
        } else
        {
            eventkey = alertactionintentbuilder.createMapActivityIntentFromLegacyLocation(eventkey);
        }
        if (eventkey == null) goto _L2; else goto _L1
_L1:
        startActivity(eventkey);
        flag = true;
_L4:
        if (!flag)
        {
            Toast.makeText(getApplicationContext(), 0x7f13032d, 0).show();
        }
        return flag;
        eventkey;
        LogUtils.w("NotifActionTrampoline", "Was able to create a geoIntent for an event, but startActivity threw ActivityNotFoundException nonetheless.", new Object[0]);
_L2:
        flag = false;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void onCreate(final Bundle future)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        flag1 = true;
        flag = false;
        flag2 = false;
        Object obj;
        Object obj1;
        super.onCreate(future);
        if (intentBuilder == null)
        {
            intentBuilder = new AlertActionIntentBuilder(this);
        }
        obj = getIntent();
        obj1 = ((Intent) (obj)).getAction();
        future = EventKey.deserialize(((Intent) (obj)).getStringExtra("eventkey"));
        LogUtils.d("NotifActionTrampoline", "onReceive: %s", new Object[] {
            obj
        });
        if (obj1 != null && future != null)
        {
            break MISSING_BLOCK_LABEL_94;
        }
        LogUtils.e("NotifActionTrampoline", "Received intent without action or without event key", new Object[0]);
        finish();
        return;
        if (!ACTIONS_REQUIRING_PERMISSIONS.contains(obj1) || AndroidPermissionUtils.hasMandatoryPermissions(this))
        {
            break MISSING_BLOCK_LABEL_128;
        }
        Toast.makeText(this, 0x7f13034d, 1).show();
        finish();
        return;
        byte byte0 = -1;
        ((String) (obj1)).hashCode();
        JVM INSTR lookupswitch 3: default 616
    //                   -1141848960: 229
    //                   -560756585: 244
    //                   -175381165: 214;
           goto _L1 _L2 _L3 _L4
_L25:
        LogUtils.wtf("NotifActionTrampoline", "Invalid action: %s", new Object[] {
            obj1
        });
        flag = flag2;
_L9:
        if (flag) goto _L6; else goto _L5
_L5:
        (new RefreshNotificationsTask(this)).execute(new Void[0]);
_L6:
        finish();
        return;
_L4:
        if (((String) (obj1)).equals("com.google.android.calendar.MAP"))
        {
            byte0 = 0;
        }
          goto _L1
_L2:
        if (((String) (obj1)).equals("com.google.android.calendar.MAIL"))
        {
            byte0 = 1;
        }
          goto _L1
_L3:
        if (((String) (obj1)).equals("com.google.android.calendar.CONTACT"))
        {
            byte0 = 2;
        }
          goto _L1
_L26:
        flag = startMap(future);
        future = AnalyticsLoggerHolder.instance;
        if (future != null) goto _L8; else goto _L7
_L7:
        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        future;
        finish();
        throw future;
_L8:
        ((AnalyticsLogger)future).trackEvent(this, "notification", "map");
          goto _L9
_L27:
        flag1 = ((Intent) (obj)).getBooleanExtra("hasEveryoneDeclinedAction", false);
        if (!flag1)
        {
            flag = true;
        }
        obj = (new Intent(intentBuilder.context, com/google/android/calendar/alerts/QuickResponseActivity)).putExtra("eventKey", future).addFlags(0x10000000);
        ((Intent) (obj)).putExtra("showQuickResponses", flag);
        startActivity(((Intent) (obj)));
        if (!flag1) goto _L11; else goto _L10
_L10:
        future = CalendarApi.Events.read(future);
        if (!(future instanceof FluentFuture)) goto _L13; else goto _L12
_L12:
        future = (FluentFuture)future;
_L16:
        class .Lambda._cls0
            implements Function
        {

            private final NotificationActionTrampoline arg$1;

            public final Object apply(Object obj2)
            {
                NotificationActionTrampoline notificationactiontrampoline = arg$1;
                Event event = (Event)obj2;
                if (AttendeeUtils.isOrganizerAndListed(event))
                {
                    obj2 = "email_guests_organizer";
                } else
                {
                    obj2 = "email_guests";
                }
                LoggingUtils.logEveryoneDeclined(notificationactiontrampoline, ((String) (obj2)), false, event.getAttendees());
                obj2 = CalendarApi.EventFactory.modifyEvent(event);
                ((EventModifications) (obj2)).getGooglePrivateDataModification().setIsEveryoneDeclinedDismissed(true);
                return obj2;
            }

            .Lambda._cls0()
            {
                arg$1 = NotificationActionTrampoline.this;
            }
        }

        class .Lambda._cls1
            implements AsyncFunction
        {

            public static final AsyncFunction $instance = new .Lambda._cls1();

            public final ListenableFuture apply(Object obj2)
            {
                return NotificationActionTrampoline.lambda$dismissWarning$1$NotificationActionTrampoline((EventModifications)obj2);
            }


            private .Lambda._cls1()
            {
            }
        }

        future = (FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create(future, new .Lambda._cls0(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), .Lambda._cls1..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        obj = new _cls1();
        obj1 = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        if (obj != null) goto _L15; else goto _L14
_L14:
        throw new NullPointerException();
_L13:
        future = new ForwardingFluentFuture(future);
          goto _L16
_L15:
        future.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(future, ((FutureCallback) (obj))), ((java.util.concurrent.Executor) (obj1)));
_L11:
        future = AnalyticsLoggerHolder.instance;
        if (future != null) goto _L18; else goto _L17
_L17:
        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
_L18:
        ((AnalyticsLogger)future).trackEvent(this, "notification", "mail");
        flag = true;
          goto _L9
_L28:
        future = new ContactNotificationImpl(intentBuilder.context, future);
        if (!future.isValid())
        {
            future = null;
        }
          goto _L19
_L24:
        if (!flag) goto _L21; else goto _L20
_L20:
        overridePendingTransition(0x10a0000, 0);
_L21:
        future = AnalyticsLoggerHolder.instance;
        if (future != null) goto _L23; else goto _L22
_L22:
        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
_L30:
        future.startActivity();
        flag = flag1;
          goto _L24
_L23:
        ((AnalyticsLogger)future).trackEvent(this, "notification", "contact");
          goto _L9
_L1:
        byte0;
        JVM INSTR tableswitch 0 2: default 644
    //                   0 259
    //                   1 311
    //                   2 523;
           goto _L25 _L26 _L27 _L28
_L19:
        if (future != null) goto _L30; else goto _L29
_L29:
        flag = false;
          goto _L24
    }


    private class _cls1
        implements FutureCallback
    {

        private final FluentFuture val$future;

        public final void onFailure(Throwable throwable)
        {
            LogUtils.logOnFailure(future, "NotifActionTrampoline", "Failed to dismiss everyone declined warning.", new Object[0]);
        }

        public final volatile void onSuccess(Object obj)
        {
        }

        _cls1()
        {
            future = fluentfuture;
            super();
        }
    }

}
