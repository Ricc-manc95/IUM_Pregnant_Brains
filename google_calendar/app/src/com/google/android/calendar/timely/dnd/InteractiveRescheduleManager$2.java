// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.dnd;

import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineTaskType;

final class  extends TimelineItemOperation
{

    public final Object onAny(TimelineItem timelineitem, Object aobj[])
    {
        return "unknown";
    }

    public final Object onAnyEvent(TimelineEvent timelineevent, Object aobj[])
    {
        return "event";
    }

    public final Object onAnyReminder(TimelineTaskType timelinetasktype, Object aobj[])
    {
        return "reminder";
    }

    public final Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
    {
        return "goal";
    }

    ()
    {
    }
}
