// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth;

import com.google.android.calendar.timely.TimelineItem;

final class yRangeGetter
    implements yRangeGetter
{

    public final int getEndDay(Object obj)
    {
        return ((TimelineItem)obj).getEndDay();
    }

    public final int getStartDay(Object obj)
    {
        return ((TimelineItem)obj).getStartDay();
    }

    public final boolean spansAtLeastOneDay(Object obj)
    {
        return ((TimelineItem)obj).spansAtLeastOneDay();
    }

    yRangeGetter()
    {
    }
}