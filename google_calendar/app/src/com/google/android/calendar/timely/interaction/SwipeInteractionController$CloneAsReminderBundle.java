// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction;

import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.calendar.timely.TimelineTaskBundle;

// Referenced classes of package com.google.android.calendar.timely.interaction:
//            SwipeInteractionController

static final class  extends TimelineItemOperation
{

    public final Object onReminderBundle(TimelineTaskBundle timelinetaskbundle, Object aobj[])
    {
        return (TimelineTaskBundle)timelinetaskbundle.clone();
    }

    public final Object onSingleReminder(TimelineTask timelinetask, Object aobj[])
    {
        return new TimelineTaskBundle(timelinetask);
    }

    ()
    {
    }
}
