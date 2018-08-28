// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

// Referenced classes of package io.grpc:
//            ServiceProviders, Attributes, NameResolver

public abstract class NameResolverProvider extends NameResolver.Factory
{
    static final class HardcodedClasses
        implements Iterable
    {

        public final Iterator iterator()
        {
            ArrayList arraylist = new ArrayList();
            try
            {
                arraylist.add(Class.forName("io.grpc.internal.DnsNameResolverProvider"));
            }
            catch (ClassNotFoundException classnotfoundexception) { }
            return arraylist.iterator();
        }

        HardcodedClasses()
        {
        }
    }

    static final class NameResolverFactory extends NameResolver.Factory
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
                return ((NameResolverProvider)providers.get(0)).getDefaultScheme();
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
                NameResolver nameresolver = ((NameResolverProvider)iterator.next()).newNameResolver(uri, attributes);
                if (nameresolver != null)
                {
                    return nameresolver;
                }
            }

            return null;
        }

        public NameResolverFactory(List list)
        {
            providers = list;
        }
    }


    private static final Iterable HARDCODED_CLASSES;
    public static final NameResolver.Factory factory;
    private static final List providers;

    public NameResolverProvider()
    {
    }

    public abstract boolean isAvailable();

    public abstract int priority();

    static 
    {
        Object obj;
        Object obj1;
        _cls1 _lcls1;
        HARDCODED_CLASSES = new HardcodedClasses();
        obj1 = HARDCODED_CLASSES;
        obj = io/grpc/NameResolverProvider.getClassLoader();
        _lcls1 = new _cls1();
        if (!ServiceProviders.isAndroid(((ClassLoader) (obj)))) goto _L2; else goto _L1
_L1:
        obj = new ArrayList();
        for (obj1 = ((Iterable) (obj1)).iterator(); ((Iterator) (obj1)).hasNext(); ((List) (obj)).add(ServiceProviders.create(io/grpc/NameResolverProvider, (Class)((Iterator) (obj1)).next()))) { }
_L4:
        obj1 = new ArrayList();
        obj = ((Iterable) (obj)).iterator();
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break;
            }
            Object obj2 = ((Iterator) (obj)).next();
            if (_lcls1.isAvailable(obj2))
            {
                ((List) (obj1)).add(obj2);
            }
        } while (true);
        break; /* Loop/switch isn't completed */
_L2:
        obj1 = ServiceLoader.load(io/grpc/NameResolverProvider, ((ClassLoader) (obj)));
        obj = obj1;
        if (!((Iterable) (obj1)).iterator().hasNext())
        {
            obj = ServiceLoader.load(io/grpc/NameResolverProvider);
        }
        if (true) goto _L4; else goto _L3
_L3:
        Collections.sort(((List) (obj1)), Collections.reverseOrder(new ServiceProviders._cls1(_lcls1)));
        providers = Collections.unmodifiableList(((List) (obj1)));
        factory = new NameResolverFactory(providers);
    }

    private class _cls1
        implements ServiceProviders.PriorityAccessor
    {

        public final int getPriority(Object obj)
        {
            return ((NameResolverProvider)obj).priority();
        }

        public final boolean isAvailable(Object obj)
        {
            return ((NameResolverProvider)obj).isAvailable();
        }

        _cls1()
        {
        }
    }

}
