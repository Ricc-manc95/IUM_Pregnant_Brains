// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timeline.chip.ChipAnimations;
import com.google.android.calendar.utils.animation.AnimationUtils;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyDayViewSwipeHelper, TimelineItem

final class val.endInteraction
    implements Runnable
{

    private final TimelyDayViewSwipeHelper this$0;
    private final Chip val$chip;
    private final int val$direction;
    private final Runnable val$endInteraction;
    private final TimelineItem val$item;

    public final void run()
    {
        Object obj = null;
        boolean flag;
        if (_flddelegate != null && _flddelegate._mth51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM7IH31F5B6IPBN7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM6IRJ595Q6AR9R94KLK___0(dayView, val$item))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            obj = val$chip;
            obj = ChipAnimations.createTranslationXSwipeAnimator(((Chip) (obj)), 0.0F, ChipAnimations.calculateTranslationDuration(-((Chip) (obj)).getTranslationX(), null));
        }
        AnimationUtils.animateThenRun(((android.animation.Animator) (obj)), val$endInteraction);
    }

    elegate()
    {
        this$0 = final_timelydayviewswipehelper;
        val$item = timelineitem;
        val$direction = i;
        val$chip = chip1;
        val$endInteraction = Runnable.this;
        super();
    }
}
