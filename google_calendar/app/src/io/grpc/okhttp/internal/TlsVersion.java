// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal;


public final class TlsVersion extends Enum
{

    private static final TlsVersion $VALUES[];
    private static final TlsVersion SSL_3_0;
    public static final TlsVersion TLS_1_0;
    public static final TlsVersion TLS_1_1;
    public static final TlsVersion TLS_1_2;
    public final String javaName;

    private TlsVersion(String s, int i, String s1)
    {
        super(s, i);
        javaName = s1;
    }

    public static TlsVersion forJavaName(String s)
    {
        if ("TLSv1.2".equals(s))
        {
            return TLS_1_2;
        }
        if ("TLSv1.1".equals(s))
        {
            return TLS_1_1;
        }
        if ("TLSv1".equals(s))
        {
            return TLS_1_0;
        }
        if ("SSLv3".equals(s))
        {
            return SSL_3_0;
        }
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            s = "Unexpected TLS version: ".concat(s);
        } else
        {
            s = new String("Unexpected TLS version: ");
        }
        throw new IllegalArgumentException(s);
    }

    public static TlsVersion[] values()
    {
        return (TlsVersion[])$VALUES.clone();
    }

    static 
    {
        TLS_1_2 = new TlsVersion("TLS_1_2", 0, "TLSv1.2");
        TLS_1_1 = new TlsVersion("TLS_1_1", 1, "TLSv1.1");
        TLS_1_0 = new TlsVersion("TLS_1_0", 2, "TLSv1");
        SSL_3_0 = new TlsVersion("SSL_3_0", 3, "SSLv3");
        $VALUES = (new TlsVersion[] {
            TLS_1_2, TLS_1_1, TLS_1_0, SSL_3_0
        });
    }
}
