// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.io;

import com.google.common.base.Preconditions;
import java.io.IOException;

// Referenced classes of package com.google.common.io:
//            BaseEncoding

final class  extends oding
{

    final int decodeTo(byte abyte0[], CharSequence charsequence)
        throws on
    {
        int j = 0;
        if (abyte0 == null)
        {
            throw new NullPointerException();
        }
        charsequence = trimTrailingPadding(charsequence);
        oding oding = alphabet;
        int i = charsequence.length();
        if (!oding.adding[i % oding.erChunk])
        {
            i = charsequence.length();
            throw new on((new StringBuilder(32)).append("Invalid input length ").append(i).toString());
        }
        i = 0;
        while (j < charsequence.length()) 
        {
            oding oding1 = alphabet;
            int k = j + 1;
            int l = oding1.(charsequence.charAt(j));
            oding1 = alphabet;
            j = k + 1;
            int j1 = l << 18 | oding1.(charsequence.charAt(k)) << 12;
            k = i + 1;
            abyte0[i] = (byte)(j1 >>> 16);
            if (j < charsequence.length())
            {
                oding oding2 = alphabet;
                int i1 = j + 1;
                j1 |= oding2.(charsequence.charAt(j)) << 6;
                i = k + 1;
                abyte0[k] = (byte)(j1 >>> 8);
                if (i1 < charsequence.length())
                {
                    oding oding3 = alphabet;
                    j = i1 + 1;
                    i1 = oding3.(charsequence.charAt(i1));
                    k = i + 1;
                    abyte0[i] = (byte)(j1 | i1);
                    i = k;
                } else
                {
                    j = i1;
                }
            } else
            {
                i = k;
            }
        }
        return i;
    }

    final void encodeTo(Appendable appendable, byte abyte0[], int i, int j)
        throws IOException
    {
        if (appendable == null)
        {
            throw new NullPointerException();
        }
        Preconditions.checkPositionIndexes(i, i + j, abyte0.length);
        int k = j;
        int l = i;
        for (; k >= 3; k -= 3)
        {
            int k1 = l + 1;
            int i1 = abyte0[l];
            int j1 = k1 + 1;
            k1 = abyte0[k1];
            l = j1 + 1;
            i1 = (k1 & 0xff) << 8 | (i1 & 0xff) << 16 | abyte0[j1] & 0xff;
            appendable.append(alphabet.alphabet[i1 >>> 18]);
            appendable.append(alphabet.alphabet[i1 >>> 12 & 0x3f]);
            appendable.append(alphabet.alphabet[i1 >>> 6 & 0x3f]);
            appendable.append(alphabet.alphabet[i1 & 0x3f]);
        }

        if (l < i + j)
        {
            encodeChunkTo(appendable, abyte0, l, (i + j) - l);
        }
    }

    final BaseEncoding newInstance(oding.encodeChunkTo encodechunkto, Character character)
    {
        return new <init>(encodechunkto, character);
    }

    private on(on on, Character character)
    {
        super(on, character);
        boolean flag;
        if (on.oding.length == 64)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            return;
        }
    }

    oding(String s, String s1, Character character)
    {
        this(new (s, s1.toCharArray()), character);
    }
}
