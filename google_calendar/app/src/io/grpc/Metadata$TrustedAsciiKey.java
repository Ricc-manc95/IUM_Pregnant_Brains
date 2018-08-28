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
        return marshaller.parseAsciiString(abyte0);
    }

    final byte[] toBytes(Object obj)
    {
        return marshaller.toAsciiString(obj);
    }

    haller(String s, boolean flag, haller haller)
    {
        super(s, flag);
        boolean flag1;
        if (!s.endsWith("-bin"))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException(Strings.lenientFormat("ASCII header is named %s.  Only binary headers may end with %s", new Object[] {
                s, "-bin"
            }));
        }
        if (haller == null)
        {
            throw new NullPointerException(String.valueOf("marshaller"));
        } else
        {
            marshaller = (haller)haller;
            return;
        }
    }
}
