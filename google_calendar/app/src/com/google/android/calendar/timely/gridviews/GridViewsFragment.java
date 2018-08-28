// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.newevent.NewEventTimeChangedListenerHolder;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.BaseCalendarFragment;
import com.google.android.calendar.timely.DataFactory;
import com.google.android.calendar.timely.MonthData;
import com.google.android.calendar.timely.ViewPagerFragment;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderArrow;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderView;
import com.google.android.calendar.utils.recycler.Recycler;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridViewFrame, GridViewPagerAdapter, GridDayView, WeekHeaderLabelsView

public abstract class GridViewsFragment extends ViewPagerFragment
    implements com.google.android.calendar.newevent.NewEventTimeChangedListenerHolder.OnCreateNewEventTimeChangedListener
{

    private static final String TAG = com/google/android/calendar/timely/gridviews/GridViewsFragment.getSimpleName();
    private int mNumDays;

    protected GridViewsFragment(int i)
    {
        mNumDays = 7;
    }

    protected volatile com.google.android.calendar.timely.ViewPagerFragment.ViewPagerAdapter getViewPagerAdapter()
    {
        return getViewPagerAdapter();
    }

    protected abstract GridViewPagerAdapter getViewPagerAdapter();

    protected final void goTo(Time time, boolean flag, boolean flag1)
    {
        int i = 0;
        if (getViewPager() == null)
        {
            LogUtils.e(TAG, "Tried to goto a time but ViewPager hadn't been created yet!", new Object[0]);
        } else
        {
            super.goTo(time, flag, flag1);
            if (!mMinimonthToggledOpen)
            {
                Object obj;
                int j;
                if (super.mHost == null)
                {
                    obj = null;
                } else
                {
                    obj = (FragmentActivity)super.mHost.mActivity;
                }
                j = getItemPositionFromTime(time, ((android.app.Activity) (obj)));
                for (obj = getViewPager(); i < ((ViewPager) (obj)).getChildCount(); i++)
                {
                    GridViewPagerAdapter.PageHolder pageholder = (GridViewPagerAdapter.PageHolder)((ViewPager) (obj)).getChildAt(i).getTag();
                    if (pageholder.itemPosition == j)
                    {
                        if (flag1)
                        {
                            time = pageholder.daysContent;
                            time.post(new GridViewFrame._cls1(time));
                            return;
                        } else
                        {
                            obj = pageholder.daysContent;
                            ((GridViewFrame) (obj)).post(new GridViewFrame._cls2(((GridViewFrame) (obj)), new Time(time)));
                            return;
                        }
                    }
                }

                obj = getViewPagerAdapter();
                obj.autoScrollItem = j;
                obj.autoScrollIgnoreTime = flag1;
                obj.autoScrollTime = time;
                return;
            }
        }
    }

    public boolean hasMiniMonth()
    {
        return true;
    }

    public final boolean isMiniMonthDraggable()
    {
        return isMiniMonthToggleable() && mIsPortrait;
    }

    public final boolean isMiniMonthToggleable()
    {
        return hasMiniMonth();
    }

    public final void onCreateNewEventTimeChanged$5152ILG_0()
    {
        ViewPager viewpager = getViewPager();
        for (int i = 0; i < viewpager.getChildCount(); i++)
        {
            GridViewFrame gridviewframe = ((GridViewPagerAdapter.PageHolder)viewpager.getChildAt(i).getTag()).daysContent;
            for (int j = 0; j < mNumDays; j++)
            {
                ((GridDayView)gridviewframe.getChildAt(gridviewframe.getChildrenBeforeGridDayViews() + j)).updateCreateNewEventView();
            }

        }

    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        if (bundle != null)
        {
            mNumDays = bundle.getInt("key_num_days");
        }
        return super.onCreateView(layoutinflater, viewgroup, bundle);
    }

    public void onInitView(LayoutInflater layoutinflater, ViewGroup viewgroup, Recycler recycler)
    {
        NewEventTimeChangedListenerHolder.INSTANCE.createNewEventTimeChangedListeners.add(this);
        super.onInitView(layoutinflater, viewgroup, recycler);
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putInt("key_num_days", mNumDays);
        super.onSaveInstanceState(bundle);
    }

    protected final void updatePage(View view)
    {
        view = (ViewGroup)view;
        Object obj = getViewPagerAdapter();
        if (!(view.getTag() instanceof GridViewPagerAdapter.PageHolder))
        {
            throw new IllegalArgumentException();
        }
        ((GridViewPagerAdapter) (obj)).pageRecyclerManager.clean(view);
        GridViewPagerAdapter.PageHolder pageholder = (GridViewPagerAdapter.PageHolder)view.getTag();
        view = pageholder.daysContent;
        obj = pageholder.allDayContent;
        pageholder.daysHeaders.setFirstJulianDay(pageholder.firstJulianDay);
        pageholder.daysContent.setFirstJulianDay(pageholder.firstJulianDay);
        ((AllDayHeaderView) (obj)).setFirstJulianDay(pageholder.firstJulianDay);
        if (mNumDays > 1 && pageholder.allDayHeaderArrow != null)
        {
            pageholder.allDayHeaderArrow.setJulianDay(pageholder.firstJulianDay);
        }
        int i = pageholder.firstJulianDay;
        int j = 0;
        while (j < mNumDays) 
        {
            GridDayView griddayview = (GridDayView)view.getChildAt(view.getChildrenBeforeGridDayViews() + j);
            MonthData monthdata = mDataFactory.getData(i, false);
            griddayview.setJulianDay(i);
            if (monthdata.isDataReady())
            {
                griddayview.onUpdate(monthdata, i);
                ((AllDayHeaderView) (obj)).onUpdate(monthdata, i);
                monthdata.registerListener(i, new GridViewPagerAdapter.GridOnUpdatelistener(i, griddayview, ((AllDayHeaderView) (obj))));
            } else
            {
                monthdata.registerListener(i, new GridViewPagerAdapter.GridOnUpdatelistener(i, griddayview, ((AllDayHeaderView) (obj))));
            }
            j++;
            i++;
        }
    }

}
