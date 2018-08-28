// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.os.Trace;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.TimeUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.geometry.GridTimelineSegmentGeometry;
import com.google.android.calendar.timely.geometry.PartitionItem;
import com.google.android.calendar.timely.gridviews.DndEventHandler;
import com.google.android.calendar.timely.gridviews.GridDayView;
import com.google.android.calendar.timely.gridviews.GridViewFrame;
import com.google.android.calendar.timely.gridviews.WeekHeaderLabelsView;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderArrow;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderView;
import com.google.android.calendar.timely.gridviews.allday.ExpandableChipColumnView;
import com.google.android.calendar.timely.interaction.helper.DelayedUpdateHelper;
import com.google.android.calendar.timely.timeline.TimelineItemCollection;
import com.google.android.calendar.utils.recycler.Recycler;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyDayHeaderView, PagedScrollView, MonthData

public class PagedDayView extends FrameLayout
    implements RangedData.OnUpdateListener
{

    public View allDayContainer;
    public View allDayDivider;
    public AllDayHeaderArrow allDayHeaderArrow;
    public AllDayHeaderView allDayHeaderView;
    public View allDayLinearLayout;
    public int allDayViewGapX;
    public Recycler chipRecycler;
    public TextView collapseButton;
    private DelayedUpdateHelper delayedUpdateHelper;
    public DndEventHandler dndHandler;
    public GridDayView gridDayView;
    public GridViewFrame gridViewFrame;
    public boolean isTablet;
    public PagedScrollView pagedScrollView;
    public int position;
    public ScrollView stickyAllDayEventsView;
    public TimelyDayHeaderView stickyHeaderView;
    public View stickyHourMask;
    public WeekHeaderLabelsView weekHeaderLabelsView;

    public PagedDayView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        delayedUpdateHelper = new DelayedUpdateHelper(this);
    }

    public final int getGridHourStartOffset()
    {
        int i;
        if (stickyHeaderView.isToday)
        {
            Object obj = new Time(Utils.getTimeZoneId(getContext()));
            float f;
            long l;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            ((Time) (obj)).set(l);
            i = TimeUtils.previousHourBefore(((Time) (obj)), 30);
        } else
        {
            Iterator iterator = gridDayView.items.allChipsView.iterator();
            long l1;
            for (l1 = 0x7fffffffffffffffL; iterator.hasNext(); l1 = Math.min(l1, ((Chip)iterator.next()).partitionInfo.getStartMillis())) { }
            long l2 = l1;
            if (l1 == 0x7fffffffffffffffL)
            {
                l2 = 0L;
            }
            if (l2 > 0L)
            {
                Time time = new Time();
                time.timezone = android.text.format.Time.getCurrentTimezone();
                time.set(l2);
                i = TimeUtils.previousHourBefore(time, 30);
            } else
            {
                i = 8;
            }
        }
        obj = gridDayView;
        f = i;
        obj = ((GridDayView) (obj)).timelineSegmentGeometry;
        i = ((GridTimelineSegmentGeometry) (obj)).gridHourCellHeight;
        return (int)(-(f * (float)(int)(float)(((GridTimelineSegmentGeometry) (obj)).gridlineHeight + i)));
    }

    public final int getListenerTag()
    {
        Object obj = getTag();
        if (obj instanceof Integer)
        {
            return ((Integer)obj).intValue();
        } else
        {
            return -1;
        }
    }

    public final int getListenerTagType()
    {
        return 1;
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        i = android.view.View.MeasureSpec.makeMeasureSpec(android.view.View.MeasureSpec.getSize(i) - allDayViewGapX, 0x40000000);
        j = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
        stickyAllDayEventsView.measure(i, j);
    }

    public final void onUpdate(MonthData monthdata, int i)
    {
        boolean flag1 = true;
        boolean flag = false;
        Trace.beginSection("PagedDayView onUpdate");
        gridDayView.setJulianDay(i);
        gridDayView.onUpdate(monthdata, i);
        allDayHeaderView.setFirstJulianDay(i);
        allDayHeaderView.onUpdate(monthdata, i);
        int j;
        if (isTablet || ((ExpandableChipColumnView) (allDayHeaderView)).items.size() > 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        monthdata = allDayDivider;
        if (monthdata != null)
        {
            if (i != 0)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            monthdata.setVisibility(j);
        }
        monthdata = allDayContainer;
        if (monthdata != null)
        {
            if (i != 0)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            monthdata.setVisibility(j);
        }
        monthdata = stickyHourMask;
        if (i == 0)
        {
            i = ((flag1) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (monthdata != null)
        {
            if (i != 0)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 8;
            }
            monthdata.setVisibility(i);
        }
        Trace.endSection();
    }

    public final void postUpdate$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NKQRREEHK48OBKC4TKIMICCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UT39DLIMOU9FA9GMSPR5CH262T314HAN0P31EHIKCQBED5PMGPB49HKN6T35DPIN4EP9AO______0(MonthData monthdata, int i, RangedData.UpdateFinishedListener updatefinishedlistener)
    {
        class .Lambda._cls0
            implements Runnable
        {

            private final PagedDayView arg$1;
            private final int arg$2;
            private final MonthData arg$3;
            private final RangedData.UpdateFinishedListener arg$4;

            public final void run()
            {
                PagedDayView pageddayview = arg$1;
                int j = arg$2;
                MonthData monthdata1 = arg$3;
                RangedData.UpdateFinishedListener updatefinishedlistener1 = arg$4;
                if (j - 0x253d8c == pageddayview.position)
                {
                    pageddayview.onUpdate(monthdata1, j);
                }
                updatefinishedlistener1.notifyUpdateFinished();
            }

            .Lambda._cls0(int i, MonthData monthdata, RangedData.UpdateFinishedListener updatefinishedlistener)
            {
                arg$1 = PagedDayView.this;
                arg$2 = i;
                arg$3 = monthdata;
                arg$4 = updatefinishedlistener;
            }
        }

        delayedUpdateHelper.postUpdate(new .Lambda._cls0(i, monthdata, updatefinishedlistener));
    }

    public void setEnabled(boolean flag)
    {
        super.setEnabled(flag);
        pagedScrollView.setEnabled(flag);
        stickyHeaderView.setEnabled(flag);
    }

    public void setListenerTag(int i)
    {
        setTag(Integer.valueOf(i));
    }
}
