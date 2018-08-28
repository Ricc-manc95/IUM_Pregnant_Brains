// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;


public final class zzaqh
{

    public static final ThreadLocal zzbjL = new ThreadLocal();

    public static String zzR(String s, String s1)
    {
        byte abyte0[];
        int i;
        int j;
        int l;
        int i1;
        int j1;
        l = 0;
        boolean flag = false;
        if (s == null || s1 == null)
        {
            return s;
        }
        abyte0 = new byte[s.length() + s1.length()];
        System.arraycopy(s.getBytes(), 0, abyte0, 0, s.length());
        System.arraycopy(s1.getBytes(), 0, abyte0, s.length(), s1.length());
        i1 = abyte0.length;
        j1 = (i1 & -4) + 0;
        j = 0;
        for (i = 0; j < j1; i = 0xe6546b64 + (i >>> 19 | i << 13) * 5)
        {
            int k1 = (abyte0[j] & 0xff | (abyte0[j + 1] & 0xff) << 8 | (abyte0[j + 2] & 0xff) << 16 | abyte0[j + 3] << 24) * 0xcc9e2d51;
            i ^= (k1 >>> 17 | k1 << 15) * 0x1b873593;
            j += 4;
        }

        j = ((flag) ? 1 : 0);
        i1 & 3;
        JVM INSTR tableswitch 1 3: default 212
    //                   1 280
    //                   2 262
    //                   3 247;
           goto _L1 _L2 _L3 _L4
_L1:
        i ^= i1;
        i = (i ^ i >>> 16) * 0x85ebca6b;
        i = (i ^ i >>> 13) * 0xc2b2ae35;
        return Integer.toHexString(i ^ i >>> 16);
_L4:
        j = (abyte0[j1 + 2] & 0xff) << 16;
_L3:
        l = j | (abyte0[j1 + 1] & 0xff) << 8;
_L2:
        int k = (l | abyte0[j1] & 0xff) * 0xcc9e2d51;
        i = (k >>> 17 | k << 15) * 0x1b873593 ^ i;
        if (true) goto _L1; else goto _L5
_L5:
    }

}
