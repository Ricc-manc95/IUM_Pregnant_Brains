// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.snackbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package android.support.design.snackbar:
//            BaseTransientBottomBar, ContentViewCallback

final class this._cls0 extends AnimatorListenerAdapter
{

    private final BaseTransientBottomBar this$0;

    public final void onAnimationEnd(Animator animator)
    {
        onViewShown();
    }

    public final void onAnimationStart(Animator animator)
    {
        contentViewCallback.animateContentIn(70, 180);
    }

    ()
    {
        this$0 = BaseTransientBottomBar.this;
        super();
    }
}
