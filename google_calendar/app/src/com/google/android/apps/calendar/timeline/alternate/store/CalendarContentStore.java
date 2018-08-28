// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

public interface CalendarContentStore
{

    public abstract Subscription subscribe(Consumer consumer, Executor executor);

    public abstract Subscription subscribeToViewport(ObservableReference observablereference);

    public abstract ListenableFuture updateStore(Consumer consumer);
}
