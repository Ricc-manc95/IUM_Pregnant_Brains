// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.ics;

import android.content.Context;
import com.google.android.calendar.api.calendarlist.CalendarListFilterOptions;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.newapi.common.AsyncTaskLoader;
import com.google.android.calendar.newapi.common.CompositeLoader;
import com.google.android.calendar.newapi.common.loader.CalendarListLoader;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.model.EventViewScreenModel;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.screen.EventViewScreenLoader;
import com.google.android.calendar.timely.TimelineEvent;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.screen.ics:
//            IcsViewScreenModel

public final class IcsViewScreenLoader extends CompositeLoader
{

    private CalendarListLoader calendarListLoader;
    private EventViewScreenLoader eventViewScreenLoader;
    private IcsViewScreenModel original;

    public IcsViewScreenLoader(Context context, EventDescriptor eventdescriptor, IcsViewScreenModel icsviewscreenmodel)
    {
        original = icsviewscreenmodel;
        if (eventdescriptor != null)
        {
            eventViewScreenLoader = new EventViewScreenLoader(context, (TimelineEvent)((ViewScreenModel) (icsviewscreenmodel)).timelineItem, eventdescriptor, null);
            eventdescriptor = eventViewScreenLoader;
            super.loaders.add(eventdescriptor);
        }
        eventdescriptor = new CalendarListFilterOptions();
        eventdescriptor.writable = Boolean.valueOf(true);
        context = new CalendarListLoader(context, eventdescriptor, null, false);
        calendarListLoader = context;
        super.loaders.add(context);
    }

    public final Object getResult()
    {
        IcsViewScreenModel icsviewscreenmodel = original;
        if (eventViewScreenLoader != null)
        {
            icsviewscreenmodel.mergeModel((EventViewScreenModel)eventViewScreenLoader.getResult());
        }
        icsviewscreenmodel.setCalendarList((CalendarList)calendarListLoader.getResult());
        return icsviewscreenmodel;
    }
}
