// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.RegularImmutableMap;
import dagger.android.DispatchingAndroidInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar:
//            CalendarApplicationComponent, CalendarApplicationPropertiesManager_Factory, CalendarApplicationPropertiesModule_ProvidesAlternateTimelineEnabledFactory, CalendarApplicationPropertiesModule_ProvidesTimeZoneObservableFactory, 
//            CalendarApplicationPropertiesModule_ProvidesFirstDayOfWeekFactory, CalendarApplicationPropertiesModule_ProvidesHideDeclinedEventsFactory, CalendarApplicationPropertiesModule_ProvidesShouldShowMonthIllustrationsFactory, CalendarApplicationPropertiesModule_ProvidesShouldShowWeekNumbersFactory, 
//            CalendarApplicationPropertiesModule_ProvidesCurrentTimeFactory, CalendarApplicationPropertiesModule_ProvidesIsA11yEnabledFactory, CalendarApplicationPropertiesModule_ProvidesIsTalkBackEnabledFactory, CalendarApplicationPropertiesModule_ProvidesCurrentJulianDayFactory, 
//            CalendarApplicationPropertiesModule_ProvidesDefaultDurationFactory, CalendarApplicationPropertiesModule_ProvidesDefaultCalendarColorFactory, CalendarApplication, AllInOneCalendarActivity, 
//            CalendarApplicationPropertiesManager

public final class DaggerCalendarApplicationComponent
    implements CalendarApplicationComponent
{

    private Provider allInOneCalendarActivitySubcomponentBuilderProvider;
    private Provider applicationProvider;
    public Provider calendarApplicationPropertiesManagerProvider;
    public CalendarApplicationPropertiesModule_ProvidesAlternateTimelineEnabledFactory providesAlternateTimelineEnabledProvider;
    public Provider providesCurrentJulianDayProvider;
    public Provider providesCurrentTimeProvider;
    public CalendarApplicationPropertiesModule_ProvidesDefaultCalendarColorFactory providesDefaultCalendarColorProvider;
    public CalendarApplicationPropertiesModule_ProvidesDefaultDurationFactory providesDefaultDurationProvider;
    public CalendarApplicationPropertiesModule_ProvidesFirstDayOfWeekFactory providesFirstDayOfWeekProvider;
    public CalendarApplicationPropertiesModule_ProvidesHideDeclinedEventsFactory providesHideDeclinedEventsProvider;
    public CalendarApplicationPropertiesModule_ProvidesIsA11yEnabledFactory providesIsA11yEnabledProvider;
    public CalendarApplicationPropertiesModule_ProvidesIsTalkBackEnabledFactory providesIsTalkBackEnabledProvider;
    public CalendarApplicationPropertiesModule_ProvidesShouldShowMonthIllustrationsFactory providesShouldShowMonthIllustrationsProvider;
    public CalendarApplicationPropertiesModule_ProvidesShouldShowWeekNumbersFactory providesShouldShowWeekNumbersProvider;
    public Provider providesTimeZoneObservableProvider;

    DaggerCalendarApplicationComponent(Builder builder)
    {
        allInOneCalendarActivitySubcomponentBuilderProvider = new _cls1();
        builder = builder.application;
        if (builder == null)
        {
            throw new NullPointerException("instance cannot be null");
        } else
        {
            applicationProvider = new InstanceFactory(builder);
            calendarApplicationPropertiesManagerProvider = DoubleCheck.provider(new CalendarApplicationPropertiesManager_Factory(applicationProvider));
            providesAlternateTimelineEnabledProvider = new CalendarApplicationPropertiesModule_ProvidesAlternateTimelineEnabledFactory(applicationProvider);
            providesTimeZoneObservableProvider = DoubleCheck.provider(new CalendarApplicationPropertiesModule_ProvidesTimeZoneObservableFactory(calendarApplicationPropertiesManagerProvider));
            providesFirstDayOfWeekProvider = new CalendarApplicationPropertiesModule_ProvidesFirstDayOfWeekFactory(calendarApplicationPropertiesManagerProvider);
            providesHideDeclinedEventsProvider = new CalendarApplicationPropertiesModule_ProvidesHideDeclinedEventsFactory(calendarApplicationPropertiesManagerProvider);
            providesShouldShowMonthIllustrationsProvider = new CalendarApplicationPropertiesModule_ProvidesShouldShowMonthIllustrationsFactory(calendarApplicationPropertiesManagerProvider);
            providesShouldShowWeekNumbersProvider = new CalendarApplicationPropertiesModule_ProvidesShouldShowWeekNumbersFactory(calendarApplicationPropertiesManagerProvider);
            providesCurrentTimeProvider = DoubleCheck.provider(CalendarApplicationPropertiesModule_ProvidesCurrentTimeFactory.INSTANCE);
            providesIsA11yEnabledProvider = new CalendarApplicationPropertiesModule_ProvidesIsA11yEnabledFactory(calendarApplicationPropertiesManagerProvider);
            providesIsTalkBackEnabledProvider = new CalendarApplicationPropertiesModule_ProvidesIsTalkBackEnabledFactory(calendarApplicationPropertiesManagerProvider);
            providesCurrentJulianDayProvider = DoubleCheck.provider(new CalendarApplicationPropertiesModule_ProvidesCurrentJulianDayFactory(applicationProvider, providesCurrentTimeProvider));
            providesDefaultDurationProvider = new CalendarApplicationPropertiesModule_ProvidesDefaultDurationFactory(calendarApplicationPropertiesManagerProvider);
            providesDefaultCalendarColorProvider = new CalendarApplicationPropertiesModule_ProvidesDefaultCalendarColorFactory(calendarApplicationPropertiesManagerProvider);
            return;
        }
    }

    public final void inject(Object obj)
    {
        obj = (CalendarApplication)obj;
        Provider provider = allInOneCalendarActivitySubcomponentBuilderProvider;
        CollectPreconditions.checkEntryNotNull(com/google/android/calendar/AllInOneCalendarActivity, provider);
        obj.dispatchingAndroidInjector = new DispatchingAndroidInjector(RegularImmutableMap.create(1, new Object[] {
            com/google/android/calendar/AllInOneCalendarActivity, provider
        }));
        obj.propertiesManager = (CalendarApplicationPropertiesManager)calendarApplicationPropertiesManagerProvider.get();
    }

    private class _cls1
        implements Provider
    {

        private final DaggerCalendarApplicationComponent this$0;

        public final Object get()
        {
            return new AllInOneCalendarActivitySubcomponentBuilder();
        }

        _cls1()
        {
            this$0 = DaggerCalendarApplicationComponent.this;
            super();
        }

        private class AllInOneCalendarActivitySubcomponentBuilder extends AllInOneCalendarActivity_Module_ContributesAllInOneCalendarActivityInjector.AllInOneCalendarActivitySubcomponent.Builder
        {

            public com.google.android.apps.calendar.timeline.alternate.view.timebox.TimeBoxLayoutModule.ProvidesModule providesModule;
            public AllInOneCalendarActivity seedInstance;
            private final DaggerCalendarApplicationComponent this$0;

            public final AndroidInjector build()
            {
                if (providesModule == null)
                {
                    providesModule = new com.google.android.apps.calendar.timeline.alternate.view.timebox.TimeBoxLayoutModule.ProvidesModule();
                }
                if (seedInstance == null)
                {
                    throw new IllegalStateException(String.valueOf(com/google/android/calendar/AllInOneCalendarActivity.getCanonicalName()).concat(" must be set"));
                } else
                {
                    return new AllInOneCalendarActivitySubcomponentImpl(this);
                }
            }

            public final void seedInstance(Object obj)
            {
                obj = (AllInOneCalendarActivity)obj;
                if (obj == null)
                {
                    throw new NullPointerException();
                } else
                {
                    seedInstance = (AllInOneCalendarActivity)obj;
                    return;
                }
            }

            AllInOneCalendarActivitySubcomponentBuilder()
            {
                this$0 = DaggerCalendarApplicationComponent.this;
                super();
            }

            private class AllInOneCalendarActivitySubcomponentImpl
                implements AllInOneCalendarActivity_Module_ContributesAllInOneCalendarActivityInjector.AllInOneCalendarActivitySubcomponent
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

                AllInOneCalendarActivitySubcomponentImpl(AllInOneCalendarActivitySubcomponentBuilder allinonecalendaractivitysubcomponentbuilder)
                {
                    this$0 = DaggerCalendarApplicationComponent.this;
                    super();
                    class _cls1
                        implements Provider
                    {

                        private final AllInOneCalendarActivitySubcomponentImpl this$1;

                        public final Object get()
                        {
                            class TaskBundleFragmentSubcomponentBuilder extends AllInOneActivityModule_ContributeTaskBundleFragment.TaskBundleFragmentSubcomponent.Builder
                            {

                                private TaskBundleFragment seedInstance;
                                private final AllInOneCalendarActivitySubcomponentImpl this$1;

                                public final AndroidInjector build()
                                {
                                    class TaskBundleFragmentSubcomponentImpl
                                        implements AllInOneActivityModule_ContributeTaskBundleFragment.TaskBundleFragmentSubcomponent
                                    {

                                        private final AllInOneCalendarActivitySubcomponentImpl this$1;

                                        public final void inject(Object obj)
                                        {
                                            ((TaskBundleFragment)obj).contentStore = optionalCalendarContentStoreProvider;
                                        }

                                            TaskBundleFragmentSubcomponentImpl()
                                            {
                                                this$1 = AllInOneCalendarActivitySubcomponentImpl.this;
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
                                        this$1 = AllInOneCalendarActivitySubcomponentImpl.this;
                                        super();
                                    }
                            }

                            return new TaskBundleFragmentSubcomponentBuilder();
                        }

                        _cls1()
                        {
                            this$1 = AllInOneCalendarActivitySubcomponentImpl.this;
                            super();
                        }
                    }

                    taskBundleFragmentSubcomponentBuilderProvider = new _cls1();
                    class _cls2
                        implements Provider
                    {

                        private final AllInOneCalendarActivitySubcomponentImpl this$1;

                        public final Object get()
                        {
                            class AlternateTimelineFragmentSubcomponentBuilder extends com.google.android.apps.calendar.timeline.alternate.fragment.impl.AlternateTimelineFragmentSubcomponent.Builder
                            {

                                private AlternateTimelineFragment seedInstance;
                                private final AllInOneCalendarActivitySubcomponentImpl this$1;

                                public final AndroidInjector build()
                                {
                                    class AlternateTimelineFragmentSubcomponentImpl
                                        implements AlternateTimelineFragmentSubcomponent
                                    {

                                        private final AllInOneCalendarActivitySubcomponentImpl this$1;

                                        public final void inject(Object obj)
                                        {
                                            obj = (AlternateTimelineFragment)obj;
                                            obj.controller = (TimelineApi)timelineApiImplProvider.get();
                                            Object obj1 = AllInOneCalendarActivitySubcomponentImpl.this;
                                            obj.timeUpdater = new TimeUpdater(new TimeUtils((ObservableReference)((AllInOneCalendarActivitySubcomponentImpl) (obj1))._fld0.providesTimeZoneObservableProvider.get(), CalendarApplicationPropertiesModule_ProvidesFirstDayOfWeekFactory.proxyProvidesFirstDayOfWeek(((AllInOneCalendarActivitySubcomponentImpl) (obj1))._fld0.calendarApplicationPropertiesManagerProvider.get())), (ObservableReference)providesCurrentTimeProvider.get(), (ObservableReference)providesCurrentJulianDayProvider.get());
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
                                                this$1 = AllInOneCalendarActivitySubcomponentImpl.this;
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
                                        this$1 = AllInOneCalendarActivitySubcomponentImpl.this;
                                        super();
                                    }
                            }

                            return new AlternateTimelineFragmentSubcomponentBuilder();
                        }

                        _cls2()
                        {
                            this$1 = AllInOneCalendarActivitySubcomponentImpl.this;
                            super();
                        }
                    }

                    alternateTimelineFragmentSubcomponentBuilderProvider = new _cls2();
                    daggercalendarapplicationcomponent = allinonecalendaractivitysubcomponentbuilder.seedInstance;
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
                        seedInstance = allinonecalendaractivitysubcomponentbuilder.seedInstance;
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
                        providesWeeksInMonthProvider = new TimelineProvidesModule_ProvidesWeeksInMonthFactory(allinonecalendaractivitysubcomponentbuilder.providesModule);
                        timelineRecyclerViewProvider = DoubleCheck.provider(new TimelineRecyclerView_Factory(seedInstanceProvider, providesWeeksInMonthProvider, providesIsA11yEnabledProvider));
                        idleTrackerProvider = DoubleCheck.provider(IdleTracker_Factory.INSTANCE);
                        animatorSetFutureProvider = new AnimatorSetFuture_Factory(idleTrackerProvider);
                        providesIsVisibleSupplierProvider = new AllInOneActivityModule_ProvidesIsVisibleSupplierFactory(seedInstanceProvider);
                        layoutUpdaterImplProvider = DoubleCheck.provider(new LayoutUpdaterImpl_Factory(timelineRecyclerViewProvider, animatorSetFutureProvider, providesIsA11yEnabledProvider, providesViewportFullyLoadedProvider, providesIsVisibleSupplierProvider));
                        layoutManagerImplProvider = DoubleCheck.provider(new LayoutManagerImpl_Factory(layoutUpdaterImplProvider, idleTrackerProvider));
                        providesIdleObservableProvider = new TimelineProvidesModule_ProvidesIdleObservableFactory(allinonecalendaractivitysubcomponentbuilder.providesModule, idleTrackerProvider);
                        providesCreateEventPhantomAdapterEventProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesCreateEventPhantomAdapterEventFactory(allinonecalendaractivitysubcomponentbuilder.providesModule));
                        providesDragStateProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesDragStateFactory(allinonecalendaractivitysubcomponentbuilder.providesModule));
                        providesAllDayExpandedObservableProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesAllDayExpandedObservableFactory(allinonecalendaractivitysubcomponentbuilder.providesModule));
                        expandButtonDrawableProvider = new AllDayExpandViewHolder_ExpandButtonDrawable_Factory(seedInstanceProvider, providesDimensConverterProvider, providesScreenTypeProvider);
                        allDayExpandViewHolderProvider = new AllDayExpandViewHolder_Factory(seedInstanceProvider, providesAllDayExpandedObservableProvider, expandButtonDrawableProvider);
                        timelineAdapterImplProvider = new DelegateFactory();
                        providesDataSetChangedObservableProvider = new TimelineProvidesModule_ProvidesDataSetChangedObservableFactory(allinonecalendaractivitysubcomponentbuilder.providesModule, timelineAdapterImplProvider);
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
                        providesEventsPerMonthViewDayProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesEventsPerMonthViewDayFactory(allinonecalendaractivitysubcomponentbuilder.providesModule));
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
                        providesColumnGridOffsetProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesColumnGridOffsetFactory(allinonecalendaractivitysubcomponentbuilder.providesModule));
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
                        providesGridMsPerVerticalPxProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesGridMsPerVerticalPxFactory(allinonecalendaractivitysubcomponentbuilder.providesModule, seedInstanceProvider, providesDimensConverterProvider));
                        columnViewportControllerProvider = DoubleCheck.provider(new ColumnViewportController_Factory(columnViewportProvider, providesColumnGridOffsetProvider, timelineRecyclerViewProvider, providesDimensConverterProvider, providesIsRtlProvider, timeUtilsProvider, viewportAnimatorProvider, providesGridMsPerVerticalPxProvider));
                        columnLayoutUpdaterProvider = new ColumnLayoutUpdater_Factory(timelineRecyclerViewProvider, providesIsRtlProvider, providesIsA11yEnabledProvider, providesDimensConverterProvider, timeUtilsProvider, providesCurrentTimeProvider, providesCreateEventPhantomAdapterEventProvider, providesDragStateProvider, timelineAdapterImplProvider, allDayManagerProvider, columnViewportProvider, columnViewportControllerProvider, providesColumnGridOffsetProvider, TimeBoxItemAdapter_Factory.INSTANCE, columnDimensProvider, layoutDimensProvider, providesScreenTypeProvider, providesShouldShowWeekNumbersProvider, providesScheduleProvider, providesCreationModeProvider);
                        providesViewportRangeObservableProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesViewportRangeObservableFactory(allinonecalendaractivitysubcomponentbuilder.providesModule));
                        createPhantomUpdaterProvider = DoubleCheck.provider(new CreatePhantomUpdater_Factory(providesCreationModeProvider, timelineRecyclerViewProvider, providesCreateEventPhantomAdapterEventProvider));
                        columnLayoutImplProvider = DoubleCheck.provider(new ColumnLayoutImpl_Factory(columnLayoutUpdaterProvider, timelineRecyclerViewProvider, allDayManagerProvider, columnViewportProvider, columnViewportControllerProvider, providesViewportRangeObservableProvider, providesSelectedDayObservableProvider, layoutDimensProvider, providesScreenTypeProvider, timeUtilsProvider, providesCurrentTimeProvider, providesShouldShowWeekNumbersProvider, createPhantomUpdaterProvider));
                        columnBackgroundDrawableProvider = DoubleCheck.provider(new ColumnBackgroundDrawable_Factory(seedInstanceProvider, providesDimensConverterProvider, layoutDimensProvider, columnDimensProvider, timeUtilsProvider, columnViewportProvider, providesCurrentTimeProvider, providesColumnGridOffsetProvider, providesScreenTypeProvider, providesIsPortraitProvider, providesIsRtlProvider, providesShouldShowWeekNumbersProvider));
                        providesDragOffsetProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesDragOffsetFactory(allinonecalendaractivitysubcomponentbuilder.providesModule));
                        dragScrollPageControllerFactoryProvider = new DragScrollPageControllerFactory_Factory(seedInstanceProvider);
                        creationItemToEventAdapterProvider = new CreationItemToEventAdapter_Factory(TimeBoxItemAdapter_Factory.INSTANCE, timeUtilsProvider, providesDefaultDurationProvider);
                        providesCreateEventPhantomItemProvider = DoubleCheck.provider(new TimelineProvidesModule_ProvidesCreateEventPhantomItemFactory(allinonecalendaractivitysubcomponentbuilder.providesModule, providesCreateEventPhantomAdapterEventProvider, creationItemToEventAdapterProvider));
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
                        providesVirtualViewsSupplierProvider = new TimelineProvidesModule_ProvidesVirtualViewsSupplierFactory(allinonecalendaractivitysubcomponentbuilder.providesModule, layoutUpdaterImplProvider);
                        timelineAccessibilityDelegateProvider = DoubleCheck.provider(new TimelineAccessibilityDelegate_Factory(layoutManagerImplProvider, providesVirtualViewsSupplierProvider, idleTrackerProvider));
                        recyclerViewAccessibilityDelegateHelperProvider = DoubleCheck.provider(new RecyclerViewAccessibilityDelegateHelper_Factory(timelineRecyclerViewProvider, timelineAccessibilityDelegateProvider));
                        daggercalendarapplicationcomponent = (DelegateFactory)timelineApiImplProvider;
                        timelineApiImplProvider = DoubleCheck.provider(new TimelineApiImpl_Factory(timelineRecyclerViewProvider, layoutManagerImplProvider, layoutManagerImplProvider, timeUtilsProvider, providesCurrentTimeProvider, providesIdleObservableProvider, columnLayoutImplProvider, columnBackgroundDrawableProvider, columnOnDragListenerProvider, columnOnTapListenerProvider, providesCreateEventPhantomAdapterEventProvider, providesCreationModeProvider, providesViewportRangeObservableProvider, providesCalendarContentStoreProvider, scheduleLayoutImplProvider, monthLayoutImplProvider, monthBackgroundDrawableProvider, timelineAdapterImplProvider, providesDragOffsetProvider, recyclerViewAccessibilityDelegateHelperProvider, providesIsA11yEnabledProvider));
                        setDelegatedProvider(timelineApiImplProvider);
                        return;
                    }
                }
            }

        }

    }


    private class Builder
        implements CalendarApplicationComponent.Builder
    {

        public Application application;

        public final CalendarApplicationComponent.Builder application(Application application1)
        {
            if (application1 == null)
            {
                throw new NullPointerException();
            } else
            {
                application = (Application)application1;
                return this;
            }
        }

        public final CalendarApplicationComponent build()
        {
            if (application == null)
            {
                throw new IllegalStateException(String.valueOf(android/app/Application.getCanonicalName()).concat(" must be set"));
            } else
            {
                return new DaggerCalendarApplicationComponent(this);
            }
        }

        Builder()
        {
        }
    }

}
