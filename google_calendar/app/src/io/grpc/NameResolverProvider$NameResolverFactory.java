// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package io.grpc:
//            NameResolverProvider, Attributes, NameResolver

static final class providers extends providers
{

    private final List providers;

    public final String getDefaultScheme()
    {
        boolean flag;
        if (!providers.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("No NameResolverProviders found via ServiceLoader, including for DNS. This is probably due to a broken build. If using ProGuard, check your configuration"));
        } else
        {
            return ((NameResolverProvider)providers.get(0)).providers();
        }
    }

    public final NameResolver newNameResolver(URI uri, Attributes attributes)
    {
        boolean flag;
        if (!providers.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("No NameResolverProviders found via ServiceLoader, including for DNS. This is probably due to a broken build. If using ProGuard, check your configuration"));
        }
        for (Iterator iterator = providers.iterator(); iterator.hasNext();)
        {
            NameResolver nameresolver = ((NameResolverProvider)iterator.next()).providers(uri, attributes);
            if (nameresolver != null)
            {
                return nameresolver;
            }
        }

        return null;
    }

    public (List list)
    {
        providers = list;
    }
}
