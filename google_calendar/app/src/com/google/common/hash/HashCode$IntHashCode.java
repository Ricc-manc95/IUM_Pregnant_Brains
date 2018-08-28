// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.hash;

import java.io.Serializable;

// Referenced classes of package com.google.common.hash:
//            HashCode

final class hash extends HashCode
    implements Serializable
{

    public static final long serialVersionUID = 0L;
    private final int hash;

    public final byte[] asBytes()
    {
        return (new byte[] {
            (byte)hash, (byte)(hash >> 8), (byte)(hash >> 16), hash >> 24
        });
    }

    public final int asInt()
    {
        return hash;
    }

    public final int bits()
    {
        return 32;
    }

    final boolean equalsSameBits(HashCode hashcode)
    {
        return hash == hashcode.asInt();
    }

    (int i)
    {
        hash = i;
    }
}
