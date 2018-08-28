// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.graphics.Point;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.CreationHandler;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnOnTapListener, ColumnViewport, ColumnDimens

public final class ColumnOnTapListener_Factory
    implements Factory
{

    private final Provider dimensProvider;
    private final Provider gridOffsetProvider;
    private final Provider handlerProvider;
    private final Provider hostViewProvider;
    private final Provider isRtlProvider;
    private final Provider isTalkBackEnabledProvider;
    private final Provider timeUtilsProvider;
    private final Provider viewportProvider;

    public ColumnOnTapListener_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7)
    {
        hostViewProvider = provider;
        viewportProvider = provider1;
        gridOffsetProvider = provider2;
        handlerProvider = provider3;
        timeUtilsProvider = provider4;
        isRtlProvider = provider5;
        dimensProvider = provider6;
        isTalkBackEnabledProvider = provider7;
    }

    public final Object get()
    {
        Provider provider = hostViewProvider;
        Provider provider1 = viewportProvider;
        Provider provider2 = gridOffsetProvider;
        Provider provider3 = handlerProvider;
        Provider provider4 = timeUtilsProvider;
        Provider provider5 = isRtlProvider;
        Provider provider6 = dimensProvider;
        Provider provider7 = isTalkBackEnabledProvider;
        return new ColumnOnTapListener((TimelineHostView)provider.get(), (ColumnViewport)provider1.get(), (Point)provider2.get(), (CreationHandler)provider3.get(), (TimeUtils)provider4.get(), (ObservableReference)provider5.get(), (ColumnDimens)provider6.get(), (ObservableReference)provider7.get());
    }
}
