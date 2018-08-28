// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import com.google.android.calendar.utils.animation.QuantumInterpolators;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public final class TrommelAnimationHelper extends ViewPropertyAnimatorListenerAdapter
{

    public boolean animating;
    private final ViewPropertyAnimatorListener listener;
    private final View primaryView;
    public boolean reversedAnimation;
    private final View secondaryView;

    public TrommelAnimationHelper(View view, View view1, ViewPropertyAnimatorListener viewpropertyanimatorlistener)
    {
        animating = false;
        primaryView = view;
        secondaryView = view1;
        listener = viewpropertyanimatorlistener;
    }

    public final void onAnimationEnd(View view)
    {
        animating = false;
        primaryView.setAlpha(1.0F);
        primaryView.setTranslationY(0.0F);
        secondaryView.setVisibility(8);
        listener.onAnimationEnd(view);
    }

    public final void onAnimationStart(View view)
    {
        animating = true;
        listener.onAnimationStart(view);
    }

    public final void startAnimation(boolean flag)
    {
        float f1 = 100F;
        reversedAnimation = flag;
        secondaryView.setAlpha(0.0F);
        secondaryView.setVisibility(0);
        Object obj = secondaryView;
        float f;
        ViewPropertyAnimatorCompat viewpropertyanimatorcompat;
        Object obj1;
        View view;
        if (reversedAnimation)
        {
            f = -100F;
        } else
        {
            f = 100F;
        }
        ((View) (obj)).setTranslationY(f);
        obj = new ViewPropertyAnimatorCompatSet();
        if (reversedAnimation)
        {
            f = f1;
        } else
        {
            f = -100F;
        }
        viewpropertyanimatorcompat = ViewCompat.animate(primaryView);
        obj1 = (View)viewpropertyanimatorcompat.mView.get();
        if (obj1 != null)
        {
            ((View) (obj1)).animate().alpha(0.0F);
        }
        obj1 = (View)viewpropertyanimatorcompat.mView.get();
        if (obj1 != null)
        {
            ((View) (obj1)).animate().translationY(f);
        }
        obj1 = (View)viewpropertyanimatorcompat.mView.get();
        if (obj1 != null)
        {
            ((View) (obj1)).animate().setDuration(225L);
        }
        obj1 = QuantumInterpolators.FAST_OUT_SLOW_IN;
        view = (View)viewpropertyanimatorcompat.mView.get();
        if (view != null)
        {
            view.animate().setInterpolator(((android.animation.TimeInterpolator) (obj1)));
        }
        if (!((ViewPropertyAnimatorCompatSet) (obj)).mIsStarted)
        {
            ((ViewPropertyAnimatorCompatSet) (obj)).mAnimators.add(viewpropertyanimatorcompat);
        }
        viewpropertyanimatorcompat = ViewCompat.animate(secondaryView);
        obj1 = (View)viewpropertyanimatorcompat.mView.get();
        if (obj1 != null)
        {
            ((View) (obj1)).animate().alpha(1.0F);
        }
        obj1 = (View)viewpropertyanimatorcompat.mView.get();
        if (obj1 != null)
        {
            ((View) (obj1)).animate().translationY(0.0F);
        }
        obj1 = (View)viewpropertyanimatorcompat.mView.get();
        if (obj1 != null)
        {
            ((View) (obj1)).animate().setDuration(225L);
        }
        obj1 = QuantumInterpolators.FAST_OUT_SLOW_IN;
        view = (View)viewpropertyanimatorcompat.mView.get();
        if (view != null)
        {
            view.animate().setInterpolator(((android.animation.TimeInterpolator) (obj1)));
        }
        if (!((ViewPropertyAnimatorCompatSet) (obj)).mIsStarted)
        {
            ((ViewPropertyAnimatorCompatSet) (obj)).mAnimators.add(viewpropertyanimatorcompat);
        }
        if (!((ViewPropertyAnimatorCompatSet) (obj)).mIsStarted)
        {
            obj.mListener = this;
        }
        ((ViewPropertyAnimatorCompatSet) (obj)).start();
    }
}
