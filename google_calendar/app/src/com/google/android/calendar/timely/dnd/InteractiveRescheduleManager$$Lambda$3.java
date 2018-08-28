// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.dnd;

import com.google.android.calendar.Rescheduler;
import com.google.android.calendar.timely.TimelineItem;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.timely.dnd:
//            InteractiveRescheduleManager

final class arg._cls2
    implements AsyncFunction
{

    private final InteractiveRescheduleManager arg$1;
    private final long arg$2;

    public final ListenableFuture apply(Object obj)
    {
        Object obj1 = arg$1;
        long l = arg$2;
        obj = (com.google.android.calendar.api.event..arg._cls2)obj;
        obj1 = ((InteractiveRescheduleManager) (obj1)).rescheduler;
        return (ListenableFuture)((Rescheduler) (obj1)).rescheduledItem.perform(new com.google.android.calendar.tion(((Rescheduler) (obj1)), ((com.google.android.calendar.api.event.>) (obj)), l), new Void[0]);
    }

    (InteractiveRescheduleManager interactivereschedulemanager, long l)
    {
        arg$1 = interactivereschedulemanager;
        arg$2 = l;
    }
}
