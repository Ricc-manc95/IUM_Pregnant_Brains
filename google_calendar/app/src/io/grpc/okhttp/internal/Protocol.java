// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal;

import java.io.IOException;

public final class Protocol extends Enum
{

    private static final Protocol $VALUES[];
    public static final Protocol GRPC_EXP;
    public static final Protocol HTTP_1_0;
    private static final Protocol HTTP_1_1;
    public static final Protocol HTTP_2;
    private static final Protocol SPDY_3;
    private final String protocol;

    private Protocol(String s, int i, String s1)
    {
        super(s, i);
        protocol = s1;
    }

    public static Protocol get(String s)
        throws IOException
    {
        if (s.equals(HTTP_1_0.protocol))
        {
            return HTTP_1_0;
        }
        if (s.equals(HTTP_1_1.protocol))
        {
            return HTTP_1_1;
        }
        if (s.equals(HTTP_2.protocol))
        {
            return HTTP_2;
        }
        if (s.equals(GRPC_EXP.protocol))
        {
            return GRPC_EXP;
        }
        if (s.equals(SPDY_3.protocol))
        {
            return SPDY_3;
        }
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            s = "Unexpected protocol: ".concat(s);
        } else
        {
            s = new String("Unexpected protocol: ");
        }
        throw new IOException(s);
    }

    public static Protocol[] values()
    {
        return (Protocol[])$VALUES.clone();
    }

    public final String toString()
    {
        return protocol;
    }

    static 
    {
        HTTP_1_0 = new Protocol("HTTP_1_0", 0, "http/1.0");
        HTTP_1_1 = new Protocol("HTTP_1_1", 1, "http/1.1");
        SPDY_3 = new Protocol("SPDY_3", 2, "spdy/3.1");
        HTTP_2 = new Protocol("HTTP_2", 3, "h2");
        GRPC_EXP = new Protocol("GRPC_EXP", 4, "grpc-exp");
        $VALUES = (new Protocol[] {
            HTTP_1_0, HTTP_1_1, SPDY_3, HTTP_2, GRPC_EXP
        });
    }
}
