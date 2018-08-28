// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.common.collect.ImmutableSet;

public abstract class CalendarDay
{

    public CalendarDay()
    {
    }

    public abstract int getCacheGeneration();

    public abstract ImmutableSet getItems();

    public abstract int getJulianDate();

    public abstract Builder toBuilder();
}
