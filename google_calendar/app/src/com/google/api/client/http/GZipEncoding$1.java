// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

final class  extends BufferedOutputStream
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

    (OutputStream outputstream)
    {
        super(outputstream);
    }
}
