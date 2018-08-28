// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import com.google.android.calendar.timely.TimelineItem;

public final class DelayedActionDescription
{

    public final int actionId;
    public final TimelineItem timelineItem;

    public DelayedActionDescription(TimelineItem timelineitem, int i)
    {
        timelineItem = timelineitem;
        actionId = i;
    }
}
