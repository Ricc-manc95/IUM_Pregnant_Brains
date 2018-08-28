// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.minimonth;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.calendar.timebox.TimeBoxUtil;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment;
import com.google.android.apps.calendar.timeline.alternate.minimonth.api.MiniMonthController;
import com.google.android.calendar.CalendarController;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.MiniMonthInteractionController;
import com.google.android.calendar.Utils;
import com.google.android.calendar.alternatecalendar.AlternateCalendarUtils;
import com.google.android.calendar.time.Time;
import java.util.Calendar;
import java.util.TimeZone;

public final class MiniMonthInteractionControllerImpl
    implements MiniMonthInteractionController
{

    public final CalendarController calendarController;
    public final MiniMonthController controller;
    private final View dragUpView;
    public final CalendarFragment fragment;
    public final View fragmentView;
    private final boolean isTablet;
    private boolean isVisible;
    public final ViewPager miniMonth;
    private final ViewGroup miniMonthContainer;

    public MiniMonthInteractionControllerImpl(CalendarFragment calendarfragment, MiniMonthController minimonthcontroller, ViewGroup viewgroup, CalendarController calendarcontroller, ActionBar actionbar)
    {
        fragment = calendarfragment;
        controller = minimonthcontroller;
        calendarController = calendarcontroller;
        miniMonth = minimonthcontroller.getViewPager();
        miniMonthContainer = (ViewGroup)viewgroup.findViewById(0x7f100241);
        dragUpView = viewgroup.findViewById(0x7f100240);
        fragmentView = viewgroup.findViewById(0x7f10011a);
        isTablet = viewgroup.getContext().getResources().getBoolean(0x7f0c0016);
        miniMonthContainer.removeAllViews();
        miniMonthContainer.addView(miniMonth);
        class .Lambda._cls0
            implements com.google.android.apps.calendar.timeline.alternate.minimonth.api.MiniMonthController.OnDayClickedListener
        {

            private final MiniMonthInteractionControllerImpl arg$1;

            public final void onDayClicked(int i)
            {
                MiniMonthInteractionControllerImpl minimonthinteractioncontrollerimpl = arg$1;
                minimonthinteractioncontrollerimpl.controller.pointTo(i, false);
                minimonthinteractioncontrollerimpl.fragment.goToDay(i);
                minimonthinteractioncontrollerimpl.calendarController.executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(new com.google.android.calendar.CalendarController.Command(4096L));
                AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
                if (analyticslogger == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                } else
                {
                    ((AnalyticsLogger)analyticslogger).trackEvent(minimonthinteractioncontrollerimpl.fragmentView.getContext(), "menu_item", "jump_to_date");
                    return;
                }
            }

            .Lambda._cls0()
            {
                arg$1 = MiniMonthInteractionControllerImpl.this;
            }
        }

        minimonthcontroller.setOnDayClickedListener(new .Lambda._cls0());
        class .Lambda._cls1
            implements com.google.android.apps.calendar.timeline.alternate.minimonth.api.MiniMonthController.OnMonthChangedListener
        {

            private final MiniMonthInteractionControllerImpl arg$1;

            public final void onMonthChanged(int i, int j)
            {
                MiniMonthInteractionControllerImpl minimonthinteractioncontrollerimpl = arg$1;
                TimeZone timezone = Utils.getTimeZone(minimonthinteractioncontrollerimpl.miniMonth.getContext());
                Calendar calendar = Calendar.getInstance(timezone);
                calendar.set(i, j, 1);
                i = TimeBoxUtil.msToJulianDay(timezone, calendar.getTimeInMillis());
                minimonthinteractioncontrollerimpl.updateActionBar(i);
                minimonthinteractioncontrollerimpl.controller.pointTo(i, false);
                minimonthinteractioncontrollerimpl.fragment.goToDay(i);
            }

            .Lambda._cls1()
            {
                arg$1 = MiniMonthInteractionControllerImpl.this;
            }
        }

        minimonthcontroller.setOnMonthChangedListener(new .Lambda._cls1());
        boolean flag;
        if (!isTablet && Utils.isPortrait(miniMonth.getContext()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            ViewCompat.setZ(miniMonthContainer, miniMonthContainer.getResources().getDimension(0x7f0e004d));
            if (actionbar != null)
            {
                actionbar.setElevation(0.0F);
            }
        }
    }

    public final int getCurrentMonthHeight()
    {
        return controller.getCurrentMonthHeight();
    }

    public final ViewPager getDatePicker()
    {
        return miniMonth;
    }

    public final View getDatePickerContainer()
    {
        return miniMonthContainer;
    }

    public final View getDragUpView()
    {
        return dragUpView;
    }

    public final boolean hasMiniMonth()
    {
        while (!isTablet && !Utils.isPortrait(miniMonth.getContext()) || fragment.getViewType() == com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType.MONTH) 
        {
            return false;
        }
        return true;
    }

    public final boolean isFragmentAttached()
    {
        return true;
    }

    public final boolean isMiniMonthDraggable()
    {
        while (!isMiniMonthToggleable() || isTablet && !Utils.isPortrait(miniMonth.getContext())) 
        {
            return false;
        }
        return true;
    }

    public final boolean isMiniMonthToggleable()
    {
        while (!hasMiniMonth() || isTablet && !Utils.isPortrait(miniMonth.getContext()) && (fragment.getViewType() == com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType.AGENDA || fragment.getViewType() == com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType.DAY)) 
        {
            return false;
        }
        return true;
    }

    public final boolean isMiniMonthVisible()
    {
        return isVisible;
    }

    public final void onMiniMonthVisibilityChanged(boolean flag)
    {
        isVisible = flag;
        if (flag)
        {
            controller.requestFocus();
            updateActionBar(controller.getCurrentJulianDay());
        }
    }

    public final void onMiniMonthVisibilityChanging(boolean flag)
    {
        ViewPager viewpager = miniMonth;
        int i;
        if (flag)
        {
            i = 0;
        } else
        {
            i = 4;
        }
        viewpager.setVisibility(i);
    }

    public final void pointTo(int i)
    {
        if (hasMiniMonth())
        {
            controller.pointTo(i, isVisible);
            if (isVisible)
            {
                updateActionBar(i);
                return;
            }
        }
    }

    public final void refreshState()
    {
        boolean flag1 = true;
        if (hasMiniMonth()) goto _L2; else goto _L1
_L1:
        Object obj;
        boolean flag;
        int i;
        if (!isTablet && Utils.isPortrait(miniMonth.getContext()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L3; else goto _L2
_L2:
        flag = true;
_L12:
        obj = miniMonthContainer;
        if (hasMiniMonth()) goto _L5; else goto _L4
_L4:
        if (!isTablet && Utils.isPortrait(miniMonth.getContext()))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L6; else goto _L5
_L5:
        i = 1;
        break MISSING_BLOCK_LABEL_78;
_L3:
        flag = false;
        continue; /* Loop/switch isn't completed */
_L6:
        i = 0;
        if (i != 0)
        {
            i = 0;
        } else
        {
            i = 4;
        }
        ((ViewGroup) (obj)).setVisibility(i);
        if (!flag)
        {
            return;
        }
        if (hasMiniMonth() && !isMiniMonthToggleable())
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            miniMonthContainer.setTranslationX(0.0F);
            miniMonthContainer.setTranslationY(0.0F);
            miniMonth.setVisibility(0);
            miniMonth.setTranslationX(0.0F);
            miniMonth.setTranslationY(0.0F);
            return;
        }
        obj = miniMonth.getResources();
        if (!Utils.isPortrait(miniMonth.getContext())) goto _L8; else goto _L7
_L7:
        miniMonthContainer.setTranslationX(0.0F);
        miniMonthContainer.setTranslationY(-((Resources) (obj)).getDimension(0x7f0e00c7));
_L10:
        miniMonth.setVisibility(4);
        return;
_L8:
        if (isTablet)
        {
            miniMonthContainer.setTranslationX(-((Resources) (obj)).getDimension(0x7f0e00d5) - ((Resources) (obj)).getDimension(0x7f0e00cb));
            miniMonthContainer.setTranslationY(0.0F);
        }
        if (true) goto _L10; else goto _L9
_L9:
        if (true) goto _L12; else goto _L11
_L11:
    }

    public final void setViewTranslationX(float f)
    {
        fragmentView.setTranslationX(f);
    }

    public final void setViewTranslationY(float f)
    {
        fragmentView.setTranslationY(f);
    }

    final void updateActionBar(int i)
    {
        Object obj = Utils.getTimeZone(miniMonth.getContext());
        Time time = new Time(((TimeZone) (obj)).getID());
        time.setJulianDaySafe(i);
        int j = DateTimeFormatHelper.getToolbarFormatFlags(miniMonth.getContext().getResources().getBoolean(0x7f0c0016), Utils.isCurrentYear(miniMonth.getContext(), i));
        calendarController.updateVisibleRange(this, time, time, null, false, j);
        if (AlternateCalendarUtils.isAlternateCalendarEnabled(miniMonth.getContext()))
        {
            Object obj1 = Calendar.getInstance(((TimeZone) (obj)));
            ((Calendar) (obj1)).setTimeInMillis(TimeBoxUtil.julianDayToMs(((TimeZone) (obj)), i));
            ((Calendar) (obj1)).set(5, 1);
            Time time1 = new Time(((TimeZone) (obj)).getID());
            long l = ((Calendar) (obj1)).getTimeInMillis();
            time1.impl.timezone = time1.timezone;
            time1.impl.set(l);
            time1.impl.toMillis(true);
            time1.copyFieldsFromImpl();
            ((Calendar) (obj1)).set(5, ((Calendar) (obj1)).getActualMaximum(5));
            obj = new Time(((TimeZone) (obj)).getID());
            l = ((Calendar) (obj1)).getTimeInMillis();
            ((Time) (obj)).impl.timezone = ((Time) (obj)).timezone;
            ((Time) (obj)).impl.set(l);
            ((Time) (obj)).impl.toMillis(true);
            ((Time) (obj)).copyFieldsFromImpl();
            obj1 = calendarController;
            com.google.android.calendar.CalendarController.Command command = new com.google.android.calendar.CalendarController.Command(8192L);
            command.startTime = time1;
            command.endTime = ((Time) (obj));
            ((CalendarController) (obj1)).executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(command);
        }
    }
}
