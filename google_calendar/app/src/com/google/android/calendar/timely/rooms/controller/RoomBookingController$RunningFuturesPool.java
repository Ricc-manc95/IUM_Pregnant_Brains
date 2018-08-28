// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class .Lambda._cls0
{

    public final Set futures = Collections.synchronizedSet(new HashSet());

    final void add(ListenableFuture listenablefuture)
    {
        if (!listenablefuture.isDone())
        {
            futures.add(listenablefuture);
            class .Lambda._cls0
                implements Runnable
            {

                private final RoomBookingController.RunningFuturesPool arg$1;
                private final ListenableFuture arg$2;

                public final void run()
                {
                    RoomBookingController.RunningFuturesPool runningfuturespool = arg$1;
                    ListenableFuture listenablefuture1 = arg$2;
                    runningfuturespool.futures.remove(listenablefuture1);
                }

            .Lambda._cls0(ListenableFuture listenablefuture)
            {
                arg$1 = RoomBookingController.RunningFuturesPool.this;
                arg$2 = listenablefuture;
            }
            }

            listenablefuture.addListener(new .Lambda._cls0(listenablefuture), com.google.common.util.concurrent.unningFuturesPool);
        }
    }

    .Lambda._cls0()
    {
    }
}
