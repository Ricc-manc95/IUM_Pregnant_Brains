// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.widget.ActionBarContainer;
import android.view.View;

// Referenced classes of package android.support.v7.app:
//            WindowDecorActionBar

final class this._cls0
    implements ViewPropertyAnimatorUpdateListener
{

    private final WindowDecorActionBar this$0;

    public final void onAnimationUpdate$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0()
    {
        ((View)mContainerView.getParent()).invalidate();
    }

    dateListener()
    {
        this$0 = WindowDecorActionBar.this;
        super();
    }
}
