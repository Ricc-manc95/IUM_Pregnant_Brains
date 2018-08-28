// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.Arrays;

final class zzcdo
{

    public final byte AM[];
    public final int tag;

    zzcdo(int i, byte abyte0[])
    {
        tag = i;
        AM = abyte0;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof zzcdo))
            {
                return false;
            }
            obj = (zzcdo)obj;
            if (tag != ((zzcdo) (obj)).tag || !Arrays.equals(AM, ((zzcdo) (obj)).AM))
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return (tag + 527) * 31 + Arrays.hashCode(AM);
    }
}
