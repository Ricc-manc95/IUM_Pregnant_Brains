// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnViewportController, ColumnViewport

final class arg._cls3
    implements Function
{

    private final ColumnViewportController arg$1;
    private final int arg$2;
    private final int arg$3;

    public final Object apply(Object obj)
    {
        ColumnViewportController columnviewportcontroller = arg$1;
        int i = arg$2;
        int j = arg$3;
        columnviewportcontroller.view.stopScroll();
        columnviewportcontroller.viewport.setGridTopMsOfDay(i + j);
        return obj;
    }

    (ColumnViewportController columnviewportcontroller, int i, int j)
    {
        arg$1 = columnviewportcontroller;
        arg$2 = i;
        arg$3 = j;
    }
}
