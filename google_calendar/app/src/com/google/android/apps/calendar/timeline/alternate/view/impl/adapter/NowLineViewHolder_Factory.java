// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            NowLineViewHolder

public final class NowLineViewHolder_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider nowLineDrawableProvider;

    public NowLineViewHolder_Factory(Provider provider, Provider provider1)
    {
        contextProvider = provider;
        nowLineDrawableProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = nowLineDrawableProvider;
        return new NowLineViewHolder((Context)provider.get(), (NowLineViewHolder.NowLineDrawable)provider1.get());
    }
}
