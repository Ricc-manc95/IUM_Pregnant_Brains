// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.common.base.Function;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnViewportController, ColumnViewport

final class arg._cls1
    implements Function
{

    private final ColumnViewportController arg$1;

    public final Object apply(Object obj)
    {
        ColumnViewportController columnviewportcontroller = arg$1;
        int i = columnviewportcontroller.viewport.getSnappedToClosestJulianDay();
        columnviewportcontroller.viewport.setStartDayAndWidthFp16(i, (long)i << 16, (long)columnviewportcontroller.viewport.snappedDays << 16);
        return obj;
    }

    (ColumnViewportController columnviewportcontroller)
    {
        arg$1 = columnviewportcontroller;
    }
}
