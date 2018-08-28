// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal;


// Referenced classes of package io.grpc.okhttp.internal:
//            ConnectionSpec, CipherSuite, TlsVersion

public final class tls
{

    public String cipherSuites[];
    public boolean supportsTlsExtensions;
    public boolean tls;
    public String tlsVersions[];

    public final transient tls cipherSuites(CipherSuite aciphersuite[])
    {
        if (!tls)
        {
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }
        String as[] = new String[aciphersuite.length];
        for (int i = 0; i < aciphersuite.length; i++)
        {
            as[i] = aciphersuite[i].javaName;
        }

        cipherSuites = as;
        return this;
    }

    public final transient cipherSuites cipherSuites(String as[])
    {
        if (!tls)
        {
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }
        if (as == null)
        {
            cipherSuites = null;
            return this;
        } else
        {
            cipherSuites = (String[])as.clone();
            return this;
        }
    }

    public final transient cipherSuites tlsVersions(TlsVersion atlsversion[])
    {
        if (!tls)
        {
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }
        if (atlsversion.length == 0)
        {
            throw new IllegalArgumentException("At least one TlsVersion is required");
        }
        String as[] = new String[atlsversion.length];
        for (int i = 0; i < atlsversion.length; i++)
        {
            as[i] = atlsversion[i].javaName;
        }

        tlsVersions = as;
        return this;
    }

    public final transient tlsVersions tlsVersions(String as[])
    {
        if (!tls)
        {
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }
        if (as == null)
        {
            tlsVersions = null;
            return this;
        } else
        {
            tlsVersions = (String[])as.clone();
            return this;
        }
    }

    public (ConnectionSpec connectionspec)
    {
        tls = connectionspec.tls;
        cipherSuites = connectionspec.cipherSuites;
        tlsVersions = connectionspec.tlsVersions;
        supportsTlsExtensions = connectionspec.supportsTlsExtensions;
    }

    public TlsExtensions(boolean flag)
    {
        tls = flag;
    }
}
