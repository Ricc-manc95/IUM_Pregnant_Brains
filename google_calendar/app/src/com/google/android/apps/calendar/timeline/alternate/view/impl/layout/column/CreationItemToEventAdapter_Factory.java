// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            CreationItemToEventAdapter

public final class CreationItemToEventAdapter_Factory
    implements Factory
{

    private final Provider adapterProvider;
    private final Provider defaultDurationProvider;
    private final Provider timeUtilsProvider;

    public CreationItemToEventAdapter_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        adapterProvider = provider;
        timeUtilsProvider = provider1;
        defaultDurationProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = adapterProvider;
        Provider provider1 = timeUtilsProvider;
        Provider provider2 = defaultDurationProvider;
        return new CreationItemToEventAdapter((ItemAdapter)provider.get(), (TimeUtils)provider1.get(), (ObservableReference)provider2.get());
    }
}
