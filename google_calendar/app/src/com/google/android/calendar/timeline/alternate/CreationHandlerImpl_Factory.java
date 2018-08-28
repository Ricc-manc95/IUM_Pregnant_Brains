// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            CreationHandlerImpl

public final class CreationHandlerImpl_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider createEventPhantomProvider;
    private final Provider defaultCalendarColorProvider;
    private final Provider defaultDurationProvider;
    private final Provider timeUtilsProvider;

    public CreationHandlerImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4)
    {
        contextProvider = provider;
        timeUtilsProvider = provider1;
        defaultDurationProvider = provider2;
        createEventPhantomProvider = provider3;
        defaultCalendarColorProvider = provider4;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = timeUtilsProvider;
        Provider provider2 = defaultDurationProvider;
        Provider provider3 = createEventPhantomProvider;
        Provider provider4 = defaultCalendarColorProvider;
        return new CreationHandlerImpl((Context)provider.get(), (TimeUtils)provider1.get(), (ObservableReference)provider2.get(), (ObservableReference)provider3.get(), (ObservableReference)provider4.get());
    }
}
