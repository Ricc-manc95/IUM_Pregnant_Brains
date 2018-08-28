// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.common.collect.ImmutableList;

public abstract class AdapterWeek
{

    public AdapterWeek()
    {
    }

    public abstract long getCacheGeneration();

    public abstract ImmutableList getDays();

    public abstract int getJulianWeek();
}
