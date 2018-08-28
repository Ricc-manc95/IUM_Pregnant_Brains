// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import com.google.common.io.BaseEncoding;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.AbstractClientStream;
import io.grpc.internal.AbstractStream;
import io.grpc.internal.Deframer;
import io.grpc.internal.TimeProvider;
import io.grpc.internal.TransportTracer;
import io.grpc.internal.WritableBuffer;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import okio.Buffer;

// Referenced classes of package io.grpc.okhttp:
//            OkHttpClientStream, OkHttpWritableBuffer, OutboundFlowController, Headers, 
//            OkHttpClientTransport

final class this._cls0
    implements io.grpc.internal.k
{

    private final OkHttpClientStream this$0;

    public final void cancel(Status status)
    {
        synchronized (state.lock)
        {
            state.cancel(status, true, null);
        }
        return;
        status;
        obj;
        JVM INSTR monitorexit ;
        throw status;
    }

    public final void request(int i)
    {
        Object obj = state.lock;
        obj;
        JVM INSTR monitorenter ;
        portState portstate = state;
        ((io.grpc.internal.State) (portstate)).deframer.request(i);
_L1:
        return;
        Throwable throwable;
        throwable;
        portstate.deframeFailed(throwable);
          goto _L1
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void writeFrame(WritableBuffer writablebuffer, boolean flag, boolean flag1, int i)
    {
        Object obj;
        portState portstate;
        if (writablebuffer == null)
        {
            writablebuffer = OkHttpClientStream.EMPTY_BUFFER;
        } else
        {
            obj = ((OkHttpWritableBuffer)writablebuffer).buffer;
            int j = (int)((Buffer) (obj)).size;
            writablebuffer = ((WritableBuffer) (obj));
            if (j > 0)
            {
                transportState().onSendingBytes(j);
                writablebuffer = ((WritableBuffer) (obj));
            }
        }
        obj = state.lock;
        obj;
        JVM INSTR monitorenter ;
        portstate = state;
        if (portstate.cancelSent) goto _L2; else goto _L1
_L1:
        if (portstate.pendingData == null) goto _L4; else goto _L3
_L3:
        portstate.pendingData.add(new ngData(writablebuffer, flag, flag1));
_L2:
        writablebuffer = transportTracer;
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_104;
        }
        writablebuffer.messagesSent = ((TransportTracer) (writablebuffer)).messagesSent + (long)i;
        ((TransportTracer) (writablebuffer)).timeProvider.currentTimeNanos();
        obj;
        JVM INSTR monitorexit ;
        return;
_L4:
        boolean flag2;
        if (portstate._fld0.id != -1)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (flag2)
        {
            break MISSING_BLOCK_LABEL_196;
        }
        throw new IllegalStateException(String.valueOf("streamId should be set"));
        writablebuffer;
        obj;
        JVM INSTR monitorexit ;
        throw writablebuffer;
        portstate.outboundFlow.data(flag, portstate._fld0.id, writablebuffer, flag1);
          goto _L2
    }

    public final void writeHeaders(Metadata metadata, byte abyte0[])
    {
        Object obj;
        obj = String.valueOf(method.fullMethodName);
        String s;
        if (((String) (obj)).length() != 0)
        {
            obj = "/".concat(((String) (obj)));
        } else
        {
            obj = new String("/");
        }
        s = ((String) (obj));
        if (abyte0 != null)
        {
            useGet = true;
            obj = String.valueOf(obj);
            abyte0 = BaseEncoding.BASE64.encode(abyte0, 0, abyte0.length);
            s = (new StringBuilder(String.valueOf(obj).length() + 1 + String.valueOf(abyte0).length())).append(((String) (obj))).append("?").append(abyte0).toString();
        }
        abyte0 = ((byte []) (state.lock));
        abyte0;
        JVM INSTR monitorenter ;
        obj = state;
        obj.requestHeaders = Headers.createRequestHeaders(metadata, s, ((portState) (obj))._fld0.authority, ((portState) (obj))._fld0.userAgent, ((portState) (obj))._fld0.useGet);
        metadata = ((portState) (obj)).transport;
        obj = ((portState) (obj))._fld0;
        if (((OkHttpClientTransport) (metadata)).goAwayStatus == null) goto _L2; else goto _L1
_L1:
        ((OkHttpClientStream) (obj)).state._mth5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMMICD5NIUPRIE1HIUJB5EHGM8OBKC4TIILG_0(((OkHttpClientTransport) (metadata)).goAwayStatus, android.support.v4.content.EFUSED._fld9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, true, new Metadata());
_L3:
        return;
_L2:
        if (((OkHttpClientTransport) (metadata)).streams.size() < ((OkHttpClientTransport) (metadata)).maxConcurrentStreams)
        {
            break MISSING_BLOCK_LABEL_245;
        }
        ((OkHttpClientTransport) (metadata)).pendingStreams.add(obj);
        metadata.setInUse();
          goto _L3
        metadata;
        abyte0;
        JVM INSTR monitorexit ;
        throw metadata;
        metadata.startStream(((OkHttpClientStream) (obj)));
          goto _L3
    }

    ransportState()
    {
        this$0 = OkHttpClientStream.this;
        super();
    }
}
