// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

final class mView extends AnimatorListenerAdapter
{

    private View mView;

    public final void onAnimationEnd(Animator animator)
    {
        mView.setLayerType(0, null);
        animator.removeListener(this);
    }

    public final void onAnimationStart(Animator animator)
    {
        mView.setLayerType(2, null);
    }

    (View view)
    {
        mView = view;
    }
}
