// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IoUtils
{

    public static long copy(InputStream inputstream, OutputStream outputstream)
        throws IOException
    {
        if (inputstream == null)
        {
            throw new NullPointerException();
        }
        if (outputstream == null)
        {
            throw new NullPointerException();
        }
        byte abyte0[] = new byte[16384];
        long l = 0L;
        do
        {
            int i = inputstream.read(abyte0);
            if (i != -1)
            {
                outputstream.write(abyte0, 0, i);
                l += i;
            } else
            {
                return l;
            }
        } while (true);
    }
}
