// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            AbsActionBarView

public final class mCanceled
    implements ViewPropertyAnimatorListener
{

    private boolean mCanceled;
    public int mFinalVisibility;
    public final AbsActionBarView this$0;

    public final void onAnimationCancel(View view)
    {
        mCanceled = true;
    }

    public final void onAnimationEnd(View view)
    {
        if (mCanceled)
        {
            return;
        } else
        {
            mVisibilityAnim = null;
            AbsActionBarView.access$101(AbsActionBarView.this, mFinalVisibility);
            return;
        }
    }

    public final void onAnimationStart(View view)
    {
        AbsActionBarView.access$001(AbsActionBarView.this, 0);
        mCanceled = false;
    }

    protected ()
    {
        this$0 = AbsActionBarView.this;
        super();
        mCanceled = false;
    }
}
