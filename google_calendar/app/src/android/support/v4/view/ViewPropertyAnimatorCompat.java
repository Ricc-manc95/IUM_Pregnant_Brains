// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v4.view:
//            ViewPropertyAnimatorListener, ViewPropertyAnimatorUpdateListener

public final class ViewPropertyAnimatorCompat
{

    public Runnable mEndAction;
    public int mOldLayerType;
    public Runnable mStartAction;
    public WeakReference mView;

    ViewPropertyAnimatorCompat(View view)
    {
        mStartAction = null;
        mEndAction = null;
        mOldLayerType = -1;
        mView = new WeakReference(view);
    }

    public final ViewPropertyAnimatorCompat setListener(final ViewPropertyAnimatorListener listener)
    {
        final View view;
label0:
        {
            view = (View)mView.get();
            if (view != null)
            {
                if (listener == null)
                {
                    break label0;
                }
                view.animate().setListener(new _cls1());
            }
            return this;
        }
        view.animate().setListener(null);
        return this;
    }

    public final ViewPropertyAnimatorCompat setUpdateListener(final ViewPropertyAnimatorUpdateListener listener)
    {
        final View view = (View)mView.get();
        if (view != null)
        {
            _cls2 _lcls2 = null;
            if (listener != null)
            {
                _lcls2 = new _cls2();
            }
            view.animate().setUpdateListener(_lcls2);
        }
        return this;
    }

    private class _cls1 extends AnimatorListenerAdapter
    {

        private final ViewPropertyAnimatorListener val$listener;
        private final View val$view;

        public final void onAnimationCancel(Animator animator)
        {
            listener.onAnimationCancel(view);
        }

        public final void onAnimationEnd(Animator animator)
        {
            listener.onAnimationEnd(view);
        }

        public final void onAnimationStart(Animator animator)
        {
            listener.onAnimationStart(view);
        }

        _cls1()
        {
            listener = viewpropertyanimatorlistener;
            view = view1;
            super();
        }
    }


    private class _cls2
        implements android.animation.ValueAnimator.AnimatorUpdateListener
    {

        private final ViewPropertyAnimatorUpdateListener val$listener;
        private final View val$view;

        public final void onAnimationUpdate(ValueAnimator valueanimator)
        {
            listener.onAnimationUpdate$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0();
        }

        _cls2()
        {
            listener = viewpropertyanimatorupdatelistener;
            view = view1;
            super();
        }
    }

}
