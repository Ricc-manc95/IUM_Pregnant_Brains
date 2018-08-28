// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month;

import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.ViewportAnimator;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month:
//            MonthViewport

public final class MonthViewportController
{

    public final MonthViewport viewport;
    public final ViewportAnimator viewportAnimator;

    public MonthViewportController(MonthViewport monthviewport, ViewportAnimator viewportanimator)
    {
        viewport = monthviewport;
        viewportAnimator = viewportanimator;
    }

    final FluentFuture animateDelta(float f)
    {
        double d = viewport.startFraction;
        class .Lambda._cls0
            implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.ViewportAnimator.ChangeAnimationListener
        {

            private final MonthViewportController arg$1;
            private final double arg$2;
            private final float arg$3;

            public final void onStep(float f1)
            {
                MonthViewportController monthviewportcontroller = arg$1;
                double d1 = arg$2;
                float f2 = arg$3;
                monthviewportcontroller.viewport.setFraction(d1 + (double)(f2 * f1));
            }

            .Lambda._cls0(double d, float f)
            {
                arg$1 = MonthViewportController.this;
                arg$2 = d;
                arg$3 = f;
            }
        }

        class .Lambda._cls1
            implements Function
        {

            private final MonthViewportController arg$1;

            public final Object apply(Object obj)
            {
                obj = arg$1.viewport;
                ((MonthViewport) (obj)).setFraction(Math.round(((MonthViewport) (obj)).startFraction));
                return new Object();
            }

            .Lambda._cls1()
            {
                arg$1 = MonthViewportController.this;
            }
        }

        if (f == 0.0F)
        {
            return null;
        } else
        {
            return (FluentFuture)AbstractTransformFuture.create(viewportAnimator.animateViewportChange(new .Lambda._cls0(d, f)), new .Lambda._cls1(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }
    }
}
