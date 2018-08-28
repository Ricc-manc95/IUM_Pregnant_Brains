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

    private final char encoding[];

    final int decodeTo(byte abyte0[], CharSequence charsequence)
        throws on
    {
        int k = 0;
        if (abyte0 == null)
        {
            throw new NullPointerException();
        }
        if (charsequence.length() % 2 == 1)
        {
            int i = charsequence.length();
            throw new on((new StringBuilder(32)).append("Invalid input length ").append(i).toString());
        }
        int j;
        for (j = 0; k < charsequence.length(); j++)
        {
            int l = alphabet.(charsequence.charAt(k));
            abyte0[j] = (byte)(alphabet.(charsequence.charAt(k + 1)) | l << 4);
            k += 2;
        }

        return j;
    }

    final void encodeTo(Appendable appendable, byte abyte0[], int i, int j)
        throws IOException
    {
        if (appendable == null)
        {
            throw new NullPointerException();
        }
        Preconditions.checkPositionIndexes(i, i + j, abyte0.length);
        for (int k = 0; k < j; k++)
        {
            int l = abyte0[i + k] & 0xff;
            appendable.append(encoding[l]);
            appendable.append(encoding[l | 0x100]);
        }

    }

    final BaseEncoding newInstance(encoding encoding1, Character character)
    {
        return new <init>(encoding1);
    }

    private on(on on)
    {
        int i = 0;
        super(on, null);
        encoding = new char[512];
        boolean flag;
        if (on.encoding.length == 16)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        for (; i < 256; i++)
        {
            encoding[i] = on.encoding[i >>> 4];
            encoding[i | 0x100] = on.encoding[i & 0xf];
        }

    }

    encoding(String s, String s1)
    {
        this(new (s, s1.toCharArray()));
    }
}
