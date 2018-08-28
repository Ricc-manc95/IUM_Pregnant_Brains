// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.graphics.Point;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.CreationHandler;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.timeline.dnd.DragScrollPageControllerFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnViewport, ColumnDimens, ColumnViewportController, DragGuideManager, 
//            ColumnOnDragListener

public final class ColumnOnDragListener_Factory
    implements Factory
{

    private final Provider columnDimensProvider;
    private final Provider createPhantomProvider;
    private final Provider creationHandlerProvider;
    private final Provider dragGuideManagerProvider;
    private final Provider dragOffsetProvider;
    private final Provider gridOffsetProvider;
    private final Provider hostViewProvider;
    private final Provider idleTrackerProvider;
    private final Provider isRtlProvider;
    private final Provider itemAdapterProvider;
    private final Provider rescheduleManagerProvider;
    private final Provider scrollPageControllerFactoryProvider;
    private final Provider stateObservableProvider;
    private final Provider storeProvider;
    private final Provider timeUtilsProvider;
    private final Provider timelineAdapterProvider;
    private final Provider viewportControllerProvider;
    private final Provider viewportProvider;

    public ColumnOnDragListener_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, Provider provider13, 
            Provider provider14, Provider provider15, Provider provider16, Provider provider17)
    {
        stateObservableProvider = provider;
        hostViewProvider = provider1;
        timelineAdapterProvider = provider2;
        timeUtilsProvider = provider3;
        isRtlProvider = provider4;
        dragOffsetProvider = provider5;
        gridOffsetProvider = provider6;
        viewportProvider = provider7;
        columnDimensProvider = provider8;
        viewportControllerProvider = provider9;
        itemAdapterProvider = provider10;
        rescheduleManagerProvider = provider11;
        dragGuideManagerProvider = provider12;
        scrollPageControllerFactoryProvider = provider13;
        idleTrackerProvider = provider14;
        storeProvider = provider15;
        createPhantomProvider = provider16;
        creationHandlerProvider = provider17;
    }

    public final Object get()
    {
        Object obj1 = stateObservableProvider;
        Object obj3 = hostViewProvider;
        Object obj5 = timelineAdapterProvider;
        Object obj7 = timeUtilsProvider;
        Object obj9 = isRtlProvider;
        Object obj11 = dragOffsetProvider;
        Object obj13 = gridOffsetProvider;
        Object obj15 = viewportProvider;
        Object obj14 = columnDimensProvider;
        Object obj12 = viewportControllerProvider;
        Object obj10 = itemAdapterProvider;
        Object obj8 = rescheduleManagerProvider;
        Object obj6 = dragGuideManagerProvider;
        Object obj4 = scrollPageControllerFactoryProvider;
        Object obj2 = idleTrackerProvider;
        Object obj = storeProvider;
        Provider provider = createPhantomProvider;
        Provider provider1 = creationHandlerProvider;
        obj1 = (ObservableReference)((Provider) (obj1)).get();
        obj3 = (TimelineHostView)((Provider) (obj3)).get();
        obj5 = (TimelineAdapter)((Provider) (obj5)).get();
        obj7 = (TimeUtils)((Provider) (obj7)).get();
        obj9 = (ObservableReference)((Provider) (obj9)).get();
        obj11 = (Point)((Provider) (obj11)).get();
        obj13 = (Point)((Provider) (obj13)).get();
        obj15 = (ColumnViewport)((Provider) (obj15)).get();
        obj14 = (ColumnDimens)((Provider) (obj14)).get();
        obj12 = (ColumnViewportController)((Provider) (obj12)).get();
        obj10 = (ItemAdapter)((Provider) (obj10)).get();
        obj8 = (com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.RescheduleManager)((Provider) (obj8)).get();
        obj6 = (DragGuideManager)((Provider) (obj6)).get();
        obj4 = (DragScrollPageControllerFactory)((Provider) (obj4)).get();
        obj2 = (IdleTracker)((Provider) (obj2)).get();
        obj = (CalendarContentStore)((Provider) (obj)).get();
        provider.get();
        return new ColumnOnDragListener(((ObservableReference) (obj1)), ((TimelineHostView) (obj3)), ((TimelineAdapter) (obj5)), ((TimeUtils) (obj7)), ((ObservableReference) (obj9)), ((Point) (obj11)), ((Point) (obj13)), ((ColumnViewport) (obj15)), ((ColumnDimens) (obj14)), ((ColumnViewportController) (obj12)), ((ItemAdapter) (obj10)), ((com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.RescheduleManager) (obj8)), ((DragGuideManager) (obj6)), ((DragScrollPageControllerFactory) (obj4)), ((IdleTracker) (obj2)), ((CalendarContentStore) (obj)), (CreationHandler)provider1.get());
    }
}
