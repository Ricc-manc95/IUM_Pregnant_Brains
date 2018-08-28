// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.animation;

import android.animation.Animator;
import android.view.View;

public final class val.animator
    implements android.view.eChangeListener
{

    private final Animator val$animator;

    public final void onViewAttachedToWindow(View view)
    {
    }

    public final void onViewDetachedFromWindow(View view)
    {
        val$animator.cancel();
    }

    public ()
    {
        val$animator = animator1;
        super();
    }
}
