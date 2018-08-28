// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.List;

// Referenced classes of package io.grpc:
//            LoadBalancer, ConnectivityState, Attributes, ConnectivityStateInfo, 
//            Status

final class helper extends LoadBalancer
{

    private final subchannel helper;
    private subchannel subchannel;

    public final void handleNameResolutionError(Status status)
    {
        if (subchannel != null)
        {
            subchannel.subchannel();
            subchannel = null;
        }
        helper.helper(ConnectivityState.TRANSIENT_FAILURE, new helper(helper(status)));
    }

    public final void handleResolvedAddressGroups(List list, Attributes attributes)
    {
        if (subchannel == null)
        {
            subchannel = helper.helper(list, Attributes.EMPTY);
            helper.helper(ConnectivityState.CONNECTING, new helper(helper(subchannel)));
            subchannel.subchannel();
            return;
        } else
        {
            helper.es(subchannel, list);
            return;
        }
    }

    public final void handleSubchannelState(subchannel subchannel1, ConnectivityStateInfo connectivitystateinfo)
    {
        ConnectivityState connectivitystate;
        connectivitystate = connectivitystateinfo.state;
        if (subchannel1 != subchannel || connectivitystate == ConnectivityState.SHUTDOWN)
        {
            return;
        }
        connectivitystate.ordinal();
        JVM INSTR tableswitch 0 3: default 56
    //                   0 98
    //                   1 119
    //                   2 127
    //                   3 119;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        subchannel1 = String.valueOf(connectivitystate);
        throw new IllegalArgumentException((new StringBuilder(String.valueOf(subchannel1).length() + 18)).append("Unsupported state:").append(subchannel1).toString());
_L2:
        subchannel1 = subchannel;
_L6:
        helper.helper(connectivitystate, new helper(subchannel1));
        return;
_L3:
        subchannel1 = helper(subchannel1);
        continue; /* Loop/switch isn't completed */
_L4:
        subchannel1 = helper(connectivitystateinfo.status);
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void shutdown()
    {
        if (subchannel != null)
        {
            subchannel.subchannel();
        }
    }

    ( )
    {
        if ( == null)
        {
            throw new NullPointerException(String.valueOf("helper"));
        } else
        {
            helper = (helper);
            return;
        }
    }
}
