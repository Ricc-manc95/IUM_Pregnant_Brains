// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;

// Referenced classes of package io.grpc.internal:
//            ForwardingConnectionClientTransport, InternalSubchannel, CallTracer, ConnectionClientTransport, 
//            ClientStream

static final class callTracer extends ForwardingConnectionClientTransport
{

    public final CallTracer callTracer;
    private final ConnectionClientTransport _flddelegate;

    protected final ConnectionClientTransport _mthdelegate()
    {
        return _flddelegate;
    }

    public final ClientStream newStream(MethodDescriptor methoddescriptor, Metadata metadata, CallOptions calloptions)
    {
        class _cls1 extends ForwardingClientStream
        {

            public final InternalSubchannel.CallTracingTransport this$0;
            private final ClientStream val$streamDelegate;

            protected final ClientStream _mthdelegate()
            {
                return streamDelegate;
            }

            public final void start(final ClientStreamListener listener)
            {
                CallTracer calltracer = callTracer;
                calltracer.callsStarted.add(1L);
                calltracer.lastCallStartedNanos = calltracer.timeProvider.currentTimeNanos();
                class _cls1 extends ForwardingClientStreamListener
                {

                    private final _cls1 this$1;
                    private final ClientStreamListener val$listener;

                    public final void closed(Status status, Metadata metadata1)
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
                        super.closed(status, metadata1);
                    }

                    public final void closed$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(Status status, int i, Metadata metadata1)
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
                        super.closed$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(status, i, metadata1);
                    }

                    protected final ClientStreamListener _mthdelegate()
                    {
                        return listener;
                    }

                        _cls1()
                        {
                            this$1 = _cls1.this;
                            listener = clientstreamlistener;
                            super();
                        }
                }

                super.start(new _cls1());
            }

            _cls1()
            {
                this$0 = InternalSubchannel.CallTracingTransport.this;
                streamDelegate = clientstream;
                super();
            }
        }

        return new _cls1();
    }

    _cls1(ConnectionClientTransport connectionclienttransport, CallTracer calltracer)
    {
        _flddelegate = connectionclienttransport;
        callTracer = calltracer;
    }
}
