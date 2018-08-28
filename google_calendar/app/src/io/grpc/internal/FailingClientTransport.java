// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import java.util.concurrent.Executor;

// Referenced classes of package io.grpc.internal:
//            ClientTransport, FailingClientStream, LogId, ClientStream

final class FailingClientTransport
    implements ClientTransport
{

    public final Status error;
    private final int rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0;

    FailingClientTransport(Status status, int i)
    {
        boolean flag1 = true;
        super();
        boolean flag;
        if (io.grpc.Status.Code.OK == status.code)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("error must not be OK"));
        } else
        {
            error = status;
            rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0 = i;
            return;
        }
    }

    public final LogId getLogId()
    {
        throw new UnsupportedOperationException("Not a real transport");
    }

    public final ClientStream newStream(MethodDescriptor methoddescriptor, Metadata metadata, CallOptions calloptions)
    {
        return new FailingClientStream(error, rpcProgress$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0);
    }

    public final void ping(final ClientTransport.PingCallback callback, Executor executor)
    {
        executor.execute(new _cls1());
    }

    private class _cls1
        implements Runnable
    {

        private final FailingClientTransport this$0;
        private final ClientTransport.PingCallback val$callback;

        public final void run()
        {
            ClientTransport.PingCallback pingcallback = callback;
            new StatusException(error);
            pingcallback.onFailure$5166KOBMC4NMOOBECSNL8Q3IDTRM2OJCCKTIILG_0();
        }

        _cls1()
        {
            this$0 = FailingClientTransport.this;
            callback = pingcallback;
            super();
        }
    }

}
