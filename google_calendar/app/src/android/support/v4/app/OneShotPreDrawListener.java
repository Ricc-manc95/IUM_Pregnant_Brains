// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.view.View;
import android.view.ViewTreeObserver;

final class OneShotPreDrawListener
    implements android.view.View.OnAttachStateChangeListener, android.view.ViewTreeObserver.OnPreDrawListener
{

    private final Runnable mRunnable;
    private final View mView;
    private ViewTreeObserver mViewTreeObserver;

    OneShotPreDrawListener(View view, Runnable runnable)
    {
        mView = view;
        mViewTreeObserver = view.getViewTreeObserver();
        mRunnable = runnable;
    }

    private final void removeListener()
    {
        if (mViewTreeObserver.isAlive())
        {
            mViewTreeObserver.removeOnPreDrawListener(this);
        } else
        {
            mView.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        mView.removeOnAttachStateChangeListener(this);
    }

    public final boolean onPreDraw()
    {
        removeListener();
        mRunnable.run();
        return true;
    }

    public final void onViewAttachedToWindow(View view)
    {
        mViewTreeObserver = view.getViewTreeObserver();
    }

    public final void onViewDetachedFromWindow(View view)
    {
        removeListener();
    }
}
