// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation;

import android.content.Context;
import android.view.Choreographer;
import android.widget.OverScroller;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;

final class OverScrollerFuture extends AbstractFuture
{

    public final ViewportAnimator.FlingAnimationListener listener;
    public final OverScroller overScroller;

    private OverScrollerFuture(OverScroller overscroller, ViewportAnimator.FlingAnimationListener flinganimationlistener, int i)
    {
        overScroller = overscroller;
        listener = flinganimationlistener;
        class .Lambda._cls0
            implements android.view.Choreographer.FrameCallback
        {

            private final OverScrollerFuture arg$1;
            private final int arg$2;
            private final int arg$3;

            public final void doFrame(long l)
            {
                OverScrollerFuture overscrollerfuture;
label0:
                {
                    overscrollerfuture = arg$1;
                    int j = arg$2;
                    int k = arg$3;
                    if (!overscrollerfuture.isCancelled())
                    {
                        if (!overscrollerfuture.overScroller.computeScrollOffset())
                        {
                            break label0;
                        }
                        int i1 = overscrollerfuture.overScroller.getCurrX();
                        overscrollerfuture.listener.scrollPx((i1 - k) * j);
                        Choreographer.getInstance().postFrameCallback(overscrollerfuture. new .Lambda._cls0(j, i1));
                    }
                    return;
                }
                overscrollerfuture.set(new Object());
            }

            .Lambda._cls0(int i, int j)
            {
                arg$1 = OverScrollerFuture.this;
                arg$2 = i;
                arg$3 = j;
            }
        }

        Choreographer.getInstance().postFrameCallback(new .Lambda._cls0(i, 0));
    }

    static Optional tryFling(Context context, IdleTracker idletracker, int i, int j, ViewportAnimator.FlingAnimationListener flinganimationlistener)
    {
        context = new OverScroller(context);
        context.fling(0, 0, Math.abs(i), 0, 0, Math.abs(j), 0, 0);
        if (context.getFinalX() >= Math.abs(j))
        {
            if (i < 0)
            {
                i = -1;
            } else
            {
                i = 1;
            }
            context = new OverScrollerFuture(context, flinganimationlistener, i);
            idletracker = idletracker.onEventBegin();
            idletracker.getClass();
            context.addListener(new com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker..Lambda._cls0(idletracker), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            if (context == null)
            {
                throw new NullPointerException();
            } else
            {
                return new Present(context);
            }
        } else
        {
            return Absent.INSTANCE;
        }
    }

    protected final void interruptTask()
    {
        overScroller.forceFinished(true);
    }
}
