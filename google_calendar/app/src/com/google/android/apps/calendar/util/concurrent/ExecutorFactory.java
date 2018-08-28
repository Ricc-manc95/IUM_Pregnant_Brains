// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.StrictMode;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            CalendarExecutor, HandlerScheduledExecutorService, WatchDogListeningScheduledExecutorService

public final class ExecutorFactory
{

    private static final android.os.StrictMode.ThreadPolicy API_POLICY = (new android.os.StrictMode.ThreadPolicy.Builder()).detectAll().permitDiskReads().permitDiskWrites().penaltyDeath().build();
    private static final android.os.StrictMode.ThreadPolicy BACKGROUND_POLICY = (new android.os.StrictMode.ThreadPolicy.Builder()).detectAll().permitDiskReads().permitDiskWrites().penaltyDeath().build();
    private static final int BACKGROUND_POOL_SIZE = Math.max(4, Math.min(8, Runtime.getRuntime().availableProcessors() << 1));
    private static final android.os.StrictMode.ThreadPolicy DISK_POLICY = (new android.os.StrictMode.ThreadPolicy.Builder()).detectAll().permitDiskReads().permitDiskWrites().penaltyDeath().build();
    private static final android.os.StrictMode.ThreadPolicy EVENTS_POLICY = (new android.os.StrictMode.ThreadPolicy.Builder()).detectAll().permitDiskReads().permitDiskWrites().penaltyDeath().build();
    private static final android.os.StrictMode.ThreadPolicy NET_POLICY = (new android.os.StrictMode.ThreadPolicy.Builder()).detectAll().permitNetwork().permitDiskReads().permitDiskWrites().penaltyDeath().build();
    private static final ThreadGroup ROOT_THREAD_GROUP = new ThreadGroup("Calendar Threads");
    public final ListeningScheduledExecutorService listeningExecutors[] = new ListeningScheduledExecutorService[CalendarExecutor.values().length];

    public ExecutorFactory(boolean flag)
    {
        Object obj1 = null;
        super();
        ListeningScheduledExecutorService alisteningscheduledexecutorservice[] = listeningExecutors;
        int i = CalendarExecutor.MAIN.ordinal();
        Object obj = new HandlerScheduledExecutorService(new Handler(Looper.getMainLooper()));
        CalendarExecutor calendarexecutor;
        if (obj instanceof ListeningScheduledExecutorService)
        {
            obj = (ListeningScheduledExecutorService)obj;
        } else
        {
            obj = new com.google.common.util.concurrent.MoreExecutors.ScheduledListeningDecorator(((java.util.concurrent.ScheduledExecutorService) (obj)));
        }
        alisteningscheduledexecutorservice[i] = ((ListeningScheduledExecutorService) (obj));
        alisteningscheduledexecutorservice = listeningExecutors;
        i = CalendarExecutor.BACKGROUND.ordinal();
        calendarexecutor = CalendarExecutor.BACKGROUND;
        if (flag)
        {
            obj = BACKGROUND_POLICY;
        } else
        {
            obj = null;
        }
        alisteningscheduledexecutorservice[i] = createExecutor(calendarexecutor, ((android.os.StrictMode.ThreadPolicy) (obj)), 1, BACKGROUND_POOL_SIZE);
        alisteningscheduledexecutorservice = listeningExecutors;
        i = CalendarExecutor.NET.ordinal();
        calendarexecutor = CalendarExecutor.NET;
        if (flag)
        {
            obj = NET_POLICY;
        } else
        {
            obj = null;
        }
        alisteningscheduledexecutorservice[i] = createExecutor(calendarexecutor, ((android.os.StrictMode.ThreadPolicy) (obj)), 1, 8);
        alisteningscheduledexecutorservice = listeningExecutors;
        i = CalendarExecutor.DISK.ordinal();
        calendarexecutor = CalendarExecutor.DISK;
        if (flag)
        {
            obj = DISK_POLICY;
        } else
        {
            obj = null;
        }
        alisteningscheduledexecutorservice[i] = createExecutor(calendarexecutor, ((android.os.StrictMode.ThreadPolicy) (obj)), 1, 4);
        alisteningscheduledexecutorservice = listeningExecutors;
        i = CalendarExecutor.API.ordinal();
        calendarexecutor = CalendarExecutor.API;
        if (flag)
        {
            obj = API_POLICY;
        } else
        {
            obj = null;
        }
        alisteningscheduledexecutorservice[i] = createExecutor(calendarexecutor, ((android.os.StrictMode.ThreadPolicy) (obj)), 1, 1);
        alisteningscheduledexecutorservice = listeningExecutors;
        i = CalendarExecutor.EVENTS.ordinal();
        calendarexecutor = CalendarExecutor.EVENTS;
        obj = obj1;
        if (flag)
        {
            obj = EVENTS_POLICY;
        }
        alisteningscheduledexecutorservice[i] = createExecutor(calendarexecutor, ((android.os.StrictMode.ThreadPolicy) (obj)), 1, 1);
        if (flag)
        {
            int j = 0;
            for (int k = listeningExecutors.length; j < k; j++)
            {
                listeningExecutors[j] = new WatchDogListeningScheduledExecutorService(listeningExecutors[j]);
            }

        }
    }

    private final ListeningScheduledExecutorService createExecutor(CalendarExecutor calendarexecutor, android.os.StrictMode.ThreadPolicy threadpolicy, int i, final int final_i)
    {
        class .Lambda._cls0
            implements ThreadFactory
        {

            private final ThreadGroup arg$1;
            private final CalendarExecutor arg$2;
            private final AtomicInteger arg$3;
            private final int arg$4;
            private final android.os.StrictMode.ThreadPolicy arg$5;

            public final Thread newThread(Runnable runnable)
            {
                return ExecutorFactory.lambda$createExecutor$1$ExecutorFactory(arg$1, arg$2, arg$3, arg$4, arg$5, runnable);
            }

            .Lambda._cls0(ThreadGroup threadgroup, CalendarExecutor calendarexecutor, AtomicInteger atomicinteger, int i, android.os.StrictMode.ThreadPolicy threadpolicy)
            {
                arg$1 = threadgroup;
                arg$2 = calendarexecutor;
                arg$3 = atomicinteger;
                arg$4 = i;
                arg$5 = threadpolicy;
            }
        }

        calendarexecutor = new _cls1(new .Lambda._cls0(new ThreadGroup(ROOT_THREAD_GROUP, calendarexecutor.name()), calendarexecutor, new AtomicInteger(), 1, threadpolicy), calendarexecutor);
        if (calendarexecutor instanceof ListeningScheduledExecutorService)
        {
            return (ListeningScheduledExecutorService)calendarexecutor;
        } else
        {
            return new com.google.common.util.concurrent.MoreExecutors.ScheduledListeningDecorator(calendarexecutor);
        }
    }

    static final void lambda$createExecutor$0$ExecutorFactory(CalendarExecutor calendarexecutor, AtomicInteger atomicinteger, int i, android.os.StrictMode.ThreadPolicy threadpolicy, Runnable runnable)
    {
        Thread thread = Thread.currentThread();
        String s = calendarexecutor.name();
        int j = atomicinteger.getAndIncrement();
        thread.setName((new StringBuilder(String.valueOf(s).length() + 12)).append(s).append("_").append(j).toString());
        boolean flag;
        if (CalendarExecutor.calendarExecutor.get() == null)
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
        CalendarExecutor.calendarExecutor.set(calendarexecutor);
        Process.setThreadPriority(i);
        if (threadpolicy != null)
        {
            StrictMode.setThreadPolicy(threadpolicy);
        }
        runnable.run();
    }

    static final Thread lambda$createExecutor$1$ExecutorFactory(ThreadGroup threadgroup, CalendarExecutor calendarexecutor, AtomicInteger atomicinteger, int i, android.os.StrictMode.ThreadPolicy threadpolicy, Runnable runnable)
    {
        class .Lambda._cls1
            implements Runnable
        {

            private final CalendarExecutor arg$1;
            private final AtomicInteger arg$2;
            private final int arg$3;
            private final android.os.StrictMode.ThreadPolicy arg$4;
            private final Runnable arg$5;

            public final void run()
            {
                ExecutorFactory.lambda$createExecutor$0$ExecutorFactory(arg$1, arg$2, arg$3, arg$4, arg$5);
            }

            .Lambda._cls1(CalendarExecutor calendarexecutor, AtomicInteger atomicinteger, int i, android.os.StrictMode.ThreadPolicy threadpolicy, Runnable runnable)
            {
                arg$1 = calendarexecutor;
                arg$2 = atomicinteger;
                arg$3 = i;
                arg$4 = threadpolicy;
                arg$5 = runnable;
            }
        }

        return new Thread(threadgroup, new .Lambda._cls1(calendarexecutor, atomicinteger, i, threadpolicy, runnable));
    }

    protected static void onAfterExecute$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCDNMSORLE9P6ARJK5T1M2R35DPI62SI5F1IM6TBKDTP3MAAM0()
    {
    }

    protected static void onBeforeExecute$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCDNMSORLE9P6ARJK5T1M2R35DPI62SI5F1IM6TBKDTP3MAAM0()
    {
    }


    private class _cls1 extends ScheduledThreadPoolExecutor
    {

        private final ExecutorFactory this$0;
        private final CalendarExecutor val$calendarExecutor;

        public final void execute(Runnable runnable)
        {
            ExecutorFactory.onBeforeExecute$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCDNMSORLE9P6ARJK5T1M2R35DPI62SI5F1IM6TBKDTP3MAAM0();
            class .Lambda._cls0
                implements Runnable
            {

                private final _cls1 arg$1;
                private final Runnable arg$2;
                private final CalendarExecutor arg$3;

                public final void run()
                {
                    Object obj;
                    obj = arg$1;
                    obj = arg$2;
                    CalendarExecutor calendarexecutor = arg$3;
                    ((Runnable) (obj)).run();
                    ExecutorFactory.onAfterExecute$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCDNMSORLE9P6ARJK5T1M2R35DPI62SI5F1IM6TBKDTP3MAAM0();
                    return;
                    Object obj1;
                    obj1;
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), ((Throwable) (obj1)));
                    ExecutorFactory.onAfterExecute$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCDNMSORLE9P6ARJK5T1M2R35DPI62SI5F1IM6TBKDTP3MAAM0();
                    return;
                    obj1;
                    ExecutorFactory.onAfterExecute$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCDNMSORLE9P6ARJK5T1M2R35DPI62SI5F1IM6TBKDTP3MAAM0();
                    throw obj1;
                }

                .Lambda._cls0(Runnable runnable, CalendarExecutor calendarexecutor)
                {
                    arg$1 = _cls1.this;
                    arg$2 = runnable;
                    arg$3 = calendarexecutor;
                }
            }

            super.execute(new .Lambda._cls0(runnable, calendarExecutor));
        }

        _cls1(ThreadFactory threadfactory, CalendarExecutor calendarexecutor)
        {
            this$0 = ExecutorFactory.this;
            calendarExecutor = calendarexecutor;
            super(final_i, threadfactory);
        }
    }

}
