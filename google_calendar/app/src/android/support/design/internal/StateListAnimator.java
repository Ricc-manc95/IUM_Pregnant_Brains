// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.animation.ValueAnimator;
import java.util.ArrayList;

public final class StateListAnimator
{

    public final android.animation.Animator.AnimatorListener animationListener = new _cls1();
    public Tuple lastMatch;
    public ValueAnimator runningAnimator;
    public final ArrayList tuples = new ArrayList();

    public StateListAnimator()
    {
        lastMatch = null;
        runningAnimator = null;
    }

    private class _cls1 extends AnimatorListenerAdapter
    {

        private final StateListAnimator this$0;

        public final void onAnimationEnd(Animator animator)
        {
            if (runningAnimator == animator)
            {
                runningAnimator = null;
            }
        }

        _cls1()
        {
            this$0 = StateListAnimator.this;
            super();
        }
    }

}
