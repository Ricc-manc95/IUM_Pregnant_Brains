// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.animation.ObjectAnimator;
import com.google.android.calendar.utils.animation.QuantumInterpolators;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            Chip

final class ChipSwipeData
{

    public boolean aboveThreshold;
    public int footprintMaskAlpha;
    public final ObjectAnimator iconAnimator;
    public float iconScaleAddend;
    public final ObjectAnimator rippleAnimator;
    public float rippleRadius;

    ChipSwipeData(Chip chip)
    {
        aboveThreshold = false;
        rippleRadius = 0.0F;
        iconScaleAddend = 0.0F;
        footprintMaskAlpha = 0;
        rippleAnimator = ObjectAnimator.ofFloat(chip, RippleRadius.PROPERTY, new float[] {
            0.0F, 1.0F
        });
        rippleAnimator.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
        ObjectAnimator objectanimator = rippleAnimator;
        if (!(objectanimator.getTarget() instanceof Chip))
        {
            throw new IllegalArgumentException("Not an animator of TimelyChip");
        } else
        {
            objectanimator.addUpdateListener(ChipAnimations.ChipInvalidator.InstanceHolder.INSTANCE);
            iconAnimator = ObjectAnimator.ofFloat(chip, IconScaleAddend.PROPERTY, new float[] {
                0.0F, 0.3F
            });
            footprintMaskAlpha = 0;
            return;
        }
    }

    final void dispose()
    {
        iconAnimator.removeAllUpdateListeners();
        iconAnimator.removeAllListeners();
        iconAnimator.cancel();
        rippleAnimator.removeAllUpdateListeners();
        rippleAnimator.removeAllListeners();
        rippleAnimator.cancel();
    }

    private class RippleRadius extends Property
    {

        public static final RippleRadius PROPERTY = new RippleRadius();

        public final Object get(Object obj)
        {
            return Float.valueOf(((Chip)obj).swipeData.rippleRadius);
        }

        public final void set(Object obj, Object obj1)
        {
            obj = (Chip)obj;
            obj1 = (Float)obj1;
            ((Chip) (obj)).swipeData.rippleRadius = ((Float) (obj1)).floatValue();
        }


        private RippleRadius()
        {
            super(java/lang/Float, "swipeData.rippleRadius");
        }
    }


    private class IconScaleAddend extends Property
    {

        public static final IconScaleAddend PROPERTY = new IconScaleAddend();

        public final Object get(Object obj)
        {
            return Float.valueOf(((Chip)obj).swipeData.iconScaleAddend);
        }

        public final void set(Object obj, Object obj1)
        {
            obj = (Chip)obj;
            obj1 = (Float)obj1;
            ((Chip) (obj)).swipeData.iconScaleAddend = ((Float) (obj1)).floatValue();
        }


        private IconScaleAddend()
        {
            super(java/lang/Float, "swipeData.iconScaleAddend");
        }
    }

}
