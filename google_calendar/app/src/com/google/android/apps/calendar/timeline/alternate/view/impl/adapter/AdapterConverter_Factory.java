// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterConverter

public final class AdapterConverter_Factory
    implements Factory
{

    private final Provider adapterProvider;
    private final Provider timeUtilsProvider;

    public AdapterConverter_Factory(Provider provider, Provider provider1)
    {
        timeUtilsProvider = provider;
        adapterProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = timeUtilsProvider;
        Provider provider1 = adapterProvider;
        return new AdapterConverter((TimeUtils)provider.get(), (ItemAdapter)provider1.get());
    }
}
