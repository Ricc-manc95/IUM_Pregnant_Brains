// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            SwipeHandlerImpl

public final class SwipeHandlerImpl_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider storeProvider;

    public SwipeHandlerImpl_Factory(Provider provider, Provider provider1)
    {
        contextProvider = provider;
        storeProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = storeProvider;
        return new SwipeHandlerImpl((Context)provider.get(), (CalendarContentStore)provider1.get());
    }
}
