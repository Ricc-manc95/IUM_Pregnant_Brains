// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

// Referenced classes of package android.support.v7.widget:
//            FastScroller, RecyclerView

final class this._cls0
    implements android.animation.istener
{

    private final FastScroller this$0;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        int i = (int)(((Float)valueanimator.getAnimatedValue()).floatValue() * 255F);
        mVerticalThumbDrawable.setAlpha(i);
        mVerticalTrackDrawable.setAlpha(i);
        mRecyclerView.invalidate();
    }

    ()
    {
        this$0 = FastScroller.this;
        super();
    }
}
