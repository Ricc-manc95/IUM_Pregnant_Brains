// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.view.View;

// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            FeatureHighlightView, FeatureHighlightContent, OuterHighlightDrawable, InnerZoneDrawable

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
        FeatureHighlightView featurehighlightview = FeatureHighlightView.this;
        ObjectAnimator objectanimator = ObjectAnimator.ofFloat(featurehighlightview.content.asView(), "alpha", new float[] {
            0.0F, 1.0F
        }).setDuration(350L);
        objectanimator.setInterpolator(com.google.android.libraries.material.animation.nterpolators.linearOutSlowIn);
        float f = featurehighlightview.targetBounds.exactCenterX();
        float f1 = featurehighlightview.outerHighlight.centerX;
        float f2 = featurehighlightview.targetBounds.exactCenterY();
        float f3 = featurehighlightview.outerHighlight.centerY;
        Animator animator = featurehighlightview.outerHighlight.createShowAnimation(f - f1, f2 - f3, 0.0F);
        Animator animator1 = featurehighlightview.innerZone.createShowAnimation(0.0F);
        AnimatorSet animatorset = new AnimatorSet();
        animatorset.playTogether(new Animator[] {
            objectanimator, animator, animator1
        });
        animatorset.addListener(new <init>(featurehighlightview));
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

    ators()
    {
        this$0 = final_featurehighlightview;
        val$beforeAnimationRunnable = Runnable.this;
        super();
    }
}
