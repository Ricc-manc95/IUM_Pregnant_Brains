// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            ScheduleItem, ScheduleLayoutImpl

final class arg._cls1
    implements Predicate
{

    private final long arg$1;

    public final boolean apply(Object obj)
    {
        return ScheduleLayoutImpl.lambda$getTimeOffsetPx$4$ScheduleLayoutImpl(arg$1, (ScheduleItem)obj);
    }

    (long l)
    {
        arg$1 = l;
    }
}
