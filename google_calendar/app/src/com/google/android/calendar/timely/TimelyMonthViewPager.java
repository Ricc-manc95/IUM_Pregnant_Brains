// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.timely.interaction.helper.DelayedUpdateHelper;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.viewpager.ListenableViewPager;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyMonthPagerAdapter

public class TimelyMonthViewPager extends ListenableViewPager
    implements DataFactory.OnAllEventsDataReadyListener
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/TimelyMonthViewPager);
    public TimelyMonthPagerAdapter adapter;
    public boolean animatedMonthHeightChanges;
    public final com.android.datetimepicker.date.MonthAdapter.CalendarDay currentDay = new com.android.datetimepicker.date.MonthAdapter.CalendarDay(0L);
    private final int defaultWidth;
    private DelayedUpdateHelper delayedUpdateHelper;
    private final boolean isTabletConfig;
    private final int leftPaddingWithoutWeekNumbers;
    private final int orientation;

    public TimelyMonthViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        delayedUpdateHelper = new DelayedUpdateHelper(this);
        attributeset = context.getResources();
        defaultWidth = attributeset.getDimensionPixelSize(0x7f0e00d5);
        leftPaddingWithoutWeekNumbers = attributeset.getDimensionPixelOffset(0x7f0e00cb);
        orientation = attributeset.getConfiguration().orientation;
        isTabletConfig = context.getResources().getBoolean(0x7f0c0016);
    }

    public final void onAllEventsDataReady()
    {
        DelayedUpdateHelper delayedupdatehelper = delayedUpdateHelper;
        Object obj = adapter;
        obj.getClass();
        class .Lambda._cls0
            implements Runnable
        {

            private final TimelyMonthPagerAdapter arg$1;

            public final void run()
            {
                arg$1.notifyDataSetChanged();
            }

            .Lambda._cls0(TimelyMonthPagerAdapter timelymonthpageradapter)
            {
                arg$1 = timelymonthpageradapter;
            }
        }

        obj = new .Lambda._cls0(((TimelyMonthPagerAdapter) (obj)));
        CalendarExecutor.MAIN.checkOnThread();
        DelayedUpdateHelper.logFishfoodInfo("Attempt Update: %s", new Object[] {
            delayedupdatehelper.view
        });
        delayedupdatehelper.delayedUpdate = ((Runnable) (obj));
        delayedupdatehelper.updateIfIdleAndNeeded();
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        updateWeekNumber(CalendarProperties.getBooleanProperty(7).booleanValue());
    }

    protected void onMeasure(int i, int j)
    {
        if (isTabletConfig && orientation == 2)
        {
            if (!CalendarProperties.getBooleanProperty(7).booleanValue())
            {
                i = leftPaddingWithoutWeekNumbers;
            } else
            {
                i = 0;
            }
            super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(defaultWidth - i, 0x40000000), j);
            return;
        } else
        {
            super.onMeasure(i, j);
            return;
        }
    }

    public final void updateWeekNumber(boolean flag)
    {
        if (isTabletConfig && orientation == 2)
        {
            View view = (View)getParent();
            int i;
            if (!flag)
            {
                i = leftPaddingWithoutWeekNumbers;
            } else
            {
                i = 0;
            }
            view.setPaddingRelative(i, 0, 0, 0);
        }
        if (adapter != null)
        {
            adapter.updateVisibleMonths();
        }
        requestLayout();
    }

}
