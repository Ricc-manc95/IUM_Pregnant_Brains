// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chipviewmodelfactory;

import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineTaskType;

final class  extends TimelineItemOperation
{

    public final Object onAnyReminder(TimelineTaskType timelinetasktype, Object aobj[])
    {
        return Integer.valueOf(timelinetasktype.getColor());
    }

    ()
    {
    }
}
