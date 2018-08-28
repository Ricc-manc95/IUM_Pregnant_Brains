// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class zzyg
{

    private static void zza(byte abyte0[], int i, long l, long l1, long al[])
    {
        ByteBuffer bytebuffer = ByteBuffer.wrap(abyte0, i, 8);
        bytebuffer.order(ByteOrder.LITTLE_ENDIAN);
        long l5 = bytebuffer.getLong();
        bytebuffer = ByteBuffer.wrap(abyte0, i + 8, 8);
        bytebuffer.order(ByteOrder.LITTLE_ENDIAN);
        long l3 = bytebuffer.getLong();
        bytebuffer = ByteBuffer.wrap(abyte0, i + 16, 8);
        bytebuffer.order(ByteOrder.LITTLE_ENDIAN);
        long l4 = bytebuffer.getLong();
        abyte0 = ByteBuffer.wrap(abyte0, i + 24, 8);
        abyte0.order(ByteOrder.LITTLE_ENDIAN);
        long l2 = abyte0.getLong();
        l = l5 + l;
        l1 = Long.rotateRight(l1 + l + l2, 51);
        l3 = l3 + l + l4;
        l4 = Long.rotateRight(l3, 23);
        al[0] = l3 + l2;
        al[1] = l + (l4 + l1);
    }

    private static long zze(long l, long l1)
    {
        l1 = (l1 ^ l) * 0xc6a4a7935bd1e995L;
        l = (l1 ^ l1 >>> 47 ^ l) * 0xc6a4a7935bd1e995L;
        return (l ^ l >>> 47) * 0xc6a4a7935bd1e995L;
    }

    public static long zzo(byte abyte0[])
    {
        long l1;
        long l5;
        if (abyte0.length <= 32)
        {
            int i1 = abyte0.length & -8;
            int k1 = abyte0.length & 7;
            l1 = (long)abyte0.length * 0xc6a4a7935bd1e995L ^ 0xec9b9ee68cf8f8ddL;
            for (int i = 0; i < i1;)
            {
                ByteBuffer bytebuffer = ByteBuffer.wrap(abyte0, i, 8);
                bytebuffer.order(ByteOrder.LITTLE_ENDIAN);
                long l2 = bytebuffer.getLong() * 0xc6a4a7935bd1e995L;
                i += 8;
                l1 = 0xc6a4a7935bd1e995L * (l1 ^ (l2 ^ l2 >>> 47) * 0xc6a4a7935bd1e995L);
            }

            long l3 = l1;
            if (k1 != 0)
            {
                l3 = 0L;
                k1 = Math.min(k1, 8);
                for (int j = 0; j < k1; j++)
                {
                    l3 |= ((long)abyte0[i1 + j] & 255L) << (j << 3);
                }

                l3 = (l1 ^ l3) * 0xc6a4a7935bd1e995L;
            }
            l1 = (l3 ^ l3 >>> 47) * 0xc6a4a7935bd1e995L;
            l1 ^= l1 >>> 47;
        } else
        {
label0:
            {
                if (abyte0.length > 64)
                {
                    break label0;
                }
                int k = abyte0.length;
                ByteBuffer bytebuffer2 = ByteBuffer.wrap(abyte0, 24, 8);
                bytebuffer2.order(ByteOrder.LITTLE_ENDIAN);
                l1 = bytebuffer2.getLong();
                bytebuffer2 = ByteBuffer.wrap(abyte0, 0, 8);
                bytebuffer2.order(ByteOrder.LITTLE_ENDIAN);
                l4 = bytebuffer2.getLong();
                l5 = k;
                bytebuffer2 = ByteBuffer.wrap(abyte0, k - 16, 8);
                bytebuffer2.order(ByteOrder.LITTLE_ENDIAN);
                l4 += (l5 + bytebuffer2.getLong()) * 0xa5b85c5e198ed849L;
                l5 = Long.rotateRight(l4 + l1, 52);
                long l6 = Long.rotateRight(l4, 37);
                bytebuffer2 = ByteBuffer.wrap(abyte0, 8, 8);
                bytebuffer2.order(ByteOrder.LITTLE_ENDIAN);
                l4 += bytebuffer2.getLong();
                long l8 = Long.rotateRight(l4, 7);
                bytebuffer2 = ByteBuffer.wrap(abyte0, 16, 8);
                bytebuffer2.order(ByteOrder.LITTLE_ENDIAN);
                l4 += bytebuffer2.getLong();
                l5 = Long.rotateRight(l4, 31) + l5 + (l6 + l8);
                bytebuffer2 = ByteBuffer.wrap(abyte0, 16, 8);
                bytebuffer2.order(ByteOrder.LITTLE_ENDIAN);
                l6 = bytebuffer2.getLong();
                bytebuffer2 = ByteBuffer.wrap(abyte0, k - 32, 8);
                bytebuffer2.order(ByteOrder.LITTLE_ENDIAN);
                long l10 = l6 + bytebuffer2.getLong();
                bytebuffer2 = ByteBuffer.wrap(abyte0, k - 8, 8);
                bytebuffer2.order(ByteOrder.LITTLE_ENDIAN);
                l6 = bytebuffer2.getLong();
                l8 = Long.rotateRight(l10 + l6, 52);
                long l9 = Long.rotateRight(l10, 37);
                bytebuffer2 = ByteBuffer.wrap(abyte0, k - 24, 8);
                bytebuffer2.order(ByteOrder.LITTLE_ENDIAN);
                long l11 = l10 + bytebuffer2.getLong();
                l10 = Long.rotateRight(l11, 7);
                bytebuffer2 = ByteBuffer.wrap(abyte0, k - 16, 8);
                bytebuffer2.order(ByteOrder.LITTLE_ENDIAN);
                l11 = bytebuffer2.getLong() + l11;
                l1 = (Long.rotateRight(l11, 31) + l8 + (l9 + l10) + (l1 + l4)) * 0xc47b6e9e3a970ed3L + (l11 + l6 + l5) * 0xa5b85c5e198ed849L;
                l1 = (l1 ^ l1 >>> 47) * 0xa5b85c5e198ed849L + l5;
                l1 = (l1 ^ l1 >>> 47) * 0xc47b6e9e3a970ed3L;
            }
        }
_L1:
        long l7;
label1:
        {
            ByteBuffer bytebuffer3;
            long al[];
            long al1[];
            ByteBuffer bytebuffer4;
            int l;
            int j1;
            long l4;
            if (abyte0.length >= 8)
            {
                ByteBuffer bytebuffer1 = ByteBuffer.wrap(abyte0, 0, 8);
                bytebuffer1.order(ByteOrder.LITTLE_ENDIAN);
                l4 = bytebuffer1.getLong();
            } else
            {
                l4 = 0xa5b85c5e198ed849L;
            }
            if (abyte0.length >= 9)
            {
                abyte0 = ByteBuffer.wrap(abyte0, abyte0.length - 8, 8);
                abyte0.order(ByteOrder.LITTLE_ENDIAN);
                l5 = abyte0.getLong();
            } else
            {
                l5 = 0xa5b85c5e198ed849L;
            }
            l4 = zze(l1 + l5, l4);
            if (l4 != 0L)
            {
                l1 = l4;
                if (l4 != 1L)
                {
                    break label1;
                }
            }
            l1 = l4 - 2L;
        }
        return l1;
        l = abyte0.length;
        bytebuffer3 = ByteBuffer.wrap(abyte0, 0, 8);
        bytebuffer3.order(ByteOrder.LITTLE_ENDIAN);
        l5 = bytebuffer3.getLong();
        bytebuffer3 = ByteBuffer.wrap(abyte0, l - 16, 8);
        bytebuffer3.order(ByteOrder.LITTLE_ENDIAN);
        l4 = bytebuffer3.getLong() ^ 0x8d58ac26afe12e47L;
        bytebuffer3 = ByteBuffer.wrap(abyte0, l - 56, 8);
        bytebuffer3.order(ByteOrder.LITTLE_ENDIAN);
        l1 = bytebuffer3.getLong();
        al = new long[2];
        al1 = new long[2];
        zza(abyte0, l - 64, l, l4, al);
        zza(abyte0, l - 32, (long)l * 0x8d58ac26afe12e47L, 0xa5b85c5e198ed849L, al1);
        l7 = al[1];
        l1 = (l1 ^ 0xa5b85c5e198ed849L) + (l7 ^ l7 >>> 47) * 0x8d58ac26afe12e47L;
        l5 = Long.rotateRight(l1 + l5, 39);
        l4 = Long.rotateRight(l4, 33) * 0x8d58ac26afe12e47L;
        j1 = 0;
        l5 = 0x8d58ac26afe12e47L * l5;
        l = l - 1 & 0xffffffc0;
_L2:
label2:
        {
            l7 = al[0];
            bytebuffer4 = ByteBuffer.wrap(abyte0, j1 + 16, 8);
            bytebuffer4.order(ByteOrder.LITTLE_ENDIAN);
            l5 = Long.rotateRight(l5 + l4 + l7 + bytebuffer4.getLong(), 37);
            l7 = al[1];
            bytebuffer4 = ByteBuffer.wrap(abyte0, j1 + 48, 8);
            bytebuffer4.order(ByteOrder.LITTLE_ENDIAN);
            l4 = Long.rotateRight(l7 + l4 + bytebuffer4.getLong(), 42);
            l5 = l5 * 0x8d58ac26afe12e47L ^ al1[1];
            l4 = l4 * 0x8d58ac26afe12e47L ^ al[0];
            l7 = Long.rotateRight(l1 ^ al1[0], 33);
            zza(abyte0, j1, al[1] * 0x8d58ac26afe12e47L, al1[0] + l5, al);
            zza(abyte0, j1 + 32, l7 + al1[1], l4, al1);
            j1 += 64;
            l -= 64;
            if (l != 0)
            {
                break label2;
            }
            l1 = zze(zze(al[0], al1[0]) + (l4 >>> 47 ^ l4) * 0x8d58ac26afe12e47L + l5, zze(al[1], al1[1]) + l7);
        }
          goto _L1
        l1 = l5;
        l5 = l7;
          goto _L2
    }
}
