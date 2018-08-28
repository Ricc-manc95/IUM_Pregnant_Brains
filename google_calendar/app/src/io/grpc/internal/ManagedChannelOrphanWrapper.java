// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.ManagedChannel;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            ForwardingManagedChannel

final class ManagedChannelOrphanWrapper extends ForwardingManagedChannel
{
    static final class ManagedChannelReference extends WeakReference
    {

        private static final boolean ENABLE_ALLOCATION_TRACKING = Boolean.parseBoolean(System.getProperty("io.grpc.ManagedChannel.enableAllocationTracking", "true"));
        private static final RuntimeException missingCallSite;
        private final Reference allocationSite;
        private final ManagedChannel channel;
        private final ReferenceQueue refqueue;
        private final ConcurrentMap refs;
        public volatile boolean shutdown;
        public volatile boolean shutdownNow;

        private static int cleanQueue(ReferenceQueue referencequeue)
        {
            int i = 0;
            do
            {
                ManagedChannelReference managedchannelreference = (ManagedChannelReference)referencequeue.poll();
                if (managedchannelreference == null)
                {
                    break;
                }
                RuntimeException runtimeexception = (RuntimeException)managedchannelreference.allocationSite.get();
                managedchannelreference.super.clear();
                managedchannelreference.refs.remove(managedchannelreference);
                managedchannelreference.allocationSite.clear();
                if (!managedchannelreference.shutdown || !managedchannelreference.channel.isTerminated())
                {
                    Object obj;
                    if (managedchannelreference.shutdownNow)
                    {
                        obj = Level.FINE;
                    } else
                    {
                        obj = Level.SEVERE;
                    }
                    if (ManagedChannelOrphanWrapper.logger.isLoggable(((Level) (obj))))
                    {
                        String s;
                        String s1;
                        if (!managedchannelreference.shutdown)
                        {
                            s = "shutdown";
                        } else
                        {
                            s = "terminated";
                        }
                        s1 = System.getProperty("line.separator");
                        obj = new LogRecord(((Level) (obj)), (new StringBuilder(String.valueOf(s).length() + 140 + String.valueOf(s1).length())).append("*~*~*~ Channel {0} was not ").append(s).append(" properly!!! ~*~*~*").append(s1).append("    Make sure to call shutdown()/shutdownNow() and wait until awaitTermination() returns true.").toString());
                        ((LogRecord) (obj)).setLoggerName(ManagedChannelOrphanWrapper.logger.getName());
                        ((LogRecord) (obj)).setParameters(new Object[] {
                            managedchannelreference.channel.toString()
                        });
                        ((LogRecord) (obj)).setThrown(runtimeexception);
                        ManagedChannelOrphanWrapper.logger.log(((LogRecord) (obj)));
                    }
                    i++;
                }
            } while (true);
            return i;
        }

        public final void clear()
        {
            super.clear();
            refs.remove(this);
            allocationSite.clear();
            cleanQueue(refqueue);
        }

        static 
        {
            RuntimeException runtimeexception = new RuntimeException("ManagedChannel allocation site not recorded.  Set -Dio.grpc.ManagedChannel.enableAllocationTracking=true to enable it");
            runtimeexception.setStackTrace(new StackTraceElement[0]);
            missingCallSite = runtimeexception;
        }

        ManagedChannelReference(ManagedChannelOrphanWrapper managedchannelorphanwrapper, ManagedChannel managedchannel, ReferenceQueue referencequeue, ConcurrentMap concurrentmap)
        {
            super(managedchannelorphanwrapper, referencequeue);
            if (ENABLE_ALLOCATION_TRACKING)
            {
                managedchannelorphanwrapper = new RuntimeException("ManagedChannel allocation site");
            } else
            {
                managedchannelorphanwrapper = missingCallSite;
            }
            allocationSite = new SoftReference(managedchannelorphanwrapper);
            channel = managedchannel;
            refqueue = referencequeue;
            refs = concurrentmap;
            refs.put(this, this);
            cleanQueue(referencequeue);
        }
    }


    public static final Logger logger = Logger.getLogger(io/grpc/internal/ManagedChannelOrphanWrapper.getName());
    private static final ReferenceQueue refqueue = new ReferenceQueue();
    private static final ConcurrentMap refs = new ConcurrentHashMap();
    private final ManagedChannelReference phantom;

    ManagedChannelOrphanWrapper(ManagedChannel managedchannel)
    {
        this(managedchannel, refqueue, refs);
    }

    private ManagedChannelOrphanWrapper(ManagedChannel managedchannel, ReferenceQueue referencequeue, ConcurrentMap concurrentmap)
    {
        super(managedchannel);
        phantom = new ManagedChannelReference(this, managedchannel, referencequeue, concurrentmap);
    }

    public final ManagedChannel shutdown()
    {
        phantom.shutdown = true;
        return super.shutdown();
    }

}
