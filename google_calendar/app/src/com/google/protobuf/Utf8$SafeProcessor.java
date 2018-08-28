// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.nio.ByteBuffer;

// Referenced classes of package com.google.protobuf:
//            Utf8

final class n extends n
{

    final int encodeUtf8(CharSequence charsequence, byte abyte0[], int i, int j)
    {
        int l1;
        int i2;
        l1 = charsequence.length();
        boolean flag = false;
        i2 = i + j;
        j = ((flag) ? 1 : 0);
        do
        {
            if (j >= l1 || j + i >= i2)
            {
                break;
            }
            char c2 = charsequence.charAt(j);
            if (c2 >= '\200')
            {
                break;
            }
            abyte0[i + j] = (byte)c2;
            j++;
        } while (true);
        if (j == l1)
        {
            return i + l1;
        }
        i += j;
_L2:
        char c;
        if (j >= l1)
        {
            break MISSING_BLOCK_LABEL_542;
        }
        c = charsequence.charAt(j);
        if (c >= '\200' || i >= i2)
        {
            break; /* Loop/switch isn't completed */
        }
        int k = i + 1;
        abyte0[i] = (byte)c;
        i = k;
_L3:
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        if (c < '\u0800' && i <= i2 - 2)
        {
            int l = i + 1;
            abyte0[i] = (byte)(c >>> 6 | 0x3c0);
            i = l + 1;
            abyte0[l] = (byte)(c & 0x3f | 0x80);
        } else
        if ((c < '\uD800' || '\uDFFF' < c) && i <= i2 - 3)
        {
            int i1 = i + 1;
            abyte0[i] = (byte)(c >>> 12 | 0x1e0);
            int j2 = i1 + 1;
            abyte0[i1] = (byte)(c >>> 6 & 0x3f | 0x80);
            i = j2 + 1;
            abyte0[j2] = (byte)(c & 0x3f | 0x80);
        } else
        {
label0:
            {
                char c1;
label1:
                {
                    if (i > i2 - 4)
                    {
                        break label0;
                    }
                    int j1 = j;
                    if (j + 1 != charsequence.length())
                    {
                        j++;
                        c1 = charsequence.charAt(j);
                        if (Character.isSurrogatePair(c, c1))
                        {
                            break label1;
                        }
                        j1 = j;
                    }
                    throw new ateException(j1 - 1, l1);
                }
                int k1 = Character.toCodePoint(c, c1);
                int k2 = i + 1;
                abyte0[i] = (byte)(k1 >>> 18 | 0xf0);
                i = k2 + 1;
                abyte0[k2] = (byte)(k1 >>> 12 & 0x3f | 0x80);
                k2 = i + 1;
                abyte0[i] = (byte)(k1 >>> 6 & 0x3f | 0x80);
                i = k2 + 1;
                abyte0[k2] = (byte)(k1 & 0x3f | 0x80);
            }
        }
          goto _L3
        if (true) goto _L2; else goto _L4
_L4:
        if ('\uD800' <= c && c <= '\uDFFF' && (j + 1 == charsequence.length() || !Character.isSurrogatePair(c, charsequence.charAt(j + 1))))
        {
            throw new ateException(j, l1);
        } else
        {
            throw new ArrayIndexOutOfBoundsException((new StringBuilder(37)).append("Failed writing ").append(c).append(" at index ").append(i).toString());
        }
        return i;
    }

    final void encodeUtf8Direct(CharSequence charsequence, ByteBuffer bytebuffer)
    {
        encodeUtf8Default(charsequence, bytebuffer);
    }

    final int partialIsValidUtf8(int i, byte abyte0[], int j, int k)
    {
        int l = j;
        if (i == 0) goto _L2; else goto _L1
_L1:
        if (j < k) goto _L4; else goto _L3
_L3:
        return i;
_L4:
        byte byte2 = (byte)i;
        if (byte2 >= -32) goto _L6; else goto _L5
_L6:
        if (byte2 >= -16) goto _L8; else goto _L7
_L7:
        l = (byte)(~(i >> 8));
        if (l == 0)
        {
            l = j + 1;
            byte byte0 = abyte0[j];
            j = byte0;
            i = l;
            if (l >= k)
            {
                return Utf8.incompleteStateFor(byte2, byte0);
            }
        } else
        {
            i = j;
            j = l;
        }
        if (j > -65 || byte2 == -32 && j < -96 || byte2 == -19 && j >= -96) goto _L10; else goto _L9
_L9:
        l = i + 1;
        if (abyte0[i] <= -65) goto _L2; else goto _L10
_L10:
        return -1;
_L8:
        int i1;
        l = (byte)(~(i >> 8));
        if (l == 0)
        {
            i1 = j + 1;
            l = abyte0[j];
            if (i1 >= k)
            {
                return Utf8.incompleteStateFor(byte2, l);
            }
            break MISSING_BLOCK_LABEL_524;
        }
        i = (byte)(i >> 16);
_L20:
        if (i == 0)
        {
            i1 = j + 1;
            byte byte1 = abyte0[j];
            i = byte1;
            j = i1;
            if (i1 >= k)
            {
                return Utf8.incompleteStateFor(byte2, l, byte1);
            }
        }
        if (l > -65 || (byte2 << 28) + (l + 112) >> 30 != 0 || i > -65) goto _L12; else goto _L11
_L11:
        l = j + 1;
        if (abyte0[j] <= -65) goto _L2; else goto _L12
_L12:
        return -1;
_L5:
        if (byte2 < -62 || abyte0[j] > -65)
        {
            return -1;
        }
        l = j + 1;
_L2:
        for (j = l; j < k && abyte0[j] >= 0; j++) { }
        i = j;
        if (j >= k)
        {
            return 0;
        }
          goto _L13
_L15:
        i = j;
_L13:
        if (i >= k)
        {
            return 0;
        }
        j = i + 1;
        l = abyte0[i];
        if (l >= 0) goto _L15; else goto _L14
_L14:
        if (l >= -32)
        {
            break MISSING_BLOCK_LABEL_372;
        }
        i = l;
        if (j >= k) goto _L3; else goto _L16
_L16:
        if (l < -62)
        {
            break; /* Loop/switch isn't completed */
        }
        i = j + 1;
        if (abyte0[j] <= -65) goto _L13; else goto _L17
_L17:
        return -1;
        if (l >= -16)
        {
            break MISSING_BLOCK_LABEL_452;
        }
        if (j >= k - 1)
        {
            return Utf8.incompleteStateFor(abyte0, j, k);
        }
        i1 = j + 1;
        i = abyte0[j];
        if (i > -65 || l == -32 && i < -96 || l == -19 && i >= -96)
        {
            break; /* Loop/switch isn't completed */
        }
        i = i1 + 1;
        if (abyte0[i1] <= -65) goto _L13; else goto _L18
_L18:
        return -1;
        if (j >= k - 2)
        {
            return Utf8.incompleteStateFor(abyte0, j, k);
        }
        i = j + 1;
        j = abyte0[j];
        if (j > -65 || j + 112 + (l << 28) >> 30 != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        j = i + 1;
        if (abyte0[i] > -65)
        {
            break; /* Loop/switch isn't completed */
        }
        i = j + 1;
        if (abyte0[j] <= -65) goto _L13; else goto _L19
_L19:
        return -1;
        i = 0;
        j = i1;
          goto _L20
    }

    n()
    {
    }
}
