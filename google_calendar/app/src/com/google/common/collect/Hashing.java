// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


final class Hashing
{

    static int closedTableSize(int i, double d)
    {
label0:
        {
            int k = Math.max(i, 2);
            int j = Integer.highestOneBit(k);
            i = j;
            if (k > (int)((double)j * d))
            {
                i = j << 1;
                if (i <= 0)
                {
                    break label0;
                }
            }
            return i;
        }
        return 0x40000000;
    }
}
