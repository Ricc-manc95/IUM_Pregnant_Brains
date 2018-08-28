// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.ColumnLayout;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import dagger.Lazy;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            TimelineApiImpl

final class arg._cls3
    implements AsyncFunction
{

    private final TimelineApiImpl arg$1;
    private final int arg$2;
    private final int arg$3;

    public final ListenableFuture apply(Object obj)
    {
        obj = arg$1;
        int i = arg$2;
        int j = arg$3;
        return ((ColumnLayout)((TimelineApiImpl) (obj)).columnLayout.get()).setNumDaysAndStart(i, j);
    }

    (TimelineApiImpl timelineapiimpl, int i, int j)
    {
        arg$1 = timelineapiimpl;
        arg$2 = i;
        arg$3 = j;
    }
}
