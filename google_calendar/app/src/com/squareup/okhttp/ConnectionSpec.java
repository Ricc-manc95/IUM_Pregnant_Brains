// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.util.Arrays;

// Referenced classes of package com.squareup.okhttp:
//            CipherSuite, TlsVersion

public final class ConnectionSpec
{

    private static final CipherSuite APPROVED_CIPHER_SUITES[];
    public static final ConnectionSpec MODERN_TLS;
    public final String cipherSuites[];
    public final boolean supportsTlsExtensions;
    public final boolean tls;
    public final String tlsVersions[];

    public ConnectionSpec(Builder builder)
    {
        tls = builder.tls;
        cipherSuites = builder.cipherSuites;
        tlsVersions = builder.tlsVersions;
        supportsTlsExtensions = builder.supportsTlsExtensions;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof ConnectionSpec)
        {
            if (obj == this)
            {
                return true;
            }
            obj = (ConnectionSpec)obj;
            if (tls == ((ConnectionSpec) (obj)).tls && (!tls || Arrays.equals(cipherSuites, ((ConnectionSpec) (obj)).cipherSuites) && Arrays.equals(tlsVersions, ((ConnectionSpec) (obj)).tlsVersions) && supportsTlsExtensions == ((ConnectionSpec) (obj)).supportsTlsExtensions))
            {
                return true;
            }
        }
        return false;
    }

    public final int hashCode()
    {
        int i = 17;
        if (tls)
        {
            int j = Arrays.hashCode(cipherSuites);
            int k = Arrays.hashCode(tlsVersions);
            if (supportsTlsExtensions)
            {
                i = 0;
            } else
            {
                i = 1;
            }
            i += ((j + 527) * 31 + k) * 31;
        }
        return i;
    }

    public final String toString()
    {
        Object obj1 = null;
        boolean flag = false;
        if (!tls)
        {
            return "ConnectionSpec()";
        }
        Object obj;
        if (cipherSuites != null)
        {
            if (cipherSuites == null)
            {
                obj = null;
            } else
            {
                obj = new CipherSuite[cipherSuites.length];
                for (int i = 0; i < cipherSuites.length; i++)
                {
                    obj[i] = CipherSuite.forJavaName(cipherSuites[i]);
                }

                obj = Util.immutableList(((Object []) (obj)));
            }
            obj = obj.toString();
        } else
        {
            obj = "[all enabled]";
        }
        if (tlsVersions != null)
        {
            if (tlsVersions != null)
            {
                obj1 = new TlsVersion[tlsVersions.length];
                for (int j = ((flag) ? 1 : 0); j < tlsVersions.length; j++)
                {
                    obj1[j] = TlsVersion.forJavaName(tlsVersions[j]);
                }

                obj1 = Util.immutableList(((Object []) (obj1)));
            }
            obj1 = obj1.toString();
        } else
        {
            obj1 = "[all enabled]";
        }
        return (new StringBuilder("ConnectionSpec(cipherSuites=")).append(((String) (obj))).append(", tlsVersions=").append(((String) (obj1))).append(", supportsTlsExtensions=").append(supportsTlsExtensions).append(")").toString();
    }

    static 
    {
        APPROVED_CIPHER_SUITES = (new CipherSuite[] {
            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, 
            CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA
        });
        Builder builder = (true. new Builder()).cipherSuites(APPROVED_CIPHER_SUITES).tlsVersions(new TlsVersion[] {
            TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0
        });
        if (!builder.tls)
        {
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }
        builder.supportsTlsExtensions = true;
        MODERN_TLS = new ConnectionSpec(builder);
        builder = (MODERN_TLS. new Builder()).tlsVersions(new TlsVersion[] {
            TlsVersion.TLS_1_0
        });
        if (!builder.tls)
        {
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        } else
        {
            builder.supportsTlsExtensions = true;
            new ConnectionSpec(builder);
            new ConnectionSpec(false. new Builder());
        }
    }

    private class Builder
    {

        public String cipherSuites[];
        public boolean supportsTlsExtensions;
        public boolean tls;
        public String tlsVersions[];

        public final transient Builder cipherSuites(CipherSuite aciphersuite[])
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

            if (!tls)
            {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            if (as.length == 0)
            {
                throw new IllegalArgumentException("At least one cipher suite is required");
            } else
            {
                cipherSuites = (String[])as.clone();
                return this;
            }
        }

        public final transient Builder tlsVersions(TlsVersion atlsversion[])
        {
            if (!tls)
            {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            String as[] = new String[atlsversion.length];
            for (int i = 0; i < atlsversion.length; i++)
            {
                as[i] = atlsversion[i].javaName;
            }

            if (!tls)
            {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            if (as.length == 0)
            {
                throw new IllegalArgumentException("At least one TLS version is required");
            } else
            {
                tlsVersions = (String[])as.clone();
                return this;
            }
        }

        public Builder()
        {
            tls = ConnectionSpec.this.tls;
            cipherSuites = ConnectionSpec.this.cipherSuites;
            tlsVersions = ConnectionSpec.this.tlsVersions;
            supportsTlsExtensions = ConnectionSpec.this.supportsTlsExtensions;
        }

        Builder()
        {
            tls = Z.this;
        }
    }

}
