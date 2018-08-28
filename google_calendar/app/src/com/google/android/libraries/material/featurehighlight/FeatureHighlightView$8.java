// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.TextView;

// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            FeatureHighlightView

final class val.hideRunnable extends AnimatorListenerAdapter
{

    private final FeatureHighlightView this$0;
    private final Runnable val$hideRunnable;

    public final void onAnimationEnd(Animator animator)
    {
        boolean flag1 = false;
        hiding = false;
        animator = FeatureHighlightView.this;
        boolean flag = flag1;
        if (((FeatureHighlightView) (animator)).targetTextColor != 0)
        {
            flag = flag1;
            if (((FeatureHighlightView) (animator)).targetView instanceof TextView)
            {
                flag = true;
            }
        }
        if (flag)
        {
            ((TextView)targetView).setTextColor(originalTextColor);
        }
        setVisibility(8);
        currentAnimation = null;
        if (val$hideRunnable != null)
        {
            val$hideRunnable.run();
        }
    }

    public final void onAnimationStart(Animator animator)
    {
        hiding = true;
    }

    ()
    {
        this$0 = final_featurehighlightview;
        val$hideRunnable = Runnable.this;
        super();
    }
}
