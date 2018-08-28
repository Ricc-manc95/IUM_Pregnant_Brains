// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AllDayExpandViewHolder_ExpandButtonDrawable_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider dimensConverterProvider;
    private final Provider screenTypeProvider;

    public AllDayExpandViewHolder_ExpandButtonDrawable_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        contextProvider = provider;
        dimensConverterProvider = provider1;
        screenTypeProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = dimensConverterProvider;
        Provider provider2 = screenTypeProvider;
        return new AllDayExpandViewHolder.ExpandButtonDrawable((Context)provider.get(), (DimensConverter)provider1.get(), (ObservableReference)provider2.get());
    }
}
