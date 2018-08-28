// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.net.Authenticator;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            ProxyDetectorImpl

final class thenticationProvider
    implements thenticationProvider
{

    public final PasswordAuthentication requestPasswordAuthentication(String s, InetAddress inetaddress, int i, String s1, String s2, String s3)
    {
        try
        {
            s3 = new URL(s1, s, i, "");
        }
        // Misplaced declaration of an exception variable
        catch (String s3)
        {
            ProxyDetectorImpl.log.logp(Level.WARNING, "io.grpc.internal.ProxyDetectorImpl$1", "requestPasswordAuthentication", String.format("failed to create URL for Authenticator: %s %s", new Object[] {
                s1, s
            }));
            s3 = null;
        }
        return Authenticator.requestPasswordAuthentication(s, inetaddress, i, s1, s2, null, s3, java.net.torType.PROXY);
    }

    thenticationProvider()
    {
    }
}
