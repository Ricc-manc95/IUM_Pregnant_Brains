// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.stitch.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public final class ByteBufferUtils
{

    public static void toFile(ByteBuffer bytebuffer, String s)
        throws IOException
    {
        s = (new RandomAccessFile(s, "rw")).getChannel();
        s.write(bytebuffer);
        bytebuffer.position(0);
        s.close();
        return;
        bytebuffer;
        s.close();
        throw bytebuffer;
    }
}
