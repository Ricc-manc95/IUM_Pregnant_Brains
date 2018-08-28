// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.io;

import com.google.common.base.Preconditions;
import java.io.IOException;

public abstract class BaseEncoding
{

    public static final BaseEncoding BASE16 = new Base16Encoding("base16()", "0123456789ABCDEF");
    public static final BaseEncoding BASE32_HEX = new StandardBaseEncoding("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", Character.valueOf('='));
    public static final BaseEncoding BASE64 = new Base64Encoding("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", Character.valueOf('='));

    BaseEncoding()
    {
    }

    public final byte[] decode(CharSequence charsequence)
    {
        int i;
        CharSequence charsequence1 = trimTrailingPadding(charsequence);
        charsequence = new byte[maxDecodedSize(charsequence1.length())];
        i = decodeTo(charsequence, charsequence1);
        if (i == charsequence.length)
        {
            return charsequence;
        }
        byte abyte0[];
        try
        {
            abyte0 = new byte[i];
            System.arraycopy(charsequence, 0, abyte0, 0, i);
        }
        // Misplaced declaration of an exception variable
        catch (CharSequence charsequence)
        {
            throw new IllegalArgumentException(charsequence);
        }
        return abyte0;
    }

    abstract int decodeTo(byte abyte0[], CharSequence charsequence)
        throws DecodingException;

    public final String encode(byte abyte0[], int i, int j)
    {
        Preconditions.checkPositionIndexes(0, j + 0, abyte0.length);
        StringBuilder stringbuilder = new StringBuilder(maxEncodedSize(j));
        try
        {
            encodeTo(stringbuilder, abyte0, 0, j);
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw new AssertionError(abyte0);
        }
        return stringbuilder.toString();
    }

    abstract void encodeTo(Appendable appendable, byte abyte0[], int i, int j)
        throws IOException;

    public abstract BaseEncoding lowerCase();

    abstract int maxDecodedSize(int i);

    abstract int maxEncodedSize(int i);

    public abstract BaseEncoding omitPadding();

    CharSequence trimTrailingPadding(CharSequence charsequence)
    {
        if (charsequence == null)
        {
            throw new NullPointerException();
        } else
        {
            return (CharSequence)charsequence;
        }
    }

    static 
    {
        new Base64Encoding("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", Character.valueOf('='));
        new StandardBaseEncoding("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", Character.valueOf('='));
    }

    private class Base64Encoding extends StandardBaseEncoding
    {
        private class StandardBaseEncoding extends BaseEncoding
        {
            private class Alphabet
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
                    throws DecodingException
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
                        throw new DecodingException(s);
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
                            throw new DecodingException(s1);
                        } else
                        {
                            throw new DecodingException((new StringBuilder(25)).append("Unrecognized character: ").append(c).toString());
                        }
                    } else
                    {
                        return byte0;
                    }
                }

                public final boolean equals(Object obj)
                {
                    if (obj instanceof Alphabet)
                    {
                        obj = (Alphabet)obj;
                        return Arrays.equals(chars, ((Alphabet) (obj)).chars);
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

                Alphabet(String s, char ac[])
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

                private class DecodingException extends IOException
                {

                    DecodingException(String s)
                    {
                        super(s);
                    }
                }

            }


            public final Alphabet alphabet;
            private transient BaseEncoding lowerCase;
            private final Character paddingChar;

            int decodeTo(byte abyte0[], CharSequence charsequence)
                throws DecodingException
            {
                if (abyte0 == null)
                {
                    throw new NullPointerException();
                }
                charsequence = trimTrailingPadding(charsequence);
                Alphabet alphabet1 = alphabet;
                int i = charsequence.length();
                if (!alphabet1.validPadding[i % alphabet1.charsPerChunk])
                {
                    int j = charsequence.length();
                    throw new DecodingException((new StringBuilder(32)).append("Invalid input length ").append(j).toString());
                }
                int l = 0;
                for (int k = 0; k < charsequence.length(); k += alphabet.charsPerChunk)
                {
                    long l2 = 0L;
                    int i1 = 0;
                    for (int j1 = 0; j1 < alphabet.charsPerChunk; j1++)
                    {
                        l2 <<= alphabet.bitsPerChar;
                        if (k + j1 < charsequence.length())
                        {
                            long l3 = alphabet.decode(charsequence.charAt(i1 + k));
                            i1++;
                            l2 |= l3;
                        }
                    }

                    int l1 = alphabet.bytesPerChunk;
                    int i2 = alphabet.bitsPerChar;
                    for (int k1 = alphabet.bytesPerChunk - 1 << 3; k1 >= (l1 << 3) - i1 * i2;)
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
                if (j <= alphabet.bytesPerChunk)
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

                int l = alphabet.bitsPerChar;
                for (i = ((flag1) ? 1 : 0); i < j << 3; i += alphabet.bitsPerChar)
                {
                    int i1 = (int)(l1 >>> (j + 1 << 3) - l - i);
                    int j1 = alphabet.mask;
                    appendable.append(alphabet.chars[i1 & j1]);
                }

                if (paddingChar != null)
                {
                    for (; i < alphabet.bytesPerChunk << 3; i += alphabet.bitsPerChar)
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
                for (int k = 0; k < j; k += alphabet.bytesPerChunk)
                {
                    encodeChunkTo(appendable, abyte0, i + k, Math.min(alphabet.bytesPerChunk, j - k));
                }

            }

            public boolean equals(Object obj)
            {
                if (obj instanceof StandardBaseEncoding)
                {
                    Object obj1 = (StandardBaseEncoding)obj;
                    if (alphabet.equals(((StandardBaseEncoding) (obj1)).alphabet))
                    {
                        obj = paddingChar;
                        obj1 = ((StandardBaseEncoding) (obj1)).paddingChar;
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
                return alphabet.hashCode() ^ Arrays.hashCode(new Object[] {
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
                ac = ((Alphabet) (obj)).chars;
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
                    obj = newInstance(((Alphabet) (obj)), paddingChar);
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
                ac1 = ((Alphabet) (obj)).chars;
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
                ac1 = new char[((Alphabet) (obj)).chars.length];
                i = 0;
                while (i < ((Alphabet) (obj)).chars.length) 
                {
                    c1 = ((Alphabet) (obj)).chars[i];
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
                obj = new Alphabet(String.valueOf(((Alphabet) (obj)).name).concat(".lowerCase()"), ac1);
                  goto _L7
            }

            final int maxDecodedSize(int i)
            {
                return (int)(((long)alphabet.bitsPerChar * (long)i + 7L) / 8L);
            }

            final int maxEncodedSize(int i)
            {
                return alphabet.charsPerChunk * IntMath.divide(i, alphabet.bytesPerChunk, RoundingMode.CEILING);
            }

            BaseEncoding newInstance(Alphabet alphabet1, Character character)
            {
                return new StandardBaseEncoding(alphabet1, character);
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
                stringbuilder.append(alphabet.toString());
                if (8 % alphabet.bitsPerChar != 0)
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

            StandardBaseEncoding(Alphabet alphabet1, Character character)
            {
                boolean flag;
                if (alphabet1 == null)
                {
                    throw new NullPointerException();
                }
                alphabet = (Alphabet)alphabet1;
                if (character != null)
                {
                    char c = character.charValue();
                    if (c < alphabet1.decodabet.length && alphabet1.decodabet[c] != -1)
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

            StandardBaseEncoding(String s, String s1, Character character)
            {
                this(new Alphabet(s, s1.toCharArray()), character);
            }
        }


        final int decodeTo(byte abyte0[], CharSequence charsequence)
            throws DecodingException
        {
            int j = 0;
            if (abyte0 == null)
            {
                throw new NullPointerException();
            }
            charsequence = trimTrailingPadding(charsequence);
            Alphabet alphabet = this.alphabet;
            int i = charsequence.length();
            if (!alphabet.validPadding[i % alphabet.charsPerChunk])
            {
                i = charsequence.length();
                throw new DecodingException((new StringBuilder(32)).append("Invalid input length ").append(i).toString());
            }
            i = 0;
            while (j < charsequence.length()) 
            {
                Alphabet alphabet1 = this.alphabet;
                int k = j + 1;
                int l = alphabet1.decode(charsequence.charAt(j));
                alphabet1 = this.alphabet;
                j = k + 1;
                int j1 = l << 18 | alphabet1.decode(charsequence.charAt(k)) << 12;
                k = i + 1;
                abyte0[i] = (byte)(j1 >>> 16);
                if (j < charsequence.length())
                {
                    Alphabet alphabet2 = this.alphabet;
                    int i1 = j + 1;
                    j1 |= alphabet2.decode(charsequence.charAt(j)) << 6;
                    i = k + 1;
                    abyte0[k] = (byte)(j1 >>> 8);
                    if (i1 < charsequence.length())
                    {
                        Alphabet alphabet3 = this.alphabet;
                        j = i1 + 1;
                        i1 = alphabet3.decode(charsequence.charAt(i1));
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
                appendable.append(alphabet.chars[i1 >>> 18]);
                appendable.append(alphabet.chars[i1 >>> 12 & 0x3f]);
                appendable.append(alphabet.chars[i1 >>> 6 & 0x3f]);
                appendable.append(alphabet.chars[i1 & 0x3f]);
            }

            if (l < i + j)
            {
                encodeChunkTo(appendable, abyte0, l, (i + j) - l);
            }
        }

        final BaseEncoding newInstance(Alphabet alphabet, Character character)
        {
            return new Base64Encoding(alphabet, character);
        }

        private Base64Encoding(Alphabet alphabet, Character character)
        {
            super(alphabet, character);
            boolean flag;
            if (alphabet.chars.length == 64)
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

        Base64Encoding(String s, String s1, Character character)
        {
            this(new Alphabet(s, s1.toCharArray()), character);
        }
    }


    private class Base16Encoding extends StandardBaseEncoding
    {

        private final char encoding[];

        final int decodeTo(byte abyte0[], CharSequence charsequence)
            throws DecodingException
        {
            int k = 0;
            if (abyte0 == null)
            {
                throw new NullPointerException();
            }
            if (charsequence.length() % 2 == 1)
            {
                int i = charsequence.length();
                throw new DecodingException((new StringBuilder(32)).append("Invalid input length ").append(i).toString());
            }
            int j;
            for (j = 0; k < charsequence.length(); j++)
            {
                int l = alphabet.decode(charsequence.charAt(k));
                abyte0[j] = (byte)(alphabet.decode(charsequence.charAt(k + 1)) | l << 4);
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

        final BaseEncoding newInstance(Alphabet alphabet, Character character)
        {
            return new Base16Encoding(alphabet);
        }

        private Base16Encoding(Alphabet alphabet)
        {
            int i = 0;
            super(alphabet, null);
            encoding = new char[512];
            boolean flag;
            if (alphabet.chars.length == 16)
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
                encoding[i] = alphabet.chars[i >>> 4];
                encoding[i | 0x100] = alphabet.chars[i & 0xf];
            }

        }

        Base16Encoding(String s, String s1)
        {
            this(new Alphabet(s, s1.toCharArray()));
        }
    }

}
