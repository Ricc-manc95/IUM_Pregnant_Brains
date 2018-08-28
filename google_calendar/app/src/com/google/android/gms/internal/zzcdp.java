// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;

// Referenced classes of package com.google.android.gms.internal:
//            zzcdd

public final class zzcdp
{

    public static final int AN[] = new int[0];
    public static final byte AT[][] = new byte[0][];
    public static final byte AU[] = new byte[0];

    public static final int zzc(zzcdd zzcdd1, int i)
        throws IOException
    {
        int j = 1;
        int k = zzcdd1.Av;
        int l = zzcdd1.As;
        zzcdd1.zzAI(i);
        while (zzcdd1.zzakA() == i) 
        {
            zzcdd1.zzAI(i);
            j++;
        }
        zzcdd1.zzAM(k - l);
        return j;
    }

}
