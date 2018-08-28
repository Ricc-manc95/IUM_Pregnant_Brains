// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.squareup.okhttp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import okio.Buffer;
import okio.Segment;

public final class HttpUrl
{

    public final String host;
    public final int port;
    private final String url;

    public HttpUrl(Builder builder)
    {
        int i = -1;
        super();
        Object obj = builder.encodedUsername;
        percentDecode(((String) (obj)), 0, ((String) (obj)).length(), false);
        obj = builder.encodedPassword;
        percentDecode(((String) (obj)), 0, ((String) (obj)).length(), false);
        host = builder.host;
        ArrayList arraylist;
        Iterator iterator;
        if (builder.port != -1)
        {
            i = builder.port;
        } else
        {
            s = builder.scheme;
            if (s.equals("http"))
            {
                i = 80;
            } else
            if (s.equals("https"))
            {
                i = 443;
            }
        }
        port = i;
        obj = builder.encodedPathSegments;
        arraylist = new ArrayList(((List) (obj)).size());
        iterator = ((List) (obj)).iterator();
        while (iterator.hasNext()) 
        {
            String s = (String)iterator.next();
            if (s != null)
            {
                s = percentDecode(s, 0, s.length(), false);
            } else
            {
                s = null;
            }
            arraylist.add(s);
        }
        Collections.unmodifiableList(arraylist);
        url = builder.toString();
    }

    static int decodeHexDigit(char c)
    {
        if (c >= '0' && c <= '9')
        {
            return c - 48;
        }
        if (c >= 'a' && c <= 'f')
        {
            return (c - 97) + 10;
        }
        if (c >= 'A' && c <= 'F')
        {
            return (c - 65) + 10;
        } else
        {
            return -1;
        }
    }

    public static int defaultPort(String s)
    {
        if (s.equals("http"))
        {
            return 80;
        }
        return !s.equals("https") ? -1 : 443;
    }

    static void pathSegmentsToString(StringBuilder stringbuilder, List list)
    {
        int j = list.size();
        for (int i = 0; i < j; i++)
        {
            stringbuilder.append('/');
            stringbuilder.append((String)list.get(i));
        }

    }

    public static String percentDecode(String s, int i, int j, boolean flag)
    {
        int k = i;
_L5:
        Buffer buffer;
        if (k >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        char c = s.charAt(k);
        if (c != '%' && (c != '+' || !flag))
        {
            break MISSING_BLOCK_LABEL_1072;
        }
        buffer = new Buffer();
        buffer.writeUtf8(s, i, k);
        i = k;
_L2:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_1066;
        }
        k = s.codePointAt(i);
        if (k != 37 || i + 2 >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        int l = decodeHexDigit(s.charAt(i + 1));
        int k2 = decodeHexDigit(s.charAt(i + 2));
        if (l == -1 || k2 == -1)
        {
            break MISSING_BLOCK_LABEL_269;
        }
        Object obj = buffer.writableSegment(1);
        byte abyte0[] = ((Segment) (obj)).data;
        int l2 = ((Segment) (obj)).limit;
        obj.limit = l2 + 1;
        abyte0[l2] = (byte)((l << 4) + k2);
        buffer.size = buffer.size + 1L;
        obj = (Buffer)buffer;
        i += 2;
_L3:
        i = Character.charCount(k) + i;
        if (true) goto _L2; else goto _L1
_L1:
        if (k != 43 || !flag)
        {
            break MISSING_BLOCK_LABEL_269;
        }
        Object obj1 = buffer.writableSegment(1);
        byte abyte1[] = ((Segment) (obj1)).data;
        int i1 = ((Segment) (obj1)).limit;
        obj1.limit = i1 + 1;
        abyte1[i1] = (byte)32;
        buffer.size = buffer.size + 1L;
        obj1 = (Buffer)buffer;
          goto _L3
        if (k < 128)
        {
            Object obj2 = buffer.writableSegment(1);
            byte abyte2[] = ((Segment) (obj2)).data;
            int j1 = ((Segment) (obj2)).limit;
            obj2.limit = j1 + 1;
            abyte2[j1] = (byte)k;
            buffer.size = buffer.size + 1L;
            obj2 = (Buffer)buffer;
        } else
        if (k < 2048)
        {
            Object obj3 = buffer.writableSegment(1);
            byte abyte3[] = ((Segment) (obj3)).data;
            int k1 = ((Segment) (obj3)).limit;
            obj3.limit = k1 + 1;
            abyte3[k1] = (byte)(k >> 6 | 0xc0);
            buffer.size = buffer.size + 1L;
            obj3 = (Buffer)buffer;
            obj3 = buffer.writableSegment(1);
            abyte3 = ((Segment) (obj3)).data;
            k1 = ((Segment) (obj3)).limit;
            obj3.limit = k1 + 1;
            abyte3[k1] = (byte)(k & 0x3f | 0x80);
            buffer.size = buffer.size + 1L;
            obj3 = (Buffer)buffer;
        } else
        if (k < 0x10000)
        {
            if (k >= 55296 && k <= 57343)
            {
                Object obj4 = buffer.writableSegment(1);
                byte abyte4[] = ((Segment) (obj4)).data;
                int l1 = ((Segment) (obj4)).limit;
                obj4.limit = l1 + 1;
                abyte4[l1] = (byte)63;
                buffer.size = buffer.size + 1L;
                obj4 = (Buffer)buffer;
            } else
            {
                Object obj5 = buffer.writableSegment(1);
                byte abyte5[] = ((Segment) (obj5)).data;
                int i2 = ((Segment) (obj5)).limit;
                obj5.limit = i2 + 1;
                abyte5[i2] = (byte)(k >> 12 | 0xe0);
                buffer.size = buffer.size + 1L;
                obj5 = (Buffer)buffer;
                obj5 = buffer.writableSegment(1);
                abyte5 = ((Segment) (obj5)).data;
                i2 = ((Segment) (obj5)).limit;
                obj5.limit = i2 + 1;
                abyte5[i2] = (byte)(k >> 6 & 0x3f | 0x80);
                buffer.size = buffer.size + 1L;
                obj5 = (Buffer)buffer;
                obj5 = buffer.writableSegment(1);
                abyte5 = ((Segment) (obj5)).data;
                i2 = ((Segment) (obj5)).limit;
                obj5.limit = i2 + 1;
                abyte5[i2] = (byte)(k & 0x3f | 0x80);
                buffer.size = buffer.size + 1L;
                obj5 = (Buffer)buffer;
            }
        } else
        if (k <= 0x10ffff)
        {
            Object obj6 = buffer.writableSegment(1);
            byte abyte6[] = ((Segment) (obj6)).data;
            int j2 = ((Segment) (obj6)).limit;
            obj6.limit = j2 + 1;
            abyte6[j2] = (byte)(k >> 18 | 0xf0);
            buffer.size = buffer.size + 1L;
            obj6 = (Buffer)buffer;
            obj6 = buffer.writableSegment(1);
            abyte6 = ((Segment) (obj6)).data;
            j2 = ((Segment) (obj6)).limit;
            obj6.limit = j2 + 1;
            abyte6[j2] = (byte)(k >> 12 & 0x3f | 0x80);
            buffer.size = buffer.size + 1L;
            obj6 = (Buffer)buffer;
            obj6 = buffer.writableSegment(1);
            abyte6 = ((Segment) (obj6)).data;
            j2 = ((Segment) (obj6)).limit;
            obj6.limit = j2 + 1;
            abyte6[j2] = (byte)(k >> 6 & 0x3f | 0x80);
            buffer.size = buffer.size + 1L;
            obj6 = (Buffer)buffer;
            obj6 = buffer.writableSegment(1);
            abyte6 = ((Segment) (obj6)).data;
            j2 = ((Segment) (obj6)).limit;
            obj6.limit = j2 + 1;
            abyte6[j2] = (byte)(k & 0x3f | 0x80);
            buffer.size = buffer.size + 1L;
            obj6 = (Buffer)buffer;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder("Unexpected code point: ")).append(Integer.toHexString(k)).toString());
        }
          goto _L3
        return buffer.readUtf8();
        k++;
        if (true) goto _L5; else goto _L4
_L4:
        return s.substring(i, j);
    }

    public final boolean equals(Object obj)
    {
        return (obj instanceof HttpUrl) && ((HttpUrl)obj).url.equals(url);
    }

    public final int hashCode()
    {
        return url.hashCode();
    }

    public final String toString()
    {
        return url;
    }

    private class Builder
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

        public Builder()
        {
            encodedUsername = "";
            encodedPassword = "";
            port = -1;
            encodedPathSegments.add("");
        }
    }

}
