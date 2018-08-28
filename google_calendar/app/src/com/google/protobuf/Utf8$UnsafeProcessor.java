// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.nio.ByteBuffer;
import sun.misc.Unsafe;

// Referenced classes of package com.google.protobuf:
//            Utf8, UnsafeUtil

final class eException extends eException
{

    private static int unsafeIncompleteStateFor(byte abyte0[], int i, long l, int j)
    {
        switch (j)
        {
        default:
            throw new AssertionError();

        case 0: // '\0'
            return Utf8.incompleteStateFor(i);

        case 1: // '\001'
            return Utf8.incompleteStateFor(i, UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + l));

        case 2: // '\002'
            return Utf8.incompleteStateFor(i, UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + l), UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, 1L + l + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET));
        }
    }

    final int encodeUtf8(CharSequence charsequence, byte abyte0[], int i, int j)
    {
        int k;
        long l;
        long l5;
        l = i;
        l5 = l + (long)j;
        k = charsequence.length();
        if (k > j || abyte0.length - j < i)
        {
            char c = charsequence.charAt(k - 1);
            throw new ArrayIndexOutOfBoundsException((new StringBuilder(37)).append("Failed writing ").append(c).append(" at index ").append(i + j).toString());
        }
        i = 0;
        do
        {
            if (i >= k)
            {
                break;
            }
            j = charsequence.charAt(i);
            if (j >= 128)
            {
                break;
            }
            byte byte0 = (byte)j;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte0, l + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte0);
            i++;
            l = 1L + l;
        } while (true);
        if (i == k)
        {
            return (int)l;
        }
_L2:
        char c1;
        if (i >= k)
        {
            break MISSING_BLOCK_LABEL_749;
        }
        c1 = charsequence.charAt(i);
        if (c1 >= '\200' || l >= l5)
        {
            break; /* Loop/switch isn't completed */
        }
        long l1 = 1L + l;
        byte byte1 = (byte)c1;
        UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte0, l + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte1);
        l = l1;
_L3:
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        if (c1 < '\u0800' && l <= l5 - 2L)
        {
            long l2 = l + 1L;
            byte byte2 = (byte)(c1 >>> 6 | 0x3c0);
            UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte0, l + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte2);
            l = 1L + l2;
            byte2 = (byte)(c1 & 0x3f | 0x80);
            UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte0, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + l2, byte2);
        } else
        if ((c1 < '\uD800' || '\uDFFF' < c1) && l <= l5 - 3L)
        {
            long l3 = 1L + l;
            byte byte3 = (byte)(c1 >>> 12 | 0x1e0);
            UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte0, l + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte3);
            long l6 = 1L + l3;
            byte3 = (byte)(c1 >>> 6 & 0x3f | 0x80);
            UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte0, l3 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte3);
            l = 1L + l6;
            byte3 = (byte)(c1 & 0x3f | 0x80);
            UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte0, l6 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte3);
        } else
        {
label0:
            {
                char c2;
label1:
                {
                    if (l > l5 - 4L)
                    {
                        break label0;
                    }
                    j = i;
                    if (i + 1 != k)
                    {
                        i++;
                        c2 = charsequence.charAt(i);
                        if (Character.isSurrogatePair(c1, c2))
                        {
                            break label1;
                        }
                        j = i;
                    }
                    throw new eException(j - 1, k);
                }
                j = Character.toCodePoint(c1, c2);
                long l4 = 1L + l;
                byte byte4 = (byte)(j >>> 18 | 0xf0);
                UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte0, l + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte4);
                l = 1L + l4;
                byte4 = (byte)(j >>> 12 & 0x3f | 0x80);
                UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte0, l4 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte4);
                l4 = l + 1L;
                byte4 = (byte)(j >>> 6 & 0x3f | 0x80);
                UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte0, l + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte4);
                l = 1L + l4;
                byte4 = (byte)(j & 0x3f | 0x80);
                UnsafeUtil.MEMORY_ACCESSOR.putByte(abyte0, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + l4, byte4);
            }
        }
          goto _L3
        if (true) goto _L2; else goto _L4
_L4:
        if ('\uD800' <= c1 && c1 <= '\uDFFF' && (i + 1 == k || !Character.isSurrogatePair(c1, charsequence.charAt(i + 1))))
        {
            throw new eException(i, k);
        } else
        {
            throw new ArrayIndexOutOfBoundsException((new StringBuilder(46)).append("Failed writing ").append(c1).append(" at index ").append(l).toString());
        }
        return (int)l;
    }

    final void encodeUtf8Direct(CharSequence charsequence, ByteBuffer bytebuffer)
    {
        int j;
        int i1;
        long l1;
        long l6;
        long l7;
        ssor ssor = UnsafeUtil.MEMORY_ACCESSOR;
        l1 = UnsafeUtil.BUFFER_ADDRESS_OFFSET;
        l6 = ssor.unsafe.getLong(bytebuffer, l1);
        l1 = l6 + (long)bytebuffer.position();
        l7 = l6 + (long)bytebuffer.limit();
        i1 = charsequence.length();
        if ((long)i1 > l7 - l1)
        {
            char c = charsequence.charAt(i1 - 1);
            int i = bytebuffer.limit();
            throw new ArrayIndexOutOfBoundsException((new StringBuilder(37)).append("Failed writing ").append(c).append(" at index ").append(i).toString());
        }
        j = 0;
        do
        {
            if (j >= i1)
            {
                break;
            }
            char c3 = charsequence.charAt(j);
            if (c3 >= '\200')
            {
                break;
            }
            byte byte0 = (byte)c3;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(l1, byte0);
            j++;
            l1 = 1L + l1;
        } while (true);
        if (j == i1)
        {
            bytebuffer.position((int)(l1 - l6));
            return;
        }
_L2:
        char c1;
        if (j >= i1)
        {
            break MISSING_BLOCK_LABEL_730;
        }
        c1 = charsequence.charAt(j);
        if (c1 >= '\200' || l1 >= l7)
        {
            break; /* Loop/switch isn't completed */
        }
        long l2 = 1L + l1;
        byte byte1 = (byte)c1;
        UnsafeUtil.MEMORY_ACCESSOR.putByte(l1, byte1);
        l1 = l2;
_L3:
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        if (c1 < '\u0800' && l1 <= l7 - 2L)
        {
            long l3 = l1 + 1L;
            byte byte2 = (byte)(c1 >>> 6 | 0x3c0);
            UnsafeUtil.MEMORY_ACCESSOR.putByte(l1, byte2);
            l1 = 1L + l3;
            byte2 = (byte)(c1 & 0x3f | 0x80);
            UnsafeUtil.MEMORY_ACCESSOR.putByte(l3, byte2);
        } else
        if ((c1 < '\uD800' || '\uDFFF' < c1) && l1 <= l7 - 3L)
        {
            long l4 = 1L + l1;
            byte byte3 = (byte)(c1 >>> 12 | 0x1e0);
            UnsafeUtil.MEMORY_ACCESSOR.putByte(l1, byte3);
            long l8 = 1L + l4;
            byte3 = (byte)(c1 >>> 6 & 0x3f | 0x80);
            UnsafeUtil.MEMORY_ACCESSOR.putByte(l4, byte3);
            l1 = 1L + l8;
            byte3 = (byte)(c1 & 0x3f | 0x80);
            UnsafeUtil.MEMORY_ACCESSOR.putByte(l8, byte3);
        } else
        {
label0:
            {
                char c2;
label1:
                {
                    if (l1 > l7 - 4L)
                    {
                        break label0;
                    }
                    int k = j;
                    if (j + 1 != i1)
                    {
                        j++;
                        c2 = charsequence.charAt(j);
                        if (Character.isSurrogatePair(c1, c2))
                        {
                            break label1;
                        }
                        k = j;
                    }
                    throw new eException(k - 1, i1);
                }
                int l = Character.toCodePoint(c1, c2);
                long l5 = 1L + l1;
                byte byte4 = (byte)(l >>> 18 | 0xf0);
                UnsafeUtil.MEMORY_ACCESSOR.putByte(l1, byte4);
                l1 = 1L + l5;
                byte4 = (byte)(l >>> 12 & 0x3f | 0x80);
                UnsafeUtil.MEMORY_ACCESSOR.putByte(l5, byte4);
                l5 = l1 + 1L;
                byte4 = (byte)(l >>> 6 & 0x3f | 0x80);
                UnsafeUtil.MEMORY_ACCESSOR.putByte(l1, byte4);
                l1 = 1L + l5;
                byte4 = (byte)(l & 0x3f | 0x80);
                UnsafeUtil.MEMORY_ACCESSOR.putByte(l5, byte4);
            }
        }
          goto _L3
        if (true) goto _L2; else goto _L4
_L4:
        if ('\uD800' <= c1 && c1 <= '\uDFFF' && (j + 1 == i1 || !Character.isSurrogatePair(c1, charsequence.charAt(j + 1))))
        {
            throw new eException(j, i1);
        } else
        {
            throw new ArrayIndexOutOfBoundsException((new StringBuilder(46)).append("Failed writing ").append(c1).append(" at index ").append(l1).toString());
        }
        bytebuffer.position((int)(l1 - l6));
        return;
    }

    final int partialIsValidUtf8(int i, byte abyte0[], int j, int k)
    {
        long l1;
        long l4;
        if ((j | k | abyte0.length - k) < 0)
        {
            throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", new Object[] {
                Integer.valueOf(abyte0.length), Integer.valueOf(j), Integer.valueOf(k)
            }));
        }
        l1 = j;
        l4 = k;
        if (i == 0) goto _L2; else goto _L1
_L1:
        if (l1 < l4) goto _L4; else goto _L3
_L3:
        j = i;
_L23:
        return j;
_L4:
        byte byte0 = (byte)i;
        if (byte0 >= -32) goto _L6; else goto _L5
_L5:
        if (byte0 < -62) goto _L8; else goto _L7
_L7:
        long l = l1 + 1L;
        if (UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET) <= -65) goto _L9; else goto _L8
_L8:
        return -1;
_L6:
        if (byte0 >= -16) goto _L11; else goto _L10
_L10:
        i = (byte)(~(i >> 8));
        if (i == 0)
        {
            l = 1L + l1;
            j = UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET);
            i = j;
            l1 = l;
            if (l >= l4)
            {
                return Utf8.incompleteStateFor(byte0, j);
            }
        }
        if (i > -65 || byte0 == -32 && i < -96 || byte0 == -19 && i >= -96) goto _L13; else goto _L12
_L12:
        l = l1 + 1L;
        if (UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET) <= -65) goto _L9; else goto _L13
_L13:
        return -1;
_L11:
        j = (byte)(~(i >> 8));
        if (j == 0)
        {
            l = 1L + l1;
            j = UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET);
            if (l >= l4)
            {
                return Utf8.incompleteStateFor(byte0, j);
            }
            break MISSING_BLOCK_LABEL_829;
        }
        i = (byte)(i >> 16);
        l = l1;
_L28:
        if (i == 0)
        {
            long l2 = 1L + l;
            k = UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, l + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET);
            i = k;
            l1 = l2;
            if (l2 >= l4)
            {
                return Utf8.incompleteStateFor(byte0, j, k);
            }
        } else
        {
            l1 = l;
        }
        if (j > -65 || j + 112 + (byte0 << 28) >> 30 != 0 || i > -65) goto _L15; else goto _L14
_L14:
        l = l1 + 1L;
        if (UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET) <= -65) goto _L9; else goto _L15
_L15:
        return -1;
_L2:
        l = l1;
_L9:
        k = (int)(l4 - l);
        if (k >= 16) goto _L17; else goto _L16
_L16:
        i = 0;
_L20:
        l = (long)i + l;
        i = k - i;
_L25:
        k = 0;
        j = i;
        i = k;
        do
        {
            if (j <= 0)
            {
                break MISSING_BLOCK_LABEL_822;
            }
            l1 = 1L + l;
            i = UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, l + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET);
            if (i < 0)
            {
                break;
            }
            j--;
            l = l1;
        } while (true);
          goto _L18
_L17:
        j = 0;
        l1 = l;
_L21:
        if (j >= k)
        {
            break MISSING_BLOCK_LABEL_527;
        }
        i = j;
        if (UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET) < 0) goto _L20; else goto _L19
_L19:
        j++;
        l1 = 1L + l1;
          goto _L21
        i = k;
          goto _L20
_L18:
        if (j == 0)
        {
            return 0;
        }
        k = j - 1;
        if (i >= -32)
        {
            break MISSING_BLOCK_LABEL_596;
        }
        j = i;
        if (k == 0) goto _L23; else goto _L22
_L22:
        j = k - 1;
        if (i < -62)
        {
            break; /* Loop/switch isn't completed */
        }
        l = 1L + l1;
        i = j;
        if (UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET) <= -65) goto _L25; else goto _L24
_L24:
        return -1;
        long l3;
        if (i >= -16)
        {
            break MISSING_BLOCK_LABEL_705;
        }
        if (k < 2)
        {
            return unsafeIncompleteStateFor(abyte0, i, l1, k);
        }
        j = k - 2;
        l3 = l1 + 1L;
        k = UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + l1);
        if (k > -65 || i == -32 && k < -96 || i == -19 && k >= -96)
        {
            break; /* Loop/switch isn't completed */
        }
        l = 1L + l3;
        i = j;
        if (UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + l3) <= -65) goto _L25; else goto _L26
_L26:
        return -1;
        if (k < 3)
        {
            return unsafeIncompleteStateFor(abyte0, i, l1, k);
        }
        j = k - 3;
        l = 1L + l1;
        k = UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET);
        if (k > -65 || k + 112 + (i << 28) >> 30 != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        l1 = 1L + l;
        if (UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, l + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET) > -65)
        {
            break; /* Loop/switch isn't completed */
        }
        l = 1L + l1;
        i = j;
        if (UnsafeUtil.MEMORY_ACCESSOR.getByte(abyte0, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET) <= -65) goto _L25; else goto _L27
_L27:
        return -1;
        l1 = l;
          goto _L18
        i = 0;
          goto _L28
    }

    eException()
    {
    }
}
