// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class closeables
    implements Closeable
{

    private List closeables;
    private boolean closed;

    final void add(Closeable closeable)
    {
        this;
        JVM INSTR monitorenter ;
        if (closed)
        {
            break MISSING_BLOCK_LABEL_41;
        }
        if (closeables == null)
        {
            closeables = new ArrayList();
        }
        closeables.add(closeable);
        this;
        JVM INSTR monitorexit ;
        return;
        this;
        JVM INSTR monitorexit ;
        if (closeable != null)
        {
            try
            {
                closeable.close();
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Closeable closeable)
            {
                return;
            }
        } else
        {
            return;
        }
        closeable;
        this;
        JVM INSTR monitorexit ;
        throw closeable;
    }

    public final void close()
    {
        this;
        JVM INSTR monitorenter ;
        if (!closed)
        {
            break MISSING_BLOCK_LABEL_12;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        closed = true;
        this;
        JVM INSTR monitorexit ;
        if (closeables == null)
        {
            break MISSING_BLOCK_LABEL_82;
        }
        Iterator iterator = closeables.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Closeable closeable = (Closeable)iterator.next();
            if (closeable != null)
            {
                try
                {
                    closeable.close();
                }
                catch (IOException ioexception) { }
            }
        } while (true);
        break MISSING_BLOCK_LABEL_77;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        closeables = null;
    }

    public ()
    {
    }

    transient (Closeable acloseable[])
    {
        closeables = new ArrayList(acloseable.length);
        closeables.addAll(Arrays.asList(acloseable));
    }
}
