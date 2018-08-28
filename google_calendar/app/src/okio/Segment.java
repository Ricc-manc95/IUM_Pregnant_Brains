// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package okio;


public final class Segment
{

    public final byte data[];
    public int limit;
    public Segment next;
    public boolean owner;
    public int pos;
    public Segment prev;
    public boolean shared;

    Segment()
    {
        data = new byte[8192];
        owner = true;
        shared = false;
    }

    public Segment(Segment segment)
    {
        this(segment.data, segment.pos, segment.limit);
        segment.shared = true;
    }

    Segment(byte abyte0[], int i, int j)
    {
        data = abyte0;
        pos = i;
        limit = j;
        owner = false;
        shared = true;
    }

    public final Segment pop()
    {
        Segment segment;
        if (next != this)
        {
            segment = next;
        } else
        {
            segment = null;
        }
        prev.next = next;
        next.prev = prev;
        next = null;
        prev = null;
        return segment;
    }

    public final void writeTo(Segment segment, int i)
    {
        if (!segment.owner)
        {
            throw new IllegalArgumentException();
        }
        if (segment.limit + i > 8192)
        {
            if (segment.shared)
            {
                throw new IllegalArgumentException();
            }
            if ((segment.limit + i) - segment.pos > 8192)
            {
                throw new IllegalArgumentException();
            }
            System.arraycopy(segment.data, segment.pos, segment.data, 0, segment.limit - segment.pos);
            segment.limit = segment.limit - segment.pos;
            segment.pos = 0;
        }
        System.arraycopy(data, pos, segment.data, segment.limit, i);
        segment.limit = segment.limit + i;
        pos = pos + i;
    }
}
