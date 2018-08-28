// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package android.support.design.internal:
//            StateListAnimator

final class this._cls0 extends AnimatorListenerAdapter
{

    private final StateListAnimator this$0;

    public final void onAnimationEnd(Animator animator)
    {
        if (runningAnimator == animator)
        {
            runningAnimator = null;
        }
    }

    _cls9()
    {
        this$0 = StateListAnimator.this;
        super();
    }
}
