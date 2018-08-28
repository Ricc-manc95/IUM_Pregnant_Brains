// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import java.util.Set;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            AlternateTimelineRescheduleManager

final class arg._cls2
    implements Runnable
{

    private final AlternateTimelineRescheduleManager arg$1;
    private final Object arg$2;

    public final void run()
    {
        AlternateTimelineRescheduleManager alternatetimelinereschedulemanager = arg$1;
        Object obj = arg$2;
        synchronized (alternatetimelinereschedulemanager.pendingReschedules)
        {
            alternatetimelinereschedulemanager.pendingReschedules.remove(obj);
        }
        return;
        exception;
        set;
        JVM INSTR monitorexit ;
        throw exception;
    }

    a(AlternateTimelineRescheduleManager alternatetimelinereschedulemanager, Object obj)
    {
        arg$1 = alternatetimelinereschedulemanager;
        arg$2 = obj;
    }
}
