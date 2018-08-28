// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.DeleteEventInteractiveHelper;
import com.google.android.calendar.api.event.EventClient;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            SwipeHandlerImpl

final class arg._cls3
    implements Consumer
{

    private final SwipeHandlerImpl arg$1;
    private final TimeRangeEntry arg$2;
    private final com.google.android.calendar.r.DeleteOptions arg$3;

    public final void accept(Object obj)
    {
        SwipeHandlerImpl swipehandlerimpl = arg$1;
        TimeRangeEntry timerangeentry = arg$2;
        com.google.android.calendar.r.DeleteOptions deleteoptions = arg$3;
        obj = (com.google.android.apps.calendar.timeline.alternate.store.ransaction)obj;
        ((com.google.android.apps.calendar.timeline.alternate.store.ransaction) (obj)).removeItem(timerangeentry);
        ((com.google.android.apps.calendar.timeline.alternate.store.ransaction) (obj)).blockUpdates(DeleteEventInteractiveHelper.eventClient.delete(deleteoptions.descriptor(), deleteoptions.scope(), deleteoptions.guestNotification()));
        swipehandlerimpl.logUserAction("delete_swipe");
    }

    tore.StoreTransaction(SwipeHandlerImpl swipehandlerimpl, TimeRangeEntry timerangeentry, com.google.android.calendar.r.DeleteOptions deleteoptions)
    {
        arg$1 = swipehandlerimpl;
        arg$2 = timerangeentry;
        arg$3 = deleteoptions;
    }
}
