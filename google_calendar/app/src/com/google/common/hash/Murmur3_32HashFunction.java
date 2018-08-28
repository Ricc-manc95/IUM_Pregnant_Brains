// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.hash;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.nio.charset.Charset;

// Referenced classes of package com.google.common.hash:
//            AbstractHashFunction, Hashing, HashCode, HashFunction, 
//            Hasher

final class Murmur3_32HashFunction extends AbstractHashFunction
    implements Serializable
{

    public static final HashFunction MURMUR3_32 = new Murmur3_32HashFunction(0);
    public static final long serialVersionUID = 0L;
    private final int seed;

    private Murmur3_32HashFunction(int i)
    {
        seed = i;
    }

    static long charToThreeUtf8Bytes(char c)
    {
        return (long)((c >>> 12 | 0x1e0) & 0xff | (c >>> 6 & 0x3f | 0x80) << 8 | (c & 0x3f | 0x80) << 16);
    }

    static long charToTwoUtf8Bytes(char c)
    {
        return (long)((c >>> 6 | 0x3c0) & 0xff | (c & 0x3f | 0x80) << 8);
    }

    static long codePointToFourUtf8Bytes(int i)
    {
        return (240L | (long)(i >>> 18)) & 255L | ((long)(i >>> 12 & 0x3f) | 128L) << 8 | ((long)(i >>> 6 & 0x3f) | 128L) << 16 | ((long)(i & 0x3f) | 128L) << 24;
    }

    static HashCode fmix(int i, int j)
    {
        i ^= j;
        i = (i ^ i >>> 16) * 0x85ebca6b;
        i = (i ^ i >>> 13) * 0xc2b2ae35;
        return HashCode.fromInt(i ^ i >>> 16);
    }

    static int getIntLittleEndian(byte abyte0[], int i)
    {
        return abyte0[i + 3] << 24 | (abyte0[i + 2] & 0xff) << 16 | (abyte0[i + 1] & 0xff) << 8 | abyte0[i] & 0xff;
    }

    static int mixH1(int i, int j)
    {
        return Integer.rotateLeft(i ^ j, 13) * 5 - 0x19ab949c;
    }

    static int mixK1(int i)
    {
        return Integer.rotateLeft(0xcc9e2d51 * i, 15) * 0x1b873593;
    }

    public final boolean equals(Object obj)
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if (obj instanceof Murmur3_32HashFunction)
        {
            obj = (Murmur3_32HashFunction)obj;
            flag = flag1;
            if (seed == ((Murmur3_32HashFunction) (obj)).seed)
            {
                flag = true;
            }
        }
        return flag;
    }

    public final HashCode hashBytes(byte abyte0[], int i, int j)
    {
        int l = 0;
        Preconditions.checkPositionIndexes(0, j + 0, abyte0.length);
        int k = seed;
        for (i = 0; i + 4 <= j;)
        {
            int i1 = i + 0;
            byte byte0 = abyte0[i1 + 3];
            byte byte1 = abyte0[i1 + 2];
            byte byte2 = abyte0[i1 + 1];
            k = Integer.rotateLeft(Integer.rotateLeft((abyte0[i1] & 0xff | (byte0 << 24 | (byte1 & 0xff) << 16 | (byte2 & 0xff) << 8)) * 0xcc9e2d51, 15) * 0x1b873593 ^ k, 13);
            i += 4;
            k = k * 5 - 0x19ab949c;
        }

        int j1 = 0;
        while (i < j) 
        {
            j1 ^= (abyte0[i + 0] & 0xff) << l;
            i++;
            l += 8;
        }
        return fmix(Integer.rotateLeft(j1 * 0xcc9e2d51, 15) * 0x1b873593 ^ k, j);
    }

    public final int hashCode()
    {
        return getClass().hashCode() ^ seed;
    }

    public final HashCode hashString(CharSequence charsequence, Charset charset)
    {
        if (Charsets.UTF_8.equals(charset))
        {
            int j2 = charsequence.length();
            int j = seed;
            int i1 = 0;
            int l = 0;
            do
            {
                if (i1 + 4 > j2)
                {
                    break;
                }
                char c = charsequence.charAt(i1);
                char c1 = charsequence.charAt(i1 + 1);
                char c2 = charsequence.charAt(i1 + 2);
                char c3 = charsequence.charAt(i1 + 3);
                if (c >= '\200' || c1 >= '\200' || c2 >= '\200' || c3 >= '\200')
                {
                    break;
                }
                j = 0xe6546b64 + Integer.rotateLeft(Integer.rotateLeft((c | c1 << 8 | c2 << 16 | c3 << 24) * 0xcc9e2d51, 15) * 0x1b873593 ^ j, 13) * 5;
                i1 += 4;
                l += 4;
            } while (true);
            long l3 = 0L;
            boolean flag = false;
            int k1 = l;
            int j1 = j;
            l = ((flag) ? 1 : 0);
            while (i1 < j2) 
            {
                int i = charsequence.charAt(i1);
                int k;
                int l1;
                int i2;
                long l2;
                if (i < 128)
                {
                    l2 = l3 | (long)i << l;
                    l += 8;
                    k = k1 + 1;
                    k1 = i1;
                } else
                if (i < 2048)
                {
                    l2 = l3 | (long)((i & 0x3f | 0x80) << 8 | (i >>> 6 | 0x3c0) & 0xff) << l;
                    l += 16;
                    k = k1 + 2;
                    k1 = i1;
                } else
                if (i < 55296 || i > 57343)
                {
                    l2 = l3 | charToThreeUtf8Bytes(i) << l;
                    l += 24;
                    k = k1 + 3;
                    k1 = i1;
                } else
                {
                    k = Character.codePointAt(charsequence, i1);
                    if (k == i)
                    {
                        charsequence = charsequence.toString().getBytes(charset);
                        return hashBytes(charsequence, 0, charsequence.length);
                    }
                    i1++;
                    l2 = l3 | codePointToFourUtf8Bytes(k) << l;
                    k = k1 + 4;
                    k1 = i1;
                }
                i1 = l;
                l1 = j1;
                l3 = l2;
                if (l >= 32)
                {
                    l1 = Integer.rotateLeft(j1 ^ Integer.rotateLeft((int)l2 * 0xcc9e2d51, 15) * 0x1b873593, 13) * 5 - 0x19ab949c;
                    l3 = l2 >>> 32;
                    i1 = l - 32;
                }
                i2 = k1 + 1;
                l = i1;
                j1 = l1;
                k1 = k;
                i1 = i2;
            }
            return fmix(Integer.rotateLeft((int)l3 * 0xcc9e2d51, 15) * 0x1b873593 ^ j1, k1);
        } else
        {
            charsequence = charsequence.toString().getBytes(charset);
            return hashBytes(charsequence, 0, charsequence.length);
        }
    }

    public final Hasher newHasher()
    {
        return new Murmur3_32Hasher(seed);
    }

    public final String toString()
    {
        int i = seed;
        return (new StringBuilder(31)).append("Hashing.murmur3_32(").append(i).append(")").toString();
    }

    static 
    {
        new Murmur3_32HashFunction(Hashing.GOOD_FAST_HASH_SEED);
    }

    private class Murmur3_32Hasher extends AbstractHasher
    {

        private long buffer;
        private int h1;
        private boolean isDone;
        private int length;
        private int shift;

        private final void update(int i, long l)
        {
            buffer = buffer | (0xffffffffL & l) << shift;
            shift = shift + (i << 3);
            length = length + i;
            if (shift >= 32)
            {
                h1 = Murmur3_32HashFunction.mixH1(h1, Murmur3_32HashFunction.mixK1((int)buffer));
                buffer = buffer >>> 32;
                shift = shift - 32;
            }
        }

        public final HashCode hash()
        {
            boolean flag;
            if (!isDone)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            } else
            {
                isDone = true;
                h1 = h1 ^ Murmur3_32HashFunction.mixK1((int)buffer);
                return Murmur3_32HashFunction.fmix(h1, length);
            }
        }

        public final Hasher putByte(byte byte0)
        {
            update(1, byte0 & 0xff);
            return this;
        }

        public final Hasher putBytes(byte abyte0[], int i, int j)
        {
            Preconditions.checkPositionIndexes(i, i + j, abyte0.length);
            int k = 0;
            int l;
            do
            {
                l = k;
                if (k + 4 > j)
                {
                    break;
                }
                update(4, Murmur3_32HashFunction.getIntLittleEndian(abyte0, i + k));
                k += 4;
            } while (true);
            for (; l < j; l++)
            {
                update(1, abyte0[i + l] & 0xff);
            }

            return this;
        }

        public final Hasher putString(CharSequence charsequence, Charset charset)
        {
            int j;
            int i1;
            if (!Charsets.UTF_8.equals(charset))
            {
                break MISSING_BLOCK_LABEL_309;
            }
            i1 = charsequence.length();
            int k = 0;
            do
            {
                j = k;
                if (k + 4 > i1)
                {
                    break;
                }
                char c = charsequence.charAt(k);
                char c1 = charsequence.charAt(k + 1);
                char c2 = charsequence.charAt(k + 2);
                char c3 = charsequence.charAt(k + 3);
                j = k;
                if (c >= '\200')
                {
                    break;
                }
                j = k;
                if (c1 >= '\200')
                {
                    break;
                }
                j = k;
                if (c2 >= '\200')
                {
                    break;
                }
                j = k;
                if (c3 >= '\200')
                {
                    break;
                }
                update(4, c | c1 << 8 | c2 << 16 | c3 << 24);
                k += 4;
            } while (true);
_L2:
            int i;
            if (j >= i1)
            {
                break; /* Loop/switch isn't completed */
            }
            i = charsequence.charAt(j);
            if (i < 128)
            {
                update(1, i);
            } else
            if (i < 2048)
            {
                update(2, Murmur3_32HashFunction.charToTwoUtf8Bytes(i));
            } else
            {
label0:
                {
                    if (i >= 55296 && i <= 57343)
                    {
                        break label0;
                    }
                    update(3, Murmur3_32HashFunction.charToThreeUtf8Bytes(i));
                }
            }
_L3:
            j++;
            if (true) goto _L2; else goto _L1
            int l;
            l = Character.codePointAt(charsequence, j);
            if (l != i)
            {
                break MISSING_BLOCK_LABEL_290;
            }
            charsequence = charsequence.subSequence(j, i1).toString().getBytes(charset);
            putBytes(charsequence, 0, charsequence.length);
_L1:
            return this;
            j++;
            update(4, Murmur3_32HashFunction.codePointToFourUtf8Bytes(l));
              goto _L3
            return super.putString(charsequence, charset);
        }

        Murmur3_32Hasher(int i)
        {
            h1 = i;
            length = 0;
            isDone = false;
        }
    }

}
