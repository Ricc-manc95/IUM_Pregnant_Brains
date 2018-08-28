// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;

final class this._cls0 extends AnimatorListenerAdapter
{

    private final validateSelf this$0;

    public final void onAnimationEnd(Animator animator)
    {
        animator = this._cls0.this;
        if (((this._cls0) (animator)).lseAnimator != null)
        {
            ((lseAnimator) (animator)).lseAnimator.cancel();
            animator.lseAlpha = 255;
            animator.validateSelf();
        }
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
