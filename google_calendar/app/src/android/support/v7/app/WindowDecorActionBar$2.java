// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

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
        mCurrentShowAnim = null;
        mContainerView.requestLayout();
    }

    stenerAdapter()
    {
        this$0 = WindowDecorActionBar.this;
        super();
    }
}
