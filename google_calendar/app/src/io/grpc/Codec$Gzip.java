// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

// Referenced classes of package io.grpc:
//            Codec

public final class 
    implements Codec
{

    public final OutputStream compress(OutputStream outputstream)
        throws IOException
    {
        return new GZIPOutputStream(outputstream);
    }

    public final InputStream decompress(InputStream inputstream)
        throws IOException
    {
        return new GZIPInputStream(inputstream);
    }

    public final String getMessageEncoding()
    {
        return "gzip";
    }

    public ()
    {
    }
}
