// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.common.base.Function;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnViewportController, ColumnViewport

final class arg._cls5
    implements Function
{

    private final ColumnViewportController arg$1;
    private final int arg$2;
    private final long arg$3;
    private final long arg$4;
    private final float arg$5;

    public final Object apply(Object obj)
    {
        ColumnViewportController columnviewportcontroller = arg$1;
        int i = arg$2;
        long l = arg$3;
        long l1 = arg$4;
        float f = arg$5;
        columnviewportcontroller.viewport.setStartDayAndWidthFp16(i, l, l1);
        columnviewportcontroller.viewport.oneDayRatio = f;
        return obj;
    }

    (ColumnViewportController columnviewportcontroller, int i, long l, long l1, float f)
    {
        arg$1 = columnviewportcontroller;
        arg$2 = i;
        arg$3 = l;
        arg$4 = l1;
        arg$5 = f;
    }
}
