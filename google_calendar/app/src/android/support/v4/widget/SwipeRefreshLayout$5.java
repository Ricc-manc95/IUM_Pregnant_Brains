// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.view.animation.Animation;

// Referenced classes of package android.support.v4.widget:
//            SwipeRefreshLayout

final class this._cls0
    implements android.view.animation.stener
{

    private final SwipeRefreshLayout this$0;

    public final void onAnimationEnd(Animation animation)
    {
        boolean flag = mScale;
        startScaleDownAnimation(null);
    }

    public final void onAnimationRepeat(Animation animation)
    {
    }

    public final void onAnimationStart(Animation animation)
    {
    }

    ner()
    {
        this$0 = SwipeRefreshLayout.this;
        super();
    }
}
