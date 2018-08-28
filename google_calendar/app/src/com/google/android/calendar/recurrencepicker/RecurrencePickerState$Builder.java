// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.recurrencepicker;

import com.google.common.collect.ImmutableSet;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.recurrencepicker:
//            RecurrencePickerState

public abstract class quency
{

    public abstract RecurrencePickerState build();

    public abstract quency setByDay(ImmutableSet immutableset);

    public abstract quency setByMonthDay(ImmutableSet immutableset);

    public abstract quency setCount(Integer integer);

    public abstract quency setEnd(quency quency);

    public abstract quency setFirstDayOfWeek(Integer integer);

    public abstract y setFrequency(y y);

    public abstract y setHasLastOption(Boolean boolean1);

    public abstract y setHasNthOption(Boolean boolean1);

    public abstract y setInterval(Integer integer);

    public abstract quency setMonthFrequency(quency quency);

    public abstract quency setStartTimeInMillis(Long long1);

    public abstract quency setStartWeekday(Integer integer);

    public abstract quency setTimeZone(TimeZone timezone);

    public abstract quency setUntilDateTimeMillis(Long long1);

    public quency()
    {
    }
}
