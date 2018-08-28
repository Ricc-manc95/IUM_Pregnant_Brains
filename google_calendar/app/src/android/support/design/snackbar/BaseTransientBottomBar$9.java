// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.snackbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package android.support.design.snackbar:
//            BaseTransientBottomBar, ContentViewCallback

final class val.event extends AnimatorListenerAdapter
{

    private final BaseTransientBottomBar this$0;
    private final int val$event;

    public final void onAnimationEnd(Animator animator)
    {
        onViewHidden(val$event);
    }

    public final void onAnimationStart(Animator animator)
    {
        contentViewCallback.animateContentOut(0, 180);
    }

    ()
    {
        this$0 = final_basetransientbottombar;
        val$event = I.this;
        super();
    }
}
