// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            TimelineItem

public final class ator extends ator
{

    public final int compare(TimelineItem timelineitem, TimelineItem timelineitem1)
    {
        if (timelineitem.getStartDay() != timelineitem1.getStartDay())
        {
            return Integer.compare(timelineitem.getStartDay(), timelineitem1.getStartDay());
        }
        if (timelineitem.getEndDay() != timelineitem1.getEndDay())
        {
            return Integer.compare(timelineitem1.getEndDay(), timelineitem.getEndDay());
        } else
        {
            return super.compare(timelineitem, timelineitem1);
        }
    }

    public final volatile int compare(Object obj, Object obj1)
    {
        return compare((TimelineItem)obj, (TimelineItem)obj1);
    }

    public ator()
    {
    }
}
