// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;

import com.google.android.libraries.performance.primes.hprof.collect.IntIntMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public final class ParseContext
{

    public final ByteBuffer buffer;
    private final ByteBuffer duplicate;
    public final int idSize;
    public final IntIntMap rootTagSizes;
    public final int typeSizes[];

    private ParseContext(ByteBuffer bytebuffer)
    {
        bytebuffer.rewind();
        bytebuffer.order(ByteOrder.BIG_ENDIAN);
        buffer = bytebuffer;
        duplicate = bytebuffer.duplicate();
        while (bytebuffer.get() != 0) ;
        idSize = bytebuffer.getInt();
        boolean flag;
        if (idSize > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            bytebuffer.getLong();
            int i = idSize;
            bytebuffer = new int[12];
            bytebuffer[2] = i;
            bytebuffer[4] = 1;
            bytebuffer[5] = 2;
            bytebuffer[6] = 4;
            bytebuffer[7] = 8;
            bytebuffer[8] = 1;
            bytebuffer[9] = 2;
            bytebuffer[10] = 4;
            bytebuffer[11] = 8;
            typeSizes = bytebuffer;
            rootTagSizes = new IntIntMap();
            i = idSize;
            class .Lambda._cls0
                implements Hprofs.RootTagSizeMapper
            {

                private final ParseContext arg$1;

                public final void addRootSize(int j, int k)
                {
                    arg$1.rootTagSizes.putIfAbsent(j, k);
                }

            .Lambda._cls0()
            {
                arg$1 = ParseContext.this;
            }
            }

            bytebuffer = new .Lambda._cls0();
            bytebuffer.addRootSize(137, i);
            bytebuffer.addRootSize(255, i);
            bytebuffer.addRootSize(139, i);
            bytebuffer.addRootSize(144, i);
            bytebuffer.addRootSize(138, i);
            bytebuffer.addRootSize(5, i);
            bytebuffer.addRootSize(7, i);
            bytebuffer.addRootSize(140, i);
            bytebuffer.addRootSize(141, i);
            bytebuffer.addRootSize(1, i + i);
            bytebuffer.addRootSize(3, i + 8);
            bytebuffer.addRootSize(2, i + 8);
            bytebuffer.addRootSize(8, i + 8);
            bytebuffer.addRootSize(142, i + 8);
            bytebuffer.addRootSize(4, i + 4);
            bytebuffer.addRootSize(6, i + 4);
            return;
        }
    }

    public static ParseContext prepareContext(File file)
        throws IOException
    {
        FileChannel filechannel = null;
        FileInputStream fileinputstream = new FileInputStream(file);
        FileChannel filechannel1 = fileinputstream.getChannel();
        file = new ParseContext(filechannel1.map(java.nio.channels.FileChannel.MapMode.READ_ONLY, 0L, filechannel1.size()));
        if (filechannel1 != null)
        {
            filechannel1.close();
        }
        fileinputstream.close();
        return file;
        file;
        fileinputstream = null;
_L2:
        if (filechannel != null)
        {
            filechannel.close();
        }
        if (fileinputstream != null)
        {
            fileinputstream.close();
        }
        throw file;
        file;
        continue; /* Loop/switch isn't completed */
        file;
        filechannel = filechannel1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final int readId()
    {
        switch (idSize)
        {
        case 3: // '\003'
        default:
            throw new IllegalStateException();

        case 4: // '\004'
            return buffer.getInt();

        case 2: // '\002'
            return buffer.getShort();

        case 1: // '\001'
            return buffer.get();
        }
    }

    public final int readId(int i)
    {
        switch (idSize)
        {
        case 3: // '\003'
        default:
            throw new IllegalStateException();

        case 4: // '\004'
            return buffer.getInt(i);

        case 2: // '\002'
            return buffer.getShort(i);

        case 1: // '\001'
            return buffer.get(i);
        }
    }

    public final String readString(int i)
    {
        boolean flag;
        if (i >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            byte abyte0[] = new byte[duplicate.getInt(i) - idSize];
            duplicate.position(i + 4 + idSize);
            duplicate.get(abyte0);
            return new String(abyte0, Charset.defaultCharset());
        }
    }

    public final void skipBytes(int i)
    {
        boolean flag;
        if (i >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        i = buffer.position() + i;
        if (i > buffer.limit())
        {
            throw new BufferUnderflowException();
        } else
        {
            buffer.position(i);
            return;
        }
    }
}
