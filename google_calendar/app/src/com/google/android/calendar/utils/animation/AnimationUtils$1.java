// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

final class val.runnable extends AnimatorListenerAdapter
{

    private final Runnable val$runnable;

    public final void onAnimationEnd(Animator animator)
    {
        val$runnable.run();
    }

    ()
    {
        val$runnable = runnable1;
        super();
    }
}
