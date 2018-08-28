// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.ArrayDeque;

// Referenced classes of package io.grpc.internal:
//            ChannelTracer

final class val.maxEvents extends ArrayDeque
{

    private final ChannelTracer this$0;
    private final int val$maxEvents;

    public final boolean add(Object obj)
    {
        obj = (Trace.Event)obj;
        if (size() == val$maxEvents)
        {
            removeFirst();
        }
        ChannelTracer channeltracer = ChannelTracer.this;
        channeltracer.eventsLogged = channeltracer.eventsLogged + 1;
        return super.add(obj);
    }

    Trace.Event()
    {
        this$0 = final_channeltracer;
        val$maxEvents = I.this;
        super();
    }
}
