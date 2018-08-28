// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnLayoutImpl, ColumnViewport

final class arg._cls1
    implements Supplier
{

    private final ColumnLayoutImpl arg$1;

    public final Object get()
    {
        return Integer.valueOf((int)(arg$1.viewport.startDayFp16 >> 16) + CalendarViewType.DAY_HEADER.minPosition + 100);
    }

    (ColumnLayoutImpl columnlayoutimpl)
    {
        arg$1 = columnlayoutimpl;
    }
}
