// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import com.google.common.base.Throwables;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

// Referenced classes of package com.google.common.util.concurrent:
//            ListeningExecutorService

public final class MoreExecutors
{

    private static boolean isAppEngine()
    {
        if (System.getProperty("com.google.appengine.runtime.environment") != null)
        {
            Object obj;
            try
            {
                obj = Class.forName("com.google.apphosting.api.ApiProxy").getMethod("getCurrentEnvironment", new Class[0]).invoke(null, new Object[0]);
            }
            catch (ClassNotFoundException classnotfoundexception)
            {
                return false;
            }
            catch (InvocationTargetException invocationtargetexception)
            {
                return false;
            }
            catch (IllegalAccessException illegalaccessexception)
            {
                return false;
            }
            catch (NoSuchMethodException nosuchmethodexception)
            {
                return false;
            }
            if (obj != null)
            {
                return true;
            }
        }
        return false;
    }

    public static ListeningExecutorService listeningDecorator(ExecutorService executorservice)
    {
        if (executorservice instanceof ListeningExecutorService)
        {
            return (ListeningExecutorService)executorservice;
        }
        if (executorservice instanceof ScheduledExecutorService)
        {
            return new ScheduledListeningDecorator((ScheduledExecutorService)executorservice);
        } else
        {
            return new ListeningDecorator(executorservice);
        }
    }

    public static ThreadFactory platformThreadFactory()
    {
        if (!isAppEngine())
        {
            return Executors.defaultThreadFactory();
        }
        ThreadFactory threadfactory = (ThreadFactory)Class.forName("com.google.appengine.api.ThreadManager").getMethod("currentRequestThreadFactory", new Class[0]).invoke(null, new Object[0]);
        return threadfactory;
        Object obj;
        obj;
_L2:
        throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", ((Throwable) (obj)));
        obj;
        throw Throwables.propagate(((InvocationTargetException) (obj)).getCause());
        obj;
        continue; /* Loop/switch isn't completed */
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private class ScheduledListeningDecorator extends ListeningDecorator
        implements ListeningScheduledExecutorService
    {
        private class ListeningDecorator extends AbstractListeningExecutorService
        {

            private final ExecutorService _flddelegate;

            public final boolean awaitTermination(long l, TimeUnit timeunit)
                throws InterruptedException
            {
                return _flddelegate.awaitTermination(l, timeunit);
            }

            public final void execute(Runnable runnable)
            {
                _flddelegate.execute(runnable);
            }

            public final boolean isShutdown()
            {
                return _flddelegate.isShutdown();
            }

            public final boolean isTerminated()
            {
                return _flddelegate.isTerminated();
            }

            public final void shutdown()
            {
                _flddelegate.shutdown();
            }

            public final List shutdownNow()
            {
                return _flddelegate.shutdownNow();
            }

            ListeningDecorator(ExecutorService executorservice)
            {
                if (executorservice == null)
                {
                    throw new NullPointerException();
                } else
                {
                    _flddelegate = (ExecutorService)executorservice;
                    return;
                }
            }
        }


        private final ScheduledExecutorService _flddelegate;

        public final ListenableScheduledFuture schedule(Runnable runnable, long l, TimeUnit timeunit)
        {
            runnable = new TrustedListenableFutureTask(Executors.callable(runnable, null));
            class ListenableScheduledTask extends ForwardingListenableFuture.SimpleForwardingListenableFuture
                implements ListenableScheduledFuture
            {

                private final ScheduledFuture scheduledDelegate;

                public final boolean cancel(boolean flag)
                {
                    boolean flag1 = super.cancel(flag);
                    if (flag1)
                    {
                        scheduledDelegate.cancel(flag);
                    }
                    return flag1;
                }

                public final int compareTo(Object obj)
                {
                    obj = (Delayed)obj;
                    return scheduledDelegate.compareTo(obj);
                }

                public final long getDelay(TimeUnit timeunit1)
                {
                    return scheduledDelegate.getDelay(timeunit1);
                }

                public ListenableScheduledTask(ListenableFuture listenablefuture, ScheduledFuture scheduledfuture)
                {
                    super(listenablefuture);
                    scheduledDelegate = scheduledfuture;
                }
            }

            return new ListenableScheduledTask(runnable, _flddelegate.schedule(runnable, l, timeunit));
        }

        public final ListenableScheduledFuture schedule(Callable callable, long l, TimeUnit timeunit)
        {
            callable = new TrustedListenableFutureTask(callable);
            return new ListenableScheduledTask(callable, _flddelegate.schedule(callable, l, timeunit));
        }

        public final volatile ScheduledFuture schedule(Runnable runnable, long l, TimeUnit timeunit)
        {
            return schedule(runnable, l, timeunit);
        }

        public final volatile ScheduledFuture schedule(Callable callable, long l, TimeUnit timeunit)
        {
            return schedule(callable, l, timeunit);
        }

        public final ListenableScheduledFuture scheduleAtFixedRate(Runnable runnable, long l, long l1, TimeUnit timeunit)
        {
            class NeverSuccessfulListenableFutureTask extends AbstractFuture
                implements Runnable
            {

                private final Runnable _flddelegate;

                public final void run()
                {
                    try
                    {
                        _flddelegate.run();
                        return;
                    }
                    catch (Throwable throwable)
                    {
                        setException(throwable);
                        throw Throwables.propagate(throwable);
                    }
                }

                public NeverSuccessfulListenableFutureTask(Runnable runnable)
                {
                    if (runnable == null)
                    {
                        throw new NullPointerException();
                    } else
                    {
                        _flddelegate = (Runnable)runnable;
                        return;
                    }
                }
            }

            runnable = new NeverSuccessfulListenableFutureTask(runnable);
            return new ListenableScheduledTask(runnable, _flddelegate.scheduleAtFixedRate(runnable, l, l1, timeunit));
        }

        public final volatile ScheduledFuture scheduleAtFixedRate(Runnable runnable, long l, long l1, TimeUnit timeunit)
        {
            return scheduleAtFixedRate(runnable, l, l1, timeunit);
        }

        public final ListenableScheduledFuture scheduleWithFixedDelay(Runnable runnable, long l, long l1, TimeUnit timeunit)
        {
            runnable = new NeverSuccessfulListenableFutureTask(runnable);
            return new ListenableScheduledTask(runnable, _flddelegate.scheduleWithFixedDelay(runnable, l, l1, timeunit));
        }

        public final volatile ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long l, long l1, TimeUnit timeunit)
        {
            return scheduleWithFixedDelay(runnable, l, l1, timeunit);
        }

        public ScheduledListeningDecorator(ScheduledExecutorService scheduledexecutorservice)
        {
            super(scheduledexecutorservice);
            if (scheduledexecutorservice == null)
            {
                throw new NullPointerException();
            } else
            {
                _flddelegate = (ScheduledExecutorService)scheduledexecutorservice;
                return;
            }
        }
    }

}
