// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;

// Referenced classes of package android.support.v4.widget:
//            SwipeRefreshLayout, CircleImageView

final class this._cls0 extends Animation
{

    private final SwipeRefreshLayout this$0;

    public final void applyTransformation(float f, Transformation transformation)
    {
        transformation = SwipeRefreshLayout.this;
        f = 1.0F - f;
        ((SwipeRefreshLayout) (transformation)).mCircleView.setScaleX(f);
        ((SwipeRefreshLayout) (transformation)).mCircleView.setScaleY(f);
    }

    ()
    {
        this$0 = SwipeRefreshLayout.this;
        super();
    }
}
