// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.android.calendar.api.event.EventKey;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineItem, TimelineEvent

public class 
    implements Comparator
{

    public int compare(TimelineItem timelineitem, TimelineItem timelineitem1)
    {
        if (timelineitem.getSortType() != timelineitem1.getSortType())
        {
            return timelineitem.getSortType().(timelineitem1.getSortType());
        }
        if (timelineitem.getSortId() != timelineitem1.getSortId())
        {
            return timelineitem.getSortId() != timelineitem1.getSortId();
        }
        if ((timelineitem instanceof TimelineEvent) && (timelineitem1 instanceof TimelineEvent))
        {
            timelineitem = (TimelineEvent)timelineitem;
            timelineitem1 = (TimelineEvent)timelineitem1;
            Comparator comparator = EventKey.COMPARATOR;
            if (((TimelineEvent) (timelineitem)).originalEventKey != null)
            {
                timelineitem = ((TimelineEvent) (timelineitem)).originalEventKey;
            } else
            {
                timelineitem = ((TimelineEvent) (timelineitem)).eventKey;
            }
            if (((TimelineEvent) (timelineitem1)).originalEventKey != null)
            {
                timelineitem1 = ((TimelineEvent) (timelineitem1)).originalEventKey;
            } else
            {
                timelineitem1 = ((TimelineEvent) (timelineitem1)).eventKey;
            }
            return comparator.compare(timelineitem, timelineitem1);
        } else
        {
            return 0;
        }
    }

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((TimelineItem)obj, (TimelineItem)obj1);
    }

    public ()
    {
    }
}
