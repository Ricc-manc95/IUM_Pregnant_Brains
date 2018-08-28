// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import android.arch.lifecycle.FullLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import com.google.android.apps.calendar.util.concurrent.Subscription;

final class val.lifecycle
    implements FullLifecycleObserver
{

    private final Lifecycle val$lifecycle;
    private final Subscription val$subscriptions;

    public final void onCreate(LifecycleOwner lifecycleowner)
    {
    }

    public final void onDestroy$51662RJ4E9NMIP1FC5P66Q1FDHKMCPB3F5HMOP9F9HKMCPB3F5HMOPAFETN6ASHR55B0____0()
    {
        val$subscriptions.cancel(false);
        val$lifecycle.removeObserver(this);
    }

    public final void onPause(LifecycleOwner lifecycleowner)
    {
    }

    public final void onResume(LifecycleOwner lifecycleowner)
    {
    }

    public final void onStart(LifecycleOwner lifecycleowner)
    {
    }

    public final void onStop(LifecycleOwner lifecycleowner)
    {
    }

    ()
    {
        val$subscriptions = subscription;
        val$lifecycle = lifecycle1;
        super();
    }
}
