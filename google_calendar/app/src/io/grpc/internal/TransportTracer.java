// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            TimeProvider, ReflectionLongAdderCounter, AtomicLongCounter, LongCounter

public final class TransportTracer
{

    public static final Factory DEFAULT_FACTORY;
    public long keepAlivesSent;
    public volatile long lastMessageReceivedTimeNanos;
    public final LongCounter messagesReceived;
    public long messagesSent;
    public long streamsFailed;
    public long streamsStarted;
    public long streamsSucceeded;
    public final TimeProvider timeProvider;

    public TransportTracer()
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
        messagesReceived = ((LongCounter) (obj));
        timeProvider = TimeProvider.SYSTEM_TIME_PROVIDER;
    }

    public TransportTracer(TimeProvider timeprovider)
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
        messagesReceived = ((LongCounter) (obj));
        timeProvider = timeprovider;
    }

    static 
    {
        DEFAULT_FACTORY = new Factory(TimeProvider.SYSTEM_TIME_PROVIDER);
    }

    private class Factory
    {

        public TimeProvider timeProvider;

        public Factory(TimeProvider timeprovider)
        {
            timeProvider = timeprovider;
        }
    }

}
