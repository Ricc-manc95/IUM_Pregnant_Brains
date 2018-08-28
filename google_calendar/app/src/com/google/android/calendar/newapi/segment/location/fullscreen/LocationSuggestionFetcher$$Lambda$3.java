// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.segment.location.fullscreen:
//            LocationSuggestionFetcher

final class arg._cls2
    implements Runnable
{

    private final LocationSuggestionFetcher arg$1;
    private final ListenableFuture arg$2;

    public final void run()
    {
        LocationSuggestionFetcher locationsuggestionfetcher = arg$1;
        ListenableFuture listenablefuture = arg$2;
        if (!listenablefuture.isCancelled())
        {
            locationsuggestionfetcher.listener.nItemsChanged((List)Futures.getUnchecked(listenablefuture));
        }
    }

    (LocationSuggestionFetcher locationsuggestionfetcher, ListenableFuture listenablefuture)
    {
        arg$1 = locationsuggestionfetcher;
        arg$2 = listenablefuture;
    }
}
