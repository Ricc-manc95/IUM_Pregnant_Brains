// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

// Referenced classes of package com.google.common.util.concurrent:
//            AbstractFuture

static final class valueUpdater extends valueUpdater
{

    public final AtomicReferenceFieldUpdater listenersUpdater;
    public final AtomicReferenceFieldUpdater valueUpdater;
    public final AtomicReferenceFieldUpdater waiterNextUpdater;
    public final AtomicReferenceFieldUpdater waiterThreadUpdater;
    public final AtomicReferenceFieldUpdater waitersUpdater;

    final boolean casListeners(AbstractFuture abstractfuture, valueUpdater valueupdater, valueUpdater valueupdater1)
    {
        return listenersUpdater.compareAndSet(abstractfuture, valueupdater, valueupdater1);
    }

    final boolean casValue(AbstractFuture abstractfuture, Object obj, Object obj1)
    {
        return valueUpdater.compareAndSet(abstractfuture, obj, obj1);
    }

    final boolean casWaiters(AbstractFuture abstractfuture, Set set, Set set1)
    {
        return waitersUpdater.compareAndSet(abstractfuture, set, set1);
    }

    final void putNext(Set set, Set set1)
    {
        waiterNextUpdater.lazySet(set, set1);
    }

    final void putThread(waiterNextUpdater waiternextupdater, Thread thread)
    {
        waiterThreadUpdater.lazySet(waiternextupdater, thread);
    }

    (AtomicReferenceFieldUpdater atomicreferencefieldupdater, AtomicReferenceFieldUpdater atomicreferencefieldupdater1, AtomicReferenceFieldUpdater atomicreferencefieldupdater2, AtomicReferenceFieldUpdater atomicreferencefieldupdater3, AtomicReferenceFieldUpdater atomicreferencefieldupdater4)
    {
        waiterThreadUpdater = atomicreferencefieldupdater;
        waiterNextUpdater = atomicreferencefieldupdater1;
        waitersUpdater = atomicreferencefieldupdater2;
        listenersUpdater = atomicreferencefieldupdater3;
        valueUpdater = atomicreferencefieldupdater4;
    }
}
