// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.content.Context;
import com.google.android.calendar.api.calendarlist.CalendarListFilterOptions;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.event.conference.PhoneNumberDetails;
import com.google.android.calendar.newapi.common.AsyncTaskLoader;
import com.google.android.calendar.newapi.common.CompositeLoader;
import com.google.android.calendar.newapi.common.loader.CalendarListLoader;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.model.EventViewScreenModel;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.common.base.Optional;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            BasicViewScreenLoader

public class EventViewScreenLoader extends BasicViewScreenLoader
{

    public final CalendarListLoader calendarListLoader;
    public final TimelineEvent timelineItem;

    public EventViewScreenLoader(Context context, TimelineEvent timelineevent, EventDescriptor eventdescriptor, EventViewScreenModel eventviewscreenmodel)
    {
        PhoneNumberDetails phonenumberdetails;
        if (eventviewscreenmodel == null)
        {
            phonenumberdetails = null;
        } else
        {
            phonenumberdetails = eventviewscreenmodel.localPhone;
        }
        super(context, eventdescriptor, phonenumberdetails, eventviewscreenmodel);
        timelineItem = timelineevent;
        timelineevent = new CalendarListFilterOptions();
        timelineevent.writable = Boolean.valueOf(true);
        context = new CalendarListLoader(context, timelineevent, null, true);
        calendarListLoader = context;
        super.loaders.add(context);
    }

    public EventViewScreenModel getResult()
    {
        com.google.android.calendar.api.event.Event event;
        TimelineEvent timelineevent;
        int i;
        if (super.event != null)
        {
            event = super.event;
        } else
        {
            event = ((com.google.android.calendar.newapi.common.FullEventLoader.EventCalendarResult)super.fullEventLoader.getResult()).event;
        }
        timelineevent = timelineItem;
        if (super.visibleCalendars != -1)
        {
            i = super.visibleCalendars;
        } else
        {
            i = ((Integer)super.visibleCalendarsLoader.getResult()).intValue();
        }
        return new EventViewScreenModel(event, timelineevent, i, (PhoneNumberDetails)getLocalPhoneNumber().orNull(), (CalendarList)calendarListLoader.getResult());
    }

    public volatile ViewScreenModel getResult()
    {
        return (EventViewScreenModel)getResult();
    }

    public volatile Object getResult()
    {
        return getResult();
    }
}
