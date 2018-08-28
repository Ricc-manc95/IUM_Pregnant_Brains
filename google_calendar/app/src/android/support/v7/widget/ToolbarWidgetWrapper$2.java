// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            ToolbarWidgetWrapper, Toolbar

final class mCanceled extends ViewPropertyAnimatorListenerAdapter
{

    private boolean mCanceled;
    private final ToolbarWidgetWrapper this$0;
    private final int val$visibility;

    public final void onAnimationCancel(View view)
    {
        mCanceled = true;
    }

    public final void onAnimationEnd(View view)
    {
        if (!mCanceled)
        {
            mToolbar.setVisibility(val$visibility);
        }
    }

    public final void onAnimationStart(View view)
    {
        mToolbar.setVisibility(0);
    }

    nerAdapter()
    {
        this$0 = final_toolbarwidgetwrapper;
        val$visibility = I.this;
        super();
        mCanceled = false;
    }
}
