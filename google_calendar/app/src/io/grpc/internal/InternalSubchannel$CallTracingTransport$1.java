// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ForwardingClientStream, CallTracer, LongCounter, TimeProvider, 
//            ClientStream, ClientStreamListener

final class val.streamDelegate extends ForwardingClientStream
{

    public final _cls1 this$0;
    private final ClientStream val$streamDelegate;

    protected final ClientStream _mthdelegate()
    {
        return val$streamDelegate;
    }

    public final void start(final ClientStreamListener listener)
    {
        CallTracer calltracer = llTracer;
        calltracer.callsStarted.add(1L);
        calltracer.lastCallStartedNanos = calltracer.timeProvider.currentTimeNanos();
        class _cls1 extends ForwardingClientStreamListener
        {

            private final InternalSubchannel.CallTracingTransport._cls1 this$1;
            private final ClientStreamListener val$listener;

            public final void closed(Status status, Metadata metadata)
            {
                CallTracer calltracer1 = callTracer;
                boolean flag;
                if (io.grpc.Status.Code.OK == status.code)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    calltracer1.callsSucceeded.add(1L);
                } else
                {
                    calltracer1.callsFailed.add(1L);
                }
                super.closed(status, metadata);
            }

            public final void closed$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(Status status, int i, Metadata metadata)
            {
                CallTracer calltracer1 = callTracer;
                boolean flag;
                if (io.grpc.Status.Code.OK == status.code)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    calltracer1.callsSucceeded.add(1L);
                } else
                {
                    calltracer1.callsFailed.add(1L);
                }
                super.closed$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(status, i, metadata);
            }

            protected final ClientStreamListener _mthdelegate()
            {
                return listener;
            }

            _cls1()
            {
                this$1 = InternalSubchannel.CallTracingTransport._cls1.this;
                listener = clientstreamlistener;
                super();
            }
        }

        super.start(new _cls1());
    }

    _cls1()
    {
        this$0 = final__pcls1;
        val$streamDelegate = ClientStream.this;
        super();
    }
}
