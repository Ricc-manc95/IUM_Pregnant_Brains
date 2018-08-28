// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import com.google.android.libraries.material.animation.CancelableLoopingListener;

// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            FeatureHighlightView

final class val.beforeAnimationRunnable
    implements android.view.ener
{

    private final FeatureHighlightView this$0;
    private final Runnable val$beforeAnimationRunnable;

    public final void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1)
    {
        if (val$beforeAnimationRunnable != null)
        {
            val$beforeAnimationRunnable.run();
        }
        view = FeatureHighlightView.this;
        if (view.getParent() != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new IllegalStateException(String.valueOf("View must be attached to view hierarchy"));
        }
        view.setVisibility(0);
        view.hiding = false;
        view = FeatureHighlightView.this;
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
        if (((FeatureHighlightView) (view)).currentAnimation != null)
        {
            ((FeatureHighlightView) (view)).currentAnimation.cancel();
        }
        if (animatorset != null)
        {
            view.currentAnimation = animatorset;
            ((FeatureHighlightView) (view)).currentAnimation.start();
        }
        removeOnLayoutChangeListener(this);
    }

    mAnimatorsDisabledListener()
    {
        this$0 = final_featurehighlightview;
        val$beforeAnimationRunnable = Runnable.this;
        super();
    }
}
