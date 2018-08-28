// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.os.Parcelable;

public interface HabitContract
    extends Parcelable
{

    public abstract int getDurationMinutes();

    public abstract int getInterval();

    public abstract int getNumInstancesPerInterval();

    public abstract long getUntilMillisUtc();

    public abstract boolean isAfternoonPreferable();

    public abstract boolean isAnyDayTimeAcceptable();

    public abstract boolean isEveningPreferable();

    public abstract boolean isMorningPreferable();
}
