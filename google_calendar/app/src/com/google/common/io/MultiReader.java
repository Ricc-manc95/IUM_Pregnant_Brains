// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.io;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

// Referenced classes of package com.google.common.io:
//            CharSource

final class MultiReader extends Reader
{

    private Reader current;
    private final Iterator it;

    MultiReader(Iterator iterator)
        throws IOException
    {
        it = iterator;
        advance();
    }

    private final void advance()
        throws IOException
    {
        close();
        if (it.hasNext())
        {
            current = ((CharSource)it.next()).openStream();
        }
    }

    public final void close()
        throws IOException
    {
        if (current == null)
        {
            break MISSING_BLOCK_LABEL_19;
        }
        current.close();
        current = null;
        return;
        Exception exception;
        exception;
        current = null;
        throw exception;
    }

    public final int read(char ac[], int i, int j)
        throws IOException
    {
        do
        {
            if (current == null)
            {
                return -1;
            }
            int k = current.read(ac, i, j);
            if (k == -1)
            {
                advance();
            } else
            {
                return k;
            }
        } while (true);
    }

    public final boolean ready()
        throws IOException
    {
        return current != null && current.ready();
    }

    public final long skip(long l)
        throws IOException
    {
        boolean flag;
        if (l >= 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("n is negative"));
        }
        if (l > 0L)
        {
            while (current != null) 
            {
                long l1 = current.skip(l);
                if (l1 > 0L)
                {
                    return l1;
                }
                advance();
            }
        }
        return 0L;
    }
}
