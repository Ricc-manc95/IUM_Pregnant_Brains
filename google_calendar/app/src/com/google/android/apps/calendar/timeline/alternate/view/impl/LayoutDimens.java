// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;

public final class LayoutDimens
{

    public final DimensConverter converter;
    private final ObservableReference eventsPerMonthViewDay;
    public final ScheduleProvider scheduleProvider;
    private final ObservableReference screenType;

    LayoutDimens(DimensConverter dimensconverter, ObservableReference observablereference, ObservableReference observablereference1, ScheduleProvider scheduleprovider)
    {
        converter = dimensconverter;
        screenType = observablereference;
        eventsPerMonthViewDay = observablereference1;
        scheduleProvider = scheduleprovider;
    }

    public final int monthDayRowHeight(int i)
    {
        int j = ((Integer)eventsPerMonthViewDay.get()).intValue();
        DimensConverter dimensconverter = converter;
        float f;
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
            f = 22F;
        } else
        {
            f = 14F;
        }
        return (i - (dimensconverter.getPixelSize(f) + converter.getPixelOffset(2.0F)) * j) + converter.getPixelOffset(2.0F);
    }

    public final float monthWeekNumberColumnWidth()
    {
        DimensConverter dimensconverter = converter;
        float f;
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
            f = 40F;
        } else
        {
            f = 24F;
        }
        return dimensconverter.dpToPx(f);
    }

    public final int scheduleChipEndMargin()
    {
        DimensConverter dimensconverter = converter;
        float f;
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
            f = 52F;
        } else
        {
            f = 16F;
        }
        return dimensconverter.getPixelSize(f);
    }
}
