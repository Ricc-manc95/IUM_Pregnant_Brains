// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.hash;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import java.nio.charset.Charset;

// Referenced classes of package com.google.common.hash:
//            AbstractHasher, Murmur3_32HashFunction, HashCode, Hasher

final class isDone extends AbstractHasher
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

    (int i)
    {
        h1 = i;
        length = 0;
        isDone = false;
    }
}
