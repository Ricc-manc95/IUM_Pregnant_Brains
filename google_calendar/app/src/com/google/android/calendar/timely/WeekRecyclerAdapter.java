// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.gridviews.DndEventHandler;
import com.google.android.calendar.timely.gridviews.GridDayView;
import com.google.android.calendar.timely.gridviews.GridViewFrame;
import com.google.android.calendar.timely.gridviews.WeekHeaderLabelsView;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderArrow;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderView;
import com.google.android.calendar.timely.gridviews.allday.ExpandableChipColumnView;
import com.google.android.calendar.timely.gridviews.allday.StickyAllDayManager;
import com.google.android.calendar.utils.recycler.Recycler;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            PagedScrollView, DataFactory, MonthData

public final class WeekRecyclerAdapter extends android.support.v7.widget.RecyclerView.Adapter
{

    private final StickyAllDayManager allDayManager;
    public boolean autoScrollIgnoreTime;
    public int autoScrollItem;
    public Time autoScrollTime;
    private final Recycler chipRecycler;
    public final Context context;
    private final DataFactory dataFactory;
    private final DndEventHandler dndHandler;
    public List holders;
    public int pageWidth;
    private final PagedScrollView.ScrollManager scrollManager = new PagedScrollView.ScrollManager();

    public WeekRecyclerAdapter(Context context1, ViewGroup viewgroup, Recycler recycler, DataFactory datafactory, DndEventHandler dndeventhandler)
    {
        holders = new ArrayList();
        context = context1;
        dataFactory = datafactory;
        chipRecycler = recycler;
        dndHandler = dndeventhandler;
        scrollManager.add((PagedScrollView)viewgroup.findViewById(0x7f10022f));
        context1 = (AllDayHeaderArrow)viewgroup.findViewById(0x7f100374);
        allDayManager = new StickyAllDayManager(context.getResources(), false);
        viewgroup = allDayManager;
        viewgroup.sharedArrow = context1;
        ((StickyAllDayManager) (viewgroup)).sharedArrow.setOnClickListener(viewgroup);
        viewgroup.sharedState = 1;
        autoScrollItem = -1;
    }

    final void clean(ViewHolder viewholder)
    {
        if (viewholder.isClean)
        {
            return;
        }
        PagedScrollView.ScrollManager scrollmanager = scrollManager;
        PagedScrollView pagedscrollview = viewholder.dayScrollView;
        scrollmanager.scrollViews.remove(pagedscrollview);
        pagedscrollview.removeOnLayoutChangeListener(scrollmanager);
        pagedscrollview.scrollManager = null;
        pagedscrollview.pinchZoomController = null;
        allDayManager.removePage(viewholder.allDayContent);
        int i = viewholder.firstJulianDay;
        for (int j = 0; j < 7;)
        {
            dataFactory.getData(i, false).unregisterListener(i, 5);
            GridViewFrame gridviewframe = viewholder.daysContent;
            ((GridDayView)gridviewframe.getChildAt(gridviewframe.getChildrenBeforeGridDayViews() + j)).clearChips();
            j++;
            i++;
        }

        viewholder.firstJulianDay = -1;
        viewholder.isClean = true;
    }

    public final int getItemCount()
    {
        return 3497;
    }

    final void init(ViewHolder viewholder)
    {
        scrollManager.add(viewholder.dayScrollView);
        allDayManager.addPage(viewholder.itemView, viewholder.allDayContent, viewholder.allDayScrollView, viewholder.dayScrollView, null, null);
        int i;
        if (((android.support.v7.widget.RecyclerView.ViewHolder) (viewholder)).mPreLayoutPosition == -1)
        {
            i = ((android.support.v7.widget.RecyclerView.ViewHolder) (viewholder)).mPosition;
        } else
        {
            i = ((android.support.v7.widget.RecyclerView.ViewHolder) (viewholder)).mPreLayoutPosition;
        }
        i = Utils.getJulianFirstDayFromWeeksSinceEpoch(i, Utils.getFirstDayOfWeekAsTime(context));
        viewholder.isClean = false;
        viewholder.firstJulianDay = i;
        viewholder.daysContent.setFirstJulianDay(i);
        viewholder.daysHeaders.setFirstJulianDay(i);
        viewholder.allDayContent.setFirstJulianDay(i);
        if (viewholder.itemView.getLayoutParams().width != pageWidth)
        {
            viewholder.itemView.getLayoutParams().width = pageWidth;
            viewholder.itemView.requestLayout();
        }
        for (int j = 0; j < 7;)
        {
            Object obj = viewholder.daysContent;
            obj = (GridDayView)((GridViewFrame) (obj)).getChildAt(((GridViewFrame) (obj)).getChildrenBeforeGridDayViews() + j);
            MonthData monthdata = dataFactory.getData(i, false);
            ((GridDayView) (obj)).setJulianDay(i);
            ((GridDayView) (obj)).onUpdate(monthdata, i);
            viewholder.allDayContent.onUpdate(monthdata, i);
            monthdata.registerListener(i, new com.google.android.calendar.timely.gridviews.GridViewPagerAdapter.GridOnUpdatelistener(i, ((GridDayView) (obj)), viewholder.allDayContent));
            j++;
            i++;
        }

        class .Lambda._cls0
            implements android.view.View.OnTouchListener
        {

            public static final android.view.View.OnTouchListener $instance = new .Lambda._cls0();

            public final boolean onTouch(View view, MotionEvent motionevent)
            {
                class .Lambda._cls1
                    implements Runnable
                {

                    public static final Runnable $instance = new .Lambda._cls1();

                    public final void run()
                    {
                        CreateNewEventView.removeSelectedTime();
                    }


                        private .Lambda._cls1()
                        {
                        }
                }

                view.post(.Lambda._cls1..instance);
                return false;
            }


            private .Lambda._cls0()
            {
            }
        }

        viewholder.dayScrollView.setOnTouchListener(.Lambda._cls0..instance);
    }

    public final void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewholder, int i)
    {
label0:
        {
            viewholder = (ViewHolder)viewholder;
            ((ViewHolder) (viewholder)).allDayContent.chipRecycler = chipRecycler;
            for (int j = 0; j < 7; j++)
            {
                GridViewFrame gridviewframe = ((ViewHolder) (viewholder)).daysContent;
                ((GridDayView)gridviewframe.getChildAt(gridviewframe.getChildrenBeforeGridDayViews() + j)).initialize(chipRecycler, dndHandler, 1);
            }

            if (i == autoScrollItem)
            {
                autoScrollItem = -1;
                if (!autoScrollIgnoreTime)
                {
                    break label0;
                }
                viewholder = ((ViewHolder) (viewholder)).daysContent;
                viewholder.post(new com.google.android.calendar.timely.gridviews.GridViewFrame._cls1(viewholder));
            }
            return;
        }
        viewholder = ((ViewHolder) (viewholder)).daysContent;
        viewholder.post(new com.google.android.calendar.timely.gridviews.GridViewFrame._cls2(viewholder, new Time(autoScrollTime)));
    }

    public final android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i)
    {
        viewgroup = LayoutInflater.from(viewgroup.getContext()).inflate(0x7f0500a4, viewgroup, false);
        viewgroup.setLayoutParams(new android.view.ViewGroup.LayoutParams(pageWidth, -1));
        return new ViewHolder((ViewGroup)viewgroup);
    }

    public final void onViewAttachedToWindow(android.support.v7.widget.RecyclerView.ViewHolder viewholder)
    {
        viewholder = (ViewHolder)viewholder;
        super.onViewAttachedToWindow(viewholder);
        holders.add(viewholder);
        init(viewholder);
    }

    public final void onViewDetachedFromWindow(android.support.v7.widget.RecyclerView.ViewHolder viewholder)
    {
        viewholder = (ViewHolder)viewholder;
        super.onViewDetachedFromWindow(viewholder);
        holders.remove(viewholder);
        clean(viewholder);
    }

    public final void onViewRecycled(android.support.v7.widget.RecyclerView.ViewHolder viewholder)
    {
        viewholder = (ViewHolder)viewholder;
        super.onViewRecycled(viewholder);
        clean(viewholder);
    }

    private class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder
    {

        public final AllDayHeaderView allDayContent;
        public final ScrollView allDayScrollView;
        public final PagedScrollView dayScrollView;
        public final GridViewFrame daysContent;
        public final WeekHeaderLabelsView daysHeaders;
        public int firstJulianDay;
        public boolean isClean;

        public ViewHolder(ViewGroup viewgroup)
        {
            super(viewgroup);
            dayScrollView = (PagedScrollView)viewgroup.findViewById(0x7f10022f);
            allDayContent = (AllDayHeaderView)viewgroup.findViewById(0x7f10022e);
            daysHeaders = (WeekHeaderLabelsView)viewgroup.findViewById(0x7f10022c);
            allDayScrollView = (ScrollView)viewgroup.findViewById(0x7f10022d);
            daysContent = (GridViewFrame)viewgroup.findViewById(0x7f100230);
        }
    }

}
