// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.io;

import com.google.common.base.Strings;
import com.google.common.math.IntMath;
import java.math.RoundingMode;
import java.util.Arrays;

final class validPadding
{

    public final int bitsPerChar;
    public final int bytesPerChunk;
    public final char chars[];
    public final int charsPerChunk;
    public final byte decodabet[];
    public final int mask;
    public final String name;
    public final boolean validPadding[];

    final int decode(char c)
        throws xception
    {
        if (c > '\177')
        {
            String s = String.valueOf(Integer.toHexString(c));
            if (s.length() != 0)
            {
                s = "Unrecognized character: 0x".concat(s);
            } else
            {
                s = new String("Unrecognized character: 0x");
            }
            throw new xception(s);
        }
        byte byte0 = decodabet[c];
        if (byte0 == -1)
        {
            if (c <= ' ' || c == '\177')
            {
                String s1 = String.valueOf(Integer.toHexString(c));
                if (s1.length() != 0)
                {
                    s1 = "Unrecognized character: 0x".concat(s1);
                } else
                {
                    s1 = new String("Unrecognized character: 0x");
                }
                throw new xception(s1);
            } else
            {
                throw new xception((new StringBuilder(25)).append("Unrecognized character: ").append(c).toString());
            }
        } else
        {
            return byte0;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof xception)
        {
            obj = (xception)obj;
            return Arrays.equals(chars, ((chars) (obj)).chars);
        } else
        {
            return false;
        }
    }

    public final int hashCode()
    {
        return Arrays.hashCode(chars);
    }

    public final String toString()
    {
        return name;
    }

    xception(String s, char ac[])
    {
        boolean flag1 = false;
        super();
        if (s == null)
        {
            throw new NullPointerException();
        }
        name = (String)s;
        if (ac == null)
        {
            throw new NullPointerException();
        }
        chars = (char[])ac;
        int i;
        try
        {
            bitsPerChar = IntMath.log2(ac.length, RoundingMode.UNNECESSARY);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            j = ac.length;
            throw new IllegalArgumentException((new StringBuilder(35)).append("Illegal alphabet length ").append(j).toString(), s);
        }
        i = Math.min(8, Integer.lowestOneBit(bitsPerChar));
        try
        {
            charsPerChunk = 8 / i;
            bytesPerChunk = bitsPerChar / i;
        }
        catch (ArithmeticException arithmeticexception)
        {
            s = String.valueOf(new String(ac));
            if (s.length() != 0)
            {
                s = "Illegal alphabet ".concat(s);
            } else
            {
                s = new String("Illegal alphabet ");
            }
            throw new IllegalArgumentException(s, arithmeticexception);
        }
        mask = ac.length - 1;
        s = new byte[128];
        Arrays.fill(s, (byte)-1);
        for (int j = 0; j < ac.length; j++)
        {
            char c = ac[j];
            boolean flag;
            if (c < '\200')
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(Strings.lenientFormat("Non-ASCII character: %s", new Object[] {
                    Character.valueOf(c)
                }));
            }
            if (s[c] == -1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(Strings.lenientFormat("Duplicate character: %s", new Object[] {
                    Character.valueOf(c)
                }));
            }
            s[c] = (byte)j;
        }

        decodabet = s;
        s = new boolean[charsPerChunk];
        for (int k = ((flag1) ? 1 : 0); k < bytesPerChunk; k++)
        {
            s[IntMath.divide(k << 3, bitsPerChar, RoundingMode.CEILING)] = 1;
        }

        validPadding = s;
    }
}
