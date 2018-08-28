// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;


// Referenced classes of package android.support.transition:
//            TransitionListenerAdapter, TransitionSet, Transition

final class mTransitionSet extends TransitionListenerAdapter
{

    private TransitionSet mTransitionSet;

    public final void onTransitionEnd(Transition transition)
    {
        TransitionSet transitionset = mTransitionSet;
        transitionset.mCurrentListeners = transitionset.mCurrentListeners - 1;
        if (mTransitionSet.mCurrentListeners == 0)
        {
            mTransitionSet.mStarted = false;
            mTransitionSet.end();
        }
        transition.removeListener(this);
    }

    public final void onTransitionStart$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
    {
        if (!mTransitionSet.mStarted)
        {
            mTransitionSet.start();
            mTransitionSet.mStarted = true;
        }
    }

    (TransitionSet transitionset)
    {
        mTransitionSet = transitionset;
    }
}
