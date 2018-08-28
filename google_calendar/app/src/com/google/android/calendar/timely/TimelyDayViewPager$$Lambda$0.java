// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            TimelyDayViewPager, PagedDayView, DayViewPagerAdapter

final class arg._cls1
    implements Runnable
{

    private final TimelyDayViewPager arg$1;

    public final void run()
    {
        TimelyDayViewPager timelydayviewpager = arg$1;
        PagedDayView apageddayview[] = timelydayviewpager.getVisibleChildren();
        int j = apageddayview.length;
        for (int i = 0; i < j; i++)
        {
            PagedDayView pageddayview = apageddayview[i];
            timelydayviewpager.dayViewPagerAdapter.updateView(pageddayview.position, pageddayview);
        }

    }

    (TimelyDayViewPager timelydayviewpager)
    {
        arg$1 = timelydayviewpager;
    }
}
