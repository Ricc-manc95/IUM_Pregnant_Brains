// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.content.Context;
import android.graphics.Point;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnBackgroundDrawable, ColumnDimens, ColumnViewport

public final class ColumnBackgroundDrawable_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider currentTimeProvider;
    private final Provider dimensConverterProvider;
    private final Provider dimensProvider;
    private final Provider gridOffsetProvider;
    private final Provider isPortraitProvider;
    private final Provider isRtlProvider;
    private final Provider layoutDimensProvider;
    private final Provider screenTypeProvider;
    private final Provider shouldShowWeekNumbersProvider;
    private final Provider timeUtilsProvider;
    private final Provider viewportProvider;

    public ColumnBackgroundDrawable_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11)
    {
        contextProvider = provider;
        dimensConverterProvider = provider1;
        layoutDimensProvider = provider2;
        dimensProvider = provider3;
        timeUtilsProvider = provider4;
        viewportProvider = provider5;
        currentTimeProvider = provider6;
        gridOffsetProvider = provider7;
        screenTypeProvider = provider8;
        isPortraitProvider = provider9;
        isRtlProvider = provider10;
        shouldShowWeekNumbersProvider = provider11;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = dimensConverterProvider;
        Provider provider2 = layoutDimensProvider;
        Provider provider3 = dimensProvider;
        Provider provider4 = timeUtilsProvider;
        Provider provider5 = viewportProvider;
        Provider provider6 = currentTimeProvider;
        Provider provider7 = gridOffsetProvider;
        Provider provider8 = screenTypeProvider;
        Provider provider9 = isPortraitProvider;
        Provider provider10 = isRtlProvider;
        Provider provider11 = shouldShowWeekNumbersProvider;
        return new ColumnBackgroundDrawable((Context)provider.get(), (DimensConverter)provider1.get(), (LayoutDimens)provider2.get(), (ColumnDimens)provider3.get(), (TimeUtils)provider4.get(), (ColumnViewport)provider5.get(), (ObservableReference)provider6.get(), (Point)provider7.get(), (ObservableReference)provider8.get(), (ObservableReference)provider9.get(), (ObservableReference)provider10.get(), (ObservableReference)provider11.get());
    }
}
