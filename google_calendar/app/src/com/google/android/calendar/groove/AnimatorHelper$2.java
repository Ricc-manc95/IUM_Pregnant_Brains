// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;

final class val.translateX extends Visibility
{

    private final float val$translateX;

    public final Animator onAppear(ViewGroup viewgroup, View view, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if (transitionvalues1 == null)
        {
            return null;
        } else
        {
            viewgroup = ObjectAnimator.ofFloat(view, "translationX", new float[] {
                val$translateX, 0.0F
            });
            viewgroup.setInterpolator(new FastOutSlowInInterpolator());
            return viewgroup;
        }
    }

    public final Animator onDisappear(ViewGroup viewgroup, View view, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if (transitionvalues == null)
        {
            return null;
        } else
        {
            viewgroup = ObjectAnimator.ofFloat(view, "translationX", new float[] {
                0.0F, val$translateX
            });
            viewgroup.setInterpolator(new FastOutSlowInInterpolator());
            return viewgroup;
        }
    }

    polator()
    {
        val$translateX = f;
        super();
    }
}
