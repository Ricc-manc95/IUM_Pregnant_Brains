// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveScheduleFragment

final class val.imageView
    implements android.animation.
{

    private final View val$imageView;

    public final void onAnimationCancel(Animator animator)
    {
    }

    public final void onAnimationEnd(Animator animator)
    {
        if (val$imageView != null)
        {
            GrooveScheduleFragment.createImageEnterAnimator(val$imageView).start();
        }
    }

    public final void onAnimationRepeat(Animator animator)
    {
    }

    public final void onAnimationStart(Animator animator)
    {
    }

    A()
    {
        val$imageView = view;
        super();
    }
}
