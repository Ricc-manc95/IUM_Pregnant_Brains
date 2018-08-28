// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.Status;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            InternalSubchannel, ConnectionClientTransport, ChannelExecutor, BackoffPolicy, 
//            LogExceptionRunnable, Channelz, WithLogId, LogId

final class address
    implements address
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
        status.transport = 0;
        status. = 0;
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
        EquivalentAddressGroup equivalentaddressgroup = (EquivalentAddressGroup)((transport) (obj1)).s.get(((s) (obj1)).s);
        obj1. = (() (obj1)). + 1;
        if ((() (obj1)). >= equivalentaddressgroup.addrs.size())
        {
            obj1. = (() (obj1)). + 1;
            obj1. = 0;
        }
        obj1 = addressIndex;
        long l;
        if ((() (obj1)). < (() (obj1)).s.size())
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
        obj1.s = 0;
        obj1. = 0;
        obj1 = InternalSubchannel.this;
        if (io.grpc.Listener.this._fld0 == status.code)
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
            obj1.reconnectPolicy = ((InternalSubchannel) (obj1)).backoffPolicyProvider.der();
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
        obj1.reconnectTask = ((InternalSubchannel) (obj1)).scheduledExecutor.schedule(new LogExceptionRunnable(new ff(((InternalSubchannel) (obj1)))), l, TimeUnit.NANOSECONDS);
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
        Channelz channelz = InternalSubchannel.this.channelz;
        ConnectionClientTransport connectionclienttransport = transport;
        channelz.otherSockets.remove(Long.valueOf(connectionclienttransport.getLogId().id));
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
                internalsubchannel.channelExecutor.executeLater(new this._cls0(internalsubchannel));
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

    ff(ConnectionClientTransport connectionclienttransport, SocketAddress socketaddress)
    {
        this$0 = InternalSubchannel.this;
        super();
        transport = connectionclienttransport;
        address = socketaddress;
    }
}
