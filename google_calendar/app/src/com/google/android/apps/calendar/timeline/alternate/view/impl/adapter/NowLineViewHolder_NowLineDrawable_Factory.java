// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NowLineViewHolder_NowLineDrawable_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider isRtlProvider;
    private final Provider layoutDimensProvider;

    public NowLineViewHolder_NowLineDrawable_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        contextProvider = provider;
        layoutDimensProvider = provider1;
        isRtlProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = layoutDimensProvider;
        Provider provider2 = isRtlProvider;
        return new NowLineViewHolder.NowLineDrawable((Context)provider.get(), (LayoutDimens)provider1.get(), (ObservableReference)provider2.get());
    }
}
