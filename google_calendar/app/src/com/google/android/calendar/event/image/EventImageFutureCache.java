// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import android.content.Context;
import android.support.v4.util.LruCache;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.event.image:
//            AutoValue_EventImageFutureCache_Key, AutoValue_ImmediateEventImageResolver, EventImageRequestKey, EventImage

public final class EventImageFutureCache
{

    private static final LruCache futuresCache = new LruCache(25);

    public static ListenableFuture getFuture(Context context, EventImage.Resolver resolver, int i, int j)
    {
        LruCache lrucache = futuresCache;
        lrucache;
        JVM INSTR monitorenter ;
        ListenableFuture listenablefuture;
        AutoValue_EventImageFutureCache_Key autovalue_eventimagefuturecache_key;
        autovalue_eventimagefuturecache_key = new AutoValue_EventImageFutureCache_Key(resolver, i, j);
        listenablefuture = (ListenableFuture)futuresCache.get(autovalue_eventimagefuturecache_key);
        Object obj;
        obj = listenablefuture;
        if (listenablefuture != null)
        {
            break MISSING_BLOCK_LABEL_80;
        }
        class .Lambda._cls0
            implements Function
        {

            private final Context arg$1;
            private final int arg$2;
            private final int arg$3;

            public final Object apply(Object obj1)
            {
                return EventImageFutureCache.lambda$getFuture$0$EventImageFutureCache(arg$1, arg$2, arg$3, (EventImage)obj1);
            }

            .Lambda._cls0(Context context, int i, int j)
            {
                arg$1 = context;
                arg$2 = i;
                arg$3 = j;
            }
        }

        obj = AbstractTransformFuture.create(resolver.resolveAsync(context, i, j), new .Lambda._cls0(context, i, j), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        futuresCache.put(autovalue_eventimagefuturecache_key, obj);
        if (!((ListenableFuture) (obj)).isDone()) goto _L2; else goto _L1
_L1:
        lrucache;
        JVM INSTR monitorexit ;
        return ((ListenableFuture) (obj));
_L2:
        context = new com.google.common.util.concurrent.Futures.NonCancellationPropagatingFuture(((ListenableFuture) (obj)));
        ((ListenableFuture) (obj)).addListener(context, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        obj = context;
          goto _L1
        context;
        lrucache;
        JVM INSTR monitorexit ;
        throw context;
    }

    static final EventImageRequestKey lambda$getFuture$0$EventImageFutureCache(Context context, int i, int j, EventImage eventimage)
    {
        return EventImageRequestKey.newInstance(context.getApplicationContext(), new AutoValue_ImmediateEventImageResolver(eventimage, i, j), i, j);
    }

}
