// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.ActionBarContextView;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.PopupWindow;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImpl

final class this._cls0
    implements Runnable
{

    public final AppCompatDelegateImpl this$0;

    public final void run()
    {
        mActionModePopup.showAtLocation(mActionModeView, 55, 0, 0);
        endOnGoingFadeAnimation();
        AppCompatDelegateImpl appcompatdelegateimpl = AppCompatDelegateImpl.this;
        boolean flag;
        if (appcompatdelegateimpl.mSubDecorInstalled && appcompatdelegateimpl.mSubDecor != null && ViewCompat.isLaidOut(appcompatdelegateimpl.mSubDecor))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            mActionModeView.setAlpha(0.0F);
            AppCompatDelegateImpl appcompatdelegateimpl1 = AppCompatDelegateImpl.this;
            ViewPropertyAnimatorCompat viewpropertyanimatorcompat = ViewCompat.animate(mActionModeView);
            View view = (View)viewpropertyanimatorcompat.mView.get();
            if (view != null)
            {
                view.animate().alpha(1.0F);
            }
            appcompatdelegateimpl1.mFadeAnim = viewpropertyanimatorcompat;
            class _cls1 extends ViewPropertyAnimatorListenerAdapter
            {

                private final AppCompatDelegateImpl._cls6 this$1;

                public final void onAnimationEnd(View view1)
                {
                    mActionModeView.setAlpha(1.0F);
                    mFadeAnim.setListener(null);
                    mFadeAnim = null;
                }

                public final void onAnimationStart(View view1)
                {
                    mActionModeView.setVisibility(0);
                }

            _cls1()
            {
                this$1 = AppCompatDelegateImpl._cls6.this;
                super();
            }
            }

            mFadeAnim.setListener(new _cls1());
            return;
        } else
        {
            mActionModeView.setAlpha(1.0F);
            mActionModeView.setVisibility(0);
            return;
        }
    }

    _cls1()
    {
        this$0 = AppCompatDelegateImpl.this;
        super();
    }
}
