// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation;

import android.animation.Animator;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;
import com.google.common.util.concurrent.AbstractFuture;

public abstract class AnimatorFuture extends AbstractFuture
{

    public final Animator animator;
    public boolean started;

    AnimatorFuture(final IdleTracker idleTracker, Animator animator1)
    {
        boolean flag = false;
        super();
        started = false;
        if (!animator1.isStarted())
        {
            flag = true;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            animator = animator1;
            animator1.addListener(new _cls1());
            return;
        }
    }

    public abstract AnimatorFuture cast();

    protected final void interruptTask()
    {
        animator.cancel();
    }

    public final AnimatorFuture start()
    {
        boolean flag1 = true;
        boolean flag;
        if (!isDone())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        if (!started)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            animator.start();
            return cast();
        }
    }

    private class _cls1 extends AnimatorListenerAdapter
    {

        private final AnimatorFuture this$0;
        private final IdleTracker val$idleTracker;

        public final void onAnimationCancel(Animator animator1)
        {
            if (!isCancelled())
            {
                throw new IllegalStateException();
            } else
            {
                return;
            }
        }

        public final void onAnimationEnd(Animator animator1)
        {
            set(new Object());
        }

        public final void onAnimationStart(Animator animator1)
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
                Object obj = idleTracker;
                animator1 = AnimatorFuture.this;
                obj = ((IdleTracker) (obj)).onEventBegin();
                obj.getClass();
                animator1.addListener(new com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker..Lambda._cls0(((com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker.OnGoingEvent) (obj))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                return;
            }
        }

        _cls1()
        {
            this$0 = AnimatorFuture.this;
            idleTracker = idletracker;
            super();
        }
    }

}
