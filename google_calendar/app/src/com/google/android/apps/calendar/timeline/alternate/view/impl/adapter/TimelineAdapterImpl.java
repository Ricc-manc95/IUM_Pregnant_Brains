// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.util.YearMonth;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.ColumnDragState;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.EventViewPositionHelper;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Optional;
import com.google.common.base.VerifyException;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            TimelineAdapter, AdapterWeek, AdapterDay, AdapterEvent, 
//            AdapterConverter, MonthAdapter, TimelineAdapterViewHolderImpl, EventViewHolder, 
//            AllDayMoreViewHolder, DayHeaderViewHolder, MonthDayViewHolder, MonthBannerViewHolder, 
//            MonthBannerView, WeekBannerViewHolder, NothingPlannedBannerViewHolder, AdapterMonth

public final class TimelineAdapterImpl extends android.support.v7.widget.RecyclerView.Adapter
    implements TimelineAdapter
{

    private final Provider allDayClickGuardHolderProvider;
    private final Provider allDayExpandViewHolder;
    private final Provider allDayMoreViewHolderProvider;
    public final AdapterConverter converter;
    private final ObservableReference createEventPhantom;
    public final ObservableReference dataSetChangedObservable;
    private final Provider dayHeaderViewHolderProvider;
    private final ObservableReference dragStateObservable;
    private final Provider eventViewHolderProvider;
    private final Provider hoursProvider;
    public final SparseArray julianWeekToWeek = new SparseArray();
    public Object lastDataSetChanged;
    public final MonthAdapter monthAdapter;
    private final Provider monthBannerViewHolderProvider;
    private final Provider monthDayViewHolderProvider;
    private final Provider nothingPlannedBannerViewHolderProvider;
    private final Provider nowLineViewHolderProvider;
    public final SparseArray positionToEvent = new SparseArray();
    private final TimeUtils timeUtils;
    private final Provider weekBannerViewHolderProvider;

    public TimelineAdapterImpl(TimeUtils timeutils, ObservableReference observablereference, ObservableReference observablereference1, CalendarContentStore calendarcontentstore, ObservableReference observablereference2, Provider provider, Provider provider1, 
            Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, 
            Provider provider9, Provider provider10, AdapterConverter adapterconverter, MonthAdapter monthadapter)
    {
        lastDataSetChanged = new Object();
        dataSetChangedObservable = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(lastDataSetChanged);
        timeUtils = timeutils;
        converter = adapterconverter;
        dragStateObservable = observablereference;
        createEventPhantom = observablereference1;
        allDayExpandViewHolder = provider;
        allDayMoreViewHolderProvider = provider1;
        eventViewHolderProvider = provider2;
        dayHeaderViewHolderProvider = provider3;
        monthDayViewHolderProvider = provider4;
        monthBannerViewHolderProvider = provider5;
        weekBannerViewHolderProvider = provider6;
        nothingPlannedBannerViewHolderProvider = provider7;
        nowLineViewHolderProvider = provider8;
        allDayClickGuardHolderProvider = provider9;
        hoursProvider = provider10;
        monthAdapter = monthadapter;
        class .Lambda._cls0
            implements Consumer
        {

            private final TimelineAdapterImpl arg$1;

            public final void accept(Object obj)
            {
                TimelineAdapterImpl timelineadapterimpl;
                Iterator iterator;
                int i;
                timelineadapterimpl = arg$1;
                iterator = ((Collection)obj).iterator();
                i = 0;
_L6:
                int ai[];
                boolean aflag[];
                CalendarWeek calendarweek;
                AdapterWeek.Builder builder1;
                ArrayList arraylist1;
                AdapterConverter adapterconverter1;
                int i2;
                int j2;
                if (!iterator.hasNext())
                {
                    break; /* Loop/switch isn't completed */
                }
                calendarweek = (CalendarWeek)iterator.next();
                obj = (AdapterWeek)timelineadapterimpl.julianWeekToWeek.get(calendarweek.getJulianWeek());
                if (obj != null && ((AdapterWeek) (obj)).getCacheGeneration() == calendarweek.getCacheGeneration())
                {
                    continue; /* Loop/switch isn't completed */
                }
                adapterconverter1 = timelineadapterimpl.converter;
                builder1 = (new AutoValue_AdapterWeek.Builder()).setCacheGeneration(calendarweek.getCacheGeneration()).setJulianWeek(calendarweek.getJulianWeek());
                arraylist1 = new ArrayList(7);
                obj = new HashSet();
                ImmutableList immutablelist = (ImmutableList)calendarweek.getDays();
                int j = immutablelist.size();
                i = 0;
                Object obj3 = (UnmodifiableIterator)null;
                while (i < j) 
                {
                    obj3 = immutablelist.get(i);
                    i++;
                    ((Set) (obj)).addAll(Collections2.filter(new com.google.common.collect.Collections2.TransformedCollection(((CalendarDay)obj3).getItems(), AdapterConverter..Lambda._cls5.$instance), new com.google.common.base.Predicates.NotPredicate(new AdapterConverter..Lambda._cls3(adapterconverter1))));
                }
                HashMap hashmap = new HashMap();
                (new AdapterConverter._cls1(adapterconverter1, hashmap)).layoutEntries(((Collection) (obj)), new EventComparators..Lambda._cls2(adapterconverter1.adapter));
                obj = new HashSet();
                immutablelist = (ImmutableList)calendarweek.getDays();
                j = immutablelist.size();
                i = 0;
                obj3 = (UnmodifiableIterator)null;
                while (i < j) 
                {
                    Object obj4 = immutablelist.get(i);
                    i++;
                    ((Set) (obj)).addAll(new com.google.common.collect.Collections2.TransformedCollection(((CalendarDay)obj4).getItems(), AdapterConverter..Lambda._cls5.$instance));
                }
                HashMap hashmap1 = new HashMap();
                (new AdapterConverter._cls2(adapterconverter1, hashmap1)).layoutEntries(((Collection) (obj)), new EventComparators..Lambda._cls3(adapterconverter1.adapter));
                for (i = 0; i < 7; i++)
                {
                    CalendarDay calendarday = (CalendarDay)calendarweek.getDays().get(i);
                    obj = Lists.newArrayList(new com.google.common.collect.Collections2.TransformedCollection(calendarday.getItems(), new AdapterConverter..Lambda._cls2(adapterconverter1, calendarday)));
                    Object obj1 = adapterconverter1.timeUtils;
                    ArrayList arraylist = new ArrayList(Collections2.filter(((Collection) (obj)), AdapterConverter..Lambda._cls4.$instance));
                    (new GroupingChipGeometry(new OverlappingChipGeometry(new SimpleChipGeometry(new GraphChipGeometry(), ((TimeUtils) (obj1))), ((TimeUtils) (obj1))))).layoutChipsHorizontally(arraylist);
                    obj1 = new ArrayList();
                    arraylist = new ArrayList();
                    Iterator iterator1 = ((Collection) (obj)).iterator();
                    while (iterator1.hasNext()) 
                    {
                        Object obj11 = (AdapterEvent.Builder)iterator1.next();
                        obj = ((AdapterEvent.Builder) (obj11)).getItem();
                        Object obj12 = adapterconverter1.adapter.getKey(obj);
                        boolean flag = adapterconverter1.isTimedEvent(obj);
                        if (flag)
                        {
                            obj = null;
                        } else
                        {
                            obj = (Integer)hashmap.get(obj12);
                            if (obj == null)
                            {
                                throw new NullPointerException(Strings.lenientFormat("No grid placement for: %s", new Object[] {
                                    obj11
                                }));
                            }
                            obj = (Integer)obj;
                        }
                        obj12 = (Integer)hashmap1.get(obj12);
                        if (obj12 == null)
                        {
                            throw new NullPointerException(Strings.lenientFormat("No month placement for: %s", new Object[] {
                                obj11
                            }));
                        }
                        obj12 = (Integer)obj12;
                        obj11 = ((AdapterEvent.Builder) (obj11)).setGridAllDaySlot(((Integer) (obj))).setMonthSlot(((Integer) (obj12)).intValue()).build();
                        if (flag)
                        {
                            obj = arraylist;
                        } else
                        {
                            obj = obj1;
                        }
                        ((List) (obj)).add(obj11);
                    }
                    Collections.sort(((List) (obj1)), AdapterConverter..Lambda._cls0.$instance);
                    Collections.sort(arraylist, AdapterConverter..Lambda._cls1.$instance);
                    arraylist1.add((new .AutoValue_AdapterDay.Builder()).setLoaded(true).setCacheGeneration(calendarday.getCacheGeneration()).setJulianDay(calendarday.getJulianDate()).setAllDayEvents(ImmutableList.copyOf(((Collection) (obj1)))).setTimedEvents(ImmutableList.copyOf(arraylist)));
                }

                i2 = calendarweek.getJulianWeek();
                obj = adapterconverter1.adapter;
                j2 = i2 * 7 - (2 - ((Integer)adapterconverter1.timeUtils.firstDayOfWeek.get()).intValue());
                ai = new int[arraylist1.size()];
                for (i = 0; i < ai.length; i++)
                {
                    ImmutableList immutablelist1 = (ImmutableList)((AdapterDay.Builder)arraylist1.get(i)).getAllDayEvents();
                    int l2 = immutablelist1.size();
                    int k = 0;
                    UnmodifiableIterator unmodifiableiterator1 = (UnmodifiableIterator)null;
                    while (k < l2) 
                    {
                        Object obj9 = immutablelist1.get(k);
                        k++;
                        obj9 = (AdapterEvent)obj9;
                        ai[i] = Math.max(ai[i], ((AdapterEvent) (obj9)).getGridAllDaySlot().intValue());
                    }
                }

                aflag = new boolean[7];
                i = 0;
_L2:
                Object obj10;
                boolean flag1;
                if (i >= 7)
                {
                    break MISSING_BLOCK_LABEL_1100;
                }
                obj10 = ((AdapterDay.Builder)arraylist1.get(i)).getAllDayEvents();
                int l = ai[i];
                if (l < 2)
                {
                    break MISSING_BLOCK_LABEL_1094;
                }
                if (l <= 2)
                {
                    break; /* Loop/switch isn't completed */
                }
                flag1 = true;
_L3:
                aflag[i] = flag1;
                i++;
                if (true) goto _L2; else goto _L1
_L1:
                int i1;
                int i3;
                com.google.common.base.Predicate predicate = OverflowComputer..Lambda._cls0.$instance;
                obj10 = (AdapterEvent)Iterators.find(((Iterable) (obj10)).iterator(), predicate);
                i1 = ((ItemAdapter) (obj)).getStartDay(((AdapterEvent) (obj10)).getItem());
                i3 = ((ItemAdapter) (obj)).getEndDay(((AdapterEvent) (obj10)).getItem());
                if (i1 == i3)
                {
                    break MISSING_BLOCK_LABEL_1094;
                }
                i1 = Math.max(0, i1 - j2);
                i3 = Math.min(6, i3 - j2);
_L4:
label0:
                {
                    if (i1 > i3)
                    {
                        break MISSING_BLOCK_LABEL_1094;
                    }
                    if (ai[i1] <= 2)
                    {
                        break label0;
                    }
                    flag1 = true;
                }
                  goto _L3
                i1++;
                  goto _L4
                flag1 = false;
                  goto _L3
                obj = ImmutableList.builder();
                int k2 = ((Integer)adapterconverter1.timeUtils.firstDayOfWeek.get()).intValue();
                i = 0;
                while (i < 7) 
                {
                    com.google.common.collect.ImmutableList.Builder builder;
                    int j1;
                    if (aflag[i])
                    {
                        j1 = (i2 * 7 - (2 - k2)) + i + CalendarViewType.ALL_DAY_MORE_HEADER.minPosition;
                    } else
                    {
                        j1 = -1;
                    }
                    builder = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj)).add(((AdapterDay.Builder)arraylist1.get(i)).setAllDayOverflowPosition(j1).build());
                    i++;
                }
                obj.forceCopy = true;
                obj = builder1.setDays(ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj)).size)).build();
                timelineadapterimpl.julianWeekToWeek.put(calendarweek.getJulianWeek(), obj);
                Object obj2 = (ImmutableList)((AdapterWeek) (obj)).getDays();
                i2 = ((ImmutableList) (obj2)).size();
                i = 0;
                Object obj5 = (UnmodifiableIterator)null;
                for (; i < i2; i++)
                {
                    obj5 = (AdapterDay)((ImmutableList) (obj2)).get(i);
                    Object obj6 = (ImmutableList)((AdapterDay) (obj5)).getAllDayEvents();
                    k2 = ((ImmutableList) (obj6)).size();
                    int k1 = 0;
                    UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
                    while (k1 < k2) 
                    {
                        Object obj8 = ((ImmutableList) (obj6)).get(k1);
                        k1++;
                        obj8 = (AdapterEvent)obj8;
                        timelineadapterimpl.positionToEvent.put(((AdapterEvent) (obj8)).getPosition(), obj8);
                    }
                    obj5 = (ImmutableList)((AdapterDay) (obj5)).getTimedEvents();
                    k2 = ((ImmutableList) (obj5)).size();
                    k1 = 0;
                    obj6 = (UnmodifiableIterator)null;
                    while (k1 < k2) 
                    {
                        Object obj7 = ((ImmutableList) (obj5)).get(k1);
                        k1++;
                        obj7 = (AdapterEvent)obj7;
                        timelineadapterimpl.positionToEvent.put(((AdapterEvent) (obj7)).getPosition(), obj7);
                    }
                }

                obj5 = timelineadapterimpl.monthAdapter;
                obj2 = ((MonthAdapter) (obj5)).timeUtils;
                int l1 = ((AdapterWeek) (obj)).getJulianWeek() * 7 - (2 - ((Integer)((TimeUtils) (obj2)).firstDayOfWeek.get()).intValue());
                i2 = (l1 + 7) - 1;
                obj2 = ((MonthAdapter) (obj5)).yearMonthHelper;
                obj = new MonthAdapter..Lambda._cls0(((MonthAdapter) (obj5)), ((AdapterWeek) (obj)));
                obj5 = ((YearMonthHelper) (obj2)).createForMs(((YearMonthHelper) (obj2)).timeUtils.julianDateToMs(l1));
                i = ((YearMonth) (obj5)).getYear();
                i = (((YearMonth) (obj5)).getMonth() + i * 12) - 1;
                obj5 = ((YearMonthHelper) (obj2)).createForMs(((YearMonthHelper) (obj2)).timeUtils.julianDateToMs(i2));
                k2 = ((YearMonth) (obj5)).getYear();
                for (int j3 = ((YearMonth) (obj5)).getMonth(); i <= j3 + k2 * 12; i++)
                {
                    AutoValue_YearMonth autovalue_yearmonth = new AutoValue_YearMonth(i / 12, i % 12);
                    if (i2 >= ((YearMonthHelper) (obj2)).getFirstVisibleJulianDay(autovalue_yearmonth) && l1 <= (((YearMonthHelper) (obj2)).getFirstVisibleJulianDay(autovalue_yearmonth) + ((YearMonthHelper) (obj2)).weeksInMonth * 7) - 1)
                    {
                        ((Consumer) (obj)).accept(autovalue_yearmonth);
                    }
                }

                i = 1;
                if (true) goto _L6; else goto _L5
_L5:
                if (i != 0)
                {
                    timelineadapterimpl.dataSetChangedObservable.set(new Object());
                }
                return;
            }

            .Lambda._cls0()
            {
                arg$1 = TimelineAdapterImpl.this;
            }
        }

        calendarcontentstore.subscribe(new .Lambda._cls0(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        class .Lambda._cls1
            implements Consumer
        {

            private final TimelineAdapterImpl arg$1;
            private final ObservableReference arg$2;

            public final void accept(Object obj)
            {
                obj = arg$1;
                if (((Boolean)arg$2.get()).booleanValue() && ((TimelineAdapterImpl) (obj)).dataSetChangedObservable.get() != ((TimelineAdapterImpl) (obj)).lastDataSetChanged)
                {
                    obj.lastDataSetChanged = ((TimelineAdapterImpl) (obj)).dataSetChangedObservable.get();
                    ((android.support.v7.widget.RecyclerView.Adapter) (obj)).mObservable.notifyChanged();
                }
            }

            .Lambda._cls1(ObservableReference observablereference)
            {
                arg$1 = TimelineAdapterImpl.this;
                arg$2 = observablereference;
            }
        }

        timeutils = new .Lambda._cls1(observablereference2);
        observablereference2.subscribe(timeutils, new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN), true);
        dataSetChangedObservable.subscribe(timeutils, new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN), true);
    }

    public final ObservableReference getDataSetChangedObservable()
    {
        return dataSetChangedObservable;
    }

    public final AdapterDay getDay(int i)
    {
        return (AdapterDay)getWeek(((2 - ((Integer)timeUtils.firstDayOfWeek.get()).intValue()) + i) / 7).getDays().get(((2 - ((Integer)timeUtils.firstDayOfWeek.get()).intValue()) + i) % 7);
    }

    public final AdapterEvent getEvent(int i)
    {
        if (CalendarViewType.forPosition(i) == CalendarViewType.CREATE_EVENT)
        {
            return (AdapterEvent)((Optional)createEventPhantom.get()).orNull();
        } else
        {
            return (AdapterEvent)positionToEvent.get(i);
        }
    }

    public final int getEventPosition(AdapterEvent adapterevent, int i)
    {
        Integer integer = Integer.valueOf(converter.getEventPosition(adapterevent.getItem(), i).intValue() + EventViewPositionHelper.MIN_POS);
        if (positionToEvent.get(integer.intValue()) == null)
        {
            positionToEvent.put(integer.intValue(), adapterevent);
        }
        return integer.intValue();
    }

    public final int getItemCount()
    {
        return 0x7fffffff;
    }

    public final int getItemViewType(int i)
    {
        return CalendarViewType.forPosition(i).ordinal();
    }

    public final AdapterMonth getMonth(YearMonth yearmonth)
    {
        return monthAdapter.getMonth(yearmonth);
    }

    public final AdapterWeek getWeek(int i)
    {
        AdapterWeek adapterweek = (AdapterWeek)julianWeekToWeek.get(i);
        Object obj = adapterweek;
        if (adapterweek == null)
        {
            obj = ImmutableList.builder();
            for (int j = 0; j < 7; j++)
            {
                int k = (i * 7 - (2 - ((Integer)timeUtils.firstDayOfWeek.get()).intValue())) + j;
                com.google.common.collect.ImmutableList.Builder builder = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj)).add((new .AutoValue_AdapterDay.Builder()).setLoaded(false).setCacheGeneration(-1).setAllDayEvents(ImmutableList.of()).setTimedEvents(ImmutableList.of()).setJulianDay(k).setAllDayOverflowPosition(k + CalendarViewType.ALL_DAY_MORE_HEADER.minPosition).build());
            }

            AdapterWeek.Builder builder1 = (new AutoValue_AdapterWeek.Builder()).setCacheGeneration(-1L).setJulianWeek(i);
            obj.forceCopy = true;
            obj = builder1.setDays(ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj)).size)).build();
            julianWeekToWeek.put(i, obj);
        }
        return ((AdapterWeek) (obj));
    }

    public final void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewholder, int i)
    {
        viewholder = (TimelineAdapterViewHolderImpl)viewholder;
        _cls1..SwitchMap.com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType[CalendarViewType.forPosition(i).ordinal()];
        JVM INSTR tableswitch 2 11: default 72
    //                   2 210
    //                   3 73
    //                   4 124
    //                   5 182
    //                   6 233
    //                   7 72
    //                   8 251
    //                   9 471
    //                   10 242
    //                   11 487;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L1 _L7 _L8 _L9 _L10
_L1:
        return;
_L3:
        viewholder = (EventViewHolder)viewholder;
        viewholder.adapterEvent = (AdapterEvent)((ColumnDragState)((Optional)dragStateObservable.get()).get()).phantoms().get(i - CalendarViewType.DRAG_EVENT.minPosition);
        viewholder.rebind();
        return;
_L4:
        if (!((Optional)createEventPhantom.get()).isPresent())
        {
            throw new VerifyException();
        } else
        {
            viewholder = (EventViewHolder)viewholder;
            viewholder.adapterEvent = (AdapterEvent)((Optional)createEventPhantom.get()).get();
            viewholder.rebind();
            return;
        }
_L5:
        viewholder = (EventViewHolder)viewholder;
        viewholder.adapterEvent = (AdapterEvent)positionToEvent.get(EventViewPositionHelper.toPrimaryPosition(i));
        viewholder.rebind();
        return;
_L2:
        viewholder = (AllDayMoreViewHolder)viewholder;
        if (((AllDayMoreViewHolder) (viewholder)).position != i)
        {
            viewholder.position = i;
        }
        viewholder.update();
        return;
_L6:
        ((DayHeaderViewHolder)viewholder).bind(i);
        return;
_L9:
        ((MonthDayViewHolder)viewholder).onUpdate(i);
        return;
_L7:
        MonthBannerView monthbannerview;
        MonthBannerViewHolder monthbannerviewholder = (MonthBannerViewHolder)viewholder;
        i -= CalendarViewType.MONTH_BANNER.minPosition;
        int j = monthbannerviewholder.timeUtils.getCalendarFieldForJulianDay(i, 2);
        long l = monthbannerviewholder.timeUtils.julianDateToMs(i);
        monthbannerview = monthbannerviewholder.view;
        String s = monthbannerviewholder.scheduleProvider.getMonthText(l);
        if (monthbannerviewholder.scheduleProvider.shouldShowMonthImages())
        {
            viewholder = monthbannerviewholder.scheduleProvider.loadBannerBitmap(j);
        } else
        {
            viewholder = null;
        }
        if (monthbannerviewholder.scheduleProvider.shouldShowMonthImages()) goto _L12; else goto _L11
_L11:
        if ((ScreenType)monthbannerviewholder.screenType.get() == ScreenType.PHONE)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L13; else goto _L12
_L12:
        i = monthbannerviewholder.scheduleProvider.getBackgroundColor(j);
_L14:
        monthbannerview.clean();
        monthbannerview.bannerBitmapFuture = viewholder;
        if (viewholder != null)
        {
            if (viewholder.isDone())
            {
                monthbannerview.onBitmapFutureComplete(false);
            } else
            {
                viewholder.addListener(new MonthBannerView..Lambda._cls0(monthbannerview), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN));
            }
        }
        monthbannerview.text = s;
        monthbannerview.setContentDescription(s);
        monthbannerview.backgroundColor = i;
        return;
_L13:
        i = -1;
          goto _L14
_L8:
        ((WeekBannerViewHolder)viewholder).bind(i - CalendarViewType.WEEK_BANNER.minPosition);
        return;
_L10:
        viewholder = (NothingPlannedBannerViewHolder)viewholder;
        int k = CalendarViewType.NOTHING_PLANNED_BANNER.minPosition;
        ((NothingPlannedBannerViewHolder) (viewholder)).view.setOnClickListener(new NothingPlannedBannerViewHolder..Lambda._cls0(viewholder, i - k));
        return;
    }

    public final android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i)
    {
        switch (_cls1..SwitchMap.com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType[CalendarViewType.values()[i].ordinal()])
        {
        default:
            throw new IllegalArgumentException("Unknown view type");

        case 1: // '\001'
            return (TimelineAdapterViewHolderImpl)allDayExpandViewHolder.get();

        case 2: // '\002'
            return (TimelineAdapterViewHolderImpl)allDayMoreViewHolderProvider.get();

        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
            return (TimelineAdapterViewHolderImpl)eventViewHolderProvider.get();

        case 6: // '\006'
            return (TimelineAdapterViewHolderImpl)dayHeaderViewHolderProvider.get();

        case 7: // '\007'
            return (TimelineAdapterViewHolderImpl)nowLineViewHolderProvider.get();

        case 8: // '\b'
            return (TimelineAdapterViewHolderImpl)monthBannerViewHolderProvider.get();

        case 9: // '\t'
            return (TimelineAdapterViewHolderImpl)weekBannerViewHolderProvider.get();

        case 10: // '\n'
            return (TimelineAdapterViewHolderImpl)monthDayViewHolderProvider.get();

        case 11: // '\013'
            return (TimelineAdapterViewHolderImpl)nothingPlannedBannerViewHolderProvider.get();

        case 12: // '\f'
            return (TimelineAdapterViewHolderImpl)allDayClickGuardHolderProvider.get();

        case 13: // '\r'
            return (TimelineAdapterViewHolderImpl)hoursProvider.get();
        }
    }

    private class _cls1
    {

        public static final int $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType[];

        static 
        {
            $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType = new int[CalendarViewType.values().length];
            try
            {
                $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType[CalendarViewType.ALL_DAY_EXPAND.ordinal()] = 1;
            }
            catch (NoSuchFieldError nosuchfielderror14) { }
            try
            {
                $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType[CalendarViewType.ALL_DAY_MORE_HEADER.ordinal()] = 2;
            }
            catch (NoSuchFieldError nosuchfielderror13) { }
            try
            {
                $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType[CalendarViewType.DRAG_EVENT.ordinal()] = 3;
            }
            catch (NoSuchFieldError nosuchfielderror12) { }
            try
            {
                $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType[CalendarViewType.CREATE_EVENT.ordinal()] = 4;
            }
            catch (NoSuchFieldError nosuchfielderror11) { }
            try
            {
                $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType[CalendarViewType.EVENT.ordinal()] = 5;
            }
            catch (NoSuchFieldError nosuchfielderror10) { }
            try
            {
                $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType[CalendarViewType.DAY_HEADER.ordinal()] = 6;
            }
            catch (NoSuchFieldError nosuchfielderror9) { }
            try
            {
                $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType[CalendarViewType.NOW_LINE.ordinal()] = 7;
            }
            catch (NoSuchFieldError nosuchfielderror8) { }
            try
            {
                $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType[CalendarViewType.MONTH_BANNER.ordinal()] = 8;
            }
            catch (NoSuchFieldError nosuchfielderror7) { }
            try
            {
                $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType[CalendarViewType.WEEK_BANNER.ordinal()] = 9;
            }
            catch (NoSuchFieldError nosuchfielderror6) { }
            try
            {
                $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType[CalendarViewType.MONTH_VIEW_DAY_HEADER.ordinal()] = 10;
            }
            catch (NoSuchFieldError nosuchfielderror5) { }
            try
            {
                $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType[CalendarViewType.NOTHING_PLANNED_BANNER.ordinal()] = 11;
            }
            catch (NoSuchFieldError nosuchfielderror4) { }
            try
            {
                $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType[CalendarViewType.ALL_DAY_CLICK_GUARD.ordinal()] = 12;
            }
            catch (NoSuchFieldError nosuchfielderror3) { }
            try
            {
                $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType[CalendarViewType.HOURS.ordinal()] = 13;
            }
            catch (NoSuchFieldError nosuchfielderror2) { }
            try
            {
                $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType[CalendarViewType.VIRTUAL_TIMED_EVENTS.ordinal()] = 14;
            }
            catch (NoSuchFieldError nosuchfielderror1) { }
            try
            {
                $SwitchMap$com$google$android$apps$calendar$timeline$alternate$view$impl$viewtype$CalendarViewType[CalendarViewType.WEEK_NUMBER.ordinal()] = 15;
            }
            catch (NoSuchFieldError nosuchfielderror)
            {
                return;
            }
        }
    }

}
