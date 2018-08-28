// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.Status;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            ManagedChannelImpl, TimeProvider, ChannelTracer, ChannelExecutor

final class helper
    implements io.grpc.enerImpl
{

    public final _cls1 helper;
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
            _cls1NamesResolved.val.servers servers2 = new onError();
            Object obj1 = String.valueOf(servers);
            servers2.ion = (new StringBuilder(String.valueOf(obj1).length() + 18)).append("Address resolved: ").append(((String) (obj1))).toString();
            servers2. = ;
            servers2.pNanos = Long.valueOf(timeProvider.currentTimeNanos());
            obj1 = servers2.pNanos();
            synchronized (channeltracer.lock)
            {
                channeltracer.events.add(obj1);
            }
        }
        _cls1NamesResolved.val.servers servers1 = helper;
        class _cls1NamesResolved
            implements Runnable
        {

            private final ManagedChannelImpl.NameResolverListenerImpl this$1;
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
                this$1 = ManagedChannelImpl.NameResolverListenerImpl.this;
                config = attributes;
                servers = list;
                super();
            }
        }

        servers = new _cls1NamesResolved();
        servers1._fld1NamesResolved.channelExecutor.executeLater(servers).drain();
        return;
        servers;
        obj;
        JVM INSTR monitorexit ;
        throw servers;
    }

    public final void onError(final Status error)
    {
        boolean flag;
        if (io.grpc.enerImpl == error.code)
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
            _cls1.val.error error1 = new _cls1.val.error();
            error1.ion = "Failed to resolve name";
            error1. = ING;
            error1.pNanos = Long.valueOf(timeProvider.currentTimeNanos());
            _cls1.val.error error2 = error1.pNanos();
            synchronized (channeltracer.lock)
            {
                channeltracer.events.add(error2);
            }
        }
        class _cls1
            implements Runnable
        {

            private final ManagedChannelImpl.NameResolverListenerImpl this$1;
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
                        nameResolverRefresh = new ManagedChannelImpl.NameResolverRefresh(this$0);
                        nameResolverRefreshFuture = transportFactory.getScheduledExecutorService().schedule(nameResolverRefresh, l, TimeUnit.NANOSECONDS);
                        return;
                    }
                }
            }

            _cls1()
            {
                this$1 = ManagedChannelImpl.NameResolverListenerImpl.this;
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

    _cls1(_cls1 _pcls1)
    {
        this$0 = ManagedChannelImpl.this;
        super();
        helper = _pcls1;
    }
}
