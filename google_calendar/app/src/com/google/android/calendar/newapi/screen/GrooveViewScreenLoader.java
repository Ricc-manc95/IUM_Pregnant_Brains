// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.content.Context;
import com.google.android.calendar.api.event.CpEventDescriptor;
import com.google.android.calendar.api.event.CpEventKey;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.api.event.V2AEventKey;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.newapi.common.AsyncTaskLoader;
import com.google.android.calendar.newapi.common.CompositeLoader;
import com.google.android.calendar.newapi.common.Loader;
import com.google.android.calendar.newapi.model.GrooveViewScreenModel;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.segment.tracking.GrooveTrackingData;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            BasicViewScreenLoader, GrooveDataLoader

public final class GrooveViewScreenLoader extends BasicViewScreenLoader
{

    private GrooveDataLoader grooveDataLoader;
    private final Habit habit;
    private final TimelineGroove timelineItem;
    private final GrooveTrackingData trackingData;

    public GrooveViewScreenLoader(Context context, TimelineGroove timelinegroove, GrooveViewScreenModel grooveviewscreenmodel)
    {
        Object obj1 = null;
        Object obj;
        if (((TimelineEvent) (timelinegroove)).eventKey instanceof CpEventKey)
        {
            obj = new CpEventDescriptor((CpEventKey)(EventKey)((TimelineEvent) (timelinegroove)).eventKey);
        } else
        if (((TimelineEvent) (timelinegroove)).eventKey instanceof V2AEventKey)
        {
            obj = (V2AEventKey)((TimelineEvent) (timelinegroove)).eventKey;
            obj = (new com.google.android.calendar.api.event..AutoValue_V2AEventDescriptor.Builder()).originalStart(0L).recurringException(false).recurringPhantom(false).key(((V2AEventKey) (obj))).build();
        } else
        {
            context = String.valueOf(((TimelineEvent) (timelinegroove)).eventKey);
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(context).length() + 20)).append("Unhandled key type: ").append(context).toString());
        }
        super(context, ((com.google.android.calendar.api.event.EventDescriptor) (obj)), null, grooveviewscreenmodel);
        if (grooveviewscreenmodel == null)
        {
            obj = null;
        } else
        {
            obj = grooveviewscreenmodel.habit;
        }
        habit = ((Habit) (obj));
        timelineItem = timelinegroove;
        if (grooveviewscreenmodel == null)
        {
            grooveviewscreenmodel = obj1;
        } else
        {
            grooveviewscreenmodel = grooveviewscreenmodel.trackingData;
        }
        trackingData = grooveviewscreenmodel;
        if (habit == null)
        {
            context = new GrooveDataLoader(context, timelinegroove);
            grooveDataLoader = context;
            super.loaders.add(context);
        }
    }

    public final volatile ViewScreenModel getResult()
    {
        return (GrooveViewScreenModel)getResult();
    }

    public final Object getResult()
    {
        Habit habit1;
        com.google.android.calendar.api.event.Event event;
        GrooveTrackingData groovetrackingdata;
        TimelineGroove timelinegroove;
        int i;
        if (habit != null)
        {
            habit1 = habit;
        } else
        {
            habit1 = ((GrooveDataLoader.GrooveDataResult)grooveDataLoader.getResult()).habit;
        }
        if (super.event != null)
        {
            event = super.event;
        } else
        {
            event = ((com.google.android.calendar.newapi.common.FullEventLoader.EventCalendarResult)super.fullEventLoader.getResult()).event;
        }
        timelinegroove = timelineItem;
        if (super.visibleCalendars != -1)
        {
            i = super.visibleCalendars;
        } else
        {
            i = ((Integer)super.visibleCalendarsLoader.getResult()).intValue();
        }
        if (trackingData != null)
        {
            groovetrackingdata = trackingData;
        } else
        {
            groovetrackingdata = ((GrooveDataLoader.GrooveDataResult)grooveDataLoader.getResult()).trackingData;
        }
        return new GrooveViewScreenModel(habit1, event, timelinegroove, i, groovetrackingdata);
    }

    public final void onLoadingFailure(Loader loader, String s)
    {
        if (loader == grooveDataLoader && ((GrooveDataLoader.GrooveDataResult)loader.getResult()).habit == null)
        {
            super.onLoadingSuccess(grooveDataLoader);
            return;
        } else
        {
            super.onLoadingFailure(loader, s);
            return;
        }
    }
}
