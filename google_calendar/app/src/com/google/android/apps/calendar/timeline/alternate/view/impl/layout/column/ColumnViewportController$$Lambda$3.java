// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;


// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnViewportController, ColumnViewport

final class arg._cls8
    implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.tener
{

    private final ColumnViewportController arg$1;
    private final int arg$2;
    private final long arg$3;
    private final long arg$4;
    private final long arg$5;
    private final long arg$6;
    private final float arg$7;
    private final float arg$8;

    public final void onStep(float f)
    {
        ColumnViewportController columnviewportcontroller = arg$1;
        int i = arg$2;
        long l = arg$3;
        long l1 = arg$4;
        long l2 = arg$5;
        long l3 = arg$6;
        float f1 = arg$7;
        float f2 = arg$8;
        long l4 = (long)(65536F * f);
        columnviewportcontroller.viewport.setStartDayAndWidthFp16(i, l + (l1 * l4 >> 16), (l3 * l4 >> 16) + l2);
        columnviewportcontroller.viewport.oneDayRatio = f2 * f + f1;
    }

    Listener(ColumnViewportController columnviewportcontroller, int i, long l, long l1, long l2, long l3, float f, float f1)
    {
        arg$1 = columnviewportcontroller;
        arg$2 = i;
        arg$3 = l;
        arg$4 = l1;
        arg$5 = l2;
        arg$6 = l3;
        arg$7 = f;
        arg$8 = f1;
    }
}
