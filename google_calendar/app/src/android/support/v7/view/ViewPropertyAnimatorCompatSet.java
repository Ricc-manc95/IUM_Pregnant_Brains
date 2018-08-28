// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view;

import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public final class ViewPropertyAnimatorCompatSet
{

    public final ArrayList mAnimators = new ArrayList();
    public long mDuration;
    public Interpolator mInterpolator;
    public boolean mIsStarted;
    public ViewPropertyAnimatorListener mListener;
    private final ViewPropertyAnimatorListenerAdapter mProxyListener = new _cls1();

    public ViewPropertyAnimatorCompatSet()
    {
        mDuration = -1L;
    }

    public final void cancel()
    {
        if (!mIsStarted)
        {
            return;
        }
        ArrayList arraylist = (ArrayList)mAnimators;
        int k = arraylist.size();
        int i = 0;
        do
        {
            if (i >= k)
            {
                break;
            }
            Object obj = arraylist.get(i);
            int j = i + 1;
            obj = (View)((ViewPropertyAnimatorCompat)obj).mView.get();
            i = j;
            if (obj != null)
            {
                ((View) (obj)).animate().cancel();
                i = j;
            }
        } while (true);
        mIsStarted = false;
    }

    public final void start()
    {
        if (mIsStarted)
        {
            return;
        }
        ArrayList arraylist = (ArrayList)mAnimators;
        int j = arraylist.size();
        for (int i = 0; i < j; i++)
        {
            Object obj = (ViewPropertyAnimatorCompat)arraylist.get(i);
            if (mDuration >= 0L)
            {
                long l = mDuration;
                View view = (View)((ViewPropertyAnimatorCompat) (obj)).mView.get();
                if (view != null)
                {
                    view.animate().setDuration(l);
                }
            }
            if (mInterpolator != null)
            {
                Interpolator interpolator = mInterpolator;
                View view1 = (View)((ViewPropertyAnimatorCompat) (obj)).mView.get();
                if (view1 != null)
                {
                    view1.animate().setInterpolator(interpolator);
                }
            }
            if (mListener != null)
            {
                ((ViewPropertyAnimatorCompat) (obj)).setListener(mProxyListener);
            }
            obj = (View)((ViewPropertyAnimatorCompat) (obj)).mView.get();
            if (obj != null)
            {
                ((View) (obj)).animate().start();
            }
        }

        mIsStarted = true;
    }

    private class _cls1 extends ViewPropertyAnimatorListenerAdapter
    {

        private int mProxyEndCount;
        private boolean mProxyStarted;
        private final ViewPropertyAnimatorCompatSet this$0;

        public final void onAnimationEnd(View view)
        {
            int i = mProxyEndCount + 1;
            mProxyEndCount = i;
            if (i == mAnimators.size())
            {
                if (mListener != null)
                {
                    mListener.onAnimationEnd(null);
                }
                mProxyEndCount = 0;
                mProxyStarted = false;
                mIsStarted = false;
            }
        }

        public final void onAnimationStart(View view)
        {
            if (!mProxyStarted)
            {
                mProxyStarted = true;
                if (mListener != null)
                {
                    mListener.onAnimationStart(null);
                    return;
                }
            }
        }

        _cls1()
        {
            this$0 = ViewPropertyAnimatorCompatSet.this;
            super();
            mProxyStarted = false;
            mProxyEndCount = 0;
        }
    }

}
