// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            ScheduleLayoutImpl

final class arg._cls1
    implements Supplier
{

    private final ScheduleLayoutImpl arg$1;

    public final Object get()
    {
        return Integer.valueOf(arg$1.topJulianDay + CalendarViewType.DAY_HEADER.minPosition + 100);
    }

    (ScheduleLayoutImpl schedulelayoutimpl)
    {
        arg$1 = schedulelayoutimpl;
    }
}
