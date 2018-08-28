// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month:
//            MonthLayoutImpl

final class selectedRangeObservable
{

    public int lastEnd;
    public int lastMain;
    public int lastStart;
    public final ObservableReference selectedRangeObservable;
    public final MonthLayoutImpl this$0;
    public final ObservableReference viewportObservable;

    (ObservableReference observablereference, ObservableReference observablereference1)
    {
        this$0 = MonthLayoutImpl.this;
        super();
        viewportObservable = observablereference;
        selectedRangeObservable = observablereference1;
    }
}
