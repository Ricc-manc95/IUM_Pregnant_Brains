// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            UnsafeUtil

final class Utf8
{

    public static final Processor processor;

    static int encodedLength(CharSequence charsequence)
    {
_L2:
        int i;
        if (j < i1)
        {
            char c = charsequence.charAt(j);
            if (c < '\u0800')
            {
                i += 127 - c >>> 31;
                j++;
                continue; /* Loop/switch isn't completed */
            }
            int k1 = charsequence.length();
            while (j < k1) 
            {
                char c1 = charsequence.charAt(j);
                int l;
                if (c1 < '\u0800')
                {
                    k += 127 - c1 >>> 31;
                    l = j;
                } else
                {
                    int j1 = k + 2;
                    l = j;
                    k = j1;
                    if ('\uD800' <= c1)
                    {
                        l = j;
                        k = j1;
                        if (c1 <= '\uDFFF')
                        {
                            if (Character.codePointAt(charsequence, j) < 0x10000)
                            {
                                throw new UnpairedSurrogateException(j, k1);
                            }
                            l = j + 1;
                            k = j1;
                        }
                    }
                }
                j = l + 1;
            }
            i += k;
        }
        if (i < i1)
        {
            long l1 = i;
            throw new IllegalArgumentException((new StringBuilder(54)).append("UTF-8 length does not fit in int: ").append(l1 + 0x100000000L).toString());
        } else
        {
            return i;
        }
        int k = 0;
        int i1 = charsequence.length();
        int j;
        for (j = 0; j < i1 && charsequence.charAt(j) < '\200'; j++) { }
        i = i1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    static int incompleteStateFor(int i)
    {
        int j = i;
        if (i > -12)
        {
            j = -1;
        }
        return j;
    }

    static int incompleteStateFor(int i, int j)
    {
        if (i > -12 || j > -65)
        {
            return -1;
        } else
        {
            return j << 8 ^ i;
        }
    }

    static int incompleteStateFor(int i, int j, int k)
    {
        if (i > -12 || j > -65 || k > -65)
        {
            return -1;
        } else
        {
            return j << 8 ^ i ^ k << 16;
        }
    }

    static int incompleteStateFor(byte abyte0[], int i, int j)
    {
        byte byte0 = abyte0[i - 1];
        j - i;
        JVM INSTR tableswitch 0 2: default 36
    //                   0 44
    //                   1 54
    //                   2 77;
           goto _L1 _L2 _L3 _L4
_L1:
        throw new AssertionError();
_L2:
        if (byte0 <= -12) goto _L6; else goto _L5
_L5:
        return -1;
_L6:
        return byte0;
_L3:
        i = abyte0[i];
        if (byte0 > -12 || i > -65) goto _L5; else goto _L7
_L7:
        return i << 8 ^ byte0;
_L4:
        return incompleteStateFor(byte0, abyte0[i], abyte0[i + 1]);
    }

    static 
    {
        Object obj;
        boolean flag;
        if (UnsafeUtil.HAS_UNSAFE_ARRAY_OPERATIONS && UnsafeUtil.HAS_UNSAFE_BYTEBUFFER_OPERATIONS)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj = new UnsafeProcessor();
        } else
        {
            obj = new SafeProcessor();
        }
        processor = ((Processor) (obj));
    }

    private class UnpairedSurrogateException extends IllegalArgumentException
    {

        UnpairedSurrogateException(int i, int j)
        {
            super((new StringBuilder(54)).append("Unpaired surrogate at index ").append(i).append(" of ").append(j).toString());
        }
    }


    private class UnsafeProcessor extends Processor
    {
        private class Processor
        {

            static void encodeUtf8Default(CharSequence charsequence, ByteBuffer bytebuffer)
            {
                int i;
                int l;
                int k1;
                k1 = charsequence.length();
                l = bytebuffer.position();
                i = 0;
_L2:
                int j;
                int k;
                if (i >= k1)
                {
                    break; /* Loop/switch isn't completed */
                }
                j = i;
                k = l;
                int i1 = charsequence.charAt(i);
                if (i1 >= '\200')
                {
                    break; /* Loop/switch isn't completed */
                }
                j = i;
                k = l;
                bytebuffer.put(l + i, (byte)i1);
                i++;
                if (true) goto _L2; else goto _L1
_L1:
                if (i != k1) goto _L4; else goto _L3
_L3:
                char c;
                j = i;
                k = l;
                byte byte0;
                try
                {
                    bytebuffer.position(l + i);
                    return;
                }
                catch (IndexOutOfBoundsException indexoutofboundsexception)
                {
                    i = j;
                }
                  goto _L5
_L10:
                if (i >= k1) goto _L7; else goto _L6
_L6:
                j = i;
                k = l;
                c = charsequence.charAt(i);
                if (c >= '\200') goto _L9; else goto _L8
_L8:
                j = i;
                k = l;
                bytebuffer.put(l, (byte)c);
_L13:
                i++;
                l++;
                  goto _L10
_L9:
                if (c >= '\u0800') goto _L12; else goto _L11
_L11:
                i1 = l + 1;
                byte0 = (byte)(c >>> 6 | 0xc0);
                k = i;
                j = i1;
                bytebuffer.put(l, byte0);
                k = i;
                j = i1;
                bytebuffer.put(i1, (byte)(c & 0x3f | 0x80));
                l = i1;
                  goto _L13
_L14:
                i1 = l + 1;
                k = i;
                j = i1;
                bytebuffer.put(l, (byte)(c >>> 12 | 0xe0));
                l = i1 + 1;
                byte0 = (byte)(c >>> 6 & 0x3f | 0x80);
                j = i;
                k = l;
                bytebuffer.put(i1, byte0);
                j = i;
                k = l;
                bytebuffer.put(l, (byte)(c & 0x3f | 0x80));
                  goto _L13
_L5:
                j = bytebuffer.position();
                k = Math.max(i, (k - bytebuffer.position()) + 1);
                c = charsequence.charAt(i);
                throw new ArrayIndexOutOfBoundsException((new StringBuilder(37)).append("Failed writing ").append(c).append(" at index ").append(k + j).toString());
_L15:
                i1 = i;
                if (i + 1 == k1)
                {
                    break MISSING_BLOCK_LABEL_468;
                }
                i++;
                j = i;
                k = l;
                char c1 = charsequence.charAt(i);
                j = i;
                k = l;
                if (Character.isSurrogatePair(c, c1))
                {
                    break MISSING_BLOCK_LABEL_488;
                }
                i1 = i;
                j = i1;
                k = l;
                throw new UnpairedSurrogateException(i1, k1);
                j = i;
                k = l;
                int l1 = Character.toCodePoint(c, c1);
                byte byte1;
                int j1;
                j1 = l + 1;
                byte1 = (byte)(l1 >>> 18 | 0xf0);
                bytebuffer.put(l, byte1);
                i1 = j1 + 1;
                byte1 = (byte)(l1 >>> 12 & 0x3f | 0x80);
                k = i;
                j = i1;
                bytebuffer.put(j1, byte1);
                l = i1 + 1;
                byte1 = (byte)(l1 >>> 6 & 0x3f | 0x80);
                j = i;
                k = l;
                bytebuffer.put(i1, byte1);
                j = i;
                k = l;
                bytebuffer.put(l, (byte)(l1 & 0x3f | 0x80));
                  goto _L13
_L7:
                j = i;
                k = l;
                bytebuffer.position(l);
                return;
                IndexOutOfBoundsException indexoutofboundsexception1;
                indexoutofboundsexception1;
                i = k;
                k = j;
                  goto _L5
                indexoutofboundsexception1;
                k = j1;
                  goto _L5
_L4:
                l += i;
                  goto _L10
_L12:
                if (c >= '\uD800' && '\uDFFF' >= c) goto _L15; else goto _L14
            }

            abstract int encodeUtf8(CharSequence charsequence, byte abyte0[], int i, int j);

            abstract void encodeUtf8Direct(CharSequence charsequence, ByteBuffer bytebuffer);

            abstract int partialIsValidUtf8(int i, byte abyte0[], int j, int k);

            Processor()
            {
            }
        }


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
                        throw new UnpairedSurrogateException(j - 1, k);
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
                throw new UnpairedSurrogateException(i, k);
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
            UnsafeUtil.MemoryAccessor memoryaccessor = UnsafeUtil.MEMORY_ACCESSOR;
            l1 = UnsafeUtil.BUFFER_ADDRESS_OFFSET;
            l6 = memoryaccessor.unsafe.getLong(bytebuffer, l1);
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
                        throw new UnpairedSurrogateException(k - 1, i1);
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
                throw new UnpairedSurrogateException(j, i1);
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

        UnsafeProcessor()
        {
        }
    }


    private class SafeProcessor extends Processor
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
                        throw new UnpairedSurrogateException(j1 - 1, l1);
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
                throw new UnpairedSurrogateException(j, l1);
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

        SafeProcessor()
        {
        }
    }

}
