// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.graphics.Point;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.ViewportAnimator;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnViewportController, ColumnViewport

public final class ColumnViewportController_Factory
    implements Factory
{

    private final Provider dimensConverterProvider;
    private final Provider gridMsPerVerticalPxProvider;
    private final Provider gridOffsetProvider;
    private final Provider isRtlProvider;
    private final Provider timeUtilsProvider;
    private final Provider viewProvider;
    private final Provider viewportAnimatorProvider;
    private final Provider viewportProvider;

    public ColumnViewportController_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7)
    {
        viewportProvider = provider;
        gridOffsetProvider = provider1;
        viewProvider = provider2;
        dimensConverterProvider = provider3;
        isRtlProvider = provider4;
        timeUtilsProvider = provider5;
        viewportAnimatorProvider = provider6;
        gridMsPerVerticalPxProvider = provider7;
    }

    public final Object get()
    {
        Provider provider = viewportProvider;
        Provider provider1 = gridOffsetProvider;
        Provider provider2 = viewProvider;
        Provider provider3 = dimensConverterProvider;
        Provider provider4 = isRtlProvider;
        Provider provider5 = timeUtilsProvider;
        Provider provider6 = viewportAnimatorProvider;
        Provider provider7 = gridMsPerVerticalPxProvider;
        return new ColumnViewportController((ColumnViewport)provider.get(), (Point)provider1.get(), (TimelineHostView)provider2.get(), (DimensConverter)provider3.get(), (ObservableReference)provider4.get(), (TimeUtils)provider5.get(), (ViewportAnimator)provider6.get(), (ObservableReference)provider7.get());
    }
}
