// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.nio.charset.Charset;

// Referenced classes of package com.google.common.hash:
//            Hasher

abstract class AbstractHasher
    implements Hasher
{

    AbstractHasher()
    {
    }

    public Hasher putBytes(byte abyte0[], int i, int j)
    {
        Preconditions.checkPositionIndexes(i, i + j, abyte0.length);
        for (int k = 0; k < j; k++)
        {
            putByte(abyte0[i + k]);
        }

        return this;
    }

    public Hasher putString(CharSequence charsequence, Charset charset)
    {
        charsequence = charsequence.toString().getBytes(charset);
        return putBytes(charsequence, 0, charsequence.length);
    }
}
