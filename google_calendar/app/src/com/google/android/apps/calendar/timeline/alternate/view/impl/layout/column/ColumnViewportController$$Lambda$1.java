// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;


// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnViewportController, ColumnViewport

final class arg._cls3
    implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.tener
{

    private final ColumnViewportController arg$1;
    private final int arg$2;
    private final int arg$3;

    public final void onStep(float f)
    {
        ColumnViewportController columnviewportcontroller = arg$1;
        int i = arg$2;
        int j = arg$3;
        columnviewportcontroller.viewport.setGridTopMsOfDay((int)((float)i + (float)j * f));
    }

    Listener(ColumnViewportController columnviewportcontroller, int i, int j)
    {
        arg$1 = columnviewportcontroller;
        arg$2 = i;
        arg$3 = j;
    }
}
