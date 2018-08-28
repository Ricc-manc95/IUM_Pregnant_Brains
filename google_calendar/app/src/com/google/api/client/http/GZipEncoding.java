// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http;

import com.google.api.client.util.StreamingContent;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

// Referenced classes of package com.google.api.client.http:
//            HttpEncoding

public final class GZipEncoding
    implements HttpEncoding
{

    public GZipEncoding()
    {
    }

    public final void encode(StreamingContent streamingcontent, OutputStream outputstream)
        throws IOException
    {
        outputstream = new GZIPOutputStream(new _cls1(outputstream));
        streamingcontent.writeTo(outputstream);
        outputstream.close();
    }

    public final String getName()
    {
        return "gzip";
    }

    private class _cls1 extends BufferedOutputStream
    {

        public final void close()
            throws IOException
        {
            try
            {
                flush();
                return;
            }
            catch (IOException ioexception)
            {
                return;
            }
        }

        _cls1(OutputStream outputstream)
        {
            super(outputstream);
        }
    }

}
