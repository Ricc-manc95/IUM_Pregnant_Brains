// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import com.google.common.base.Strings;

// Referenced classes of package io.grpc:
//            Metadata

static final class haller extends haller
{

    private final haller marshaller;

    final Object parseBytes(byte abyte0[])
    {
        return marshaller.parseBytes(abyte0);
    }

    final byte[] toBytes(Object obj)
    {
        return marshaller.toBytes(obj);
    }

    haller(String s, haller haller)
    {
        boolean flag = true;
        super(s, false);
        if (!s.endsWith("-bin"))
        {
            throw new IllegalArgumentException(Strings.lenientFormat("Binary header is named %s. It must end with %s", new Object[] {
                s, "-bin"
            }));
        }
        if (s.length() <= 4)
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("empty key name"));
        }
        if (haller == null)
        {
            throw new NullPointerException(String.valueOf("marshaller is null"));
        } else
        {
            marshaller = (haller)haller;
            return;
        }
    }
}
