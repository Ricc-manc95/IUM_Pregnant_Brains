// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnViewportController

final class arg._cls2
    implements AsyncFunction
{

    private final ColumnViewportController arg$1;
    private final Integer arg$2;

    public final ListenableFuture apply(Object obj)
    {
        return arg$1.animateVerticalViewportChange(arg$2.intValue());
    }

    (ColumnViewportController columnviewportcontroller, Integer integer)
    {
        arg$1 = columnviewportcontroller;
        arg$2 = integer;
    }
}
