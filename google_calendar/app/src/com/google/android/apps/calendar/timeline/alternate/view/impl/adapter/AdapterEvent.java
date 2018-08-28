// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry.PositionOnGrid;

public abstract class AdapterEvent
{

    public AdapterEvent()
    {
    }

    public abstract long getDisplayEndFp16();

    public abstract long getDisplayStartFp16();

    public abstract long getEndTimeMs();

    public abstract Integer getGridAllDaySlot();

    public abstract PositionOnGrid getGridTimedPosition();

    public abstract boolean getIsTimedEvent();

    public abstract Object getItem();

    public abstract int getItemVersion();

    public abstract int getJulianDay();

    public abstract int getMonthSlot();

    public abstract int getPosition();

    public abstract long getStartTimeMs();

    public abstract Builder toBuilder();
}
