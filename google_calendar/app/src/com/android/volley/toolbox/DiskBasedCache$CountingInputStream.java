// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

final class length extends FilterInputStream
{

    public long bytesRead;
    public final long length;

    public final int read()
        throws IOException
    {
        int i = super.read();
        if (i != -1)
        {
            bytesRead = bytesRead + 1L;
        }
        return i;
    }

    public final int read(byte abyte0[], int i, int j)
        throws IOException
    {
        i = super.read(abyte0, i, j);
        if (i != -1)
        {
            bytesRead = bytesRead + (long)i;
        }
        return i;
    }

    (InputStream inputstream, long l)
    {
        super(inputstream);
        length = l;
    }
}
