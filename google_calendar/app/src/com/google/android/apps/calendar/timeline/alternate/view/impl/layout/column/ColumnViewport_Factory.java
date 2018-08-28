// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnViewport

public final class ColumnViewport_Factory
    implements Factory
{

    private final Provider isRtlProvider;
    private final Provider timeUtilsProvider;

    public ColumnViewport_Factory(Provider provider, Provider provider1)
    {
        timeUtilsProvider = provider;
        isRtlProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = timeUtilsProvider;
        Provider provider1 = isRtlProvider;
        return new ColumnViewport((TimeUtils)provider.get(), (ObservableReference)provider1.get());
    }
}
