// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            ViewUtils, ViewGroupUtils, Transition

final class suppressLayout extends AnimatorListenerAdapter
    implements 
{

    private boolean mCanceled;
    private final int mFinalVisibility;
    private boolean mLayoutSuppressed;
    private final ViewGroup mParent;
    private final boolean mSuppressLayout = true;
    private final View mView;

    private final void hideViewWhenNotCanceled()
    {
        if (!mCanceled)
        {
            ViewUtils.setTransitionVisibility(mView, mFinalVisibility);
            if (mParent != null)
            {
                mParent.invalidate();
            }
        }
        suppressLayout(false);
    }

    private final void suppressLayout(boolean flag)
    {
        if (mSuppressLayout && mLayoutSuppressed != flag && mParent != null)
        {
            mLayoutSuppressed = flag;
            ViewGroupUtils.suppressLayout(mParent, flag);
        }
    }

    public final void onAnimationCancel(Animator animator)
    {
        mCanceled = true;
    }

    public final void onAnimationEnd(Animator animator)
    {
        hideViewWhenNotCanceled();
    }

    public final void onAnimationPause(Animator animator)
    {
        if (!mCanceled)
        {
            ViewUtils.setTransitionVisibility(mView, mFinalVisibility);
        }
    }

    public final void onAnimationRepeat(Animator animator)
    {
    }

    public final void onAnimationResume(Animator animator)
    {
        if (!mCanceled)
        {
            ViewUtils.setTransitionVisibility(mView, 0);
        }
    }

    public final void onAnimationStart(Animator animator)
    {
    }

    public final void onTransitionEnd(Transition transition)
    {
        hideViewWhenNotCanceled();
        transition.removeListener(this);
    }

    public final void onTransitionPause$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
    {
        suppressLayout(false);
    }

    public final void onTransitionResume$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
    {
        suppressLayout(true);
    }

    public final void onTransitionStart$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
    {
    }

    (View view, int i, boolean flag)
    {
        mCanceled = false;
        mView = view;
        mFinalVisibility = i;
        mParent = (ViewGroup)view.getParent();
        suppressLayout(true);
    }
}
