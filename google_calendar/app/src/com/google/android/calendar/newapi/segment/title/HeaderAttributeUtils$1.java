// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.title;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

final class val.attribute extends AnimatorListenerAdapter
{

    private final View val$attribute;
    private final boolean val$reverse;

    public final void onAnimationEnd(Animator animator)
    {
        if (val$reverse)
        {
            val$attribute.setVisibility(4);
        }
    }

    public final void onAnimationStart(Animator animator)
    {
        if (!val$reverse)
        {
            val$attribute.setVisibility(0);
        }
    }

    ()
    {
        val$reverse = flag;
        val$attribute = view;
        super();
    }
}
