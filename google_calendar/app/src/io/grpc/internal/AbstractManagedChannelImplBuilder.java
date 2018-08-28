// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.BinaryLog;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolverProvider;
import io.opencensus.trace.TraceComponent;
import io.opencensus.trace.Tracing;
import io.opencensus.trace.propagation.PropagationComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

// Referenced classes of package io.grpc.internal:
//            SharedResourcePool, GrpcUtil, Channelz, TransportTracer, 
//            CensusStatsModule, CensusTracingModule, ManagedChannelOrphanWrapper, ManagedChannelImpl, 
//            TimeProvider, ObjectPool, ClientTransportFactory

public abstract class AbstractManagedChannelImplBuilder extends ManagedChannelBuilder
{

    private static final CompressorRegistry DEFAULT_COMPRESSOR_REGISTRY;
    private static final DecompressorRegistry DEFAULT_DECOMPRESSOR_REGISTRY;
    private static final ObjectPool DEFAULT_EXECUTOR_POOL;
    private static final io.grpc.NameResolver.Factory DEFAULT_NAME_RESOLVER_FACTORY;
    private static final long IDLE_MODE_DEFAULT_TIMEOUT_MILLIS;
    public static final long IDLE_MODE_MIN_TIMEOUT_MILLIS;
    public BinaryLog binlog;
    public Channelz channelz;
    public CompressorRegistry compressorRegistry;
    public DecompressorRegistry decompressorRegistry;
    public ObjectPool executorPool;
    public boolean fullStreamDecompression;
    public long idleTimeoutMillis;
    private final List interceptors = new ArrayList();
    public io.grpc.LoadBalancer.Factory loadBalancerFactory;
    public int maxInboundMessageSize;
    public int maxRetryAttempts;
    public int maxTraceEvents;
    public io.grpc.NameResolver.Factory nameResolverFactory;
    public long perRpcBufferLimit;
    private boolean recordFinishedRpcs;
    private boolean recordStartedRpcs;
    public long retryBufferSize;
    public boolean retryEnabled;
    private boolean statsEnabled;
    public final String target;
    public boolean temporarilyDisableRetry;
    private boolean tracingEnabled;
    public TransportTracer.Factory transportTracerFactory;
    public String userAgent;

    public AbstractManagedChannelImplBuilder(String s)
    {
        executorPool = DEFAULT_EXECUTOR_POOL;
        nameResolverFactory = DEFAULT_NAME_RESOLVER_FACTORY;
        decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
        compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
        idleTimeoutMillis = IDLE_MODE_DEFAULT_TIMEOUT_MILLIS;
        maxRetryAttempts = 5;
        retryBufferSize = 0x1000000L;
        perRpcBufferLimit = 0x100000L;
        retryEnabled = false;
        channelz = Channelz.INSTANCE;
        transportTracerFactory = TransportTracer.DEFAULT_FACTORY;
        maxInboundMessageSize = 0x400000;
        statsEnabled = true;
        recordStartedRpcs = true;
        recordFinishedRpcs = true;
        tracingEnabled = true;
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("target"));
        } else
        {
            target = (String)s;
            return;
        }
    }

    public final ManagedChannel build()
    {
        ClientTransportFactory clienttransportfactory = buildTransportFactory();
        ExponentialBackoffPolicy.Provider provider = new ExponentialBackoffPolicy.Provider();
        SharedResourcePool sharedresourcepool = new SharedResourcePool(GrpcUtil.SHARED_CHANNEL_EXECUTOR);
        com.google.common.base.Supplier supplier = GrpcUtil.STOPWATCH_SUPPLIER;
        ArrayList arraylist = new ArrayList(interceptors);
        temporarilyDisableRetry = false;
        if (statsEnabled)
        {
            temporarilyDisableRetry = true;
            arraylist.add(0, new CensusStatsModule.StatsClientInterceptor(new CensusStatsModule(GrpcUtil.STOPWATCH_SUPPLIER, true), recordStartedRpcs, recordFinishedRpcs));
        }
        if (tracingEnabled)
        {
            temporarilyDisableRetry = true;
            arraylist.add(0, (new CensusTracingModule(Tracing.traceComponent.getTracer(), Tracing.traceComponent.getPropagationComponent().getBinaryFormat())).clientInterceptor);
        }
        return new ManagedChannelOrphanWrapper(new ManagedChannelImpl(this, clienttransportfactory, provider, sharedresourcepool, supplier, arraylist, TimeProvider.SYSTEM_TIME_PROVIDER));
    }

    public abstract ClientTransportFactory buildTransportFactory();

    public Attributes getNameResolverParams()
    {
        return Attributes.EMPTY;
    }

    static 
    {
        IDLE_MODE_DEFAULT_TIMEOUT_MILLIS = TimeUnit.MINUTES.toMillis(30L);
        IDLE_MODE_MIN_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(1L);
        DEFAULT_EXECUTOR_POOL = new SharedResourcePool(GrpcUtil.SHARED_CHANNEL_EXECUTOR);
        DEFAULT_NAME_RESOLVER_FACTORY = NameResolverProvider.factory;
        DEFAULT_DECOMPRESSOR_REGISTRY = DecompressorRegistry.DEFAULT_INSTANCE;
        DEFAULT_COMPRESSOR_REGISTRY = CompressorRegistry.DEFAULT_INSTANCE;
    }
}
