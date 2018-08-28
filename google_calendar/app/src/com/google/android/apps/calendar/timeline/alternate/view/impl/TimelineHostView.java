// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;

public interface TimelineHostView
{

    public abstract Context getContext();

    public abstract int getMeasuredHeight();

    public abstract int getMeasuredWidth();

    public abstract void invalidate();

    public abstract void requestFocusAfterLayout(ListenableFuture listenablefuture, Supplier supplier);

    public abstract void requestLayout();

    public abstract void stopScroll();
}
