// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ClientInterceptors;
import io.grpc.CompressorRegistry;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.DecompressorRegistry;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;
import io.grpc.NameResolver;
import io.grpc.Status;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package io.grpc.internal:
//            Instrumented, LogId, ConnectivityStateManager, AbstractManagedChannelImplBuilder, 
//            AutoConfiguredLoadBalancerFactory, ObjectPool, DelayedClientTransport, CallCredentialsApplyingTransportFactory, 
//            ServiceConfigInterceptor, Rescheduler, ClientTransportFactory, TimeProvider, 
//            Channelz, ChannelTracer, GrpcAttributes, ServiceConfigUtil, 
//            ChannelExecutor, InUseStateAggregator, InternalSubchannel, OobChannel, 
//            CallTracer, BackoffPolicy, ManagedClientTransport, AbstractSubchannel, 
//            ClientCallImpl, LogExceptionRunnable, ClientTransport, RetriableStream

final class ManagedChannelImpl extends ManagedChannel
    implements Instrumented
{
    final class IdleModeTimer
        implements Runnable
    {

        private final ManagedChannelImpl this$0;

        public final void run()
        {
            enterIdleMode();
        }

        IdleModeTimer()
        {
            this$0 = ManagedChannelImpl.this;
            super();
        }
    }

    final class LbHelperImpl extends io.grpc.LoadBalancer.Helper
    {

        public LoadBalancer lb;
        public final NameResolver nr;
        public final ManagedChannelImpl this$0;

        private final AbstractSubchannel createSubchannel(List list, final Attributes internalSubchannel)
        {
            if (list == null)
            {
                throw new NullPointerException(String.valueOf("addressGroups"));
            }
            if (internalSubchannel == null)
            {
                throw new NullPointerException(String.valueOf("attrs"));
            }
            boolean flag;
            if (!terminated)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("Channel is terminated"));
            }
            final SubchannelImpl subchannel = new SubchannelImpl(internalSubchannel);
            internalSubchannel = null;
            long l = timeProvider.currentTimeNanos();
            if (maxTraceEvents > 0)
            {
                internalSubchannel = new ChannelTracer(maxTraceEvents, l, "Subchannel");
            }
            class _cls1 extends InternalSubchannel.Callback
            {

                private final LbHelperImpl this$1;
                private final SubchannelImpl val$subchannel;

                final void onInUse(InternalSubchannel internalsubchannel)
                {
                    inUseStateAggregator.updateObjectInUse(internalsubchannel, true);
                }

                final void onNotInUse(InternalSubchannel internalsubchannel)
                {
                    inUseStateAggregator.updateObjectInUse(internalsubchannel, false);
                }

                final void onStateChange$5166IRPFCTP70OPFD5N78PBIDPGMOBQ9DPQ6ASJEC5M56TB2CDK62RJECLM3MJ39DSNMESJGCCNK6RREDPIM6T39EPKN8UAJEHGN8PA9DPJ6UEP9AO______0(ConnectivityStateInfo connectivitystateinfo)
                {
                    LbHelperImpl lbhelperimpl = LbHelperImpl.this;
                    if (connectivitystateinfo.state == ConnectivityState.TRANSIENT_FAILURE || connectivitystateinfo.state == ConnectivityState.IDLE)
                    {
                        lbhelperimpl.nr.refresh();
                    }
                    if (LbHelperImpl.this == lbHelper)
                    {
                        lb.handleSubchannelState(subchannel, connectivitystateinfo);
                    }
                }

                final void onTerminated(InternalSubchannel internalsubchannel)
                {
                    subchannels.remove(internalsubchannel);
                    channelz.subchannels.remove(Long.valueOf(internalsubchannel.getLogId().id));
                    internalsubchannel = _fld0;
                    if (!((ManagedChannelImpl) (internalsubchannel)).terminated && ((ManagedChannelImpl) (internalsubchannel)).shutdown.get() && ((ManagedChannelImpl) (internalsubchannel)).subchannels.isEmpty() && ((ManagedChannelImpl) (internalsubchannel)).oobChannels.isEmpty())
                    {
                        ManagedChannelImpl.logger.logp(Level.FINE, "io.grpc.internal.ManagedChannelImpl", "maybeTerminateChannel", "[{0}] Terminated", ((ManagedChannelImpl) (internalsubchannel)).logId);
                        ((ManagedChannelImpl) (internalsubchannel)).channelz.rootChannels.remove(Long.valueOf(internalsubchannel.getLogId().id));
                        internalsubchannel.terminated = true;
                        ((ManagedChannelImpl) (internalsubchannel)).terminatedLatch.countDown();
                        ((ManagedChannelImpl) (internalsubchannel)).executorPool.returnObject(((ManagedChannelImpl) (internalsubchannel)).executor);
                        ((ManagedChannelImpl) (internalsubchannel)).transportFactory.close();
                    }
                }

                _cls1()
                {
                    this$1 = LbHelperImpl.this;
                    subchannel = subchannelimpl;
                    super();
                }
            }

            internalSubchannel = new InternalSubchannel(list, authority(), userAgent, backoffPolicyProvider, transportFactory, transportFactory.getScheduledExecutorService(), stopwatchSupplier, channelExecutor, new _cls1(), channelz, callTracerFactory.create(), internalSubchannel, timeProvider);
            if (channelTracer != null)
            {
                ChannelTracer channeltracer = channelTracer;
                Channelz.ChannelTrace.Event.Builder builder = new Channelz.ChannelTrace.Event.Builder();
                builder.description = "Child channel created";
                builder.severity = Channelz.ChannelTrace.Event.Severity.CT_INFO;
                builder.timestampNanos = Long.valueOf(l);
                builder.subchannelRef = internalSubchannel;
                Channelz.ChannelTrace.Event event = builder.build();
                synchronized (channeltracer.lock)
                {
                    channeltracer.events.add(event);
                }
            }
            channelz.subchannels.put(Long.valueOf(internalSubchannel.getLogId().id), internalSubchannel);
            subchannel.subchannel = internalSubchannel;
            ManagedChannelImpl.logger.logp(Level.FINE, "io.grpc.internal.ManagedChannelImpl$LbHelperImpl", "createSubchannel", "[{0}] {1} created for {2}", new Object[] {
                logId, ((InternalSubchannel) (internalSubchannel)).logId, list
            });
            class _cls2
                implements Runnable
            {

                private final LbHelperImpl this$1;
                private final InternalSubchannel val$internalSubchannel;

                public final void run()
                {
                    if (terminating)
                    {
                        internalSubchannel.shutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
                    }
                    if (!terminated)
                    {
                        subchannels.add(internalSubchannel);
                    }
                }

                _cls2()
                {
                    this$1 = LbHelperImpl.this;
                    internalSubchannel = internalsubchannel;
                    super();
                }
            }

            list = new _cls2();
            channelExecutor.executeLater(list).drain();
            return subchannel;
            list;
            obj;
            JVM INSTR monitorexit ;
            throw list;
        }

        public final volatile io.grpc.LoadBalancer.Subchannel createSubchannel(List list, Attributes attributes)
        {
            return createSubchannel(list, attributes);
        }

        public final void updateBalancingState(final ConnectivityState newState, final io.grpc.LoadBalancer.SubchannelPicker newPicker)
        {
            if (newState == null)
            {
                throw new NullPointerException(String.valueOf("newState"));
            }
            if (newPicker == null)
            {
                throw new NullPointerException(String.valueOf("newPicker"));
            } else
            {
                class _cls3
                    implements Runnable
                {

                    private final LbHelperImpl this$1;
                    private final io.grpc.LoadBalancer.SubchannelPicker val$newPicker;
                    private final ConnectivityState val$newState;

                    public final void run()
                    {
                        if (LbHelperImpl.this == lbHelper) goto _L2; else goto _L1
_L1:
                        return;
_L2:
                        updateSubchannelPicker(newPicker);
                        if (newState == ConnectivityState.SHUTDOWN) goto _L1; else goto _L3
_L3:
                        if (channelTracer != null)
                        {
                            ChannelTracer channeltracer = channelTracer;
                            Channelz.ChannelTrace.Event.Builder builder = new Channelz.ChannelTrace.Event.Builder();
                            Object obj1 = String.valueOf(newState);
                            builder.description = (new StringBuilder(String.valueOf(obj1).length() + 15)).append("Entering ").append(((String) (obj1))).append(" state").toString();
                            builder.severity = Channelz.ChannelTrace.Event.Severity.CT_INFO;
                            builder.timestampNanos = Long.valueOf(timeProvider.currentTimeNanos());
                            obj1 = builder.build();
                            synchronized (channeltracer.lock)
                            {
                                channeltracer.events.add(obj1);
                            }
                        }
                        channelStateManager.gotoState(newState);
                        return;
                        exception;
                        obj;
                        JVM INSTR monitorexit ;
                        throw exception;
                    }

                _cls3()
                {
                    this$1 = LbHelperImpl.this;
                    newPicker = subchannelpicker;
                    newState = connectivitystate;
                    super();
                }
                }

                newState = new _cls3();
                channelExecutor.executeLater(newState).drain();
                return;
            }
        }

        public final void updateSubchannelAddresses(io.grpc.LoadBalancer.Subchannel subchannel, List list)
        {
            InternalSubchannel internalsubchannel;
            boolean flag1;
            flag1 = true;
            if (!(subchannel instanceof SubchannelImpl))
            {
                throw new IllegalArgumentException(String.valueOf("subchannel must have been returned from createSubchannel"));
            }
            internalsubchannel = ((SubchannelImpl)subchannel).subchannel;
            if (list == null)
            {
                throw new NullPointerException(String.valueOf("newAddressGroups"));
            }
            InternalSubchannel.checkListHasNoNulls(list, "newAddressGroups contains null entry");
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
                throw new IllegalArgumentException(String.valueOf("newAddressGroups is empty"));
            }
            subchannel = Collections.unmodifiableList(new ArrayList(list));
            list = ((List) (internalsubchannel.lock));
            list;
            JVM INSTR monitorenter ;
            Object obj;
            obj = internalsubchannel.addressIndex;
            obj = (SocketAddress)((EquivalentAddressGroup)((InternalSubchannel.Index) (obj)).addressGroups.get(((InternalSubchannel.Index) (obj)).groupIndex)).addrs.get(((InternalSubchannel.Index) (obj)).addressIndex);
            InternalSubchannel.Index index2 = internalsubchannel.addressIndex;
            index2.addressGroups = subchannel;
            index2.groupIndex = 0;
            index2.addressIndex = 0;
            if (internalsubchannel.state.state != ConnectivityState.READY && internalsubchannel.state.state != ConnectivityState.CONNECTING)
            {
                break MISSING_BLOCK_LABEL_425;
            }
            subchannel = internalsubchannel.addressIndex;
            int i = 0;
_L7:
            if (i >= ((InternalSubchannel.Index) (subchannel)).addressGroups.size()) goto _L2; else goto _L1
_L1:
            int j = ((EquivalentAddressGroup)((InternalSubchannel.Index) (subchannel)).addressGroups.get(i)).addrs.indexOf(obj);
            if (j == -1) goto _L4; else goto _L3
_L3:
            subchannel.groupIndex = i;
            subchannel.addressIndex = j;
            i = ((flag1) ? 1 : 0);
_L8:
            if (i != 0)
            {
                break MISSING_BLOCK_LABEL_425;
            }
            if (internalsubchannel.state.state != ConnectivityState.READY) goto _L6; else goto _L5
_L5:
            subchannel = internalsubchannel.activeTransport;
            internalsubchannel.activeTransport = null;
            InternalSubchannel.Index index = internalsubchannel.addressIndex;
            index.groupIndex = 0;
            index.addressIndex = 0;
            internalsubchannel.gotoState(ConnectivityStateInfo.forNonError(ConnectivityState.IDLE));
_L9:
            list;
            JVM INSTR monitorexit ;
            internalsubchannel.channelExecutor.drain();
            if (subchannel != null)
            {
                subchannel.shutdown(Status.UNAVAILABLE.withDescription("InternalSubchannel closed transport due to address change"));
            }
            return;
_L4:
            i++;
              goto _L7
_L2:
            i = 0;
              goto _L8
_L6:
            subchannel = internalsubchannel.pendingTransport;
            internalsubchannel.pendingTransport = null;
            InternalSubchannel.Index index1 = internalsubchannel.addressIndex;
            index1.groupIndex = 0;
            index1.addressIndex = 0;
            internalsubchannel.startNewTransport();
              goto _L9
            subchannel;
            list;
            JVM INSTR monitorexit ;
            throw subchannel;
            subchannel;
            internalsubchannel.channelExecutor.drain();
            throw subchannel;
            subchannel = null;
              goto _L9
        }

        LbHelperImpl(NameResolver nameresolver)
        {
            this$0 = ManagedChannelImpl.this;
            super();
            if (nameresolver == null)
            {
                throw new NullPointerException(String.valueOf("NameResolver"));
            } else
            {
                nr = (NameResolver)nameresolver;
                return;
            }
        }
    }

    final class NameResolverListenerImpl
        implements io.grpc.NameResolver.Listener
    {

        public final LbHelperImpl helper;
        public final ManagedChannelImpl this$0;

        public final void onAddresses(final List servers, final Attributes config)
        {
            if (servers.isEmpty())
            {
                onError(Status.UNAVAILABLE.withDescription("NameResolver returned an empty list"));
                return;
            }
            if (ManagedChannelImpl.logger.isLoggable(Level.FINE))
            {
                ManagedChannelImpl.logger.logp(Level.FINE, "io.grpc.internal.ManagedChannelImpl$NameResolverListenerImpl", "onAddresses", "[{0}] resolved address: {1}, config={2}", new Object[] {
                    logId, servers, config
                });
            }
            if (channelTracer != null)
            {
                ChannelTracer channeltracer = channelTracer;
                Channelz.ChannelTrace.Event.Builder builder = new Channelz.ChannelTrace.Event.Builder();
                Object obj1 = String.valueOf(servers);
                builder.description = (new StringBuilder(String.valueOf(obj1).length() + 18)).append("Address resolved: ").append(((String) (obj1))).toString();
                builder.severity = Channelz.ChannelTrace.Event.Severity.CT_INFO;
                builder.timestampNanos = Long.valueOf(timeProvider.currentTimeNanos());
                obj1 = builder.build();
                synchronized (channeltracer.lock)
                {
                    channeltracer.events.add(obj1);
                }
            }
            LbHelperImpl lbhelperimpl = helper;
            class _cls1NamesResolved
                implements Runnable
            {

                private final NameResolverListenerImpl this$1;
                private final Attributes val$config;
                private final List val$servers;

                public final void run()
                {
                    Object obj5;
                    if (helper != lbHelper)
                    {
                        return;
                    }
                    nameResolverBackoffPolicy = null;
                    Attributes attributes = config;
                    io.grpc.Attributes.Key key = GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG;
                    obj5 = (Map)attributes.data.get(key);
                    if (obj5 == null) goto _L2; else goto _L1
_L1:
                    Object obj2;
                    Object obj3;
                    Object obj4;
                    obj2 = serviceConfigInterceptor;
                    obj3 = new HashMap();
                    obj4 = new HashMap();
                    obj5 = ServiceConfigUtil.getMethodConfigFromServiceConfig(((Map) (obj5)));
                    if (obj5 != null) goto _L4; else goto _L3
_L3:
                    ServiceConfigInterceptor.logger.logp(Level.FINE, "io.grpc.internal.ServiceConfigInterceptor", "handleUpdate", "No method configs found, skipping");
                    obj2.nameResolveComplete = true;
_L9:
                    if (retryEnabled)
                    {
                        throttle = ManagedChannelImpl.getThrottle(config);
                    }
_L2:
                    helper.lb.handleResolvedAddressGroups(servers, config);
                    return;
_L4:
                    obj5 = ((List) (obj5)).iterator();
_L7:
                    ServiceConfigInterceptor.MethodInfo methodinfo;
                    Object obj6;
                    Object obj7;
                    if (!((Iterator) (obj5)).hasNext())
                    {
                        break MISSING_BLOCK_LABEL_554;
                    }
                    obj6 = (Map)((Iterator) (obj5)).next();
                    methodinfo = new ServiceConfigInterceptor.MethodInfo(((Map) (obj6)), ((ServiceConfigInterceptor) (obj2)).retryEnabled, ((ServiceConfigInterceptor) (obj2)).maxRetryAttemptsLimit);
                    obj7 = ServiceConfigUtil.getNameListFromMethodConfig(((Map) (obj6)));
                    if (obj7 == null)
                    {
                        break MISSING_BLOCK_LABEL_347;
                    }
                    if (((List) (obj7)).isEmpty())
                    {
                        break MISSING_BLOCK_LABEL_347;
                    }
                    boolean flag = true;
_L5:
                    if (flag)
                    {
                        break MISSING_BLOCK_LABEL_353;
                    }
                    try
                    {
                        throw new IllegalArgumentException(Strings.lenientFormat("no names in method config %s", new Object[] {
                            obj6
                        }));
                    }
                    // Misplaced declaration of an exception variable
                    catch (Object obj2)
                    {
                        obj3 = ManagedChannelImpl.logger;
                    }
                    obj4 = Level.WARNING;
                    String s = String.valueOf(logId);
                    ((Logger) (obj3)).logp(((Level) (obj4)), "io.grpc.internal.ManagedChannelImpl$NameResolverListenerImpl$1NamesResolved", "run", (new StringBuilder(String.valueOf(s).length() + 51)).append("[").append(s).append("] Unexpected exception from parsing service config").toString(), ((Throwable) (obj2)));
                      goto _L2
                    flag = false;
                      goto _L5
                    obj6 = ((List) (obj7)).iterator();
_L8:
                    if (!((Iterator) (obj6)).hasNext()) goto _L7; else goto _L6
_L6:
                    Object obj8;
                    obj8 = (Map)((Iterator) (obj6)).next();
                    obj7 = ServiceConfigUtil.getServiceFromName(((Map) (obj8)));
                    if (!Platform.stringIsNullOrEmpty(((String) (obj7))))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        break MISSING_BLOCK_LABEL_420;
                    }
                    throw new IllegalArgumentException(String.valueOf("missing service name"));
                    obj8 = ServiceConfigUtil.getMethodFromName(((Map) (obj8)));
                    if (!Platform.stringIsNullOrEmpty(((String) (obj8))))
                    {
                        break MISSING_BLOCK_LABEL_490;
                    }
                    if (!((Map) (obj4)).containsKey(obj7))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        break MISSING_BLOCK_LABEL_476;
                    }
                    throw new IllegalArgumentException(Strings.lenientFormat("Duplicate service %s", new Object[] {
                        obj7
                    }));
                    ((Map) (obj4)).put(obj7, methodinfo);
                      goto _L8
                    obj7 = MethodDescriptor.generateFullMethodName(((String) (obj7)), ((String) (obj8)));
                    if (!((Map) (obj3)).containsKey(obj7))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        break MISSING_BLOCK_LABEL_540;
                    }
                    throw new IllegalArgumentException(Strings.lenientFormat("Duplicate method name %s", new Object[] {
                        obj7
                    }));
                    ((Map) (obj3)).put(obj7, methodinfo);
                      goto _L8
                      goto _L7
                    ((ServiceConfigInterceptor) (obj2)).serviceMethodMap.set(Collections.unmodifiableMap(((Map) (obj3))));
                    ((ServiceConfigInterceptor) (obj2)).serviceMap.set(Collections.unmodifiableMap(((Map) (obj4))));
                    obj2.nameResolveComplete = true;
                      goto _L9
                }

                _cls1NamesResolved()
                {
                    this$1 = NameResolverListenerImpl.this;
                    config = attributes;
                    servers = list;
                    super();
                }
            }

            servers = new _cls1NamesResolved();
            lbhelperimpl._fld0.channelExecutor.executeLater(servers).drain();
            return;
            servers;
            obj;
            JVM INSTR monitorexit ;
            throw servers;
        }

        public final void onError(final Status error)
        {
            boolean flag;
            if (io.grpc.Status.Code.OK == error.code)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("the error status must not be OK"));
            }
            ManagedChannelImpl.logger.logp(Level.WARNING, "io.grpc.internal.ManagedChannelImpl$NameResolverListenerImpl", "onError", "[{0}] Failed to resolve name. status={1}", new Object[] {
                logId, error
            });
            if (channelTracer != null)
            {
                ChannelTracer channeltracer = channelTracer;
                Channelz.ChannelTrace.Event.Builder builder = new Channelz.ChannelTrace.Event.Builder();
                builder.description = "Failed to resolve name";
                builder.severity = Channelz.ChannelTrace.Event.Severity.CT_WARNING;
                builder.timestampNanos = Long.valueOf(timeProvider.currentTimeNanos());
                Channelz.ChannelTrace.Event event = builder.build();
                synchronized (channeltracer.lock)
                {
                    channeltracer.events.add(event);
                }
            }
            class _cls1
                implements Runnable
            {

                private final NameResolverListenerImpl this$1;
                private final Status val$error;

                public final void run()
                {
                    if (helper == lbHelper)
                    {
                        helper.lb.handleNameResolutionError(error);
                        if (nameResolverRefreshFuture == null)
                        {
                            if (nameResolverBackoffPolicy == null)
                            {
                                nameResolverBackoffPolicy = backoffPolicyProvider.get();
                            }
                            long l = nameResolverBackoffPolicy.nextBackoffNanos();
                            if (ManagedChannelImpl.logger.isLoggable(Level.FINE))
                            {
                                ManagedChannelImpl.logger.log(Level.FINE, "[{0}] Scheduling DNS resolution backoff for {1} ns", new Object[] {
                                    logId, Long.valueOf(l)
                                });
                            }
                            nameResolverRefresh = new NameResolverRefresh();
                            nameResolverRefreshFuture = transportFactory.getScheduledExecutorService().schedule(nameResolverRefresh, l, TimeUnit.NANOSECONDS);
                            return;
                        }
                    }
                }

                _cls1()
                {
                    this$1 = NameResolverListenerImpl.this;
                    error = status;
                    super();
                }
            }

            channelExecutor.executeLater(new _cls1()).drain();
            return;
            error;
            obj;
            JVM INSTR monitorexit ;
            throw error;
        }

        NameResolverListenerImpl(LbHelperImpl lbhelperimpl)
        {
            this$0 = ManagedChannelImpl.this;
            super();
            helper = lbhelperimpl;
        }
    }

    final class NameResolverRefresh
        implements Runnable
    {

        public boolean cancelled;
        private final ManagedChannelImpl this$0;

        public final void run()
        {
            if (!cancelled)
            {
                nameResolverRefreshFuture = null;
                nameResolverRefresh = null;
                if (nameResolver != null)
                {
                    nameResolver.refresh();
                    return;
                }
            }
        }

        NameResolverRefresh()
        {
            this$0 = ManagedChannelImpl.this;
            super();
        }
    }

    final class RealChannel extends Channel
    {

        private final ManagedChannelImpl this$0;

        public final String authority()
        {
            String s = nameResolver.getServiceAuthority();
            if (s == null)
            {
                throw new NullPointerException(String.valueOf("authority"));
            } else
            {
                return (String)s;
            }
        }

        public final ClientCall newCall(MethodDescriptor methoddescriptor, CallOptions calloptions)
        {
            Object obj1 = ManagedChannelImpl.this;
            Object obj = calloptions.executor;
            Executor executor1 = ((Executor) (obj));
            if (obj == null)
            {
                executor1 = ((ManagedChannelImpl) (obj1)).executor;
            }
            obj1 = transportProvider;
            if (terminated)
            {
                obj = null;
            } else
            {
                obj = transportFactory.getScheduledExecutorService();
            }
            methoddescriptor = new ClientCallImpl(methoddescriptor, executor1, calloptions, ((ClientCallImpl.ClientTransportProvider) (obj1)), ((ScheduledExecutorService) (obj)), channelCallTracer, retryEnabled);
            methoddescriptor.fullStreamDecompression = fullStreamDecompression;
            methoddescriptor.decompressorRegistry = decompressorRegistry;
            methoddescriptor.compressorRegistry = compressorRegistry;
            return methoddescriptor;
        }

        RealChannel()
        {
            this$0 = ManagedChannelImpl.this;
            super();
        }
    }

    final class SubchannelImpl extends AbstractSubchannel
    {

        private ScheduledFuture delayedShutdownTask;
        private final Object shutdownLock = new Object();
        private boolean shutdownRequested;
        public InternalSubchannel subchannel;
        private final ManagedChannelImpl this$0;

        final ClientTransport obtainActiveTransport()
        {
            return subchannel.obtainActiveTransport();
        }

        public final void requestConnection()
        {
            subchannel.obtainActiveTransport();
        }

        public final void shutdown()
        {
            Object obj = shutdownLock;
            obj;
            JVM INSTR monitorenter ;
            if (!shutdownRequested)
            {
                break MISSING_BLOCK_LABEL_110;
            }
            if (!terminating || delayedShutdownTask == null) goto _L2; else goto _L1
_L1:
            delayedShutdownTask.cancel(false);
            delayedShutdownTask = null;
_L3:
            if (terminating)
            {
                break MISSING_BLOCK_LABEL_118;
            }
            class _cls1
                implements Runnable
            {

                private final SubchannelImpl this$1;

                public final void run()
                {
                    subchannel.shutdown(ManagedChannelImpl.SUBCHANNEL_SHUTDOWN_STATUS);
                }

                _cls1()
                {
                    this$1 = SubchannelImpl.this;
                    super();
                }
            }

            delayedShutdownTask = transportFactory.getScheduledExecutorService().schedule(new LogExceptionRunnable(new _cls1()), 5L, TimeUnit.SECONDS);
            return;
_L2:
            return;
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            shutdownRequested = true;
              goto _L3
            obj;
            JVM INSTR monitorexit ;
            subchannel.shutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
            return;
        }

        public final String toString()
        {
            return subchannel.logId.toString();
        }

        SubchannelImpl(Attributes attributes)
        {
            this$0 = ManagedChannelImpl.this;
            super();
            if (attributes == null)
            {
                throw new NullPointerException(String.valueOf("attrs"));
            } else
            {
                return;
            }
        }
    }

    final class UncommittedRetriableStreamsRegistry
    {

        public final Object lock = new Object();
        public Status shutdownStatus;
        public final ManagedChannelImpl this$0;
        public Collection uncommittedRetriableStreams;

        final Status add(RetriableStream retriablestream)
        {
label0:
            {
                synchronized (lock)
                {
                    if (shutdownStatus == null)
                    {
                        break label0;
                    }
                    retriablestream = shutdownStatus;
                }
                return retriablestream;
            }
            uncommittedRetriableStreams.add(retriablestream);
            obj;
            JVM INSTR monitorexit ;
            return null;
            retriablestream;
            obj;
            JVM INSTR monitorexit ;
            throw retriablestream;
        }

        final void onShutdown(Status status)
        {
            boolean flag;
label0:
            {
                flag = false;
                synchronized (lock)
                {
                    if (shutdownStatus == null)
                    {
                        break label0;
                    }
                }
                return;
            }
            shutdownStatus = status;
            if (uncommittedRetriableStreams.isEmpty())
            {
                flag = true;
            }
            obj;
            JVM INSTR monitorexit ;
            if (flag)
            {
                delayedTransport.shutdown(status);
                return;
            } else
            {
                return;
            }
            status;
            obj;
            JVM INSTR monitorexit ;
            throw status;
        }

        UncommittedRetriableStreamsRegistry()
        {
            this$0 = ManagedChannelImpl.this;
            super();
            uncommittedRetriableStreams = new HashSet();
        }
    }


    private static final Status SHUTDOWN_NOW_STATUS;
    public static final Status SHUTDOWN_STATUS;
    public static final Status SUBCHANNEL_SHUTDOWN_STATUS;
    private static final Pattern URI_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+.-]*:/.*");
    public static final Logger logger = Logger.getLogger(io/grpc/internal/ManagedChannelImpl.getName());
    public final BackoffPolicy.Provider backoffPolicyProvider;
    public final CallTracer.Factory callTracerFactory;
    public final long channelBufferLimit;
    public final RetriableStream.ChannelBufferMeter channelBufferUsed = new RetriableStream.ChannelBufferMeter();
    public final CallTracer channelCallTracer;
    public final ChannelExecutor channelExecutor = new _cls1();
    public final ConnectivityStateManager channelStateManager = new ConnectivityStateManager();
    public final ChannelTracer channelTracer;
    public final Channelz channelz;
    public final CompressorRegistry compressorRegistry;
    public final DecompressorRegistry decompressorRegistry;
    public final DelayedClientTransport delayedTransport;
    private final ManagedClientTransport.Listener delayedTransportListener = new _cls2();
    public final Executor executor;
    public final ObjectPool executorPool;
    public boolean fullStreamDecompression;
    private final long idleTimeoutMillis;
    private final Rescheduler idleTimer;
    public final InUseStateAggregator inUseStateAggregator = new _cls3();
    private final Channel interceptorChannel;
    public LbHelperImpl lbHelper;
    private final io.grpc.LoadBalancer.Factory loadBalancerFactory = new AutoConfiguredLoadBalancerFactory();
    public final LogId logId;
    public final int maxTraceEvents;
    public NameResolver nameResolver;
    public BackoffPolicy nameResolverBackoffPolicy;
    private final io.grpc.NameResolver.Factory nameResolverFactory;
    private final Attributes nameResolverParams;
    public NameResolverRefresh nameResolverRefresh;
    public ScheduledFuture nameResolverRefreshFuture;
    public final Set oobChannels = new HashSet(1, 0.75F);
    public boolean panicMode;
    public final long perRpcBufferLimit;
    public final boolean retryEnabled = false;
    public final ServiceConfigInterceptor serviceConfigInterceptor;
    public final AtomicBoolean shutdown = new AtomicBoolean(false);
    public boolean shutdownNowed;
    public final Supplier stopwatchSupplier;
    public volatile io.grpc.LoadBalancer.SubchannelPicker subchannelPicker;
    public final Set subchannels = new HashSet(16, 0.75F);
    private final String target;
    public volatile boolean terminated;
    public final CountDownLatch terminatedLatch = new CountDownLatch(1);
    public volatile boolean terminating;
    public RetriableStream.Throttle throttle;
    public final TimeProvider timeProvider;
    public final ClientTransportFactory transportFactory;
    public final ClientCallImpl.ClientTransportProvider transportProvider = new _cls5();
    public final UncommittedRetriableStreamsRegistry uncommittedRetriableStreamsRegistry = new UncommittedRetriableStreamsRegistry();
    public final String userAgent;

    ManagedChannelImpl(AbstractManagedChannelImplBuilder abstractmanagedchannelimplbuilder, ClientTransportFactory clienttransportfactory, BackoffPolicy.Provider provider, ObjectPool objectpool, Supplier supplier, List list, final TimeProvider timeProvider)
    {
        logId = new LogId(getClass().getName(), LogId.idAlloc.incrementAndGet());
        Object obj = abstractmanagedchannelimplbuilder.target;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("target"));
        }
        target = (String)obj;
        nameResolverFactory = abstractmanagedchannelimplbuilder.nameResolverFactory;
        obj = abstractmanagedchannelimplbuilder.getNameResolverParams();
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("nameResolverParams"));
        }
        nameResolverParams = (Attributes)obj;
        nameResolver = getNameResolver(target, nameResolverFactory, nameResolverParams);
        obj = abstractmanagedchannelimplbuilder.loadBalancerFactory;
        obj = abstractmanagedchannelimplbuilder.executorPool;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("executorPool"));
        }
        executorPool = (ObjectPool)obj;
        if (objectpool == null)
        {
            throw new NullPointerException(String.valueOf("oobExecutorPool"));
        }
        objectpool = (Executor)executorPool.getObject();
        if (objectpool == null)
        {
            throw new NullPointerException(String.valueOf("executor"));
        }
        executor = (Executor)objectpool;
        delayedTransport = new DelayedClientTransport(executor, channelExecutor);
        delayedTransport.start(delayedTransportListener);
        backoffPolicyProvider = provider;
        transportFactory = new CallCredentialsApplyingTransportFactory(clienttransportfactory, executor);
        boolean flag1 = abstractmanagedchannelimplbuilder.retryEnabled;
        serviceConfigInterceptor = new ServiceConfigInterceptor(retryEnabled, abstractmanagedchannelimplbuilder.maxRetryAttempts);
        clienttransportfactory = ClientInterceptors.intercept(new RealChannel(), new ClientInterceptor[] {
            serviceConfigInterceptor
        });
        provider = abstractmanagedchannelimplbuilder.binlog;
        interceptorChannel = ClientInterceptors.intercept(clienttransportfactory, list);
        if (supplier == null)
        {
            throw new NullPointerException(String.valueOf("stopwatchSupplier"));
        }
        stopwatchSupplier = (Supplier)supplier;
        if (abstractmanagedchannelimplbuilder.idleTimeoutMillis != -1L)
        {
            boolean flag;
            long l;
            if (abstractmanagedchannelimplbuilder.idleTimeoutMillis >= AbstractManagedChannelImplBuilder.IDLE_MODE_MIN_TIMEOUT_MILLIS)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            l = abstractmanagedchannelimplbuilder.idleTimeoutMillis;
            if (!flag)
            {
                throw new IllegalArgumentException(Strings.lenientFormat("invalid idleTimeoutMillis %s", new Object[] {
                    Long.valueOf(l)
                }));
            }
        }
        idleTimeoutMillis = abstractmanagedchannelimplbuilder.idleTimeoutMillis;
        idleTimer = new Rescheduler(new IdleModeTimer(), new _cls1AutoDrainChannelExecutor(), transportFactory.getScheduledExecutorService(), (Stopwatch)supplier.get());
        fullStreamDecompression = abstractmanagedchannelimplbuilder.fullStreamDecompression;
        clienttransportfactory = abstractmanagedchannelimplbuilder.decompressorRegistry;
        if (clienttransportfactory == null)
        {
            throw new NullPointerException(String.valueOf("decompressorRegistry"));
        }
        decompressorRegistry = (DecompressorRegistry)clienttransportfactory;
        clienttransportfactory = abstractmanagedchannelimplbuilder.compressorRegistry;
        if (clienttransportfactory == null)
        {
            throw new NullPointerException(String.valueOf("compressorRegistry"));
        }
        compressorRegistry = (CompressorRegistry)clienttransportfactory;
        userAgent = abstractmanagedchannelimplbuilder.userAgent;
        channelBufferLimit = abstractmanagedchannelimplbuilder.retryBufferSize;
        perRpcBufferLimit = abstractmanagedchannelimplbuilder.perRpcBufferLimit;
        if (timeProvider == null)
        {
            throw new NullPointerException(String.valueOf("timeProvider"));
        }
        this.timeProvider = (TimeProvider)timeProvider;
        callTracerFactory = new _cls6();
        channelCallTracer = callTracerFactory.create();
        clienttransportfactory = abstractmanagedchannelimplbuilder.channelz;
        if (clienttransportfactory == null)
        {
            throw new NullPointerException();
        }
        channelz = (Channelz)clienttransportfactory;
        channelz.rootChannels.put(Long.valueOf(getLogId().id), this);
        maxTraceEvents = abstractmanagedchannelimplbuilder.maxTraceEvents;
        if (maxTraceEvents > 0)
        {
            long l1 = timeProvider.currentTimeNanos();
            channelTracer = new ChannelTracer(abstractmanagedchannelimplbuilder.maxTraceEvents, l1, "Channel");
        } else
        {
            channelTracer = null;
        }
        logger.logp(Level.FINE, "io.grpc.internal.ManagedChannelImpl", "<init>", "[{0}] Created with target {1}", new Object[] {
            logId, target
        });
    }

    private static NameResolver getNameResolver(String s, io.grpc.NameResolver.Factory factory, Attributes attributes)
    {
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder();
        Object obj;
        try
        {
            obj = new URI(s);
        }
        catch (URISyntaxException urisyntaxexception)
        {
            stringbuilder.append(urisyntaxexception.getMessage());
            urisyntaxexception = null;
        }
        if (obj == null) goto _L2; else goto _L1
_L1:
        obj = factory.newNameResolver(((URI) (obj)), attributes);
        if (obj == null) goto _L2; else goto _L3
_L3:
        factory = ((io.grpc.NameResolver.Factory) (obj));
_L5:
        return factory;
_L2:
        if (URI_PATTERN.matcher(s).matches())
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj1;
        String s1;
        s1 = factory.getDefaultScheme();
        obj1 = String.valueOf(s);
        if (((String) (obj1)).length() == 0)
        {
            break MISSING_BLOCK_LABEL_196;
        }
        obj1 = "/".concat(((String) (obj1)));
_L6:
        obj1 = new URI(s1, "", ((String) (obj1)), null);
        attributes = factory.newNameResolver(((URI) (obj1)), attributes);
        factory = attributes;
        if (attributes != null) goto _L5; else goto _L4
_L4:
        if (stringbuilder.length() > 0)
        {
            factory = String.valueOf(stringbuilder);
            factory = (new StringBuilder(String.valueOf(factory).length() + 3)).append(" (").append(factory).append(")").toString();
        } else
        {
            factory = "";
        }
        throw new IllegalArgumentException(String.format("cannot find a NameResolver for %s%s", new Object[] {
            s, factory
        }));
        try
        {
            obj1 = new String("/");
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new IllegalArgumentException(s);
        }
          goto _L6
    }

    static RetriableStream.Throttle getThrottle(Attributes attributes)
    {
        io.grpc.Attributes.Key key = GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG;
        return ServiceConfigUtil.getThrottlePolicy((Map)attributes.data.get(key));
    }

    private final ManagedChannelImpl shutdown()
    {
        logger.logp(Level.FINE, "io.grpc.internal.ManagedChannelImpl", "shutdown", "[{0}] shutdown() called", logId);
        if (!shutdown.compareAndSet(false, true))
        {
            return this;
        } else
        {
            channelExecutor.executeLater(new _cls7());
            uncommittedRetriableStreamsRegistry.onShutdown(SHUTDOWN_STATUS);
            channelExecutor.executeLater(new _cls8()).drain();
            logger.logp(Level.FINE, "io.grpc.internal.ManagedChannelImpl", "shutdown", "[{0}] Shutting down", logId);
            return this;
        }
    }

    public final String authority()
    {
        return interceptorChannel.authority();
    }

    final void cancelIdleTimer(boolean flag)
    {
        Rescheduler rescheduler = idleTimer;
        rescheduler.enabled = false;
        if (flag && rescheduler.wakeUp != null)
        {
            rescheduler.wakeUp.cancel(false);
            rescheduler.wakeUp = null;
        }
    }

    final void enterIdleMode()
    {
        logger.logp(Level.FINE, "io.grpc.internal.ManagedChannelImpl", "enterIdleMode", "[{0}] Entering idle mode", logId);
        shutdownNameResolverAndLoadBalancer(true);
        delayedTransport.reprocess(null);
        nameResolver = getNameResolver(target, nameResolverFactory, nameResolverParams);
        if (channelTracer != null)
        {
            ChannelTracer channeltracer = channelTracer;
            Channelz.ChannelTrace.Event.Builder builder = new Channelz.ChannelTrace.Event.Builder();
            builder.description = "Entering IDLE state";
            builder.severity = Channelz.ChannelTrace.Event.Severity.CT_INFO;
            builder.timestampNanos = Long.valueOf(timeProvider.currentTimeNanos());
            Channelz.ChannelTrace.Event event = builder.build();
            synchronized (channeltracer.lock)
            {
                channeltracer.events.add(event);
            }
        }
        channelStateManager.gotoState(ConnectivityState.IDLE);
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final void exitIdleMode()
    {
        if (!shutdown.get() && !panicMode)
        {
            boolean flag;
            if (!inUseStateAggregator.inUseObjects.isEmpty())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                idleTimer.enabled = false;
            } else
            if (idleTimeoutMillis != -1L)
            {
                Rescheduler rescheduler = idleTimer;
                long l = idleTimeoutMillis;
                l = TimeUnit.MILLISECONDS.toNanos(l);
                long l1 = rescheduler.stopwatch.elapsed(TimeUnit.NANOSECONDS) + l;
                rescheduler.enabled = true;
                if (l1 - rescheduler.runAtNanos < 0L || rescheduler.wakeUp == null)
                {
                    if (rescheduler.wakeUp != null)
                    {
                        rescheduler.wakeUp.cancel(false);
                    }
                    rescheduler.wakeUp = rescheduler.scheduler.schedule(new Rescheduler.FutureRunnable(rescheduler), l, TimeUnit.NANOSECONDS);
                }
                rescheduler.runAtNanos = l1;
            }
            if (lbHelper == null)
            {
                logger.logp(Level.FINE, "io.grpc.internal.ManagedChannelImpl", "exitIdleMode", "[{0}] Exiting idle mode", logId);
                lbHelper = new LbHelperImpl(nameResolver);
                lbHelper.lb = loadBalancerFactory.newLoadBalancer(lbHelper);
                NameResolverListenerImpl nameresolverlistenerimpl = new NameResolverListenerImpl(lbHelper);
                try
                {
                    nameResolver.start(nameresolverlistenerimpl);
                    return;
                }
                catch (Throwable throwable)
                {
                    nameresolverlistenerimpl.onError(Status.fromThrowable(throwable));
                }
                return;
            }
        }
    }

    public final LogId getLogId()
    {
        return logId;
    }

    public final boolean isTerminated()
    {
        return terminated;
    }

    final void maybeShutdownNowSubchannels()
    {
        if (shutdownNowed)
        {
            for (Iterator iterator = subchannels.iterator(); iterator.hasNext(); ((InternalSubchannel)iterator.next()).shutdownNow(SHUTDOWN_NOW_STATUS)) { }
            for (Iterator iterator1 = oobChannels.iterator(); iterator1.hasNext(); OobChannel.getInternalSubchannel().shutdownNow(SHUTDOWN_NOW_STATUS))
            {
                iterator1.next();
            }

        }
    }

    public final ClientCall newCall(MethodDescriptor methoddescriptor, CallOptions calloptions)
    {
        return interceptorChannel.newCall(methoddescriptor, calloptions);
    }

    final void rescheduleIdleTimer()
    {
        if (idleTimeoutMillis == -1L)
        {
            return;
        }
        Rescheduler rescheduler = idleTimer;
        long l = idleTimeoutMillis;
        l = TimeUnit.MILLISECONDS.toNanos(l);
        long l1 = rescheduler.stopwatch.elapsed(TimeUnit.NANOSECONDS) + l;
        rescheduler.enabled = true;
        if (l1 - rescheduler.runAtNanos < 0L || rescheduler.wakeUp == null)
        {
            if (rescheduler.wakeUp != null)
            {
                rescheduler.wakeUp.cancel(false);
            }
            rescheduler.wakeUp = rescheduler.scheduler.schedule(new Rescheduler.FutureRunnable(rescheduler), l, TimeUnit.NANOSECONDS);
        }
        rescheduler.runAtNanos = l1;
    }

    public final volatile ManagedChannel shutdown()
    {
        return shutdown();
    }

    final void shutdownNameResolverAndLoadBalancer(boolean flag)
    {
        if (flag)
        {
            boolean flag1;
            if (nameResolver != null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                throw new IllegalStateException(String.valueOf("nameResolver is null"));
            }
            if (lbHelper != null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                throw new IllegalStateException(String.valueOf("lbHelper is null"));
            }
        }
        if (nameResolver != null)
        {
            if (nameResolverRefreshFuture != null)
            {
                nameResolverRefreshFuture.cancel(false);
                nameResolverRefresh.cancelled = true;
                nameResolverRefreshFuture = null;
                nameResolverRefresh = null;
                nameResolverBackoffPolicy = null;
            }
            nameResolver.shutdown();
            nameResolver = null;
        }
        if (lbHelper != null)
        {
            lbHelper.lb.shutdown();
            lbHelper = null;
        }
        subchannelPicker = null;
    }

    public final String toString()
    {
        return (new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName())).add("logId", logId.id).add("target", target).toString();
    }

    final void updateSubchannelPicker(io.grpc.LoadBalancer.SubchannelPicker subchannelpicker)
    {
        subchannelPicker = subchannelpicker;
        delayedTransport.reprocess(subchannelpicker);
    }

    static 
    {
        SHUTDOWN_NOW_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdownNow invoked");
        SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdown invoked");
        SUBCHANNEL_SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Subchannel shutdown invoked");
    }

    private class _cls1 extends ChannelExecutor
    {

        private final ManagedChannelImpl this$0;

        final void handleUncaughtThrowable(final Throwable t)
        {
            super.handleUncaughtThrowable(t);
            ManagedChannelImpl managedchannelimpl = ManagedChannelImpl.this;
            if (!managedchannelimpl.panicMode)
            {
                managedchannelimpl.panicMode = true;
                managedchannelimpl.cancelIdleTimer(true);
                managedchannelimpl.shutdownNameResolverAndLoadBalancer(false);
                t = new _cls10();
                managedchannelimpl.subchannelPicker = t;
                managedchannelimpl.delayedTransport.reprocess(t);
                if (managedchannelimpl.channelTracer != null)
                {
                    t = managedchannelimpl.channelTracer;
                    Channelz.ChannelTrace.Event.Builder builder = new Channelz.ChannelTrace.Event.Builder();
                    builder.description = "Entering TRANSIENT_FAILURE state";
                    builder.severity = Channelz.ChannelTrace.Event.Severity.CT_INFO;
                    builder.timestampNanos = Long.valueOf(managedchannelimpl.timeProvider.currentTimeNanos());
                    Channelz.ChannelTrace.Event event = builder.build();
                    synchronized (((ChannelTracer) (t)).lock)
                    {
                        ((ChannelTracer) (t)).events.add(event);
                    }
                }
                managedchannelimpl.channelStateManager.gotoState(ConnectivityState.TRANSIENT_FAILURE);
            }
            return;
            t;
            obj;
            JVM INSTR monitorexit ;
            throw t;
        }

        _cls1()
        {
            this$0 = ManagedChannelImpl.this;
            super();
        }

        private class _cls10 extends io.grpc.LoadBalancer.SubchannelPicker
        {

            private final io.grpc.LoadBalancer.PickResult panicPickResult;
            private final Throwable val$t;

            public final io.grpc.LoadBalancer.PickResult pickSubchannel$5166IRPFCTP70OPF9HNM2P22C5M62RJ3CLP28K39CDLL6TB2CDK62RJECLM42SJ7ECTIIJ39DSNMESJGCCNKORR1CH162R31DPHMASH4A1KM6QQICLPNAR3K7C______0()
            {
                return panicPickResult;
            }

            _cls10()
            {
                t = throwable;
                super();
                panicPickResult = io.grpc.LoadBalancer.PickResult.withDrop(Status.INTERNAL.withDescription("Panic! This is a bug!").withCause(t));
            }
        }

    }


    private class _cls2
        implements ManagedClientTransport.Listener
    {

        private final ManagedChannelImpl this$0;

        public final void transportInUse(boolean flag)
        {
            inUseStateAggregator.updateObjectInUse(delayedTransport, flag);
        }

        public final void transportReady()
        {
        }

        public final void transportShutdown(Status status)
        {
            if (!shutdown.get())
            {
                throw new IllegalStateException(String.valueOf("Channel must have been shut down"));
            } else
            {
                return;
            }
        }

        public final void transportTerminated()
        {
            if (!shutdown.get())
            {
                throw new IllegalStateException(String.valueOf("Channel must have been shut down"));
            }
            terminating = true;
            shutdownNameResolverAndLoadBalancer(false);
            maybeShutdownNowSubchannels();
            ManagedChannelImpl managedchannelimpl = ManagedChannelImpl.this;
            if (!managedchannelimpl.terminated && managedchannelimpl.shutdown.get() && managedchannelimpl.subchannels.isEmpty() && managedchannelimpl.oobChannels.isEmpty())
            {
                ManagedChannelImpl.logger.logp(Level.FINE, "io.grpc.internal.ManagedChannelImpl", "maybeTerminateChannel", "[{0}] Terminated", managedchannelimpl.logId);
                managedchannelimpl.channelz.rootChannels.remove(Long.valueOf(managedchannelimpl.getLogId().id));
                managedchannelimpl.terminated = true;
                managedchannelimpl.terminatedLatch.countDown();
                managedchannelimpl.executorPool.returnObject(managedchannelimpl.executor);
                managedchannelimpl.transportFactory.close();
            }
        }

        _cls2()
        {
            this$0 = ManagedChannelImpl.this;
            super();
        }
    }


    private class _cls3 extends InUseStateAggregator
    {

        private final ManagedChannelImpl this$0;

        final void handleInUse()
        {
            exitIdleMode();
        }

        final void handleNotInUse()
        {
            if (shutdown.get())
            {
                return;
            } else
            {
                rescheduleIdleTimer();
                return;
            }
        }

        _cls3()
        {
            this$0 = ManagedChannelImpl.this;
            super();
        }
    }


    private class _cls5
        implements ClientCallImpl.ClientTransportProvider
    {

        public final ManagedChannelImpl this$0;

        public final ClientTransport get(io.grpc.LoadBalancer.PickSubchannelArgs picksubchannelargs)
        {
            Object obj = subchannelPicker;
            if (shutdown.get())
            {
                picksubchannelargs = delayedTransport;
            } else
            {
                if (obj == null)
                {
                    class _cls1
                        implements Runnable
                    {

                        private final _cls5 this$1;

                        public final void run()
                        {
                            exitIdleMode();
                        }

                _cls1()
                {
                    this$1 = _cls5.this;
                    super();
                }
                    }

                    channelExecutor.executeLater(new _cls1()).drain();
                    return delayedTransport;
                }
                obj = GrpcUtil.getTransportFromPickResult(((io.grpc.LoadBalancer.SubchannelPicker) (obj)).pickSubchannel$5166IRPFCTP70OPF9HNM2P22C5M62RJ3CLP28K39CDLL6TB2CDK62RJECLM42SJ7ECTIIJ39DSNMESJGCCNKORR1CH162R31DPHMASH4A1KM6QQICLPNAR3K7C______0(), picksubchannelargs.getCallOptions().waitForReady);
                picksubchannelargs = ((io.grpc.LoadBalancer.PickSubchannelArgs) (obj));
                if (obj == null)
                {
                    return delayedTransport;
                }
            }
            return picksubchannelargs;
        }

        public final RetriableStream newRetriableStream(final MethodDescriptor final_methoddescriptor, CallOptions calloptions, final Metadata final_metadata, Context context)
        {
            Executor executor1;
            Object obj;
            final RetriableStream.ChannelBufferMeter final_channelbuffermeter;
            Object obj1;
            int i;
            long l;
            long l1;
            if (!retryEnabled)
            {
                throw new IllegalStateException(String.valueOf("retry should be enabled"));
            }
            final_channelbuffermeter = channelBufferUsed;
            l = perRpcBufferLimit;
            l1 = channelBufferLimit;
            obj1 = ManagedChannelImpl.this;
            obj = calloptions.executor;
            executor1 = ((Executor) (obj));
            if (obj == null)
            {
                executor1 = ((ManagedChannelImpl) (obj1)).executor;
            }
            obj1 = transportFactory.getScheduledExecutorService();
            obj = ServiceConfigInterceptor.RETRY_POLICY_KEY;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("key"));
            }
            i = 0;
_L3:
            if (i >= calloptions.customOptions.length)
            {
                break MISSING_BLOCK_LABEL_202;
            }
            if (!obj.equals(calloptions.customOptions[i][0])) goto _L2; else goto _L1
_L1:
            obj = calloptions.customOptions[i][1];
_L4:
            class _cls2 extends RetriableStream
            {

                private final _cls5 this$1;
                private final CallOptions val$callOptions;
                private final Context val$context;
                private final MethodDescriptor val$method;

                final ClientStream newSubstream(io.grpc.ClientStreamTracer.Factory factory, Metadata metadata)
                {
                    CallOptions calloptions1;
                    ClientTransport clienttransport;
                    calloptions1 = callOptions.withStreamTracerFactory(factory);
                    clienttransport = get(new PickSubchannelArgsImpl(method, metadata, calloptions1));
                    factory = context.attach();
                    metadata = clienttransport.newStream(method, metadata, calloptions1);
                    context.detach(factory);
                    return metadata;
                    metadata;
                    context.detach(factory);
                    throw metadata;
                }

                final void postCommit()
                {
                    UncommittedRetriableStreamsRegistry uncommittedretriablestreamsregistry = uncommittedRetriableStreamsRegistry;
                    Status status = null;
                    synchronized (uncommittedretriablestreamsregistry.lock)
                    {
                        uncommittedretriablestreamsregistry.uncommittedRetriableStreams.remove(this);
                        if (uncommittedretriablestreamsregistry.uncommittedRetriableStreams.isEmpty())
                        {
                            status = uncommittedretriablestreamsregistry.shutdownStatus;
                            uncommittedretriablestreamsregistry.uncommittedRetriableStreams = new HashSet();
                        }
                    }
                    if (status != null)
                    {
                        uncommittedretriablestreamsregistry._fld0.delayedTransport.shutdown(status);
                    }
                    return;
                    exception;
                    obj2;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

                final Status prestart()
                {
                    return uncommittedRetriableStreamsRegistry.add(this);
                }

                _cls2(long l, long l1, Executor executor1, ScheduledExecutorService scheduledexecutorservice, RetryPolicy.Provider provider, RetriableStream.Throttle throttle1, CallOptions calloptions, MethodDescriptor methoddescriptor1, 
                        Context context1)
                {
                    this$1 = _cls5.this;
                    callOptions = calloptions;
                    method = methoddescriptor1;
                    context = context1;
                    super(final_methoddescriptor, final_metadata, final_channelbuffermeter, l, l1, executor1, scheduledexecutorservice, provider, throttle1);
                }
            }

            return new _cls2(l, l1, executor1, ((ScheduledExecutorService) (obj1)), (RetryPolicy.Provider)obj, throttle, calloptions, final_methoddescriptor, context);
_L2:
            i++;
              goto _L3
            obj = ((io.grpc.CallOptions.Key) (obj)).defaultValue;
              goto _L4
        }

        _cls5()
        {
            this$0 = ManagedChannelImpl.this;
            super();
        }
    }


    private class _cls1AutoDrainChannelExecutor
        implements Executor
    {

        private final ManagedChannelImpl this$0;

        public final void execute(Runnable runnable)
        {
            channelExecutor.executeLater(runnable);
            channelExecutor.drain();
        }

        _cls1AutoDrainChannelExecutor()
        {
            this$0 = ManagedChannelImpl.this;
            super();
        }
    }


    private class _cls6
        implements CallTracer.Factory
    {

        private final TimeProvider val$timeProvider;

        public final CallTracer create()
        {
            return new CallTracer(timeProvider);
        }

        _cls6()
        {
            timeProvider = timeprovider;
            super();
        }
    }


    private class _cls7
        implements Runnable
    {

        private final ManagedChannelImpl this$0;

        public final void run()
        {
            if (channelTracer != null)
            {
                ChannelTracer channeltracer = channelTracer;
                Channelz.ChannelTrace.Event.Builder builder = new Channelz.ChannelTrace.Event.Builder();
                builder.description = "Entering SHUTDOWN state";
                builder.severity = Channelz.ChannelTrace.Event.Severity.CT_INFO;
                builder.timestampNanos = Long.valueOf(timeProvider.currentTimeNanos());
                Channelz.ChannelTrace.Event event = builder.build();
                synchronized (channeltracer.lock)
                {
                    channeltracer.events.add(event);
                }
            }
            channelStateManager.gotoState(ConnectivityState.SHUTDOWN);
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        _cls7()
        {
            this$0 = ManagedChannelImpl.this;
            super();
        }
    }


    private class _cls8
        implements Runnable
    {

        private final ManagedChannelImpl this$0;

        public final void run()
        {
            cancelIdleTimer(true);
        }

        _cls8()
        {
            this$0 = ManagedChannelImpl.this;
            super();
        }
    }

}
