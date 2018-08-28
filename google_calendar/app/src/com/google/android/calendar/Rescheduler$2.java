// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.res.Resources;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineTask;

final class eItemOperation extends TimelineItemOperation
{

    private final long val$newStartTimeMillis;
    private final Resources val$resources;

    public final volatile Object onAny(TimelineItem timelineitem, Object aobj[])
    {
        return null;
    }

    public final Object onSingleReminder(TimelineTask timelinetask, Object aobj[])
    {
        long l1 = val$newStartTimeMillis;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        if (l1 >= l)
        {
            return null;
        } else
        {
            return val$resources.getString(0x7f130366);
        }
    }

    eTask()
    {
        val$newStartTimeMillis = l;
        val$resources = resources1;
        super();
    }
}
