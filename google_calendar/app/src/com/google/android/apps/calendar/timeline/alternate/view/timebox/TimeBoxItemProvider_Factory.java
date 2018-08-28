// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.timebox;

import com.google.android.apps.calendar.timebox.EventsApi;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.timebox:
//            TimeBoxItemProvider

public final class TimeBoxItemProvider_Factory
    implements Factory
{

    private final Provider apiProvider;
    private final Provider hideDeclinedEventsProvider;

    public TimeBoxItemProvider_Factory(Provider provider, Provider provider1)
    {
        apiProvider = provider;
        hideDeclinedEventsProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = apiProvider;
        Provider provider1 = hideDeclinedEventsProvider;
        return new TimeBoxItemProvider((EventsApi)provider.get(), (ObservableReference)provider1.get());
    }
}
