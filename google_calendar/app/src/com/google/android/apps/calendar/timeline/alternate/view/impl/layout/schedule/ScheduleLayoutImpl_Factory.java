// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.ViewportAnimator;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            ScheduleLayoutImpl, ScheduleCache, MonthLabelThresholdEvaluator

public final class ScheduleLayoutImpl_Factory
    implements Factory
{

    private final Provider currentTimeProvider;
    private final Provider hostViewProvider;
    private final Provider isA11yEnabledProvider;
    private final Provider isTalkBackEnabledProvider;
    private final Provider layoutDimensProvider;
    private final Provider scheduleCacheProvider;
    private final Provider screenTypeProvider;
    private final Provider selectedRangeObservableProvider;
    private final Provider thresholdEvaluatorProvider;
    private final Provider timeUtilsProvider;
    private final Provider toolbarTitleHelperProvider;
    private final Provider viewportAnimatorProvider;
    private final Provider viewportObservableProvider;

    public ScheduleLayoutImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12)
    {
        hostViewProvider = provider;
        scheduleCacheProvider = provider1;
        timeUtilsProvider = provider2;
        currentTimeProvider = provider3;
        viewportObservableProvider = provider4;
        selectedRangeObservableProvider = provider5;
        viewportAnimatorProvider = provider6;
        layoutDimensProvider = provider7;
        isTalkBackEnabledProvider = provider8;
        isA11yEnabledProvider = provider9;
        toolbarTitleHelperProvider = provider10;
        screenTypeProvider = provider11;
        thresholdEvaluatorProvider = provider12;
    }

    public final Object get()
    {
        Provider provider = hostViewProvider;
        Provider provider1 = scheduleCacheProvider;
        Provider provider2 = timeUtilsProvider;
        Provider provider3 = currentTimeProvider;
        Provider provider4 = viewportObservableProvider;
        Provider provider5 = selectedRangeObservableProvider;
        Provider provider6 = viewportAnimatorProvider;
        Provider provider7 = layoutDimensProvider;
        Provider provider8 = isTalkBackEnabledProvider;
        Provider provider9 = isA11yEnabledProvider;
        Provider provider10 = toolbarTitleHelperProvider;
        Provider provider11 = screenTypeProvider;
        Provider provider12 = thresholdEvaluatorProvider;
        return new ScheduleLayoutImpl((TimelineHostView)provider.get(), (ScheduleCache)provider1.get(), (TimeUtils)provider2.get(), (ObservableReference)provider3.get(), (ObservableReference)provider4.get(), (ObservableReference)provider5.get(), (ViewportAnimator)provider6.get(), (LayoutDimens)provider7.get(), (ObservableReference)provider8.get(), (ObservableReference)provider9.get(), (com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.ToolbarTitleHelper)provider10.get(), (ObservableReference)provider11.get(), (MonthLabelThresholdEvaluator)provider12.get());
    }
}
