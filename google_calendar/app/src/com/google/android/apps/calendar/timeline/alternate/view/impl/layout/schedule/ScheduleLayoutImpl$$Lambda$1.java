// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            ScheduleLayoutImpl

final class arg._cls2
    implements Supplier
{

    private final ScheduleLayoutImpl arg$1;
    private final long arg$2;

    public final Object get()
    {
        return Integer.valueOf(arg$1.getTimeOffsetPx(arg$2));
    }

    (ScheduleLayoutImpl schedulelayoutimpl, long l)
    {
        arg$1 = schedulelayoutimpl;
        arg$2 = l;
    }
}
