// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import android.os.Looper;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            ExecutorFactory

public final class CalendarExecutor extends Enum
    implements ListeningScheduledExecutorService
{

    private static final CalendarExecutor $VALUES[];
    public static final CalendarExecutor API;
    public static final CalendarExecutor BACKGROUND;
    public static final CalendarExecutor DISK;
    public static final CalendarExecutor EVENTS;
    public static final CalendarExecutor MAIN;
    public static final CalendarExecutor NET;
    public static final ThreadLocal calendarExecutor = new ThreadLocal();
    private static ExecutorFactory delegateFactory;

    private CalendarExecutor(String s, int i)
    {
        super(s, i);
    }

    public static CalendarExecutor currentExecutor()
    {
        if (calendarExecutor.get() == null && Looper.getMainLooper() == Looper.myLooper())
        {
            return MAIN;
        } else
        {
            return (CalendarExecutor)calendarExecutor.get();
        }
    }

    public static void installExecutorFactory(ExecutorFactory executorfactory)
    {
        com/google/android/apps/calendar/util/concurrent/CalendarExecutor;
        JVM INSTR monitorenter ;
        boolean flag;
        if (delegateFactory == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_39;
        }
        throw new IllegalStateException(String.valueOf("Factory already set."));
        executorfactory;
        com/google/android/apps/calendar/util/concurrent/CalendarExecutor;
        JVM INSTR monitorexit ;
        throw executorfactory;
        delegateFactory = executorfactory;
        com/google/android/apps/calendar/util/concurrent/CalendarExecutor;
        JVM INSTR monitorexit ;
    }

    public static CalendarExecutor[] values()
    {
        return (CalendarExecutor[])$VALUES.clone();
    }

    public final boolean awaitTermination(long l, TimeUnit timeunit)
        throws InterruptedException
    {
        return getDelegate().awaitTermination(l, timeunit);
    }

    public final void checkOnThread()
        throws IllegalStateException
    {
        boolean flag;
        if (currentExecutor() == this)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            String s = String.valueOf(this);
            String s1 = String.valueOf(currentExecutor());
            throw new IllegalStateException((new StringBuilder(String.valueOf(s).length() + 40 + String.valueOf(s1).length())).append("Expected to be on ").append(s).append(", actually running on ").append(s1).toString());
        } else
        {
            return;
        }
    }

    public final void execute(Runnable runnable)
    {
        getDelegate().execute(runnable);
    }

    public final ListeningScheduledExecutorService getDelegate()
    {
        this;
        JVM INSTR monitorenter ;
        ListeningScheduledExecutorService listeningscheduledexecutorservice;
        if (delegateFactory == null)
        {
            delegateFactory = new ExecutorFactory(true);
        }
        listeningscheduledexecutorservice = delegateFactory.listeningExecutors[ordinal()];
        this;
        JVM INSTR monitorexit ;
        return listeningscheduledexecutorservice;
        Exception exception;
        exception;
        throw exception;
    }

    public final List invokeAll(Collection collection)
        throws InterruptedException
    {
        return getDelegate().invokeAll(collection);
    }

    public final List invokeAll(Collection collection, long l, TimeUnit timeunit)
        throws InterruptedException
    {
        return getDelegate().invokeAll(collection, l, timeunit);
    }

    public final Object invokeAny(Collection collection)
        throws InterruptedException, ExecutionException
    {
        return getDelegate().invokeAny(collection);
    }

    public final Object invokeAny(Collection collection, long l, TimeUnit timeunit)
        throws InterruptedException, ExecutionException, TimeoutException
    {
        return getDelegate().invokeAny(collection, l, timeunit);
    }

    public final boolean isShutdown()
    {
        return getDelegate().isShutdown();
    }

    public final boolean isTerminated()
    {
        return getDelegate().isTerminated();
    }

    public final ListenableScheduledFuture schedule(Runnable runnable, long l, TimeUnit timeunit)
    {
        return getDelegate().schedule(runnable, l, timeunit);
    }

    public final ListenableScheduledFuture schedule(Callable callable, long l, TimeUnit timeunit)
    {
        return getDelegate().schedule(callable, l, timeunit);
    }

    public final ScheduledFuture schedule(Runnable runnable, long l, TimeUnit timeunit)
    {
        return getDelegate().schedule(runnable, l, timeunit);
    }

    public final ScheduledFuture schedule(Callable callable, long l, TimeUnit timeunit)
    {
        return getDelegate().schedule(callable, l, timeunit);
    }

    public final ListenableScheduledFuture scheduleAtFixedRate(Runnable runnable, long l, long l1, TimeUnit timeunit)
    {
        return getDelegate().scheduleAtFixedRate(runnable, l, l1, timeunit);
    }

    public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long l, long l1, TimeUnit timeunit)
    {
        return getDelegate().scheduleAtFixedRate(runnable, l, l1, timeunit);
    }

    public final ListenableScheduledFuture scheduleWithFixedDelay(Runnable runnable, long l, long l1, TimeUnit timeunit)
    {
        return getDelegate().scheduleWithFixedDelay(runnable, l, l1, timeunit);
    }

    public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long l, long l1, TimeUnit timeunit)
    {
        return getDelegate().scheduleWithFixedDelay(runnable, l, l1, timeunit);
    }

    public final void shutdown()
    {
        getDelegate().shutdown();
    }

    public final List shutdownNow()
    {
        return getDelegate().shutdownNow();
    }

    public final ListenableFuture submit(Runnable runnable)
    {
        runnable = getDelegate().submit(runnable);
        if (runnable instanceof FluentFuture)
        {
            return (FluentFuture)runnable;
        } else
        {
            return new ForwardingFluentFuture(runnable);
        }
    }

    public final ListenableFuture submit(Runnable runnable, Object obj)
    {
        runnable = getDelegate().submit(runnable, obj);
        if (runnable instanceof FluentFuture)
        {
            return (FluentFuture)runnable;
        } else
        {
            return new ForwardingFluentFuture(runnable);
        }
    }

    public final ListenableFuture submit(Callable callable)
    {
        callable = getDelegate().submit(callable);
        if (callable instanceof FluentFuture)
        {
            return (FluentFuture)callable;
        } else
        {
            return new ForwardingFluentFuture(callable);
        }
    }

    public final Future submit(Runnable runnable)
    {
        runnable = getDelegate().submit(runnable);
        if (runnable instanceof FluentFuture)
        {
            runnable = (FluentFuture)runnable;
        } else
        {
            runnable = new ForwardingFluentFuture(runnable);
        }
        return (FluentFuture)runnable;
    }

    public final Future submit(Runnable runnable, Object obj)
    {
        runnable = getDelegate().submit(runnable, obj);
        if (runnable instanceof FluentFuture)
        {
            runnable = (FluentFuture)runnable;
        } else
        {
            runnable = new ForwardingFluentFuture(runnable);
        }
        return (FluentFuture)runnable;
    }

    public final Future submit(Callable callable)
    {
        callable = getDelegate().submit(callable);
        if (callable instanceof FluentFuture)
        {
            callable = (FluentFuture)callable;
        } else
        {
            callable = new ForwardingFluentFuture(callable);
        }
        return (FluentFuture)callable;
    }

    static 
    {
        MAIN = new CalendarExecutor("MAIN", 0);
        BACKGROUND = new CalendarExecutor("BACKGROUND", 1);
        NET = new CalendarExecutor("NET", 2);
        DISK = new CalendarExecutor("DISK", 3);
        API = new CalendarExecutor("API", 4);
        EVENTS = new CalendarExecutor("EVENTS", 5);
        $VALUES = (new CalendarExecutor[] {
            MAIN, BACKGROUND, NET, DISK, API, EVENTS
        });
    }
}
