// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.List;

// Referenced classes of package io.grpc:
//            Status, Attributes, ConnectivityStateInfo

public abstract class LoadBalancer
{

    public LoadBalancer()
    {
    }

    public abstract void handleNameResolutionError(Status status);

    public abstract void handleResolvedAddressGroups(List list, Attributes attributes);

    public abstract void handleSubchannelState(Subchannel subchannel, ConnectivityStateInfo connectivitystateinfo);

    public abstract void shutdown();
}
