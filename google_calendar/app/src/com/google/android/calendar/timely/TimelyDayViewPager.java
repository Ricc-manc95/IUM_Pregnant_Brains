// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.gridviews.GridDayView;
import com.google.android.calendar.timely.interaction.helper.DelayedUpdateHelper;
import com.google.android.calendar.utils.viewpager.DisablableViewPager;

// Referenced classes of package com.google.android.calendar.timely:
//            DayViewPagerAdapter, PagedDayView, DataFactory

public class TimelyDayViewPager extends DisablableViewPager
    implements android.support.v4.view.ViewPager.OnPageChangeListener, com.google.android.calendar.newevent.NewEventTimeChangedListenerHolder.OnCreateNewEventTimeChangedListener, DataFactory.OnAllEventsDataReadyListener
{

    public static final String TAG = com/google/android/calendar/timely/TimelyDayViewPager.getSimpleName();
    public boolean accessibilityEnabled;
    public DayViewPagerAdapter dayViewPagerAdapter;
    private DelayedUpdateHelper delayedUpdateHelper;
    private boolean internalPageScroll;
    private int lastFocusedPosition;
    public Time timeToday;

    public TimelyDayViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        delayedUpdateHelper = new DelayedUpdateHelper(this);
        dayViewPagerAdapter = new DayViewPagerAdapter(context);
        setAdapter(dayViewPagerAdapter);
    }

    private final void focusNewPositionForA11y(int i)
    {
        if (accessibilityEnabled && getChildAtPosition(getCurrentItem()) != null)
        {
            Object obj = getChildAtPosition(getCurrentItem());
            class .Lambda._cls1
                implements Runnable
            {

                private final View arg$1;

                public final void run()
                {
                    arg$1.requestFocusFromTouch();
                }

            .Lambda._cls1(View view)
            {
                arg$1 = view;
            }
            }

            if (((PagedDayView) (obj)).isTablet)
            {
                obj = ((PagedDayView) (obj)).weekHeaderLabelsView;
            } else
            {
                obj = ((PagedDayView) (obj)).stickyHeaderView;
            }
            lastFocusedPosition = i;
            ((View) (obj)).setFocusableInTouchMode(true);
            ((View) (obj)).clearFocus();
            obj.getClass();
            ((View) (obj)).post(new .Lambda._cls1(((View) (obj))));
        }
    }

    static int getPosition(Time time)
    {
        time.writeFieldsToImpl();
        return android.text.format.Time.getJulianDay(time.impl.toMillis(false), time.gmtoff) - 0x253d8c;
    }

    final PagedDayView getChildAtPosition(int i)
    {
        PagedDayView apageddayview[] = getVisibleChildren();
        int k = apageddayview.length;
        for (int j = 0; j < k; j++)
        {
            PagedDayView pageddayview = apageddayview[j];
            if (pageddayview.position == i)
            {
                return pageddayview;
            }
        }

        return null;
    }

    final PagedDayView[] getVisibleChildren()
    {
        int j = getChildCount();
        PagedDayView apageddayview[] = new PagedDayView[j];
        for (int i = 0; i < j; i++)
        {
            apageddayview[i] = (PagedDayView)getChildAt(i);
        }

        return apageddayview;
    }

    public final void onAllEventsDataReady()
    {
        DelayedUpdateHelper delayedupdatehelper = delayedUpdateHelper;
        class .Lambda._cls0
            implements Runnable
        {

            private final TimelyDayViewPager arg$1;

            public final void run()
            {
                TimelyDayViewPager timelydayviewpager = arg$1;
                PagedDayView apageddayview[] = timelydayviewpager.getVisibleChildren();
                int j = apageddayview.length;
                for (int i = 0; i < j; i++)
                {
                    PagedDayView pageddayview = apageddayview[i];
                    timelydayviewpager.dayViewPagerAdapter.updateView(pageddayview.position, pageddayview);
                }

            }

            .Lambda._cls0()
            {
                arg$1 = TimelyDayViewPager.this;
            }
        }

        .Lambda._cls0 _lcls0 = new .Lambda._cls0();
        CalendarExecutor.MAIN.checkOnThread();
        DelayedUpdateHelper.logFishfoodInfo("Attempt Update: %s", new Object[] {
            delayedupdatehelper.view
        });
        delayedupdatehelper.delayedUpdate = _lcls0;
        delayedupdatehelper.updateIfIdleAndNeeded();
    }

    public final void onCreateNewEventTimeChanged$5152ILG_0()
    {
        PagedDayView apageddayview[] = getVisibleChildren();
        int j = apageddayview.length;
        for (int i = 0; i < j; i++)
        {
            apageddayview[i].gridDayView.updateCreateNewEventView();
        }

    }

    public final void onPageScrollStateChanged(int i)
    {
    }

    public final void onPageScrolled(int i, float f, int j)
    {
        if (!internalPageScroll)
        {
            internalPageScroll = true;
            super.onPageScrolled(i, f, j);
            internalPageScroll = false;
            if ((double)f == 0.0D && lastFocusedPosition != i)
            {
                focusNewPositionForA11y(i);
                return;
            }
        }
    }

    public final void onPageSelected(int i)
    {
        dayViewPagerAdapter.mDataFactory.setTimePassage(0, 0x253d8c + i);
    }

    public final void setActive(boolean flag)
    {
        setEnabled(flag);
        dayViewPagerAdapter.enabled = flag;
        PagedDayView apageddayview[] = getVisibleChildren();
        int j = apageddayview.length;
        for (int i = 0; i < j; i++)
        {
            apageddayview[i].setEnabled(flag);
        }

    }

    public final void setCurrentItem(int i, boolean flag)
    {
        super.setCurrentItem(i, flag);
        focusNewPositionForA11y(i);
    }

}
