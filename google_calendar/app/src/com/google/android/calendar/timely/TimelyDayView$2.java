// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            TimelineItemOperation, TimelineTaskType

public final class ation extends TimelineItemOperation
{

    private final String val$accountName;

    public final Object onAnyReminder(TimelineTaskType timelinetasktype, Object aobj[])
    {
        if (timelinetasktype.isDone() && timelinetasktype.getSourceAccountName().equals(val$accountName))
        {
            return timelinetasktype;
        } else
        {
            return null;
        }
    }

    public ()
    {
        val$accountName = s;
        super();
    }
}
