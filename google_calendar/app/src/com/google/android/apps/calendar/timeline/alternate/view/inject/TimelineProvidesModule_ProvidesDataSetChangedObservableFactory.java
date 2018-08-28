// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.inject;

import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapter;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.inject:
//            TimelineProvidesModule

public final class TimelineProvidesModule_ProvidesDataSetChangedObservableFactory
    implements Factory
{

    private final Provider adapterProvider;
    private final TimelineProvidesModule module;

    public TimelineProvidesModule_ProvidesDataSetChangedObservableFactory(TimelineProvidesModule timelineprovidesmodule, Provider provider)
    {
        module = timelineprovidesmodule;
        adapterProvider = provider;
    }

    public final Object get()
    {
        Object obj = module;
        obj = ((TimelineAdapter)adapterProvider.get()).getDataSetChangedObservable();
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ObservableReference)obj;
        }
    }
}
