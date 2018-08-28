// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineItem

public final class 
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        obj = (TimelineItem)obj;
        obj1 = (TimelineItem)obj1;
        if (((TimelineItem) (obj)).getStartDay() != ((TimelineItem) (obj1)).getStartDay())
        {
            return Integer.compare(((TimelineItem) (obj)).getStartDay(), ((TimelineItem) (obj1)).getStartDay());
        }
        if (((TimelineItem) (obj)).getEndDay() != ((TimelineItem) (obj1)).getEndDay())
        {
            return Integer.compare(((TimelineItem) (obj1)).getEndDay(), ((TimelineItem) (obj)).getEndDay());
        } else
        {
            return TimelineItem.ItemComparator.compare(obj, obj1);
        }
    }

    public ()
    {
    }
}
