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

    private final FragmentManagerImpl this$0;
    private final ViewGroup val$container;
    private final Fragment val$fragment;
    private final View val$viewToAnimate;

    public final void onAnimationEnd(Animator animator)
    {
        val$container.endViewTransition(val$viewToAnimate);
        animator = val$fragment;
        Fragment fragment1;
        if (((Fragment) (animator)).mAnimationInfo == null)
        {
            animator = null;
        } else
        {
            animator = ((Fragment) (animator)).mAnimationInfo.mAnimator;
        }
        fragment1 = val$fragment;
        if (fragment1.mAnimationInfo == null)
        {
            fragment1.mAnimationInfo = new ();
        }
        fragment1.mAnimationInfo.mAnimator = null;
        if (animator != null && val$container.indexOfChild(val$viewToAnimate) < 0)
        {
            animator = FragmentManagerImpl.this;
            Fragment fragment2 = val$fragment;
            Fragment fragment3 = val$fragment;
            int i;
            if (fragment3.mAnimationInfo == null)
            {
                i = 0;
            } else
            {
                i = fragment3.mAnimationInfo.mStateAfterAnimating;
            }
            animator.moveToState(fragment2, i, 0, 0, false);
        }
    }

    ()
    {
        this$0 = final_fragmentmanagerimpl;
        val$container = viewgroup;
        val$viewToAnimate = view;
        val$fragment = Fragment.this;
        super();
    }
}
