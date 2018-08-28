// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Compressor;
import io.grpc.Metadata;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package io.grpc.internal:
//            Framer, AbstractClientStream, StatsTraceContext, IoUtils

final class statsTraceCtx
    implements Framer
{

    private boolean closed;
    private Metadata headers;
    private byte payload[];
    private final StatsTraceContext statsTraceCtx;
    private final AbstractClientStream this$0;

    public final void close()
    {
        boolean flag = true;
        closed = true;
        if (payload == null)
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Lack of request message. GET request is only supported for unary requests"));
        } else
        {
            abstractClientStreamSink().Headers(headers, payload);
            payload = null;
            headers = null;
            return;
        }
    }

    public final void flush()
    {
    }

    public final boolean isClosed()
    {
        return closed;
    }

    public final Framer setCompressor(Compressor compressor)
    {
        return this;
    }

    public final void setMaxOutboundMessageSize(int i)
    {
    }

    public final void writePayload(InputStream inputstream)
    {
        boolean flag;
        if (payload == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("writePayload should not be called multiple times"));
        }
        try
        {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            IoUtils.copy(inputstream, bytearrayoutputstream);
            payload = bytearrayoutputstream.toByteArray();
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            throw new RuntimeException(inputstream);
        }
        statsTraceCtx.outboundMessage(0);
        statsTraceCtx.outboundMessageSent(0, payload.length, payload.length);
        statsTraceCtx.outboundUncompressedSize(payload.length);
        statsTraceCtx.outboundWireSize(payload.length);
    }

    public (Metadata metadata, StatsTraceContext statstracecontext)
    {
        this$0 = AbstractClientStream.this;
        super();
        if (metadata == null)
        {
            throw new NullPointerException(String.valueOf("headers"));
        }
        headers = (Metadata)metadata;
        if (statstracecontext == null)
        {
            throw new NullPointerException(String.valueOf("statsTraceCtx"));
        } else
        {
            statsTraceCtx = (StatsTraceContext)statstracecontext;
            return;
        }
    }
}
