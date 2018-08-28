// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.Channel;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.NameResolver;
import io.grpc.Status;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            ManagedChannelImpl, TimeProvider, ChannelTracer, InternalSubchannel, 
//            ClientTransportFactory, Channelz, Instrumented, LogId, 
//            ChannelExecutor, ManagedClientTransport, AbstractSubchannel

final class nr extends io.grpc.lperImpl
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
        final l subchannel = new l(ManagedChannelImpl.this, internalSubchannel);
        internalSubchannel = null;
        long l = timeProvider.currentTimeNanos();
        if (maxTraceEvents > 0)
        {
            internalSubchannel = new ChannelTracer(maxTraceEvents, l, "Subchannel");
        }
        class _cls1 extends InternalSubchannel.Callback
        {

            private final ManagedChannelImpl.LbHelperImpl this$1;
            private final ManagedChannelImpl.SubchannelImpl val$subchannel;

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
                ManagedChannelImpl.LbHelperImpl lbhelperimpl = ManagedChannelImpl.LbHelperImpl.this;
                if (connectivitystateinfo.state == ConnectivityState.TRANSIENT_FAILURE || connectivitystateinfo.state == ConnectivityState.IDLE)
                {
                    lbhelperimpl.nr.refresh();
                }
                if (ManagedChannelImpl.LbHelperImpl.this == lbHelper)
                {
                    lb.handleSubchannelState(subchannel, connectivitystateinfo);
                }
            }

            final void onTerminated(InternalSubchannel internalsubchannel)
            {
                subchannels.remove(internalsubchannel);
                channelz.subchannels.remove(Long.valueOf(internalsubchannel.getLogId().id));
                internalsubchannel = this$0;
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
                this$1 = ManagedChannelImpl.LbHelperImpl.this;
                subchannel = subchannelimpl;
                super();
            }
        }

        internalSubchannel = new InternalSubchannel(list, authority(), userAgent, backoffPolicyProvider, transportFactory, transportFactory.getScheduledExecutorService(), stopwatchSupplier, channelExecutor, new _cls1(), channelz, callTracerFactory.tory(), internalSubchannel, timeProvider);
        if (channelTracer != null)
        {
            ChannelTracer channeltracer = channelTracer;
            der der = new der();
            der.description = "Child channel created";
            der.severity = rity.CT_INFO;
            der.timestampNanos = Long.valueOf(l);
            der.subchannelRef = internalSubchannel;
            der der1 = der.build();
            synchronized (channeltracer.lock)
            {
                channeltracer.events.add(der1);
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

            private final ManagedChannelImpl.LbHelperImpl this$1;
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
                this$1 = ManagedChannelImpl.LbHelperImpl.this;
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

    public final volatile io.grpc.lperImpl createSubchannel(List list, Attributes attributes)
    {
        return createSubchannel(list, attributes);
    }

    public final void updateBalancingState(final ConnectivityState newState, final io.grpc.lperImpl newPicker)
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

                private final ManagedChannelImpl.LbHelperImpl this$1;
                private final io.grpc.LoadBalancer.SubchannelPicker val$newPicker;
                private final ConnectivityState val$newState;

                public final void run()
                {
                    if (ManagedChannelImpl.LbHelperImpl.this == lbHelper) goto _L2; else goto _L1
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
                this$1 = ManagedChannelImpl.LbHelperImpl.this;
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

    public final void updateSubchannelAddresses(io.grpc.lperImpl lperimpl, List list)
    {
        InternalSubchannel internalsubchannel;
        boolean flag1;
        flag1 = true;
        if (!(lperimpl instanceof l))
        {
            throw new IllegalArgumentException(String.valueOf("subchannel must have been returned from createSubchannel"));
        }
        internalsubchannel = ((l)lperimpl).subchannel;
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
        lperimpl = Collections.unmodifiableList(new ArrayList(list));
        list = ((List) (internalsubchannel.lock));
        list;
        JVM INSTR monitorenter ;
        Object obj;
        obj = internalsubchannel.addressIndex;
        obj = (SocketAddress)((EquivalentAddressGroup)((oNulls) (obj)).Groups.get(((Groups) (obj)).dex)).addrs.get(((dex) (obj)).Index);
        or or2 = internalsubchannel.addressIndex;
        or2.Groups = lperimpl;
        or2.dex = 0;
        or2.Index = 0;
        if (internalsubchannel.state.state != ConnectivityState.READY && internalsubchannel.state.state != ConnectivityState.CONNECTING)
        {
            break MISSING_BLOCK_LABEL_425;
        }
        lperimpl = internalsubchannel.addressIndex;
        int i = 0;
_L7:
        if (i >= ((Index) (lperimpl)).Groups.size()) goto _L2; else goto _L1
_L1:
        int j = ((EquivalentAddressGroup)((Groups) (lperimpl)).Groups.get(i)).addrs.indexOf(obj);
        if (j == -1) goto _L4; else goto _L3
_L3:
        lperimpl.dex = i;
        lperimpl.Index = j;
        i = ((flag1) ? 1 : 0);
_L8:
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_425;
        }
        if (internalsubchannel.state.state != ConnectivityState.READY) goto _L6; else goto _L5
_L5:
        lperimpl = internalsubchannel.activeTransport;
        internalsubchannel.activeTransport = null;
        or or = internalsubchannel.addressIndex;
        or.dex = 0;
        or.Index = 0;
        internalsubchannel.gotoState(ConnectivityStateInfo.forNonError(ConnectivityState.IDLE));
_L9:
        list;
        JVM INSTR monitorexit ;
        internalsubchannel.channelExecutor.drain();
        if (lperimpl != null)
        {
            lperimpl.shutdown(Status.UNAVAILABLE.withDescription("InternalSubchannel closed transport due to address change"));
        }
        return;
_L4:
        i++;
          goto _L7
_L2:
        i = 0;
          goto _L8
_L6:
        lperimpl = internalsubchannel.pendingTransport;
        internalsubchannel.pendingTransport = null;
        or or1 = internalsubchannel.addressIndex;
        or1.dex = 0;
        or1.Index = 0;
        internalsubchannel.startNewTransport();
          goto _L9
        lperimpl;
        list;
        JVM INSTR monitorexit ;
        throw lperimpl;
        lperimpl;
        internalsubchannel.channelExecutor.drain();
        throw lperimpl;
        lperimpl = null;
          goto _L9
    }

    _cls3(NameResolver nameresolver)
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
