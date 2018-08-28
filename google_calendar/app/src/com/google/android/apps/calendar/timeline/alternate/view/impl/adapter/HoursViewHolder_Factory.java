// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.HoursDrawable;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            HoursViewHolder

public final class HoursViewHolder_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider hoursDrawableProvider;

    public HoursViewHolder_Factory(Provider provider, Provider provider1)
    {
        contextProvider = provider;
        hoursDrawableProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = hoursDrawableProvider;
        return new HoursViewHolder((Context)provider.get(), (HoursDrawable)provider1.get());
    }
}
