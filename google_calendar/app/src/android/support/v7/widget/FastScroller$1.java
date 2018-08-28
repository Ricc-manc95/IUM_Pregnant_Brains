// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.animation.ValueAnimator;

// Referenced classes of package android.support.v7.widget:
//            FastScroller

final class this._cls0
    implements Runnable
{

    private final FastScroller this$0;

    public final void run()
    {
        FastScroller fastscroller = FastScroller.this;
        switch (fastscroller.mAnimationState)
        {
        default:
            return;

        case 1: // '\001'
            fastscroller.mShowHideAnimator.cancel();
            // fall through

        case 2: // '\002'
            fastscroller.mAnimationState = 3;
            break;
        }
        fastscroller.mShowHideAnimator.setFloatValues(new float[] {
            ((Float)fastscroller.mShowHideAnimator.getAnimatedValue()).floatValue(), 0.0F
        });
        fastscroller.mShowHideAnimator.setDuration(500L);
        fastscroller.mShowHideAnimator.start();
    }

    ()
    {
        this$0 = FastScroller.this;
        super();
    }
}
