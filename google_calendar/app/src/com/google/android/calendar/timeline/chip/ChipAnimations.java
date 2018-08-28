// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            ChipConstants, Chip, ChipViewModel

public final class ChipAnimations
{

    public static long calculateTranslationDuration(float f, Float float1)
    {
        if (float1 == null)
        {
            return 150L;
        }
        if (float1.floatValue() == 0.0F || float1.floatValue() * f < 0.0F)
        {
            return 300L;
        } else
        {
            return (long)Math.min((int)((1000F * f) / float1.floatValue()), 300);
        }
    }

    public static Animator createTranslationXSwipeAnimator(Chip chip, float f, long l)
    {
        chip = ObjectAnimator.ofFloat(chip, View.TRANSLATION_X, new float[] {
            f
        });
        chip.setDuration(l);
        chip.setInterpolator(ChipConstants.DECELERATE_INTERPOLATOR);
        if (!(chip.getTarget() instanceof Chip))
        {
            throw new IllegalArgumentException("Not an animator of TimelyChip");
        } else
        {
            class ChipInvalidator.InstanceHolder
            {

                public static final ChipInvalidator INSTANCE = new ChipInvalidator();


                private class ChipInvalidator
                    implements android.animation.ValueAnimator.AnimatorUpdateListener
                {

                    public final void onAnimationUpdate(ValueAnimator valueanimator)
                    {
                        ((Chip)((ObjectAnimator)valueanimator).getTarget()).invalidateIncludingFootprint();
                    }

                    ChipInvalidator()
                    {
                    }
                }

            }

            chip.addUpdateListener(ChipInvalidator.InstanceHolder.INSTANCE);
            return chip;
        }
    }

    public static Animator dismiss(Chip chip, Float float1, int i)
    {
        float f;
        boolean flag;
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Invalid direction");

        case 2: // '\002'
            break MISSING_BLOCK_LABEL_93;

        case 1: // '\001'
            if (!chip.viewModel.getIsRtl())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            break;
        }
_L1:
        if (flag)
        {
            f = ((View)chip.getParent()).getWidth() - chip.getLeft() - 1;
        } else
        {
            f = -chip.getRight() + 1;
        }
        return createTranslationXSwipeAnimator(chip, f, calculateTranslationDuration(f - chip.getTranslationX(), float1));
        flag = chip.viewModel.getIsRtl();
          goto _L1
    }
}
