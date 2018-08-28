// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Map;

public final class ConcurrentFunctions
{

    public static ListenableFuture computeIfNotPending(Map map, Object obj, Supplier supplier)
    {
        map;
        JVM INSTR monitorenter ;
        Object obj1;
        .Lambda._cls0 _lcls0;
        class .Lambda._cls0
            implements Supplier
        {

            private final Supplier arg$1;
            private final Map arg$2;
            private final Object arg$3;

            public final Object get()
            {
                Object obj3 = arg$1;
                Map map1 = arg$2;
                Object obj2 = arg$3;
                obj3 = (ListenableFuture)((Supplier) (obj3)).get();
                class .Lambda._cls1
                    implements Runnable
                {

                    private final Map arg$1;
                    private final Object arg$2;

                    public final void run()
                    {
                        Map map2 = arg$1;
                        Object obj4 = arg$2;
                        map2;
                        JVM INSTR monitorenter ;
                        map2.remove(obj4);
                        map2;
                        JVM INSTR monitorexit ;
                        return;
                        Exception exception;
                        exception;
                        map2;
                        JVM INSTR monitorexit ;
                        throw exception;
                    }

                        .Lambda._cls1(Map map, Object obj)
                        {
                            arg$1 = map;
                            arg$2 = obj;
                        }
                }

                ((ListenableFuture) (obj3)).addListener(new .Lambda._cls1(map1, obj2), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                if (((ListenableFuture) (obj3)).isDone())
                {
                    return obj3;
                } else
                {
                    com.google.common.util.concurrent.Futures.NonCancellationPropagatingFuture noncancellationpropagatingfuture = new com.google.common.util.concurrent.Futures.NonCancellationPropagatingFuture(((ListenableFuture) (obj3)));
                    ((ListenableFuture) (obj3)).addListener(noncancellationpropagatingfuture, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                    return noncancellationpropagatingfuture;
                }
            }

            .Lambda._cls0(Supplier supplier, Map map, Object obj)
            {
                arg$1 = supplier;
                arg$2 = map;
                arg$3 = obj;
            }
        }

        _lcls0 = new .Lambda._cls0(supplier, map, obj);
        obj1 = map.get(obj);
        supplier = ((Supplier) (obj1));
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        supplier = ((Supplier) (_lcls0.get()));
        map.put(obj, supplier);
        obj = (ListenableFuture)supplier;
        map;
        JVM INSTR monitorexit ;
        return ((ListenableFuture) (obj));
        obj;
        map;
        JVM INSTR monitorexit ;
        throw obj;
    }
}
