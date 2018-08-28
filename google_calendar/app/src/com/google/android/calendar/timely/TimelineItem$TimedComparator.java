// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            TimelineItem

public final class rator extends rator
{

    public final int compare(TimelineItem timelineitem, TimelineItem timelineitem1)
    {
        if (timelineitem.getStartDay() != timelineitem1.getStartDay())
        {
            return Integer.valueOf(timelineitem.getStartDay()).compareTo(Integer.valueOf(timelineitem1.getStartDay()));
        }
        if (timelineitem.isAllDay() != timelineitem1.isAllDay())
        {
            return Boolean.valueOf(timelineitem1.isAllDay()).compareTo(Boolean.valueOf(timelineitem.isAllDay()));
        }
        if (!timelineitem.isAllDay())
        {
            if (timelineitem.getStartTime() != timelineitem1.getStartTime())
            {
                return Integer.valueOf(timelineitem.getStartTime()).compareTo(Integer.valueOf(timelineitem1.getStartTime()));
            }
            if (timelineitem.getEndDay() != timelineitem1.getEndDay())
            {
                return Integer.valueOf(timelineitem.getEndDay()).compareTo(Integer.valueOf(timelineitem1.getEndDay()));
            }
            if (timelineitem.getEndTime() != timelineitem1.getEndTime())
            {
                return Integer.valueOf(timelineitem.getEndTime()).compareTo(Integer.valueOf(timelineitem1.getEndTime()));
            }
        } else
        if (timelineitem.getEndDay() != timelineitem1.getEndDay())
        {
            return Integer.valueOf(timelineitem1.getEndDay()).compareTo(Integer.valueOf(timelineitem.getEndDay()));
        }
        return super.compare(timelineitem, timelineitem1);
    }

    public final volatile int compare(Object obj, Object obj1)
    {
        return compare((TimelineItem)obj, (TimelineItem)obj1);
    }

    public rator()
    {
    }
}
