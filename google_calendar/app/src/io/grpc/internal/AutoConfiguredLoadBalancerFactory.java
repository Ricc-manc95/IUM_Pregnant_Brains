// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.LoadBalancer;

final class AutoConfiguredLoadBalancerFactory extends io.grpc.LoadBalancer.Factory
{

    AutoConfiguredLoadBalancerFactory()
    {
    }

    public final LoadBalancer newLoadBalancer(io.grpc.LoadBalancer.Helper helper)
    {
        return new AutoConfiguredLoadBalancer(helper);
    }

    private class AutoConfiguredLoadBalancer extends LoadBalancer
    {

        private LoadBalancer _flddelegate;
        private io.grpc.LoadBalancer.Factory delegateFactory;
        private final io.grpc.LoadBalancer.Helper helper;

        private static io.grpc.LoadBalancer.Factory decideLoadBalancerFactory(List list, Map map)
        {
            list = list.iterator();
_L4:
            if (!list.hasNext()) goto _L2; else goto _L1
_L1:
            Attributes attributes;
            io.grpc.Attributes.Key key;
            attributes = ((EquivalentAddressGroup)list.next()).attrs;
            key = GrpcAttributes.ATTR_LB_ADDR_AUTHORITY;
            if (attributes.data.get(key) == null) goto _L4; else goto _L3
_L3:
            boolean flag = true;
_L6:
            if (flag)
            {
                try
                {
                    list = (io.grpc.LoadBalancer.Factory)Class.forName("io.grpc.grpclb.GrpclbLoadBalancerFactory").getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                }
                // Misplaced declaration of an exception variable
                catch (List list)
                {
                    throw list;
                }
                // Misplaced declaration of an exception variable
                catch (List list)
                {
                    throw new RuntimeException("Can't get GRPCLB, but balancer addresses were present", list);
                }
                return list;
            }
            if (map != null)
            {
                list = ServiceConfigUtil.getLoadBalancingPolicyFromServiceConfig(map);
            } else
            {
                list = null;
            }
            if (list != null)
            {
                if (list.toUpperCase(Locale.ROOT).equals("ROUND_ROBIN"))
                {
                    try
                    {
                        list = (io.grpc.LoadBalancer.Factory)Class.forName("io.grpc.util.RoundRobinLoadBalancerFactory").getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                    }
                    // Misplaced declaration of an exception variable
                    catch (List list)
                    {
                        throw list;
                    }
                    // Misplaced declaration of an exception variable
                    catch (List list)
                    {
                        throw new RuntimeException("Can't get Round Robin LB", list);
                    }
                    return list;
                }
                list = String.valueOf(list);
                if (list.length() != 0)
                {
                    list = "Unknown service config policy: ".concat(list);
                } else
                {
                    list = new String("Unknown service config policy: ");
                }
                throw new IllegalArgumentException(list);
            } else
            {
                return PickFirstBalancerFactory.INSTANCE;
            }
_L2:
            flag = false;
            if (true) goto _L6; else goto _L5
_L5:
        }

        public final void handleNameResolutionError(Status status)
        {
            _flddelegate.handleNameResolutionError(status);
        }

        public final void handleResolvedAddressGroups(List list, Attributes attributes)
        {
            Object obj = GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG;
            obj = decideLoadBalancerFactory(list, (Map)attributes.data.get(obj));
            if (obj != null && obj != delegateFactory)
            {
                helper.updateBalancingState(ConnectivityState.CONNECTING, new EmptySubchannelPicker());
                _flddelegate.shutdown();
                delegateFactory = ((io.grpc.LoadBalancer.Factory) (obj));
                _flddelegate = delegateFactory.newLoadBalancer(helper);
            }
            _flddelegate.handleResolvedAddressGroups(list, attributes);
        }

        public final void handleSubchannelState(io.grpc.LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivitystateinfo)
        {
            _flddelegate.handleSubchannelState(subchannel, connectivitystateinfo);
        }

        public final void shutdown()
        {
            _flddelegate.shutdown();
            _flddelegate = null;
        }

        AutoConfiguredLoadBalancer(io.grpc.LoadBalancer.Helper helper1)
        {
            helper = helper1;
            delegateFactory = PickFirstBalancerFactory.INSTANCE;
            _flddelegate = delegateFactory.newLoadBalancer(helper1);
        }

        private class EmptySubchannelPicker extends io.grpc.LoadBalancer.SubchannelPicker
        {

            public final io.grpc.LoadBalancer.PickResult pickSubchannel$5166IRPFCTP70OPF9HNM2P22C5M62RJ3CLP28K39CDLL6TB2CDK62RJECLM42SJ7ECTIIJ39DSNMESJGCCNKORR1CH162R31DPHMASH4A1KM6QQICLPNAR3K7C______0()
            {
                return io.grpc.LoadBalancer.PickResult.NO_RESULT;
            }

            EmptySubchannelPicker()
            {
            }
        }

    }

}
