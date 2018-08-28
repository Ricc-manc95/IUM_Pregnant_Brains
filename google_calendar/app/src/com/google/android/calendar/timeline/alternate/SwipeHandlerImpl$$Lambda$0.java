// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            SwipeHandlerImpl

final class arg._cls2
    implements AsyncFunction
{

    private final SwipeHandlerImpl arg$1;
    private final TimeRangeEntry arg$2;

    public final ListenableFuture apply(Object obj)
    {
        SwipeHandlerImpl swipehandlerimpl = arg$1;
        TimeRangeEntry timerangeentry = arg$2;
        obj = (com.google.android.calendar.r.DeleteOptions)obj;
        return swipehandlerimpl.store.updateStore(new <init>(swipehandlerimpl, timerangeentry, ((com.google.android.calendar.r.DeleteOptions) (obj))));
    }

    tore(SwipeHandlerImpl swipehandlerimpl, TimeRangeEntry timerangeentry)
    {
        arg$1 = swipehandlerimpl;
        arg$2 = timerangeentry;
    }
}
