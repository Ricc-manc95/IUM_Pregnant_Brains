// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            Chip

public final class val.chip extends AnimatorListenerAdapter
{

    private final Chip val$chip;

    public final void onAnimationEnd(Animator animator)
    {
        val$chip.setVisibility(4);
    }

    public ()
    {
        val$chip = chip1;
        super();
    }
}
