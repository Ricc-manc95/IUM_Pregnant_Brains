// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            ViewScreen

final class val.onAnimationEnd extends AnimatorListenerAdapter
{

    private final ViewScreen this$0;
    private final Runnable val$onAnimationEnd;

    public final void onAnimationEnd(Animator animator)
    {
        contentView.requestLayout();
        if (val$onAnimationEnd != null)
        {
            val$onAnimationEnd.run();
        }
    }

    ()
    {
        this$0 = final_viewscreen;
        val$onAnimationEnd = Runnable.this;
        super();
    }
}
