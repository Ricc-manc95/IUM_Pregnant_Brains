// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package com.google.android.calendar:
//            SpeedDialLayout

final class t> extends AnimatorListenerAdapter
{

    private final SpeedDialLayout this$0;

    public final void onAnimationEnd(Animator animator)
    {
        currentAnimator = null;
    }

    public final void onAnimationStart(Animator animator)
    {
        setVisibility(0);
    }

    ()
    {
        this$0 = SpeedDialLayout.this;
        super();
    }
}
