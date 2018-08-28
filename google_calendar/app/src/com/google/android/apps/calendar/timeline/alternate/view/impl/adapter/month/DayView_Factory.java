// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.month;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.month:
//            DayView

public final class DayView_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider dayDrawableProvider;

    public DayView_Factory(Provider provider, Provider provider1)
    {
        contextProvider = provider;
        dayDrawableProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = dayDrawableProvider;
        return new DayView((Context)provider.get(), provider1);
    }
}
