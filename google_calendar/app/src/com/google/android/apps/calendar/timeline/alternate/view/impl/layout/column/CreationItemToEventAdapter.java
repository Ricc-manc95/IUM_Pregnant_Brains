// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry.PositionOnGrid;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.Constants;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Function;
import java.util.concurrent.atomic.AtomicInteger;

public final class CreationItemToEventAdapter
    implements Function
{

    private static final AtomicInteger lastVersion = new AtomicInteger();
    private final ItemAdapter adapter;
    private final TimeUtils timeUtils;

    public CreationItemToEventAdapter(ItemAdapter itemadapter, TimeUtils timeutils, ObservableReference observablereference)
    {
        adapter = itemadapter;
        timeUtils = timeutils;
    }

    public final Object apply(Object obj)
    {
        long l = adapter.getLocalStartMillis(obj);
        long l1 = adapter.getLocalEndMillis(obj);
        long l2 = Math.max(l1 - l, Constants.MIN_CHIP_HEIGHT_MS);
        int i = timeUtils.msToJulianDate(l);
        return (new com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AutoValue_AdapterEvent.Builder()).setItem(obj).setItemVersion(lastVersion.incrementAndGet()).setPosition(CalendarViewType.CREATE_EVENT.minPosition).setMonthSlot(1).setGridTimedPosition(new PositionOnGrid(0.0F, 1.0F, 0)).setIsTimedEvent(true).setJulianDay(i).setStartTimeMs(l).setEndTimeMs(l1).setDisplayStartFp16(timeUtils.msToFp16(l)).setDisplayEndFp16(Math.min((long)(i + 1) << 16, timeUtils.msToFp16(l2 + l))).build();
    }

}
