// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            TransitionListenerAdapter, ViewGroupUtilsApi18, Transition, ChangeBounds

final class mCanceled extends TransitionListenerAdapter
{

    private boolean mCanceled;
    private final ViewGroup val$parent;

    public final void onTransitionEnd(Transition transition)
    {
        ViewGroupUtilsApi18.suppressLayout(val$parent, false);
        transition.removeListener(this);
    }

    public final void onTransitionPause$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
    {
        ViewGroupUtilsApi18.suppressLayout(val$parent, false);
    }

    public final void onTransitionResume$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
    {
        ViewGroupUtilsApi18.suppressLayout(val$parent, true);
    }

    pi18(ViewGroup viewgroup)
    {
        val$parent = viewgroup;
        super();
        mCanceled = false;
    }
}
