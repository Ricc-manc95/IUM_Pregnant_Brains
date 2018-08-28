// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.animation.Animator;
import android.view.animation.Interpolator;

// Referenced classes of package android.support.v4.widget:
//            CircularProgressDrawable

final class ng
    implements android.animation.wable._cls2
{

    private final CircularProgressDrawable this$0;
    private final ng val$ring;

    public final void onAnimationCancel(Animator animator)
    {
    }

    public final void onAnimationEnd(Animator animator)
    {
    }

    public final void onAnimationRepeat(Animator animator)
    {
        Object obj = CircularProgressDrawable.this;
        ng ng = val$ring;
        if (((CircularProgressDrawable) (obj)).mFinishing)
        {
            ((CircularProgressDrawable) (obj)).updateRingColor(1.0F, ng);
            float f = (float)(Math.floor(ng.mStartingRotation / 0.8F) + 1.0D);
            ng.mStartTrim = ng.mStartingStartTrim + (ng.mStartingEndTrim - 0.01F - ng.mStartingStartTrim) * 1.0F;
            ng.mEndTrim = ng.mStartingEndTrim;
            float f2 = ng.mStartingRotation;
            ng.mRotation = (f - ng.mStartingRotation) * 1.0F + f2;
        } else
        {
            if (1.0F != 1.0F);
            float f4 = ng.mStartingRotation;
            float f1;
            float f3;
            float f5;
            if (1.0F < 0.5F)
            {
                f1 = 1.0F / 0.5F;
                f3 = ng.mStartingStartTrim;
                f1 = CircularProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation(f1) * 0.79F + 0.01F + f3;
            } else
            {
                f3 = (1.0F - 0.5F) / 0.5F;
                f1 = ng.mStartingStartTrim + 0.79F;
                f3 = f1 - ((1.0F - CircularProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation(f3)) * 0.79F + 0.01F);
            }
            f5 = ((CircularProgressDrawable) (obj)).mRotationCount;
            ng.mStartTrim = f3;
            ng.mEndTrim = f1;
            ng.mRotation = f4 + 0.21F * 1.0F;
            obj.mRotation = 216F * (f5 + 1.0F);
        }
        obj = val$ring;
        obj.mStartingStartTrim = ((ng) (obj)).mStartTrim;
        obj.mStartingEndTrim = ((ng) (obj)).mEndTrim;
        obj.mStartingRotation = ((ng) (obj)).mRotation;
        obj = val$ring;
        obj.mColorIndex = (((ng) (obj)).mColorIndex + 1) % ((ng) (obj)).mColors.length;
        obj.mCurrentColor = ((ng) (obj)).mColors[((ng) (obj)).mColorIndex];
        if (mFinishing)
        {
            mFinishing = false;
            animator.cancel();
            animator.setDuration(1332L);
            animator.start();
            animator = val$ring;
            if (((ng) (animator)).mShowArrow)
            {
                animator.mShowArrow = false;
            }
            return;
        } else
        {
            mRotationCount = mRotationCount + 1.0F;
            return;
        }
    }

    public final void onAnimationStart(Animator animator)
    {
        mRotationCount = 0.0F;
    }

    ng()
    {
        this$0 = final_circularprogressdrawable;
        val$ring = ng.this;
        super();
    }
}
