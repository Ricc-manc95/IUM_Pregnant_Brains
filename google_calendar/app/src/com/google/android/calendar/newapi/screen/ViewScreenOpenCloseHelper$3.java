// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import com.google.android.calendar.timely.animations.EventInfoAnimationView;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            ViewScreenOpenCloseHelper, ViewScreenController

final class val.onAnimationEnd extends AnimatorListenerAdapter
{

    private final ViewScreenOpenCloseHelper this$0;
    private final Runnable val$onAnimationEnd;

    public final void onAnimationEnd(Animator animator)
    {
        controller.notifyAnimationFinished(val$onAnimationEnd);
    }

    public final void onAnimationStart(Animator animator)
    {
        super.onAnimationStart(animator);
        animationView.setVisibility(0);
        overlayView.setVisibility(0);
    }

    ()
    {
        this$0 = final_viewscreenopenclosehelper;
        val$onAnimationEnd = Runnable.this;
        super();
    }
}
