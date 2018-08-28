// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

public final class val.callback extends AnimatorListenerAdapter
{

    private final Runnable val$callback;

    public final void onAnimationEnd(Animator animator)
    {
        val$callback.run();
    }

    public ()
    {
        val$callback = runnable;
        super();
    }
}
