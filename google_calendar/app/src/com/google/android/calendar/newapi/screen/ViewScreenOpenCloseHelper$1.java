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

final class this._cls0 extends AnimatorListenerAdapter
{

    private final ViewScreenOpenCloseHelper this$0;

    public final void onAnimationEnd(Animator animator)
    {
        controller.notifyAnimationFinished(null);
    }

    public final void onAnimationStart(Animator animator)
    {
        rootView.setBackgroundResource(0x106000d);
        animationView.setVisibility(0);
    }

    ()
    {
        this$0 = ViewScreenOpenCloseHelper.this;
        super();
    }
}
