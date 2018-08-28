// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.util;


public final class zzm
{

    public static String zza(byte abyte0[], int i, int j, boolean flag)
    {
        if (abyte0 == null || abyte0.length == 0 || j <= 0 || j + 0 > abyte0.length)
        {
            return null;
        }
        StringBuilder stringbuilder = new StringBuilder((((j + 16) - 1) / 16) * 57);
        int k = 0;
        int l = j;
        i = 0;
        while (l > 0) 
        {
            if (i == 0)
            {
                if (j < 0x10000)
                {
                    stringbuilder.append(String.format("%04X:", new Object[] {
                        Integer.valueOf(k)
                    }));
                } else
                {
                    stringbuilder.append(String.format("%08X:", new Object[] {
                        Integer.valueOf(k)
                    }));
                }
            } else
            if (i == 8)
            {
                stringbuilder.append(" -");
            }
            stringbuilder.append(String.format(" %02X", new Object[] {
                Integer.valueOf(abyte0[k] & 0xff)
            }));
            l--;
            i++;
            if (i == 16 || l == 0)
            {
                stringbuilder.append('\n');
                i = 0;
            }
            k++;
        }
        return stringbuilder.toString();
    }
}
