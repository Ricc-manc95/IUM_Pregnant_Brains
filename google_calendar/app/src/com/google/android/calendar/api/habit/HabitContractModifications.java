// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitContract

public interface HabitContractModifications
    extends Parcelable, HabitContract
{

    public abstract void applyModifications(HabitContractModifications habitcontractmodifications);

    public abstract boolean isAnyDayTimeAcceptable();

    public abstract boolean isDailyPatternModified();

    public abstract boolean isDurationMinutesModified();

    public abstract boolean isIntervalModified();

    public abstract boolean isModified();

    public abstract boolean isNumInstancesPerIntervalModified();

    public abstract boolean isUntilMillisUtcModified();

    public abstract HabitContractModifications setAfternoonPreferable(boolean flag);

    public abstract HabitContractModifications setAnyDayTimeAcceptable();

    public abstract HabitContractModifications setDurationMinutes(int i);

    public abstract HabitContractModifications setEveningPreferable(boolean flag);

    public abstract HabitContractModifications setInterval(int i);

    public abstract HabitContractModifications setMorningPreferable(boolean flag);

    public abstract HabitContractModifications setNumInstancesPerInterval(int i);

    public abstract HabitContractModifications setUntilMillisUtc(long l);
}
