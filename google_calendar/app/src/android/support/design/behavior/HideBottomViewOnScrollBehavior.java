// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.behavior;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.design.animation.AnimationUtils;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class HideBottomViewOnScrollBehavior extends android.support.design.widget.CoordinatorLayout.Behavior
{

    public ViewPropertyAnimator currentAnimator;
    private int currentState;
    private int height;

    public HideBottomViewOnScrollBehavior()
    {
        height = 0;
        currentState = 2;
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        height = 0;
        currentState = 2;
    }

    private final void animateChildTo(View view, int i, long l, TimeInterpolator timeinterpolator)
    {
        currentAnimator = view.animate().translationY(i).setInterpolator(timeinterpolator).setDuration(l).setListener(new _cls1());
    }

    public final boolean onLayoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
    {
        height = view.getMeasuredHeight();
        return super.onLayoutChild(coordinatorlayout, view, i);
    }

    public final void onNestedScroll$51662RJ4E9NMIP1FEDQN0S3FE9Q2UP35EDKMERHFETKM8PR5EGNK6RRFE9I6IRJ1EHNN4J31F5NNAT1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2UTJ9CLRIULJ9CLRJMIA9954IILG_0(View view, int i)
    {
        if (currentState != 1 && i > 0)
        {
            if (currentAnimator != null)
            {
                currentAnimator.cancel();
                view.clearAnimation();
            }
            currentState = 1;
            animateChildTo(view, height, 175L, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        } else
        if (currentState != 2 && i < 0)
        {
            if (currentAnimator != null)
            {
                currentAnimator.cancel();
                view.clearAnimation();
            }
            currentState = 2;
            animateChildTo(view, 0, 225L, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
            return;
        }
    }

    public final boolean onStartNestedScroll$51662RJ4E9NMIP1FEDQN0S3FE9Q2UP35EDKMERHFETKM8PR5EGNK6RRFE9I6IRJ1EHNN4J31F5NNAT1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2UTJ9CLRIULJ9CLRJMJ31DPI74RR9CGNNCQB5ESNLCQB5ESTKIAAQ0(int i)
    {
        return i == 2;
    }

    private class _cls1 extends AnimatorListenerAdapter
    {

        private final HideBottomViewOnScrollBehavior this$0;

        public final void onAnimationEnd(Animator animator)
        {
            currentAnimator = null;
        }

        _cls1()
        {
            this$0 = HideBottomViewOnScrollBehavior.this;
            super();
        }
    }

}
