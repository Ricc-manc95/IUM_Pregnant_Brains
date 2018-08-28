// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.io;

import java.io.OutputStream;

final class  extends OutputStream
{

    public final String toString()
    {
        return "ByteStreams.nullOutputStream()";
    }

    public final void write(int i)
    {
    }

    public final void write(byte abyte0[])
    {
        if (abyte0 == null)
        {
            throw new NullPointerException();
        } else
        {
            return;
        }
    }

    public final void write(byte abyte0[], int i, int j)
    {
        if (abyte0 == null)
        {
            throw new NullPointerException();
        } else
        {
            return;
        }
    }

    ()
    {
    }
}
