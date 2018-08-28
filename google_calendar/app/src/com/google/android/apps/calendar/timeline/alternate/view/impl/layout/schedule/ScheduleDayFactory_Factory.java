// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            ScheduleDayFactory

public final class ScheduleDayFactory_Factory
    implements Factory
{

    private final Provider dimensConverterProvider;
    private final Provider hostViewProvider;
    private final Provider isRtlProvider;
    private final Provider itemAdapterProvider;
    private final Provider layoutDimensProvider;
    private final Provider scheduleProvider;
    private final Provider screenTypeProvider;
    private final Provider timeUtilsProvider;

    public ScheduleDayFactory_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7)
    {
        itemAdapterProvider = provider;
        scheduleProvider = provider1;
        screenTypeProvider = provider2;
        isRtlProvider = provider3;
        timeUtilsProvider = provider4;
        hostViewProvider = provider5;
        dimensConverterProvider = provider6;
        layoutDimensProvider = provider7;
    }

    public final Object get()
    {
        Provider provider = itemAdapterProvider;
        Provider provider1 = scheduleProvider;
        Provider provider2 = screenTypeProvider;
        Provider provider3 = isRtlProvider;
        Provider provider4 = timeUtilsProvider;
        Provider provider5 = hostViewProvider;
        Provider provider6 = dimensConverterProvider;
        Provider provider7 = layoutDimensProvider;
        return new ScheduleDayFactory((ItemAdapter)provider.get(), (ScheduleProvider)provider1.get(), (ObservableReference)provider2.get(), (ObservableReference)provider3.get(), (TimeUtils)provider4.get(), (TimelineHostView)provider5.get(), (DimensConverter)provider6.get(), (LayoutDimens)provider7.get());
    }
}
