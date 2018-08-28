// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnDimens

public final class ColumnDimens_Factory
    implements Factory
{

    private final Provider dimensConverterProvider;
    private final Provider isPortraitProvider;
    private final Provider layoutDimensProvider;
    private final Provider screenTypeProvider;

    public ColumnDimens_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3)
    {
        screenTypeProvider = provider;
        isPortraitProvider = provider1;
        dimensConverterProvider = provider2;
        layoutDimensProvider = provider3;
    }

    public final Object get()
    {
        Provider provider = screenTypeProvider;
        Provider provider1 = isPortraitProvider;
        Provider provider2 = dimensConverterProvider;
        Provider provider3 = layoutDimensProvider;
        return new ColumnDimens((ObservableReference)provider.get(), (ObservableReference)provider1.get(), (DimensConverter)provider2.get(), (LayoutDimens)provider3.get());
    }
}
