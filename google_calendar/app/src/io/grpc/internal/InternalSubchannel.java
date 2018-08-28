// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            Instrumented, LogId, TimeProvider, ChannelTracer, 
//            ChannelExecutor, ManagedClientTransport, ConnectionClientTransport, PairSocketAddress, 
//            ProxyDetector, ProxyParameters, ClientTransportFactory, Channelz, 
//            CallTracer, InUseStateAggregator, BackoffPolicy, ClientTransport, 
//            ForwardingConnectionClientTransport, ClientStream, LogExceptionRunnable, WithLogId

final class InternalSubchannel
    implements Instrumented
{
    static final class CallTracingTransport extends ForwardingConnectionClientTransport
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

                public final CallTracingTransport this$0;
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
                    this$0 = CallTracingTransport.this;
                    streamDelegate = clientstream;
                    super();
                }
            }

            return new _cls1();
        }

        CallTracingTransport(ConnectionClientTransport connectionclienttransport, CallTracer calltracer)
        {
            _flddelegate = connectionclienttransport;
            callTracer = calltracer;
        }
    }

    static class Callback
    {

        void onInUse(InternalSubchannel internalsubchannel)
        {
        }

        void onNotInUse(InternalSubchannel internalsubchannel)
        {
        }

        void onStateChange$5166IRPFCTP70OPFD5N78PBIDPGMOBQ9DPQ6ASJEC5M56TB2CDK62RJECLM3MJ39DSNMESJGCCNK6RREDPIM6T39EPKN8UAJEHGN8PA9DPJ6UEP9AO______0(ConnectivityStateInfo connectivitystateinfo)
        {
        }

        void onTerminated(InternalSubchannel internalsubchannel)
        {
        }

        Callback()
        {
        }
    }

    static final class Index
    {

        public List addressGroups;
        public int addressIndex;
        public int groupIndex;

        public Index(List list)
        {
            addressGroups = list;
        }
    }

    final class TransportListener
        implements ManagedClientTransport.Listener
    {

        private final SocketAddress address;
        private final InternalSubchannel this$0;
        private final ConnectionClientTransport transport;

        public final void transportInUse(boolean flag)
        {
            handleTransportInUseState(transport, flag);
        }

        public final void transportReady()
        {
            if (InternalSubchannel.log.isLoggable(Level.FINE))
            {
                InternalSubchannel.log.logp(Level.FINE, "io.grpc.internal.InternalSubchannel$TransportListener", "transportReady", "[{0}] {1} for {2} is ready", new Object[] {
                    logId, transport.getLogId(), address
                });
            }
            Object obj = lock;
            obj;
            JVM INSTR monitorenter ;
            Object obj1;
            obj1 = shutdownReason;
            reconnectPolicy = null;
            if (obj1 == null)
            {
                break MISSING_BLOCK_LABEL_142;
            }
            boolean flag;
            if (activeTransport == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                break MISSING_BLOCK_LABEL_188;
            }
            throw new IllegalStateException(String.valueOf("Unexpected non-null activeTransport"));
            obj1;
            obj;
            JVM INSTR monitorexit ;
            throw obj1;
            obj;
            channelExecutor.drain();
            throw obj;
            if (pendingTransport == transport)
            {
                gotoState(ConnectivityStateInfo.forNonError(ConnectivityState.READY));
                activeTransport = transport;
                pendingTransport = null;
            }
            obj;
            JVM INSTR monitorexit ;
            channelExecutor.drain();
            if (obj1 != null)
            {
                transport.shutdown(((Status) (obj1)));
            }
            return;
        }

        public final void transportShutdown(Status status)
        {
            if (InternalSubchannel.log.isLoggable(Level.FINE))
            {
                InternalSubchannel.log.logp(Level.FINE, "io.grpc.internal.InternalSubchannel$TransportListener", "transportShutdown", "[{0}] {1} for {2} is being shutdown with status {3}", new Object[] {
                    logId, transport.getLogId(), address, status
                });
            }
label0:
            {
                synchronized (lock)
                {
                    if (state.state != ConnectivityState.SHUTDOWN)
                    {
                        break label0;
                    }
                }
                channelExecutor.drain();
                return;
            }
            if (activeTransport != transport) goto _L2; else goto _L1
_L1:
            gotoState(ConnectivityStateInfo.forNonError(ConnectivityState.IDLE));
            activeTransport = null;
            status = addressIndex;
            status.groupIndex = 0;
            status.addressIndex = 0;
_L4:
            obj;
            JVM INSTR monitorexit ;
            channelExecutor.drain();
            return;
_L2:
            if (pendingTransport != transport) goto _L4; else goto _L3
_L3:
            Object obj1;
            boolean flag;
            if (state.state == ConnectivityState.CONNECTING)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            obj1 = state.state;
            if (flag)
            {
                break MISSING_BLOCK_LABEL_263;
            }
            throw new IllegalStateException(Strings.lenientFormat("Expected state is CONNECTING, actual state is %s", new Object[] {
                obj1
            }));
            status;
            obj;
            JVM INSTR monitorexit ;
            throw status;
            status;
            channelExecutor.drain();
            throw status;
            obj1 = addressIndex;
            EquivalentAddressGroup equivalentaddressgroup = (EquivalentAddressGroup)((Index) (obj1)).addressGroups.get(((Index) (obj1)).groupIndex);
            obj1.addressIndex = ((Index) (obj1)).addressIndex + 1;
            if (((Index) (obj1)).addressIndex >= equivalentaddressgroup.addrs.size())
            {
                obj1.groupIndex = ((Index) (obj1)).groupIndex + 1;
                obj1.addressIndex = 0;
            }
            obj1 = addressIndex;
            long l;
            if (((Index) (obj1)).groupIndex < ((Index) (obj1)).addressGroups.size())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                break MISSING_BLOCK_LABEL_601;
            }
            pendingTransport = null;
            obj1 = addressIndex;
            obj1.groupIndex = 0;
            obj1.addressIndex = 0;
            obj1 = InternalSubchannel.this;
            if (io.grpc.Status.Code.OK == status.code)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            break MISSING_BLOCK_LABEL_611;
            if (flag)
            {
                break MISSING_BLOCK_LABEL_428;
            }
            throw new IllegalArgumentException(String.valueOf("The error status must not be OK"));
            ((InternalSubchannel) (obj1)).gotoState(new ConnectivityStateInfo(ConnectivityState.TRANSIENT_FAILURE, status));
            if (((InternalSubchannel) (obj1)).reconnectPolicy == null)
            {
                obj1.reconnectPolicy = ((InternalSubchannel) (obj1)).backoffPolicyProvider.get();
            }
            l = ((InternalSubchannel) (obj1)).reconnectPolicy.nextBackoffNanos() - ((InternalSubchannel) (obj1)).connectingTimer.elapsed(TimeUnit.NANOSECONDS);
            if (InternalSubchannel.log.isLoggable(Level.FINE))
            {
                InternalSubchannel.log.logp(Level.FINE, "io.grpc.internal.InternalSubchannel", "scheduleBackoff", "[{0}] Scheduling backoff for {1} ns", new Object[] {
                    ((InternalSubchannel) (obj1)).logId, Long.valueOf(l)
                });
            }
            if (((InternalSubchannel) (obj1)).reconnectTask == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                break MISSING_BLOCK_LABEL_560;
            }
            throw new IllegalStateException(String.valueOf("previous reconnectTask is not done"));
            obj1.reconnectCanceled = false;
            obj1.reconnectTask = ((InternalSubchannel) (obj1)).scheduledExecutor.schedule(new LogExceptionRunnable(((_cls1EndOfCurrentBackoff) (obj1)). new _cls1EndOfCurrentBackoff()), l, TimeUnit.NANOSECONDS);
              goto _L4
            startNewTransport();
              goto _L4
            if (!flag)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            break MISSING_BLOCK_LABEL_410;
        }

        public final void transportTerminated()
        {
            if (InternalSubchannel.log.isLoggable(Level.FINE))
            {
                InternalSubchannel.log.logp(Level.FINE, "io.grpc.internal.InternalSubchannel$TransportListener", "transportTerminated", "[{0}] {1} for {2} is terminated", new Object[] {
                    logId, transport.getLogId(), address
                });
            }
            Channelz channelz1 = channelz;
            ConnectionClientTransport connectionclienttransport = transport;
            channelz1.otherSockets.remove(Long.valueOf(connectionclienttransport.getLogId().id));
            handleTransportInUseState(transport, false);
            synchronized (lock)
            {
                transports.remove(transport);
                if (state.state == ConnectivityState.SHUTDOWN && transports.isEmpty())
                {
                    if (InternalSubchannel.log.isLoggable(Level.FINE))
                    {
                        InternalSubchannel.log.logp(Level.FINE, "io.grpc.internal.InternalSubchannel$TransportListener", "transportTerminated", "[{0}] Terminated in transportTerminated()", logId);
                    }
                    InternalSubchannel internalsubchannel = InternalSubchannel.this;
                    internalsubchannel.channelExecutor.executeLater(internalsubchannel. new _cls3());
                }
            }
            channelExecutor.drain();
            Exception exception;
            boolean flag;
            if (activeTransport != transport)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("activeTransport still points to this transport. Seems transportShutdown() was not called."));
            } else
            {
                return;
            }
            exception1;
            obj;
            JVM INSTR monitorexit ;
            throw exception1;
            exception;
            channelExecutor.drain();
            throw exception;
        }

        TransportListener(ConnectionClientTransport connectionclienttransport, SocketAddress socketaddress)
        {
            this$0 = InternalSubchannel.this;
            super();
            transport = connectionclienttransport;
            address = socketaddress;
        }

        private class _cls1EndOfCurrentBackoff
            implements Runnable
        {

            private final InternalSubchannel this$0;

            public final void run()
            {
label0:
                {
                    synchronized (lock)
                    {
                        reconnectTask = null;
                        if (!reconnectCanceled)
                        {
                            break label0;
                        }
                    }
                    channelExecutor.drain();
                    return;
                }
                gotoState(ConnectivityStateInfo.forNonError(ConnectivityState.CONNECTING));
                startNewTransport();
                obj;
                JVM INSTR monitorexit ;
                channelExecutor.drain();
                return;
                exception;
                obj;
                JVM INSTR monitorexit ;
                throw exception;
                Object obj1;
                obj1;
                InternalSubchannel.log.logp(Level.WARNING, "io.grpc.internal.InternalSubchannel$1EndOfCurrentBackoff", "run", "Exception handling end of backoff", ((Throwable) (obj1)));
                channelExecutor.drain();
                return;
                obj1;
                channelExecutor.drain();
                throw obj1;
            }

            _cls1EndOfCurrentBackoff()
            {
                this$0 = InternalSubchannel.this;
                super();
            }
        }


        private class _cls3
            implements Runnable
        {

            private final InternalSubchannel this$0;

            public final void run()
            {
                callback.onTerminated(InternalSubchannel.this);
            }

            _cls3()
            {
                this$0 = InternalSubchannel.this;
                super();
            }
        }

    }


    public static final Logger log = Logger.getLogger(io/grpc/internal/InternalSubchannel.getName());
    public volatile ManagedClientTransport activeTransport;
    public Index addressIndex;
    private final String authority;
    public final BackoffPolicy.Provider backoffPolicyProvider;
    public final Callback callback;
    private final CallTracer callsTracer;
    public final ChannelExecutor channelExecutor;
    private final ChannelTracer channelTracer;
    public final Channelz channelz;
    public final Stopwatch connectingTimer;
    public final InUseStateAggregator inUseStateAggregator = new _cls1();
    public final Object lock = new Object();
    public final LogId logId;
    public ConnectionClientTransport pendingTransport;
    public boolean reconnectCanceled;
    public BackoffPolicy reconnectPolicy;
    public ScheduledFuture reconnectTask;
    public final ScheduledExecutorService scheduledExecutor;
    public Status shutdownReason;
    public ConnectivityStateInfo state;
    private final TimeProvider timeProvider;
    private final ClientTransportFactory transportFactory;
    public final Collection transports = new ArrayList();
    private final String userAgent;

    InternalSubchannel(List list, String s, String s1, BackoffPolicy.Provider provider, ClientTransportFactory clienttransportfactory, ScheduledExecutorService scheduledexecutorservice, Supplier supplier, 
            ChannelExecutor channelexecutor, Callback callback1, Channelz channelz1, CallTracer calltracer, ChannelTracer channeltracer, TimeProvider timeprovider)
    {
        logId = new LogId(getClass().getName(), LogId.idAlloc.incrementAndGet());
        state = ConnectivityStateInfo.forNonError(ConnectivityState.IDLE);
        if (list == null)
        {
            throw new NullPointerException(String.valueOf("addressGroups"));
        }
        boolean flag;
        if (!list.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("addressGroups is empty"));
        } else
        {
            checkListHasNoNulls(list, "addressGroups contains null entry");
            addressIndex = new Index(Collections.unmodifiableList(new ArrayList(list)));
            authority = s;
            userAgent = s1;
            backoffPolicyProvider = provider;
            transportFactory = clienttransportfactory;
            scheduledExecutor = scheduledexecutorservice;
            connectingTimer = (Stopwatch)supplier.get();
            channelExecutor = channelexecutor;
            callback = callback1;
            channelz = channelz1;
            callsTracer = calltracer;
            channelTracer = channeltracer;
            timeProvider = timeprovider;
            return;
        }
    }

    static void checkListHasNoNulls(List list, String s)
    {
        for (list = list.iterator(); list.hasNext();)
        {
            if (list.next() == null)
            {
                throw new NullPointerException(String.valueOf(s));
            }
        }

    }

    public final LogId getLogId()
    {
        return logId;
    }

    final void gotoState(final ConnectivityStateInfo newState)
    {
        if (state.state != newState.state)
        {
            String s;
            boolean flag;
            if (state.state != ConnectivityState.SHUTDOWN)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            s = String.valueOf(newState);
            s = (new StringBuilder(String.valueOf(s).length() + 37)).append("Cannot transition out of SHUTDOWN to ").append(s).toString();
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf(s));
            }
            state = newState;
            if (channelTracer != null)
            {
                ChannelTracer channeltracer = channelTracer;
                Channelz.ChannelTrace.Event.Builder builder = new Channelz.ChannelTrace.Event.Builder();
                Object obj1 = String.valueOf(state);
                builder.description = (new StringBuilder(String.valueOf(obj1).length() + 15)).append("Entering ").append(((String) (obj1))).append(" state").toString();
                builder.severity = Channelz.ChannelTrace.Event.Severity.CT_INFO;
                builder.timestampNanos = Long.valueOf(timeProvider.currentTimeNanos());
                obj1 = builder.build();
                synchronized (channeltracer.lock)
                {
                    channeltracer.events.add(obj1);
                }
            }
            channelExecutor.executeLater(new _cls2());
        }
        return;
        newState;
        obj;
        JVM INSTR monitorexit ;
        throw newState;
    }

    final void handleTransportInUseState(final ConnectionClientTransport transport, final boolean inUse)
    {
        channelExecutor.executeLater(new _cls4()).drain();
    }

    final ClientTransport obtainActiveTransport()
    {
        ManagedClientTransport managedclienttransport = activeTransport;
        if (managedclienttransport != null)
        {
            return managedclienttransport;
        }
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        ManagedClientTransport managedclienttransport1 = activeTransport;
        if (managedclienttransport1 == null)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        obj;
        JVM INSTR monitorexit ;
        channelExecutor.drain();
        return managedclienttransport1;
        if (state.state == ConnectivityState.IDLE)
        {
            gotoState(ConnectivityStateInfo.forNonError(ConnectivityState.CONNECTING));
            startNewTransport();
        }
        obj;
        JVM INSTR monitorexit ;
        channelExecutor.drain();
        return null;
        Exception exception1;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        channelExecutor.drain();
        throw exception;
    }

    public final void shutdown(Status status)
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        if (state.state != ConnectivityState.SHUTDOWN) goto _L2; else goto _L1
_L1:
        channelExecutor.drain();
_L4:
        return;
_L2:
        ManagedClientTransport managedclienttransport;
        ConnectionClientTransport connectionclienttransport;
        shutdownReason = status;
        gotoState(ConnectivityStateInfo.forNonError(ConnectivityState.SHUTDOWN));
        managedclienttransport = activeTransport;
        connectionclienttransport = pendingTransport;
        activeTransport = null;
        pendingTransport = null;
        Index index = addressIndex;
        index.groupIndex = 0;
        index.addressIndex = 0;
        if (transports.isEmpty())
        {
            channelExecutor.executeLater(new _cls3());
            if (log.isLoggable(Level.FINE))
            {
                log.logp(Level.FINE, "io.grpc.internal.InternalSubchannel", "shutdown", "[{0}] Terminated in shutdown()", logId);
            }
        }
        if (reconnectTask != null)
        {
            reconnectTask.cancel(false);
            reconnectCanceled = true;
            reconnectTask = null;
            reconnectPolicy = null;
        }
        obj;
        JVM INSTR monitorexit ;
        channelExecutor.drain();
        if (managedclienttransport != null)
        {
            managedclienttransport.shutdown(status);
        }
        if (connectionclienttransport == null) goto _L4; else goto _L3
_L3:
        connectionclienttransport.shutdown(status);
        return;
        status;
        obj;
        JVM INSTR monitorexit ;
        throw status;
        status;
        channelExecutor.drain();
        throw status;
    }

    final void shutdownNow(Status status)
    {
        shutdown(status);
        ArrayList arraylist;
        synchronized (lock)
        {
            arraylist = new ArrayList(transports);
        }
        channelExecutor.drain();
        obj = (ArrayList)arraylist;
        int j = ((ArrayList) (obj)).size();
        for (int i = 0; i < j;)
        {
            Object obj1 = ((ArrayList) (obj)).get(i);
            i++;
            ((ManagedClientTransport)obj1).shutdownNow(status);
        }

        break MISSING_BLOCK_LABEL_95;
        status;
        obj;
        JVM INSTR monitorexit ;
        throw status;
        status;
        channelExecutor.drain();
        throw status;
    }

    final void startNewTransport()
    {
        boolean flag;
        if (reconnectTask == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Should have no reconnectTask scheduled"));
        }
        Object obj = addressIndex;
        Object obj1;
        if (((Index) (obj)).groupIndex == 0 && ((Index) (obj)).addressIndex == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj = connectingTimer;
            obj.elapsedNanos = 0L;
            obj.isRunning = false;
            ((Stopwatch) (obj)).start();
        }
        obj = addressIndex;
        obj = (SocketAddress)((EquivalentAddressGroup)((Index) (obj)).addressGroups.get(((Index) (obj)).groupIndex)).addrs.get(((Index) (obj)).addressIndex);
        obj1 = null;
        if (obj instanceof PairSocketAddress)
        {
            obj1 = ((PairSocketAddress)obj).attributes;
            io.grpc.Attributes.Key key = ProxyDetector.PROXY_PARAMS_KEY;
            obj1 = (ProxyParameters)((Attributes) (obj1)).data.get(key);
            obj = ((PairSocketAddress)obj).address;
        }
        obj1 = new CallTracingTransport(transportFactory.newClientTransport(((SocketAddress) (obj)), authority, userAgent, ((ProxyParameters) (obj1))), callsTracer);
        channelz.otherSockets.put(Long.valueOf(((Instrumented) (obj1)).getLogId().id), obj1);
        if (log.isLoggable(Level.FINE))
        {
            log.logp(Level.FINE, "io.grpc.internal.InternalSubchannel", "startNewTransport", "[{0}] Created {1} for {2}", new Object[] {
                logId, ((ConnectionClientTransport) (obj1)).getLogId(), obj
            });
        }
        pendingTransport = ((ConnectionClientTransport) (obj1));
        transports.add(obj1);
        obj = ((ConnectionClientTransport) (obj1)).start(new TransportListener(((ConnectionClientTransport) (obj1)), ((SocketAddress) (obj))));
        if (obj != null)
        {
            channelExecutor.executeLater(((Runnable) (obj)));
        }
    }

    public final String toString()
    {
        List list;
        synchronized (lock)
        {
            list = addressIndex.addressGroups;
        }
        return (new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName())).add("logId", logId.id).add("addressGroups", list).toString();
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }


    private class _cls1 extends InUseStateAggregator
    {

        private final InternalSubchannel this$0;

        final void handleInUse()
        {
            callback.onInUse(InternalSubchannel.this);
        }

        final void handleNotInUse()
        {
            callback.onNotInUse(InternalSubchannel.this);
        }

        _cls1()
        {
            this$0 = InternalSubchannel.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        private final InternalSubchannel this$0;
        private final ConnectivityStateInfo val$newState;

        public final void run()
        {
            callback._mth5166IRPFCTP70OPFD5N78PBIDPGMOBQ9DPQ6ASJEC5M56TB2CDK62RJECLM3MJ39DSNMESJGCCNK6RREDPIM6T39EPKN8UAJEHGN8PA9DPJ6UEP9AO______0(newState);
        }

        _cls2()
        {
            this$0 = InternalSubchannel.this;
            newState = connectivitystateinfo;
            super();
        }
    }


    private class _cls4
        implements Runnable
    {

        private final InternalSubchannel this$0;
        private final boolean val$inUse;
        private final ConnectionClientTransport val$transport;

        public final void run()
        {
            inUseStateAggregator.updateObjectInUse(transport, inUse);
        }

        _cls4()
        {
            this$0 = InternalSubchannel.this;
            transport = connectionclienttransport;
            inUse = flag;
            super();
        }
    }

}
