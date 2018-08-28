// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public final class val.view extends AnimatorListenerAdapter
{

    private boolean canceled;
    private final View val$view;

    public final void onAnimationCancel(Animator animator)
    {
        canceled = true;
    }

    public final void onAnimationEnd(Animator animator)
    {
        if (!canceled)
        {
            val$view.setAlpha(1.0F);
        }
    }

    public ()
    {
        val$view = view1;
        super();
    }
}
