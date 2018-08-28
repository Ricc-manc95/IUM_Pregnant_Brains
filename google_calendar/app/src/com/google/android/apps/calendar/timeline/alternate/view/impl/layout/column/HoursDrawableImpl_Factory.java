// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.content.Context;
import android.graphics.Point;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            HoursDrawableImpl, ColumnDimens, ColumnViewport, DragGuideManager

public final class HoursDrawableImpl_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider dimensConverterProvider;
    private final Provider dimensProvider;
    private final Provider dragGuideManagerProvider;
    private final Provider gridOffsetProvider;
    private final Provider isRtlProvider;
    private final Provider timeUtilsProvider;
    private final Provider viewportProvider;

    public HoursDrawableImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7)
    {
        contextProvider = provider;
        isRtlProvider = provider1;
        gridOffsetProvider = provider2;
        dimensProvider = provider3;
        viewportProvider = provider4;
        dimensConverterProvider = provider5;
        dragGuideManagerProvider = provider6;
        timeUtilsProvider = provider7;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = isRtlProvider;
        Provider provider2 = gridOffsetProvider;
        Provider provider3 = dimensProvider;
        Provider provider4 = viewportProvider;
        Provider provider5 = dimensConverterProvider;
        Provider provider6 = dragGuideManagerProvider;
        Provider provider7 = timeUtilsProvider;
        return new HoursDrawableImpl((Context)provider.get(), (ObservableReference)provider1.get(), (Point)provider2.get(), (ColumnDimens)provider3.get(), (ColumnViewport)provider4.get(), (DimensConverter)provider5.get(), (DragGuideManager)provider6.get(), (TimeUtils)provider7.get());
    }
}
