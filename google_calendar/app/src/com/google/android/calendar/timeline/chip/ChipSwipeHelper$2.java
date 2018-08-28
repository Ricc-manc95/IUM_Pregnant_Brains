// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            ChipSwipeHelper

final class this._cls0 extends AnimatorListenerAdapter
{

    private final ChipSwipeHelper this$0;

    public final void onAnimationEnd(Animator animator)
    {
        ChipSwipeHelper.this.animator = null;
        if (_flddelegate != null)
        {
            _flddelegate.onSwipeGestureCancel(chip);
        }
    }

    legate()
    {
        this$0 = ChipSwipeHelper.this;
        super();
    }
}
