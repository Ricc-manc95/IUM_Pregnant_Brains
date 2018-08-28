// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.smartmail;

import android.content.Context;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.event.conference.PhoneNumberDetails;
import com.google.android.calendar.newapi.common.AsyncTaskLoader;
import com.google.android.calendar.newapi.common.CompositeLoader;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.model.EventViewScreenModel;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.screen.BasicViewScreenLoader;
import com.google.android.calendar.newapi.screen.EventViewScreenLoader;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.common.base.Optional;

// Referenced classes of package com.google.android.calendar.newapi.screen.smartmail:
//            SmartMailViewScreenModel

public final class SmartMailViewScreenLoader extends EventViewScreenLoader
{

    protected SmartMailViewScreenLoader(Context context, TimelineEvent timelineevent, EventDescriptor eventdescriptor, EventViewScreenModel eventviewscreenmodel)
    {
        super(context, timelineevent, eventdescriptor, eventviewscreenmodel);
    }

    public final EventViewScreenModel getResult()
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
        timelineevent = super.timelineItem;
        if (super.visibleCalendars != -1)
        {
            i = super.visibleCalendars;
        } else
        {
            i = ((Integer)super.visibleCalendarsLoader.getResult()).intValue();
        }
        return new SmartMailViewScreenModel(event, timelineevent, i, (PhoneNumberDetails)getLocalPhoneNumber().orNull(), (CalendarList)super.calendarListLoader.getResult());
    }

    public final volatile ViewScreenModel getResult()
    {
        return (EventViewScreenModel)getResult();
    }

    public final volatile Object getResult()
    {
        return getResult();
    }
}
