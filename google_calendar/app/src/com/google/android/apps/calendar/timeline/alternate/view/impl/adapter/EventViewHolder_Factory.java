// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.view.api.CreationMode;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemViewFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            EventViewHolder, TimelineAdapter

public final class EventViewHolder_Factory
    implements Factory
{

    private final Provider creationModeProvider;
    private final Provider itemViewFactoryProvider;
    private final Provider timelineAdapterProvider;

    public EventViewHolder_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        itemViewFactoryProvider = provider;
        creationModeProvider = provider1;
        timelineAdapterProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = itemViewFactoryProvider;
        Provider provider1 = creationModeProvider;
        Provider provider2 = timelineAdapterProvider;
        return new EventViewHolder((ItemViewFactory)provider.get(), (CreationMode)provider1.get(), (TimelineAdapter)provider2.get());
    }
}
