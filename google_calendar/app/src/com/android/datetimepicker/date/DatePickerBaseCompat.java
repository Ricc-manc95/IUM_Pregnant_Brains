// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import java.util.Calendar;

class DatePickerBaseCompat
{

    public int firstDayOfWeek;
    public Calendar minDate;
    public boolean rtlEnabled;

    DatePickerBaseCompat()
    {
        firstDayOfWeek = -1;
        rtlEnabled = false;
    }

    public void setFirstDayOfWeek(int i)
    {
        firstDayOfWeek = i;
    }

    public void setMinDate(Calendar calendar)
    {
        minDate = calendar;
    }

    public void setRtlEnabled(boolean flag)
    {
        rtlEnabled = flag;
    }
}
