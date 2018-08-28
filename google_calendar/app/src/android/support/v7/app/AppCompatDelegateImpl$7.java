// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.widget.ActionBarContextView;
import android.view.View;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImpl

final class tenerAdapter extends ViewPropertyAnimatorListenerAdapter
{

    private final AppCompatDelegateImpl this$0;

    public final void onAnimationEnd(View view)
    {
        mActionModeView.setAlpha(1.0F);
        mFadeAnim.setListener(null);
        mFadeAnim = null;
    }

    public final void onAnimationStart(View view)
    {
        mActionModeView.setVisibility(0);
        mActionModeView.sendAccessibilityEvent(32);
        if (mActionModeView.getParent() instanceof View)
        {
            ViewCompat.requestApplyInsets((View)mActionModeView.getParent());
        }
    }

    pat()
    {
        this$0 = AppCompatDelegateImpl.this;
        super();
    }
}
