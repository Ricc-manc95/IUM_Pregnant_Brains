// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.google.android.calendar.timely.DataFactory;
import com.google.android.calendar.timely.MonthData;
import com.google.android.calendar.timely.PagedScrollView;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderArrow;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderView;
import com.google.android.calendar.timely.gridviews.allday.ExpandableChipColumnView;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridViewPagerAdapter, GridViewFrame, GridDayView, WeekHeaderLabelsView

final class resourceId
    implements com.google.android.calendar.utils.recycler.r
{

    private final Context context;
    private final int numDays;
    private final int resourceId;
    private final GridViewPagerAdapter this$0;

    public final void clean(ViewGroup viewgroup)
    {
        viewgroup = (resourceId)viewgroup.getTag();
        GridViewFrame gridviewframe = ((resourceId) (viewgroup)).resourceId;
        int i = ((resourceId) (viewgroup)).y;
        for (int j = 0; j < numDays;)
        {
            mDataFactory.getData(i, false).unregisterListener(i, 5);
            ((GridDayView)gridviewframe.getChildAt(gridviewframe.getChildrenBeforeGridDayViews() + j)).clearChips();
            j++;
            i++;
        }

        ((numDays) (viewgroup))..clear();
        (() (viewgroup)).iew.setScrollY(0);
        ((iew) (viewgroup)).w.setVerticalScrollPositionFromBottom(0, false);
    }

    public final volatile void clean(Object obj)
    {
        clean((ViewGroup)obj);
    }

    public final Object createObject()
    {
        ViewGroup viewgroup = (ViewGroup)View.inflate(context, resourceId, null);
        clean clean1 = new resourceId();
        clean1.w = (PagedScrollView)viewgroup.findViewById(0x7f10022f);
        clean1.w = (GridViewFrame)viewgroup.findViewById(0x7f100230);
        clean1. = (AllDayHeaderView)viewgroup.findViewById(0x7f10022e);
        clean1.iew = (ScrollView)viewgroup.findViewById(0x7f10022d);
        clean1.iew = (WeekHeaderLabelsView)viewgroup.findViewById(0x7f10022c);
        clean1.rrow = (AllDayHeaderArrow)viewgroup.findViewById(0x7f100374);
        for (int i = 0; i < numDays; i++)
        {
            View.inflate(context, 0x7f050183, clean1.context);
        }

        viewgroup.setTag(clean1);
        return viewgroup;
    }

    public final volatile void prepareToReuse(Object obj)
    {
    }

    public (Context context1, int i, int j)
    {
        this$0 = GridViewPagerAdapter.this;
        super();
        context = context1;
        numDays = j;
        resourceId = i;
    }
}
