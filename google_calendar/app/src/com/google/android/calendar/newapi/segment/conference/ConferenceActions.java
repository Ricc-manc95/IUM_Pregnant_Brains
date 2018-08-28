// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.conference.Conference;
import com.google.android.calendar.event.ConferenceCallUtils;
import com.google.android.calendar.experimental.ExperimentalOptions;
import com.google.android.calendar.newapi.model.ConferenceEvent;
import com.google.android.calendar.utils.activity.ActivityUtils;
import com.google.android.calendar.utils.phone.PhoneUtil;

// Referenced classes of package com.google.android.calendar.newapi.segment.conference:
//            PhoneNumberDetailsFactory, ConferenceTypes

class ConferenceActions
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/conference/ConferenceActions);

    ConferenceActions()
    {
    }

    static void dialPhoneConference(Context context, PhoneUtil phoneutil, ConferenceEvent conferenceevent, Event event)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "event_action", "join_conference_by_phone");
            ConferenceCallUtils.dialPhoneConference(phoneutil, PhoneNumberDetailsFactory.create(conferenceevent, event));
            return;
        }
    }

    static void joinLiveStream(Context context, Event event)
    {
        event = ConferenceTypes.getLiveStreamConference(context, event.getConferenceData());
        if (event != null)
        {
            ActivityUtils.startActivityForUrl(context, event.getUri(), TAG);
        }
    }

    static void joinVideoConference(Context context, Conference conference, Account account)
    {
        String s;
        AnalyticsLogger analyticslogger;
        boolean flag;
        if (conference.getType() == 3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            s = "join_meeting";
        } else
        {
            s = "join_hangout";
        }
        analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)analyticslogger).trackEvent(context, "event_action", s);
        if (flag && ActivityUtils.startActivityForUrlWithApp(context, conference.getUri(), "com.google.android.apps.meetings", null, account.name, TAG))
        {
            return;
        } else
        {
            ActivityUtils.startActivityForUrl(context, conference.getUri(), TAG);
            return;
        }
    }

    static void showInteropInstructions(Context context, Conference conference, Event event)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "event_action", "conference_interop_instructions");
            ConferenceCallUtils.showInteropInstructions(context, Uri.parse(conference.getUri()), event.getColor().getValue());
            return;
        }
    }

    static void showMoreThorPhones(Context context, Conference conference, Event event)
    {
        boolean flag = true;
        Object obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)obj).trackEvent(context, "event_action", "conference_more_phones");
        Object obj1 = Uri.parse(conference.getUri());
        obj = obj1;
        if (conference.getGatewayAccess() == 1)
        {
            obj = ((Uri) (obj1)).buildUpon().appendQueryParameter("ijf", "1").build();
        }
        obj1 = event.getCalendar().account;
        int i = event.getColor().getValue();
        boolean flag1 = ExperimentalOptions.shouldThorFetchGstatic(context);
        if (conference.getGatewayAccess() != 1)
        {
            flag = false;
        }
        ConferenceCallUtils.showMoreThorPhones(context, ((Uri) (obj)), ((Account) (obj1)), i, flag1, flag);
    }

}
