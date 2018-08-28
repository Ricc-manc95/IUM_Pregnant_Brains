// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ApplicationThreadDeframer, MessageDeframer, GzipInflatingBuffer, CompositeReadableBuffer

final class this._cls0
    implements Runnable
{

    private final ApplicationThreadDeframer this$0;

    public final void run()
    {
        MessageDeframer messagedeframer;
        boolean flag2;
        flag2 = false;
        boolean flag1 = false;
        messagedeframer = deframer;
        boolean flag;
        if (messagedeframer.unprocessed == null && messagedeframer.fullStreamDecompressor == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        if (messagedeframer.fullStreamDecompressor == null) goto _L4; else goto _L3
_L3:
        GzipInflatingBuffer gzipinflatingbuffer = messagedeframer.fullStreamDecompressor;
        flag = flag1;
        if (!gzipinflatingbuffer.closed)
        {
            flag = true;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("GzipInflatingBuffer is closed"));
        }
        flag2 = gzipinflatingbuffer.isStalled;
_L6:
        if (!flag2)
        {
            break; /* Loop/switch isn't completed */
        }
        messagedeframer.close();
_L2:
        return;
_L4:
        if (messagedeframer.unprocessed.readableBytes == 0)
        {
            flag2 = true;
        }
        if (true) goto _L6; else goto _L5
_L5:
        messagedeframer.closeWhenComplete = true;
        return;
    }

    ()
    {
        this$0 = ApplicationThreadDeframer.this;
        super();
    }
}
