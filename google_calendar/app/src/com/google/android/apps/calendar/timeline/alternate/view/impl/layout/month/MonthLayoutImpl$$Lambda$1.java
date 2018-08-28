// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month;

import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterMonth;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterMonthDay;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapter;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month:
//            MonthLayoutImpl, MonthViewport

final class arg._cls1
    implements Function
{

    private final MonthLayoutImpl arg$1;

    public final Object apply(Object obj)
    {
        obj = arg$1;
        return Integer.valueOf(((AdapterMonthDay)((MonthLayoutImpl) (obj)).adapter.getMonth(((MonthLayoutImpl) (obj)).viewport.start).getDays().get(0)).getMonthDayHeaderPosition());
    }

    (MonthLayoutImpl monthlayoutimpl)
    {
        arg$1 = monthlayoutimpl;
    }
}
