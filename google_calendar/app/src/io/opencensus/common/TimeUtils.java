// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.common;

import java.math.BigInteger;

final class TimeUtils
{

    static int compareLongs(long l, long l1)
    {
        if (l < l1)
        {
            return -1;
        }
        return l != l1 ? 1 : 0;
    }

    static 
    {
        BigInteger.valueOf(0x7fffffffffffffffL);
        BigInteger.valueOf(0x8000000000000000L);
    }
}
