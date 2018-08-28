// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.io;

import java.io.IOException;
import java.io.Reader;

public final class CharStreams
{

    static long copyReaderToBuilder(Reader reader, StringBuilder stringbuilder)
        throws IOException
    {
        if (reader == null)
        {
            throw new NullPointerException();
        }
        if (stringbuilder == null)
        {
            throw new NullPointerException();
        }
        char ac[] = new char[2048];
        long l = 0L;
        do
        {
            int i = reader.read(ac);
            if (i != -1)
            {
                stringbuilder.append(ac, 0, i);
                l += i;
            } else
            {
                return l;
            }
        } while (true);
    }
}
