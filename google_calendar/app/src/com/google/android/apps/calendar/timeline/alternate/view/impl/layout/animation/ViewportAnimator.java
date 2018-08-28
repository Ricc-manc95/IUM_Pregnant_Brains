// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation:
//            OverScrollerFuture, ValueAnimatorFuture, AnimatorFuture

public final class ViewportAnimator
{

    private final Context context;
    public FluentFuture currentAnimator;
    private final IdleTracker idleTracker;
    private final Provider valueAnimatorFutureProvider;

    public ViewportAnimator(Context context1, Provider provider, IdleTracker idletracker)
    {
        context = context1;
        valueAnimatorFutureProvider = provider;
        idleTracker = idletracker;
    }

    public final Optional animateFling(int i, int j, FlingAnimationListener flinganimationlistener)
    {
        if (currentAnimator != null)
        {
            currentAnimator.cancel(true);
            currentAnimator = null;
        }
        flinganimationlistener = OverScrollerFuture.tryFling(context, idleTracker, i, j, flinganimationlistener);
        if (flinganimationlistener.isPresent())
        {
            currentAnimator = (FluentFuture)flinganimationlistener.get();
            flinganimationlistener = (ListenableFuture)flinganimationlistener.get();
            if (flinganimationlistener instanceof FluentFuture)
            {
                flinganimationlistener = (FluentFuture)flinganimationlistener;
            } else
            {
                flinganimationlistener = new ForwardingFluentFuture(flinganimationlistener);
            }
            if (flinganimationlistener == null)
            {
                throw new NullPointerException();
            } else
            {
                return new Present(flinganimationlistener);
            }
        } else
        {
            return Absent.INSTANCE;
        }
    }

    public final FluentFuture animateViewportChange(ChangeAnimationListener changeanimationlistener)
    {
        if (currentAnimator != null)
        {
            currentAnimator.cancel(true);
            currentAnimator = null;
        }
        ValueAnimatorFuture valueanimatorfuture = (ValueAnimatorFuture)valueAnimatorFutureProvider.get();
        ((ValueAnimator)((AnimatorFuture) (valueanimatorfuture)).animator).addUpdateListener(new ValueAnimatorFuture..Lambda._cls0(valueanimatorfuture, changeanimationlistener));
        changeanimationlistener = valueanimatorfuture.start();
        currentAnimator = changeanimationlistener;
        return changeanimationlistener;
    }
}
