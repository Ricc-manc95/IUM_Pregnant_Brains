// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IOUtils
{

    public static long copyStream(InputStream inputstream, OutputStream outputstream, boolean flag, int i)
        throws IOException
    {
        byte abyte0[];
        long l;
        abyte0 = new byte[1024];
        l = 0L;
_L1:
        i = inputstream.read(abyte0, 0, 1024);
        if (i == -1)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        l += i;
        outputstream.write(abyte0, 0, i);
          goto _L1
        Exception exception;
        exception;
        if (flag)
        {
            if (inputstream != null)
            {
                try
                {
                    inputstream.close();
                }
                // Misplaced declaration of an exception variable
                catch (InputStream inputstream) { }
            }
            if (outputstream != null)
            {
                try
                {
                    outputstream.close();
                }
                // Misplaced declaration of an exception variable
                catch (InputStream inputstream) { }
            }
        }
        throw exception;
        if (flag)
        {
            if (inputstream != null)
            {
                try
                {
                    inputstream.close();
                }
                // Misplaced declaration of an exception variable
                catch (InputStream inputstream) { }
            }
            if (outputstream != null)
            {
                try
                {
                    outputstream.close();
                }
                // Misplaced declaration of an exception variable
                catch (InputStream inputstream)
                {
                    return l;
                }
            }
        }
        return l;
    }
}
