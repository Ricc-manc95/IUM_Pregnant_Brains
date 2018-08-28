// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            ScheduleItem, ScheduleLayoutImpl

final class arg._cls1
    implements Predicate
{

    private final CalendarViewType arg$1;

    public final boolean apply(Object obj)
    {
        return ScheduleLayoutImpl.lambda$getBottomPxOfFirstView$3$ScheduleLayoutImpl(arg$1, (ScheduleItem)obj);
    }

    (CalendarViewType calendarviewtype)
    {
        arg$1 = calendarviewtype;
    }
}
