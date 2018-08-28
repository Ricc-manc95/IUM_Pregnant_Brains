// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.function.BiFunction;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.apps.calendar.util.function.TriFunction;
import com.google.android.apps.calendar.util.handler.Handler;
import com.google.android.apps.calendar.util.handler.Handlers;
import com.google.common.base.Absent;
import com.google.common.base.Function;
import com.google.common.base.Pair;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.CombinedFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.Runnables;
import com.google.common.util.concurrent.Uninterruptibles;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            Cancelable

public class CalendarFutures
{

    public static final FluentFuture IMMEDIATE_ABSENT_FUTURE;
    private static final String LOG_TAG = com/google/android/apps/calendar/util/concurrent/CalendarFutures.getSimpleName();

    public CalendarFutures()
    {
    }

    public static Cancelable asyncGet(ListenableFuture listenablefuture, Consumer consumer, Executor executor)
    {
        Runnable runnable = Runnables.EMPTY_RUNNABLE;
        Handler handler = Handlers.RAW_UNHANDLED;
        Handler handler1 = Handlers.RAW_UNHANDLED;
        Handler handler2 = Handlers.RAW_UNHANDLED;
        AtomicReference atomicreference = new AtomicReference(listenablefuture);
        class .Lambda._cls7
            implements Runnable
        {

            private final AtomicReference arg$1;
            private final Handler arg$2;
            private final Handler arg$3;
            private final Handler arg$4;
            private final Consumer arg$5;
            private final Runnable arg$6;

            public final void run()
            {
                CalendarFutures.lambda$asyncGet$7$CalendarFutures(arg$1, arg$2, arg$3, arg$4, arg$5, arg$6);
            }

            .Lambda._cls7(AtomicReference atomicreference, Handler handler, Handler handler1, Handler handler2, Consumer consumer, Runnable runnable)
            {
                arg$1 = atomicreference;
                arg$2 = handler;
                arg$3 = handler1;
                arg$4 = handler2;
                arg$5 = consumer;
                arg$6 = runnable;
            }
        }

        listenablefuture.addListener(new .Lambda._cls7(atomicreference, handler, handler1, handler2, consumer, runnable), executor);
        class .Lambda._cls8
            implements Cancelable
        {

            private final AtomicReference arg$1;

            public final void cancel(boolean flag)
            {
                CalendarFutures.lambda$asyncGet$8$CalendarFutures(arg$1, flag);
            }

            .Lambda._cls8(AtomicReference atomicreference)
            {
                arg$1 = atomicreference;
            }
        }

        return new .Lambda._cls8(atomicreference);
    }

    static final void lambda$asyncGet$7$CalendarFutures(AtomicReference atomicreference, Handler handler, Handler handler1, Handler handler2, Consumer consumer, Runnable runnable)
    {
        atomicreference = (ListenableFuture)atomicreference.getAndSet(null);
        if (atomicreference == null)
        {
            break MISSING_BLOCK_LABEL_35;
        }
        atomicreference = ((AtomicReference) (atomicreference.get()));
        consumer.accept(atomicreference);
        runnable.run();
        return;
        atomicreference;
        atomicreference = atomicreference.getCause();
        if (!handler.handle(atomicreference))
        {
            LogUtils.wtf(LOG_TAG, atomicreference, "Unhandled throwable: %s", new Object[] {
                atomicreference
            });
        }
        runnable.run();
        return;
        atomicreference;
        if (!handler1.handle(atomicreference))
        {
            LogUtils.wtf(LOG_TAG, atomicreference, "Unhandled throwable: %s", new Object[] {
                atomicreference
            });
        }
        runnable.run();
        return;
        atomicreference;
        if (!handler2.handle(atomicreference))
        {
            LogUtils.wtf(LOG_TAG, atomicreference, "Unhandled throwable: %s", new Object[] {
                atomicreference
            });
        }
        runnable.run();
        return;
        atomicreference;
_L2:
        LogUtils.wtf(LOG_TAG, atomicreference, "Unhandled throwable: %s", new Object[] {
            atomicreference
        });
        runnable.run();
        return;
        atomicreference;
        runnable.run();
        throw atomicreference;
        atomicreference;
        if (true) goto _L2; else goto _L1
_L1:
    }

    static final void lambda$asyncGet$8$CalendarFutures(AtomicReference atomicreference, boolean flag)
    {
        atomicreference = (ListenableFuture)atomicreference.getAndSet(null);
        if (atomicreference != null)
        {
            atomicreference.cancel(flag);
        }
    }

    static final Pair lambda$mapFold$4$CalendarFutures(Object obj, Object obj1)
    {
        return new Pair(obj, obj1);
    }

    static final Object lambda$mapFold$5$CalendarFutures(Object obj, TriFunction trifunction, List list)
    {
        for (list = list.iterator(); list.hasNext();)
        {
            Pair pair = (Pair)list.next();
            obj = trifunction.apply(pair.first, pair.second, obj);
        }

        return obj;
    }

    static final Object lambda$transform$0$CalendarFutures(BiFunction bifunction, ListenableFuture listenablefuture, ListenableFuture listenablefuture1)
        throws Exception
    {
        if (!listenablefuture.isDone())
        {
            throw new IllegalStateException(Strings.lenientFormat("Future was expected to be done: %s", new Object[] {
                listenablefuture
            }));
        }
        listenablefuture = ((ListenableFuture) (Uninterruptibles.getUninterruptibly(listenablefuture)));
        if (!listenablefuture1.isDone())
        {
            throw new IllegalStateException(Strings.lenientFormat("Future was expected to be done: %s", new Object[] {
                listenablefuture1
            }));
        } else
        {
            return bifunction.apply(listenablefuture, Uninterruptibles.getUninterruptibly(listenablefuture1));
        }
    }

    static final ListenableFuture lambda$transformAsync$2$CalendarFutures(BiFunction bifunction, ListenableFuture listenablefuture, ListenableFuture listenablefuture1)
        throws Exception
    {
        if (!listenablefuture.isDone())
        {
            throw new IllegalStateException(Strings.lenientFormat("Future was expected to be done: %s", new Object[] {
                listenablefuture
            }));
        }
        listenablefuture = ((ListenableFuture) (Uninterruptibles.getUninterruptibly(listenablefuture)));
        if (!listenablefuture1.isDone())
        {
            throw new IllegalStateException(Strings.lenientFormat("Future was expected to be done: %s", new Object[] {
                listenablefuture1
            }));
        } else
        {
            return (ListenableFuture)bifunction.apply(listenablefuture, Uninterruptibles.getUninterruptibly(listenablefuture1));
        }
    }

    static final void lambda$withSideEffect$6$CalendarFutures$5166KOBMC4NMOOBECSNL8Q3IDTRM2OJCCKTIILG_0()
    {
    }

    public static FluentFuture mapFold(Iterable iterable, Function function, Object obj, TriFunction trifunction, Executor executor)
    {
        ArrayList arraylist = new ArrayList();
        class .Lambda._cls4
            implements Function
        {

            private final Object arg$1;

            public final Object apply(Object obj2)
            {
                return CalendarFutures.lambda$mapFold$4$CalendarFutures(arg$1, obj2);
            }

            .Lambda._cls4(Object obj)
            {
                arg$1 = obj;
            }
        }

        Object obj1;
        for (iterable = iterable.iterator(); iterable.hasNext(); arraylist.add(AbstractTransformFuture.create((ListenableFuture)function.apply(obj1), new .Lambda._cls4(obj1), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE)))
        {
            obj1 = iterable.next();
        }

        iterable = new com.google.common.util.concurrent.CollectionFuture.ListFuture(ImmutableList.copyOf(arraylist), true);
        class .Lambda._cls5
            implements Function
        {

            private final Object arg$1;
            private final TriFunction arg$2;

            public final Object apply(Object obj2)
            {
                return CalendarFutures.lambda$mapFold$5$CalendarFutures(arg$1, arg$2, (List)obj2);
            }

            .Lambda._cls5(Object obj, TriFunction trifunction)
            {
                arg$1 = obj;
                arg$2 = trifunction;
            }
        }

        if (iterable instanceof FluentFuture)
        {
            iterable = (FluentFuture)iterable;
        } else
        {
            iterable = new ForwardingFluentFuture(iterable);
        }
        return (FluentFuture)AbstractTransformFuture.create(iterable, new .Lambda._cls5(obj, trifunction), executor);
    }

    public static FluentFuture transform(ListenableFuture listenablefuture, ListenableFuture listenablefuture1, BiFunction bifunction, Executor executor)
    {
        com.google.common.util.concurrent.Futures.FutureCombiner futurecombiner = new com.google.common.util.concurrent.Futures.FutureCombiner(true, ImmutableList.copyOf(new ListenableFuture[] {
            listenablefuture, listenablefuture1
        }));
        class .Lambda._cls0
            implements Callable
        {

            private final BiFunction arg$1;
            private final ListenableFuture arg$2;
            private final ListenableFuture arg$3;

            public final Object call()
            {
                return CalendarFutures.lambda$transform$0$CalendarFutures(arg$1, arg$2, arg$3);
            }

            .Lambda._cls0(BiFunction bifunction, ListenableFuture listenablefuture, ListenableFuture listenablefuture1)
            {
                arg$1 = bifunction;
                arg$2 = listenablefuture;
                arg$3 = listenablefuture1;
            }
        }

        listenablefuture = new .Lambda._cls0(bifunction, listenablefuture, listenablefuture1);
        listenablefuture = new CombinedFuture(futurecombiner.futures, futurecombiner.allMustSucceed, executor, listenablefuture);
        if (listenablefuture instanceof FluentFuture)
        {
            return (FluentFuture)listenablefuture;
        } else
        {
            return new ForwardingFluentFuture(listenablefuture);
        }
    }

    public static FluentFuture transformAsync(ListenableFuture listenablefuture, ListenableFuture listenablefuture1, BiFunction bifunction, Executor executor)
    {
        com.google.common.util.concurrent.Futures.FutureCombiner futurecombiner = new com.google.common.util.concurrent.Futures.FutureCombiner(true, ImmutableList.copyOf(new ListenableFuture[] {
            listenablefuture, listenablefuture1
        }));
        class .Lambda._cls2
            implements AsyncCallable
        {

            private final BiFunction arg$1;
            private final ListenableFuture arg$2;
            private final ListenableFuture arg$3;

            public final ListenableFuture call()
            {
                return CalendarFutures.lambda$transformAsync$2$CalendarFutures(arg$1, arg$2, arg$3);
            }

            .Lambda._cls2(BiFunction bifunction, ListenableFuture listenablefuture, ListenableFuture listenablefuture1)
            {
                arg$1 = bifunction;
                arg$2 = listenablefuture;
                arg$3 = listenablefuture1;
            }
        }

        listenablefuture = new .Lambda._cls2(bifunction, listenablefuture, listenablefuture1);
        listenablefuture = new CombinedFuture(futurecombiner.futures, futurecombiner.allMustSucceed, executor, listenablefuture);
        if (listenablefuture instanceof FluentFuture)
        {
            return (FluentFuture)listenablefuture;
        } else
        {
            return new ForwardingFluentFuture(listenablefuture);
        }
    }

    public static ListenableFuture withSideEffect(ListenableFuture listenablefuture, final Consumer onSuccess, Executor executor)
    {
        class .Lambda._cls6
            implements Consumer
        {

            public static final Consumer $instance = new .Lambda._cls6();

            public final void accept(Object obj)
            {
                CalendarFutures.lambda$withSideEffect$6$CalendarFutures$5166KOBMC4NMOOBECSNL8Q3IDTRM2OJCCKTIILG_0();
            }


            private .Lambda._cls6()
            {
            }
        }

        onSuccess = new _cls2();
        if (onSuccess == null)
        {
            throw new NullPointerException();
        } else
        {
            listenablefuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuture, onSuccess), executor);
            return listenablefuture;
        }
    }

    static 
    {
        Object obj = Absent.INSTANCE;
        if (obj == null)
        {
            obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj);
        }
        if (obj instanceof FluentFuture)
        {
            obj = (FluentFuture)obj;
        } else
        {
            obj = new ForwardingFluentFuture(((ListenableFuture) (obj)));
        }
        IMMEDIATE_ABSENT_FUTURE = ((FluentFuture) (obj));
    }

    private class _cls2
        implements FutureCallback
    {

        private final Consumer val$onFailure;
        private final Consumer val$onSuccess;

        public final void onFailure(Throwable throwable)
        {
            onFailure.accept(throwable);
        }

        public final void onSuccess(Object obj)
        {
            onSuccess.accept(obj);
        }

        _cls2()
        {
            onSuccess = consumer;
            onFailure = consumer1;
            super();
        }
    }

}
