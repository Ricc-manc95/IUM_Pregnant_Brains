// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package android.support.v7.widget:
//            ActionBarOverlayLayout

final class this._cls0 extends AnimatorListenerAdapter
{

    private final ActionBarOverlayLayout this$0;

    public final void onAnimationCancel(Animator animator)
    {
        mCurrentActionBarTopAnimator = null;
        mAnimatingForFling = false;
    }

    public final void onAnimationEnd(Animator animator)
    {
        mCurrentActionBarTopAnimator = null;
        mAnimatingForFling = false;
    }

    ()
    {
        this$0 = ActionBarOverlayLayout.this;
        super();
    }
}
