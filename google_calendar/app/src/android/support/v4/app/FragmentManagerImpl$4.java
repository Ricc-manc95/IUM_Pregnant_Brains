// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.support.v4.app:
//            Fragment, FragmentManagerImpl

final class it> extends AnimatorListenerAdapter
{

    private final View val$animatingView;
    private final ViewGroup val$container;
    private final Fragment val$fragment;

    public final void onAnimationEnd(Animator animator)
    {
        val$container.endViewTransition(val$animatingView);
        animator.removeListener(this);
        if (val$fragment.mView != null)
        {
            val$fragment.mView.setVisibility(8);
        }
    }

    (Fragment fragment1)
    {
        val$container = viewgroup;
        val$animatingView = view;
        val$fragment = fragment1;
        super();
    }
}
