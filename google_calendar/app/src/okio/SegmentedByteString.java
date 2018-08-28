// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package okio;

import java.util.Arrays;

// Referenced classes of package okio:
//            ByteString, Buffer, Util, Segment

final class SegmentedByteString extends ByteString
{

    private final transient int directory[];
    private final transient byte segments[][];

    SegmentedByteString(Buffer buffer, int i)
    {
        boolean flag = false;
        super(null);
        Util.checkOffsetAndCount(buffer.size, 0L, i);
        Segment segment = buffer.head;
        int j = 0;
        for (int l = 0; l < i;)
        {
            if (segment.limit == segment.pos)
            {
                throw new AssertionError("s.limit == s.pos");
            }
            l += segment.limit - segment.pos;
            j++;
            segment = segment.next;
        }

        segments = new byte[j][];
        directory = new int[j << 1];
        buffer = buffer.head;
        int i1 = 0;
        for (int k = ((flag) ? 1 : 0); k < i;)
        {
            segments[i1] = ((Segment) (buffer)).data;
            int j1 = (((Segment) (buffer)).limit - ((Segment) (buffer)).pos) + k;
            k = j1;
            if (j1 > i)
            {
                k = i;
            }
            directory[i1] = k;
            directory[segments.length + i1] = ((Segment) (buffer)).pos;
            buffer.shared = true;
            i1++;
            buffer = ((Segment) (buffer)).next;
        }

    }

    private final Object writeReplace()
    {
        return new ByteString(toByteArray());
    }

    public final String base64()
    {
        return (new ByteString(toByteArray())).base64();
    }

    public final boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        return (obj instanceof ByteString) && ((ByteString)obj).size() == size() && rangeEquals(0, (ByteString)obj, 0, size());
    }

    public final byte getByte(int i)
    {
        Util.checkOffsetAndCount(directory[segments.length - 1], i, 1L);
        int j = Arrays.binarySearch(directory, 0, segments.length, i + 1);
        int k;
        int l;
        if (j < 0)
        {
            j = ~j;
        }
        if (j == 0)
        {
            k = 0;
        } else
        {
            k = directory[j - 1];
        }
        l = directory[segments.length + j];
        return segments[j][(i - k) + l];
    }

    public final int hashCode()
    {
        int i = hashCode;
        if (i != 0)
        {
            return i;
        }
        i = 1;
        int k1 = segments.length;
        int j = 0;
        int i1;
        for (int k = 0; j < k1; k = i1)
        {
            byte abyte0[] = segments[j];
            int j1 = directory[k1 + j];
            i1 = directory[j];
            for (int l = j1; l < (i1 - k) + j1; l++)
            {
                i = i * 31 + abyte0[l];
            }

            j++;
        }

        hashCode = i;
        return i;
    }

    public final String hex()
    {
        return (new ByteString(toByteArray())).hex();
    }

    public final boolean rangeEquals(int i, ByteString bytestring, int j, int k)
    {
        if (size() - k >= 0) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int l = Arrays.binarySearch(directory, 0, segments.length, 1);
        int i1;
        if (l < 0)
        {
            l = ~l;
        }
        i1 = l;
        l = i;
        i = i1;
        do
        {
label0:
            {
                if (k <= 0)
                {
                    break label0;
                }
                int j1;
                int k1;
                int l1;
                if (i == 0)
                {
                    j1 = 0;
                } else
                {
                    j1 = directory[i - 1];
                }
                k1 = Math.min(k, ((directory[i] - j1) + j1) - l);
                l1 = directory[segments.length + i];
                if (!bytestring.rangeEquals(j, segments[i], (l - j1) + l1, k1))
                {
                    continue; /* Loop/switch isn't completed */
                }
                l += k1;
                j += k1;
                k -= k1;
                i++;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        return true;
    }

    public final boolean rangeEquals(int i, byte abyte0[], int j, int k)
    {
        if (i >= 0 && i <= size() - k && j >= 0 && j <= abyte0.length - k) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int l = Arrays.binarySearch(directory, 0, segments.length, i + 1);
        int i1;
        if (l < 0)
        {
            l = ~l;
        }
        i1 = l;
        l = i;
        i = i1;
        do
        {
label0:
            {
                if (k <= 0)
                {
                    break label0;
                }
                int j1;
                int k1;
                int l1;
                if (i == 0)
                {
                    j1 = 0;
                } else
                {
                    j1 = directory[i - 1];
                }
                k1 = Math.min(k, ((directory[i] - j1) + j1) - l);
                l1 = directory[segments.length + i];
                if (!Util.arrayRangeEquals(segments[i], (l - j1) + l1, abyte0, j, k1))
                {
                    continue; /* Loop/switch isn't completed */
                }
                l += k1;
                j += k1;
                k -= k1;
                i++;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        return true;
    }

    public final int size()
    {
        return directory[segments.length - 1];
    }

    public final ByteString substring(int i, int j)
    {
        return (new ByteString(toByteArray())).substring(i, j);
    }

    public final ByteString toAsciiLowercase()
    {
        return (new ByteString(toByteArray())).toAsciiLowercase();
    }

    public final byte[] toByteArray()
    {
        int i = 0;
        byte abyte0[] = new byte[directory[segments.length - 1]];
        int l = segments.length;
        int k;
        for (int j = 0; i < l; j = k)
        {
            int i1 = directory[l + i];
            k = directory[i];
            System.arraycopy(segments[i], i1, abyte0, j, k - j);
            i++;
        }

        return abyte0;
    }

    public final String toString()
    {
        return (new ByteString(toByteArray())).toString();
    }

    public final String utf8()
    {
        return (new ByteString(toByteArray())).utf8();
    }

    final void write(Buffer buffer)
    {
        int i = 0;
        int l = segments.length;
        int j = 0;
        while (i < l) 
        {
            int i1 = directory[l + i];
            int k = directory[i];
            Segment segment = new Segment(segments[i], i1, (i1 + k) - j);
            if (buffer.head == null)
            {
                segment.prev = segment;
                segment.next = segment;
                buffer.head = segment;
            } else
            {
                Segment segment1 = buffer.head.prev;
                segment.prev = segment1;
                segment.next = segment1.next;
                segment1.next.prev = segment;
                segment1.next = segment;
            }
            i++;
            j = k;
        }
        long l1 = buffer.size;
        buffer.size = (long)j + l1;
    }
}
