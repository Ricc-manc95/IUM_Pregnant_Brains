// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package okio;

import java.io.IOException;

// Referenced classes of package okio:
//            Sink, AsyncTimeout, Buffer, Util, 
//            Segment

final class it>
    implements Sink
{

    private final AsyncTimeout this$0;
    private final Sink val$sink;

    public final void close()
        throws IOException
    {
        enter();
        val$sink.close();
        exit(true);
        return;
        Object obj;
        obj;
        throw exit(((IOException) (obj)));
        obj;
        exit(false);
        throw obj;
    }

    public final void flush()
        throws IOException
    {
        enter();
        val$sink.flush();
        exit(true);
        return;
        Object obj;
        obj;
        throw exit(((IOException) (obj)));
        obj;
        exit(false);
        throw obj;
    }

    public final String toString()
    {
        return (new StringBuilder("AsyncTimeout.sink(")).append(val$sink).append(")").toString();
    }

    public final void write(Buffer buffer, long l)
        throws IOException
    {
        Util.checkOffsetAndCount(buffer.size, 0L, l);
_L2:
        long l2;
        if (l <= 0L)
        {
            break; /* Loop/switch isn't completed */
        }
        Segment segment = buffer.head;
        long l1 = 0L;
        do
        {
            l2 = l1;
            if (l1 >= 0x10000L)
            {
                break;
            }
            l2 = l1 + (long)(buffer.head.limit - buffer.head.pos);
            l1 = l2;
            if (l2 < l)
            {
                continue;
            }
            l2 = l;
            break;
        } while (true);
        enter();
        val$sink.write(buffer, l2);
        l -= l2;
        exit(true);
        if (true) goto _L2; else goto _L1
        buffer;
        throw exit(buffer);
        buffer;
        exit(false);
        throw buffer;
_L1:
    }

    der()
    {
        this$0 = final_asynctimeout;
        val$sink = Sink.this;
        super();
    }
}
