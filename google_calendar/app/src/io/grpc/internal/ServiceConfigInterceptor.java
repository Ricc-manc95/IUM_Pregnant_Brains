// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Strings;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.Deadline;
import io.grpc.MethodDescriptor;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            RetryPolicy, ServiceConfigUtil

final class ServiceConfigInterceptor
    implements ClientInterceptor
{
    static final class MethodInfo
    {

        public final Integer maxInboundMessageSize;
        public final Integer maxOutboundMessageSize;
        public final RetryPolicy retryPolicy;
        public final Long timeoutNanos;
        public final Boolean waitForReady;

        public final boolean equals(Object obj)
        {
            if (obj instanceof MethodInfo)
            {
                obj = (MethodInfo)obj;
                Long long1 = timeoutNanos;
                Long long2 = ((MethodInfo) (obj)).timeoutNanos;
                boolean flag;
                if (long1 == long2 || long1 != null && long1.equals(long2))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    Boolean boolean1 = waitForReady;
                    Boolean boolean2 = ((MethodInfo) (obj)).waitForReady;
                    if (boolean1 == boolean2 || boolean1 != null && boolean1.equals(boolean2))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        Integer integer = maxInboundMessageSize;
                        Integer integer2 = ((MethodInfo) (obj)).maxInboundMessageSize;
                        if (integer == integer2 || integer != null && integer.equals(integer2))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            Integer integer1 = maxOutboundMessageSize;
                            Integer integer3 = ((MethodInfo) (obj)).maxOutboundMessageSize;
                            if (integer1 == integer3 || integer1 != null && integer1.equals(integer3))
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (flag)
                            {
                                RetryPolicy retrypolicy = retryPolicy;
                                obj = ((MethodInfo) (obj)).retryPolicy;
                                if (retrypolicy == obj || retrypolicy != null && retrypolicy.equals(obj))
                                {
                                    flag = true;
                                } else
                                {
                                    flag = false;
                                }
                                if (flag)
                                {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }

        public final int hashCode()
        {
            return Arrays.hashCode(new Object[] {
                timeoutNanos, waitForReady, maxInboundMessageSize, maxOutboundMessageSize, retryPolicy
            });
        }

        public final String toString()
        {
            return (new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName())).add("timeoutNanos", timeoutNanos).add("waitForReady", waitForReady).add("maxInboundMessageSize", maxInboundMessageSize).add("maxOutboundMessageSize", maxOutboundMessageSize).add("retryPolicy", retryPolicy).toString();
        }

        MethodInfo(Map map, boolean flag, int i)
        {
            timeoutNanos = ServiceConfigUtil.getTimeoutFromMethodConfig(map);
            waitForReady = ServiceConfigUtil.getWaitForReadyFromMethodConfig(map);
            maxInboundMessageSize = ServiceConfigUtil.getMaxResponseMessageBytesFromMethodConfig(map);
            if (maxInboundMessageSize != null)
            {
                Integer integer;
                boolean flag1;
                if (maxInboundMessageSize.intValue() >= 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                integer = maxInboundMessageSize;
                if (!flag1)
                {
                    throw new IllegalArgumentException(Strings.lenientFormat("maxInboundMessageSize %s exceeds bounds", new Object[] {
                        integer
                    }));
                }
            }
            maxOutboundMessageSize = ServiceConfigUtil.getMaxRequestMessageBytesFromMethodConfig(map);
            if (maxOutboundMessageSize != null)
            {
                Integer integer1;
                boolean flag2;
                if (maxOutboundMessageSize.intValue() >= 0)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                integer1 = maxOutboundMessageSize;
                if (!flag2)
                {
                    throw new IllegalArgumentException(Strings.lenientFormat("maxOutboundMessageSize %s exceeds bounds", new Object[] {
                        integer1
                    }));
                }
            }
            if (flag)
            {
                map = ServiceConfigUtil.getRetryPolicyFromMethodConfig(map);
            } else
            {
                map = null;
            }
            if (map == null)
            {
                map = RetryPolicy.DEFAULT;
            } else
            {
                Object obj = ServiceConfigUtil.getMaxAttemptsFromRetryPolicy(map);
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("maxAttempts cannot be empty"));
                }
                int k = ((Integer)obj).intValue();
                boolean flag3;
                if (k >= 2)
                {
                    flag3 = true;
                } else
                {
                    flag3 = false;
                }
                if (!flag3)
                {
                    throw new IllegalArgumentException(Strings.lenientFormat("maxAttempts must be greater than 1: %s", new Object[] {
                        Integer.valueOf(k)
                    }));
                }
                int j = Math.min(k, i);
                obj = ServiceConfigUtil.getInitialBackoffNanosFromRetryPolicy(map);
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("initialBackoff cannot be empty"));
                }
                long l = ((Long)obj).longValue();
                if (l > 0L)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    throw new IllegalArgumentException(Strings.lenientFormat("initialBackoffNanos must be greater than 0: %s", new Object[] {
                        Long.valueOf(l)
                    }));
                }
                obj = ServiceConfigUtil.getMaxBackoffNanosFromRetryPolicy(map);
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("maxBackoff cannot be empty"));
                }
                long l1 = ((Long)obj).longValue();
                if (l1 > 0L)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    throw new IllegalArgumentException(Strings.lenientFormat("maxBackoff must be greater than 0: %s", new Object[] {
                        Long.valueOf(l1)
                    }));
                }
                obj = ServiceConfigUtil.getBackoffMultiplierFromRetryPolicy(map);
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("backoffMultiplier cannot be empty"));
                }
                double d = ((Double)obj).doubleValue();
                if (d > 0.0D)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    throw new IllegalArgumentException(Strings.lenientFormat("backoffMultiplier must be greater than 0: %s", new Object[] {
                        Double.valueOf(d)
                    }));
                }
                obj = ServiceConfigUtil.getRetryableStatusCodesFromRetryPolicy(map);
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("rawCodes must be present"));
                }
                if (!((List) (obj)).isEmpty())
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    throw new IllegalArgumentException(String.valueOf("rawCodes can't be empty"));
                }
                map = EnumSet.noneOf(io/grpc/Status$Code);
                for (obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); map.add(io.grpc.Status.Code.valueOf((String)((Iterator) (obj)).next()))) { }
                map = new RetryPolicy(j, l, l1, d, Collections.unmodifiableSet(map));
            }
            retryPolicy = map;
        }
    }


    public static final io.grpc.CallOptions.Key RETRY_POLICY_KEY = io.grpc.CallOptions.Key.create("internal-retry-policy");
    public static final Logger logger = Logger.getLogger(io/grpc/internal/ServiceConfigInterceptor.getName());
    public final int maxRetryAttemptsLimit;
    public volatile boolean nameResolveComplete;
    public final boolean retryEnabled;
    public final AtomicReference serviceMap = new AtomicReference();
    public final AtomicReference serviceMethodMap = new AtomicReference();

    ServiceConfigInterceptor(boolean flag, int i)
    {
        retryEnabled = flag;
        maxRetryAttemptsLimit = i;
    }

    final MethodInfo getMethodInfo(MethodDescriptor methoddescriptor)
    {
        Map map = (Map)serviceMethodMap.get();
        MethodInfo methodinfo = null;
        if (map != null)
        {
            methodinfo = (MethodInfo)map.get(methoddescriptor.fullMethodName);
        }
        if (methodinfo == null)
        {
            Map map1 = (Map)serviceMap.get();
            if (map1 != null)
            {
                return (MethodInfo)map1.get(MethodDescriptor.extractFullServiceName(methoddescriptor.fullMethodName));
            }
        }
        return methodinfo;
    }

    public final ClientCall interceptCall(final MethodDescriptor method, CallOptions calloptions, Channel channel)
    {
        final Object retryPolicy;
        MethodInfo methodinfo;
label0:
        {
            retryPolicy = calloptions;
            if (retryEnabled)
            {
                if (nameResolveComplete)
                {
                    retryPolicy = getMethodInfo(method);
                    if (retryPolicy == null || ((MethodInfo) (retryPolicy)).retryPolicy == null)
                    {
                        retryPolicy = RetryPolicy.DEFAULT;
                    } else
                    {
                        retryPolicy = ((MethodInfo) (retryPolicy)).retryPolicy;
                    }
                    retryPolicy = calloptions.withOption(RETRY_POLICY_KEY, new _cls1ImmediateRetryPolicyProvider());
                } else
                {
                    retryPolicy = calloptions.withOption(RETRY_POLICY_KEY, new _cls1DelayedRetryPolicyProvider());
                }
            }
            methodinfo = getMethodInfo(method);
            if (methodinfo == null)
            {
                return channel.newCall(method, ((CallOptions) (retryPolicy)));
            }
            calloptions = ((CallOptions) (retryPolicy));
            if (methodinfo.timeoutNanos == null)
            {
                break label0;
            }
            Deadline deadline = Deadline.after(methodinfo.timeoutNanos.longValue(), TimeUnit.NANOSECONDS);
            calloptions = ((CallOptions) (retryPolicy)).deadline;
            if (calloptions != null)
            {
                long l = deadline.deadlineNanos - ((Deadline) (calloptions)).deadlineNanos;
                byte byte0;
                if (l < 0L)
                {
                    byte0 = -1;
                } else
                if (l > 0L)
                {
                    byte0 = 1;
                } else
                {
                    byte0 = 0;
                }
                calloptions = ((CallOptions) (retryPolicy));
                if (byte0 >= 0)
                {
                    break label0;
                }
            }
            calloptions = new CallOptions(((CallOptions) (retryPolicy)));
            calloptions.deadline = deadline;
        }
        if (methodinfo.waitForReady != null)
        {
            if (methodinfo.waitForReady.booleanValue())
            {
                retryPolicy = new CallOptions(calloptions);
                retryPolicy.waitForReady = true;
            } else
            {
                retryPolicy = new CallOptions(calloptions);
                retryPolicy.waitForReady = false;
            }
        } else
        {
            retryPolicy = calloptions;
        }
        calloptions = ((CallOptions) (retryPolicy));
        if (methodinfo.maxInboundMessageSize != null)
        {
            calloptions = ((CallOptions) (retryPolicy)).maxInboundMessageSize;
            if (calloptions != null)
            {
                calloptions = ((CallOptions) (retryPolicy)).withMaxInboundMessageSize(Math.min(calloptions.intValue(), methodinfo.maxInboundMessageSize.intValue()));
            } else
            {
                calloptions = ((CallOptions) (retryPolicy)).withMaxInboundMessageSize(methodinfo.maxInboundMessageSize.intValue());
            }
        }
        retryPolicy = calloptions;
        if (methodinfo.maxOutboundMessageSize != null)
        {
            retryPolicy = calloptions.maxOutboundMessageSize;
            if (retryPolicy != null)
            {
                retryPolicy = calloptions.withMaxOutboundMessageSize(Math.min(((Integer) (retryPolicy)).intValue(), methodinfo.maxOutboundMessageSize.intValue()));
            } else
            {
                retryPolicy = calloptions.withMaxOutboundMessageSize(methodinfo.maxOutboundMessageSize.intValue());
            }
        }
        return channel.newCall(method, ((CallOptions) (retryPolicy)));
    }


    private class _cls1ImmediateRetryPolicyProvider
        implements RetryPolicy.Provider
    {

        private final RetryPolicy val$retryPolicy;

        public final RetryPolicy get()
        {
            return retryPolicy;
        }

        _cls1ImmediateRetryPolicyProvider()
        {
            retryPolicy = retrypolicy;
            super();
        }
    }


    private class _cls1DelayedRetryPolicyProvider
        implements RetryPolicy.Provider
    {

        private final ServiceConfigInterceptor this$0;
        private final MethodDescriptor val$method;

        public final RetryPolicy get()
        {
            if (!nameResolveComplete)
            {
                return RetryPolicy.DEFAULT;
            }
            MethodInfo methodinfo = getMethodInfo(method);
            if (methodinfo == null || methodinfo.retryPolicy == null)
            {
                return RetryPolicy.DEFAULT;
            } else
            {
                return methodinfo.retryPolicy;
            }
        }

        _cls1DelayedRetryPolicyProvider()
        {
            this$0 = ServiceConfigInterceptor.this;
            method = methoddescriptor;
            super();
        }
    }

}
