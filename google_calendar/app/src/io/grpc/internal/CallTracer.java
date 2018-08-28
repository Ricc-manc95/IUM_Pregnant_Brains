// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ReflectionLongAdderCounter, AtomicLongCounter, LongCounter, TimeProvider

final class CallTracer
{

    public final LongCounter callsFailed;
    public final LongCounter callsStarted;
    public final LongCounter callsSucceeded;
    public volatile long lastCallStartedNanos;
    public final TimeProvider timeProvider;

    CallTracer(TimeProvider timeprovider)
    {
        Object obj;
        boolean flag;
        if (ReflectionLongAdderCounter.initializationException == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj = new ReflectionLongAdderCounter();
        } else
        {
            obj = new AtomicLongCounter();
        }
        callsStarted = ((LongCounter) (obj));
        if (ReflectionLongAdderCounter.initializationException == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj = new ReflectionLongAdderCounter();
        } else
        {
            obj = new AtomicLongCounter();
        }
        callsSucceeded = ((LongCounter) (obj));
        if (ReflectionLongAdderCounter.initializationException == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj = new ReflectionLongAdderCounter();
        } else
        {
            obj = new AtomicLongCounter();
        }
        callsFailed = ((LongCounter) (obj));
        timeProvider = timeprovider;
    }

    static 
    {
        new _cls1();
    }

    private class _cls1
        implements Factory
    {

        public final CallTracer create()
        {
            return new CallTracer(TimeProvider.SYSTEM_TIME_PROVIDER);
        }

        _cls1()
        {
        }
    }

}
