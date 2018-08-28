// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.DataFactory;
import com.google.android.calendar.timely.MonthData;
import com.google.android.calendar.timely.PagedScrollView;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderArrow;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderView;
import com.google.android.calendar.timely.gridviews.allday.ExpandableChipColumnView;
import com.google.android.calendar.timely.gridviews.allday.StickyAllDayManager;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.android.calendar.utils.recycler.Recycler;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridViewFrame, WeekHeaderLabelsView, GridDayView, DndEventHandler

public abstract class GridViewPagerAdapter extends com.google.android.calendar.timely.ViewPagerFragment.ViewPagerAdapter
{

    private final AllDayLayoutListener allDayLayoutListener = new AllDayLayoutListener();
    public boolean autoScrollIgnoreTime;
    public int autoScrollItem;
    public Time autoScrollTime;
    private final Recycler chipRecycler;
    private final DndEventHandler dndHandler;
    public final ArrayList itemsAdded = new ArrayList();
    private final ArrayList itemsToAdd = new ArrayList();
    public final Activity mActivity;
    private final int numDays = 7;
    private final Recycler pageRecycler;
    public final GridPageRecyclerManager pageRecyclerManager;
    private PageHolder primaryPageHolder;
    public final com.google.android.calendar.timely.PagedScrollView.ScrollManager scrollManager = new com.google.android.calendar.timely.PagedScrollView.ScrollManager();
    public final StickyAllDayManager stickyAllDayManager;
    public ViewGroup stickyColumnContainer;

    public GridViewPagerAdapter(Activity activity, int i, int j, Recycler recycler, DndEventHandler dndeventhandler)
    {
        mActivity = activity;
        pageRecyclerManager = new GridPageRecyclerManager(activity, j, 7);
        pageRecycler = new Recycler(pageRecyclerManager, 6);
        chipRecycler = recycler;
        dndHandler = dndeventhandler;
        stickyAllDayManager = new StickyAllDayManager(activity.getResources(), false);
        autoScrollItem = -1;
    }

    public static View getCurrentView(ViewPager viewpager)
    {
        if (!(viewpager.getAdapter() instanceof GridViewPagerAdapter))
        {
            return null;
        }
        int j = viewpager.getChildCount();
        for (int i = 0; i < j; i++)
        {
            View view = viewpager.getChildAt(i);
            Object obj = view.getTag();
            if ((obj instanceof PageHolder) && ((PageHolder)obj).itemPosition == viewpager.getCurrentItem())
            {
                return view;
            }
        }

        return null;
    }

    private final void init(ViewGroup viewgroup)
    {
        PageHolder pageholder = (PageHolder)viewgroup.getTag();
        viewgroup = pageholder.daysContent;
        AllDayHeaderView alldayheaderview = pageholder.allDayContent;
        viewgroup.setFirstJulianDay(pageholder.firstJulianDay);
        pageholder.daysHeaders.setFirstJulianDay(pageholder.firstJulianDay);
        alldayheaderview.setFirstJulianDay(pageholder.firstJulianDay);
        if (numDays > 1 && pageholder.allDayHeaderArrow != null)
        {
            pageholder.allDayHeaderArrow.setJulianDay(pageholder.firstJulianDay);
        }
        int i = pageholder.firstJulianDay;
        for (int j = 0; j < numDays;)
        {
            GridDayView griddayview = (GridDayView)viewgroup.getChildAt(viewgroup.getChildrenBeforeGridDayViews() + j);
            MonthData monthdata = mDataFactory.getData(i, false);
            griddayview.setJulianDay(i);
            griddayview.onUpdate(monthdata, i);
            alldayheaderview.onUpdate(monthdata, i);
            monthdata.registerListener(i, new GridOnUpdatelistener(i, griddayview, alldayheaderview));
            j++;
            i++;
        }

    }

    public final void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        ViewGroup viewgroup1 = (ViewGroup)obj;
        PageHolder pageholder = (PageHolder)viewgroup1.getTag();
        itemsToAdd.remove(obj);
        itemsAdded.remove(obj);
        obj = scrollManager;
        PagedScrollView pagedscrollview = pageholder.daysScrollView;
        ((com.google.android.calendar.timely.PagedScrollView.ScrollManager) (obj)).scrollViews.remove(pagedscrollview);
        pagedscrollview.removeOnLayoutChangeListener(((android.view.View.OnLayoutChangeListener) (obj)));
        pagedscrollview.scrollManager = null;
        pagedscrollview.pinchZoomController = null;
        stickyAllDayManager.removePage(pageholder.allDayContent);
        viewgroup.removeView(viewgroup1);
        pageRecycler.recycle(viewgroup1);
    }

    public final void finishUpdate(ViewGroup viewgroup)
    {
        ArrayList arraylist = (ArrayList)itemsToAdd;
        int k = arraylist.size();
        int i = 0;
        while (i < k) 
        {
            ViewGroup viewgroup1 = (ViewGroup)arraylist.get(i);
            Object obj = (PageHolder)viewgroup1.getTag();
            ((PageHolder) (obj)).allDayContent.chipRecycler = chipRecycler;
            for (int j = 0; j < numDays; j++)
            {
                GridViewFrame gridviewframe = ((PageHolder) (obj)).daysContent;
                ((GridDayView)gridviewframe.getChildAt(gridviewframe.getChildrenBeforeGridDayViews() + j)).initialize(chipRecycler, dndHandler, 1);
            }

            ((PageHolder) (obj)).daysScrollView.setOnTouchListener(new _cls1());
            if (((PageHolder) (obj)).itemPosition == autoScrollItem)
            {
                autoScrollItem = -1;
                if (autoScrollIgnoreTime)
                {
                    obj = ((PageHolder) (obj)).daysContent;
                    ((GridViewFrame) (obj)).post(new GridViewFrame._cls1(((GridViewFrame) (obj))));
                } else
                {
                    obj = ((PageHolder) (obj)).daysContent;
                    ((GridViewFrame) (obj)).post(new GridViewFrame._cls2(((GridViewFrame) (obj)), new Time(autoScrollTime)));
                }
            }
            init(viewgroup1);
            viewgroup.addView(viewgroup1);
            i++;
        }
        viewgroup.requestLayout();
        scrollManager.notifyListeners(null);
    }

    public abstract int getFirstJulianDayOfItem(int i);

    public final Object instantiateItem(ViewGroup viewgroup, int i)
    {
        viewgroup = (ViewGroup)pageRecycler.getOrCreateObject();
        PageHolder pageholder = (PageHolder)viewgroup.getTag();
        pageholder.itemPosition = i;
        pageholder.firstJulianDay = getFirstJulianDayOfItem(pageholder.itemPosition);
        itemsToAdd.add(viewgroup);
        itemsAdded.add(viewgroup);
        scrollManager.add(pageholder.daysScrollView);
        stickyAllDayManager.addPage(viewgroup, pageholder.allDayContent, pageholder.allDayScrollView, pageholder.daysScrollView, pageholder.allDayHeaderArrow, null);
        return viewgroup;
    }

    public final void setPrimaryItem(final ViewGroup daysHeaders, int i, Object obj)
    {
        super.setPrimaryItem(daysHeaders, i, obj);
        obj = (PageHolder)((ViewGroup)obj).getTag();
        if (primaryPageHolder != obj)
        {
            if (primaryPageHolder != null)
            {
                primaryPageHolder.allDayScrollView.removeOnLayoutChangeListener(allDayLayoutListener);
            }
            primaryPageHolder = ((PageHolder) (obj));
            primaryPageHolder.allDayScrollView.addOnLayoutChangeListener(allDayLayoutListener);
            if (stickyColumnContainer != null)
            {
                daysHeaders = stickyAllDayManager;
                obj = ((PageHolder) (obj)).allDayContent;
                daysHeaders.mainAllDayInfo = (com.google.android.calendar.timely.gridviews.allday.StickyAllDayManager.AllDayInfo)((StickyAllDayManager) (daysHeaders)).allDayInfo.get(obj);
                daysHeaders.setSharedState(((StickyAllDayManager) (daysHeaders)).sharedState);
                ((AllDayHeaderArrow)stickyColumnContainer.findViewById(0x7f100374)).setJulianDay(primaryPageHolder.firstJulianDay);
            }
            if (primaryPageHolder != null && AccessibilityUtils.isAccessibilityEnabled(mActivity))
            {
                daysHeaders = primaryPageHolder.daysHeaders;
                daysHeaders.setFocusable(true);
                daysHeaders.clearFocus();
                daysHeaders.post(new _cls2());
                return;
            }
        }
    }

    public final void startUpdate(ViewGroup viewgroup)
    {
        itemsToAdd.clear();
    }

    public final void updateVisibleViews()
    {
        ArrayList arraylist = (ArrayList)itemsAdded;
        int j = arraylist.size();
        for (int i = 0; i < j; i++)
        {
            ViewGroup viewgroup = (ViewGroup)arraylist.get(i);
            pageRecyclerManager.clean(viewgroup);
            PageHolder pageholder = (PageHolder)viewgroup.getTag();
            pageholder.firstJulianDay = getFirstJulianDayOfItem(pageholder.itemPosition);
            init(viewgroup);
        }

    }

    private class GridPageRecyclerManager
        implements com.google.android.calendar.utils.recycler.Recycler.RecyclerManager
    {

        private final Context context;
        private final int numDays;
        private final int resourceId;
        private final GridViewPagerAdapter this$0;

        public final void clean(ViewGroup viewgroup)
        {
            viewgroup = (PageHolder)viewgroup.getTag();
            GridViewFrame gridviewframe = ((PageHolder) (viewgroup)).daysContent;
            int i = ((PageHolder) (viewgroup)).firstJulianDay;
            for (int j = 0; j < numDays;)
            {
                mDataFactory.getData(i, false).unregisterListener(i, 5);
                ((GridDayView)gridviewframe.getChildAt(gridviewframe.getChildrenBeforeGridDayViews() + j)).clearChips();
                j++;
                i++;
            }

            ((PageHolder) (viewgroup)).allDayContent.clear();
            ((PageHolder) (viewgroup)).allDayScrollView.setScrollY(0);
            ((PageHolder) (viewgroup)).daysScrollView.setVerticalScrollPositionFromBottom(0, false);
        }

        public final volatile void clean(Object obj)
        {
            clean((ViewGroup)obj);
        }

        public final Object createObject()
        {
            ViewGroup viewgroup = (ViewGroup)View.inflate(context, resourceId, null);
            PageHolder pageholder = new PageHolder();
            pageholder.daysScrollView = (PagedScrollView)viewgroup.findViewById(0x7f10022f);
            pageholder.daysContent = (GridViewFrame)viewgroup.findViewById(0x7f100230);
            pageholder.allDayContent = (AllDayHeaderView)viewgroup.findViewById(0x7f10022e);
            pageholder.allDayScrollView = (ScrollView)viewgroup.findViewById(0x7f10022d);
            pageholder.daysHeaders = (WeekHeaderLabelsView)viewgroup.findViewById(0x7f10022c);
            pageholder.allDayHeaderArrow = (AllDayHeaderArrow)viewgroup.findViewById(0x7f100374);
            for (int i = 0; i < numDays; i++)
            {
                View.inflate(context, 0x7f050183, pageholder.daysContent);
            }

            viewgroup.setTag(pageholder);
            return viewgroup;
        }

        public final volatile void prepareToReuse(Object obj)
        {
        }

        public GridPageRecyclerManager(Context context1, int i, int j)
        {
            this$0 = GridViewPagerAdapter.this;
            super();
            context = context1;
            numDays = j;
            resourceId = i;
        }
    }


    private class AllDayLayoutListener
        implements android.view.View.OnLayoutChangeListener
    {

        private final GridViewPagerAdapter this$0;

        public final void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
                int k1, int l1)
        {
            i = l - j;
            j = l1 - j1;
            if (i != j)
            {
                view = scrollManager;
                view.verticalScrollPositionFromBottom = (i - j) + ((com.google.android.calendar.timely.PagedScrollView.ScrollManager) (view)).verticalScrollPositionFromBottom;
                view.notifyListeners(null);
            }
        }

        AllDayLayoutListener()
        {
            this$0 = GridViewPagerAdapter.this;
            super();
        }
    }


    private class PageHolder
    {

        public AllDayHeaderView allDayContent;
        public AllDayHeaderArrow allDayHeaderArrow;
        public ScrollView allDayScrollView;
        public GridViewFrame daysContent;
        public WeekHeaderLabelsView daysHeaders;
        public PagedScrollView daysScrollView;
        public int firstJulianDay;
        public int itemPosition;

        public PageHolder()
        {
        }
    }


    private class GridOnUpdatelistener
        implements com.google.android.calendar.timely.RangedData.OnUpdateListener
    {

        private final AllDayHeaderView allDayView;
        private final GridDayView dayView;
        private int tag;

        public final int getListenerTag()
        {
            return tag;
        }

        public final int getListenerTagType()
        {
            return 5;
        }

        public final void postUpdate$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NKQRREEHK48OBKC4TKIMICCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UT39DLIMOU9FA9GMSPR5CH262T314HAN0P31EHIKCQBED5PMGPB49HKN6T35DPIN4EP9AO______0(MonthData monthdata, int i, final com.google.android.calendar.timely.RangedData.UpdateFinishedListener updateFinishedListener)
        {
            class _cls1
                implements com.google.android.calendar.timely.RangedData.UpdateFinishedListener
            {

                private int counter;
                private final com.google.android.calendar.timely.RangedData.UpdateFinishedListener val$updateFinishedListener;

                public final void notifyUpdateFinished()
                {
                    counter = counter - 1;
                    if (counter == 0)
                    {
                        updateFinishedListener.notifyUpdateFinished();
                    }
                }

                _cls1()
                {
                    updateFinishedListener = updatefinishedlistener;
                    super();
                    counter = 2;
                }
            }

            updateFinishedListener = new _cls1();
            if (dayView != null)
            {
                dayView.postUpdate(monthdata, i, updateFinishedListener);
            }
            if (allDayView != null)
            {
                AllDayHeaderView alldayheaderview = allDayView;
                monthdata = (FluentFuture)CalendarExecutor.MAIN.submit(new com.google.android.calendar.timely.gridviews.allday.AllDayHeaderView..Lambda._cls0(alldayheaderview, monthdata, i, updateFinishedListener));
            }
        }

        public final void setListenerTag(int i)
        {
            tag = i;
        }

        public GridOnUpdatelistener(int i, GridDayView griddayview, AllDayHeaderView alldayheaderview)
        {
            tag = i;
            dayView = griddayview;
            allDayView = alldayheaderview;
        }
    }


    private class _cls1
        implements android.view.View.OnTouchListener
    {

        public final boolean onTouch(View view, MotionEvent motionevent)
        {
            class .Lambda._cls0
                implements Runnable
            {

                public static final Runnable $instance = new .Lambda._cls0();

                public final void run()
                {
                    CreateNewEventView.removeSelectedTime();
                }


                private .Lambda._cls0()
                {
                }
            }

            view.getHandler().post(.Lambda._cls0..instance);
            return false;
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements Runnable
    {

        private final WeekHeaderLabelsView val$daysHeaders;

        public final void run()
        {
            daysHeaders.requestFocus();
            AccessibilityUtils.requestAccessibilityFocus(daysHeaders);
        }

        _cls2()
        {
            daysHeaders = weekheaderlabelsview;
            super();
        }
    }

}
