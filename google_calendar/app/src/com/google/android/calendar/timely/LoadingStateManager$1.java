// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

final class val.view extends AnimatorListenerAdapter
{

    private final View val$view;

    public final void onAnimationEnd(Animator animator)
    {
        val$view.setVisibility(8);
    }

    ()
    {
        val$view = view1;
        super();
    }
}
