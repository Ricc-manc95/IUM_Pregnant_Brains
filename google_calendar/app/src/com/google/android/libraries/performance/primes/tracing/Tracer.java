// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.tracing;

import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.libraries.performance.primes.PrimesLog;
import com.google.android.libraries.performance.primes.PrimesToken;
import com.google.android.libraries.stitch.util.ThreadUtil;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import logs.proto.wireless.performance.mobile.nano.Span;

// Referenced classes of package com.google.android.libraries.performance.primes.tracing:
//            TraceData, SpanEvent, SpanProtoGenerator

public final class Tracer
{

    private static final AtomicReference traceData = new AtomicReference(null);

    public static void activateSideLoadSpans(PrimesToken primestoken)
    {
        if (primestoken == null)
        {
            throw new NullPointerException();
        }
        primestoken = (TraceData)traceData.get();
        if (primestoken == null)
        {
            return;
        } else
        {
            primestoken = (TraceData.ThreadData)((WeakReference)((TraceData) (primestoken)).activeNode.get()).get();
            return;
        }
    }

    public static void cancel(PrimesToken primestoken)
    {
        if (primestoken == null)
        {
            throw new NullPointerException();
        }
        primestoken = (TraceData)traceData.getAndSet(null);
        if (primestoken != null)
        {
            PrimesLog.log(3, "Tracer", "Cancel trace: %s", new Object[] {
                ((TraceData) (primestoken)).rootSpan.spanName
            });
        }
    }

    public static void createRootSpan(PrimesToken primestoken, String s)
    {
        if (primestoken == null)
        {
            throw new NullPointerException();
        }
        if (s == null)
        {
            throw new NullPointerException();
        }
        primestoken = (TraceData)traceData.get();
        if (primestoken != null)
        {
            int i = android.support.v4.content.ModernAsyncTask.Status.CONSTANT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0;
            long l = Thread.currentThread().getId();
            int j = android.support.v4.content.ModernAsyncTask.Status.ROOT_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0;
            primestoken.rootSpan = new SpanEvent(s, i, SystemClock.elapsedRealtime(), -1L, l, j);
            PrimesLog.log(3, "Tracer", "Create root span: %s", new Object[] {
                s
            });
        }
    }

    public static Span[] flush(PrimesToken primestoken, TraceData tracedata)
    {
        if (primestoken == null)
        {
            throw new NullPointerException();
        }
        if (ThreadUtil.sMainThread == null)
        {
            ThreadUtil.sMainThread = Looper.getMainLooper().getThread();
        }
        boolean flag;
        if (Thread.currentThread() == ThreadUtil.sMainThread)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            throw new RuntimeException("Must be called on a background thread");
        }
        if (tracedata.numOfSpans.get() == 0)
        {
            return null;
        } else
        {
            return (new SpanProtoGenerator(tracedata.linkTraceAndGetRootSpan())).generate();
        }
    }

    public static void shutdown(PrimesToken primestoken)
    {
        if (primestoken == null)
        {
            throw new NullPointerException();
        } else
        {
            traceData.set(null);
            return;
        }
    }

    public static void sideLoadSpan(PrimesToken primestoken, String s, long l, long l1)
    {
        if (primestoken == null)
        {
            throw new NullPointerException();
        }
        primestoken = (TraceData)traceData.get();
        TraceData.ThreadData threaddata;
        if (primestoken != null)
        {
            if ((threaddata = (TraceData.ThreadData)((WeakReference)((TraceData) (primestoken)).activeNode.get()).get()).threadSpan.startMs <= l)
            {
                PrimesLog.log(3, "TraceData", "Sideload span: %s. startMs: %d, durationMs: %d", new Object[] {
                    s, Long.valueOf(l), Long.valueOf(l1)
                });
                s = SpanEvent.newSpan$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFA1P6IRB5EDA6UQR5DOTKOQJ1EPGIUR31DPJIUKRKE9KMSPPR9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357D54KIICCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BRGCLP6CRRIDLGMSOR55TO74QBDCLPIUT3IC5HMIRJ75T9N0OBE8LR6ARJK4H9N0OBEAHSN0P9R55666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFEHP62OR9DPJIUKRGC5N4ATJ5DPQ3M___0(PrimesToken.PRIMES_TOKEN, s, android.support.v4.content.ModernAsyncTask.Status.CONSTANT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0, l, l + l1, threaddata.threadId, android.support.v4.content.ModernAsyncTask.Status.CHILD_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0);
                threaddata.threadSpan.addChildSpan(s);
                ((TraceData) (primestoken)).numOfSpans.incrementAndGet();
                return;
            }
        }
    }

    public static boolean start(PrimesToken primestoken, int i, int j)
    {
        if (primestoken == null)
        {
            throw new NullPointerException();
        }
        if (traceData.get() != null)
        {
            PrimesLog.log(3, "Tracer", "Ignore Tracer.start(), current active trace...", new Object[0]);
            return false;
        }
        if (!traceData.compareAndSet(null, new TraceData()))
        {
            PrimesLog.log(3, "Tracer", "Ignore Tracer.start(), current active trace...", new Object[0]);
            return false;
        } else
        {
            PrimesLog.log(3, "Tracer", "Start tracing with buffer: %d", new Object[] {
                Integer.valueOf(j)
            });
            return true;
        }
    }

    public static TraceData stop(PrimesToken primestoken, String s)
    {
        if (primestoken == null)
        {
            throw new NullPointerException();
        }
        boolean flag;
        if (!TextUtils.isEmpty(s))
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
        primestoken = (TraceData)traceData.getAndSet(null);
        if (primestoken != null)
        {
            ((TraceData) (primestoken)).rootSpan.spanName = s;
        }
        PrimesLog.log(3, "Tracer", "Stop trace: %s", new Object[] {
            s
        });
        return primestoken;
    }

}
