// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.inject;

import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.inject:
//            TimelineProvidesModule

public final class TimelineProvidesModule_ProvidesIdleObservableFactory
    implements Factory
{

    private final Provider idleTrackerProvider;
    private final TimelineProvidesModule module;

    public TimelineProvidesModule_ProvidesIdleObservableFactory(TimelineProvidesModule timelineprovidesmodule, Provider provider)
    {
        module = timelineprovidesmodule;
        idleTrackerProvider = provider;
    }

    public final Object get()
    {
        Object obj = module;
        obj = ((IdleTracker)idleTrackerProvider.get()).observable;
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ObservableReference)obj;
        }
    }
}