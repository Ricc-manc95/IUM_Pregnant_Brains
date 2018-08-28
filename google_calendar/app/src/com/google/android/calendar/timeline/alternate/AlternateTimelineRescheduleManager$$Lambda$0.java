// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.dnd.InteractiveRescheduleManager;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.SettableFuture;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            AlternateTimelineRescheduleManager

final class arg._cls4
    implements Function
{

    private final AlternateTimelineRescheduleManager arg$1;
    private final InteractiveRescheduleManager arg$2;
    private final TimelineItem arg$3;
    private final Object arg$4;

    public final Object apply(Object obj)
    {
        AlternateTimelineRescheduleManager alternatetimelinereschedulemanager = arg$1;
        InteractiveRescheduleManager interactivereschedulemanager = arg$2;
        TimelineItem timelineitem = arg$3;
        Object obj1 = arg$4;
        obj = (Optional)obj;
        SettableFuture settablefuture = new SettableFuture();
        if (!((Optional) (obj)).isPresent())
        {
            settablefuture.set(new Object());
        } else
        {
            interactivereschedulemanager.reschedule(((Long)((Optional) (obj)).get()).longValue(), new arg._cls4(alternatetimelinereschedulemanager, settablefuture, timelineitem, ((Optional) (obj))));
        }
        settablefuture.addListener(new <init>(alternatetimelinereschedulemanager, obj1), com.google.common.util.concurrent.er);
        return settablefuture;
    }

    a(AlternateTimelineRescheduleManager alternatetimelinereschedulemanager, InteractiveRescheduleManager interactivereschedulemanager, TimelineItem timelineitem, Object obj)
    {
        arg$1 = alternatetimelinereschedulemanager;
        arg$2 = interactivereschedulemanager;
        arg$3 = timelineitem;
        arg$4 = obj;
    }
}
