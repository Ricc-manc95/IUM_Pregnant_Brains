// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            ItemTransformer

public final class ItemTransformer_Factory
    implements Factory
{

    private final Provider adapterProvider;
    private final Provider timeUtilsProvider;

    public ItemTransformer_Factory(Provider provider, Provider provider1)
    {
        adapterProvider = provider;
        timeUtilsProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = adapterProvider;
        Provider provider1 = timeUtilsProvider;
        return new ItemTransformer((ItemAdapter)provider.get(), (TimeUtils)provider1.get());
    }
}
