// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation:
//            AnimatorFuture

final class val.idleTracker extends AnimatorListenerAdapter
{

    private final AnimatorFuture this$0;
    private final IdleTracker val$idleTracker;

    public final void onAnimationCancel(Animator animator)
    {
        if (!isCancelled())
        {
            throw new IllegalStateException();
        } else
        {
            return;
        }
    }

    public final void onAnimationEnd(Animator animator)
    {
        set(new Object());
    }

    public final void onAnimationStart(Animator animator)
    {
        boolean flag;
        if (!started)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            started = true;
            Object obj = val$idleTracker;
            animator = AnimatorFuture.this;
            obj = ((IdleTracker) (obj)).onEventBegin();
            obj.getClass();
            animator.addListener(new com.google.android.apps.calendar.timeline.alternate.view.impl.util.da._cls0(((com.google.android.apps.calendar.timeline.alternate.view.impl.util.ngEvent) (obj))), com.google.common.util.concurrent.ectExecutor.INSTANCE);
            return;
        }
    }

    ()
    {
        this$0 = final_animatorfuture;
        val$idleTracker = IdleTracker.this;
        super();
    }
}
