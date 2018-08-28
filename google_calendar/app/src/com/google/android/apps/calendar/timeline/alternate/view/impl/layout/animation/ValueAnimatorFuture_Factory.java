// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation;

import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation:
//            ValueAnimatorFuture

public final class ValueAnimatorFuture_Factory
    implements Factory
{

    private final Provider idleTrackerProvider;

    public ValueAnimatorFuture_Factory(Provider provider)
    {
        idleTrackerProvider = provider;
    }

    public final Object get()
    {
        return new ValueAnimatorFuture((IdleTracker)idleTrackerProvider.get());
    }
}
