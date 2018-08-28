// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            TimelineItemOperation, TimelineTaskBundle, TimelineTask

public final class ation extends TimelineItemOperation
{

    private final TimelineTask val$task;

    public final Object onReminderBundle(TimelineTaskBundle timelinetaskbundle, Object aobj[])
    {
        if (timelinetaskbundle.contains(val$task))
        {
            return timelinetaskbundle;
        } else
        {
            return null;
        }
    }

    public le()
    {
        val$task = timelinetask;
        super();
    }
}
