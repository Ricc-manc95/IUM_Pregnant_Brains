// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import com.google.android.apps.calendar.timeline.alternate.fragment.api.AlternateTimelineChipViewModelFactory;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.ChipClickListener;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.SwipeHandler;
import com.google.android.apps.calendar.timeline.alternate.view.api.TimelineApi;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.timeline.chip.ChipFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.fragment.impl:
//            ChipItemViewFactory

public final class ChipItemViewFactory_Factory
    implements Factory
{

    private final Provider chipFactoryProvider;
    private final Provider clickListenerProvider;
    private final Provider idleProvider;
    private final Provider isTalkBackEnabledProvider;
    private final Provider screenTypeProvider;
    private final Provider swipeHandlerProvider;
    private final Provider timelineApiProvider;
    private final Provider viewModelFactoryProvider;

    public ChipItemViewFactory_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7)
    {
        timelineApiProvider = provider;
        chipFactoryProvider = provider1;
        clickListenerProvider = provider2;
        viewModelFactoryProvider = provider3;
        screenTypeProvider = provider4;
        isTalkBackEnabledProvider = provider5;
        swipeHandlerProvider = provider6;
        idleProvider = provider7;
    }

    public final Object get()
    {
        Provider provider = timelineApiProvider;
        Provider provider1 = chipFactoryProvider;
        Provider provider2 = clickListenerProvider;
        Provider provider3 = viewModelFactoryProvider;
        Provider provider4 = screenTypeProvider;
        Provider provider5 = isTalkBackEnabledProvider;
        Provider provider6 = swipeHandlerProvider;
        Provider provider7 = idleProvider;
        return new ChipItemViewFactory((TimelineApi)provider.get(), (ChipFactory)provider1.get(), (ChipClickListener)provider2.get(), (AlternateTimelineChipViewModelFactory)provider3.get(), (ObservableReference)provider4.get(), (ObservableReference)provider5.get(), (SwipeHandler)provider6.get(), (ObservableReference)provider7.get());
    }
}
