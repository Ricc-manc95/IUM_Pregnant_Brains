// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

final class val.visible extends AnimatorListenerAdapter
{

    private final val.visible this$1;
    private final boolean val$visible;

    public final void onAnimationEnd(Animator animator)
    {
        animator = _fld0;
        DatePickerVisibilityChangeDone(val$visible);
    }

    ()
    {
        this$1 = final_;
        val$visible = Z.this;
        super();
    }
}
