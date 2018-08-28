// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import android.os.Trace;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.apps.calendar.util.concurrent.SubscriptionManager;
import com.google.android.calendar.timely.animations.TimelineAgendaGridAnimationStatus;
import com.google.android.calendar.utils.recycler.Recycler;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyDayHeaderView, DualTimelineGridFragment, DataFactory, MonthData, 
//            TimelyDayView, StickyAllDayEventsView, TimelyDayViewSwipeHelper, DayViewConfig, 
//            OnTimelineGestureListener, OnTimelineModeChangedListener

public class TimelineAdapter extends android.support.v7.widget.RecyclerView.Adapter
{
    static final class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder
    {

        public MonthData data;
        public int julianDay;

        ViewHolder(View view)
        {
            super(view);
            julianDay = -1;
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/TimelineAdapter);
    public int animAllDayEventsHeight;
    public int animAllDayEventsScroll;
    public int animGridOffset;
    public int animPosition;
    public TimelineAgendaGridAnimationStatus animationStatus;
    public Recycler chipRecycler;
    public DataFactory dataFactory;
    public DayViewConfig dayViewConfig;
    public int forceShowPosition;
    public final TimelyDayHeaderView headerView;
    public OnTimelineGestureListener onTimelineGestureListener;
    public int scrollingVelocity;
    public TimelyDayView.SwipeGestureDelegate swipeDelegate;
    public OnTimelineModeChangedListener timelineModeChangedListener;

    public TimelineAdapter(final Context context)
    {
        scrollingVelocity = 0;
        headerView = new TimelyDayHeaderView(context);
        if (!context.getResources().getBoolean(0x7f0c0016))
        {
            headerView.setOnClickListener(new _cls1());
        }
        animPosition = -1;
    }

    public final TimelineRecyclerView.HeaderViewDescriptor getHeaderView(int i)
    {
        int j = DualTimelineGridFragment.getJulianDayFromPosition(i);
        MonthData monthdata = dataFactory.getData(j, false);
        headerView.setPosition(i);
        headerView.setDateInfo(monthdata.getDateInfo(j), true);
        headerView.initializeText();
        return headerView;
    }

    public final int getItemCount()
    {
        return 24479;
    }

    public final void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewholder, int i)
    {
        viewholder = (ViewHolder)viewholder;
        TimelyDayView timelydayview = (TimelyDayView)((ViewHolder) (viewholder)).itemView;
        if (((ViewHolder) (viewholder)).julianDay != -1)
        {
            int j = DualTimelineGridFragment.getPositionFromJulianDay(((ViewHolder) (viewholder)).julianDay);
            if (j != i && forceShowPosition == j)
            {
                forceShowPosition = -1;
            }
        }
        if (i != 0)
        {
            if (i == animPosition)
            {
                StickyAllDayEventsView stickyalldayeventsview = timelydayview.stickyAllDayEventsView;
                stickyalldayeventsview.setGridOffset(animGridOffset);
                stickyalldayeventsview.setGridHeight(animAllDayEventsHeight);
                stickyalldayeventsview.setGridScrollPosition(animAllDayEventsScroll);
                animPosition = -1;
            }
            viewholder.julianDay = DualTimelineGridFragment.getJulianDayFromPosition(i);
            MonthData monthdata = dataFactory.getData(((ViewHolder) (viewholder)).julianDay, false);
            viewholder.data = monthdata;
            int k = ((ViewHolder) (viewholder)).julianDay;
            timelydayview.setJulianDay(k);
            Object obj = timelydayview.subscriber;
            if (((TimelyDayView.Subscriber) (obj)).monthData != monthdata)
            {
                if (((TimelyDayView.Subscriber) (obj)).dataSubscription != null)
                {
                    ((TimelyDayView.Subscriber) (obj)).dataSubscription.cancel(false);
                    obj.dataSubscription = null;
                }
                obj.monthData = monthdata;
                obj.day = k;
                if (ViewCompat.isAttachedToWindow(((TimelyDayView.Subscriber) (obj)).this$0))
                {
                    TimelyDayView.Subscriber..Lambda._cls0 _lcls0 = new TimelyDayView.Subscriber..Lambda._cls0(((TimelyDayView.Subscriber) (obj)), k);
                    Object obj1 = ((TimelyDayView.Subscriber) (obj)).this$0.delayedUpdateHelper;
                    obj1.getClass();
                    obj1 = new TimelyDayView.Subscriber..Lambda._cls1(((com.google.android.calendar.timely.interaction.helper.DelayedUpdateHelper) (obj1)));
                    obj.dataSubscription = monthdata.subscriptions.subscribeFunction(new com.google.android.apps.calendar.util.concurrent.SubscriptionManager..Lambda._cls0(_lcls0), ((java.util.concurrent.Executor) (obj1)));
                }
            }
            obj = ((ViewHolder) (viewholder)).data;
            k = ((ViewHolder) (viewholder)).julianDay;
            int l;
            boolean flag;
            if (forceShowPosition == i)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            timelydayview.onUpdate(((MonthData) (obj)), k, flag);
            k = monthdata.startDay;
            i = monthdata.startDay;
            l = monthdata.numDays;
            if (scrollingVelocity != 0)
            {
                l = (i + l) - 1;
                i = 0;
                do
                {
                    DataFactory.getNumData();
                    if (i >= 12)
                    {
                        break;
                    }
                    if (scrollingVelocity > 0)
                    {
                        viewholder = dataFactory.getData(l + 1, false);
                        l = ((MonthData) (viewholder)).startDay;
                        l = (((MonthData) (viewholder)).numDays + l) - 1;
                    } else
                    {
                        k = dataFactory.getData(k - 1, false).startDay;
                    }
                    i++;
                } while (true);
            }
        }
    }

    public final android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i)
    {
        viewgroup = new TimelyDayView(viewgroup.getContext(), chipRecycler, dayViewConfig, animationStatus, timelineModeChangedListener);
        viewgroup.timelineGestureListener = onTimelineGestureListener;
        TimelyDayView.SwipeGestureDelegate swipegesturedelegate = swipeDelegate;
        ((TimelyDayView) (viewgroup)).swipe._flddelegate = swipegesturedelegate;
        return new ViewHolder(viewgroup);
    }

    public final void onViewRecycled(android.support.v7.widget.RecyclerView.ViewHolder viewholder)
    {
        viewholder = (ViewHolder)viewholder;
        super.onViewRecycled(viewholder);
        viewholder.data = null;
        ((TimelyDayView)((ViewHolder) (viewholder)).itemView).recycleDayView();
    }

    public final void refresh(int i, boolean flag)
    {
        Trace.beginSection("DayViewListAdapter.refresh");
        if (flag)
        {
            dataFactory.updateToday();
        }
        i = DualTimelineGridFragment.getJulianDayFromPosition(i);
        dataFactory.refreshDataAroundDay(i, flag, true);
        Trace.endSection();
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final TimelineAdapter this$0;
        private final Context val$context;

        public final void onClick(View view)
        {
            view = context.getString(0x7f130449, new Object[] {
                context.getString(0x7f1302f6)
            });
            headerView.announceForAccessibility(view);
            view = AnalyticsLoggerHolder.instance;
            if (view == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            } else
            {
                ((AnalyticsLogger)view).trackEvent(context, "menu_item", "day_toggle");
                timelineModeChangedListener.onModeChanged(headerView.position);
                return;
            }
        }

        _cls1()
        {
            this$0 = TimelineAdapter.this;
            context = context1;
            super();
        }
    }

}
