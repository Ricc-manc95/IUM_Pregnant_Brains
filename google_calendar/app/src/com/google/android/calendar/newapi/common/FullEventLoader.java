// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;

import android.content.Context;
import com.google.android.apps.calendar.meetings.impl.MeetingsPstnFinderImpl;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.experimental.ExperimentalOptions;
import com.google.android.calendar.newapi.exchange.EasSupport;
import com.google.android.calendar.newapi.exchange.EasSupportLoader;
import com.google.android.calendar.newapi.meetings.LocalPhoneNumberLoader;
import com.google.android.calendar.newapi.segment.conference.ConferenceTypes;
import com.google.android.calendar.utils.account.AccountUtil;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.common:
//            SerialLoader, EventLoader, CompositeLoader, EventInfoLoader, 
//            AsyncTaskLoader

public final class FullEventLoader extends SerialLoader
{

    private EventInfoLoader eventInfoLoader;
    private EventLoader eventLoader;

    public FullEventLoader(Context context, EventDescriptor eventdescriptor)
    {
        super(new EventCalendarResult());
        eventdescriptor = new EventLoader(eventdescriptor);
        eventLoader = eventdescriptor;
        super.loaders.add(eventdescriptor);
        context = new EventInfoLoader(context);
        eventInfoLoader = context;
        super.loaders.add(context);
    }

    protected final void onLoaderFinished(int i, Object obj)
    {
        Object obj1 = (EventCalendarResult)obj;
        if (i == 0)
        {
            obj1.event = (Event)eventLoader.getResult();
            obj = eventInfoLoader;
            obj1 = ((EventCalendarResult) (obj1)).event;
            Object obj2;
            boolean flag;
            if (EasSupport.proposeTimeSupported != null && EasSupport.addNoteSupported != null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                flag = false;
            } else
            if (obj1 == null || ((Event) (obj1)).getDescriptor() == null || ((Event) (obj1)).getCalendar() == null)
            {
                flag = false;
            } else
            {
                flag = AccountUtil.isGoogleExchangeAccount(((Event) (obj1)).getCalendar().account);
            }
            if (flag)
            {
                EasSupportLoader eassupportloader = new EasSupportLoader(((EventInfoLoader) (obj)).context);
                ((CompositeLoader) (obj)).loaders.add(eassupportloader);
            }
            obj2 = ((Event) (obj1)).getConferenceData();
            obj1 = ConferenceTypes.getPhoneConference(((com.google.android.calendar.api.event.conference.ConferenceData) (obj2)));
            obj2 = ConferenceTypes.getPhoneNumbersLinkConference(((com.google.android.calendar.api.event.conference.ConferenceData) (obj2)));
            if (obj1 != null && obj2 != null)
            {
                Context context = ((EventInfoLoader) (obj)).context.getApplicationContext();
                obj.localPhoneLoader = new LocalPhoneNumberLoader(((com.google.android.calendar.api.event.conference.Conference) (obj1)), ((com.google.android.calendar.api.event.conference.Conference) (obj2)), new MeetingsPstnFinderImpl(context, ExperimentalOptions.shouldThorFetchGstatic(context)));
                obj1 = ((EventInfoLoader) (obj)).localPhoneLoader;
                ((CompositeLoader) (obj)).loaders.add(obj1);
            }
            return;
        } else
        {
            obj1.localPhone = ((EventInfoLoader.EventInfoResult)eventInfoLoader.getResult()).localPhone;
            return;
        }
    }

    private class EventCalendarResult
    {

        public Event event;
        public PhoneNumberDetails localPhone;

        public EventCalendarResult()
        {
        }
    }

}
