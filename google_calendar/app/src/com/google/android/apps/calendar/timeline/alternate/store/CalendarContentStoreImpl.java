// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import android.arch.lifecycle.Lifecycle;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemProvider;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutors;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.collect.Cut;
import com.google.common.collect.Range;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            CalendarContentStore, ItemTransformer, CalendarWeekCache

public final class CalendarContentStoreImpl
    implements CalendarContentStore
{

    public int cacheGeneration;
    public final ObservableReference calendarsObservable = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Collections.emptyList());
    public FluentFuture currentFuture;
    public Range currentViewPort;
    public final ItemTransformer itemTransformer;
    private final ItemProvider provider;
    public final com.google.android.apps.calendar.util.concurrent.CalendarExecutors.SerialExecutor serialExecutor;
    private final TimeUtils timeUtils;
    public int updateCount;
    public final CalendarWeekCache weekCache;
    public Iterator weeksToCache;

    CalendarContentStoreImpl(Lifecycle lifecycle, CalendarWeekCache calendarweekcache, ItemTransformer itemtransformer, ItemProvider itemprovider, com.google.android.apps.calendar.util.concurrent.CalendarExecutors.SerialExecutor serialexecutor, TimeUtils timeutils, ObservableReference observablereference)
    {
        cacheGeneration = 1;
        class .Lambda._cls0
            implements Consumer
        {

            private final CalendarContentStoreImpl arg$1;

            public final void accept(Object obj1)
            {
                arg$1.invalidate();
            }

            .Lambda._cls0()
            {
                arg$1 = CalendarContentStoreImpl.this;
            }
        }

        Object obj;
        if (true)
        {
            obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
        }
        if (obj instanceof FluentFuture)
        {
            obj = (FluentFuture)obj;
        } else
        {
            obj = new ForwardingFluentFuture(((ListenableFuture) (obj)));
        }
        currentFuture = ((FluentFuture) (obj));
        weeksToCache = Collections.emptyList().iterator();
        weekCache = calendarweekcache;
        itemTransformer = itemtransformer;
        provider = itemprovider;
        serialExecutor = serialexecutor;
        timeUtils = timeutils;
        lifecycle.addObserver(new _cls1());
    }

    final void bridge$lambda$1$CalendarContentStoreImpl()
    {
        com.google.android.apps.calendar.util.concurrent.CalendarExecutors.SerialExecutor serialexecutor = serialExecutor;
        boolean flag;
        if (CalendarExecutors.serialExecutorTag.get() == serialexecutor)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        if (updateCount != 0 || !weeksToCache.hasNext())
        {
            return;
        } else
        {
            int i = ((Integer)weeksToCache.next()).intValue();
            class .Lambda._cls5
                implements Function
            {

                private final CalendarContentStoreImpl arg$1;
                private final int arg$2;

                public final Object apply(Object obj)
                {
                    Object obj1 = arg$1;
                    int l1 = arg$2;
                    obj = (List)obj;
                    ItemTransformer itemtransformer = ((CalendarContentStoreImpl) (obj1)).itemTransformer;
                    int i2 = ((CalendarContentStoreImpl) (obj1)).cacheGeneration;
                    Object obj2 = new SparseArray();
                    for (Iterator iterator = ((List) (obj)).iterator(); iterator.hasNext();)
                    {
                        obj = iterator.next();
                        VersionedItem versioneditem = itemtransformer.updateVersionedItem(obj);
                        int i1 = itemtransformer.adapter.getEndDay(obj);
                        int j = itemtransformer.adapter.getStartDay(obj);
                        while (j <= i1) 
                        {
                            obj1 = (com.google.common.collect.ImmutableSet.Builder)((SparseArray) (obj2)).get(j);
                            obj = obj1;
                            if (obj1 == null)
                            {
                                obj = new com.google.common.collect.ImmutableSet.Builder();
                                ((SparseArray) (obj2)).put(j, obj);
                            }
                            obj = (com.google.common.collect.ImmutableSet.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj)).add(versioneditem);
                            j++;
                        }
                    }

                    obj1 = new SparseArray();
                    int k = 0;
                    while (k < ((SparseArray) (obj2)).size()) 
                    {
                        int j1 = ((SparseArray) (obj2)).keyAt(k);
                        obj = (com.google.common.collect.ImmutableSet.Builder)((SparseArray) (obj2)).get(j1);
                        CalendarDay.Builder builder = (new AutoValue_CalendarDay.Builder()).setJulianDate(j1).setCacheGeneration(i2);
                        if (obj != null)
                        {
                            obj = ((com.google.common.collect.ImmutableSet.Builder) (obj)).build();
                        } else
                        {
                            obj = RegularImmutableSet.EMPTY;
                        }
                        ((SparseArray) (obj1)).put(j1, builder.setItems(((com.google.common.collect.ImmutableSet) (obj))).build());
                        k++;
                    }
                    obj2 = new ArrayList();
                    for (int l = l1; l <= l1; l++)
                    {
                        int j2 = l * 7 - (2 - ((Integer)itemtransformer.timeUtils.firstDayOfWeek.get()).intValue());
                        com.google.common.collect.ImmutableList.Builder builder1 = ImmutableList.builder();
                        int k1 = 0;
                        while (k1 < 7) 
                        {
                            obj = (CalendarDay)((SparseArray) (obj1)).get(j2 + k1);
                            if (obj == null)
                            {
                                obj = Absent.INSTANCE;
                            } else
                            {
                                obj = new Present(obj);
                            }
                            obj = (com.google.common.collect.ImmutableList.Builder)builder1.add((CalendarDay)((Optional) (obj)).or((new AutoValue_CalendarDay.Builder()).setJulianDate(j2 + k1).setCacheGeneration(i2).setItems(RegularImmutableSet.EMPTY).build()));
                            k1++;
                        }
                        obj = (new AutoValue_CalendarWeek.Builder()).setCacheGeneration(i2).setJulianWeek(l);
                        builder1.forceCopy = true;
                        ((List) (obj2)).add(((CalendarWeek.Builder) (obj)).setDays(ImmutableList.asImmutableList(builder1.contents, builder1.size)).build());
                    }

                    return obj2;
                }

            .Lambda._cls5(int i)
            {
                arg$1 = CalendarContentStoreImpl.this;
                arg$2 = i;
            }
            }

            FluentFuture fluentfuture = (FluentFuture)AbstractTransformFuture.create(provider.loadItems(i * 7 - (2 - ((Integer)timeUtils.firstDayOfWeek.get()).intValue()), (i + 1) * 7 - (2 - ((Integer)timeUtils.firstDayOfWeek.get()).intValue()) - 1), new .Lambda._cls5(i), serialExecutor);
            class .Lambda._cls6
                implements Runnable
            {

                private final CalendarContentStoreImpl arg$1;
                private final FluentFuture arg$2;

                public final void run()
                {
                    CalendarContentStoreImpl calendarcontentstoreimpl;
                    Object obj;
                    calendarcontentstoreimpl = arg$1;
                    obj = arg$2;
                    com.google.android.apps.calendar.util.concurrent.CalendarExecutors.SerialExecutor serialexecutor1 = calendarcontentstoreimpl.serialExecutor;
                    boolean flag1;
                    if (CalendarExecutors.serialExecutorTag.get() == serialexecutor1)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (!flag1)
                    {
                        throw new IllegalStateException();
                    }
                    if (calendarcontentstoreimpl.updateCount != 0)
                    {
                        break MISSING_BLOCK_LABEL_117;
                    }
                    obj = (List)((FluentFuture) (obj)).get();
                    CalendarWeek calendarweek;
                    CalendarWeekCache calendarweekcache;
                    int j;
                    class .Lambda._cls9
                        implements Runnable
                    {

                        private final CalendarContentStoreImpl arg$1;

                        public final void run()
                        {
                            arg$1.bridge$lambda$1$CalendarContentStoreImpl();
                        }

                            .Lambda._cls9()
                            {
                                arg$1 = CalendarContentStoreImpl.this;
                            }
                    }

                    boolean flag2;
                    if (((List) (obj)).size() == 1)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    if (flag2)
                    {
                        break MISSING_BLOCK_LABEL_124;
                    }
                    class .Lambda._cls10
                        implements Runnable
                    {

                        private final CalendarContentStoreImpl arg$1;

                        public final void run()
                        {
                            arg$1.bridge$lambda$1$CalendarContentStoreImpl();
                        }

                            .Lambda._cls10()
                            {
                                arg$1 = CalendarContentStoreImpl.this;
                            }
                    }

                    com.google.android.apps.calendar.util.concurrent.CalendarExecutors.SerialExecutor serialexecutor2;
                    try
                    {
                        throw new IllegalStateException();
                    }
                    // Misplaced declaration of an exception variable
                    catch (Object obj) { }
                    finally
                    {
                        calendarcontentstoreimpl.currentFuture.addListener(calendarcontentstoreimpl. new .Lambda._cls10(), calendarcontentstoreimpl.serialExecutor);
                    }
                    Log.e("CalendarContentStore", "Unable to load weeks", ((Throwable) (obj)));
                    calendarcontentstoreimpl.currentFuture.addListener(calendarcontentstoreimpl. new .Lambda._cls9(), calendarcontentstoreimpl.serialExecutor);
                    return;
                    obj = ((List) (obj)).iterator();
_L1:
                    if (!((Iterator) (obj)).hasNext())
                    {
                        break MISSING_BLOCK_LABEL_251;
                    }
                    calendarweek = (CalendarWeek)((Iterator) (obj)).next();
                    calendarweekcache = calendarcontentstoreimpl.weekCache;
                    j = calendarweek.getJulianWeek();
                    serialexecutor2 = calendarweekcache.serialExecutor;
                    Exception exception;
                    boolean flag3;
                    if (CalendarExecutors.serialExecutorTag.get() == serialexecutor2)
                    {
                        flag3 = true;
                    } else
                    {
                        flag3 = false;
                    }
                    if (flag3)
                    {
                        break MISSING_BLOCK_LABEL_224;
                    }
                    throw new IllegalStateException();
                    throw exception;
                    calendarweekcache.weeks.put(j, calendarweek);
                    calendarcontentstoreimpl.calendarsObservable.set(Collections.singleton(calendarweek));
                      goto _L1
                    class .Lambda._cls8
                        implements Runnable
                    {

                        private final CalendarContentStoreImpl arg$1;

                        public final void run()
                        {
                            arg$1.bridge$lambda$1$CalendarContentStoreImpl();
                        }

                            .Lambda._cls8()
                            {
                                arg$1 = CalendarContentStoreImpl.this;
                            }
                    }

                    calendarcontentstoreimpl.currentFuture.addListener(calendarcontentstoreimpl. new .Lambda._cls8(), calendarcontentstoreimpl.serialExecutor);
                    return;
                }

            .Lambda._cls6(FluentFuture fluentfuture)
            {
                arg$1 = CalendarContentStoreImpl.this;
                arg$2 = fluentfuture;
            }
            }

            fluentfuture.addListener(new .Lambda._cls6(fluentfuture), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            currentFuture = fluentfuture;
            return;
        }
    }

    final void invalidate()
    {
        com.google.android.apps.calendar.util.concurrent.CalendarExecutors.SerialExecutor serialexecutor = serialExecutor;
        boolean flag;
        if (CalendarExecutors.serialExecutorTag.get() == serialexecutor)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        if (updateCount == 0)
        {
            cacheGeneration = cacheGeneration + 1;
            if (currentViewPort != null)
            {
                weeksToCache = newWeekCacheIterator(currentViewPort);
                class .Lambda._cls3
                    implements Runnable
                {

                    private final CalendarContentStoreImpl arg$1;

                    public final void run()
                    {
                        arg$1.bridge$lambda$1$CalendarContentStoreImpl();
                    }

            .Lambda._cls3()
            {
                arg$1 = CalendarContentStoreImpl.this;
            }
                }

                currentFuture.addListener(new .Lambda._cls3(), serialExecutor);
                return;
            }
        }
    }

    final Iterator newWeekCacheIterator(Range range)
    {
        Object obj = timeUtils;
        int i = ((Integer)range.lowerBound.endpoint()).intValue();
        i = ((2 - ((Integer)((TimeUtils) (obj)).firstDayOfWeek.get()).intValue()) + i) / 7;
        obj = timeUtils;
        int j = ((Integer)range.upperBound.endpoint()).intValue();
        j = ((2 - ((Integer)((TimeUtils) (obj)).firstDayOfWeek.get()).intValue()) + j) / 7;
        if (i == 0 || j == 0)
        {
            return Collections.emptyList().iterator();
        }
        range = new com.google.android.apps.calendar.timeline.alternate.util.CacheLoadStrategies._cls1(52, i, j);
        class .Lambda._cls7
            implements Predicate
        {

            private final CalendarContentStoreImpl arg$1;

            public final boolean apply(Object obj1)
            {
                CalendarContentStoreImpl calendarcontentstoreimpl = arg$1;
                obj1 = (Integer)obj1;
                Object obj2 = calendarcontentstoreimpl.serialExecutor;
                boolean flag;
                if (CalendarExecutors.serialExecutorTag.get() == obj2)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalStateException();
                }
                obj2 = calendarcontentstoreimpl.weekCache;
                int k = ((Integer) (obj1)).intValue();
                obj1 = ((CalendarWeekCache) (obj2)).serialExecutor;
                if (CalendarExecutors.serialExecutorTag.get() == obj1)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalStateException();
                }
                obj1 = (CalendarWeek)((CalendarWeekCache) (obj2)).weeks.get(k);
                return obj1 == null || ((CalendarWeek) (obj1)).getCacheGeneration() != (long)calendarcontentstoreimpl.cacheGeneration;
            }

            .Lambda._cls7()
            {
                arg$1 = CalendarContentStoreImpl.this;
            }
        }

        obj = new .Lambda._cls7();
        if (range == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            return new com.google.common.collect.Iterators._cls5(range, ((Predicate) (obj)));
        }
    }

    public final Subscription subscribe(Consumer consumer, Executor executor)
    {
        return calendarsObservable.subscribe(consumer, executor, false);
    }

    public final Subscription subscribeToViewport(ObservableReference observablereference)
    {
        class .Lambda._cls1
            implements Consumer
        {

            private final CalendarContentStoreImpl arg$1;

            public final void accept(Object obj)
            {
                CalendarContentStoreImpl calendarcontentstoreimpl = arg$1;
                obj = (Range)obj;
                com.google.android.apps.calendar.util.concurrent.CalendarExecutors.SerialExecutor serialexecutor = calendarcontentstoreimpl.serialExecutor;
                boolean flag;
                if (CalendarExecutors.serialExecutorTag.get() == serialexecutor)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalStateException();
                }
                if (!((Range) (obj)).equals(calendarcontentstoreimpl.currentViewPort))
                {
                    calendarcontentstoreimpl.currentViewPort = ((Range) (obj));
                    calendarcontentstoreimpl.weeksToCache = calendarcontentstoreimpl.newWeekCacheIterator(((Range) (obj)));
                    class .Lambda._cls4
                        implements Runnable
                    {

                        private final CalendarContentStoreImpl arg$1;

                        public final void run()
                        {
                            arg$1.bridge$lambda$1$CalendarContentStoreImpl();
                        }

                        .Lambda._cls4()
                        {
                            arg$1 = CalendarContentStoreImpl.this;
                        }
                    }

                    calendarcontentstoreimpl.currentFuture.addListener(calendarcontentstoreimpl. new .Lambda._cls4(), calendarcontentstoreimpl.serialExecutor);
                }
            }

            .Lambda._cls1()
            {
                arg$1 = CalendarContentStoreImpl.this;
            }
        }

        return observablereference.subscribe(new .Lambda._cls1(), serialExecutor, true);
    }

    public final ListenableFuture updateStore(Consumer consumer)
    {
        SettableFuture settablefuture = new SettableFuture();
        class .Lambda._cls2
            implements Runnable
        {

            private final CalendarContentStoreImpl arg$1;
            private final Consumer arg$2;
            private final SettableFuture arg$3;

            public final void run()
            {
                CalendarContentStoreImpl calendarcontentstoreimpl = arg$1;
                Object obj = arg$2;
                SettableFuture settablefuture1 = arg$3;
                calendarcontentstoreimpl.cacheGeneration = calendarcontentstoreimpl.cacheGeneration + 1;
                final HashSet updatedWeekNumbers = new HashSet();
                final boolean inProgress[] = new boolean[1];
                inProgress[0] = true;
                ((Consumer) (obj)).accept(calendarcontentstoreimpl. new _cls2());
                inProgress[0] = false;
                obj = new ArrayList(updatedWeekNumbers.size());
                CalendarWeekCache calendarweekcache;
                boolean flag;
                int i;
                for (updatedWeekNumbers = updatedWeekNumbers.iterator(); updatedWeekNumbers.hasNext(); ((List) (obj)).add((CalendarWeek)calendarweekcache.weeks.get(i)))
                {
                    i = ((Integer)updatedWeekNumbers.next()).intValue();
                    calendarweekcache = calendarcontentstoreimpl.weekCache;
                    com.google.android.apps.calendar.util.concurrent.CalendarExecutors.SerialExecutor serialexecutor = calendarweekcache.serialExecutor;
                    if (CalendarExecutors.serialExecutorTag.get() == serialexecutor)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag)
                    {
                        throw new IllegalStateException();
                    }
                }

                class .Lambda._cls11
                    implements Runnable
                {

                    private final SettableFuture arg$1;

                    public final void run()
                    {
                        arg$1.set(new Object());
                    }

                        .Lambda._cls11(SettableFuture settablefuture)
                        {
                            arg$1 = settablefuture;
                        }
                }

                calendarcontentstoreimpl.calendarsObservable.set(obj, new .Lambda._cls11(settablefuture1), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            }

            .Lambda._cls2(Consumer consumer, SettableFuture settablefuture)
            {
                arg$1 = CalendarContentStoreImpl.this;
                arg$2 = consumer;
                arg$3 = settablefuture;
            }

            private class _cls2
                implements CalendarContentStore.StoreTransaction
            {

                public final CalendarContentStoreImpl this$0;
                private final boolean val$inProgress[];
                private final Set val$updatedWeekNumbers;

                public final void addItem(Object obj)
                {
                    boolean flag = false;
                    if (!inProgress[0])
                    {
                        throw new IllegalStateException();
                    }
                    CalendarWeekCache calendarweekcache = weekCache;
                    obj = itemTransformer.updateVersionedItem(obj);
                    int j = cacheGeneration;
                    Set set = updatedWeekNumbers;
                    Object obj1 = calendarweekcache.serialExecutor;
                    if (CalendarExecutors.serialExecutorTag.get() == obj1)
                    {
                        flag = true;
                    }
                    if (!flag)
                    {
                        throw new IllegalStateException();
                    }
                    obj1 = calendarweekcache.timeUtils;
                    int i = calendarweekcache.adapter.getStartDay(((VersionedItem) (obj)).getItem());
                    i = ((2 - ((Integer)((TimeUtils) (obj1)).firstDayOfWeek.get()).intValue()) + i) / 7;
                    obj1 = calendarweekcache.timeUtils;
                    int k = calendarweekcache.adapter.getEndDay(((VersionedItem) (obj)).getItem());
                    for (k = ((2 - ((Integer)((TimeUtils) (obj1)).firstDayOfWeek.get()).intValue()) + k) / 7; i <= k; i++)
                    {
                        Object obj2 = (CalendarWeek)calendarweekcache.weeks.get(i);
                        if (obj2 == null)
                        {
                            continue;
                        }
                        SparseArray sparsearray = calendarweekcache.weeks;
                        CalendarWeek.Builder builder = ((CalendarWeek) (obj2)).toBuilder().setCacheGeneration(j);
                        obj2 = ((CalendarWeek) (obj2)).getDays();
                        CalendarWeekCache..Lambda._cls2 _lcls2 = new CalendarWeekCache..Lambda._cls2(calendarweekcache, ((VersionedItem) (obj)), j);
                        if (obj2 == null)
                        {
                            throw new NullPointerException();
                        }
                        if (_lcls2 == null)
                        {
                            throw new NullPointerException();
                        }
                        sparsearray.put(i, builder.setDays(ImmutableList.copyOf(new com.google.common.collect.Iterables._cls5(((Iterable) (obj2)), _lcls2))).build());
                        set.add(Integer.valueOf(i));
                    }

                }

                public final void blockUpdates(ListenableFuture listenablefuture)
                {
                    if (!inProgress[0])
                    {
                        throw new IllegalStateException();
                    } else
                    {
                        CalendarContentStoreImpl calendarcontentstoreimpl = CalendarContentStoreImpl.this;
                        calendarcontentstoreimpl.updateCount = calendarcontentstoreimpl.updateCount + 1;
                        class .Lambda._cls0
                            implements Runnable
                        {

                            private final _cls2 arg$1;

                            public final void run()
                            {
                                _cls2 _lcls2 = arg$1;
                                CalendarContentStoreImpl calendarcontentstoreimpl1 = _lcls2._fld0;
                                calendarcontentstoreimpl1.updateCount = calendarcontentstoreimpl1.updateCount - 1;
                                _lcls2._fld0.invalidate();
                            }

                        .Lambda._cls0()
                        {
                            arg$1 = _cls2.this;
                        }
                        }

                        listenablefuture.addListener(new .Lambda._cls0(), serialExecutor);
                        return;
                    }
                }

                public final void invalidate()
                {
                    CalendarContentStoreImpl.this.invalidate();
                }

                public final void removeItem(Object obj)
                {
                    boolean flag = false;
                    if (!inProgress[0])
                    {
                        throw new IllegalStateException();
                    }
                    CalendarWeekCache calendarweekcache = weekCache;
                    int j = cacheGeneration;
                    Set set = updatedWeekNumbers;
                    Object obj1 = calendarweekcache.serialExecutor;
                    if (CalendarExecutors.serialExecutorTag.get() == obj1)
                    {
                        flag = true;
                    }
                    if (!flag)
                    {
                        throw new IllegalStateException();
                    }
                    obj1 = calendarweekcache.timeUtils;
                    int i = calendarweekcache.adapter.getStartDay(obj);
                    i = ((2 - ((Integer)((TimeUtils) (obj1)).firstDayOfWeek.get()).intValue()) + i) / 7;
                    obj1 = calendarweekcache.timeUtils;
                    int k = calendarweekcache.adapter.getEndDay(obj);
                    for (k = ((2 - ((Integer)((TimeUtils) (obj1)).firstDayOfWeek.get()).intValue()) + k) / 7; i <= k; i++)
                    {
                        Object obj2 = (CalendarWeek)calendarweekcache.weeks.get(i);
                        if (obj2 == null)
                        {
                            continue;
                        }
                        SparseArray sparsearray = calendarweekcache.weeks;
                        CalendarWeek.Builder builder = ((CalendarWeek) (obj2)).toBuilder().setCacheGeneration(j);
                        obj2 = ((CalendarWeek) (obj2)).getDays();
                        CalendarWeekCache..Lambda._cls0 _lcls0 = new CalendarWeekCache..Lambda._cls0(calendarweekcache, j, obj);
                        if (obj2 == null)
                        {
                            throw new NullPointerException();
                        }
                        if (_lcls0 == null)
                        {
                            throw new NullPointerException();
                        }
                        sparsearray.put(i, builder.setDays(ImmutableList.copyOf(new com.google.common.collect.Iterables._cls5(((Iterable) (obj2)), _lcls0))).build());
                        set.add(Integer.valueOf(i));
                    }

                }

                _cls2()
                {
                    this$0 = CalendarContentStoreImpl.this;
                    inProgress = aflag;
                    updatedWeekNumbers = set;
                    super();
                }
            }

        }

        serialExecutor.execute(new .Lambda._cls2(consumer, settablefuture));
        return settablefuture;
    }

    private class _cls1
        implements FullLifecycleObserver
    {

        private final Lifecycle val$lifecycle;
        private final Subscription val$subscriptions;

        public final void onCreate(LifecycleOwner lifecycleowner)
        {
        }

        public final void onDestroy$51662RJ4E9NMIP1FC5P66Q1FDHKMCPB3F5HMOP9F9HKMCPB3F5HMOPAFETN6ASHR55B0____0()
        {
            subscriptions.cancel(false);
            lifecycle.removeObserver(this);
        }

        public final void onPause(LifecycleOwner lifecycleowner)
        {
        }

        public final void onResume(LifecycleOwner lifecycleowner)
        {
        }

        public final void onStart(LifecycleOwner lifecycleowner)
        {
        }

        public final void onStop(LifecycleOwner lifecycleowner)
        {
        }

        _cls1()
        {
            subscriptions = subscription;
            lifecycle = lifecycle1;
            super();
        }
    }

}
