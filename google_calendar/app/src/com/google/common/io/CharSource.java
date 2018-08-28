// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.io;

import com.google.common.base.Throwables;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.Deque;

// Referenced classes of package com.google.common.io:
//            Closer, CharStreams

public abstract class CharSource
{

    protected CharSource()
    {
    }

    public abstract Reader openStream()
        throws IOException;

    public String read()
        throws IOException
    {
        Closer closer = new Closer(Closer.SUPPRESSOR);
        Object obj = openStream();
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_30;
        }
        closer.stack.addFirst(obj);
        StringBuilder stringbuilder;
        obj = (Reader)obj;
        stringbuilder = new StringBuilder();
        if (!(obj instanceof Reader)) goto _L2; else goto _L1
_L1:
        CharStreams.copyReaderToBuilder((Reader)obj, stringbuilder);
_L5:
        obj = stringbuilder.toString();
        closer.close();
        return ((String) (obj));
_L2:
        if (!(obj instanceof Reader)) goto _L4; else goto _L3
_L3:
        CharStreams.copyReaderToBuilder((Reader)obj, (StringBuilder)stringbuilder);
          goto _L5
_L7:
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_194;
        }
        throw new NullPointerException();
_L4:
        if (obj != null)
        {
            break; /* Loop/switch isn't completed */
        }
        try
        {
            throw new NullPointerException();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj) { }
        finally
        {
            closer.close();
            throw obj;
        }
        if (true) goto _L7; else goto _L6
_L6:
        if (stringbuilder != null)
        {
            break MISSING_BLOCK_LABEL_136;
        }
        throw new NullPointerException();
        long l = 0L;
        CharBuffer charbuffer = CharBuffer.allocate(2048);
        while (((Readable) (obj)).read(charbuffer) != -1) 
        {
            charbuffer.flip();
            stringbuilder.append(charbuffer);
            l += charbuffer.remaining();
            charbuffer.clear();
        }
          goto _L5
        closer.thrown = ((Throwable) (obj));
        Throwables.propagateIfPossible(((Throwable) (obj)), java/io/IOException);
        throw new RuntimeException(((Throwable) (obj)));
    }
}
