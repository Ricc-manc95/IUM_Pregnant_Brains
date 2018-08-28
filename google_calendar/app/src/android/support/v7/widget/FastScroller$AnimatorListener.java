// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;

// Referenced classes of package android.support.v7.widget:
//            FastScroller, RecyclerView

final class mCanceled extends AnimatorListenerAdapter
{

    private boolean mCanceled;
    private final FastScroller this$0;

    public final void onAnimationCancel(Animator animator)
    {
        mCanceled = true;
    }

    public final void onAnimationEnd(Animator animator)
    {
        if (mCanceled)
        {
            mCanceled = false;
            return;
        }
        if (((Float)mShowHideAnimator.getAnimatedValue()).floatValue() == 0.0F)
        {
            mAnimationState = 0;
            setState(0);
            return;
        } else
        {
            mAnimationState = 2;
            mRecyclerView.invalidate();
            return;
        }
    }

    ()
    {
        this$0 = FastScroller.this;
        super();
        mCanceled = false;
    }
}
