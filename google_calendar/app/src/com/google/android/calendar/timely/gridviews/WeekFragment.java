// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.app.Activity;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.calendar.CalendarController;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.BaseCalendarFragment;
import com.google.android.calendar.timely.PagedScrollView;
import com.google.android.calendar.timely.ViewPagerFragment;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderArrow;
import com.google.android.calendar.timely.gridviews.allday.StickyAllDayManager;
import com.google.android.calendar.utils.recycler.Recycler;
import com.google.android.calendar.utils.rtl.RtlUtils;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridViewsFragment, GridViewPagerAdapter, GridHourView, GridDndController, 
//            GridViewFrame, GridHourDrawable, DndEventHandler

public class WeekFragment extends GridViewsFragment
    implements com.google.android.calendar.CalendarController.Command.Handler
{
    final class DndControllerDelegate
        implements GridDndController.Delegate
    {

        private final GridHourView staticHourView;
        private final WeekFragment this$0;

        public final GridHourDrawable getCurrentHourDrawable()
        {
            boolean flag1 = true;
            if (staticHourView == null)
            {
                Object obj = GridViewPagerAdapter.getCurrentView(viewPager);
                boolean flag;
                if (obj != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalStateException();
                }
                obj = (GridViewFrame)((View) (obj)).findViewById(0x7f100230);
                if (obj != null)
                {
                    flag = flag1;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalStateException();
                } else
                {
                    return ((GridViewFrame) (obj)).gridHourDrawable;
                }
            } else
            {
                return staticHourView.gridHourDrawable;
            }
        }

        public final String getViewMode()
        {
            return "preferences_value_week_view";
        }

        public final void scrollHorizontally(int i)
        {
            int j = i;
            if (RtlUtils.isLayoutDirectionRtl(getContext()))
            {
                j = -i;
            }
            viewPager.setCurrentItem(viewPager.getCurrentItem() + j);
        }

        public final void scrollVertically(int i)
        {
            boolean flag1 = true;
            Object obj = GridViewPagerAdapter.getCurrentView(viewPager);
            boolean flag;
            if (obj != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            }
            obj = (PagedScrollView)((View) (obj)).findViewById(0x7f10022f);
            if (obj != null)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            } else
            {
                ((PagedScrollView) (obj)).verticalScrollBy(i);
                return;
            }
        }

        public DndControllerDelegate(GridHourView gridhourview)
        {
            this$0 = WeekFragment.this;
            super();
            staticHourView = gridhourview;
        }
    }

    final class MenuListAdapter
        implements ListAdapter
    {

        private Menu sourceMenu;
        private final WeekFragment this$0;

        public final boolean areAllItemsEnabled()
        {
            return true;
        }

        public final int getCount()
        {
            return sourceMenu.size();
        }

        public final Object getItem(int i)
        {
            return sourceMenu.getItem(i);
        }

        public final long getItemId(int i)
        {
            return (long)((MenuItem)getItem(i)).getItemId();
        }

        public final int getItemViewType(int i)
        {
            return 0;
        }

        public final View getView(int i, View view, ViewGroup viewgroup)
        {
            view = WeekFragment.this;
            if (((Fragment) (view)).mHost == null)
            {
                view = null;
            } else
            {
                view = (FragmentActivity)((Fragment) (view)).mHost.mActivity;
            }
            view = view.getLayoutInflater().inflate(0x7f05010d, viewgroup, false);
            viewgroup = (MenuItem)getItem(i);
            ((TextView)view.findViewById(0x7f100047)).setText(viewgroup.getTitle());
            return view;
        }

        public final int getViewTypeCount()
        {
            return 1;
        }

        public final boolean hasStableIds()
        {
            return true;
        }

        public final boolean isEmpty()
        {
            return sourceMenu != null;
        }

        public final boolean isEnabled(int i)
        {
            return true;
        }

        public final void registerDataSetObserver(DataSetObserver datasetobserver)
        {
        }

        public final void unregisterDataSetObserver(DataSetObserver datasetobserver)
        {
        }

        MenuListAdapter(Activity activity, int i, View view)
        {
            this$0 = WeekFragment.this;
            super();
            weekfragment = new PopupMenu(activity, view);
            getMenuInflater().inflate(i, getMenu());
            sourceMenu = getMenu();
        }
    }

    public final class WeekViewPageAdapter extends GridViewPagerAdapter
    {

        public final int getCount()
        {
            return 3497;
        }

        public final int getFirstJulianDayOfItem(int i)
        {
            return Utils.getJulianFirstDayFromWeeksSinceEpoch(i, Utils.getFirstDayOfWeekAsTime(mActivity));
        }

        public WeekViewPageAdapter(Activity activity, View view, Recycler recycler)
        {
            super(activity, 7, 0x7f050184, recycler, DndEventHandler.create(dndController, view));
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/gridviews/WeekFragment);
    public GridDndController dndController;
    private ViewGroup hoursContainer;
    public MenuListAdapter overflowAdapter;
    public View overflowButton;
    private Drawable overflowCircleBackground;
    private Drawable overflowEmptyBackground;
    private boolean showingBackground;
    public ViewPager viewPager;
    private GridViewPagerAdapter viewPagerAdapter;

    public WeekFragment()
    {
        super(7);
    }

    private final void updateTitle(int i, int j)
    {
        Object obj;
        Time time;
        Object obj1;
        CalendarController calendarcontroller1;
        boolean flag;
        boolean flag1;
        boolean flag2;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = Utils.getTimeZoneId(((android.content.Context) (obj)));
        obj1 = new Time(((String) (obj)));
        ((Time) (obj1)).setJulianDaySafe(i);
        ((Time) (obj1)).writeFieldsToImpl();
        ((Time) (obj1)).impl.normalize(false);
        ((Time) (obj1)).copyFieldsFromImpl();
        obj = new Time(((String) (obj)));
        if (mMinimonthToggledOpen)
        {
            obj = obj1;
        } else
        {
            ((Time) (obj)).writeFieldsToImpl();
            ((Time) (obj)).impl.setJulianDay(j);
            ((Time) (obj)).copyFieldsFromImpl();
            obj.hour = 1;
            ((Time) (obj)).writeFieldsToImpl();
            ((Time) (obj)).impl.normalize(false);
            ((Time) (obj)).copyFieldsFromImpl();
        }
        calendarcontroller1 = mController;
        if (mMinimonthToggledOpen)
        {
            time = mLastSelectedTime;
        } else
        {
            time = null;
        }
        flag1 = mIsTabletConfig;
        flag2 = mIsTabletConfig;
        if (mLastSelectedTime.year == mTodayYear)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        calendarcontroller1.updateVisibleRange(this, ((Time) (obj1)), ((Time) (obj)), time, flag1, DateTimeFormatHelper.getToolbarFormatFlags(flag2, flag));
        if (mMinimonthToggledOpen)
        {
            obj = new Time("UTC");
            i = Utils.getJulianFirstDayFromMonth(super.mLastSelectedTime.month, super.mLastSelectedTime.year);
            ((Time) (obj)).writeFieldsToImpl();
            ((Time) (obj)).impl.setJulianDay(i);
            ((Time) (obj)).copyFieldsFromImpl();
            time = new Time("UTC");
            i = getMonthEndJulianDay();
            time.writeFieldsToImpl();
            time.impl.setJulianDay(i);
            time.copyFieldsFromImpl();
            obj1 = mController;
            com.google.android.calendar.CalendarController.Command command = new com.google.android.calendar.CalendarController.Command(8192L);
            command.startTime = ((Time) (obj));
            command.endTime = time;
            ((CalendarController) (obj1)).executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(command);
            return;
        } else
        {
            CalendarController calendarcontroller = mController;
            com.google.android.calendar.CalendarController.Command command1 = new com.google.android.calendar.CalendarController.Command(8192L);
            command1.startTime = ((Time) (obj1));
            command1.endTime = ((Time) (obj));
            calendarcontroller.executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(command1);
            return;
        }
    }

    protected final int getFirstJulianDay(int i)
    {
        return viewPagerAdapter.getFirstJulianDayOfItem(i);
    }

    protected final int getItemPositionFromTime(Time time, Activity activity)
    {
        return Utils.getWeekNumberFromTime(mLastSelectedTime, activity);
    }

    protected final String getPrimesLogTag()
    {
        return "WeekView";
    }

    protected final ViewPager getViewPager()
    {
        return viewPager;
    }

    protected final volatile com.google.android.calendar.timely.ViewPagerFragment.ViewPagerAdapter getViewPagerAdapter()
    {
        return getViewPagerAdapter();
    }

    protected final GridViewPagerAdapter getViewPagerAdapter()
    {
        return viewPagerAdapter;
    }

    public final boolean hasMiniMonth()
    {
        return mIsPortrait || mIsTabletConfig;
    }

    public final void onDestroy()
    {
        super.onDestroy();
        if (viewPagerAdapter != null)
        {
            GridViewPagerAdapter gridviewpageradapter = viewPagerAdapter;
            ArrayList arraylist = (ArrayList)gridviewpageradapter.itemsAdded;
            int j = arraylist.size();
            for (int i = 0; i < j;)
            {
                Object obj = arraylist.get(i);
                i++;
                obj = (ViewGroup)obj;
                gridviewpageradapter.pageRecyclerManager.clean(((ViewGroup) (obj)));
            }

        }
    }

    public final void onInitView(LayoutInflater layoutinflater, ViewGroup viewgroup, Recycler recycler)
    {
        Object obj1 = null;
        viewPager = (ViewPager)viewgroup.findViewById(0x7f1001ed);
        GridHourView gridhourview = (GridHourView)viewgroup.findViewById(0x7f10001d);
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        dndController = new GridDndController(((Activity) (obj)), new DndControllerDelegate(gridhourview));
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        viewPagerAdapter = new WeekViewPageAdapter(((Activity) (obj)), viewgroup, recycler);
        hoursContainer = (ViewGroup)viewgroup.findViewById(0x7f100393);
        if (hoursContainer != null)
        {
            obj = viewPagerAdapter;
            ViewGroup viewgroup1 = hoursContainer;
            obj.stickyColumnContainer = viewgroup1;
            StickyAllDayManager stickyalldaymanager = ((GridViewPagerAdapter) (obj)).stickyAllDayManager;
            stickyalldaymanager.sharedArrow = (AllDayHeaderArrow)viewgroup1.findViewById(0x7f100374);
            stickyalldaymanager.sharedArrow.setOnClickListener(stickyalldaymanager);
            stickyalldaymanager.sharedState = 1;
            ((GridViewPagerAdapter) (obj)).scrollManager.add((PagedScrollView)viewgroup1.findViewById(0x7f10022f));
        }
        super.onInitView(layoutinflater, viewgroup, recycler);
        overflowButton = viewgroup.findViewById(0x7f100394);
        if (overflowButton != null)
        {
            if (super.mHost == null)
            {
                layoutinflater = obj1;
            } else
            {
                layoutinflater = (FragmentActivity)super.mHost.mActivity;
            }
            layoutinflater = layoutinflater.getResources().getDrawable(0x7f0200d9);
            overflowButton.setOnClickListener(new _cls1());
            overflowEmptyBackground = overflowButton.getBackground();
            overflowCircleBackground = layoutinflater;
        }
        layoutinflater = viewgroup.findViewById(0x7f100392);
        if (layoutinflater != null)
        {
            viewgroup = VisualElementHolder.instance;
            if (viewgroup == null)
            {
                throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
            } else
            {
                ((VisualElementAttacher)viewgroup).attachWeekView(layoutinflater);
                return;
            }
        } else
        {
            LogUtils.wtf(TAG, "Week container not found in week fragment!", new Object[0]);
            return;
        }
    }

    public final void onMiniMonthVisibilityChanged(boolean flag)
    {
        super.onMiniMonthVisibilityChanged(flag);
        updateTitle(getViewPager().getCurrentItem());
    }

    public final void onPageScrollStateChanged(int i)
    {
        super.onPageScrollStateChanged(i);
        if (overflowButton == null) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 0 2: default 40
    //                   0 65
    //                   1 41
    //                   2 41;
           goto _L2 _L3 _L4 _L4
_L2:
        return;
_L4:
        if (!showingBackground)
        {
            overflowButton.setBackground(overflowCircleBackground);
            showingBackground = true;
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (showingBackground)
        {
            overflowButton.setBackground(overflowEmptyBackground);
            showingBackground = false;
            return;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public final void onResume()
    {
        super.onResume();
        Object obj = VisualElementHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        }
        VisualElementAttacher visualelementattacher = (VisualElementAttacher)obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        visualelementattacher.recordImpression(((Context) (obj)), viewPager);
    }

    public final void setViewTranslationY(float f)
    {
        super.setViewTranslationY(f);
        if (hoursContainer != null)
        {
            hoursContainer.setTranslationY(f);
        }
    }

    public final void updateTitle(int i)
    {
        if (mMinimonthToggledOpen)
        {
            Time time = mLastSelectedTime;
            time.writeFieldsToImpl();
            i = android.text.format.Time.getJulianDay(time.impl.toMillis(false), mLastSelectedTime.gmtoff);
            updateTitle(i, i);
            return;
        } else
        {
            i = viewPagerAdapter.getFirstJulianDayOfItem(i);
            updateTitle(i, (i + 7) - 1);
            return;
        }
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        public final WeekFragment this$0;

        public final void onClick(View view)
        {
            Object obj1 = null;
            Object obj = WeekFragment.this;
            final ListPopupWindow popup;
            WeekFragment weekfragment;
            if (((Fragment) (obj)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            popup = new ListPopupWindow(((Context) (obj)));
            popup.setAnchorView(view);
            popup.setModal(true);
            popup.setWidth((int)requireContext().getResources().getDimension(0x7f0e030c));
            weekfragment = WeekFragment.this;
            if (weekfragment.overflowAdapter == null)
            {
                class _cls1
                    implements android.widget.AdapterView.OnItemClickListener
                {

                    private final _cls1 this$1;
                    private final ListPopupWindow val$popup;

                    public final void onItemClick(AdapterView adapterview, View view1, int i, long l)
                    {
                        adapterview = _fld0;
                        if (((Fragment) (adapterview)).mHost == null)
                        {
                            adapterview = null;
                        } else
                        {
                            adapterview = (FragmentActivity)((Fragment) (adapterview)).mHost.mActivity;
                        }
                        adapterview.onOptionsItemSelected((MenuItem)overflowAdapter.getItem(i));
                        popup.dismiss();
                    }

                _cls1()
                {
                    this$1 = _cls1.this;
                    popup = listpopupwindow;
                    super();
                }
                }

                if (((Fragment) (weekfragment)).mHost == null)
                {
                    obj = obj1;
                } else
                {
                    obj = (FragmentActivity)((Fragment) (weekfragment)).mHost.mActivity;
                }
                weekfragment.overflowAdapter = weekfragment. new MenuListAdapter(((Activity) (obj)), 0x7f150009, weekfragment.overflowButton);
            }
            popup.setAdapter(overflowAdapter);
            popup.setOnItemClickListener(new _cls1());
            view.setOnTouchListener(popup.createDragToOpenListener(view));
            popup.show();
        }

        _cls1()
        {
            this$0 = WeekFragment.this;
            super();
        }
    }

}
