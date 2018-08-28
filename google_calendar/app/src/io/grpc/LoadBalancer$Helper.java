// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.List;

// Referenced classes of package io.grpc:
//            Attributes, ConnectivityState

public abstract class nel
{

    public nel createSubchannel(List list, Attributes attributes)
    {
        throw new UnsupportedOperationException();
    }

    public abstract void updateBalancingState(ConnectivityState connectivitystate, nelPicker nelpicker);

    public void updateSubchannelAddresses(nel nel, List list)
    {
        throw new UnsupportedOperationException();
    }

    public nel()
    {
    }
}
