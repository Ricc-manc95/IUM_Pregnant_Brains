// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.Collection;

final class ChannelTracer
{

    public final Collection events;
    public int eventsLogged;
    public final Object lock;

    ChannelTracer(final int maxEvents, long l, String s)
    {
        lock = new Object();
        boolean flag;
        if (maxEvents > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("maxEvents must be greater than zero"));
        }
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("channelType"));
        }
        events = new _cls1();
        Object obj = new Channelz.ChannelTrace.Event.Builder();
        obj.description = String.valueOf(s).concat(" created");
        obj.severity = Channelz.ChannelTrace.Event.Severity.CT_INFO;
        obj.timestampNanos = Long.valueOf(l);
        obj = ((Channelz.ChannelTrace.Event.Builder) (obj)).build();
        synchronized (lock)
        {
            events.add(obj);
        }
        return;
        exception;
        s;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private class _cls1 extends ArrayDeque
    {

        private final ChannelTracer this$0;
        private final int val$maxEvents;

        public final boolean add(Object obj)
        {
            obj = (Channelz.ChannelTrace.Event)obj;
            if (size() == maxEvents)
            {
                removeFirst();
            }
            ChannelTracer channeltracer = ChannelTracer.this;
            channeltracer.eventsLogged = channeltracer.eventsLogged + 1;
            return super.add(obj);
        }

        _cls1()
        {
            this$0 = ChannelTracer.this;
            maxEvents = i;
            super();
        }
    }

}
