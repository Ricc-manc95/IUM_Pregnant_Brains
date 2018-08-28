// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import java.io.IOException;
import java.io.OutputStream;

final class ByteCountingOutputStream extends OutputStream
{

    public long count;

    ByteCountingOutputStream()
    {
    }

    public final void write(int i)
        throws IOException
    {
        count = count + 1L;
    }

    public final void write(byte abyte0[], int i, int j)
        throws IOException
    {
        count = count + (long)j;
    }
}
