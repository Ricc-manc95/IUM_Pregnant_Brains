// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package com.google.android.calendar.timely.interaction:
//            InteractionTracker

public final class val.target extends AnimatorListenerAdapter
{

    private final InteractionTracker this$0;
    private final Object val$controller;
    private final Object val$target;

    public final void onAnimationEnd(Animator animator)
    {
        trackInteractionEnd(val$controller, val$target);
    }

    public final void onAnimationStart(Animator animator)
    {
        trackInteractionStart(val$controller, val$target);
    }

    public I()
    {
        this$0 = final_interactiontracker;
        val$controller = obj;
        val$target = Object.this;
        super();
    }
}
