// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.collect.Range;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnViewport

final class selectedRangeObservable
    implements Consumer
{

    public int lastEnd;
    public int lastStart;
    private final ObservableReference selectedRangeObservable;
    private final ColumnViewport viewport;
    private final ObservableReference viewportObservable;

    public final void accept(Object obj)
    {
        int i = (int)(viewport.startDayFp16 >> 16);
        int j = viewport.getRightVisibleJulianDay();
        if (lastStart != i || lastEnd != j)
        {
            lastStart = i;
            lastEnd = j;
            obj = Range.closed(Integer.valueOf(i), Integer.valueOf(j));
            viewportObservable.set(obj);
            selectedRangeObservable.set(obj);
        }
    }

    (ColumnViewport columnviewport, ObservableReference observablereference, ObservableReference observablereference1)
    {
        viewport = columnviewport;
        viewportObservable = observablereference;
        selectedRangeObservable = observablereference1;
    }
}
