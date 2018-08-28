// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.widget:
//            DefaultItemAnimator

final class  extends AnimatorListenerAdapter
{

    private final DefaultItemAnimator this$0;
    private final ViewPropertyAnimator val$animation;
    private final r val$holder;
    private final View val$view;

    public final void onAnimationEnd(Animator animator)
    {
        val$animation.setListener(null);
        val$view.setAlpha(1.0F);
        animator = DefaultItemAnimator.this;
        r r = val$holder;
        if (((tor) (animator)).mListener != null)
        {
            ((tor) (animator)).mListener.onAnimationFinished(r);
        }
        mRemoveAnimations.remove(val$holder);
        animator = DefaultItemAnimator.this;
        if (!animator.isRunning())
        {
            animator.dispatchAnimationsFinished();
        }
    }

    public final void onAnimationStart(Animator animator)
    {
    }

    r()
    {
        this$0 = final_defaultitemanimator;
        val$holder = r;
        val$animation = viewpropertyanimator;
        val$view = View.this;
        super();
    }
}
