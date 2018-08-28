// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.nio.charset.Charset;

// Referenced classes of package com.google.common.hash:
//            HashFunction, Hasher, HashCode

abstract class AbstractHashFunction
    implements HashFunction
{

    AbstractHashFunction()
    {
    }

    public HashCode hashBytes(byte abyte0[], int i, int j)
    {
        Preconditions.checkPositionIndexes(0, j + 0, abyte0.length);
        if (j >= 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new IllegalArgumentException(Strings.lenientFormat("expectedInputSize must be >= 0 but was %s", new Object[] {
                Integer.valueOf(j)
            }));
        } else
        {
            return newHasher().putBytes(abyte0, 0, j).hash();
        }
    }

    public HashCode hashString(CharSequence charsequence, Charset charset)
    {
        return newHasher().putString(charsequence, charset).hash();
    }
}
