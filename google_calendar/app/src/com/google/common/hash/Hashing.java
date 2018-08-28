// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.hash;


// Referenced classes of package com.google.common.hash:
//            Murmur3_32HashFunction, HashFunction

public final class Hashing
{

    public static final int GOOD_FAST_HASH_SEED = (int)System.currentTimeMillis();

    public static HashFunction murmur3_32()
    {
        return Murmur3_32HashFunction.MURMUR3_32;
    }

}
