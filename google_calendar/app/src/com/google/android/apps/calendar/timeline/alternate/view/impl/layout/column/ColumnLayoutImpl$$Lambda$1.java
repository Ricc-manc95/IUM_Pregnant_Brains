// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnLayoutImpl, ColumnViewport

final class arg._cls1
    implements Function
{

    private final ColumnLayoutImpl arg$1;

    public final Object apply(Object obj)
    {
        obj = arg$1;
        int i;
        if (((Boolean)((ColumnLayoutImpl) (obj)).shouldShowWeekNumbers.get()).booleanValue())
        {
            i = CalendarViewType.WEEK_NUMBER.minPosition;
        } else
        {
            i = (int)(((ColumnLayoutImpl) (obj)).viewport.startDayFp16 >> 16) + CalendarViewType.DAY_HEADER.minPosition + 100;
        }
        return Integer.valueOf(i);
    }

    (ColumnLayoutImpl columnlayoutimpl)
    {
        arg$1 = columnlayoutimpl;
    }
}
