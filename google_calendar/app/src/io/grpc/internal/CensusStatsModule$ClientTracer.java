// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.ClientStreamTracer;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            CensusStatsModule

static final class er extends ClientStreamTracer
{

    private static final AtomicLongFieldUpdater inboundMessageCountUpdater;
    private static final AtomicLongFieldUpdater inboundUncompressedSizeUpdater;
    private static final AtomicLongFieldUpdater inboundWireSizeUpdater;
    private static final AtomicLongFieldUpdater outboundMessageCountUpdater;
    private static final AtomicLongFieldUpdater outboundUncompressedSizeUpdater;
    private static final AtomicLongFieldUpdater outboundWireSizeUpdater;
    public volatile long inboundMessageCount;
    public volatile long inboundUncompressedSize;
    public volatile long inboundWireSize;
    public volatile long outboundMessageCount;
    public volatile long outboundUncompressedSize;
    public volatile long outboundWireSize;

    public final void inboundMessage$514IILG_0()
    {
        if (inboundMessageCountUpdater != null)
        {
            inboundMessageCountUpdater.getAndIncrement(this);
            return;
        } else
        {
            inboundMessageCount = inboundMessageCount + 1L;
            return;
        }
    }

    public final void inboundUncompressedSize(long l)
    {
        if (inboundUncompressedSizeUpdater != null)
        {
            inboundUncompressedSizeUpdater.getAndAdd(this, l);
            return;
        } else
        {
            inboundUncompressedSize = inboundUncompressedSize + l;
            return;
        }
    }

    public final void inboundWireSize(long l)
    {
        if (inboundWireSizeUpdater != null)
        {
            inboundWireSizeUpdater.getAndAdd(this, l);
            return;
        } else
        {
            inboundWireSize = inboundWireSize + l;
            return;
        }
    }

    public final void outboundMessage$514IILG_0()
    {
        if (outboundMessageCountUpdater != null)
        {
            outboundMessageCountUpdater.getAndIncrement(this);
            return;
        } else
        {
            outboundMessageCount = outboundMessageCount + 1L;
            return;
        }
    }

    public final void outboundUncompressedSize(long l)
    {
        if (outboundUncompressedSizeUpdater != null)
        {
            outboundUncompressedSizeUpdater.getAndAdd(this, l);
            return;
        } else
        {
            outboundUncompressedSize = outboundUncompressedSize + l;
            return;
        }
    }

    public final void outboundWireSize(long l)
    {
        if (outboundWireSizeUpdater != null)
        {
            outboundWireSizeUpdater.getAndAdd(this, l);
            return;
        } else
        {
            outboundWireSize = outboundWireSize + l;
            return;
        }
    }

    static 
    {
        AtomicLongFieldUpdater atomiclongfieldupdater5 = null;
        AtomicLongFieldUpdater atomiclongfieldupdater;
        AtomicLongFieldUpdater atomiclongfieldupdater1;
        AtomicLongFieldUpdater atomiclongfieldupdater2;
        AtomicLongFieldUpdater atomiclongfieldupdater3;
        AtomicLongFieldUpdater atomiclongfieldupdater4;
        AtomicLongFieldUpdater atomiclongfieldupdater6;
        atomiclongfieldupdater4 = AtomicLongFieldUpdater.newUpdater(io/grpc/internal/CensusStatsModule$ClientTracer, "outboundMessageCount");
        atomiclongfieldupdater3 = AtomicLongFieldUpdater.newUpdater(io/grpc/internal/CensusStatsModule$ClientTracer, "inboundMessageCount");
        atomiclongfieldupdater2 = AtomicLongFieldUpdater.newUpdater(io/grpc/internal/CensusStatsModule$ClientTracer, "outboundWireSize");
        atomiclongfieldupdater1 = AtomicLongFieldUpdater.newUpdater(io/grpc/internal/CensusStatsModule$ClientTracer, "inboundWireSize");
        atomiclongfieldupdater6 = AtomicLongFieldUpdater.newUpdater(io/grpc/internal/CensusStatsModule$ClientTracer, "outboundUncompressedSize");
        atomiclongfieldupdater = AtomicLongFieldUpdater.newUpdater(io/grpc/internal/CensusStatsModule$ClientTracer, "inboundUncompressedSize");
        atomiclongfieldupdater5 = atomiclongfieldupdater6;
_L2:
        outboundMessageCountUpdater = atomiclongfieldupdater4;
        inboundMessageCountUpdater = atomiclongfieldupdater3;
        outboundWireSizeUpdater = atomiclongfieldupdater2;
        inboundWireSizeUpdater = atomiclongfieldupdater1;
        outboundUncompressedSizeUpdater = atomiclongfieldupdater5;
        inboundUncompressedSizeUpdater = atomiclongfieldupdater;
        return;
        Throwable throwable;
        throwable;
        CensusStatsModule.logger.logp(Level.SEVERE, "io.grpc.internal.CensusStatsModule$ClientTracer", "<clinit>", "Creating atomic field updaters failed", throwable);
        throwable = null;
        atomiclongfieldupdater1 = null;
        atomiclongfieldupdater2 = null;
        atomiclongfieldupdater3 = null;
        atomiclongfieldupdater4 = null;
        if (true) goto _L2; else goto _L1
_L1:
    }

    er()
    {
    }
}
