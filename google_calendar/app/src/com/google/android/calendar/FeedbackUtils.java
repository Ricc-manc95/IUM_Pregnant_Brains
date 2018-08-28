// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timebox.EventsApiImpl;
import com.google.android.apps.calendar.timebox.Item;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.groove.GroovePostCreationBottomSheet;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timebox.adapter.TimeBoxToTimelineAdapter;
import com.google.android.calendar.timely.BottomSheet;
import com.google.android.calendar.utils.ActivitySingletonCache;
import com.google.android.calendar.utils.snackbar.SnackbarUtils;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar:
//            Utils, AllInOneCalendarActivity, CalendarController

public class FeedbackUtils
{
    public static final class FeedbackBroadcastReceiver extends BroadcastReceiver
    {

        public final AllInOneCalendarActivity activity;

        public final void onReceive(Context context, Intent intent)
        {
            FeedbackUtils.showFeedbackInActivity(activity, intent);
        }

        public FeedbackBroadcastReceiver(AllInOneCalendarActivity allinonecalendaractivity)
        {
            activity = allinonecalendaractivity;
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/FeedbackUtils);

    public FeedbackUtils()
    {
    }

    static final void lambda$showSnackbarFeedbackInActivity$0$FeedbackUtils$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFC5O6IBR5EPIMST1F8LR6ARJK8HIN6ORID5O78RRI7D662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0(EventDescriptor eventdescriptor)
    {
        CalendarApi.Events.delete(eventdescriptor, 0, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED);
    }

    static final TimeZone lambda$showSnackbarFeedbackInActivity$1$FeedbackUtils(AllInOneCalendarActivity allinonecalendaractivity)
    {
        return Utils.getTimeZone(allinonecalendaractivity);
    }

    static final void lambda$showSnackbarFeedbackInActivity$2$FeedbackUtils(AllInOneCalendarActivity allinonecalendaractivity, TimeRangeEntry timerangeentry)
    {
        allinonecalendaractivity.launchInfoBubble((new TimeBoxToTimelineAdapter(allinonecalendaractivity)).createEvent((Item)timerangeentry.getValue(), timerangeentry.getRange()), null, null);
    }

    static final void lambda$showSnackbarFeedbackInActivity$3$FeedbackUtils$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHF85M6OIBE9TN6AGR1DHIMSP31E90M6T39EPKN8U9R9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBRLEHKMOBR3DTN66TBIE9IMST1F8PM7APBEEH37AT3LE9IJMJ31DPI74RR9CGNNCQB5ESNLCQB5ESTIILG_0(AllInOneCalendarActivity allinonecalendaractivity, FluentFuture fluentfuture)
    {
        if (allinonecalendaractivity != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(allinonecalendaractivity, "groove", "view_link_clicked", "", null);
        }
        class .Lambda._cls3
            implements Consumer
        {

            private final AllInOneCalendarActivity arg$1;

            public final void accept(Object obj)
            {
                FeedbackUtils.lambda$showSnackbarFeedbackInActivity$2$FeedbackUtils(arg$1, (TimeRangeEntry)obj);
            }

            .Lambda._cls3(AllInOneCalendarActivity allinonecalendaractivity)
            {
                arg$1 = allinonecalendaractivity;
            }
        }

        allinonecalendaractivity = LogUtils.newFailureLoggingCallback(new .Lambda._cls3(allinonecalendaractivity), TAG, "Unable to load event", new Object[0]);
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        if (allinonecalendaractivity == null)
        {
            throw new NullPointerException();
        } else
        {
            fluentfuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(fluentfuture, allinonecalendaractivity), calendarexecutor);
            return;
        }
    }

    private static EventDescriptor readDescriptor(EventKey eventkey)
    {
        try
        {
            eventkey = (EventDescriptor)CalendarApi.Events.readDescriptor(eventkey).get();
        }
        // Misplaced declaration of an exception variable
        catch (EventKey eventkey)
        {
            LogUtils.e(TAG, eventkey, "Unable to load descriptor", new Object[0]);
            return null;
        }
        return eventkey;
    }

    static void showFeedbackInActivity(Activity activity, Intent intent)
    {
        AllInOneCalendarActivity allinonecalendaractivity;
        if (!(activity instanceof AllInOneCalendarActivity))
        {
            return;
        }
        allinonecalendaractivity = (AllInOneCalendarActivity)activity;
        intent.getIntExtra("feedbackType", 0);
        JVM INSTR tableswitch 1 1: default 40
    //                   1 133;
           goto _L1 _L2
_L1:
        EventKey eventkey;
        String s1 = intent.getStringExtra("feedbackMessage");
        Time time;
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        long l1;
        long l2;
        if (intent.getBooleanExtra("shortLength", true))
        {
            i = -1;
        } else
        {
            i = 0;
        }
        activity = allinonecalendaractivity.getResources();
        if (!intent.hasExtra("eventKey")) goto _L4; else goto _L3
_L3:
        eventkey = (EventKey)intent.getParcelableExtra("eventKey");
        intent.getIntExtra("eventAction", 0);
        JVM INSTR tableswitch 1 2: default 116
    //                   1 487
    //                   2 448;
           goto _L5 _L6 _L7
_L5:
        activity = null;
        intent = null;
_L9:
        SnackbarUtils.showSnackbar(allinonecalendaractivity, s1, i, activity, intent, null);
        return;
_L2:
        l2 = intent.getLongExtra("timeMillis", -1L);
        activity = (EventKey)intent.getParcelableExtra("eventKey");
        if (Clock.mockedTimestamp > 0L)
        {
            l1 = Clock.mockedTimestamp;
        } else
        {
            l1 = System.currentTimeMillis();
        }
        activity = new GroovePostCreationBottomSheet(allinonecalendaractivity, allinonecalendaractivity.getString(0x7f1300be, new Object[] {
            Utils.getDisplayedDatetime(l2, l2, l1, Utils.getTimeZoneId(allinonecalendaractivity), false, false, allinonecalendaractivity)
        }), activity);
        ((ViewGroup)allinonecalendaractivity.findViewById(0x7f100122)).addView(activity);
        activity.show();
        activity = new Time();
        ((Time) (activity)).impl.timezone = ((Time) (activity)).timezone;
        ((Time) (activity)).impl.set(l2);
        ((Time) (activity)).impl.toMillis(true);
        activity.copyFieldsFromImpl();
        intent = Utils.getTimeZoneId(allinonecalendaractivity);
        activity.writeFieldsToImpl();
        ((Time) (activity)).impl.switchTimezone(intent);
        activity.copyFieldsFromImpl();
        intent = (CalendarController)CalendarController.instances.getInstance(allinonecalendaractivity);
        activity.writeFieldsToImpl();
        i = android.text.format.Time.getJulianDay(((Time) (activity)).impl.toMillis(false), ((Time) (activity)).gmtoff);
        if (i < ((CalendarController) (intent)).startDay || i > ((CalendarController) (intent)).endDay)
        {
            intent.goTo(allinonecalendaractivity, activity, 0L);
            return;
        } else
        {
            time = new Time();
            time.setJulianDaySafe(((CalendarController) (intent)).startDay);
            i = ((Time) (activity)).second;
            j = ((Time) (activity)).minute;
            k = ((Time) (activity)).hour;
            l = time.monthDay;
            i1 = time.month;
            j1 = time.year;
            time.writeFieldsToImpl();
            time.impl.set(i, j, k, l, i1, j1);
            time.copyFieldsFromImpl();
            time.normalizeSafe();
            intent.goTo(allinonecalendaractivity, time, 0L);
            return;
        }
_L7:
        String s = activity.getString(0x7f130080);
        activity = readDescriptor(eventkey);
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final EventDescriptor arg$1;

            public final void onClick(View view)
            {
                FeedbackUtils.lambda$showSnackbarFeedbackInActivity$0$FeedbackUtils$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFC5O6IBR5EPIMST1F8LR6ARJK8HIN6ORID5O78RRI7D662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0(arg$1);
            }

            .Lambda._cls0(EventDescriptor eventdescriptor)
            {
                arg$1 = eventdescriptor;
            }
        }

        if (activity != null)
        {
            activity = new .Lambda._cls0(activity);
        } else
        {
            activity = null;
        }
        intent = activity;
        activity = s;
        continue; /* Loop/switch isn't completed */
_L6:
        activity = activity.getString(0x7f130088);
        class .Lambda._cls2
            implements android.view.View.OnClickListener
        {

            private final AllInOneCalendarActivity arg$1;
            private final FluentFuture arg$2;

            public final void onClick(View view)
            {
                FeedbackUtils.lambda$showSnackbarFeedbackInActivity$3$FeedbackUtils$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHF85M6OIBE9TN6AGR1DHIMSP31E90M6T39EPKN8U9R9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBRLEHKMOBR3DTN66TBIE9IMST1F8PM7APBEEH37AT3LE9IJMJ31DPI74RR9CGNNCQB5ESNLCQB5ESTIILG_0(arg$1, arg$2);
            }

            .Lambda._cls2(AllInOneCalendarActivity allinonecalendaractivity, FluentFuture fluentfuture)
            {
                arg$1 = allinonecalendaractivity;
                arg$2 = fluentfuture;
            }
        }

        class .Lambda._cls1
            implements Supplier
        {

            private final AllInOneCalendarActivity arg$1;

            public final Object get()
            {
                return FeedbackUtils.lambda$showSnackbarFeedbackInActivity$1$FeedbackUtils(arg$1);
            }

            .Lambda._cls1(AllInOneCalendarActivity allinonecalendaractivity)
            {
                arg$1 = allinonecalendaractivity;
            }
        }

        intent = new .Lambda._cls2(allinonecalendaractivity, (new EventsApiImpl(allinonecalendaractivity, new .Lambda._cls1(allinonecalendaractivity))).getAsync(eventkey));
        continue; /* Loop/switch isn't completed */
_L4:
        activity = null;
        intent = null;
        if (true) goto _L9; else goto _L8
_L8:
    }

}
