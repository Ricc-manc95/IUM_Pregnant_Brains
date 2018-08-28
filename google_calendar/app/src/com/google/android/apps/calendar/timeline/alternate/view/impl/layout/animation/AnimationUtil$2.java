// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public final class val.recycler extends AnimatorListenerAdapter
{

    private boolean canceled;
    private final android.support.v7.widget.outManager val$layoutManager;
    private final android.support.v7.widget.ycler val$recycler;
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
            val$layoutManager.detachView(val$view);
            val$recycler.recycleView(val$view);
        }
    }

    public ()
    {
        val$view = view1;
        val$layoutManager = outmanager;
        val$recycler = ycler;
        super();
    }
}
