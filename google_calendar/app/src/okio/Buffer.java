// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package okio;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;

// Referenced classes of package okio:
//            BufferedSink, BufferedSource, Util, Segment, 
//            SegmentPool, ByteString, SegmentedByteString

public final class Buffer
    implements Cloneable, BufferedSink, BufferedSource
{

    public static final byte DIGITS[] = {
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 
        97, 98, 99, 100, 101, 102
    };
    public Segment head;
    public long size;

    public Buffer()
    {
    }

    private final String readString(long l, Charset charset)
        throws EOFException
    {
        Util.checkOffsetAndCount(size, 0L, l);
        if (charset == null)
        {
            throw new IllegalArgumentException("charset == null");
        }
        if (l > 0x7fffffffL)
        {
            throw new IllegalArgumentException((new StringBuilder("byteCount > Integer.MAX_VALUE: ")).append(l).toString());
        }
        if (l == 0L)
        {
            charset = "";
        } else
        {
            Segment segment = head;
            if ((long)segment.pos + l > (long)segment.limit)
            {
                return new String(readByteArray(l), charset);
            }
            String s = new String(segment.data, segment.pos, (int)l, charset);
            segment.pos = (int)((long)segment.pos + l);
            size = size - l;
            charset = s;
            if (segment.pos == segment.limit)
            {
                head = segment.pop();
                SegmentPool.recycle(segment);
                return s;
            }
        }
        return charset;
    }

    public final Buffer buffer()
    {
        return this;
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        Buffer buffer1 = new Buffer();
        if (size == 0L)
        {
            return buffer1;
        }
        buffer1.head = new Segment(head);
        Segment segment = buffer1.head;
        Segment segment2 = buffer1.head;
        Segment segment4 = buffer1.head;
        segment2.prev = segment4;
        segment.next = segment4;
        for (Segment segment1 = head.next; segment1 != head; segment1 = segment1.next)
        {
            Segment segment3 = buffer1.head.prev;
            Segment segment5 = new Segment(segment1);
            segment5.prev = segment3;
            segment5.next = segment3.next;
            segment3.next.prev = segment5;
            segment3.next = segment5;
        }

        buffer1.size = size;
        return buffer1;
    }

    public final void close()
    {
    }

    public final boolean equals(Object obj)
    {
        long l1 = 0L;
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof Buffer))
        {
            return false;
        }
        obj = (Buffer)obj;
        if (size != ((Buffer) (obj)).size)
        {
            return false;
        }
        if (size == 0L)
        {
            return true;
        }
        Object obj1 = head;
        obj = ((Buffer) (obj)).head;
        int j = ((Segment) (obj1)).pos;
        int i = ((Segment) (obj)).pos;
        while (l1 < size) 
        {
            long l2 = Math.min(((Segment) (obj1)).limit - j, ((Segment) (obj)).limit - i);
            for (int k = 0; (long)k < l2;)
            {
                if (((Segment) (obj1)).data[j] != ((Segment) (obj)).data[i])
                {
                    return false;
                }
                k++;
                i++;
                j++;
            }

            int l = j;
            Segment segment = ((Segment) (obj1));
            if (j == ((Segment) (obj1)).limit)
            {
                segment = ((Segment) (obj1)).next;
                l = segment.pos;
            }
            j = i;
            obj1 = obj;
            if (i == ((Segment) (obj)).limit)
            {
                obj1 = ((Segment) (obj)).next;
                j = ((Segment) (obj1)).pos;
            }
            l1 += l2;
            i = j;
            j = l;
            obj = obj1;
            obj1 = segment;
        }
        return true;
    }

    public final boolean exhausted()
    {
        return size == 0L;
    }

    public final void flush()
    {
    }

    public final byte getByte(long l)
    {
        Util.checkOffsetAndCount(size, l, 1L);
        Segment segment = head;
        do
        {
            int i = segment.limit - segment.pos;
            if (l < (long)i)
            {
                return segment.data[segment.pos + (int)l];
            }
            l -= i;
            segment = segment.next;
        } while (true);
    }

    public final int hashCode()
    {
        Segment segment = head;
        if (segment == null)
        {
            return 0;
        }
        int j = 1;
        Segment segment1;
        int i;
        do
        {
            int k = segment.pos;
            int l = segment.limit;
            for (i = j; k < l; i = j + i * 31)
            {
                j = segment.data[k];
                k++;
            }

            segment1 = segment.next;
            j = i;
            segment = segment1;
        } while (segment1 != head);
        return i;
    }

    public final int read(byte abyte0[], int i, int j)
    {
        Util.checkOffsetAndCount(abyte0.length, i, j);
        Segment segment = head;
        if (segment == null)
        {
            i = -1;
        } else
        {
            j = Math.min(j, segment.limit - segment.pos);
            System.arraycopy(segment.data, segment.pos, abyte0, i, j);
            segment.pos = segment.pos + j;
            size = size - (long)j;
            i = j;
            if (segment.pos == segment.limit)
            {
                head = segment.pop();
                SegmentPool.recycle(segment);
                return j;
            }
        }
        return i;
    }

    public final long read(Buffer buffer1, long l)
    {
        if (buffer1 == null)
        {
            throw new IllegalArgumentException("sink == null");
        }
        if (l < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder("byteCount < 0: ")).append(l).toString());
        }
        if (size == 0L)
        {
            return -1L;
        }
        long l1 = l;
        if (l > size)
        {
            l1 = size;
        }
        buffer1.write(this, l1);
        return l1;
    }

    public final byte readByte()
    {
        if (size == 0L)
        {
            throw new IllegalStateException("size == 0");
        }
        Segment segment = head;
        int i = segment.pos;
        int j = segment.limit;
        byte abyte0[] = segment.data;
        int k = i + 1;
        byte byte0 = abyte0[i];
        size = size - 1L;
        if (k == j)
        {
            head = segment.pop();
            SegmentPool.recycle(segment);
            return byte0;
        } else
        {
            segment.pos = k;
            return byte0;
        }
    }

    public final byte[] readByteArray()
    {
        byte abyte0[];
        try
        {
            abyte0 = readByteArray(size);
        }
        catch (EOFException eofexception)
        {
            throw new AssertionError(eofexception);
        }
        return abyte0;
    }

    public final byte[] readByteArray(long l)
        throws EOFException
    {
        Util.checkOffsetAndCount(size, 0L, l);
        if (l > 0x7fffffffL)
        {
            throw new IllegalArgumentException((new StringBuilder("byteCount > Integer.MAX_VALUE: ")).append(l).toString());
        }
        byte abyte0[] = new byte[(int)l];
        int j;
        for (int i = 0; i < abyte0.length; i += j)
        {
            j = read(abyte0, i, abyte0.length - i);
            if (j == -1)
            {
                throw new EOFException();
            }
        }

        return abyte0;
    }

    public final ByteString readByteString(long l)
        throws EOFException
    {
        return new ByteString(readByteArray(l));
    }

    public final int readInt()
    {
        if (size < 4L)
        {
            throw new IllegalStateException((new StringBuilder("size < 4: ")).append(size).toString());
        }
        Segment segment = head;
        int j = segment.pos;
        int i = segment.limit;
        if (i - j < 4)
        {
            return (readByte() & 0xff) << 24 | (readByte() & 0xff) << 16 | (readByte() & 0xff) << 8 | readByte() & 0xff;
        }
        byte abyte0[] = segment.data;
        int k = j + 1;
        j = abyte0[j];
        int i1 = k + 1;
        k = abyte0[k];
        int l = i1 + 1;
        byte byte0 = abyte0[i1];
        i1 = l + 1;
        j = (j & 0xff) << 24 | (k & 0xff) << 16 | (byte0 & 0xff) << 8 | abyte0[l] & 0xff;
        size = size - 4L;
        if (i1 == i)
        {
            head = segment.pop();
            SegmentPool.recycle(segment);
            return j;
        } else
        {
            segment.pos = i1;
            return j;
        }
    }

    public final short readShort()
    {
        if (size < 2L)
        {
            throw new IllegalStateException((new StringBuilder("size < 2: ")).append(size).toString());
        }
        Segment segment = head;
        int k = segment.pos;
        int i = segment.limit;
        if (i - k < 2)
        {
            return (short)((readByte() & 0xff) << 8 | readByte() & 0xff);
        }
        byte abyte0[] = segment.data;
        int j = k + 1;
        k = abyte0[k];
        int l = j + 1;
        j = abyte0[j];
        size = size - 2L;
        if (l == i)
        {
            head = segment.pop();
            SegmentPool.recycle(segment);
        } else
        {
            segment.pos = l;
        }
        return (short)((k & 0xff) << 8 | j & 0xff);
    }

    public final String readUtf8()
    {
        String s;
        try
        {
            s = readString(size, Util.UTF_8);
        }
        catch (EOFException eofexception)
        {
            throw new AssertionError(eofexception);
        }
        return s;
    }

    public final String readUtf8Line(long l)
        throws EOFException
    {
        if (l > 0L && getByte(l - 1L) == 13)
        {
            String s = readString(l - 1L, Util.UTF_8);
            skip(2L);
            return s;
        } else
        {
            String s1 = readString(l, Util.UTF_8);
            skip(1L);
            return s1;
        }
    }

    public final void require(long l)
        throws EOFException
    {
        if (size < l)
        {
            throw new EOFException();
        } else
        {
            return;
        }
    }

    public final void skip(long l)
        throws EOFException
    {
        do
        {
            if (l <= 0L)
            {
                break;
            }
            if (head == null)
            {
                throw new EOFException();
            }
            int i = (int)Math.min(l, head.limit - head.pos);
            size = size - (long)i;
            long l1 = l - (long)i;
            Segment segment = head;
            segment.pos = i + segment.pos;
            l = l1;
            if (head.pos == head.limit)
            {
                Segment segment1 = head;
                head = segment1.pop();
                SegmentPool.recycle(segment1);
                l = l1;
            }
        } while (true);
    }

    public final String toString()
    {
        if (size > 0x7fffffffL)
        {
            throw new IllegalArgumentException((new StringBuilder("size > Integer.MAX_VALUE: ")).append(size).toString());
        }
        int i = (int)size;
        Object obj;
        if (i == 0)
        {
            obj = ByteString.EMPTY;
        } else
        {
            obj = new SegmentedByteString(this, i);
        }
        return ((ByteString) (obj)).toString();
    }

    public final Segment writableSegment(int i)
    {
        if (i <= 0 || i > 8192)
        {
            throw new IllegalArgumentException();
        }
        if (head != null) goto _L2; else goto _L1
_L1:
        Segment segment;
        head = SegmentPool.take();
        Segment segment2 = head;
        Segment segment4 = head;
        segment = head;
        segment4.prev = segment;
        segment2.next = segment;
_L4:
        return segment;
_L2:
        Segment segment3;
        segment3 = head.prev;
        if (segment3.limit + i > 8192)
        {
            break; /* Loop/switch isn't completed */
        }
        segment = segment3;
        if (segment3.owner) goto _L4; else goto _L3
_L3:
        Segment segment1 = SegmentPool.take();
        segment1.prev = segment3;
        segment1.next = segment3.next;
        segment3.next.prev = segment1;
        segment3.next = segment1;
        return segment1;
    }

    public final Buffer write(byte abyte0[], int i, int j)
    {
        if (abyte0 == null)
        {
            throw new IllegalArgumentException("source == null");
        }
        Util.checkOffsetAndCount(abyte0.length, i, j);
        for (int k = i + j; i < k;)
        {
            Segment segment = writableSegment(1);
            int l = Math.min(k - i, 8192 - segment.limit);
            System.arraycopy(abyte0, i, segment.data, segment.limit, l);
            i += l;
            segment.limit = l + segment.limit;
        }

        size = size + (long)j;
        return this;
    }

    public final BufferedSink write(byte abyte0[])
        throws IOException
    {
        if (abyte0 == null)
        {
            throw new IllegalArgumentException("source == null");
        } else
        {
            return write(abyte0, 0, abyte0.length);
        }
    }

    public final void write(Buffer buffer1, long l)
    {
        if (buffer1 == null)
        {
            throw new IllegalArgumentException("source == null");
        }
        if (buffer1 == this)
        {
            throw new IllegalArgumentException("source == this");
        }
        Util.checkOffsetAndCount(buffer1.size, 0L, l);
_L2:
        Segment segment1;
label0:
        {
label1:
            {
                if (l > 0L)
                {
                    if (l >= (long)(buffer1.head.limit - buffer1.head.pos))
                    {
                        break label0;
                    }
                    Segment segment;
                    int i;
                    long l1;
                    if (head != null)
                    {
                        segment = head.prev;
                    } else
                    {
                        segment = null;
                    }
                    if (segment == null || !segment.owner)
                    {
                        break label1;
                    }
                    l1 = segment.limit;
                    if (segment.shared)
                    {
                        i = 0;
                    } else
                    {
                        i = segment.pos;
                    }
                    if ((l1 + l) - (long)i > 8192L)
                    {
                        break label1;
                    }
                    buffer1.head.writeTo(segment, (int)l);
                    buffer1.size = buffer1.size - l;
                    size = size + l;
                }
                return;
            }
            Segment segment2 = buffer1.head;
            int j = (int)l;
            if (j <= 0 || j > segment2.limit - segment2.pos)
            {
                throw new IllegalArgumentException();
            }
            Segment segment4;
            long l2;
            if (j >= 1024)
            {
                segment1 = new Segment(segment2);
            } else
            {
                segment1 = SegmentPool.take();
                System.arraycopy(segment2.data, segment2.pos, segment1.data, 0, j);
            }
            segment1.limit = segment1.pos + j;
            segment2.pos = j + segment2.pos;
            segment2 = segment2.prev;
            segment1.prev = segment2;
            segment1.next = segment2.next;
            segment2.next.prev = segment1;
            segment2.next = segment1;
            buffer1.head = segment1;
        }
        segment1 = buffer1.head;
        l2 = segment1.limit - segment1.pos;
        buffer1.head = segment1.pop();
        if (head != null)
        {
            break; /* Loop/switch isn't completed */
        }
        head = segment1;
        segment1 = head;
        segment2 = head;
        segment4 = head;
        segment2.prev = segment4;
        segment1.next = segment4;
_L3:
        buffer1.size = buffer1.size - l2;
        size = size + l2;
        l -= l2;
        if (true) goto _L2; else goto _L1
_L1:
        Segment segment3 = head.prev;
        segment1.prev = segment3;
        segment1.next = segment3.next;
        segment3.next.prev = segment1;
        segment3.next = segment1;
        if (segment1.prev == segment1)
        {
            throw new IllegalStateException();
        }
        if (segment1.prev.owner)
        {
            int i1 = segment1.limit - segment1.pos;
            int j1 = segment1.prev.limit;
            int k;
            if (segment1.prev.shared)
            {
                k = 0;
            } else
            {
                k = segment1.prev.pos;
            }
            if (i1 <= k + (8192 - j1))
            {
                segment1.writeTo(segment1.prev, i1);
                segment1.pop();
                SegmentPool.recycle(segment1);
            }
        }
          goto _L3
        if (true) goto _L2; else goto _L4
_L4:
    }

    public final BufferedSink writeByte(int i)
        throws IOException
    {
        Segment segment = writableSegment(1);
        byte abyte0[] = segment.data;
        int j = segment.limit;
        segment.limit = j + 1;
        abyte0[j] = (byte)i;
        size = size + 1L;
        return this;
    }

    public final BufferedSink writeInt(int i)
        throws IOException
    {
        Segment segment = writableSegment(4);
        byte abyte0[] = segment.data;
        int k = segment.limit;
        int j = k + 1;
        abyte0[k] = (byte)(i >>> 24);
        k = j + 1;
        abyte0[j] = (byte)(i >>> 16);
        j = k + 1;
        abyte0[k] = (byte)(i >>> 8);
        abyte0[j] = (byte)i;
        segment.limit = j + 1;
        size = size + 4L;
        return this;
    }

    public final BufferedSink writeShort(int i)
        throws IOException
    {
        Segment segment = writableSegment(2);
        byte abyte0[] = segment.data;
        int j = segment.limit;
        int k = j + 1;
        abyte0[j] = (byte)(i >>> 8);
        abyte0[k] = (byte)i;
        segment.limit = k + 1;
        size = size + 2L;
        return this;
    }

    public final Buffer writeUtf8(String s, int i, int j)
    {
        if (s == null)
        {
            throw new IllegalArgumentException("string == null");
        }
        if (i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder("beginIndex < 0: ")).append(i).toString());
        }
        if (j < i)
        {
            throw new IllegalArgumentException((new StringBuilder("endIndex < beginIndex: ")).append(j).append(" < ").append(i).toString());
        }
        if (j > s.length())
        {
            throw new IllegalArgumentException((new StringBuilder("endIndex > string.length: ")).append(j).append(" > ").append(s.length()).toString());
        }
          goto _L1
_L3:
        Segment segment;
        int i2;
        int k = (i + i2) - segment.limit;
        segment.limit = segment.limit + k;
        size = size + (long)k;
_L1:
        byte abyte0[];
        int l1;
        int j2;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_1010;
        }
        l1 = s.charAt(i);
        if (l1 >= '\200')
        {
            break MISSING_BLOCK_LABEL_278;
        }
        segment = writableSegment(1);
        abyte0 = segment.data;
        i2 = segment.limit - i;
        j2 = Math.min(j, 8192 - i2);
        int l = i + 1;
        abyte0[i2 + i] = (byte)l1;
        i = l;
_L5:
        if (i >= j2) goto _L3; else goto _L2
_L2:
        char c = s.charAt(i);
        if (c >= '\200') goto _L3; else goto _L4
_L4:
        abyte0[i + i2] = (byte)c;
        i++;
          goto _L5
        if (l1 < '\u0800')
        {
            Object obj = writableSegment(1);
            byte abyte1[] = ((Segment) (obj)).data;
            int i1 = ((Segment) (obj)).limit;
            obj.limit = i1 + 1;
            abyte1[i1] = (byte)(l1 >> 6 | 0xc0);
            size = size + 1L;
            obj = (Buffer)this;
            obj = writableSegment(1);
            abyte1 = ((Segment) (obj)).data;
            i1 = ((Segment) (obj)).limit;
            obj.limit = i1 + 1;
            abyte1[i1] = (byte)(l1 & 0x3f | 0x80);
            size = size + 1L;
            obj = (Buffer)this;
            i++;
        } else
        if (l1 < '\uD800' || l1 > '\uDFFF')
        {
            Object obj1 = writableSegment(1);
            byte abyte2[] = ((Segment) (obj1)).data;
            int j1 = ((Segment) (obj1)).limit;
            obj1.limit = j1 + 1;
            abyte2[j1] = (byte)(l1 >> 12 | 0xe0);
            size = size + 1L;
            obj1 = (Buffer)this;
            obj1 = writableSegment(1);
            abyte2 = ((Segment) (obj1)).data;
            j1 = ((Segment) (obj1)).limit;
            obj1.limit = j1 + 1;
            abyte2[j1] = (byte)(l1 >> 6 & 0x3f | 0x80);
            size = size + 1L;
            obj1 = (Buffer)this;
            obj1 = writableSegment(1);
            abyte2 = ((Segment) (obj1)).data;
            j1 = ((Segment) (obj1)).limit;
            obj1.limit = j1 + 1;
            abyte2[j1] = (byte)(l1 & 0x3f | 0x80);
            size = size + 1L;
            obj1 = (Buffer)this;
            i++;
        } else
        {
            int k1;
            if (i + 1 < j)
            {
                k1 = s.charAt(i + 1);
            } else
            {
                k1 = 0;
            }
            if (l1 > '\uDBFF' || k1 < 56320 || k1 > 57343)
            {
                Object obj2 = writableSegment(1);
                byte abyte3[] = ((Segment) (obj2)).data;
                k1 = ((Segment) (obj2)).limit;
                obj2.limit = k1 + 1;
                abyte3[k1] = (byte)63;
                size = size + 1L;
                obj2 = (Buffer)this;
                i++;
            } else
            {
                k1 = 0x10000 + (k1 & 0xffff23ff | (l1 & 0xffff27ff) << 10);
                Object obj3 = writableSegment(1);
                byte abyte4[] = ((Segment) (obj3)).data;
                l1 = ((Segment) (obj3)).limit;
                obj3.limit = l1 + 1;
                abyte4[l1] = (byte)(k1 >> 18 | 0xf0);
                size = size + 1L;
                obj3 = (Buffer)this;
                obj3 = writableSegment(1);
                abyte4 = ((Segment) (obj3)).data;
                l1 = ((Segment) (obj3)).limit;
                obj3.limit = l1 + 1;
                abyte4[l1] = (byte)(k1 >> 12 & 0x3f | 0x80);
                size = size + 1L;
                obj3 = (Buffer)this;
                obj3 = writableSegment(1);
                abyte4 = ((Segment) (obj3)).data;
                l1 = ((Segment) (obj3)).limit;
                obj3.limit = l1 + 1;
                abyte4[l1] = (byte)(k1 >> 6 & 0x3f | 0x80);
                size = size + 1L;
                obj3 = (Buffer)this;
                obj3 = writableSegment(1);
                abyte4 = ((Segment) (obj3)).data;
                l1 = ((Segment) (obj3)).limit;
                obj3.limit = l1 + 1;
                abyte4[l1] = (byte)(k1 & 0x3f | 0x80);
                size = size + 1L;
                obj3 = (Buffer)this;
                i += 2;
            }
        }
          goto _L1
        return this;
    }

    public final BufferedSink writeUtf8(String s)
        throws IOException
    {
        return writeUtf8(s, 0, s.length());
    }

}
