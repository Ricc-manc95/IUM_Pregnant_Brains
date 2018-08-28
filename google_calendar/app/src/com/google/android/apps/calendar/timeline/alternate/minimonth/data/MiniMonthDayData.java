// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth.data;

import com.google.common.collect.ImmutableList;

public abstract class MiniMonthDayData
{

    public MiniMonthDayData()
    {
    }

    public abstract String getAlternateName();

    public abstract ImmutableList getColors();

    public abstract String getName();

    public abstract boolean hasOverflow();
}
