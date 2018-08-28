// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Platform;
import com.google.common.base.Strings;
import io.grpc.Attributes;
import io.grpc.LoadBalancer;
import io.grpc.MethodDescriptor;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            ManagedChannelImpl, GrpcAttributes, ServiceConfigUtil, ServiceConfigInterceptor

final class val.servers
    implements Runnable
{

    private final val.config this$1;
    private final Attributes val$config;
    private final List val$servers;

    public final void run()
    {
        Object obj3;
        if (this._cls1.this.servers != lbHelper)
        {
            return;
        }
        nameResolverBackoffPolicy = null;
        Attributes attributes = val$config;
        io.grpc.Resolved resolved = GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG;
        obj3 = (Map)attributes.data.get(resolved);
        if (obj3 == null) goto _L2; else goto _L1
_L1:
        Object obj;
        Object obj1;
        Object obj2;
        obj = serviceConfigInterceptor;
        obj1 = new HashMap();
        obj2 = new HashMap();
        obj3 = ServiceConfigUtil.getMethodConfigFromServiceConfig(((Map) (obj3)));
        if (obj3 != null) goto _L4; else goto _L3
_L3:
        ServiceConfigInterceptor.logger.logp(Level.FINE, "io.grpc.internal.ServiceConfigInterceptor", "handleUpdate", "No method configs found, skipping");
        obj.nameResolveComplete = true;
_L9:
        if (retryEnabled)
        {
            throttle = ManagedChannelImpl.getThrottle(val$config);
        }
_L2:
        this._cls1.this.config._fld1.handleResolvedAddressGroups(val$servers, val$config);
        return;
_L4:
        obj3 = ((List) (obj3)).iterator();
_L7:
        val.servers servers1;
        Object obj4;
        Object obj5;
        if (!((Iterator) (obj3)).hasNext())
        {
            break MISSING_BLOCK_LABEL_554;
        }
        obj4 = (Map)((Iterator) (obj3)).next();
        servers1 = new val.config(((Map) (obj4)), ((ServiceConfigInterceptor) (obj)).retryEnabled, ((ServiceConfigInterceptor) (obj)).maxRetryAttemptsLimit);
        obj5 = ServiceConfigUtil.getNameListFromMethodConfig(((Map) (obj4)));
        if (obj5 == null)
        {
            break MISSING_BLOCK_LABEL_347;
        }
        if (((List) (obj5)).isEmpty())
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
                obj4
            }));
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            obj1 = ManagedChannelImpl.logger;
        }
        obj2 = Level.WARNING;
        String s = String.valueOf(logId);
        ((Logger) (obj1)).logp(((Level) (obj2)), "io.grpc.internal.ManagedChannelImpl$NameResolverListenerImpl$1NamesResolved", "run", (new StringBuilder(String.valueOf(s).length() + 51)).append("[").append(s).append("] Unexpected exception from parsing service config").toString(), ((Throwable) (obj)));
          goto _L2
        flag = false;
          goto _L5
        obj4 = ((List) (obj5)).iterator();
_L8:
        if (!((Iterator) (obj4)).hasNext()) goto _L7; else goto _L6
_L6:
        Object obj6;
        obj6 = (Map)((Iterator) (obj4)).next();
        obj5 = ServiceConfigUtil.getServiceFromName(((Map) (obj6)));
        if (!Platform.stringIsNullOrEmpty(((String) (obj5))))
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
        obj6 = ServiceConfigUtil.getMethodFromName(((Map) (obj6)));
        if (!Platform.stringIsNullOrEmpty(((String) (obj6))))
        {
            break MISSING_BLOCK_LABEL_490;
        }
        if (!((Map) (obj2)).containsKey(obj5))
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
            obj5
        }));
        ((Map) (obj2)).put(obj5, servers1);
          goto _L8
        obj5 = MethodDescriptor.generateFullMethodName(((String) (obj5)), ((String) (obj6)));
        if (!((Map) (obj1)).containsKey(obj5))
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
            obj5
        }));
        ((Map) (obj1)).put(obj5, servers1);
          goto _L8
          goto _L7
        ((ServiceConfigInterceptor) (obj)).serviceMethodMap.set(Collections.unmodifiableMap(((Map) (obj1))));
        ((ServiceConfigInterceptor) (obj)).serviceMap.set(Collections.unmodifiableMap(((Map) (obj2))));
        obj.nameResolveComplete = true;
          goto _L9
    }

    Q()
    {
        this$1 = final_q;
        val$config = attributes;
        val$servers = List.this;
        super();
    }
}
