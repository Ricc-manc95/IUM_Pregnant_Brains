// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.timeline.alternate.activity.inject.AlternateTimelineActivityImplModule_OptionalCalendarContentStoreFactory;
import com.google.android.apps.calendar.timeline.alternate.activity.inject.AlternateTimelineActivityImplModule_OptionalMiniMonthControllerFactory;
import com.google.android.apps.calendar.timeline.alternate.activity.inject.AlternateTimelineActivityImplModule_ProvidesCalendarStoreInvalidatorFactory;
import com.google.android.apps.calendar.timeline.alternate.activity.inject.AlternateTimelineActivityImplModule_ProvidesItemProvidersFactory;
import com.google.android.apps.calendar.timeline.alternate.activity.inject.AlternateTimelineActivityImplModule_ProvidesStoreExecutorFactory;
import com.google.android.apps.calendar.timeline.alternate.fragment.impl.AlternateTimelineFragment;
import com.google.android.apps.calendar.timeline.alternate.fragment.impl.AlternateTimelineFragmentSubcomponent;
import com.google.android.apps.calendar.timeline.alternate.fragment.impl.AlternateTimelineModule_ProvidesChipFactoryFactory;
import com.google.android.apps.calendar.timeline.alternate.fragment.impl.AlternateTimelineModule_ProvidesDayHeaderClickListenerFactory;
import com.google.android.apps.calendar.timeline.alternate.fragment.impl.AlternateTimelineModule_ProvidesDayHeaderNextModeSupplierFactory;
import com.google.android.apps.calendar.timeline.alternate.fragment.impl.ChipItemViewFactory_Factory;
import com.google.android.apps.calendar.timeline.alternate.fragment.impl.TimeUpdater;
import com.google.android.apps.calendar.timeline.alternate.fragment.inject.AlternateTimelineFragmentModule_ProvidesCalendarFragmentFactoryFactory;
import com.google.android.apps.calendar.timeline.alternate.minimonth.MiniMonthControllerImpl_Factory;
import com.google.android.apps.calendar.timeline.alternate.minimonth.MiniMonthDataAdapter_Factory;
import com.google.android.apps.calendar.timeline.alternate.minimonth.MiniMonthDrawable_Factory;
import com.google.android.apps.calendar.timeline.alternate.minimonth.MiniMonthGeometry_Factory;
import com.google.android.apps.calendar.timeline.alternate.minimonth.MiniMonthViewBinder_Factory;
import com.google.android.apps.calendar.timeline.alternate.minimonth.MiniMonthViewPagerAdapter_Factory;
import com.google.android.apps.calendar.timeline.alternate.minimonth.MiniMonthViewPager_Factory;
import com.google.android.apps.calendar.timeline.alternate.minimonth.MiniMonthView_Factory;
import com.google.android.apps.calendar.timeline.alternate.minimonth.data.MiniMonthDayDataFactoryImpl_Factory;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStoreImpl_Factory;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarStoreInvalidatorImpl_Factory;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarWeekCache_Factory;
import com.google.android.apps.calendar.timeline.alternate.store.ItemTransformer_Factory;
import com.google.android.apps.calendar.timeline.alternate.util.ContextDimensConverter_Factory;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.api.TimelineApi;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.RecyclerViewAccessibilityDelegateHelper_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineAccessibilityDelegate_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineApiImpl_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineRecyclerView_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterConverter_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AllDayClickGuardHolder_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AllDayExpandViewHolder_ExpandButtonDrawable_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AllDayExpandViewHolder_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AllDayMoreViewHolder_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.DayHeaderViewHolder_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.DayHeaderViewHolder_HeaderDrawable_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.EventViewHolder_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.HoursViewHolder_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.MonthAdapter_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.MonthBannerViewHolder_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.MonthBannerView_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.MonthDayViewHolder_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.NothingPlannedBannerViewHolder_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.NowLineViewHolder_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.NowLineViewHolder_NowLineDrawable_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapterImpl_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.WeekBannerViewHolder_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.month.DayView_DayDrawable_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.month.DayView_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutManagerImpl_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutUpdaterImpl_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.AnimatorSetFuture_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.ValueAnimatorFuture_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.ViewportAnimator_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.AllDayManager_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.ColumnBackgroundDrawable_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.ColumnDimens_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.ColumnLayoutImpl_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.ColumnLayoutUpdater_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.ColumnOnDragListener_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.ColumnOnTapListener_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.ColumnViewportController_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.ColumnViewport_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.CreatePhantomUpdater_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.CreationItemToEventAdapter_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.DragGuideManager_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.HoursDrawableImpl_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month.MonthBackgroundDrawable_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month.MonthLayoutImpl_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month.MonthViewportController_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month.MonthViewport_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month.WeekdayNames_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule.MonthLabelThresholdEvaluator_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule.ScheduleCache_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule.ScheduleDayFactory_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule.ScheduleLayoutImpl_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.YearMonthHelper_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.inject.TimelineProvidesModule_ProvidesAllDayExpandedObservableFactory;
import com.google.android.apps.calendar.timeline.alternate.view.inject.TimelineProvidesModule_ProvidesColumnGridOffsetFactory;
import com.google.android.apps.calendar.timeline.alternate.view.inject.TimelineProvidesModule_ProvidesCreateEventPhantomAdapterEventFactory;
import com.google.android.apps.calendar.timeline.alternate.view.inject.TimelineProvidesModule_ProvidesCreateEventPhantomItemFactory;
import com.google.android.apps.calendar.timeline.alternate.view.inject.TimelineProvidesModule_ProvidesDataSetChangedObservableFactory;
import com.google.android.apps.calendar.timeline.alternate.view.inject.TimelineProvidesModule_ProvidesDragOffsetFactory;
import com.google.android.apps.calendar.timeline.alternate.view.inject.TimelineProvidesModule_ProvidesDragStateFactory;
import com.google.android.apps.calendar.timeline.alternate.view.inject.TimelineProvidesModule_ProvidesEventsPerMonthViewDayFactory;
import com.google.android.apps.calendar.timeline.alternate.view.inject.TimelineProvidesModule_ProvidesGridMsPerVerticalPxFactory;
import com.google.android.apps.calendar.timeline.alternate.view.inject.TimelineProvidesModule_ProvidesIdleObservableFactory;
import com.google.android.apps.calendar.timeline.alternate.view.inject.TimelineProvidesModule_ProvidesViewportRangeObservableFactory;
import com.google.android.apps.calendar.timeline.alternate.view.inject.TimelineProvidesModule_ProvidesVirtualViewsSupplierFactory;
import com.google.android.apps.calendar.timeline.alternate.view.inject.TimelineProvidesModule_ProvidesWeeksInMonthFactory;
import com.google.android.apps.calendar.timeline.alternate.view.timebox.TaskItemProvider_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.timebox.TimeBoxItemAdapter_Factory;
import com.google.android.apps.calendar.timeline.alternate.view.timebox.TimeBoxItemProvider_Factory;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.alternatecalendar.AlternateCalendarHelperImpl_Factory;
import com.google.android.calendar.latency.LatencyLoggerModule_ProvidesLatencyLoggerFactory;
import com.google.android.calendar.task.alternate.CachedTaskDataLoader_Factory;
import com.google.android.calendar.task.alternate.SimpleTaskDataLoader_Factory;
import com.google.android.calendar.task.alternate.TaskCacheInvalidator_Factory;
import com.google.android.calendar.timebox.adapter.TimeBoxToTimelineAdapter_Factory;
import com.google.android.calendar.timeline.alternate.AlternateTimelineAnimatedToolbarTitleHelper_Factory;
import com.google.android.calendar.timeline.alternate.AlternateTimelineRescheduleManager_Factory;
import com.google.android.calendar.timeline.alternate.ChipHeights_Factory;
import com.google.android.calendar.timeline.alternate.CreationHandlerImpl_Factory;
import com.google.android.calendar.timeline.alternate.DefaultBundleFactory_Factory;
import com.google.android.calendar.timeline.alternate.ScheduleProviderImpl_Factory;
import com.google.android.calendar.timeline.alternate.SwipeHandlerImpl_Factory;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfoFactory_Factory;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipViewModelFactory_Factory;
import com.google.android.calendar.timeline.dnd.DragScrollPageControllerFactory_Factory;
import com.google.android.calendar.timely.MonthBackgrounds_Factory;
import com.google.android.calendar.timely.TaskBundleFragment;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.collect.ImmutableMap;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar:
//            AllInOneActivityModule_ProvidesTimelineLifecycleFactory, DaggerCalendarApplicationComponent, AllInOneActivityModule_ProvidesEventsApiFactory, AllInOneActivityModule_ProvidesSettingsCacheFactory, 
//            AllInOneActivityModule_ProvidesTasksApiFactory, CalendarActivityPropertiesManager_Factory, AllInOneActivityModule_ProvidesSelectedDayObservableFactory, AllInOneActivityModule_ProvidesTaskCacheFactory, 
//            AllInOneActivityModule_ProvidesCalendarListEntryCacheFactory, AllInOneActivityModule_ProvidesTaskDataFactoryFactory, AllInOneActivityModule_ProvidesScreenTypeFactory, CalendarActivityPreferencesModule_ProvidesIsRtlFactory, 
//            CalendarActivityPreferencesModule_ProvidesIsPortraitFactory, AllInOneActivityModule_ProvidesViewportFullyLoadedFactory, AllInOneActivityModule_ProvidesDayClickCallbackFactory, AllInOneActivityModule_ProvidesWeekBannerClickCallbackFactory, 
//            AllInOneActivityModule_ProvidesIsVisibleSupplierFactory, AllInOneActivityModule_ProvidesChipClickListenerFactory, AllInOneActivityModule_ProvidesChipViewModelFactoryFactory, AllInOneActivityModule_ProvidesCreationModeFactory, 
//            AllInOneActivityModule_ProvidesViewModeChangeListenerFactory, AllInOneActivityModule_ProvidesDateTimeFormatHelperFactory, AllInOneActivityModule_ProvidesEmptyDayClickListenerFactory, AllInOneCalendarActivity, 
//            CalendarActivityPropertiesManager, AllInOneActivityModule_OptionalTaskCacheInvalidatorFactory, CalendarApplicationPropertiesModule_ProvidesFirstDayOfWeekFactory

final class timelineApiImplProvider
    implements AllInOneCalendarActivitySubcomponent
{

    private Provider adapterConverterProvider;
    private AllDayClickGuardHolder_Factory allDayClickGuardHolderProvider;
    private AllDayExpandViewHolder_Factory allDayExpandViewHolderProvider;
    private Provider allDayManagerProvider;
    private AllDayMoreViewHolder_Factory allDayMoreViewHolderProvider;
    private AlternateCalendarHelperImpl_Factory alternateCalendarHelperImplProvider;
    private AlternateTimelineAnimatedToolbarTitleHelper_Factory alternateTimelineAnimatedToolbarTitleHelperProvider;
    private Provider alternateTimelineFragmentSubcomponentBuilderProvider;
    private Provider alternateTimelineRescheduleManagerProvider;
    private AnimatorSetFuture_Factory animatorSetFutureProvider;
    private Provider cachedTaskDataLoaderProvider;
    public Provider calendarActivityPropertiesManagerProvider;
    private CalendarContentStoreImpl_Factory calendarContentStoreImplProvider;
    private CalendarStoreInvalidatorImpl_Factory calendarStoreInvalidatorImplProvider;
    private CalendarWeekCache_Factory calendarWeekCacheProvider;
    private Provider chipFragmentInfoFactoryProvider;
    private ChipHeights_Factory chipHeightsProvider;
    private Provider chipItemViewFactoryProvider;
    private ChipViewModelFactory_Factory chipViewModelFactoryProvider;
    private Provider columnBackgroundDrawableProvider;
    private ColumnDimens_Factory columnDimensProvider;
    private Provider columnLayoutImplProvider;
    private ColumnLayoutUpdater_Factory columnLayoutUpdaterProvider;
    private Provider columnOnDragListenerProvider;
    private Provider columnOnTapListenerProvider;
    private Provider columnViewportControllerProvider;
    private Provider columnViewportProvider;
    private ContextDimensConverter_Factory contextDimensConverterProvider;
    private Provider createPhantomUpdaterProvider;
    private Provider creationHandlerImplProvider;
    private CreationItemToEventAdapter_Factory creationItemToEventAdapterProvider;
    private DayView_DayDrawable_Factory dayDrawableProvider;
    private DayHeaderViewHolder_Factory dayHeaderViewHolderProvider;
    private DayView_Factory dayViewProvider;
    private DefaultBundleFactory_Factory defaultBundleFactoryProvider;
    private Provider dragGuideManagerProvider;
    private DragScrollPageControllerFactory_Factory dragScrollPageControllerFactoryProvider;
    private EventViewHolder_Factory eventViewHolderProvider;
    private AllDayExpandViewHolder_ExpandButtonDrawable_Factory expandButtonDrawableProvider;
    private DayHeaderViewHolder_HeaderDrawable_Factory headerDrawableProvider;
    private HoursDrawableImpl_Factory hoursDrawableImplProvider;
    private HoursViewHolder_Factory hoursViewHolderProvider;
    private Provider idleTrackerProvider;
    private ItemTransformer_Factory itemTransformerProvider;
    private LayoutDimens_Factory layoutDimensProvider;
    private Provider layoutManagerImplProvider;
    private Provider layoutUpdaterImplProvider;
    private MiniMonthControllerImpl_Factory miniMonthControllerImplProvider;
    private Provider miniMonthDataAdapterProvider;
    private MiniMonthDayDataFactoryImpl_Factory miniMonthDayDataFactoryImplProvider;
    private MiniMonthDrawable_Factory miniMonthDrawableProvider;
    private MiniMonthGeometry_Factory miniMonthGeometryProvider;
    private MiniMonthViewBinder_Factory miniMonthViewBinderProvider;
    private MiniMonthViewPagerAdapter_Factory miniMonthViewPagerAdapterProvider;
    private MiniMonthViewPager_Factory miniMonthViewPagerProvider;
    private MiniMonthView_Factory miniMonthViewProvider;
    private Provider monthAdapterProvider;
    private Provider monthBackgroundDrawableProvider;
    private MonthBackgrounds_Factory monthBackgroundsProvider;
    private MonthBannerViewHolder_Factory monthBannerViewHolderProvider;
    private MonthBannerView_Factory monthBannerViewProvider;
    private MonthDayViewHolder_Factory monthDayViewHolderProvider;
    private Provider monthLayoutImplProvider;
    private Provider monthViewportControllerProvider;
    private Provider monthViewportProvider;
    private NothingPlannedBannerViewHolder_Factory nothingPlannedBannerViewHolderProvider;
    private NowLineViewHolder_NowLineDrawable_Factory nowLineDrawableProvider;
    private NowLineViewHolder_Factory nowLineViewHolderProvider;
    public Provider optionalCalendarContentStoreProvider;
    private Provider optionalMiniMonthControllerProvider;
    private Provider providesAllDayExpandedObservableProvider;
    private Provider providesAlternateCalendarHelperProvider;
    private Provider providesCalendarContentStoreProvider;
    private Provider providesCalendarStoreInvalidatorProvider;
    private Provider providesCalendarStoreInvalidatorProvider2;
    private Provider providesChipClickListenerProvider;
    private Provider providesChipFactoryProvider;
    private Provider providesChipViewModelFactoryProvider;
    private Provider providesColumnGridOffsetProvider;
    private Provider providesCreateEventPhantomAdapterEventProvider;
    public Provider providesCreateEventPhantomItemProvider;
    private Provider providesCreationModeProvider;
    private TimelineProvidesModule_ProvidesDataSetChangedObservableFactory providesDataSetChangedObservableProvider;
    private Provider providesDayClickCallbackProvider;
    private AlternateTimelineModule_ProvidesDayHeaderClickListenerFactory providesDayHeaderClickListenerProvider;
    private AlternateTimelineModule_ProvidesDayHeaderNextModeSupplierFactory providesDayHeaderNextModeSupplierProvider;
    private Provider providesDimensConverterProvider;
    private Provider providesDragOffsetProvider;
    private Provider providesDragStateProvider;
    private Provider providesEmptyDayClickListenerProvider;
    private Provider providesEventsApiProvider;
    private Provider providesEventsPerMonthViewDayProvider;
    private Provider providesGridMsPerVerticalPxProvider;
    private TimelineProvidesModule_ProvidesIdleObservableFactory providesIdleObservableProvider;
    private CalendarActivityPreferencesModule_ProvidesIsPortraitFactory providesIsPortraitProvider;
    private CalendarActivityPreferencesModule_ProvidesIsRtlFactory providesIsRtlProvider;
    private AllInOneActivityModule_ProvidesIsVisibleSupplierFactory providesIsVisibleSupplierProvider;
    private Provider providesItemProvidersProvider;
    private Provider providesMiniMonthControllerProvider;
    private Provider providesMiniMonthDayDataFactoryProvider;
    private Provider providesScheduleProvider;
    public Provider providesScreenTypeProvider;
    private Provider providesSelectedDayObservableProvider;
    private Provider providesStoreExecutorProvider;
    private Provider providesTaskCacheProvider;
    private Provider providesTasksApiProvider;
    private AllInOneActivityModule_ProvidesTimelineLifecycleFactory providesTimelineLifecycleProvider;
    private Provider providesToolbarTitleHelperProvider;
    private AllInOneActivityModule_ProvidesViewModeChangeListenerFactory providesViewModeChangeListenerProvider;
    private Provider providesViewportFullyLoadedProvider;
    private Provider providesViewportRangeObservableProvider;
    private TimelineProvidesModule_ProvidesVirtualViewsSupplierFactory providesVirtualViewsSupplierProvider;
    private Provider providesWeekBannerClickCallbackProvider;
    private TimelineProvidesModule_ProvidesWeeksInMonthFactory providesWeeksInMonthProvider;
    private Provider recyclerViewAccessibilityDelegateHelperProvider;
    private ScheduleCache_Factory scheduleCacheProvider;
    private ScheduleDayFactory_Factory scheduleDayFactoryProvider;
    private Provider scheduleLayoutImplProvider;
    private ScheduleProviderImpl_Factory scheduleProviderImplProvider;
    private AllInOneCalendarActivity seedInstance;
    private Provider seedInstanceProvider;
    private SimpleTaskDataLoader_Factory simpleTaskDataLoaderProvider;
    private SwipeHandlerImpl_Factory swipeHandlerImplProvider;
    private Provider taskBundleFragmentSubcomponentBuilderProvider;
    private TaskCacheInvalidator_Factory taskCacheInvalidatorProvider;
    private TaskItemProvider_Factory taskItemProvider;
    public final DaggerCalendarApplicationComponent this$0;
    private TimeBoxItemProvider_Factory timeBoxItemProvider;
    private TimeBoxToTimelineAdapter_Factory timeBoxToTimelineAdapterProvider;
    private TimeUtils_Factory timeUtilsProvider;
    private Provider timelineAccessibilityDelegateProvider;
    private Provider timelineAdapterImplProvider;
    public Provider timelineApiImplProvider;
    private Provider timelineRecyclerViewProvider;
    private ValueAnimatorFuture_Factory valueAnimatorFutureProvider;
    private ViewportAnimator_Factory viewportAnimatorProvider;
    private WeekBannerViewHolder_Factory weekBannerViewHolderProvider;
    private WeekdayNames_Factory weekdayNamesProvider;
    private Provider yearMonthHelperProvider;

    public final void inject(Object obj)
    {
        obj = (AllInOneCalendarActivity)obj;
        obj.dispatchingFragmentInjector = new DispatchingAndroidInjector(ImmutableMap.of(com/google/android/calendar/timely/TaskBundleFragment, taskBundleFragmentSubcomponentBuilderProvider, com/google/android/apps/calendar/timeline/alternate/fragment/impl/AlternateTimelineFragment, alternateTimelineFragmentSubcomponentBuilderProvider));
        com.google.android.apps.calendar.util.function.BiFunction bifunction = AlternateTimelineFragmentModule_ProvidesCalendarFragmentFactoryFactory.proxyProvidesCalendarFragmentFactory();
        if (bifunction == null)
        {
            throw new NullPointerException();
        } else
        {
            obj.calendarFragmentFactory = new Present(bifunction);
            obj.calendarContentStore = (Optional)optionalCalendarContentStoreProvider.get();
            obj.preferencesManager = (CalendarActivityPropertiesManager)calendarActivityPropertiesManagerProvider.get();
            obj.selectedRangeObservable = (ObservableReference)providesSelectedDayObservableProvider.get();
            obj.calendarStoreInvalidator = (Optional)providesCalendarStoreInvalidatorProvider2.get();
            obj.taskCacheInvalidator = AllInOneActivityModule_OptionalTaskCacheInvalidatorFactory.proxyOptionalTaskCacheInvalidator(seedInstance, taskCacheInvalidatorProvider);
            obj.miniMonthController = (Optional)optionalMiniMonthControllerProvider.get();
            obj.viewportLoaded = (ObservableReference)providesViewportFullyLoadedProvider.get();
            return;
        }
    }

    _cls2.this._cls1(er er)
    {
        this$0 = DaggerCalendarApplicationComponent.this;
        super();
        class _cls1
            implements Provider
        {

            private final DaggerCalendarApplicationComponent.AllInOneCalendarActivitySubcomponentImpl this$1;

            public final Object get()
            {
                class TaskBundleFragmentSubcomponentBuilder extends AllInOneActivityModule_ContributeTaskBundleFragment.TaskBundleFragmentSubcomponent.Builder
                {

                    private TaskBundleFragment seedInstance;
                    private final DaggerCalendarApplicationComponent.AllInOneCalendarActivitySubcomponentImpl this$1;

                    public final AndroidInjector build()
                    {
                        class TaskBundleFragmentSubcomponentImpl
                            implements AllInOneActivityModule_ContributeTaskBundleFragment.TaskBundleFragmentSubcomponent
                        {

                            private final DaggerCalendarApplicationComponent.AllInOneCalendarActivitySubcomponentImpl this$1;

                            public final void inject(Object obj)
                            {
                                ((TaskBundleFragment)obj).contentStore = optionalCalendarContentStoreProvider;
                            }

                                TaskBundleFragmentSubcomponentImpl()
                                {
                                    this$1 = DaggerCalendarApplicationComponent.AllInOneCalendarActivitySubcomponentImpl.this;
                                    super();
                                }
                        }

                        if (seedInstance == null)
                        {
                            throw new IllegalStateException(String.valueOf(com/google/android/calendar/timely/TaskBundleFragment.getCanonicalName()).concat(" must be set"));
                        } else
                        {
                            return new TaskBundleFragmentSubcomponentImpl();
                        }
                    }

                    public final void seedInstance(Object obj)
                    {
                        obj = (TaskBundleFragment)obj;
                        if (obj == null)
                        {
                            throw new NullPointerException();
                        } else
                        {
                            seedInstance = (TaskBundleFragment)obj;
                            return;
                        }
                    }

                        TaskBundleFragmentSubcomponentBuilder()
                        {
                            this$1 = DaggerCalendarApplicationComponent.AllInOneCalendarActivitySubcomponentImpl.this;
                            super();
                        }
                }

                return new TaskBundleFragmentSubcomponentBuilder();
            }

            _cls1()
            {
                this$1 = DaggerCalendarApplicationComponent.AllInOneCalendarActivitySubcomponentImpl.this;
                super();
            }
        }

        taskBundleFragmentSubcomponentBuilderProvider = new _cls1();
        class _cls2
            implements Provider
        {

            private final DaggerCalendarApplicationComponent.AllInOneCalendarActivitySubcomponentImpl this$1;

            public final Object get()
            {
                class AlternateTimelineFragmentSubcomponentBuilder extends com.google.android.apps.calendar.timeline.alternate.fragment.impl.AlternateTimelineFragmentSubcomponent.Builder
                {

                    private AlternateTimelineFragment seedInstance;
                    private final DaggerCalendarApplicationComponent.AllInOneCalendarActivitySubcomponentImpl this$1;

                    public final AndroidInjector build()
                    {
                        class AlternateTimelineFragmentSubcomponentImpl
                            implements AlternateTimelineFragmentSubcomponent
                        {

                            private final DaggerCalendarApplicationComponent.AllInOneCalendarActivitySubcomponentImpl this$1;

                            public final void inject(Object obj)
                            {
                                obj = (AlternateTimelineFragment)obj;
                                obj.controller = (TimelineApi)timelineApiImplProvider.get();
                                Object obj1 = DaggerCalendarApplicationComponent.AllInOneCalendarActivitySubcomponentImpl.this;
                                obj.timeUpdater = new TimeUpdater(new TimeUtils((ObservableReference)((DaggerCalendarApplicationComponent.AllInOneCalendarActivitySubcomponentImpl) (obj1)).this$0.providesTimeZoneObservableProvider.get(), CalendarApplicationPropertiesModule_ProvidesFirstDayOfWeekFactory.proxyProvidesFirstDayOfWeek(((DaggerCalendarApplicationComponent.AllInOneCalendarActivitySubcomponentImpl) (obj1)).this$0.calendarApplicationPropertiesManagerProvider.get())), (ObservableReference)providesCurrentTimeProvider.get(), (ObservableReference)providesCurrentJulianDayProvider.get());
                                obj1 = ((CalendarActivityPropertiesManager)calendarActivityPropertiesManagerProvider.get()).isPortrait;
                                if (obj1 == null)
                                {
                                    throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
                                } else
                                {
                                    obj.isPortrait = (ObservableReference)obj1;
                                    obj.screenType = (ObservableReference)providesScreenTypeProvider.get();
                                    obj.phantom = (ObservableReference)providesCreateEventPhantomItemProvider.get();
                                    return;
                                }
                            }

                                AlternateTimelineFragmentSubcomponentImpl()
                                {
                                    this$1 = DaggerCalendarApplicationComponent.AllInOneCalendarActivitySubcomponentImpl.this;
                                    super();
                                }
                        }

                        if (seedInstance == null)
                        {
                            throw new IllegalStateException(String.valueOf(com/google/android/apps/calendar/timeline/alternate/fragment/impl/AlternateTimelineFragment.getCanonicalName()).concat(" must be set"));
                        } else
                        {
                            return new AlternateTimelineFragmentSubcomponentImpl();
                        }
                    }

                    public final void seedInstance(Object obj)
                    {
                        obj = (AlternateTimelineFragment)obj;
                        if (obj == null)
                        {
                            throw new NullPointerException();
                        } else
                        {
                            seedInstance = (AlternateTimelineFragment)obj;
                            return;
                        }
                    }

                        AlternateTimelineFragmentSubcomponentBuilder()
                        {
                            this$1 = DaggerCalendarApplicationComponent.AllInOneCalendarActivitySubcomponentImpl.this;
                            super();
                        }
                }

                return new AlternateTimelineFragmentSubcomponentBuilder();
            }

            _cls2()
            {
                this$1 = DaggerCalendarApplicationComponent.AllInOneCalendarActivitySubcomponentImpl.this;
                super();
            }
        }

        alternateTimelineFragmentSubcomponentBuilderProvider = new _cls2();
        daggercalendarapplicationcomponent = er.seedInstance;
        if (DaggerCalendarApplicationComponent.this == null)
        {
            throw new NullPointerException("instance cannot be null");
        } else
        {
            seedInstanceProvider = new InstanceFactory(DaggerCalendarApplicationComponent.this);
            providesTimelineLifecycleProvider = new AllInOneActivityModule_ProvidesTimelineLifecycleFactory(seedInstanceProvider);
            providesStoreExecutorProvider = DoubleCheck.provider(new AlternateTimelineActivityImplModule_ProvidesStoreExecutorFactory(providesAlternateTimelineEnabledProvider));
            timeUtilsProvider = new TimeUtils_Factory(providesTimeZoneObservableProvider, providesFirstDayOfWeekProvider);
            calendarWeekCacheProvider = new CalendarWeekCache_Factory(providesStoreExecutorProvider, timeUtilsProvider, TimeBoxItemAdapter_Factory.INSTANCE);
            itemTransformerProvider = new ItemTransformer_Factory(TimeBoxItemAdapter_Factory.INSTANCE, timeUtilsProvider);
            providesEventsApiProvider = DoubleCheck.provider(new AllInOneActivityModule_ProvidesEventsApiFactory(seedInstanceProvider, providesTimeZoneObservableProvider));
            timeBoxItemProvider = new TimeBoxItemProvider_Factory(providesEventsApiProvider, providesHideDeclinedEventsProvider);
            simpleTaskDataLoaderProvider = new SimpleTaskDataLoader_Factory(seedInstanceProvider, AllInOneActivityModule_ProvidesSettingsCacheFactory.INSTANCE);
            cachedTaskDataLoaderProvider = DoubleCheck.provider(new CachedTaskDataLoader_Factory(providesTimeZoneObservableProvider, simpleTaskDataLoaderProvider));
            providesTasksApiProvider = DoubleCheck.provider(new AllInOneActivityModule_ProvidesTasksApiFactory(cachedTaskDataLoaderProvider, providesTimeZoneObservableProvider));
            taskItemProvider = new TaskItemProvider_Factory(providesTasksApiProvider);
            providesItemProvidersProvider = DoubleCheck.provider(new AlternateTimelineActivityImplModule_ProvidesItemProvidersFactory(providesAlternateTimelineEnabledProvider, timeBoxItemProvider, taskItemProvider, LatencyLoggerModule_ProvidesLatencyLoggerFactory.INSTANCE));
            calendarContentStoreImplProvider = new CalendarContentStoreImpl_Factory(providesTimelineLifecycleProvider, calendarWeekCacheProvider, itemTransformerProvider, providesItemProvidersProvider, providesStoreExecutorProvider, timeUtilsProvider, providesFirstDayOfWeekProvider);
            providesCalendarContentStoreProvider = DoubleCheck.provider(calendarContentStoreImplProvider);
            optionalCalendarContentStoreProvider = DoubleCheck.provider(new AlternateTimelineActivityImplModule_OptionalCalendarContentStoreFactory(providesAlternateTimelineEnabledProvider, providesCalendarContentStoreProvider));
            calendarActivityPropertiesManagerProvider = DoubleCheck.provider(new CalendarActivityPropertiesManager_Factory(seedInstanceProvider));
            providesSelectedDayObservableProvider = DoubleCheck.provider(AllInOneActivityModule_ProvidesSelectedDayObservableFactory.INSTANCE);
            providesTaskCacheProvider = DoubleCheck.provider(new AllInOneActivityModule_ProvidesTaskCacheFactory(cachedTaskDataLoaderProvider));
            calendarStoreInvalidatorImplProvider = new CalendarStoreInvalidatorImpl_Factory(providesAlternateTimelineEnabledProvider, providesCalendarContentStoreProvider, AllInOneActivityModule_ProvidesCalendarListEntryCacheFactory.INSTANCE, AllInOneActivityModule_ProvidesSettingsCacheFactory.INSTANCE, providesTaskCacheProvider, providesTimeZoneObservableProvider, seedInstanceProvider);
            providesCalendarStoreInvalidatorProvider = DoubleCheck.provider(calendarStoreInvalidatorImplProvider);
            providesCalendarStoreInvalidatorProvider2 = DoubleCheck.provider(new AlternateTimelineActivityImplModule_ProvidesCalendarStoreInvalidatorFactory(providesAlternateTimelineEnabledProvider, providesCalendarStoreInvalidatorProvider));
            seedInstance = er.seedInstance;
            taskCacheInvalidatorProvider = new TaskCacheInvalidator_Factory(AllInOneActivityModule_ProvidesSettingsCacheFactory.INSTANCE, AllInOneActivityModule_ProvidesTaskDataFactoryFactory.INSTANCE, seedInstanceProvider, providesTaskCacheProvider);
            miniMonthViewPagerProvider = new MiniMonthViewPager_Factory(seedInstanceProvider);
            contextDimensConverterProvider = new ContextDimensConverter_Factory(seedInstanceProvider);
            providesDimensConverterProvider = DoubleCheck.provider(contextDimensConverterProvider);
            providesScreenTypeProvider = DoubleCheck.provider(new AllInOneActivityModule_ProvidesScreenTypeFactory(seedInstanceProvider));
            providesIsRtlProvider = new CalendarActivityPreferencesModule_ProvidesIsRtlFactory(calendarActivityPropertiesManagerProvider);
            miniMonthDrawableProvider = new MiniMonthDrawable_Factory(seedInstanceProvider, providesDimensConverterProvider, timeUtilsProvider, providesScreenTypeProvider, providesShouldShowMonthIllustrationsProvider, providesIsRtlProvider, providesShouldShowWeekNumbersProvider);
            providesIsPortraitProvider = new CalendarActivityPreferencesModule_ProvidesIsPortraitFactory(calendarActivityPropertiesManagerProvider);
            alternateCalendarHelperImplProvider = new AlternateCalendarHelperImpl_Factory(seedInstanceProvider);
            providesAlternateCalendarHelperProvider = DoubleCheck.provider(alternateCalendarHelperImplProvider);
            miniMonthGeometryProvider = new MiniMonthGeometry_Factory(seedInstanceProvider, providesDimensConverterProvider, timeUtilsProvider, providesIsRtlProvider, providesScreenTypeProvider, providesIsPortraitProvider, providesShouldShowWeekNumbersProvider, providesAlternateCalendarHelperProvider);
            miniMonthViewProvider = new MiniMonthView_Factory(seedInstanceProvider, miniMonthDrawableProvider, miniMonthGeometryProvider, timeUtilsProvider, providesAlternateCalendarHelperProvider);
            miniMonthDayDataFactoryImplProvider = new MiniMonthDayDataFactoryImpl_Factory(seedInstanceProvider, providesAlternateCalendarHelperProvider, timeUtilsProvider);
            providesMiniMonthDayDataFactoryProvider = DoubleCheck.provider(miniMonthDayDataFactoryImplProvider);
            miniMonthDataAdapterProvider = DoubleCheck.provider(new MiniMonthDataAdapter_Factory(providesCalendarContentStoreProvider, providesMiniMonthDayDataFactoryProvider, timeUtilsProvider));
            miniMonthViewBinderProvider = new MiniMonthViewBinder_Factory(miniMonthViewProvider, providesCurrentTimeProvider, timeUtilsProvider, miniMonthDataAdapterProvider);
            miniMonthViewPagerAdapterProvider = new MiniMonthViewPagerAdapter_Factory(timeUtilsProvider, miniMonthViewBinderProvider, miniMonthDataAdapterProvider);
            miniMonthControllerImplProvider = new MiniMonthControllerImpl_Factory(miniMonthViewPagerProvider, miniMonthViewPagerAdapterProvider, providesScreenTypeProvider, providesIsPortraitProvider, providesIsA11yEnabledProvider, providesShouldShowWeekNumbersProvider, providesDimensConverterProvider, timeUtilsProvider);
            providesMiniMonthControllerProvider = DoubleCheck.provider(miniMonthControllerImplProvider);
            optionalMiniMonthControllerProvider = DoubleCheck.provider(new AlternateTimelineActivityImplModule_OptionalMiniMonthControllerFactory(providesAlternateTimelineEnabledProvider, providesMiniMonthControllerProvider));
            providesViewportFullyLoadedProvider = DoubleCheck.provider(AllInOneActivityModule_ProvidesViewportFullyLoadedFactory.INSTANCE);
            providesDayClickCallbackProvider = DoubleCheck.provider(new AllInOneActivityModule_ProvidesDayClickCallbackFactory(seedInstanceProvider));
            providesWeekBannerClickCallbackProvider = DoubleCheck.provider(new AllInOneActivityModule_ProvidesWeekBannerClickCallbackFactory(seedInstanceProvider));
            alternateTimelineRescheduleManagerProvider = DoubleCheck.provider(new AlternateTimelineRescheduleManager_Factory(seedInstanceProvider, providesTaskCacheProvider));
            providesWeeksInMonthProvider = new TimelineProvidesModule_ProvidesWeeksInMonthFactory(er.providesModule);
            timelineRecyclerViewProvider = DoubleCheck.provider(new TimelineRecyclerView_Factory(seedInstanceProvider, providesWeeksInMonthProvider, providesIsA11yEnabledProvider));
            idleTrackerProvider = DoubleCheck.provider(IdleTracker_Factory.INSTANCE);
            animatorSetFutureProvider = new AnimatorSetFuture_Factory(idleTrackerProvider);
            providesIsVisibleSupplierProvider = new AllInOneActivityModule_ProvidesIsVisibleSupplierFactory(seedInstanceProvider);
            layoutUpdaterImplProvider = DoubleCheck.provider(new LayoutUpdaterImpl_Factory(timelineRecyclerViewProvider, animatorSetFutureProvider, providesIsA11yEnabledProvider, providesViewportFullyLoadedProvider, providesIsVisibleSupplierProvider));
            layoutManagerImplProvider = DoubleCheck.provider(new LayoutManagerImpl_Factory(layoutUpdaterImplProvider, idleTrackerProvider));
            providesIdleObservableProvider = new TimelineProvidesModule_ProvidesIdleObservableFactory(er.providesModule, idleTrackerProvider);
            providesCreateEventPhantomAdapterEventProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesCreateEventPhantomAdapterEventFactory(er.providesModule));
            providesDragStateProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesDragStateFactory(er.providesModule));
            providesAllDayExpandedObservableProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesAllDayExpandedObservableFactory(er.providesModule));
            expandButtonDrawableProvider = new AllDayExpandViewHolder_ExpandButtonDrawable_Factory(seedInstanceProvider, providesDimensConverterProvider, providesScreenTypeProvider);
            allDayExpandViewHolderProvider = new AllDayExpandViewHolder_Factory(seedInstanceProvider, providesAllDayExpandedObservableProvider, expandButtonDrawableProvider);
            timelineAdapterImplProvider = new DelegateFactory();
            providesDataSetChangedObservableProvider = new TimelineProvidesModule_ProvidesDataSetChangedObservableFactory(er.providesModule, timelineAdapterImplProvider);
            allDayMoreViewHolderProvider = new AllDayMoreViewHolder_Factory(seedInstanceProvider, timelineAdapterImplProvider, providesDimensConverterProvider, providesDataSetChangedObservableProvider, providesAllDayExpandedObservableProvider);
            timelineApiImplProvider = new DelegateFactory();
            providesChipFactoryProvider = DoubleCheck.provider(new AlternateTimelineModule_ProvidesChipFactoryFactory(seedInstanceProvider));
            timeBoxToTimelineAdapterProvider = new TimeBoxToTimelineAdapter_Factory(seedInstanceProvider);
            chipFragmentInfoFactoryProvider = DoubleCheck.provider(ChipFragmentInfoFactory_Factory.INSTANCE);
            providesChipClickListenerProvider = DoubleCheck.provider(new AllInOneActivityModule_ProvidesChipClickListenerFactory(seedInstanceProvider, timeBoxToTimelineAdapterProvider, chipFragmentInfoFactoryProvider, alternateTimelineRescheduleManagerProvider));
            chipViewModelFactoryProvider = new ChipViewModelFactory_Factory(seedInstanceProvider);
            providesChipViewModelFactoryProvider = DoubleCheck.provider(new AllInOneActivityModule_ProvidesChipViewModelFactoryFactory(timeBoxToTimelineAdapterProvider, chipViewModelFactoryProvider, chipFragmentInfoFactoryProvider));
            swipeHandlerImplProvider = new SwipeHandlerImpl_Factory(seedInstanceProvider, providesCalendarContentStoreProvider);
            chipItemViewFactoryProvider = DoubleCheck.provider(new ChipItemViewFactory_Factory(timelineApiImplProvider, providesChipFactoryProvider, providesChipClickListenerProvider, providesChipViewModelFactoryProvider, providesScreenTypeProvider, providesIsTalkBackEnabledProvider, swipeHandlerImplProvider, providesIdleObservableProvider));
            providesCreationModeProvider = DoubleCheck.provider(AllInOneActivityModule_ProvidesCreationModeFactory.INSTANCE);
            eventViewHolderProvider = new EventViewHolder_Factory(chipItemViewFactoryProvider, providesCreationModeProvider, timelineAdapterImplProvider);
            headerDrawableProvider = new DayHeaderViewHolder_HeaderDrawable_Factory(seedInstanceProvider, providesDimensConverterProvider, providesIsRtlProvider, providesScreenTypeProvider, providesAlternateCalendarHelperProvider);
            providesViewModeChangeListenerProvider = new AllInOneActivityModule_ProvidesViewModeChangeListenerFactory(seedInstanceProvider);
            providesDayHeaderNextModeSupplierProvider = new AlternateTimelineModule_ProvidesDayHeaderNextModeSupplierFactory(seedInstanceProvider, timelineApiImplProvider, providesIsPortraitProvider, providesScreenTypeProvider);
            providesDayHeaderClickListenerProvider = new AlternateTimelineModule_ProvidesDayHeaderClickListenerFactory(seedInstanceProvider, timelineApiImplProvider, providesViewModeChangeListenerProvider, providesDayHeaderNextModeSupplierProvider);
            dayHeaderViewHolderProvider = new DayHeaderViewHolder_Factory(seedInstanceProvider, providesIsA11yEnabledProvider, headerDrawableProvider, timeUtilsProvider, providesTimeZoneObservableProvider, providesDayHeaderClickListenerProvider, providesDayHeaderNextModeSupplierProvider, providesCurrentJulianDayProvider, providesAlternateCalendarHelperProvider);
            yearMonthHelperProvider = DoubleCheck.provider(new YearMonthHelper_Factory(timeUtilsProvider, providesWeeksInMonthProvider));
            monthAdapterProvider = DoubleCheck.provider(new MonthAdapter_Factory(timeUtilsProvider, yearMonthHelperProvider, providesWeeksInMonthProvider));
            providesEventsPerMonthViewDayProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesEventsPerMonthViewDayFactory(er.providesModule));
            monthBackgroundsProvider = new MonthBackgrounds_Factory(seedInstanceProvider);
            chipHeightsProvider = new ChipHeights_Factory(seedInstanceProvider);
            scheduleProviderImplProvider = new ScheduleProviderImpl_Factory(seedInstanceProvider, monthBackgroundsProvider, AllInOneActivityModule_ProvidesDateTimeFormatHelperFactory.INSTANCE, providesChipViewModelFactoryProvider, chipHeightsProvider);
            providesScheduleProvider = DoubleCheck.provider(scheduleProviderImplProvider);
            layoutDimensProvider = new LayoutDimens_Factory(providesDimensConverterProvider, providesScreenTypeProvider, providesEventsPerMonthViewDayProvider, providesScheduleProvider);
            dayDrawableProvider = new DayView_DayDrawable_Factory(seedInstanceProvider, providesEventsPerMonthViewDayProvider, providesIsRtlProvider, providesScreenTypeProvider, providesIsPortraitProvider, layoutDimensProvider, providesDimensConverterProvider);
            dayViewProvider = new DayView_Factory(seedInstanceProvider, dayDrawableProvider);
            monthDayViewHolderProvider = new MonthDayViewHolder_Factory(seedInstanceProvider, timeUtilsProvider, TimeBoxItemAdapter_Factory.INSTANCE, monthAdapterProvider, providesCurrentJulianDayProvider, providesDayClickCallbackProvider, providesEventsPerMonthViewDayProvider, providesDataSetChangedObservableProvider, providesIsA11yEnabledProvider, providesAlternateCalendarHelperProvider, dayViewProvider);
            monthBannerViewProvider = new MonthBannerView_Factory(seedInstanceProvider, providesIsRtlProvider, providesScheduleProvider, providesScreenTypeProvider, providesDimensConverterProvider);
            monthBannerViewHolderProvider = new MonthBannerViewHolder_Factory(monthBannerViewProvider, timeUtilsProvider, providesScheduleProvider, providesScreenTypeProvider);
            weekBannerViewHolderProvider = new WeekBannerViewHolder_Factory(seedInstanceProvider, timeUtilsProvider, providesScheduleProvider, providesShouldShowWeekNumbersProvider, providesWeekBannerClickCallbackProvider);
            defaultBundleFactoryProvider = new DefaultBundleFactory_Factory(timeUtilsProvider);
            providesEmptyDayClickListenerProvider = DoubleCheck.provider(new AllInOneActivityModule_ProvidesEmptyDayClickListenerFactory(seedInstanceProvider, defaultBundleFactoryProvider));
            nothingPlannedBannerViewHolderProvider = new NothingPlannedBannerViewHolder_Factory(seedInstanceProvider, providesScheduleProvider, providesEmptyDayClickListenerProvider);
            nowLineDrawableProvider = new NowLineViewHolder_NowLineDrawable_Factory(seedInstanceProvider, layoutDimensProvider, providesIsRtlProvider);
            nowLineViewHolderProvider = new NowLineViewHolder_Factory(seedInstanceProvider, nowLineDrawableProvider);
            allDayClickGuardHolderProvider = new AllDayClickGuardHolder_Factory(seedInstanceProvider);
            providesColumnGridOffsetProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesColumnGridOffsetFactory(er.providesModule));
            columnDimensProvider = new ColumnDimens_Factory(providesScreenTypeProvider, providesIsPortraitProvider, providesDimensConverterProvider, layoutDimensProvider);
            columnViewportProvider = DoubleCheck.provider(new ColumnViewport_Factory(timeUtilsProvider, providesIsRtlProvider));
            dragGuideManagerProvider = DoubleCheck.provider(DragGuideManager_Factory.INSTANCE);
            hoursDrawableImplProvider = new HoursDrawableImpl_Factory(seedInstanceProvider, providesIsRtlProvider, providesColumnGridOffsetProvider, columnDimensProvider, columnViewportProvider, providesDimensConverterProvider, dragGuideManagerProvider, timeUtilsProvider);
            hoursViewHolderProvider = new HoursViewHolder_Factory(seedInstanceProvider, hoursDrawableImplProvider);
            adapterConverterProvider = DoubleCheck.provider(new AdapterConverter_Factory(timeUtilsProvider, TimeBoxItemAdapter_Factory.INSTANCE));
            daggercalendarapplicationcomponent = (DelegateFactory)timelineAdapterImplProvider;
            timelineAdapterImplProvider = DoubleCheck.provider(new TimelineAdapterImpl_Factory(timeUtilsProvider, providesDragStateProvider, providesCreateEventPhantomAdapterEventProvider, providesCalendarContentStoreProvider, providesIdleObservableProvider, allDayExpandViewHolderProvider, allDayMoreViewHolderProvider, eventViewHolderProvider, dayHeaderViewHolderProvider, monthDayViewHolderProvider, monthBannerViewHolderProvider, weekBannerViewHolderProvider, nothingPlannedBannerViewHolderProvider, nowLineViewHolderProvider, allDayClickGuardHolderProvider, hoursViewHolderProvider, adapterConverterProvider, monthAdapterProvider));
            setDelegatedProvider(timelineAdapterImplProvider);
            allDayManagerProvider = DoubleCheck.provider(new AllDayManager_Factory(timelineRecyclerViewProvider, timelineAdapterImplProvider, columnViewportProvider, columnDimensProvider, layoutDimensProvider, providesAllDayExpandedObservableProvider, idleTrackerProvider, providesIsVisibleSupplierProvider));
            valueAnimatorFutureProvider = new ValueAnimatorFuture_Factory(idleTrackerProvider);
            viewportAnimatorProvider = new ViewportAnimator_Factory(seedInstanceProvider, valueAnimatorFutureProvider, idleTrackerProvider);
            providesGridMsPerVerticalPxProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesGridMsPerVerticalPxFactory(er.providesModule, seedInstanceProvider, providesDimensConverterProvider));
            columnViewportControllerProvider = DoubleCheck.provider(new ColumnViewportController_Factory(columnViewportProvider, providesColumnGridOffsetProvider, timelineRecyclerViewProvider, providesDimensConverterProvider, providesIsRtlProvider, timeUtilsProvider, viewportAnimatorProvider, providesGridMsPerVerticalPxProvider));
            columnLayoutUpdaterProvider = new ColumnLayoutUpdater_Factory(timelineRecyclerViewProvider, providesIsRtlProvider, providesIsA11yEnabledProvider, providesDimensConverterProvider, timeUtilsProvider, providesCurrentTimeProvider, providesCreateEventPhantomAdapterEventProvider, providesDragStateProvider, timelineAdapterImplProvider, allDayManagerProvider, columnViewportProvider, columnViewportControllerProvider, providesColumnGridOffsetProvider, TimeBoxItemAdapter_Factory.INSTANCE, columnDimensProvider, layoutDimensProvider, providesScreenTypeProvider, providesShouldShowWeekNumbersProvider, providesScheduleProvider, providesCreationModeProvider);
            providesViewportRangeObservableProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesViewportRangeObservableFactory(er.providesModule));
            createPhantomUpdaterProvider = DoubleCheck.provider(new CreatePhantomUpdater_Factory(providesCreationModeProvider, timelineRecyclerViewProvider, providesCreateEventPhantomAdapterEventProvider));
            columnLayoutImplProvider = DoubleCheck.provider(new ColumnLayoutImpl_Factory(columnLayoutUpdaterProvider, timelineRecyclerViewProvider, allDayManagerProvider, columnViewportProvider, columnViewportControllerProvider, providesViewportRangeObservableProvider, providesSelectedDayObservableProvider, layoutDimensProvider, providesScreenTypeProvider, timeUtilsProvider, providesCurrentTimeProvider, providesShouldShowWeekNumbersProvider, createPhantomUpdaterProvider));
            columnBackgroundDrawableProvider = DoubleCheck.provider(new ColumnBackgroundDrawable_Factory(seedInstanceProvider, providesDimensConverterProvider, layoutDimensProvider, columnDimensProvider, timeUtilsProvider, columnViewportProvider, providesCurrentTimeProvider, providesColumnGridOffsetProvider, providesScreenTypeProvider, providesIsPortraitProvider, providesIsRtlProvider, providesShouldShowWeekNumbersProvider));
            providesDragOffsetProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesDragOffsetFactory(er.providesModule));
            dragScrollPageControllerFactoryProvider = new DragScrollPageControllerFactory_Factory(seedInstanceProvider);
            creationItemToEventAdapterProvider = new CreationItemToEventAdapter_Factory(TimeBoxItemAdapter_Factory.INSTANCE, timeUtilsProvider, providesDefaultDurationProvider);
            providesCreateEventPhantomItemProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesCreateEventPhantomItemFactory(er.providesModule, providesCreateEventPhantomAdapterEventProvider, creationItemToEventAdapterProvider));
            creationHandlerImplProvider = DoubleCheck.provider(new CreationHandlerImpl_Factory(seedInstanceProvider, timeUtilsProvider, providesDefaultDurationProvider, providesCreateEventPhantomItemProvider, providesDefaultCalendarColorProvider));
            columnOnDragListenerProvider = DoubleCheck.provider(new ColumnOnDragListener_Factory(providesDragStateProvider, timelineRecyclerViewProvider, timelineAdapterImplProvider, timeUtilsProvider, providesIsRtlProvider, providesDragOffsetProvider, providesColumnGridOffsetProvider, columnViewportProvider, columnDimensProvider, columnViewportControllerProvider, TimeBoxItemAdapter_Factory.INSTANCE, alternateTimelineRescheduleManagerProvider, dragGuideManagerProvider, dragScrollPageControllerFactoryProvider, idleTrackerProvider, providesCalendarContentStoreProvider, providesCreateEventPhantomItemProvider, creationHandlerImplProvider));
            columnOnTapListenerProvider = DoubleCheck.provider(new ColumnOnTapListener_Factory(timelineRecyclerViewProvider, columnViewportProvider, providesColumnGridOffsetProvider, creationHandlerImplProvider, timeUtilsProvider, providesIsRtlProvider, columnDimensProvider, providesIsTalkBackEnabledProvider));
            scheduleDayFactoryProvider = new ScheduleDayFactory_Factory(TimeBoxItemAdapter_Factory.INSTANCE, providesScheduleProvider, providesScreenTypeProvider, providesIsRtlProvider, timeUtilsProvider, timelineRecyclerViewProvider, providesDimensConverterProvider, layoutDimensProvider);
            scheduleCacheProvider = new ScheduleCache_Factory(timeUtilsProvider, providesCurrentTimeProvider, timelineAdapterImplProvider, scheduleDayFactoryProvider);
            alternateTimelineAnimatedToolbarTitleHelperProvider = new AlternateTimelineAnimatedToolbarTitleHelper_Factory(seedInstanceProvider, providesScreenTypeProvider);
            providesToolbarTitleHelperProvider = DoubleCheck.provider(alternateTimelineAnimatedToolbarTitleHelperProvider);
            scheduleLayoutImplProvider = DoubleCheck.provider(new ScheduleLayoutImpl_Factory(timelineRecyclerViewProvider, scheduleCacheProvider, timeUtilsProvider, providesCurrentTimeProvider, providesViewportRangeObservableProvider, providesSelectedDayObservableProvider, viewportAnimatorProvider, layoutDimensProvider, providesIsTalkBackEnabledProvider, providesIsA11yEnabledProvider, providesToolbarTitleHelperProvider, providesScreenTypeProvider, MonthLabelThresholdEvaluator_Factory.INSTANCE));
            monthViewportProvider = DoubleCheck.provider(new MonthViewport_Factory(yearMonthHelperProvider, providesIsRtlProvider, providesScreenTypeProvider, providesShouldShowWeekNumbersProvider, providesCurrentTimeProvider, layoutDimensProvider, providesDimensConverterProvider));
            monthViewportControllerProvider = DoubleCheck.provider(new MonthViewportController_Factory(monthViewportProvider, viewportAnimatorProvider));
            monthLayoutImplProvider = DoubleCheck.provider(new MonthLayoutImpl_Factory(timelineRecyclerViewProvider, timelineAdapterImplProvider, monthViewportProvider, monthViewportControllerProvider, providesViewportRangeObservableProvider, providesSelectedDayObservableProvider, providesEventsPerMonthViewDayProvider, TimeBoxItemAdapter_Factory.INSTANCE, yearMonthHelperProvider, timeUtilsProvider, layoutDimensProvider, providesDimensConverterProvider, providesIsRtlProvider, providesScreenTypeProvider, providesCurrentTimeProvider));
            weekdayNamesProvider = new WeekdayNames_Factory(providesScreenTypeProvider, timeUtilsProvider);
            monthBackgroundDrawableProvider = DoubleCheck.provider(new MonthBackgroundDrawable_Factory(seedInstanceProvider, timeUtilsProvider, layoutDimensProvider, monthViewportProvider, yearMonthHelperProvider, weekdayNamesProvider, providesCurrentTimeProvider, providesWeeksInMonthProvider, providesIsRtlProvider, providesScreenTypeProvider, providesIsPortraitProvider, providesShouldShowWeekNumbersProvider, providesDimensConverterProvider));
            providesVirtualViewsSupplierProvider = new TimelineProvidesModule_ProvidesVirtualViewsSupplierFactory(er.providesModule, layoutUpdaterImplProvider);
            timelineAccessibilityDelegateProvider = DoubleCheck.provider(new TimelineAccessibilityDelegate_Factory(layoutManagerImplProvider, providesVirtualViewsSupplierProvider, idleTrackerProvider));
            recyclerViewAccessibilityDelegateHelperProvider = DoubleCheck.provider(new RecyclerViewAccessibilityDelegateHelper_Factory(timelineRecyclerViewProvider, timelineAccessibilityDelegateProvider));
            daggercalendarapplicationcomponent = (DelegateFactory)timelineApiImplProvider;
            timelineApiImplProvider = DoubleCheck.provider(new TimelineApiImpl_Factory(timelineRecyclerViewProvider, layoutManagerImplProvider, layoutManagerImplProvider, timeUtilsProvider, providesCurrentTimeProvider, providesIdleObservableProvider, columnLayoutImplProvider, columnBackgroundDrawableProvider, columnOnDragListenerProvider, columnOnTapListenerProvider, providesCreateEventPhantomAdapterEventProvider, providesCreationModeProvider, providesViewportRangeObservableProvider, providesCalendarContentStoreProvider, scheduleLayoutImplProvider, monthLayoutImplProvider, monthBackgroundDrawableProvider, timelineAdapterImplProvider, providesDragOffsetProvider, recyclerViewAccessibilityDelegateHelperProvider, providesIsA11yEnabledProvider));
            setDelegatedProvider(timelineApiImplProvider);
            return;
        }
    }
}
