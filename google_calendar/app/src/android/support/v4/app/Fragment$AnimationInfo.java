// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.animation.Animator;
import android.view.View;

// Referenced classes of package android.support.v4.app:
//            Fragment, SharedElementCallback

public static final class mExitTransitionCallback
{

    public View mAnimatingAway;
    public Animator mAnimator;
    public Object mEnterTransition;
    public SharedElementCallback mEnterTransitionCallback;
    public Object mExitTransition;
    public SharedElementCallback mExitTransitionCallback;
    public boolean mIsHideReplaced;
    public int mNextAnim;
    public int mNextTransition;
    public int mNextTransitionStyle;
    public Object mReenterTransition;
    public Object mReturnTransition;
    public Object mSharedElementEnterTransition;
    public Object mSharedElementReturnTransition;
    public ansitionListener mStartEnterTransitionListener;
    public int mStateAfterAnimating;

    public ansitionListener()
    {
        mEnterTransition = null;
        mReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
        mExitTransition = null;
        mReenterTransition = Fragment.USE_DEFAULT_TRANSITION;
        mSharedElementEnterTransition = null;
        mSharedElementReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
        mEnterTransitionCallback = null;
        mExitTransitionCallback = null;
    }
}
