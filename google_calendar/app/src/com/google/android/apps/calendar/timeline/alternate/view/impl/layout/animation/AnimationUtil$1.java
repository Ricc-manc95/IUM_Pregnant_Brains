// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

final class val.bottom extends AnimatorListenerAdapter
{

    private boolean canceled;
    private final int val$bottom;
    private final int val$left;
    private final int val$right;
    private final int val$top;
    private final View val$view;

    public final void onAnimationCancel(Animator animator)
    {
        canceled = true;
    }

    public final void onAnimationEnd(Animator animator)
    {
        if (!canceled)
        {
            val$view.setLeft(val$left);
            val$view.setTop(val$top);
            val$view.setRight(val$right);
            val$view.setBottom(val$bottom);
        }
    }

    ()
    {
        val$view = view1;
        val$left = i;
        val$top = j;
        val$right = k;
        val$bottom = l;
        super();
    }
}
