// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Referenced classes of package io.grpc.internal:
//            DnsNameResolver

static final class _cls9 extends Enum
    implements _cls9
{

    private static final INSTANCE $VALUES[];
    public static final INSTANCE INSTANCE;

    public static _cls9[] values()
    {
        return (_cls9[])$VALUES.clone();
    }

    public final List resolveAddress(String s)
        throws UnknownHostException
    {
        return Collections.unmodifiableList(Arrays.asList(InetAddress.getAllByName(s)));
    }

    static 
    {
        INSTANCE = new <init>("INSTANCE", 0);
        $VALUES = (new .VALUES[] {
            INSTANCE
        });
    }

    private _cls9(String s, int i)
    {
        super(s, 0);
    }
}
