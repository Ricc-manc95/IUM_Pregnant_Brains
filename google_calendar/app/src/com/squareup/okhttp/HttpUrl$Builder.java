// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.squareup.okhttp;

import java.net.IDN;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

// Referenced classes of package com.squareup.okhttp:
//            HttpUrl

public final class encodedPathSegments
{

    public String encodedPassword;
    public final List encodedPathSegments = new ArrayList();
    public String encodedUsername;
    public String host;
    public int port;
    public String scheme;

    public static InetAddress decodeIpv6(String s, int i, int j)
    {
        byte abyte0[];
        int k;
        int l;
        int i1;
        int j1;
        abyte0 = new byte[16];
        k = 0;
        l = -1;
        i1 = -1;
        j1 = 1;
_L12:
        int k1;
        k1 = l;
        i = k;
        if (j1 >= j) goto _L2; else goto _L1
_L1:
        if (k == 16)
        {
            return null;
        }
        if (j1 + 2 > j || !s.regionMatches(j1, "::", 0, 2)) goto _L4; else goto _L3
_L3:
        if (l != -1)
        {
            return null;
        }
        i = j1 + 2;
        k += 2;
        if (i != j)
        {
            break MISSING_BLOCK_LABEL_529;
        }
        i = k;
        k1 = k;
          goto _L2
_L4:
        int l1;
        i = j1;
        k1 = l;
        l1 = k;
        if (k == 0) goto _L6; else goto _L5
_L5:
        if (!s.regionMatches(j1, ":", 0, 1)) goto _L8; else goto _L7
_L7:
        i = j1 + 1;
        l1 = k;
        k1 = l;
          goto _L6
_L8:
        if (!s.regionMatches(j1, ".", 0, 1))
        {
            break MISSING_BLOCK_LABEL_413;
        }
        l1 = k - 2;
        j1 = l1;
_L11:
        if (i1 >= j)
        {
            break MISSING_BLOCK_LABEL_382;
        }
        if (j1 == abyte0.length)
        {
            i = 0;
        } else
        {
label0:
            {
                i = i1;
                if (j1 == l1)
                {
                    break MISSING_BLOCK_LABEL_269;
                }
                if (s.charAt(i1) == '.')
                {
                    break label0;
                }
                i = 0;
            }
        }
_L9:
        if (i == 0)
        {
            return null;
        }
        break MISSING_BLOCK_LABEL_401;
        i = i1 + 1;
        k1 = 0;
        i1 = i;
_L10:
        if (i1 >= j)
        {
            break MISSING_BLOCK_LABEL_354;
        }
        char c = s.charAt(i1);
        if (c < '0' || c > '9')
        {
            break MISSING_BLOCK_LABEL_354;
        }
        if (k1 == 0 && i != i1)
        {
            i = 0;
        } else
        {
label1:
            {
                k1 = (k1 * 10 + c) - 48;
                if (k1 <= 255)
                {
                    break label1;
                }
                i = 0;
            }
        }
          goto _L9
        i1++;
          goto _L10
label2:
        {
            if (i1 - i != 0)
            {
                break label2;
            }
            i = 0;
        }
          goto _L9
        abyte0[j1] = (byte)k1;
        j1++;
          goto _L11
        if (j1 != l1 + 4)
        {
            i = 0;
        } else
        {
            i = 1;
        }
          goto _L9
        i = k + 2;
        k1 = l;
          goto _L2
        return null;
_L6:
        l = 0;
        k = i;
        do
        {
            if (k >= j)
            {
                break;
            }
            i1 = HttpUrl.decodeHexDigit(s.charAt(k));
            if (i1 == -1)
            {
                break;
            }
            l = (l << 4) + i1;
            k++;
        } while (true);
        i1 = k - i;
        if (i1 == 0 || i1 > 4)
        {
            return null;
        }
        j1 = l1 + 1;
        abyte0[l1] = (byte)(l >>> 8);
        i1 = j1 + 1;
        abyte0[j1] = (byte)l;
        j1 = k;
        l = k1;
        k = i1;
        i1 = i;
        if (true) goto _L12; else goto _L8
_L2:
        if (i != 16)
        {
            if (k1 == -1)
            {
                return null;
            }
            System.arraycopy(abyte0, k1, abyte0, 16 - (i - k1), i - k1);
            Arrays.fill(abyte0, k1, (16 - i) + k1, (byte)0);
        }
        try
        {
            s = InetAddress.getByAddress(abyte0);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new AssertionError();
        }
        return s;
        l1 = k;
        k1 = k;
          goto _L6
    }

    public static String domainToAscii(String s)
    {
        boolean flag1 = true;
        s = IDN.toASCII(s).toLowerCase(Locale.US);
        break MISSING_BLOCK_LABEL_13;
_L10:
        if (i >= s.length()) goto _L2; else goto _L1
_L1:
        j = s.charAt(i);
        boolean flag = flag1;
        if (j <= '\037') goto _L4; else goto _L3
_L3:
        if (j < '\177') goto _L6; else goto _L5
_L5:
        flag = flag1;
          goto _L4
_L6:
        j = " #%/:?@[\\]".indexOf(j);
        flag = flag1;
        if (j == -1)
        {
            i++;
            continue; /* Loop/switch isn't completed */
        }
          goto _L4
_L2:
        flag = false;
          goto _L4
        s;
_L8:
        return null;
        int i;
        int j;
        if (s.isEmpty())
        {
            return null;
        }
        i = 0;
        continue; /* Loop/switch isn't completed */
_L4:
        if (flag) goto _L8; else goto _L7
_L7:
        return s;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(scheme);
        stringbuilder.append("://");
        if (!encodedUsername.isEmpty() || !encodedPassword.isEmpty())
        {
            stringbuilder.append(encodedUsername);
            if (!encodedPassword.isEmpty())
            {
                stringbuilder.append(':');
                stringbuilder.append(encodedPassword);
            }
            stringbuilder.append('@');
        }
        int i;
        if (host.indexOf(':') != -1)
        {
            stringbuilder.append('[');
            stringbuilder.append(host);
            stringbuilder.append(']');
        } else
        {
            stringbuilder.append(host);
        }
        if (port != -1)
        {
            i = port;
        } else
        {
            i = HttpUrl.defaultPort(scheme);
        }
        if (i != HttpUrl.defaultPort(scheme))
        {
            stringbuilder.append(':');
            stringbuilder.append(i);
        }
        HttpUrl.pathSegmentsToString(stringbuilder, encodedPathSegments);
        return stringbuilder.toString();
    }

    public ()
    {
        encodedUsername = "";
        encodedPassword = "";
        port = -1;
        encodedPathSegments.add("");
    }
}
