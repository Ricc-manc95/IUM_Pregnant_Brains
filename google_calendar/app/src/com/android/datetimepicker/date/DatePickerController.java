// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import java.util.Calendar;

public interface DatePickerController
{

    public abstract int getFirstDayOfWeek();

    public abstract Calendar getMaxDate();

    public abstract int getMaxYear();

    public abstract Calendar getMinDate();

    public abstract int getMinYear();

    public abstract MonthAdapter.CalendarDay getSelectedDay();

    public abstract void onDayOfMonthSelected(int i, int j, int k);

    public abstract void onYearSelected(int i);

    public abstract void registerOnDateChangedListener(DatePickerDialog.OnDateChangedListener ondatechangedlistener);

    public abstract void tryVibrate();
}
