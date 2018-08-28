// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.common.collect.ImmutableList;

public abstract class CalendarWeek
{

    public CalendarWeek()
    {
    }

    public abstract long getCacheGeneration();

    public abstract ImmutableList getDays();

    public abstract int getJulianWeek();

    public abstract Builder toBuilder();
}
