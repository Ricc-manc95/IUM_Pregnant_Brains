// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.io;

import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Throwables;
import java.io.IOException;
import java.io.InputStream;
import java.util.Deque;

// Referenced classes of package com.google.common.io:
//            Closer, ByteStreams

public abstract class ByteSource
{

    protected ByteSource()
    {
    }

    public abstract InputStream openStream()
        throws IOException;

    public byte[] read()
        throws IOException
    {
        Closer closer = new Closer(Closer.SUPPRESSOR);
        Object obj = openStream();
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_30;
        }
        closer.stack.addFirst(obj);
        Optional optional;
        obj = (InputStream)obj;
        optional = sizeIfKnown();
        if (!optional.isPresent()) goto _L2; else goto _L1
_L1:
        obj = ByteStreams.toByteArray(((InputStream) (obj)), ((Long)optional.get()).longValue());
_L4:
        closer.close();
        return ((byte []) (obj));
_L2:
        obj = ByteStreams.toByteArray(((InputStream) (obj)));
        if (true) goto _L4; else goto _L3
_L3:
        Object obj1;
        obj1;
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_96;
        }
        throw new NullPointerException();
        obj1;
        closer.close();
        throw obj1;
        closer.thrown = ((Throwable) (obj1));
        Throwables.propagateIfPossible(((Throwable) (obj1)), java/io/IOException);
        throw new RuntimeException(((Throwable) (obj1)));
    }

    public Optional sizeIfKnown()
    {
        return Absent.INSTANCE;
    }
}
