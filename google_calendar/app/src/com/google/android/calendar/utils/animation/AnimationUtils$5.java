// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

final class val.fadeIn extends AnimatorListenerAdapter
{

    private final boolean val$fadeIn;
    private final View val$view;

    public final void onAnimationEnd(Animator animator)
    {
        animator = val$view;
        boolean flag = val$fadeIn;
        if (animator != null)
        {
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            animator.setVisibility(i);
        }
    }

    public final void onAnimationStart(Animator animator)
    {
        animator = val$view;
        if (animator != null)
        {
            animator.setVisibility(0);
        }
    }

    ()
    {
        val$view = view1;
        val$fadeIn = flag;
        super();
    }
}
