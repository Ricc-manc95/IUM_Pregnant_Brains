// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// Referenced classes of package io.grpc:
//            Codec

public final class 
    implements Codec
{

    public static final Codec NONE = new <init>();

    public final OutputStream compress(OutputStream outputstream)
        throws IOException
    {
        return outputstream;
    }

    public final InputStream decompress(InputStream inputstream)
        throws IOException
    {
        return inputstream;
    }

    public final String getMessageEncoding()
    {
        return "identity";
    }


    private ()
    {
    }
}
