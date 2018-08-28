// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Trace;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.TimeUtils;
import com.google.android.calendar.timely.geometry.GridTimelineSegmentGeometry;
import com.google.android.calendar.timely.gridviews.DndEventHandler;
import com.google.android.calendar.timely.gridviews.GridDayView;
import com.google.android.calendar.timely.gridviews.GridDrawable;
import com.google.android.calendar.timely.gridviews.GridHourDrawable;
import com.google.android.calendar.timely.gridviews.GridViewFrame;
import com.google.android.calendar.timely.gridviews.WeekHeaderLabelsView;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderArrow;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderView;
import com.google.android.calendar.timely.gridviews.allday.ExpandableChipColumnView;
import com.google.android.calendar.timely.gridviews.allday.StickyAllDayManager;
import com.google.android.calendar.utils.recycler.Recycler;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.timely:
//            PagedDayView, PagedScrollView, DataFactory, MonthData, 
//            NowLineDrawable, TimelyDayHeaderView, PinchZoomController, OnTimelineModeChangedListener

public final class DayViewPagerAdapter extends ViewPagerFragment.ViewPagerAdapter
{

    private final AllDayLayoutListener allDayLayoutListener = new AllDayLayoutListener();
    public Recycler chipRecycler;
    public DndEventHandler dndHandler;
    public boolean enabled;
    private final boolean isTablet;
    private final ArrayList itemsToAdd = new ArrayList();
    private final ArrayList itemsToRemove = new ArrayList();
    public int manualScrollPosition;
    public Time manualScrollPositionTime;
    private PagedDayView primaryPage;
    private final ArrayList recycledViews = new ArrayList();
    public final PagedScrollView.ScrollManager scrollManager = new PagedScrollView.ScrollManager();
    public boolean setNextScrollPositionManually;
    private final StickyAllDayManager stickyAllDayManager;
    public OnTimelineModeChangedListener timelineModeChangedListener;

    public DayViewPagerAdapter(Context context)
    {
        isTablet = context.getResources().getBoolean(0x7f0c0016);
        context = context.getResources();
        boolean flag;
        if (!isTablet)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        stickyAllDayManager = new StickyAllDayManager(context, flag);
        setNextScrollPositionManually = false;
    }

    public final void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        obj = (PagedDayView)obj;
        stickyAllDayManager.removePage(((PagedDayView) (obj)).allDayHeaderView);
        recycledViews.add(obj);
        itemsToRemove.add(obj);
        viewgroup = scrollManager;
        obj = ((PagedDayView) (obj)).pagedScrollView;
        ((PagedScrollView.ScrollManager) (viewgroup)).scrollViews.remove(obj);
        ((PagedScrollView) (obj)).removeOnLayoutChangeListener(viewgroup);
        obj.scrollManager = null;
        obj.pinchZoomController = null;
    }

    public final void finishUpdate(ViewGroup viewgroup)
    {
        boolean flag = false;
        ArrayList arraylist = (ArrayList)itemsToRemove;
        int k = arraylist.size();
        for (int i = 0; i < k;)
        {
            Object obj = arraylist.get(i);
            i++;
            viewgroup.removeView((View)obj);
        }

        arraylist = (ArrayList)itemsToAdd;
        k = arraylist.size();
        for (int j = ((flag) ? 1 : 0); j < k;)
        {
            Object obj1 = arraylist.get(j);
            j++;
            viewgroup.addView((View)obj1);
        }

    }

    public final int getCount()
    {
        return 24479;
    }

    public final int getItemPosition(Object obj)
    {
        if (obj != null && (obj instanceof PagedDayView))
        {
            ((PagedDayView)obj).invalidate();
        }
        return super.getItemPosition(obj);
    }

    public final Object instantiateItem(final ViewGroup view, int i)
    {
        Trace.beginSection("PagedDayView instantiateItem");
        if (recycledViews.size() <= 0) goto _L2; else goto _L1
_L1:
        Object obj;
        view = (PagedDayView)recycledViews.remove(0);
        if (!itemsToRemove.remove(view))
        {
            itemsToAdd.add(view);
        }
        obj = view.getTag();
        if (!(obj instanceof Integer)) goto _L4; else goto _L3
_L3:
        int j = ((Integer)obj).intValue();
_L5:
        if (j == -1)
        {
            break MISSING_BLOCK_LABEL_96;
        }
        obj = mDataFactory.getDataAllowNull(j);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_96;
        }
        ((MonthData) (obj)).unregisterListener(j, 1);
        ((PagedDayView) (view)).gridDayView.initialize(((PagedDayView) (view)).chipRecycler, ((PagedDayView) (view)).dndHandler, 3);
_L9:
        if (i <= 1)
        {
            Trace.endSection();
            return view;
        }
        break MISSING_BLOCK_LABEL_899;
_L4:
        j = -1;
          goto _L5
_L2:
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        obj = (PagedDayView)View.inflate(view.getContext(), 0x7f05010b, null);
        view = view.getContext();
        obj1 = chipRecycler;
        obj2 = timelineModeChangedListener;
        obj3 = stickyAllDayManager;
        obj4 = dndHandler;
        obj5 = view.getResources();
        obj.isTablet = view.getResources().getBoolean(0x7f0c0016);
        obj.pagedScrollView = (PagedScrollView)((PagedDayView) (obj)).findViewById(0x7f1002d2);
        obj.chipRecycler = ((Recycler) (obj1));
        obj.dndHandler = ((DndEventHandler) (obj4));
        obj.gridViewFrame = new GridViewFrame(view, null);
        View.inflate(view, 0x7f050183, ((PagedDayView) (obj)).gridViewFrame);
        GridViewFrame gridviewframe = ((PagedDayView) (obj)).gridViewFrame;
        obj.gridDayView = (GridDayView)gridviewframe.getChildAt(0 + gridviewframe.getChildrenBeforeGridDayViews());
        if (!((PagedDayView) (obj)).isTablet) goto _L7; else goto _L6
_L6:
        Object obj6;
        Object obj8;
        ((PagedDayView) (obj)).gridViewFrame.setHourViewWidth(((Resources) (obj5)).getDimensionPixelSize(0x7f0e0307));
        obj8 = ((PagedDayView) (obj)).gridViewFrame;
        obj6 = android.graphics.Paint.Align.CENTER;
        if (((GridViewFrame) (obj8)).gridHourDrawable == null)
        {
            throw new NullPointerException(String.valueOf("View does not draw hours"));
        }
          goto _L8
        view;
        Trace.endSection();
        throw view;
_L8:
        obj8 = ((GridViewFrame) (obj8)).gridHourDrawable;
        obj8.startMargin = 0;
        ((GridHourDrawable) (obj8)).paint.setTextAlign(((android.graphics.Paint.Align) (obj6)));
        ((GridHourDrawable) (obj8)).highlightPaint.setTextAlign(((android.graphics.Paint.Align) (obj6)));
        ((GridHourDrawable) (obj8)).invalidateSelf();
        obj6 = ((PagedDayView) (obj)).gridViewFrame;
        ((GridViewFrame) (obj6)).gridDrawable.drawStartVerticalLine = true;
        obj6.gridStartPadding = 0;
        obj6.gridEndPadding = 0;
_L10:
        ((PagedDayView) (obj)).gridViewFrame.nowLineDrawable = NowLineDrawable.createAgendaStyle(((Resources) (obj5)), ((PagedDayView) (obj)).isTablet);
        ((PagedDayView) (obj)).gridDayView.initialize(((Recycler) (obj1)), ((DndEventHandler) (obj4)), 3);
        ((PagedDayView) (obj)).pagedScrollView.addView(((PagedDayView) (obj)).gridViewFrame);
        obj.stickyAllDayEventsView = (ScrollView)((PagedDayView) (obj)).findViewById(0x7f1002cf);
        obj.allDayHeaderView = (AllDayHeaderView)((PagedDayView) (obj)).findViewById(0x7f1002d0);
        ((PagedDayView) (obj)).allDayHeaderView.chipRecycler = ((Recycler) (obj1));
        obj.weekHeaderLabelsView = (WeekHeaderLabelsView)((PagedDayView) (obj)).findViewById(0x7f10022c);
        obj.allDayHeaderArrow = (AllDayHeaderArrow)((PagedDayView) (obj)).findViewById(0x7f1002cc);
        obj.collapseButton = (TextView)((PagedDayView) (obj)).findViewById(0x7f1002d1);
        obj.stickyHourMask = ((PagedDayView) (obj)).findViewById(0x7f1002d4);
        obj.stickyHeaderView = (TimelyDayHeaderView)((PagedDayView) (obj)).findViewById(0x7f1002d3);
        if (!view.getResources().getBoolean(0x7f0c0016))
        {
            ((PagedDayView) (obj)).stickyHeaderView.setOnClickListener(new PagedDayView._cls1(((PagedDayView) (obj)), ((OnTimelineModeChangedListener) (obj2)), ((StickyAllDayManager) (obj3))));
        }
        obj.allDayLinearLayout = ((PagedDayView) (obj)).findViewById(0x7f1002ce);
        view = ((PagedDayView) (obj)).findViewById(0x7f1002cd);
        if (((PagedDayView) (obj)).isTablet)
        {
            j = 0;
        } else
        {
            j = 8;
        }
        view.setVisibility(j);
        obj.allDayViewGapX = ((PagedDayView) (obj)).getResources().getDimensionPixelSize(0x7f0e0307);
        if (!((PagedDayView) (obj)).isTablet)
        {
            break MISSING_BLOCK_LABEL_877;
        }
        obj.allDayViewGapX = ((PagedDayView) (obj)).allDayViewGapX + ((PagedDayView) (obj)).getResources().getDimensionPixelSize(0x7f0e01dc);
_L11:
        obj.allDayContainer = ((PagedDayView) (obj)).findViewById(0x7f1002cb);
        obj.allDayDivider = ((PagedDayView) (obj)).findViewById(0x7f10012b);
        itemsToAdd.add(obj);
        view = ((ViewGroup) (obj));
          goto _L9
_L7:
        ((PagedDayView) (obj)).gridViewFrame.setHourViewWidth(((Resources) (obj5)).getDimensionPixelSize(0x7f0e03b3));
        Object obj9 = ((PagedDayView) (obj)).gridViewFrame;
        Object obj7 = android.graphics.Paint.Align.LEFT;
        j = ((Resources) (obj5)).getDimensionPixelOffset(0x7f0e03b7);
        if (((GridViewFrame) (obj9)).gridHourDrawable == null)
        {
            throw new NullPointerException(String.valueOf("View does not draw hours"));
        }
        obj9 = ((GridViewFrame) (obj9)).gridHourDrawable;
        obj9.startMargin = j;
        ((GridHourDrawable) (obj9)).paint.setTextAlign(((android.graphics.Paint.Align) (obj7)));
        ((GridHourDrawable) (obj9)).highlightPaint.setTextAlign(((android.graphics.Paint.Align) (obj7)));
        ((GridHourDrawable) (obj9)).invalidateSelf();
        obj7 = ((PagedDayView) (obj)).gridViewFrame;
        j = ((Resources) (obj5)).getDimensionPixelOffset(0x7f0e0306);
        int k = ((Resources) (obj5)).getDimensionPixelSize(0x7f0e00df);
        ((GridViewFrame) (obj7)).gridDrawable.drawStartVerticalLine = false;
        obj7.gridStartPadding = j;
        obj7.gridEndPadding = k;
        ((PagedDayView) (obj)).gridViewFrame.useNowLineOneDayPadding = true;
        obj7 = (android.view.ViewGroup.MarginLayoutParams)((PagedDayView) (obj)).gridDayView.getLayoutParams();
        ((android.view.ViewGroup.MarginLayoutParams) (obj7)).setMarginStart(((Resources) (obj5)).getDimensionPixelOffset(0x7f0e0306) - ((Resources) (obj5)).getDimensionPixelSize(0x7f0e01db));
        ((android.view.ViewGroup.MarginLayoutParams) (obj7)).setMarginEnd(((Resources) (obj5)).getDimensionPixelOffset(0x7f0e00df));
          goto _L10
        obj.allDayViewGapX = ((PagedDayView) (obj)).allDayViewGapX + ((PagedDayView) (obj)).getResources().getDimensionPixelSize(0x7f0e00df);
          goto _L11
        obj = updateView(i, view);
        mDataFactory.getData(((MonthData) (obj)).startDay - 1, false);
        obj1 = mDataFactory;
        i = ((MonthData) (obj)).startDay;
        ((DataFactory) (obj1)).getData(((((MonthData) (obj)).numDays + i) - 1) + 1, false);
        obj = scrollManager;
        obj1 = ((PagedDayView) (view)).pagedScrollView;
        ((PagedScrollView) (obj1)).setVerticalScrollPositionFromBottom(((PagedScrollView.ScrollManager) (obj)).verticalScrollPositionFromBottom, false);
        ((PagedScrollView.ScrollManager) (obj)).scrollViews.add(obj1);
        ((PagedScrollView) (obj1)).addOnLayoutChangeListener(((android.view.View.OnLayoutChangeListener) (obj)));
        obj1.scrollManager = ((PagedScrollView.ScrollManager) (obj));
        obj1.pinchZoomController = new PinchZoomController(((PagedScrollView) (obj1)), ((PagedScrollView.ScrollManager) (obj)));
        ((PagedScrollView) (obj1)).pinchZoomController.bottomPadding = 0;
        view.getViewTreeObserver().addOnPreDrawListener(new _cls1());
        view.setEnabled(enabled);
        obj1 = stickyAllDayManager;
        obj2 = ((PagedDayView) (view)).allDayHeaderView;
        obj3 = ((PagedDayView) (view)).stickyAllDayEventsView;
        obj4 = ((PagedDayView) (view)).pagedScrollView;
        obj5 = ((PagedDayView) (view)).allDayHeaderArrow;
        if (!((PagedDayView) (view)).isTablet)
        {
            break MISSING_BLOCK_LABEL_1099;
        }
        obj = null;
_L12:
        ((StickyAllDayManager) (obj1)).addPage(view, ((AllDayHeaderView) (obj2)), ((ScrollView) (obj3)), ((PagedScrollView) (obj4)), ((AllDayHeaderArrow) (obj5)), ((View) (obj)));
        Trace.endSection();
        return view;
        obj = ((PagedDayView) (view)).collapseButton;
          goto _L12
    }

    public final boolean isViewFromObject(View view, Object obj)
    {
        return view == obj;
    }

    public final void manuallySetScrollPosition(PagedDayView pageddayview, Time time)
    {
        PagedScrollView pagedscrollview;
        int i;
        if (time == null)
        {
            i = pageddayview.getGridHourStartOffset();
        } else
        {
            GridDayView griddayview = pageddayview.gridDayView;
            float f = TimeUtils.previousHourBefore(time, 30);
            time = griddayview.timelineSegmentGeometry;
            i = ((GridTimelineSegmentGeometry) (time)).gridHourCellHeight;
            i = (int)(-((float)(int)(float)(((GridTimelineSegmentGeometry) (time)).gridlineHeight + i) * f));
        }
        i = pageddayview.pagedScrollView.computeScrollPositionFromBottom(i * -1);
        time = scrollManager;
        pagedscrollview = pageddayview.pagedScrollView;
        if (i != ((PagedScrollView.ScrollManager) (time)).verticalScrollPositionFromBottom)
        {
            time.verticalScrollPositionFromBottom = i;
            time.notifyListeners(pagedscrollview);
        }
        time = scrollManager;
        pageddayview.pagedScrollView.setVerticalScrollPositionFromBottom(((PagedScrollView.ScrollManager) (time)).verticalScrollPositionFromBottom, false);
    }

    public final void refresh(int i, boolean flag)
    {
        if (flag)
        {
            mDataFactory.updateToday();
        }
        mDataFactory.refreshDataAroundDay(0x253d8c + i, flag, false);
    }

    protected final void setDataFactory(DataFactory datafactory)
    {
        mDataFactory = datafactory;
    }

    public final void setPrimaryItem(ViewGroup viewgroup, int i, Object obj)
    {
        super.setPrimaryItem(viewgroup, i, obj);
        if (primaryPage != null)
        {
            primaryPage.allDayLinearLayout.removeOnLayoutChangeListener(allDayLayoutListener);
        }
        primaryPage = (PagedDayView)obj;
        primaryPage.allDayLinearLayout.addOnLayoutChangeListener(allDayLayoutListener);
    }

    public final void startUpdate(ViewGroup viewgroup)
    {
        itemsToAdd.clear();
        itemsToRemove.clear();
    }

    public final MonthData updateView(int i, PagedDayView pageddayview)
    {
        Trace.beginSection("DayViewPagerAdapter updateView");
        int j = 0x253d8c + i;
        MonthData monthdata = mDataFactory.getData(j, false);
        monthdata.registerListener(j, pageddayview);
        pageddayview.onUpdate(monthdata, j);
        int ai[] = monthdata.getDateInfo(j);
        if (pageddayview.isTablet)
        {
            pageddayview.stickyHeaderView.setVisibility(4);
            pageddayview.weekHeaderLabelsView.setVisibility(0);
            pageddayview.weekHeaderLabelsView.setFirstJulianDay(j);
        } else
        {
            pageddayview.weekHeaderLabelsView.setVisibility(8);
            pageddayview.stickyHeaderView.setVisibility(0);
            pageddayview.stickyHeaderView.setPosition(i);
            pageddayview.stickyHeaderView.setDateInfo(ai, false);
            pageddayview.stickyHeaderView.initializeText();
            pageddayview.stickyHeaderView.invalidate();
        }
        pageddayview.position = i;
        pageddayview.gridViewFrame.setFirstJulianDay(j);
        Trace.endSection();
        return monthdata;
    }

    private class AllDayLayoutListener
        implements android.view.View.OnLayoutChangeListener
    {

        private final DayViewPagerAdapter this$0;

        public final void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
                int k1, int l1)
        {
            i = l - j;
            j = l1 - j1;
            if (i != j)
            {
                view = scrollManager;
                view.verticalScrollPositionFromBottom = (i - j) + ((PagedScrollView.ScrollManager) (view)).verticalScrollPositionFromBottom;
                view.notifyListeners(null);
            }
        }

        AllDayLayoutListener()
        {
            this$0 = DayViewPagerAdapter.this;
            super();
        }
    }


    private class _cls1
        implements android.view.ViewTreeObserver.OnPreDrawListener
    {

        private final DayViewPagerAdapter this$0;
        private final PagedDayView val$view;

        public final boolean onPreDraw()
        {
label0:
            {
                int i;
                if (!view.gridDayView.isLayoutRequested())
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    view.getViewTreeObserver().removeOnPreDrawListener(this);
                    if (!setNextScrollPositionManually)
                    {
                        break label0;
                    }
                    Object obj = DayViewPagerAdapter.this;
                    PagedDayView pageddayview = view;
                    i = pageddayview.getGridHourStartOffset();
                    i = pageddayview.pagedScrollView.computeScrollPositionFromBottom(i * -1);
                    PagedScrollView.ScrollManager scrollmanager1 = ((DayViewPagerAdapter) (obj)).scrollManager;
                    PagedScrollView pagedscrollview = pageddayview.pagedScrollView;
                    if (i != scrollmanager1.verticalScrollPositionFromBottom)
                    {
                        scrollmanager1.verticalScrollPositionFromBottom = i;
                        scrollmanager1.notifyListeners(pagedscrollview);
                    }
                    obj = ((DayViewPagerAdapter) (obj)).scrollManager;
                    pageddayview.pagedScrollView.setVerticalScrollPositionFromBottom(((PagedScrollView.ScrollManager) (obj)).verticalScrollPositionFromBottom, false);
                    setNextScrollPositionManually = false;
                }
                return true;
            }
            if (view.position == manualScrollPosition)
            {
                manuallySetScrollPosition(view, manualScrollPositionTime);
                DayViewPagerAdapter dayviewpageradapter = DayViewPagerAdapter.this;
                Time time = manualScrollPositionTime;
                dayviewpageradapter.manualScrollPosition = -1;
                dayviewpageradapter.manualScrollPositionTime = time;
                return true;
            } else
            {
                PagedScrollView.ScrollManager scrollmanager = scrollManager;
                view.pagedScrollView.setVerticalScrollPositionFromBottom(scrollmanager.verticalScrollPositionFromBottom, false);
                return true;
            }
        }

        _cls1()
        {
            this$0 = DayViewPagerAdapter.this;
            view = pageddayview;
            super();
        }
    }

}
