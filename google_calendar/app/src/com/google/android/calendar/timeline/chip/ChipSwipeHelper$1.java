// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            ChipSwipeHelper

final class val.direction extends AnimatorListenerAdapter
{

    private final ChipSwipeHelper this$0;
    private final int val$direction;

    public final void onAnimationEnd(Animator animator)
    {
        ChipSwipeHelper.this.animator = null;
        boolean flag;
        if (_flddelegate != null && _flddelegate.onSwipeGestureComplete(chip, val$direction))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            animateRestore(null);
        }
    }

    legate()
    {
        this$0 = final_chipswipehelper;
        val$direction = I.this;
        super();
    }
}
