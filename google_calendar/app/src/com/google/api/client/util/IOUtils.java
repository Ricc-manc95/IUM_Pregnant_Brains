// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// Referenced classes of package com.google.api.client.util:
//            ByteCountingOutputStream, StreamingContent

public final class IOUtils
{

    public static long computeLength(StreamingContent streamingcontent)
        throws IOException
    {
        ByteCountingOutputStream bytecountingoutputstream = new ByteCountingOutputStream();
        streamingcontent.writeTo(bytecountingoutputstream);
        bytecountingoutputstream.close();
        return bytecountingoutputstream.count;
        streamingcontent;
        bytecountingoutputstream.close();
        throw streamingcontent;
    }

    public static void copy(InputStream inputstream, OutputStream outputstream, boolean flag)
        throws IOException
    {
        if (inputstream != null)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        throw new NullPointerException();
        outputstream;
        if (flag)
        {
            inputstream.close();
        }
        throw outputstream;
        if (outputstream != null)
        {
            break MISSING_BLOCK_LABEL_35;
        }
        throw new NullPointerException();
        byte abyte0[] = new byte[4096];
        long l = 0L;
_L2:
        int i = inputstream.read(abyte0);
        if (i == -1)
        {
            break; /* Loop/switch isn't completed */
        }
        outputstream.write(abyte0, 0, i);
        l += i;
        if (true) goto _L2; else goto _L1
_L1:
        if (flag)
        {
            inputstream.close();
        }
        return;
    }
}
