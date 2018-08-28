// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util.escape;


// Referenced classes of package com.google.api.client.util.escape:
//            UnicodeEscaper, Platform

public final class PercentEscaper extends UnicodeEscaper
{

    private static final char UPPER_HEX_DIGITS[] = "0123456789ABCDEF".toCharArray();
    private static final char URI_ESCAPED_SPACE[] = {
        '+'
    };
    private final boolean plusForSpace;
    private final boolean safeOctets[];

    public PercentEscaper(String s, boolean flag)
    {
        boolean flag1 = false;
        super();
        if (s.matches(".*[0-9A-Za-z].*"))
        {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        }
        if (flag && s.contains(" "))
        {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        }
        if (s.contains("%"))
        {
            throw new IllegalArgumentException("The '%' character cannot be specified as 'safe'");
        }
        plusForSpace = flag;
        s = s.toCharArray();
        int k1 = s.length;
        int i = 0;
        int j1 = 122;
        for (; i < k1; i++)
        {
            j1 = Math.max(s[i], j1);
        }

        boolean aflag[] = new boolean[j1 + 1];
        for (int j = 48; j <= 57; j++)
        {
            aflag[j] = true;
        }

        for (int k = 65; k <= 90; k++)
        {
            aflag[k] = true;
        }

        for (int l = 97; l <= 122; l++)
        {
            aflag[l] = true;
        }

        j1 = s.length;
        for (int i1 = ((flag1) ? 1 : 0); i1 < j1; i1++)
        {
            aflag[s[i1]] = true;
        }

        safeOctets = aflag;
    }

    public final String escape(String s)
    {
        int j = s.length();
        int i = 0;
        do
        {
label0:
            {
                Object obj = s;
                if (i < j)
                {
                    int l = s.charAt(i);
                    if (l < safeOctets.length && safeOctets[l])
                    {
                        break label0;
                    }
                    int l1 = s.length();
                    obj = (char[])Platform.DEST_TL.get();
                    int k = 0;
                    l = 0;
                    int i1 = i;
                    i = l;
                    while (i1 < l1) 
                    {
                        if (i1 < l1)
                        {
                            int j1 = i1 + 1;
                            char c = s.charAt(i1);
                            l = c;
                            if (c >= '\uD800')
                            {
                                if (c > '\uDFFF')
                                {
                                    l = c;
                                } else
                                if (c <= '\uDBFF')
                                {
                                    if (j1 == l1)
                                    {
                                        l = -c;
                                    } else
                                    {
                                        char c1 = s.charAt(j1);
                                        if (Character.isLowSurrogate(c1))
                                        {
                                            l = Character.toCodePoint(c, c1);
                                        } else
                                        {
                                            throw new IllegalArgumentException((new StringBuilder(83)).append("Expected low surrogate but got char '").append(c1).append("' with value ").append(c1).append(" at index ").append(j1).toString());
                                        }
                                    }
                                } else
                                {
                                    throw new IllegalArgumentException((new StringBuilder(82)).append("Unexpected low surrogate character '").append(c).append("' with value ").append(c).append(" at index ").append(j1 - 1).toString());
                                }
                            }
                            if (l < 0)
                            {
                                throw new IllegalArgumentException("Trailing high surrogate at end of input");
                            }
                        } else
                        {
                            throw new IndexOutOfBoundsException("Index exceeds specified range");
                        }
                        char ac2[] = escape(l);
                        char ac[];
                        int k1;
                        int i2;
                        if (Character.isSupplementaryCodePoint(l))
                        {
                            l = 2;
                        } else
                        {
                            l = 1;
                        }
                        k1 = l + i1;
                        if (ac2 == null)
                        {
                            continue;
                        }
                        i2 = i1 - k;
                        l = i + i2 + ac2.length;
                        ac = ((char []) (obj));
                        if (obj.length < l)
                        {
                            ac = new char[((l + l1) - i1) + 32];
                            if (i > 0)
                            {
                                System.arraycopy(obj, 0, ac, 0, i);
                            }
                        }
                        l = i;
                        if (i2 > 0)
                        {
                            s.getChars(k, i1, ac, i);
                            l = i + i2;
                        }
                        i = l;
                        if (ac2.length > 0)
                        {
                            System.arraycopy(ac2, 0, ac, l, ac2.length);
                            i = l + ac2.length;
                        }
                        k = k1;
                        obj = ac;
                        i1 = nextEscapeIndex(s, k1, l1);
                    }
                    i1 = l1 - k;
                    l = i;
                    char ac1[] = ((char []) (obj));
                    if (i1 > 0)
                    {
                        l = i + i1;
                        ac1 = ((char []) (obj));
                        if (obj.length < l)
                        {
                            ac1 = new char[l];
                            if (i > 0)
                            {
                                System.arraycopy(obj, 0, ac1, 0, i);
                            }
                        }
                        s.getChars(k, l1, ac1, i);
                    }
                    obj = new String(ac1, 0, l);
                }
                return ((String) (obj));
            }
            i++;
        } while (true);
    }

    protected final char[] escape(int i)
    {
        if (i < safeOctets.length && safeOctets[i])
        {
            return null;
        }
        if (i == 32 && plusForSpace)
        {
            return URI_ESCAPED_SPACE;
        }
        if (i <= 127)
        {
            char c = UPPER_HEX_DIGITS[i & 0xf];
            return (new char[] {
                '%', UPPER_HEX_DIGITS[i >>> 4], c
            });
        }
        if (i <= 2047)
        {
            char c1 = UPPER_HEX_DIGITS[i & 0xf];
            i >>>= 4;
            char c4 = UPPER_HEX_DIGITS[i & 3 | 8];
            i >>>= 2;
            char c7 = UPPER_HEX_DIGITS[i & 0xf];
            return (new char[] {
                '%', UPPER_HEX_DIGITS[i >>> 4 | 0xc], c7, '%', c4, c1
            });
        }
        if (i <= 65535)
        {
            char c2 = UPPER_HEX_DIGITS[i & 0xf];
            i >>>= 4;
            char c5 = UPPER_HEX_DIGITS[i & 3 | 8];
            i >>>= 2;
            char c8 = UPPER_HEX_DIGITS[i & 0xf];
            i >>>= 4;
            char c10 = UPPER_HEX_DIGITS[i & 3 | 8];
            return (new char[] {
                '%', 'E', UPPER_HEX_DIGITS[i >>> 2], '%', c10, c8, '%', c5, c2
            });
        }
        if (i <= 0x10ffff)
        {
            char c3 = UPPER_HEX_DIGITS[i & 0xf];
            i >>>= 4;
            char c6 = UPPER_HEX_DIGITS[i & 3 | 8];
            i >>>= 2;
            char c9 = UPPER_HEX_DIGITS[i & 0xf];
            i >>>= 4;
            char c11 = UPPER_HEX_DIGITS[i & 3 | 8];
            i >>>= 2;
            char c12 = UPPER_HEX_DIGITS[i & 0xf];
            i >>>= 4;
            char c13 = UPPER_HEX_DIGITS[i & 3 | 8];
            return (new char[] {
                '%', 'F', UPPER_HEX_DIGITS[i >>> 2 & 7], '%', c13, c12, '%', c11, c9, '%', 
                c6, c3
            });
        } else
        {
            throw new IllegalArgumentException((new StringBuilder(43)).append("Invalid unicode character value ").append(i).toString());
        }
    }

    protected final int nextEscapeIndex(CharSequence charsequence, int i, int j)
    {
        do
        {
            if (i >= j)
            {
                break;
            }
            char c = charsequence.charAt(i);
            if (c >= safeOctets.length || !safeOctets[c])
            {
                break;
            }
            i++;
        } while (true);
        return i;
    }

}
