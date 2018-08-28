// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.io;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.math.IntMath;
import java.io.IOException;
import java.math.RoundingMode;
import java.util.Arrays;

// Referenced classes of package com.google.common.io:
//            BaseEncoding

class <init> extends BaseEncoding
{

    public final paddingChar alphabet;
    private transient BaseEncoding lowerCase;
    private final Character paddingChar;

    int decodeTo(byte abyte0[], CharSequence charsequence)
        throws <init>
    {
        if (abyte0 == null)
        {
            throw new NullPointerException();
        }
        charsequence = trimTrailingPadding(charsequence);
        <init> <init>1 = alphabet;
        int i = charsequence.length();
        if (!<init>1.[i % <init>1.k])
        {
            int j = charsequence.length();
            throw new it>((new StringBuilder(32)).append("Invalid input length ").append(j).toString());
        }
        int l = 0;
        for (int k = 0; k < charsequence.length(); k += alphabet.k)
        {
            long l2 = 0L;
            int i1 = 0;
            for (int j1 = 0; j1 < alphabet.k; j1++)
            {
                l2 <<= alphabet.alphabet;
                if (k + j1 < charsequence.length())
                {
                    long l3 = alphabet.alphabet(charsequence.charAt(i1 + k));
                    i1++;
                    l2 |= l3;
                }
            }

            int l1 = alphabet.k;
            int i2 = alphabet.alphabet;
            for (int k1 = alphabet.k - 1 << 3; k1 >= (l1 << 3) - i1 * i2;)
            {
                abyte0[l] = (byte)(int)(l2 >>> k1 & 255L);
                k1 -= 8;
                l++;
            }

        }

        return l;
    }

    final void encodeChunkTo(Appendable appendable, byte abyte0[], int i, int j)
        throws IOException
    {
        boolean flag1 = false;
        if (appendable == null)
        {
            throw new NullPointerException();
        }
        Preconditions.checkPositionIndexes(i, i + j, abyte0.length);
        boolean flag;
        if (j <= alphabet.k)
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
        long l1 = 0L;
        for (int k = 0; k < j; k++)
        {
            l1 = (l1 | (long)(abyte0[i + k] & 0xff)) << 8;
        }

        int l = alphabet.alphabet;
        for (i = ((flag1) ? 1 : 0); i < j << 3; i += alphabet.alphabet)
        {
            int i1 = (int)(l1 >>> (j + 1 << 3) - l - i);
            int j1 = alphabet.alphabet;
            appendable.append(alphabet.alphabet[i1 & j1]);
        }

        if (paddingChar != null)
        {
            for (; i < alphabet.k << 3; i += alphabet.alphabet)
            {
                appendable.append(paddingChar.charValue());
            }

        }
    }

    void encodeTo(Appendable appendable, byte abyte0[], int i, int j)
        throws IOException
    {
        if (appendable == null)
        {
            throw new NullPointerException();
        }
        Preconditions.checkPositionIndexes(i, i + j, abyte0.length);
        for (int k = 0; k < j; k += alphabet.k)
        {
            encodeChunkTo(appendable, abyte0, i + k, Math.min(alphabet.k, j - k));
        }

    }

    public boolean equals(Object obj)
    {
        if (obj instanceof k)
        {
            Object obj1 = (k)obj;
            if (alphabet.alphabet(((alphabet) (obj1)).alphabet))
            {
                obj = paddingChar;
                obj1 = ((paddingChar) (obj1)).paddingChar;
                boolean flag;
                if (obj == obj1 || obj != null && obj.equals(obj1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    return true;
                }
            }
            return false;
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return alphabet.alphabet() ^ Arrays.hashCode(new Object[] {
            paddingChar
        });
    }

    public final BaseEncoding lowerCase()
    {
        Object obj;
        BaseEncoding baseencoding;
        baseencoding = lowerCase;
        obj = baseencoding;
        if (baseencoding != null) goto _L2; else goto _L1
_L1:
        char ac[];
        int i;
        int j;
        obj = alphabet;
        ac = ((alphabet) (obj)).alphabet;
        j = ac.length;
        i = 0;
_L9:
        if (i >= j) goto _L4; else goto _L3
_L3:
        char c2 = ac[i];
        if (c2 >= 'A' && c2 <= 'Z')
        {
            c2 = '\001';
        } else
        {
            c2 = '\0';
        }
        if (!c2) goto _L6; else goto _L5
_L5:
        i = 1;
_L10:
        if (i != 0) goto _L8; else goto _L7
_L7:
        char c;
        char c1;
        char ac1[];
        char c3;
        boolean flag;
        int k;
        if (obj == alphabet)
        {
            obj = this;
        } else
        {
            obj = newInstance(((newInstance) (obj)), paddingChar);
        }
        lowerCase = ((BaseEncoding) (obj));
_L2:
        return ((BaseEncoding) (obj));
_L6:
        i++;
          goto _L9
_L4:
        i = 0;
          goto _L10
_L8:
        ac1 = ((lowerCase) (obj)).lowerCase;
        k = ac1.length;
        i = 0;
_L13:
        if (i >= k)
        {
            break MISSING_BLOCK_LABEL_209;
        }
        c3 = ac1[i];
        if (c3 >= 'a' && c3 <= 'z')
        {
            c3 = '\001';
        } else
        {
            c3 = '\0';
        }
        if (!c3) goto _L12; else goto _L11
_L11:
        i = 1;
_L14:
        if (i == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new IllegalStateException(String.valueOf("Cannot call lowerCase() on a mixed-case alphabet"));
        }
        break MISSING_BLOCK_LABEL_221;
_L12:
        i++;
          goto _L13
        i = 0;
          goto _L14
        ac1 = new char[((lowerCase) (obj)).lowerCase.length];
        i = 0;
        while (i < ((lowerCase) (obj)).lowerCase.length) 
        {
            c1 = ((lowerCase) (obj)).lowerCase[i];
            if (c1 >= 'A' && c1 <= 'Z')
            {
                flag = true;
            } else
            {
                flag = false;
            }
            c = c1;
            if (flag)
            {
                c = (char)(c1 ^ 0x20);
            }
            ac1[i] = c;
            i++;
        }
        obj = new lowerCase(String.valueOf(((lowerCase) (obj)).lowerCase).concat(".lowerCase()"), ac1);
          goto _L7
    }

    final int maxDecodedSize(int i)
    {
        return (int)(((long)alphabet.alphabet * (long)i + 7L) / 8L);
    }

    final int maxEncodedSize(int i)
    {
        return alphabet.k * IntMath.divide(i, alphabet.k, RoundingMode.CEILING);
    }

    BaseEncoding newInstance(k k, Character character)
    {
        return new <init>(k, character);
    }

    public final BaseEncoding omitPadding()
    {
        if (paddingChar == null)
        {
            return this;
        } else
        {
            return newInstance(alphabet, null);
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("BaseEncoding.");
        stringbuilder.append(alphabet.alphabet());
        if (8 % alphabet.alphabet != 0)
        {
            if (paddingChar == null)
            {
                stringbuilder.append(".omitPadding()");
            } else
            {
                stringbuilder.append(".withPadChar('").append(paddingChar).append("')");
            }
        }
        return stringbuilder.toString();
    }

    final CharSequence trimTrailingPadding(CharSequence charsequence)
    {
        if (charsequence == null)
        {
            throw new NullPointerException();
        }
        if (paddingChar == null)
        {
            return charsequence;
        }
        char c = paddingChar.charValue();
        int i;
        for (i = charsequence.length() - 1; i >= 0 && charsequence.charAt(i) == c; i--) { }
        return charsequence.subSequence(0, i + 1);
    }

    ( , Character character)
    {
        boolean flag;
        if ( == null)
        {
            throw new NullPointerException();
        }
        alphabet = (alphabet);
        if (character != null)
        {
            char c = character.charValue();
            if (c < .alphabet.length && .alphabet[c] != -1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                break MISSING_BLOCK_LABEL_90;
            }
        }
        flag = true;
_L1:
        if (!flag)
        {
            throw new IllegalArgumentException(Strings.lenientFormat("Padding character %s was already in alphabet", new Object[] {
                character
            }));
        } else
        {
            paddingChar = character;
            return;
        }
        flag = false;
          goto _L1
    }

    paddingChar(String s, String s1, Character character)
    {
        this(new <init>(s, s1.toCharArray()), character);
    }
}
