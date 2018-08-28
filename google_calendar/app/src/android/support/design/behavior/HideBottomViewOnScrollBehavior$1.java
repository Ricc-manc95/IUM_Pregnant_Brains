// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package android.support.design.behavior:
//            HideBottomViewOnScrollBehavior

final class this._cls0 extends AnimatorListenerAdapter
{

    private final HideBottomViewOnScrollBehavior this$0;

    public final void onAnimationEnd(Animator animator)
    {
        currentAnimator = null;
    }

    ()
    {
        this$0 = HideBottomViewOnScrollBehavior.this;
        super();
    }
}
