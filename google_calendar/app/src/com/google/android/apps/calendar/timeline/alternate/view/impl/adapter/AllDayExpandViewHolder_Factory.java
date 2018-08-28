// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AllDayExpandViewHolder

public final class AllDayExpandViewHolder_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider expandAllDayObservableProvider;
    private final Provider expandButtonDrawableProvider;

    public AllDayExpandViewHolder_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        contextProvider = provider;
        expandAllDayObservableProvider = provider1;
        expandButtonDrawableProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = expandAllDayObservableProvider;
        Provider provider2 = expandButtonDrawableProvider;
        return new AllDayExpandViewHolder((Context)provider.get(), (ObservableReference)provider1.get(), (AllDayExpandViewHolder.ExpandButtonDrawable)provider2.get());
    }
}
