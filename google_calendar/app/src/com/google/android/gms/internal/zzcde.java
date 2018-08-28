// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

// Referenced classes of package com.google.android.gms.internal:
//            zzcdm

public final class zzcde
{

    public final ByteBuffer AB;

    private zzcde(ByteBuffer bytebuffer)
    {
        AB = bytebuffer;
        AB.order(ByteOrder.LITTLE_ENDIAN);
    }

    zzcde(byte abyte0[], int i, int j)
    {
        this(ByteBuffer.wrap(abyte0, i, j));
    }

    private final void zzAS(int i)
        throws IOException
    {
        byte byte0 = (byte)i;
        if (!AB.hasRemaining())
        {
            throw new zza(AB.position(), AB.limit());
        } else
        {
            AB.put(byte0);
            return;
        }
    }

    public static int zzAV(int i)
    {
        if ((i & 0xffffff80) == 0)
        {
            return 1;
        }
        if ((i & 0xffffc000) == 0)
        {
            return 2;
        }
        if ((0xffe00000 & i) == 0)
        {
            return 3;
        }
        return (0xf0000000 & i) != 0 ? 5 : 4;
    }

    private static void zza(CharSequence charsequence, ByteBuffer bytebuffer)
    {
        int i;
        int j;
        j = 0;
        i = 0;
        if (bytebuffer.isReadOnly())
        {
            throw new ReadOnlyBufferException();
        }
        if (!bytebuffer.hasArray()) goto _L2; else goto _L1
_L1:
        byte abyte0[];
        int k;
        int k1;
        abyte0 = bytebuffer.array();
        j = bytebuffer.arrayOffset() + bytebuffer.position();
        k = bytebuffer.remaining();
        k1 = charsequence.length();
        int l1 = j + k;
_L4:
        if (i >= k1 || i + j >= l1)
        {
            break; /* Loop/switch isn't completed */
        }
        k = charsequence.charAt(i);
        if (k >= '\200')
        {
            break; /* Loop/switch isn't completed */
        }
        abyte0[j + i] = (byte)k;
        i++;
        if (true) goto _L4; else goto _L3
_L6:
        if (i >= k1)
        {
            break MISSING_BLOCK_LABEL_604;
        }
        char c;
        c = charsequence.charAt(i);
        break MISSING_BLOCK_LABEL_150;
_L5:
        int i2;
        try
        {
            bytebuffer.position(i - bytebuffer.arrayOffset());
            return;
        }
        // Misplaced declaration of an exception variable
        catch (CharSequence charsequence)
        {
            bytebuffer = new BufferOverflowException();
        }
        bytebuffer.initCause(charsequence);
        throw bytebuffer;
        if (c < '\200' && j < l1)
        {
            k = j + 1;
            abyte0[j] = (byte)c;
            j = i;
            i = k;
            break MISSING_BLOCK_LABEL_943;
        }
        if (c < '\u0800' && j <= l1 - 2)
        {
            i2 = j + 1;
            abyte0[j] = (byte)(c >>> 6 | 0x3c0);
            k = i2 + 1;
            abyte0[i2] = (byte)(c & 0x3f | 0x80);
            j = i;
            i = k;
            break MISSING_BLOCK_LABEL_943;
        }
        if ((c < '\uD800' || '\uDFFF' < c) && j <= l1 - 3)
        {
            k = j + 1;
            byte byte0 = (byte)(c >>> 12 | 0x1e0);
            abyte0[j] = byte0;
            j = k + 1;
            abyte0[k] = (byte)(c >>> 6 & 0x3f | 0x80);
            k = j + 1;
            abyte0[j] = (byte)(c & 0x3f | 0x80);
            j = i;
            i = k;
            break MISSING_BLOCK_LABEL_943;
        }
        if (j > l1 - 4)
        {
            break MISSING_BLOCK_LABEL_565;
        }
        k = i;
        if (i + 1 == charsequence.length())
        {
            break MISSING_BLOCK_LABEL_424;
        }
        i++;
        char c2;
        c2 = charsequence.charAt(i);
        if (Character.isSurrogatePair(c, c2))
        {
            break MISSING_BLOCK_LABEL_456;
        }
        k = i;
        throw new IllegalArgumentException((new StringBuilder(39)).append("Unpaired surrogate at index ").append(k - 1).toString());
        int j2 = Character.toCodePoint(c, c2);
        int l = j + 1;
        abyte0[j] = (byte)(j2 >>> 18 | 0xf0);
        j = l + 1;
        abyte0[l] = (byte)(j2 >>> 12 & 0x3f | 0x80);
        int k2 = j + 1;
        abyte0[j] = (byte)(j2 >>> 6 & 0x3f | 0x80);
        l = k2 + 1;
        abyte0[k2] = (byte)(j2 & 0x3f | 0x80);
        j = i;
        i = l;
        break MISSING_BLOCK_LABEL_943;
        throw new ArrayIndexOutOfBoundsException((new StringBuilder(37)).append("Failed writing ").append(c).append(" at index ").append(j).toString());
        i = j;
          goto _L5
_L3:
        if (i != k1)
        {
            break MISSING_BLOCK_LABEL_933;
        }
        i = j + k1;
          goto _L5
_L2:
        int i1 = charsequence.length();
        i = j;
        while (i < i1) 
        {
            char c1 = charsequence.charAt(i);
            if (c1 < '\200')
            {
                bytebuffer.put((byte)c1);
            } else
            if (c1 < '\u0800')
            {
                bytebuffer.put((byte)(c1 >>> 6 | 0x3c0));
                bytebuffer.put((byte)(c1 & 0x3f | 0x80));
            } else
            if (c1 < '\uD800' || '\uDFFF' < c1)
            {
                bytebuffer.put((byte)(c1 >>> 12 | 0x1e0));
                bytebuffer.put((byte)(c1 >>> 6 & 0x3f | 0x80));
                bytebuffer.put((byte)(c1 & 0x3f | 0x80));
            } else
            {
                char c3;
label0:
                {
                    j = i;
                    if (i + 1 != charsequence.length())
                    {
                        i++;
                        c3 = charsequence.charAt(i);
                        if (Character.isSurrogatePair(c1, c3))
                        {
                            break label0;
                        }
                        j = i;
                    }
                    throw new IllegalArgumentException((new StringBuilder(39)).append("Unpaired surrogate at index ").append(j - 1).toString());
                }
                j = Character.toCodePoint(c1, c3);
                bytebuffer.put((byte)(j >>> 18 | 0xf0));
                bytebuffer.put((byte)(j >>> 12 & 0x3f | 0x80));
                bytebuffer.put((byte)(j >>> 6 & 0x3f | 0x80));
                bytebuffer.put((byte)(j & 0x3f | 0x80));
            }
            i++;
        }
        return;
        j += i;
          goto _L6
        int j1 = j + 1;
        j = i;
        i = j1;
          goto _L6
    }

    public static int zzaa(int i, int j)
    {
        int k = zzAV(0 | i << 3);
        if (j >= 0)
        {
            i = zzAV(j);
        } else
        {
            i = 10;
        }
        return i + k;
    }

    static int zzb(CharSequence charsequence)
    {
_L2:
        int i;
        if (j < i1)
        {
            char c = charsequence.charAt(j);
            if (c < '\u0800')
            {
                i += 127 - c >>> 31;
                j++;
                continue; /* Loop/switch isn't completed */
            }
            int k1 = charsequence.length();
            while (j < k1) 
            {
                char c1 = charsequence.charAt(j);
                int l;
                if (c1 < '\u0800')
                {
                    k += 127 - c1 >>> 31;
                    l = j;
                } else
                {
                    int j1 = k + 2;
                    l = j;
                    k = j1;
                    if ('\uD800' <= c1)
                    {
                        l = j;
                        k = j1;
                        if (c1 <= '\uDFFF')
                        {
                            if (Character.codePointAt(charsequence, j) < 0x10000)
                            {
                                throw new IllegalArgumentException((new StringBuilder(39)).append("Unpaired surrogate at index ").append(j).toString());
                            }
                            l = j + 1;
                            k = j1;
                        }
                    }
                }
                j = l + 1;
            }
            i += k;
        }
        if (i < i1)
        {
            long l1 = i;
            throw new IllegalArgumentException((new StringBuilder(54)).append("UTF-8 length does not fit in int: ").append(l1 + 0x100000000L).toString());
        } else
        {
            return i;
        }
        int k = 0;
        int i1 = charsequence.length();
        int j;
        for (j = 0; j < i1 && charsequence.charAt(j) < '\200'; j++) { }
        i = i1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static int zzbu(long l)
    {
        if ((-128L & l) == 0L)
        {
            return 1;
        }
        if ((-16384L & l) == 0L)
        {
            return 2;
        }
        if ((0xffffffffffe00000L & l) == 0L)
        {
            return 3;
        }
        if ((0xfffffffff0000000L & l) == 0L)
        {
            return 4;
        }
        if ((0xfffffff800000000L & l) == 0L)
        {
            return 5;
        }
        if ((0xfffffc0000000000L & l) == 0L)
        {
            return 6;
        }
        if ((0xfffe000000000000L & l) == 0L)
        {
            return 7;
        }
        if ((0xff00000000000000L & l) == 0L)
        {
            return 8;
        }
        return (0x8000000000000000L & l) != 0L ? 10 : 9;
    }

    public static int zzc(int i, zzcdm zzcdm1)
    {
        i = zzAV(0 | i << 3);
        int j = zzcdm1.computeSerializedSize();
        zzcdm1.AL = j;
        return i + (j + zzAV(j));
    }

    public static int zzl(int i, long l)
    {
        return zzAV(0 | i << 3) + zzbu(l << 1 ^ l >> 63);
    }

    public final void zzAU(int i)
        throws IOException
    {
        do
        {
            if ((i & 0xffffff80) == 0)
            {
                zzAS(i);
                return;
            }
            zzAS(i & 0x7f | 0x80);
            i >>>= 7;
        } while (true);
    }

    public final void zzY(int i, int j)
        throws IOException
    {
        zzAU(0 | i << 3);
        if (j >= 0)
        {
            zzAU(j);
            return;
        } else
        {
            zzbt(j);
            return;
        }
    }

    public final void zza(int i, zzcdm zzcdm1)
        throws IOException
    {
        zzAU(2 | i << 3);
        if (zzcdm1.AL < 0)
        {
            zzcdm1.AL = zzcdm1.computeSerializedSize();
        }
        zzAU(zzcdm1.AL);
        zzcdm1.writeTo(this);
    }

    public final void zzat(byte abyte0[])
        throws IOException
    {
        int i = abyte0.length;
        if (AB.remaining() >= i)
        {
            AB.put(abyte0, 0, i);
            return;
        } else
        {
            throw new zza(AB.position(), AB.limit());
        }
    }

    public final void zzb(int i, byte abyte0[])
        throws IOException
    {
        zzAU(2 | i << 3);
        zzAU(abyte0.length);
        i = abyte0.length;
        if (AB.remaining() >= i)
        {
            AB.put(abyte0, 0, i);
            return;
        } else
        {
            throw new zza(AB.position(), AB.limit());
        }
    }

    public final void zzbt(long l)
        throws IOException
    {
        do
        {
            if ((-128L & l) == 0L)
            {
                zzAS((int)l);
                return;
            }
            zzAS((int)l & 0x7f | 0x80);
            l >>>= 7;
        } while (true);
    }

    public final void zzf(int i, long l)
        throws IOException
    {
        zzAU(0 | i << 3);
        zzbt(l);
    }

    public final void zzh(int i, long l)
        throws IOException
    {
        zzAU(0 | i << 3);
        zzbt(l << 1 ^ l >> 63);
    }

    public final void zzj(int i, boolean flag)
        throws IOException
    {
        boolean flag1 = false;
        zzAU(i << 3 | 0);
        i = ((flag1) ? 1 : 0);
        if (flag)
        {
            i = 1;
        }
        byte byte0 = (byte)i;
        if (!AB.hasRemaining())
        {
            throw new zza(AB.position(), AB.limit());
        } else
        {
            AB.put(byte0);
            return;
        }
    }

    public final void zzmN(String s)
        throws IOException
    {
        zza zza1;
        int i;
        int j;
        i = zzAV(s.length());
        if (i != zzAV(s.length() * 3))
        {
            break MISSING_BLOCK_LABEL_152;
        }
        j = AB.position();
        if (AB.remaining() < i)
        {
            throw new zza(i + j, AB.limit());
        }
        try
        {
            AB.position(j + i);
            zza(s, AB);
            int k = AB.position();
            AB.position(j);
            zzAU(k - j - i);
            AB.position(k);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            zza1 = new zza(AB.position(), AB.limit());
            zza1.initCause(s);
            throw zza1;
        }
        zzAU(zzb(s));
        zza(s, AB);
        return;
    }

    public final void zzs(int i, String s)
        throws IOException
    {
        zzAU(2 | i << 3);
        zza zza1;
        int j;
        i = zzAV(s.length());
        if (i != zzAV(s.length() * 3))
        {
            break MISSING_BLOCK_LABEL_161;
        }
        j = AB.position();
        if (AB.remaining() < i)
        {
            throw new zza(i + j, AB.limit());
        }
        try
        {
            AB.position(j + i);
            zza(s, AB);
            int k = AB.position();
            AB.position(j);
            zzAU(k - j - i);
            AB.position(k);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            zza1 = new zza(AB.position(), AB.limit());
            zza1.initCause(s);
            throw zza1;
        }
        zzAU(zzb(s));
        zza(s, AB);
        return;
    }

    private class zza extends IOException
    {

        zza(int i, int j)
        {
            super((new StringBuilder(108)).append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ").append(i).append(" limit ").append(j).append(").").toString());
        }
    }

}
