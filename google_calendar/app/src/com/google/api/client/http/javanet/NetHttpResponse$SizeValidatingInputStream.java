// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http.javanet;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

// Referenced classes of package com.google.api.client.http.javanet:
//            NetHttpResponse

final class bytesRead extends FilterInputStream
{

    private long bytesRead;
    private final NetHttpResponse this$0;

    private final void throwIfFalseEOF()
        throws IOException
    {
        String s = connection.getHeaderField("Content-Length");
        long l;
        if (s == null)
        {
            l = -1L;
        } else
        {
            l = Long.parseLong(s);
        }
        while (l == -1L || bytesRead == 0L || bytesRead >= l) 
        {
            return;
        }
        long l1 = bytesRead;
        throw new IOException((new StringBuilder(102)).append("Connection closed prematurely: bytesRead = ").append(l1).append(", Content-Length = ").append(l).toString());
    }

    public final int read()
        throws IOException
    {
        int i = in.read();
        if (i == -1)
        {
            throwIfFalseEOF();
            return i;
        } else
        {
            bytesRead = bytesRead + 1L;
            return i;
        }
    }

    public final int read(byte abyte0[], int i, int j)
        throws IOException
    {
        i = in.read(abyte0, i, j);
        if (i == -1)
        {
            throwIfFalseEOF();
            return i;
        } else
        {
            bytesRead = bytesRead + (long)i;
            return i;
        }
    }

    public (InputStream inputstream)
    {
        this$0 = NetHttpResponse.this;
        super(inputstream);
        bytesRead = 0L;
    }
}
