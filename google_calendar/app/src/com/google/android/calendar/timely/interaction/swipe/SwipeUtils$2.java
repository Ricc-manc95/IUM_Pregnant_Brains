// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction.swipe;

import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.calendar.timely.TimelineTaskBundle;

final class  extends TimelineItemOperation
{

    public final Object onAnyEvent(TimelineEvent timelineevent, Object aobj[])
    {
        return Integer.valueOf(0x7f0201de);
    }

    public final Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
    {
        return Integer.valueOf(0x7f0201e3);
    }

    public final Object onReminderBundle(TimelineTaskBundle timelinetaskbundle, Object aobj[])
    {
        return Integer.valueOf(0x7f020238);
    }

    public final Object onSingleReminder(TimelineTask timelinetask, Object aobj[])
    {
        return Integer.valueOf(0x7f0201e3);
    }

    ()
    {
    }
}
