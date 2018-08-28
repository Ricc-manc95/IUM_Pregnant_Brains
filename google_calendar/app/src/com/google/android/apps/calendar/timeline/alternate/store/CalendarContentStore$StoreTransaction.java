// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.common.util.concurrent.ListenableFuture;

public interface 
{

    public abstract void addItem(Object obj);

    public abstract void blockUpdates(ListenableFuture listenablefuture);

    public abstract void invalidate();

    public abstract void removeItem(Object obj);
}
