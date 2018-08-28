// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chipviewmodelfactory;

import com.google.android.calendar.timely.TimelineBirthday;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineTaskBundle;

final class  extends TimelineItemOperation
{

    public final Object onAny(TimelineItem timelineitem, Object aobj[])
    {
        return timelineitem.getTitle();
    }

    public final Object onBirthdayBundle(TimelineBirthday timelinebirthday, Object aobj[])
    {
        return timelinebirthday.singleLineTitle;
    }

    public final Object onReminderBundle(TimelineTaskBundle timelinetaskbundle, Object aobj[])
    {
        return timelinetaskbundle.singleLineTitle;
    }

    ()
    {
    }
}
