// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.ViewGroup;
import android.widget.ScrollView;
import com.google.android.calendar.timely.gridviews.GridViewFrame;
import com.google.android.calendar.timely.gridviews.WeekHeaderLabelsView;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderView;

// Referenced classes of package com.google.android.calendar.timely:
//            PagedScrollView

public final class daysContent extends android.support.v7.widget.ewHolder
{

    public final AllDayHeaderView allDayContent;
    public final ScrollView allDayScrollView;
    public final PagedScrollView dayScrollView;
    public final GridViewFrame daysContent;
    public final WeekHeaderLabelsView daysHeaders;
    public int firstJulianDay;
    public boolean isClean;

    public ew(ViewGroup viewgroup)
    {
        super(viewgroup);
        dayScrollView = (PagedScrollView)viewgroup.findViewById(0x7f10022f);
        allDayContent = (AllDayHeaderView)viewgroup.findViewById(0x7f10022e);
        daysHeaders = (WeekHeaderLabelsView)viewgroup.findViewById(0x7f10022c);
        allDayScrollView = (ScrollView)viewgroup.findViewById(0x7f10022d);
        daysContent = (GridViewFrame)viewgroup.findViewById(0x7f100230);
    }
}
