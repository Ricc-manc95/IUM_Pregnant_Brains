// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.accessibility.AccessibilityNodeProvider;
import com.google.android.apps.calendar.timeline.alternate.minimonth.api.MiniMonthController;
import com.google.android.apps.calendar.timeline.alternate.util.AutoValue_YearMonth;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.util.YearMonth;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.utils.viewpager.NinjaViewPager;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth:
//            MiniMonthViewPager, MiniMonthViewPagerAdapter, MiniMonthView, MiniMonthGeometry

public final class MiniMonthControllerImpl
    implements MiniMonthController
{

    private final MiniMonthViewPagerAdapter adapter;
    private final DimensConverter dimensConverter;
    private final ObservableReference isA11yEnabled;
    public final ObservableReference isPortrait;
    public YearMonth lastMonth;
    public com.google.android.apps.calendar.timeline.alternate.minimonth.api.MiniMonthController.OnMonthChangedListener onMonthChangedListener;
    public final ObservableReference screenType;
    public final ObservableReference shouldShowWeekNumbers;
    private final TimeUtils timeUtils;
    private final MiniMonthViewPager viewPager;

    public MiniMonthControllerImpl(final MiniMonthViewPager viewPager, MiniMonthViewPagerAdapter minimonthviewpageradapter, ObservableReference observablereference, ObservableReference observablereference1, ObservableReference observablereference2, ObservableReference observablereference3, DimensConverter dimensconverter, 
            TimeUtils timeutils)
    {
        this.viewPager = viewPager;
        adapter = minimonthviewpageradapter;
        screenType = observablereference;
        isPortrait = observablereference1;
        timeUtils = timeutils;
        isA11yEnabled = observablereference2;
        shouldShowWeekNumbers = observablereference3;
        dimensConverter = dimensconverter;
        viewPager.setAdapter(minimonthviewpageradapter);
        viewPager.addOnAttachStateChangeListener(new _cls2());
        viewPager.addOnPageChangeListener(new com.google.android.calendar.utils.viewpager.NinjaViewPager._cls1(viewPager, new _cls1()));
        updateLayoutParams();
    }

    public final int getCurrentJulianDay()
    {
        return adapter.selectedJulianDay;
    }

    public final int getCurrentMonthHeight()
    {
        boolean flag;
        if ((ScreenType)screenType.get() == ScreenType.PHONE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return viewPager.getHeight();
        }
        Object obj = adapter;
        int i = viewPager.getCurrentItem();
        obj = ((MiniMonthViewPagerAdapter) (obj)).currentViews;
        YearMonth yearmonth = MiniMonthViewPagerAdapter.MIN_MONTH;
        int j = yearmonth.getYear();
        i += yearmonth.getMonth() + j * 12;
        obj = (View)((HashMap) (obj)).get(new AutoValue_YearMonth(i / 12, i % 12));
        if (obj == null)
        {
            return 0;
        } else
        {
            return ((View) (obj)).getHeight();
        }
    }

    public final ViewPager getViewPager()
    {
        return viewPager;
    }

    public final void onAlternateCalendarChanged()
    {
        MiniMonthView minimonthview;
        for (Iterator iterator = adapter.currentViews.values().iterator(); iterator.hasNext(); minimonthview.requestLayout())
        {
            minimonthview = (MiniMonthView)iterator.next();
            minimonthview.geometry.computeRowDimens(minimonthview.getContext());
        }

    }

    public final void pointTo(int i, boolean flag)
    {
        Object obj = adapter;
        TimeUtils timeutils = timeUtils;
        long l1 = TimeUtils.utcJulianDayToMs(i);
        long l2 = ((TimeZone)timeutils.timeZone.get()).getOffset(l1);
        ((MiniMonthViewPagerAdapter) (obj)).timeUtils.initCalendar(((MiniMonthViewPagerAdapter) (obj)).calendar);
        ((MiniMonthViewPagerAdapter) (obj)).calendar.setTimeInMillis(l1 - l2);
        int j = ((MiniMonthViewPagerAdapter) (obj)).calendar.get(1);
        int k = ((MiniMonthViewPagerAdapter) (obj)).calendar.get(2);
        obj = MiniMonthViewPagerAdapter.MIN_MONTH;
        int l = ((YearMonth) (obj)).getYear();
        j = (j * 12 + k) - (((YearMonth) (obj)).getMonth() + l * 12);
        obj = viewPager;
        obj.isInStealthMode = true;
        ((ViewPager) (obj)).setCurrentItem(j, flag);
        obj.isInStealthMode = false;
        obj = adapter;
        obj.selectedJulianDay = i;
        ((MiniMonthViewPagerAdapter) (obj)).onUpdate(false);
        obj = MiniMonthViewPagerAdapter.MIN_MONTH;
        i = ((YearMonth) (obj)).getYear();
        i = j + (((YearMonth) (obj)).getMonth() + i * 12);
        lastMonth = new AutoValue_YearMonth(i / 12, i % 12);
    }

    public final void requestFocus()
    {
        boolean flag = true;
        if (((Boolean)isA11yEnabled.get()).booleanValue())
        {
            Object obj = adapter;
            int i = viewPager.getCurrentItem();
            obj = ((MiniMonthViewPagerAdapter) (obj)).currentViews;
            YearMonth yearmonth = MiniMonthViewPagerAdapter.MIN_MONTH;
            int k = yearmonth.getYear();
            i += yearmonth.getMonth() + k * 12;
            obj = (MiniMonthView)((HashMap) (obj)).get(new AutoValue_YearMonth(i / 12, i % 12));
            if (obj != null)
            {
                ((MiniMonthView) (obj)).sendAccessibilityEvent(2048);
                AccessibilityNodeProvider accessibilitynodeprovider = ((MiniMonthView) (obj)).getAccessibilityNodeProvider();
                int j = ((MiniMonthView) (obj)).todayJulianDay;
                if (((MiniMonthView) (obj)).firstJulianDay <= j && j < ((MiniMonthView) (obj)).firstJulianDay + ((MiniMonthView) (obj)).totalDays)
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                if (j != 0)
                {
                    j = ((MiniMonthView) (obj)).todayJulianDay;
                } else
                {
                    j = ((MiniMonthView) (obj)).selectedJulianDay;
                    if (((MiniMonthView) (obj)).firstJulianDay <= j && j < ((MiniMonthView) (obj)).firstJulianDay + ((MiniMonthView) (obj)).totalDays)
                    {
                        j = ((flag) ? 1 : 0);
                    } else
                    {
                        j = 0;
                    }
                    if (j != 0)
                    {
                        j = ((MiniMonthView) (obj)).selectedJulianDay;
                    } else
                    {
                        j = ((MiniMonthView) (obj)).firstJulianDay;
                    }
                }
                accessibilitynodeprovider.performAction(j, 64, null);
            }
        }
    }

    public final void setOnDayClickedListener(com.google.android.apps.calendar.timeline.alternate.minimonth.api.MiniMonthController.OnDayClickedListener ondayclickedlistener)
    {
        Object obj = adapter;
        obj.onDayClickedListener = ondayclickedlistener;
        for (obj = ((MiniMonthViewPagerAdapter) (obj)).currentViews.values().iterator(); ((Iterator) (obj)).hasNext();)
        {
            ((MiniMonthView)((Iterator) (obj)).next()).onDayClickedListener = ondayclickedlistener;
        }

    }

    public final void setOnMonthChangedListener(com.google.android.apps.calendar.timeline.alternate.minimonth.api.MiniMonthController.OnMonthChangedListener onmonthchangedlistener)
    {
        onMonthChangedListener = onmonthchangedlistener;
    }

    final void updateLayoutParams()
    {
        int i;
        if ((ScreenType)screenType.get() == ScreenType.PHONE)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0 && !((Boolean)isPortrait.get()).booleanValue())
        {
            Object obj = dimensConverter;
            float f;
            android.view.ViewGroup.LayoutParams layoutparams;
            if (screenType.get() == ScreenType.LARGE_TABLET)
            {
                f = 396F;
            } else
            {
                f = 368F;
            }
            i = ((DimensConverter) (obj)).getPixelSize(f);
            layoutparams = viewPager.getLayoutParams();
            obj = layoutparams;
            if (layoutparams == null)
            {
                obj = new android.view.ViewGroup.LayoutParams(0, 0);
            }
            obj.width = i;
            obj.height = -1;
            viewPager.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
            if (!((Boolean)shouldShowWeekNumbers.get()).booleanValue())
            {
                i = dimensConverter.getPixelOffset(66F);
                viewPager.setPaddingRelative(i, 0, 0, 0);
            }
        }
    }

    private class _cls2
        implements android.view.View.OnAttachStateChangeListener
    {

        private Subscription attachSubscriptions;
        public final MiniMonthControllerImpl this$0;

        public final void onViewAttachedToWindow(View view)
        {
            boolean flag;
            if (attachSubscriptions == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            } else
            {
                class .Lambda._cls0
                    implements Consumer
                {

                    private final _cls2 arg$1;

                    public final void accept(Object obj)
                    {
                        arg$1._fld0.updateLayoutParams();
                    }

                .Lambda._cls0()
                {
                    arg$1 = _cls2.this;
                }
                }

                view = new .Lambda._cls0();
                attachSubscriptions = new com.google.android.apps.calendar.util.concurrent.Subscription..Lambda._cls0(Arrays.asList(new Subscription[] {
                    screenType.subscribe(view, new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN), false), isPortrait.subscribe(view, new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN), false), shouldShowWeekNumbers.subscribe(view, new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN), false)
                }));
                return;
            }
        }

        public final void onViewDetachedFromWindow(View view)
        {
            attachSubscriptions.cancel(false);
            attachSubscriptions = null;
        }

        _cls2()
        {
            this$0 = MiniMonthControllerImpl.this;
            super();
        }
    }


    private class _cls1 extends android.support.v4.view.ViewPager.SimpleOnPageChangeListener
    {

        private final MiniMonthControllerImpl this$0;
        private final MiniMonthViewPager val$viewPager;

        public final void onPageSelected(int i)
        {
            i = viewPager.getCurrentItem();
            Object obj = MiniMonthViewPagerAdapter.MIN_MONTH;
            int j = ((YearMonth) (obj)).getYear();
            i += ((YearMonth) (obj)).getMonth() + j * 12;
            obj = new AutoValue_YearMonth(i / 12, i % 12);
            MiniMonthControllerImpl minimonthcontrollerimpl = MiniMonthControllerImpl.this;
            if (minimonthcontrollerimpl.onMonthChangedListener != null && !obj.equals(minimonthcontrollerimpl.lastMonth))
            {
                minimonthcontrollerimpl.onMonthChangedListener.onMonthChanged(((YearMonth) (obj)).getYear(), ((YearMonth) (obj)).getMonth());
                minimonthcontrollerimpl.lastMonth = ((YearMonth) (obj));
                minimonthcontrollerimpl.requestFocus();
            }
        }

        _cls1()
        {
            this$0 = MiniMonthControllerImpl.this;
            viewPager = minimonthviewpager;
            super();
        }
    }

}
