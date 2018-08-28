// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.animation.Animator;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            Chip, ChipAnimations, ChipViewModel

public final class ChipSwipeHelper
{

    public Animator animator;
    public final Chip chip;
    public Delegate _flddelegate;
    public int directions;
    public float initialTouchX;
    public float initialTouchY;
    public final int maxFlingVelocity;
    public final int minFlingVelocity;
    public int recognitionStatus;
    public final int swipeThreshold;
    public final int touchSlop;
    public VelocityTracker velocityTracker;

    ChipSwipeHelper(Chip chip1, int i, ViewConfiguration viewconfiguration)
    {
        directions = 0;
        recognitionStatus = 2;
        _flddelegate = null;
        chip = chip1;
        touchSlop = viewconfiguration.getScaledTouchSlop();
        minFlingVelocity = viewconfiguration.getScaledMinimumFlingVelocity();
        maxFlingVelocity = viewconfiguration.getScaledMaximumFlingVelocity();
        swipeThreshold = i;
    }

    final void animateRestore(Float float1)
    {
        Chip chip1 = chip;
        float1 = ChipAnimations.createTranslationXSwipeAnimator(chip1, 0.0F, ChipAnimations.calculateTranslationDuration(-chip1.getTranslationX(), float1));
        float1.addListener(new _cls2());
        if (animator != null && animator.isRunning())
        {
            animator.end();
        }
        animator = float1;
        animator.start();
    }

    final int getDirectionsFromTranslation(float f)
    {
        if (f <= 0.0F) goto _L2; else goto _L1
_L1:
        if (!chip.viewModel.getIsRtl()) goto _L4; else goto _L3
_L3:
        return 2;
_L4:
        return 1;
_L2:
        if (f < 0.0F)
        {
            if (chip.viewModel.getIsRtl())
            {
                return 1;
            }
        } else
        {
            return 3;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    private class _cls2 extends AnimatorListenerAdapter
    {

        private final ChipSwipeHelper this$0;

        public final void onAnimationEnd(Animator animator1)
        {
            animator = null;
            if (_flddelegate != null)
            {
                _flddelegate.onSwipeGestureCancel(chip);
            }
        }

        _cls2()
        {
            this$0 = ChipSwipeHelper.this;
            super();
        }

        private class Delegate
        {

            public abstract boolean isSwipeEnabled();

            public abstract boolean isSwipePossible();

            public abstract void onSwipeGestureCancel(Chip chip1);

            public abstract boolean onSwipeGestureComplete(Chip chip1, int i);

            public abstract void onSwipeGestureStart(Chip chip1);
        }

    }

}
