// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

// Referenced classes of package com.android.bitmap.drawable:
//            TileDrawable, ExtendedBitmapDrawable

static final class _cls2 extends TileDrawable
{

    public int pulseAlpha;
    public final ValueAnimator pulseAnimator;
    public boolean pulseEnabled;

    protected final int getInnerAlpha()
    {
        return pulseAlpha;
    }

    public final boolean setVisible(boolean flag)
    {
        flag = super.setVisible(flag);
        if (flag)
        {
            if (isVisible())
            {
                if (pulseAnimator != null && pulseEnabled && !pulseAnimator.isStarted())
                {
                    pulseAnimator.start();
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
                if (flag1 && pulseAnimator != null)
                {
                    pulseAnimator.cancel();
                    pulseAlpha = 255;
                    invalidateSelf();
                    return flag;
                }
            }
        }
        return flag;
    }

    public ons(Drawable drawable, Resources resources, int i, int j, int k, ons ons)
    {
        super(drawable, i, j, k, ons);
        pulseEnabled = true;
        pulseAlpha = 255;
        class _cls2 extends AnimatorListenerAdapter
        {

            private final ExtendedBitmapDrawable.Placeholder this$0;

            public final void onAnimationEnd(Animator animator)
            {
                animator = ExtendedBitmapDrawable.Placeholder.this;
                if (((ExtendedBitmapDrawable.Placeholder) (animator)).pulseAnimator != null)
                {
                    ((ExtendedBitmapDrawable.Placeholder) (animator)).pulseAnimator.cancel();
                    animator.pulseAlpha = 255;
                    animator.invalidateSelf();
                }
            }

            _cls2()
            {
                this$0 = ExtendedBitmapDrawable.Placeholder.this;
                super();
            }
        }

        if (ons.placeholderAnimationDuration == -1)
        {
            pulseAnimator = null;
        } else
        {
            class _cls1
                implements android.animation.ValueAnimator.AnimatorUpdateListener
            {

                private final ExtendedBitmapDrawable.Placeholder this$0;

                public final void onAnimationUpdate(ValueAnimator valueanimator)
                {
                    pulseAlpha = ((Integer)valueanimator.getAnimatedValue()).intValue();
                    invalidateSelf();
                }

            _cls1()
            {
                this$0 = ExtendedBitmapDrawable.Placeholder.this;
                super();
            }
            }

            long l;
            if (ons.placeholderAnimationDuration == 0)
            {
                l = resources.getInteger(0x7f110004);
            } else
            {
                l = ons.placeholderAnimationDuration;
            }
            pulseAnimator = ValueAnimator.ofInt(new int[] {
                55, 255
            }).setDuration(l);
            pulseAnimator.setRepeatCount(-1);
            pulseAnimator.setRepeatMode(2);
            pulseAnimator.addUpdateListener(new _cls1());
        }
        mFadeOutAnimator.addListener(new _cls2());
    }
}
