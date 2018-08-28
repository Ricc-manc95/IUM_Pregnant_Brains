// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.newevent.CreateNewEventView;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.utils.recycler.Recycler;

// Referenced classes of package com.google.android.calendar.timely:
//            BaseCalendarFragment, DataFactory

public abstract class ViewPagerFragment extends BaseCalendarFragment
    implements android.support.v4.view.ViewPager.OnPageChangeListener, DataFactory.OnAllEventsDataReadyListener
{
    public static abstract class ViewPagerAdapter extends PagerAdapter
    {

        public DataFactory mDataFactory;

        public boolean isViewFromObject(View view, Object obj)
        {
            return view == obj;
        }

        protected void setDataFactory(DataFactory datafactory)
        {
            mDataFactory = datafactory;
        }

        public void updateVisibleViews()
        {
        }

        public ViewPagerAdapter()
        {
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/ViewPagerFragment);
    public int sourceOfPageChange;

    public ViewPagerFragment()
    {
        super(0x7f050182);
        sourceOfPageChange = 0;
    }

    protected ViewPagerFragment(int i)
    {
        super(i);
        sourceOfPageChange = 0;
    }

    protected final void eventsChanged()
    {
        if (mDataFactory != null)
        {
            DataFactory datafactory = mDataFactory;
            Object obj;
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            datafactory.setHideDeclinedEvents(((Context) (obj)));
            mDataFactory.updateToday();
            mDataFactory.refreshDataAroundDay(getFirstJulianDay(getViewPager().getCurrentItem()), true, true);
        }
    }

    public abstract int getFirstJulianDay(int i);

    public abstract int getItemPositionFromTime(Time time, Activity activity);

    public abstract ViewPager getViewPager();

    public abstract ViewPagerAdapter getViewPagerAdapter();

    public void goTo(Time time, boolean flag, boolean flag1)
    {
        Object obj1 = null;
        if (getViewPager() == null)
        {
            LogUtils.d(TAG, "Tried to goto a time but ViewPager hadn't been created yet!", new Object[0]);
            return;
        }
        super.goTo(time, flag, flag1);
        sourceOfPageChange = 1;
        Object obj;
        ViewPager viewpager;
        int i;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        i = getItemPositionFromTime(time, ((Activity) (obj)));
        if (getViewPager().getCurrentItem() == i)
        {
            updateTitle(i);
        } else
        {
            getViewPager().setCurrentItem(i, flag);
        }
        viewpager = getViewPager();
        if (super.mHost == null)
        {
            obj = obj1;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        viewpager.setCurrentItem(getItemPositionFromTime(time, ((Activity) (obj))), flag);
        time.writeFieldsToImpl();
        i = android.text.format.Time.getJulianDay(time.impl.toMillis(false), time.gmtoff);
        mDataFactory.setTimePassage(0, i);
        mDataFactory.refreshDataAroundDay(i, false, false);
    }

    public final void onAllEventsDataReady()
    {
        class .Lambda._cls0
            implements Runnable
        {

            private final ViewPagerFragment arg$1;

            public final void run()
            {
                ViewPagerFragment viewpagerfragment = arg$1;
                ViewPager viewpager = viewpagerfragment.getViewPager();
                for (int i = 0; i < viewpager.getChildCount(); i++)
                {
                    viewpagerfragment.updatePage(viewpager.getChildAt(i));
                }

            }

            .Lambda._cls0()
            {
                arg$1 = ViewPagerFragment.this;
            }
        }

        runWhenNotInteracting(new .Lambda._cls0());
    }

    public void onCalendarPropertyChanged(int i, Object obj)
    {
        super.onCalendarPropertyChanged(i, obj);
        if (i == 5 || i == 0 || i == 13)
        {
            obj = getViewPagerAdapter();
            if (obj != null)
            {
                ((ViewPagerAdapter) (obj)).updateVisibleViews();
            }
        }
    }

    public void onInitView(LayoutInflater layoutinflater, ViewGroup viewgroup, Recycler recycler)
    {
        Object obj = null;
        super.onInitView(layoutinflater, viewgroup, recycler);
        recycler = getViewPagerAdapter();
        mDataFactory.registerOnAllEventsDataReadyListener(this);
        recycler.setDataFactory(mDataFactory);
        viewgroup = getViewPager();
        if (super.mHost == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        viewgroup.setPageMargin(layoutinflater.getResources().getDimensionPixelOffset(0x7f0e03da));
        viewgroup.setAdapter(recycler);
        recycler = mLastSelectedTime;
        if (super.mHost == null)
        {
            layoutinflater = obj;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        viewgroup.setCurrentItem(getItemPositionFromTime(recycler, layoutinflater));
        viewgroup.setOnPageChangeListener(this);
        updateTitle(viewgroup.getCurrentItem());
    }

    public void onPageScrollStateChanged(int i)
    {
        if (i == 0)
        {
            CreateNewEventView.removeSelectedTime();
        }
        sourceOfPageChange = 2;
    }

    public final void onPageScrolled(int i, float f, int j)
    {
    }

    public final void onPageSelected(final int position)
    {
        final int julianDay;
        if (sourceOfPageChange == 1)
        {
            Time time = mLastSelectedTime;
            time.writeFieldsToImpl();
            julianDay = android.text.format.Time.getJulianDay(time.impl.toMillis(false), mLastSelectedTime.gmtoff);
        } else
        {
            julianDay = getFirstJulianDay(position);
        }
        mDataFactory.refreshDataAroundDay(julianDay, false, false);
        getViewPager().post(new _cls1());
    }

    public final void setViewTranslationX(float f)
    {
        getViewPager().setTranslationX(f);
    }

    public void setViewTranslationY(float f)
    {
        super.setViewTranslationY(f);
        getViewPager().setTranslationY(f);
    }

    public abstract void updatePage(View view);

    public abstract void updateTitle(int i);

    protected final void updateTopValues(boolean flag)
    {
        boolean flag1 = false;
        Resources resources = requireContext().getResources();
        Object obj;
        int i;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = ((FragmentActivity) (obj)).findViewById(0x7f100243);
        if (flag)
        {
            if (resources.getConfiguration().orientation == 1)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                i = 0x7f0e043e;
            } else
            {
                i = 0x7f0e03db;
            }
            ((View) (obj)).setPadding(0, resources.getDimensionPixelOffset(i), 0, 0);
        } else
        {
            ((View) (obj)).setPadding(0, 0, 0, 0);
        }
        obj = getViewPager();
        if (!flag)
        {
            i = ((flag1) ? 1 : 0);
        } else
        {
            i = resources.getDimensionPixelOffset(0x7f0e0445);
        }
        ((android.view.ViewGroup.MarginLayoutParams)((View) (obj)).getLayoutParams()).topMargin = i;
        ((View) (obj)).requestLayout();
    }


    private class _cls1
        implements Runnable
    {

        private final ViewPagerFragment this$0;
        private final int val$julianDay;
        private final int val$position;

        public final void run()
        {
            boolean flag;
            if (mState >= 5)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                return;
            }
            if (sourceOfPageChange == 2)
            {
                Time time = mLastSelectedTime;
                Object obj = ViewPagerFragment.this;
                int i = ((ViewPagerFragment) (obj)).getFirstJulianDay(position);
                if (((Fragment) (obj)).mHost == null)
                {
                    obj = null;
                } else
                {
                    obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
                }
                obj = new Time(Utils.getTimeZoneId(((Context) (obj))));
                ((Time) (obj)).setJulianDaySafe(i);
                ((Time) (obj)).writeFieldsToImpl();
                time.set(((Time) (obj)).impl.toMillis(false));
            }
            updateMiniMonth(julianDay);
            updateTitle(position);
        }

        _cls1()
        {
            this$0 = ViewPagerFragment.this;
            position = i;
            julianDay = j;
            super();
        }
    }

}
