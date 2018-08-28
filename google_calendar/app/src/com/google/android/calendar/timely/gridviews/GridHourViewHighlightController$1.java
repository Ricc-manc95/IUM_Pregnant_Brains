// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.SparseArray;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridHourViewHighlightController

final class val.minuteOfDay extends AnimatorListenerAdapter
{

    private final GridHourViewHighlightController this$0;
    private final int val$minuteOfDay;

    public final void onAnimationEnd(Animator animator)
    {
        if (val$minuteOfDay != lastHighlightedMinute)
        {
            highlightFadeAnimators.remove(val$minuteOfDay);
        }
    }

    ()
    {
        this$0 = final_gridhourviewhighlightcontroller;
        val$minuteOfDay = I.this;
        super();
    }
}
