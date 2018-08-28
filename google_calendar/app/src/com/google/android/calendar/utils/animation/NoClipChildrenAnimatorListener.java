// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;

public final class NoClipChildrenAnimatorListener extends AnimatorListenerAdapter
{

    private boolean restoreClipChildren;
    private final ViewGroup target;

    public NoClipChildrenAnimatorListener(ViewGroup viewgroup)
    {
        target = viewgroup;
    }

    public final void onAnimationEnd(Animator animator)
    {
        if (restoreClipChildren)
        {
            target.setClipChildren(true);
        }
    }

    public final void onAnimationStart(Animator animator)
    {
        restoreClipChildren = target.getClipChildren();
        target.setClipChildren(false);
    }
}
