// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Strings;
import io.grpc.Attributes;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;
import java.net.URI;

// Referenced classes of package io.grpc.internal:
//            GrpcUtil, DnsNameResolver

public final class DnsNameResolverProvider extends NameResolverProvider
{

    public DnsNameResolverProvider()
    {
    }

    public final String getDefaultScheme()
    {
        return "dns";
    }

    protected final boolean isAvailable()
    {
        return true;
    }

    public final NameResolver newNameResolver(URI uri, Attributes attributes)
    {
        if ("dns".equals(uri.getScheme()))
        {
            String s = uri.getPath();
            if (s == null)
            {
                throw new NullPointerException(String.valueOf("targetPath"));
            }
            s = (String)s;
            if (!s.startsWith("/"))
            {
                throw new IllegalArgumentException(Strings.lenientFormat("the path component (%s) of the target (%s) must start with '/'", new Object[] {
                    s, uri
                }));
            }
            s = s.substring(1);
            uri.getAuthority();
            SharedResourceHolder.Resource resource = GrpcUtil.SHARED_CHANNEL_EXECUTOR;
            if (GrpcUtil.IS_RESTRICTED_APPENGINE)
            {
                uri = GrpcUtil.NOOP_PROXY_DETECTOR;
            } else
            {
                uri = GrpcUtil.DEFAULT_PROXY_DETECTOR;
            }
            return new DnsNameResolver(s, attributes, resource, uri);
        } else
        {
            return null;
        }
    }

    protected final int priority()
    {
        return 5;
    }
}
