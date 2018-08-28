// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry.PositionOnGrid;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterEvent

public abstract class id
{

    public abstract AdapterEvent build();

    public abstract long getDisplayEndFp16();

    public abstract long getDisplayStartFp16();

    public abstract PositionOnGrid getGridTimedPosition();

    public abstract boolean getIsTimedEvent();

    public abstract Object getItem();

    public abstract int getJulianDay();

    public abstract int getPosition();

    public abstract id setDisplayEndFp16(long l);

    public abstract id setDisplayStartFp16(long l);

    public abstract id setEndTimeMs(long l);

    public abstract id setGridAllDaySlot(Integer integer);

    public abstract id setGridTimedPosition(PositionOnGrid positionongrid);

    public abstract id setIsTimedEvent(boolean flag);

    public abstract id setItem(Object obj);

    public abstract id setItemVersion(int i);

    public abstract id setJulianDay(int i);

    public abstract id setMonthSlot(int i);

    public abstract id setPosition(int i);

    public abstract id setStartTimeMs(long l);

    public id()
    {
    }
}
