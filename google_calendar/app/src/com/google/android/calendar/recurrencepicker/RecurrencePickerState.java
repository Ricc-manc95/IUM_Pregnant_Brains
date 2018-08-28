// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.recurrencepicker;

import android.os.Parcelable;
import com.google.common.collect.ImmutableSet;
import java.util.TimeZone;

public abstract class RecurrencePickerState
    implements Parcelable
{

    public RecurrencePickerState()
    {
    }

    public abstract ImmutableSet getByDay();

    public abstract ImmutableSet getByMonthDay();

    public abstract Integer getCount();

    public abstract End getEnd();

    public abstract Integer getFirstDayOfWeek();

    public abstract Frequency getFrequency();

    public abstract Boolean getHasLastOption();

    public abstract Boolean getHasNthOption();

    public abstract Integer getInterval();

    public abstract MonthFrequency getMonthFrequency();

    public abstract Long getStartTimeInMillis();

    public abstract Integer getStartWeekday();

    public abstract TimeZone getTimeZone();

    public abstract Long getUntilDateTimeMillis();

    public abstract Builder toBuilder();
}
