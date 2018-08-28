// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation:
//            ViewportAnimator

public final class ViewportAnimator_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider idleTrackerProvider;
    private final Provider valueAnimatorFutureProvider;

    public ViewportAnimator_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        contextProvider = provider;
        valueAnimatorFutureProvider = provider1;
        idleTrackerProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = valueAnimatorFutureProvider;
        Provider provider2 = idleTrackerProvider;
        return new ViewportAnimator((Context)provider.get(), provider1, (IdleTracker)provider2.get());
    }
}
