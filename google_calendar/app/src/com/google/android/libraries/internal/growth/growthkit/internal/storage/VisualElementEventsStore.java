// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage;

import com.google.common.util.concurrent.ListenableFuture;

public interface VisualElementEventsStore
{

    public abstract ListenableFuture cleanup(long l);

    public abstract ListenableFuture clearAll();

    public abstract ListenableFuture getAllEventsCounts();

    public abstract ListenableFuture getEventsCounts(Iterable iterable);
}
