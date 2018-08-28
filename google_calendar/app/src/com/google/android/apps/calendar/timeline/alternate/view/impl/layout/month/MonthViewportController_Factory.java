// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month;

import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.ViewportAnimator;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month:
//            MonthViewportController, MonthViewport

public final class MonthViewportController_Factory
    implements Factory
{

    private final Provider viewportAnimatorProvider;
    private final Provider viewportProvider;

    public MonthViewportController_Factory(Provider provider, Provider provider1)
    {
        viewportProvider = provider;
        viewportAnimatorProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = viewportProvider;
        Provider provider1 = viewportAnimatorProvider;
        return new MonthViewportController((MonthViewport)provider.get(), (ViewportAnimator)provider1.get());
    }
}
