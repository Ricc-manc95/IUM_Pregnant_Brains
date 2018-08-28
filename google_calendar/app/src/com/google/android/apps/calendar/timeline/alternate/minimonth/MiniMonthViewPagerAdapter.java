// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.calendar.timeline.alternate.util.AutoValue_YearMonth;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.util.YearMonth;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth:
//            MiniMonthDataAdapter, MiniMonthViewBinder, MiniMonthView

final class MiniMonthViewPagerAdapter extends PagerAdapter
{

    private static final YearMonth MAX_MONTH;
    public static final YearMonth MIN_MONTH;
    private static final int NUM_MONTHS;
    public final Calendar calendar = Calendar.getInstance();
    public final HashMap currentViews = new HashMap();
    public com.google.android.apps.calendar.timeline.alternate.minimonth.api.MiniMonthController.OnDayClickedListener onDayClickedListener;
    public int selectedJulianDay;
    public final TimeUtils timeUtils;
    private final MiniMonthViewBinder viewBinder;

    public MiniMonthViewPagerAdapter(TimeUtils timeutils, MiniMonthViewBinder minimonthviewbinder, MiniMonthDataAdapter minimonthdataadapter)
    {
        timeUtils = timeutils;
        viewBinder = minimonthviewbinder;
        class .Lambda._cls0
            implements Consumer
        {

            private final MiniMonthViewPagerAdapter arg$1;

            public final void accept(Object obj)
            {
                arg$1.onUpdate(true);
            }

            .Lambda._cls0()
            {
                arg$1 = MiniMonthViewPagerAdapter.this;
            }
        }

        minimonthdataadapter.invalidationObservable.subscribe(new .Lambda._cls0(), CalendarExecutor.MAIN, false);
    }

    public final void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        viewgroup.removeView((View)currentViews.remove(obj));
    }

    public final int getCount()
    {
        return NUM_MONTHS;
    }

    public final Object instantiateItem(ViewGroup viewgroup, int i)
    {
        Object obj = MIN_MONTH;
        int j = ((YearMonth) (obj)).getYear();
        i = ((YearMonth) (obj)).getMonth() + j * 12 + i;
        obj = new AutoValue_YearMonth(i / 12, i % 12);
        MiniMonthView minimonthview = (MiniMonthView)viewBinder.miniMonthViewProvider.get();
        minimonthview.onDayClickedListener = onDayClickedListener;
        viewBinder.updateView(minimonthview, ((YearMonth) (obj)), selectedJulianDay, true);
        viewgroup.addView(minimonthview);
        currentViews.put(obj, minimonthview);
        return obj;
    }

    public final boolean isViewFromObject(View view, Object obj)
    {
        return view == currentViews.get(obj);
    }

    final void onUpdate(boolean flag)
    {
        java.util.Map.Entry entry;
        for (Iterator iterator = currentViews.entrySet().iterator(); iterator.hasNext(); viewBinder.updateView((MiniMonthView)entry.getValue(), (YearMonth)entry.getKey(), selectedJulianDay, flag))
        {
            entry = (java.util.Map.Entry)iterator.next();
        }

    }

    static 
    {
        MIN_MONTH = new AutoValue_YearMonth(1970, 0);
        Object obj = new AutoValue_YearMonth(2036, 11);
        MAX_MONTH = ((YearMonth) (obj));
        int i = ((YearMonth) (obj)).getYear();
        int j = ((YearMonth) (obj)).getMonth();
        obj = MIN_MONTH;
        int k = ((YearMonth) (obj)).getYear();
        NUM_MONTHS = ((i * 12 + j) - (((YearMonth) (obj)).getMonth() + k * 12)) + 1;
    }
}
