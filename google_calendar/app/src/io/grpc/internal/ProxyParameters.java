// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.net.InetSocketAddress;
import java.util.Arrays;

public final class ProxyParameters
{

    public final String password;
    public final InetSocketAddress proxyAddress;
    public final String username;

    public ProxyParameters(InetSocketAddress inetsocketaddress, String s, String s1)
    {
        if (inetsocketaddress == null)
        {
            throw new NullPointerException();
        }
        boolean flag;
        if (!inetsocketaddress.isUnresolved())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            proxyAddress = inetsocketaddress;
            username = s;
            password = s1;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof ProxyParameters)
        {
            obj = (ProxyParameters)obj;
            InetSocketAddress inetsocketaddress = proxyAddress;
            InetSocketAddress inetsocketaddress1 = ((ProxyParameters) (obj)).proxyAddress;
            boolean flag;
            if (inetsocketaddress == inetsocketaddress1 || inetsocketaddress != null && inetsocketaddress.equals(inetsocketaddress1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                String s = username;
                String s2 = ((ProxyParameters) (obj)).username;
                if (s == s2 || s != null && s.equals(s2))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    String s1 = password;
                    obj = ((ProxyParameters) (obj)).password;
                    if (s1 == obj || s1 != null && s1.equals(obj))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            proxyAddress, username, password
        });
    }
}
