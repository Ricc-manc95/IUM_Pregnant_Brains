// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.widget.ActionBarContainer;
import android.view.View;

// Referenced classes of package android.support.v7.app:
//            WindowDecorActionBar

final class stenerAdapter extends ViewPropertyAnimatorListenerAdapter
{

    private final WindowDecorActionBar this$0;

    public final void onAnimationEnd(View view)
    {
        if (mContentAnimations && mContentView != null)
        {
            mContentView.setTranslationY(0.0F);
            mContainerView.setTranslationY(0.0F);
        }
        mContainerView.setVisibility(8);
        view = mContainerView;
        view.mIsTransitioning = false;
        view.setDescendantFocusability(0x40000);
        mCurrentShowAnim = null;
        view = WindowDecorActionBar.this;
        if (((WindowDecorActionBar) (view)).mDeferredModeDestroyCallback != null)
        {
            ((WindowDecorActionBar) (view)).mDeferredModeDestroyCallback.estroyActionMode(((WindowDecorActionBar) (view)).mDeferredDestroyActionMode);
            view.mDeferredDestroyActionMode = null;
            view.mDeferredModeDestroyCallback = null;
        }
        if (mOverlayLayout != null)
        {
            ViewCompat.requestApplyInsets(mOverlayLayout);
        }
    }

    stenerAdapter()
    {
        this$0 = WindowDecorActionBar.this;
        super();
    }
}
