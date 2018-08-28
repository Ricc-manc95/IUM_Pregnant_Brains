// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.support.v4.view.ViewCompat;
import android.view.animation.Animation;
import android.view.animation.Transformation;

// Referenced classes of package android.support.v4.widget:
//            SwipeRefreshLayout, CircleImageView, CircularProgressDrawable

final class this._cls0 extends Animation
{

    private final SwipeRefreshLayout this$0;

    public final void applyTransformation(float f, Transformation transformation)
    {
        boolean flag = mUsingCustomStart;
        int j = mSpinnerOffsetEnd;
        int k = Math.abs(mOriginalOffsetTop);
        int i = mFrom;
        j = (int)((float)(j - k - mFrom) * f);
        k = mCircleView.getTop();
        transformation = SwipeRefreshLayout.this;
        ((SwipeRefreshLayout) (transformation)).mCircleView.bringToFront();
        ViewCompat.offsetTopAndBottom(((SwipeRefreshLayout) (transformation)).mCircleView, (j + i) - k);
        transformation.mCurrentTargetOffsetTop = ((SwipeRefreshLayout) (transformation)).mCircleView.getTop();
        transformation = mProgress;
        f = 1.0F - f;
        ble.Ring ring = ((CircularProgressDrawable) (transformation)).mRing;
        if (f != ring.mArrowScale)
        {
            ring.mArrowScale = f;
        }
        transformation.invalidateSelf();
    }

    ble.Ring()
    {
        this$0 = SwipeRefreshLayout.this;
        super();
    }
}
