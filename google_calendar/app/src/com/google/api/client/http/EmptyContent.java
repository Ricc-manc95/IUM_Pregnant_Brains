// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package com.google.api.client.http:
//            HttpContent

public final class EmptyContent
    implements HttpContent
{

    public EmptyContent()
    {
    }

    public final long getLength()
        throws IOException
    {
        return 0L;
    }

    public final String getType()
    {
        return null;
    }

    public final boolean retrySupported()
    {
        return true;
    }

    public final void writeTo(OutputStream outputstream)
        throws IOException
    {
        outputstream.flush();
    }
}
