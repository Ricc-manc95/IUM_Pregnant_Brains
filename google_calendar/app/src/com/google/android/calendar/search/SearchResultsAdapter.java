// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.search;

import android.content.Context;
import android.util.SparseArray;
import android.view.ViewGroup;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.DayViewConfig;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelyDayView;
import com.google.android.calendar.utils.recycler.Recycler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class SearchResultsAdapter extends android.support.v7.widget.RecyclerView.Adapter
{

    private static final DayViewConfig DAY_VIEW_CONFIG;
    private final Recycler chipRecycler;
    private final Context context;
    public int dayToScroll;
    public SparseArray daysToItems;
    private final Time recyclerTime;
    private boolean shouldDrawYearHeader;

    public SearchResultsAdapter(Context context1, Recycler recycler)
    {
        context = context1;
        chipRecycler = recycler;
        daysToItems = new SparseArray();
        recyclerTime = new Time(Utils.getTimeZoneId(context));
    }

    public final int getItemCount()
    {
        return daysToItems.size();
    }

    public final void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewholder, int i)
    {
        List list;
        int j;
        viewholder = (TimelyDayView)viewholder.itemView;
        j = daysToItems.keyAt(i);
        list = (List)daysToItems.valueAt(i);
        if (!shouldDrawYearHeader) goto _L2; else goto _L1
_L1:
        if (i != 0) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        viewholder.shouldDrawYearHeader = flag;
        viewholder.setJulianDay(j);
        if (j == ((TimelyDayView) (viewholder)).julianDay)
        {
            viewholder.onUpdate(list, Utils.getDateInfo(j), false);
            viewholder.mDataLoaded = true;
        }
        return;
_L4:
        Time time = recyclerTime;
        int k = daysToItems.keyAt(i - 1);
        time.writeFieldsToImpl();
        time.impl.setJulianDay(k);
        time.copyFieldsFromImpl();
        time = recyclerTime;
        time.writeFieldsToImpl();
        time.impl.normalize(true);
        time.copyFieldsFromImpl();
        k = recyclerTime.year;
        time = recyclerTime;
        i = daysToItems.keyAt(i);
        time.writeFieldsToImpl();
        time.impl.setJulianDay(i);
        time.copyFieldsFromImpl();
        time = recyclerTime;
        time.writeFieldsToImpl();
        time.impl.normalize(true);
        time.copyFieldsFromImpl();
        if (recyclerTime.year != k)
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        flag = false;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i)
    {
        viewgroup = context;
        Recycler recycler = chipRecycler;
        DayViewConfig dayviewconfig = DAY_VIEW_CONFIG;
        float f;
        if (DAY_VIEW_CONFIG.inGridMode())
        {
            f = 1.0F;
        } else
        {
            f = 0.0F;
        }
        return new SimpleViewHolder(new TimelyDayView(viewgroup, recycler, dayviewconfig, new com.google.android.calendar.timely.animations.TimelineAgendaGridAnimationStatus.ImmutableTimelineAgendaGridAnimationStatus(false, f), null));
    }

    public final void swapItemList(List list)
    {
        daysToItems.clear();
        if (list == null || list.isEmpty())
        {
            return;
        }
        Collections.sort(list, TimelineItem.ItemComparator);
        int k = Utils.getTodayJulianDay(context);
        dayToScroll = 0x7fffffff;
        for (Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            TimelineItem timelineitem = (TimelineItem)iterator.next();
            int i = timelineitem.getStartDay();
            while (i <= timelineitem.getEndDay()) 
            {
                List list1 = (List)daysToItems.get(i);
                list = list1;
                if (list1 == null)
                {
                    list = new ArrayList();
                    daysToItems.put(i, list);
                }
                list.add(timelineitem);
                if (i >= k && i < dayToScroll)
                {
                    dayToScroll = i;
                }
                i++;
            }
        }

        if (dayToScroll == 0x7fffffff)
        {
            dayToScroll = daysToItems.keyAt(daysToItems.size() - 1);
        }
        list = recyclerTime;
        int j = daysToItems.keyAt(0);
        list.writeFieldsToImpl();
        ((Time) (list)).impl.setJulianDay(j);
        list.copyFieldsFromImpl();
        list = recyclerTime;
        list.writeFieldsToImpl();
        ((Time) (list)).impl.normalize(true);
        list.copyFieldsFromImpl();
        j = recyclerTime.year;
        list = recyclerTime;
        k = daysToItems.keyAt(daysToItems.size() - 1);
        list.writeFieldsToImpl();
        ((Time) (list)).impl.setJulianDay(k);
        list.copyFieldsFromImpl();
        list = recyclerTime;
        list.writeFieldsToImpl();
        ((Time) (list)).impl.normalize(true);
        list.copyFieldsFromImpl();
        boolean flag;
        if (recyclerTime.year != j)
        {
            flag = true;
        } else
        {
            list = recyclerTime;
            long l;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            ((Time) (list)).impl.timezone = ((Time) (list)).timezone;
            ((Time) (list)).impl.set(l);
            ((Time) (list)).impl.toMillis(true);
            list.copyFieldsFromImpl();
            list = recyclerTime;
            list.writeFieldsToImpl();
            ((Time) (list)).impl.normalize(true);
            list.copyFieldsFromImpl();
            if (recyclerTime.year != j)
            {
                flag = true;
            } else
            {
                flag = false;
            }
        }
        shouldDrawYearHeader = flag;
    }

    static 
    {
        com.google.android.calendar.timely.DayViewConfig.Builder builder = new com.google.android.calendar.timely.DayViewConfig.Builder();
        builder.inGridMode = Boolean.valueOf(false);
        builder.shouldDrawDayHeader = true;
        builder.shouldDrawYearHeader = true;
        builder.neverDrawNowLine = true;
        builder.shouldDrawMonthInDayHeader = true;
        builder.isChipClickable = true;
        builder.canDrawBackgroundImage = true;
        DAY_VIEW_CONFIG = builder.build();
    }

    private class SimpleViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder
    {

        SimpleViewHolder(View view)
        {
            super(view);
        }
    }

}
