// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.timeline.alternate.fragment.api.AlternateTimelineChipViewModelFactory;
import com.google.android.calendar.timebox.adapter.TimeBoxToTimelineAdapter;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfoFactory;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipViewModelFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AllInOneActivityModule_ProvidesChipViewModelFactoryFactory
    implements Factory
{

    private final Provider chipFragmentInfoFactoryProvider;
    private final Provider chipViewModelFactoryProvider;
    private final Provider timeBoxToTimelineAdapterProvider;

    public AllInOneActivityModule_ProvidesChipViewModelFactoryFactory(Provider provider, Provider provider1, Provider provider2)
    {
        timeBoxToTimelineAdapterProvider = provider;
        chipViewModelFactoryProvider = provider1;
        chipFragmentInfoFactoryProvider = provider2;
    }

    public final Object get()
    {
        Object obj1 = timeBoxToTimelineAdapterProvider;
        Object obj = chipViewModelFactoryProvider;
        Provider provider = chipFragmentInfoFactoryProvider;
        obj1 = (TimeBoxToTimelineAdapter)((Provider) (obj1)).get();
        obj = new AllInOneActivityModule..Lambda._cls0((ChipViewModelFactory)((Provider) (obj)).get(), ((TimeBoxToTimelineAdapter) (obj1)), (ChipFragmentInfoFactory)provider.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (AlternateTimelineChipViewModelFactory)obj;
        }
    }
}
