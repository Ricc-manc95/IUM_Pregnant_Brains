// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal;

import javax.security.auth.x500.X500Principal;

final class DistinguishedNameParser
{

    public int beg;
    public char chars[];
    public int cur;
    public final String dn;
    public int end;
    public final int length;
    public int pos;

    public DistinguishedNameParser(X500Principal x500principal)
    {
        dn = x500principal.getName("RFC2253");
        length = dn.length();
    }

    final int getByte(int i)
    {
        if (i + 1 >= length)
        {
            String s = String.valueOf(dn);
            if (s.length() != 0)
            {
                s = "Malformed DN: ".concat(s);
            } else
            {
                s = new String("Malformed DN: ");
            }
            throw new IllegalStateException(s);
        }
        int j = chars[i];
        if (j >= 48 && j <= 57)
        {
            j -= 48;
        } else
        if (j >= 97 && j <= 102)
        {
            j -= 87;
        } else
        if (j >= 65 && j <= 70)
        {
            j -= 55;
        } else
        {
            String s1 = String.valueOf(dn);
            if (s1.length() != 0)
            {
                s1 = "Malformed DN: ".concat(s1);
            } else
            {
                s1 = new String("Malformed DN: ");
            }
            throw new IllegalStateException(s1);
        }
        i = chars[i + 1];
        if (i < 48 || i > 57) goto _L2; else goto _L1
_L1:
        i -= 48;
_L4:
        return (j << 4) + i;
_L2:
        if (i >= 97 && i <= 102)
        {
            i -= 87;
            continue; /* Loop/switch isn't completed */
        }
        if (i < 65 || i > 70)
        {
            break; /* Loop/switch isn't completed */
        }
        i -= 55;
        if (true) goto _L4; else goto _L3
_L3:
        String s2 = String.valueOf(dn);
        if (s2.length() != 0)
        {
            s2 = "Malformed DN: ".concat(s2);
        } else
        {
            s2 = new String("Malformed DN: ");
        }
        throw new IllegalStateException(s2);
    }

    final char getEscaped()
    {
label0:
        {
            pos = pos + 1;
            if (pos == length)
            {
                String s = String.valueOf(dn);
                if (s.length() != 0)
                {
                    s = "Unexpected end of DN: ".concat(s);
                } else
                {
                    s = new String("Unexpected end of DN: ");
                }
                throw new IllegalStateException(s);
            }
            int i;
            switch (chars[pos])
            {
            default:
                i = getByte(pos);
                pos = pos + 1;
                if (i < 128)
                {
                    return (char)i;
                }
                break;

            case 32: // ' '
            case 34: // '"'
            case 35: // '#'
            case 37: // '%'
            case 42: // '*'
            case 43: // '+'
            case 44: // ','
            case 59: // ';'
            case 60: // '<'
            case 61: // '='
            case 62: // '>'
            case 92: // '\\'
            case 95: // '_'
                return chars[pos];
            }
            if (i < 192 || i > 247)
            {
                break label0;
            }
            int j;
            int k;
            boolean flag;
            if (i <= 223)
            {
                j = 1;
                i &= 0x1f;
            } else
            if (i <= 239)
            {
                j = 2;
                i &= 0xf;
            } else
            {
                j = 3;
                i &= 7;
            }
            flag = false;
            k = i;
            for (i = ((flag) ? 1 : 0); i < j; i++)
            {
                pos = pos + 1;
                if (pos == length || chars[pos] != '\\')
                {
                    return '?';
                }
                pos = pos + 1;
                int l = getByte(pos);
                pos = pos + 1;
                if ((l & 0xc0) != 128)
                {
                    break label0;
                }
                k = (k << 6) + (l & 0x3f);
            }

            return (char)k;
        }
        return '?';
    }

    final String nextAT()
    {
        for (; pos < length && chars[pos] == ' '; pos = pos + 1) { }
        if (pos == length)
        {
            return null;
        }
        beg = pos;
        for (pos = pos + 1; pos < length && chars[pos] != '=' && chars[pos] != ' '; pos = pos + 1) { }
        if (pos >= length)
        {
            String s = String.valueOf(dn);
            if (s.length() != 0)
            {
                s = "Unexpected end of DN: ".concat(s);
            } else
            {
                s = new String("Unexpected end of DN: ");
            }
            throw new IllegalStateException(s);
        }
        end = pos;
        if (chars[pos] == ' ')
        {
            for (; pos < length && chars[pos] != '=' && chars[pos] == ' '; pos = pos + 1) { }
            if (chars[pos] != '=' || pos == length)
            {
                String s1 = String.valueOf(dn);
                if (s1.length() != 0)
                {
                    s1 = "Unexpected end of DN: ".concat(s1);
                } else
                {
                    s1 = new String("Unexpected end of DN: ");
                }
                throw new IllegalStateException(s1);
            }
        }
        do
        {
            pos = pos + 1;
        } while (pos < length && chars[pos] == ' ');
        if (end - beg > 4 && chars[beg + 3] == '.' && (chars[beg] == 'O' || chars[beg] == 'o') && (chars[beg + 1] == 'I' || chars[beg + 1] == 'i') && (chars[beg + 2] == 'D' || chars[beg + 2] == 'd'))
        {
            beg = beg + 4;
        }
        return new String(chars, beg, end - beg);
    }
}
