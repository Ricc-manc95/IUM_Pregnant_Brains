// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction;

import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.calendar.timely.TimelineTaskBundle;
import java.util.Collections;

// Referenced classes of package com.google.android.calendar.timely.interaction:
//            SwipeInteractionController

static final class  extends TimelineItemOperation
{

    public final Object onAny(TimelineItem timelineitem, Object aobj[])
    {
        return Collections.emptyList();
    }

    public final Object onReminderBundle(TimelineTaskBundle timelinetaskbundle, Object aobj[])
    {
        return timelinetaskbundle.timelineTaskList;
    }

    public final Object onSingleReminder(TimelineTask timelinetask, Object aobj[])
    {
        return Collections.singletonList(timelinetask);
    }

    ()
    {
    }
}
