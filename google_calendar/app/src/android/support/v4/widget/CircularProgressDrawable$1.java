// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.animation.ValueAnimator;
import android.view.animation.Interpolator;

// Referenced classes of package android.support.v4.widget:
//            CircularProgressDrawable

final class ng
    implements android.animation.eListener
{

    private final CircularProgressDrawable this$0;
    private final ng val$ring;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        float f4;
        ng ng;
        f4 = ((Float)valueanimator.getAnimatedValue()).floatValue();
        updateRingColor(f4, val$ring);
        valueanimator = CircularProgressDrawable.this;
        ng = val$ring;
        if (!((CircularProgressDrawable) (valueanimator)).mFinishing) goto _L2; else goto _L1
_L1:
        valueanimator.updateRingColor(f4, ng);
        float f = (float)(Math.floor(ng.mStartingRotation / 0.8F) + 1.0D);
        ng.mStartTrim = ng.mStartingStartTrim + (ng.mStartingEndTrim - 0.01F - ng.mStartingStartTrim) * f4;
        ng.mEndTrim = ng.mStartingEndTrim;
        float f2 = ng.mStartingRotation;
        ng.mRotation = (f - ng.mStartingRotation) * f4 + f2;
_L4:
        invalidateSelf();
        return;
_L2:
        if (f4 != 1.0F)
        {
            float f5 = ng.mStartingRotation;
            float f1;
            float f3;
            float f6;
            if (f4 < 0.5F)
            {
                f1 = f4 / 0.5F;
                f3 = ng.mStartingStartTrim;
                f1 = CircularProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation(f1) * 0.79F + 0.01F + f3;
            } else
            {
                f3 = (f4 - 0.5F) / 0.5F;
                f1 = ng.mStartingStartTrim + 0.79F;
                f3 = f1 - ((1.0F - CircularProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation(f3)) * 0.79F + 0.01F);
            }
            f6 = ((CircularProgressDrawable) (valueanimator)).mRotationCount;
            ng.mStartTrim = f3;
            ng.mEndTrim = f1;
            ng.mRotation = f5 + 0.21F * f4;
            valueanimator.mRotation = (f4 + f6) * 216F;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    ng()
    {
        this$0 = final_circularprogressdrawable;
        val$ring = ng.this;
        super();
    }
}
