// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Strings;
import com.google.common.base.VerifyException;
import io.grpc.Attributes;
import io.grpc.NameResolver;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            ServiceConfigUtil, JsonParser, SharedResourceHolder, ProxyDetector

final class DnsNameResolver extends NameResolver
{
    static interface AddressResolver
    {

        public abstract List resolveAddress(String s)
            throws Exception;
    }

    static final class JdkAddressResolver extends Enum
        implements AddressResolver
    {

        private static final JdkAddressResolver $VALUES[];
        public static final JdkAddressResolver INSTANCE;

        public static JdkAddressResolver[] values()
        {
            return (JdkAddressResolver[])$VALUES.clone();
        }

        public final List resolveAddress(String s)
            throws UnknownHostException
        {
            return Collections.unmodifiableList(Arrays.asList(InetAddress.getAllByName(s)));
        }

        static 
        {
            INSTANCE = new JdkAddressResolver("INSTANCE", 0);
            $VALUES = (new JdkAddressResolver[] {
                INSTANCE
            });
        }

        private JdkAddressResolver(String s, int i)
        {
            super(s, 0);
        }
    }

    static final class ResolutionResults
    {

        public final List addresses;
        public final List balancerAddresses;
        public final List txtRecords;

        ResolutionResults(List list, List list1, List list2)
        {
            if (list == null)
            {
                throw new NullPointerException(String.valueOf("addresses"));
            }
            addresses = Collections.unmodifiableList((List)list);
            if (list1 == null)
            {
                throw new NullPointerException(String.valueOf("txtRecords"));
            }
            txtRecords = Collections.unmodifiableList((List)list1);
            if (list2 == null)
            {
                throw new NullPointerException(String.valueOf("balancerAddresses"));
            } else
            {
                balancerAddresses = Collections.unmodifiableList((List)list2);
                return;
            }
        }
    }

    static interface ResourceResolver
    {

        public abstract List resolveSrv$5166IRPFCTP70OPFD5N78PBIDPGMOBQ4DPPKSOBDCL96ASRFDHR6ASH485I68SJ5EDPL4PBJDTM7CPBI7D66KOBMC4NMOOBECSNL6T3ID5N6EEP99HL62TJ15TQN8QBC5T66ISRK7C______0()
            throws Exception;

        public abstract List resolveTxt$5166KOBMC4NMOOBECSNL6T3ID5N6EEP99HL62TJ15TQN8QBC5T66ISRK7C______0()
            throws Exception;
    }

    static interface ResourceResolverFactory
    {

        public abstract ResourceResolver newResourceResolver();

        public abstract Throwable unavailabilityCause();
    }


    private static final String JNDI_PROPERTY;
    private static final Set SERVICE_CONFIG_CHOICE_KEYS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] {
        "clientLanguage", "percentage", "clientHostname", "serviceConfig"
    })));
    public static boolean enableJndi;
    private static String localHostname;
    public static final Logger logger = Logger.getLogger(io/grpc/internal/DnsNameResolver.getName());
    public static final ResourceResolverFactory resourceResolverFactory = getResourceResolverFactory(io/grpc/internal/DnsNameResolver.getClassLoader());
    public volatile AddressResolver addressResolver;
    private final String authority;
    private ExecutorService executor;
    private final SharedResourceHolder.Resource executorResource;
    public final String host;
    public io.grpc.NameResolver.Listener listener;
    public final int port;
    public final ProxyDetector proxyDetector;
    public final Random random;
    private final Runnable resolutionRunnable;
    public boolean resolving;
    public final AtomicReference resourceResolver;
    public boolean shutdown;

    DnsNameResolver(String s, Attributes attributes, SharedResourceHolder.Resource resource, ProxyDetector proxydetector)
    {
        random = new Random();
        addressResolver = JdkAddressResolver.INSTANCE;
        resourceResolver = new AtomicReference();
        resolutionRunnable = new _cls1();
        executorResource = resource;
        resource = String.valueOf(s);
        String s1;
        if (resource.length() != 0)
        {
            resource = "//".concat(resource);
        } else
        {
            resource = new String("//");
        }
        resource = URI.create(resource);
        s1 = resource.getAuthority();
        if (s1 == null)
        {
            throw new NullPointerException(Strings.lenientFormat("nameUri (%s) doesn't have an authority", new Object[] {
                resource
            }));
        }
        authority = (String)s1;
        s1 = resource.getHost();
        if (s1 == null)
        {
            throw new NullPointerException(String.valueOf("host"));
        }
        host = (String)s1;
        if (resource.getPort() != -1) goto _L2; else goto _L1
_L1:
        resource = io.grpc.NameResolver.Factory.PARAMS_DEFAULT_PORT;
        attributes = (Integer)attributes.data.get(resource);
        if (attributes == null) goto _L4; else goto _L3
_L3:
        port = attributes.intValue();
_L6:
        proxyDetector = proxydetector;
        return;
_L4:
        throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 69)).append("name '").append(s).append("' doesn't contain a port, and default port is not set in params").toString());
_L2:
        port = resource.getPort();
        if (true) goto _L6; else goto _L5
_L5:
    }

    static String getLocalHostname()
    {
        if (localHostname == null)
        {
            try
            {
                localHostname = InetAddress.getLocalHost().getHostName();
            }
            catch (UnknownHostException unknownhostexception)
            {
                throw new RuntimeException(unknownhostexception);
            }
        }
        return localHostname;
    }

    private static ResourceResolverFactory getResourceResolverFactory(ClassLoader classloader)
    {
        try
        {
            classloader = Class.forName("io.grpc.internal.JndiResourceResolverFactory", true, classloader).asSubclass(io/grpc/internal/DnsNameResolver$ResourceResolverFactory);
        }
        // Misplaced declaration of an exception variable
        catch (ClassLoader classloader)
        {
            logger.logp(Level.FINE, "io.grpc.internal.DnsNameResolver", "getResourceResolverFactory", "Unable to find JndiResourceResolverFactory, skipping.", classloader);
            return null;
        }
        try
        {
            classloader = classloader.getConstructor(new Class[0]);
        }
        // Misplaced declaration of an exception variable
        catch (ClassLoader classloader)
        {
            logger.logp(Level.FINE, "io.grpc.internal.DnsNameResolver", "getResourceResolverFactory", "Can't find JndiResourceResolverFactory ctor, skipping.", classloader);
            return null;
        }
        try
        {
            classloader = (ResourceResolverFactory)classloader.newInstance(new Object[0]);
        }
        // Misplaced declaration of an exception variable
        catch (ClassLoader classloader)
        {
            logger.logp(Level.FINE, "io.grpc.internal.DnsNameResolver", "getResourceResolverFactory", "Can't construct JndiResourceResolverFactory, skipping.", classloader);
            return null;
        }
        if (classloader.unavailabilityCause() != null)
        {
            logger.logp(Level.FINE, "io.grpc.internal.DnsNameResolver", "getResourceResolverFactory", "JndiResourceResolverFactory not available, skipping.", classloader.unavailabilityCause());
        }
        return classloader;
    }

    static Map maybeChooseServiceConfig(Map map, Random random1, String s)
    {
        boolean flag;
        boolean flag1;
        flag1 = true;
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            if (!SERVICE_CONFIG_CHOICE_KEYS.contains(entry.getKey()))
            {
                throw new VerifyException(Strings.lenientFormat("Bad key: %s", new Object[] {
                    entry
                }));
            }
        }

        Object obj;
        if (!map.containsKey("clientLanguage"))
        {
            obj = null;
        } else
        {
            obj = ServiceConfigUtil.checkStringList(ServiceConfigUtil.getList(map, "clientLanguage"));
        }
        if (obj == null || ((List) (obj)).isEmpty()) goto _L2; else goto _L1
_L1:
        obj = ((List) (obj)).iterator();
_L6:
        if (!((Iterator) (obj)).hasNext()) goto _L4; else goto _L3
_L3:
        if (!"java".equalsIgnoreCase((String)((Iterator) (obj)).next())) goto _L6; else goto _L5
_L5:
        flag = true;
_L15:
        if (!flag)
        {
            return null;
        }
_L2:
        Object obj1;
        if (!map.containsKey("percentage"))
        {
            obj1 = null;
        } else
        {
            obj1 = ServiceConfigUtil.getDouble(map, "percentage");
        }
        if (obj1 != null)
        {
            int i = ((Double) (obj1)).intValue();
            if (i >= 0 && i <= 100)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new VerifyException(Strings.lenientFormat("Bad percentage: %s", new Object[] {
                    obj1
                }));
            }
            if (random1.nextInt(100) >= i)
            {
                return null;
            }
        }
        if (!map.containsKey("clientHostname"))
        {
            random1 = null;
        } else
        {
            random1 = ServiceConfigUtil.checkStringList(ServiceConfigUtil.getList(map, "clientHostname"));
        }
        if (random1 == null || random1.isEmpty()) goto _L8; else goto _L7
_L7:
        random1 = random1.iterator();
_L12:
        if (!random1.hasNext()) goto _L10; else goto _L9
_L9:
        if (!((String)random1.next()).equals(s)) goto _L12; else goto _L11
_L11:
        flag = flag1;
_L13:
        if (!flag)
        {
            return null;
        }
_L8:
        return ServiceConfigUtil.getObject(map, "serviceConfig");
_L10:
        flag = false;
        if (true) goto _L13; else goto _L4
_L4:
        flag = false;
        if (true) goto _L15; else goto _L14
_L14:
    }

    static List parseTxtResults(List list)
    {
        ArrayList arraylist;
        Iterator iterator;
        arraylist = new ArrayList();
        iterator = list.iterator();
_L2:
        Object obj1;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        list = (String)iterator.next();
        if (!list.startsWith("_grpc_config="))
        {
            break MISSING_BLOCK_LABEL_260;
        }
        Object obj;
        try
        {
            obj = JsonParser.parse(list.substring(13));
            if (!(obj instanceof List))
            {
                obj = String.valueOf(obj);
                throw new IOException((new StringBuilder(String.valueOf(obj).length() + 11)).append("wrong type ").append(((String) (obj))).toString());
            }
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            obj1 = logger;
            Object obj2 = Level.WARNING;
            list = String.valueOf(list);
            if (list.length() != 0)
            {
                list = "Bad service config: ".concat(list);
            } else
            {
                list = new String("Bad service config: ");
            }
            ((Logger) (obj1)).logp(((Level) (obj2)), "io.grpc.internal.DnsNameResolver", "parseTxtResults", list, ((Throwable) (obj)));
            continue; /* Loop/switch isn't completed */
        }
        obj1 = (List)obj;
        for (obj2 = ((List) (obj1)).iterator(); ((Iterator) (obj2)).hasNext();)
        {
            if (!(((Iterator) (obj2)).next() instanceof Map))
            {
                obj = String.valueOf(obj);
                throw new IOException((new StringBuilder(String.valueOf(obj).length() + 19)).append("wrong element type ").append(((String) (obj))).toString());
            }
        }

        arraylist.addAll(((java.util.Collection) (obj1)));
        continue; /* Loop/switch isn't completed */
        logger.logp(Level.FINE, "io.grpc.internal.DnsNameResolver", "parseTxtResults", "Ignoring non service config {0}", new Object[] {
            list
        });
        if (true) goto _L2; else goto _L1
_L1:
        return arraylist;
    }

    static ResolutionResults resolveAll(AddressResolver addressresolver, ResourceResolver resourceresolver, String s)
    {
        Object obj;
        Object obj1;
        Object obj3;
        obj3 = Collections.emptyList();
        obj = Collections.emptyList();
        obj1 = Collections.emptyList();
        addressresolver = addressresolver.resolveAddress(s);
        Object obj2;
        obj2 = null;
        obj3 = addressresolver;
_L9:
        if (resourceresolver == null)
        {
            break MISSING_BLOCK_LABEL_363;
        }
        Object obj4;
        addressresolver = String.valueOf("_grpclb._tcp.");
        obj4 = String.valueOf(s);
        if (((String) (obj4)).length() == 0) goto _L2; else goto _L1
_L1:
        addressresolver.concat(((String) (obj4)));
_L10:
        addressresolver = resourceresolver._mth5166IRPFCTP70OPFD5N78PBIDPGMOBQ4DPPKSOBDCL96ASRFDHR6ASH485I68SJ5EDPL4PBJDTM7CPBI7D66KOBMC4NMOOBECSNL6T3ID5N6EEP99HL62TJ15TQN8QBC5T66ISRK7C______0();
        obj = null;
_L11:
        if (obj != null && obj2 != null) goto _L4; else goto _L3
_L3:
        obj4 = String.valueOf("_grpc_config.");
        s = String.valueOf(s);
        if (s.length() == 0) goto _L6; else goto _L5
_L5:
        ((String) (obj4)).concat(s);
_L12:
        s = resourceresolver._mth5166KOBMC4NMOOBECSNL6T3ID5N6EEP99HL62TJ15TQN8QBC5T66ISRK7C______0();
        resourceresolver = null;
        obj1 = s;
        s = ((String) (obj));
        obj = addressresolver;
        addressresolver = ((AddressResolver) (obj1));
_L13:
        if (obj2 == null || s == null) goto _L8; else goto _L7
_L7:
        throw new RuntimeException(((Throwable) (obj2)));
        addressresolver;
        if (obj2 != null)
        {
            logger.logp(Level.FINE, "io.grpc.internal.DnsNameResolver", "resolveAll", "Address resolution failure", ((Throwable) (obj2)));
        }
        if (s != null)
        {
            logger.logp(Level.FINE, "io.grpc.internal.DnsNameResolver", "resolveAll", "Balancer resolution failure", s);
        }
        if (resourceresolver != null)
        {
            logger.logp(Level.FINE, "io.grpc.internal.DnsNameResolver", "resolveAll", "ServiceConfig resolution failure", resourceresolver);
        }
        throw addressresolver;
        obj2;
          goto _L9
_L2:
        new String(addressresolver);
          goto _L10
        obj4;
        addressresolver = ((AddressResolver) (obj));
        obj = obj4;
          goto _L11
_L6:
        new String(((String) (obj4)));
          goto _L12
        resourceresolver;
        s = ((String) (obj));
        obj = addressresolver;
        addressresolver = ((AddressResolver) (obj1));
          goto _L13
_L8:
        if (obj2 != null)
        {
            logger.logp(Level.FINE, "io.grpc.internal.DnsNameResolver", "resolveAll", "Address resolution failure", ((Throwable) (obj2)));
        }
        if (s != null)
        {
            logger.logp(Level.FINE, "io.grpc.internal.DnsNameResolver", "resolveAll", "Balancer resolution failure", s);
        }
        if (resourceresolver != null)
        {
            logger.logp(Level.FINE, "io.grpc.internal.DnsNameResolver", "resolveAll", "ServiceConfig resolution failure", resourceresolver);
        }
        return new ResolutionResults(((List) (obj3)), addressresolver, ((List) (obj)));
_L4:
        resourceresolver = null;
        s = ((String) (obj));
        obj = addressresolver;
        addressresolver = ((AddressResolver) (obj1));
          goto _L13
        resourceresolver = null;
        addressresolver = ((AddressResolver) (obj1));
        s = null;
          goto _L13
    }

    public final String getServiceAuthority()
    {
        return authority;
    }

    public final void refresh()
    {
        this;
        JVM INSTR monitorenter ;
        Exception exception;
        boolean flag;
        if (listener != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_39;
        }
        throw new IllegalStateException(String.valueOf("not started"));
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        boolean flag1;
        if (resolving)
        {
            break MISSING_BLOCK_LABEL_55;
        }
        flag1 = shutdown;
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_58;
        }
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        executor.execute(resolutionRunnable);
          goto _L1
    }

    public final void shutdown()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = shutdown;
        if (!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        shutdown = true;
        if (executor != null)
        {
            SharedResourceHolder.Resource resource = executorResource;
            ExecutorService executorservice = executor;
            executor = (ExecutorService)SharedResourceHolder.holder.releaseInternal(resource, executorservice);
        }
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public final void start(io.grpc.NameResolver.Listener listener1)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        if (listener == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_39;
        }
        throw new IllegalStateException(String.valueOf("already started"));
        listener1;
        this;
        JVM INSTR monitorexit ;
        throw listener1;
        SharedResourceHolder.Resource resource = executorResource;
        executor = (ExecutorService)SharedResourceHolder.holder.getInternal(resource);
        if (listener1 != null)
        {
            break MISSING_BLOCK_LABEL_76;
        }
        throw new NullPointerException(String.valueOf("listener"));
        boolean flag1;
        listener = (io.grpc.NameResolver.Listener)listener1;
        if (resolving)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        flag1 = shutdown;
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_105;
        }
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        executor.execute(resolutionRunnable);
          goto _L1
    }

    static 
    {
        String s = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi", "false");
        JNDI_PROPERTY = s;
        enableJndi = Boolean.parseBoolean(s);
    }

    private class _cls1
        implements Runnable
    {

        private final DnsNameResolver this$0;

        public final void run()
        {
            Object obj4;
label0:
            {
                obj3 = null;
                obj4 = null;
                synchronized (DnsNameResolver.this)
                {
                    if (!shutdown)
                    {
                        break label0;
                    }
                }
                return;
            }
            io.grpc.NameResolver.Listener listener1;
            listener1 = listener;
            resolving = true;
            obj;
            JVM INSTR monitorexit ;
            obj = InetSocketAddress.createUnresolved(host, port);
            Object obj5 = proxyDetector.proxyFor(((java.net.SocketAddress) (obj)));
            if (obj5 == null)
            {
                break MISSING_BLOCK_LABEL_253;
            }
            listener1.onAddresses(Collections.singletonList(new EquivalentAddressGroup(new PairSocketAddress(((java.net.SocketAddress) (obj)), Attributes.newBuilder().set(ProxyDetector.PROXY_PARAMS_KEY, obj5).build()))), Attributes.EMPTY);
            synchronized (DnsNameResolver.this)
            {
                resolving = false;
            }
            return;
            obj3;
            obj;
            JVM INSTR monitorexit ;
            throw obj3;
            obj3;
            obj;
            JVM INSTR monitorexit ;
            throw obj3;
            obj3;
            obj4 = Status.UNAVAILABLE;
            String s = String.valueOf(host);
            if (s.length() == 0)
            {
                break MISSING_BLOCK_LABEL_220;
            }
            obj1 = "Unable to resolve host ".concat(s);
_L1:
            listener1.onError(((Status) (obj4)).withDescription(((String) (obj1))).withCause(((Throwable) (obj3))));
            synchronized (DnsNameResolver.this)
            {
                resolving = false;
            }
            return;
            obj3;
            obj1;
            JVM INSTR monitorexit ;
            throw obj3;
            obj1 = new String("Unable to resolve host ");
              goto _L1
            obj3;
            synchronized (DnsNameResolver.this)
            {
                resolving = false;
            }
            throw obj3;
            if (!DnsNameResolver.enableJndi)
            {
                break MISSING_BLOCK_LABEL_758;
            }
            obj5 = (ResourceResolver)resourceResolver.get();
            obj2 = obj5;
            if (obj5 != null)
            {
                break MISSING_BLOCK_LABEL_300;
            }
            obj2 = obj5;
            if (DnsNameResolver.resourceResolverFactory != null)
            {
                obj2 = DnsNameResolver.resourceResolverFactory.newResourceResolver();
            }
_L8:
            obj2 = DnsNameResolver.resolveAll(addressResolver, ((ResourceResolver) (obj2)), host);
            obj5 = new ArrayList();
            for (Iterator iterator = ((ResolutionResults) (obj2)).addresses.iterator(); iterator.hasNext(); ((List) (obj5)).add(new EquivalentAddressGroup(new InetSocketAddress((InetAddress)iterator.next(), port)))) { }
              goto _L2
            obj3;
            obj4 = Status.UNAVAILABLE;
            obj2 = String.valueOf(host);
            if (((String) (obj2)).length() == 0)
            {
                break MISSING_BLOCK_LABEL_460;
            }
            obj2 = "Unable to resolve host ".concat(((String) (obj2)));
_L3:
            listener1.onError(((Status) (obj4)).withDescription(((String) (obj2))).withCause(((Throwable) (obj3))));
            synchronized (DnsNameResolver.this)
            {
                resolving = false;
            }
            return;
            obj3;
            obj2;
            JVM INSTR monitorexit ;
            throw obj3;
            obj2 = new String("Unable to resolve host ");
              goto _L3
_L2:
            io.grpc.Attributes.Builder builder;
            boolean flag;
            ((List) (obj5)).addAll(((ResolutionResults) (obj2)).balancerAddresses);
            builder = Attributes.newBuilder();
            flag = ((ResolutionResults) (obj2)).txtRecords.isEmpty();
            if (flag)
            {
                break MISSING_BLOCK_LABEL_720;
            }
            Iterator iterator1 = DnsNameResolver.parseTxtResults(((ResolutionResults) (obj2)).txtRecords).iterator();
            obj2 = obj4;
_L5:
            obj3 = obj2;
            obj4 = obj2;
            if (!iterator1.hasNext())
            {
                break MISSING_BLOCK_LABEL_763;
            }
            obj3 = obj2;
            obj4 = (Map)iterator1.next();
            obj3 = DnsNameResolver.maybeChooseServiceConfig(((Map) (obj4)), random, DnsNameResolver.getLocalHostname());
            obj2 = obj3;
_L6:
            obj3 = obj2;
            if (obj2 != null)
            {
                break MISSING_BLOCK_LABEL_669;
            }
            if (true) goto _L5; else goto _L4
_L4:
            RuntimeException runtimeexception;
            runtimeexception;
            obj3 = obj2;
            Logger logger1 = DnsNameResolver.logger;
            obj3 = obj2;
            Level level = Level.WARNING;
            obj3 = obj2;
            obj4 = String.valueOf(obj4);
            obj3 = obj2;
            logger1.logp(level, "io.grpc.internal.DnsNameResolver$1", "run", (new StringBuilder(String.valueOf(obj4).length() + 26)).append("Bad service config choice ").append(((String) (obj4))).toString(), runtimeexception);
              goto _L6
            obj2;
            DnsNameResolver.logger.logp(Level.WARNING, "io.grpc.internal.DnsNameResolver$1", "run", "Can't parse service Configs", ((Throwable) (obj2)));
            obj4 = obj3;
            break MISSING_BLOCK_LABEL_763;
            for (; obj3 == null; obj3 = obj4)
            {
                break MISSING_BLOCK_LABEL_683;
            }

            builder.set(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG, obj3);
_L7:
            listener1.onAddresses(((List) (obj5)), builder.build());
            synchronized (DnsNameResolver.this)
            {
                resolving = false;
            }
            return;
            obj3;
            obj2;
            JVM INSTR monitorexit ;
            throw obj3;
            DnsNameResolver.logger.logp(Level.FINE, "io.grpc.internal.DnsNameResolver$1", "run", "No TXT records found for {0}", new Object[] {
                host
            });
              goto _L7
            obj3;
            obj2;
            JVM INSTR monitorexit ;
            throw obj3;
            obj2 = null;
              goto _L8
        }

        _cls1()
        {
            this$0 = DnsNameResolver.this;
            super();
        }
    }

}
