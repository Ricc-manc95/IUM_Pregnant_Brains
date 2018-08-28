// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

// Referenced classes of package com.android.bitmap.drawable:
//            TileDrawable, ExtendedBitmapDrawable

static final class _cls2 extends TileDrawable
{

    public final ValueAnimator rotateAnimator;

    public final boolean setVisible(boolean flag)
    {
        flag = super.setVisible(flag);
        if (flag)
        {
            if (isVisible())
            {
                if (rotateAnimator != null)
                {
                    rotateAnimator.start();
                }
            } else
            {
                boolean flag1;
                if (super.fadeAlpha == 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1 && rotateAnimator != null)
                {
                    rotateAnimator.cancel();
                    return flag;
                }
            }
        }
        return flag;
    }

    public ptions(Drawable drawable, Resources resources, int i, int j, int k, ptions ptions)
    {
        super(drawable, i, j, k, ptions);
        rotateAnimator = ValueAnimator.ofInt(new int[] {
            0, 10000
        }).setDuration(resources.getInteger(0x7f110006));
        rotateAnimator.setInterpolator(new LinearInterpolator());
        rotateAnimator.setRepeatCount(-1);
        class _cls1
            implements android.animation.ValueAnimator.AnimatorUpdateListener
        {

            private final ExtendedBitmapDrawable.Progress this$0;

            public final void onAnimationUpdate(ValueAnimator valueanimator)
            {
                setLevel(((Integer)valueanimator.getAnimatedValue()).intValue());
            }

            _cls1()
            {
                this$0 = ExtendedBitmapDrawable.Progress.this;
                super();
            }
        }

        rotateAnimator.addUpdateListener(new _cls1());
        class _cls2 extends AnimatorListenerAdapter
        {

            private final ExtendedBitmapDrawable.Progress this$0;

            public final void onAnimationEnd(Animator animator)
            {
                if (rotateAnimator != null)
                {
                    rotateAnimator.cancel();
                }
            }

            _cls2()
            {
                this$0 = ExtendedBitmapDrawable.Progress.this;
                super();
            }
        }

        mFadeOutAnimator.addListener(new _cls2());
    }
}
