// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import com.google.android.libraries.material.animation.CancelableLoopingListener;

// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            FeatureHighlightView

final class this._cls0 extends AnimatorListenerAdapter
{

    private final FeatureHighlightView this$0;

    public final void onAnimationEnd(Animator animator)
    {
        currentAnimation = null;
        if (!hiding)
        {
            animator = FeatureHighlightView.this;
            Object obj1 = FeatureHighlightView.this;
            Object obj = ((FeatureHighlightView) (obj1)).innerZone;
            obj1 = ((FeatureHighlightView) (obj1)).getContext();
            AnimatorSet animatorset = new AnimatorSet();
            ObjectAnimator objectanimator = ObjectAnimator.ofFloat(obj, "scale", new float[] {
                1.0F, 1.1F
            }).setDuration(500L);
            ObjectAnimator objectanimator1 = ObjectAnimator.ofFloat(obj, "scale", new float[] {
                1.1F, 1.0F
            }).setDuration(500L);
            obj = ObjectAnimator.ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
                PropertyValuesHolder.ofFloat("pulseScale", new float[] {
                    1.1F, 2.0F
                }), PropertyValuesHolder.ofFloat("pulseAlpha", new float[] {
                    1.0F, 0.0F
                })
            }).setDuration(500L);
            animatorset.play(objectanimator);
            animatorset.play(objectanimator1).h(((Animator) (obj))).er(objectanimator);
            animatorset.setInterpolator(com.google.android.libraries.material.animation.nterpolators.fastOutSlowIn);
            animatorset.setStartDelay(500L);
            animatorset.addListener(new CancelableLoopingListener(animatorset, -1, null));
            animatorset.addListener(new mAnimatorsDisabledListener(((android.content.Context) (obj1))));
            if (((FeatureHighlightView) (animator)).currentAnimation != null)
            {
                ((FeatureHighlightView) (animator)).currentAnimation.cancel();
            }
            if (animatorset != null)
            {
                animator.currentAnimation = animatorset;
                ((FeatureHighlightView) (animator)).currentAnimation.start();
            }
        }
    }

    mAnimatorsDisabledListener()
    {
        this$0 = FeatureHighlightView.this;
        super();
    }
}
