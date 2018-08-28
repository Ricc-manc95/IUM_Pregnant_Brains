// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth.api;

import android.support.v4.view.ViewPager;

public interface MiniMonthController
{

    public abstract int getCurrentJulianDay();

    public abstract int getCurrentMonthHeight();

    public abstract ViewPager getViewPager();

    public abstract void onAlternateCalendarChanged();

    public abstract void pointTo(int i, boolean flag);

    public abstract void requestFocus();

    public abstract void setOnDayClickedListener(OnDayClickedListener ondayclickedlistener);

    public abstract void setOnMonthChangedListener(OnMonthChangedListener onmonthchangedlistener);
}
