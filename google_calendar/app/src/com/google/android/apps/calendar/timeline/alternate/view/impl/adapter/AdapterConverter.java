// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry.PositionOnGrid;
import com.google.common.collect.ComparisonChain;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterEvent, AutoValue_AdapterEvent_Key

final class AdapterConverter
{

    private static final long MS_PER_DAY;
    public final ItemAdapter adapter;
    private final Map keyToPosition = new HashMap();
    private int nextEntryPosition;
    public final TimeUtils timeUtils;

    AdapterConverter(TimeUtils timeutils, ItemAdapter itemadapter)
    {
        nextEntryPosition = 0;
        timeUtils = timeutils;
        adapter = itemadapter;
    }

    static final int lambda$prepareDaysWithEvents$0$AdapterConverter(AdapterEvent adapterevent, AdapterEvent adapterevent1)
    {
        return adapterevent.getGridAllDaySlot().intValue() - adapterevent1.getGridAllDaySlot().intValue();
    }

    static final int lambda$prepareDaysWithEvents$1$AdapterConverter(AdapterEvent adapterevent, AdapterEvent adapterevent1)
    {
        return ComparisonChain.ACTIVE.compare(adapterevent.getStartTimeMs(), adapterevent1.getStartTimeMs()).compare(adapterevent.getGridTimedPosition().startFraction, adapterevent1.getGridTimedPosition().startFraction).result();
    }

    final Integer getEventPosition(Object obj, int i)
    {
        AutoValue_AdapterEvent_Key autovalue_adapterevent_key = new AutoValue_AdapterEvent_Key(adapter.getKey(obj), i);
        Map map = keyToPosition;
        map;
        JVM INSTR monitorenter ;
        Integer integer = (Integer)keyToPosition.get(autovalue_adapterevent_key);
        obj = integer;
        if (integer != null)
        {
            break MISSING_BLOCK_LABEL_80;
        }
        i = nextEntryPosition;
        nextEntryPosition = i + 1;
        obj = Integer.valueOf(i);
        keyToPosition.put(autovalue_adapterevent_key, obj);
        map;
        JVM INSTR monitorexit ;
        return ((Integer) (obj));
        obj;
        map;
        JVM INSTR monitorexit ;
        throw obj;
    }

    final boolean isTimedEvent(Object obj)
    {
        return !adapter.isAllDay(obj) && adapter.getLocalEndMillis(obj) - adapter.getLocalStartMillis(obj) < MS_PER_DAY;
    }

    static 
    {
        MS_PER_DAY = TimeUnit.DAYS.toMillis(1L);
    }
}
