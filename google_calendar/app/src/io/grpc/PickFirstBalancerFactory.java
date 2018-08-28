// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            LoadBalancer

public final class PickFirstBalancerFactory extends LoadBalancer.Factory
{

    public static final PickFirstBalancerFactory INSTANCE = new PickFirstBalancerFactory();

    private PickFirstBalancerFactory()
    {
    }

    public final LoadBalancer newLoadBalancer(LoadBalancer.Helper helper)
    {
        return new PickFirstBalancer(helper);
    }


    private class PickFirstBalancer extends LoadBalancer
    {

        private final LoadBalancer.Helper helper;
        private LoadBalancer.Subchannel subchannel;

        public final void handleNameResolutionError(Status status)
        {
            if (subchannel != null)
            {
                subchannel.shutdown();
                subchannel = null;
            }
            helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new Picker(LoadBalancer.PickResult.withError(status)));
        }

        public final void handleResolvedAddressGroups(List list, Attributes attributes)
        {
            if (subchannel == null)
            {
                subchannel = helper.createSubchannel(list, Attributes.EMPTY);
                helper.updateBalancingState(ConnectivityState.CONNECTING, new Picker(LoadBalancer.PickResult.withSubchannel(subchannel)));
                subchannel.requestConnection();
                return;
            } else
            {
                helper.updateSubchannelAddresses(subchannel, list);
                return;
            }
        }

        public final void handleSubchannelState(LoadBalancer.Subchannel subchannel1, ConnectivityStateInfo connectivitystateinfo)
        {
            ConnectivityState connectivitystate;
            connectivitystate = connectivitystateinfo.state;
            if (subchannel1 != subchannel || connectivitystate == ConnectivityState.SHUTDOWN)
            {
                return;
            }
            connectivitystate.ordinal();
            JVM INSTR tableswitch 0 3: default 56
        //                       0 98
        //                       1 119
        //                       2 127
        //                       3 119;
               goto _L1 _L2 _L3 _L4 _L3
_L1:
            subchannel1 = String.valueOf(connectivitystate);
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(subchannel1).length() + 18)).append("Unsupported state:").append(subchannel1).toString());
_L2:
            subchannel1 = LoadBalancer.PickResult.NO_RESULT;
_L6:
            helper.updateBalancingState(connectivitystate, new Picker(subchannel1));
            return;
_L3:
            subchannel1 = LoadBalancer.PickResult.withSubchannel(subchannel1);
            continue; /* Loop/switch isn't completed */
_L4:
            subchannel1 = LoadBalancer.PickResult.withError(connectivitystateinfo.status);
            if (true) goto _L6; else goto _L5
_L5:
        }

        public final void shutdown()
        {
            if (subchannel != null)
            {
                subchannel.shutdown();
            }
        }

        PickFirstBalancer(LoadBalancer.Helper helper1)
        {
            if (helper1 == null)
            {
                throw new NullPointerException(String.valueOf("helper"));
            } else
            {
                helper = (LoadBalancer.Helper)helper1;
                return;
            }
        }

        private class Picker extends LoadBalancer.SubchannelPicker
        {

            private final LoadBalancer.PickResult result;

            public final LoadBalancer.PickResult pickSubchannel$5166IRPFCTP70OPF9HNM2P22C5M62RJ3CLP28K39CDLL6TB2CDK62RJECLM42SJ7ECTIIJ39DSNMESJGCCNKORR1CH162R31DPHMASH4A1KM6QQICLPNAR3K7C______0()
            {
                return result;
            }

            Picker(LoadBalancer.PickResult pickresult)
            {
                if (pickresult == null)
                {
                    throw new NullPointerException(String.valueOf("result"));
                } else
                {
                    result = (LoadBalancer.PickResult)pickresult;
                    return;
                }
            }
        }

    }

}
