// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Attributes;
import java.net.SocketAddress;

final class PairSocketAddress extends SocketAddress
{

    public static final long serialVersionUID = 0xa0de2cd3ce22b807L;
    public final SocketAddress address;
    public final Attributes attributes;

    PairSocketAddress(SocketAddress socketaddress, Attributes attributes1)
    {
        if (socketaddress == null)
        {
            throw new NullPointerException();
        }
        address = (SocketAddress)socketaddress;
        if (attributes1 == null)
        {
            throw new NullPointerException();
        } else
        {
            attributes = (Attributes)attributes1;
            return;
        }
    }
}
