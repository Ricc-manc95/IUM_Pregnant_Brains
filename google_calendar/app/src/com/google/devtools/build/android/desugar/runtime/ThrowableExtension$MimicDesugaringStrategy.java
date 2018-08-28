// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.devtools.build.android.desugar.runtime;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

final class shMap extends gy
{

    private final shMap map = new shMap();

    public final void addSuppressed(Throwable throwable, Throwable throwable1)
    {
        if (throwable1 == throwable)
        {
            throw new IllegalArgumentException("Self suppression is not allowed.", throwable1);
        }
        if (throwable1 == null)
        {
            throw new NullPointerException("The suppressed exception cannot be null.");
        } else
        {
            map.get(throwable, true).add(throwable1);
            return;
        }
    }

    public final Throwable[] getSuppressed(Throwable throwable)
    {
        throwable = map.get(throwable, false);
        if (throwable == null || throwable.isEmpty())
        {
            return EMPTY_THROWABLE_ARRAY;
        } else
        {
            return (Throwable[])throwable.toArray(EMPTY_THROWABLE_ARRAY);
        }
    }

    public final void printStackTrace(Throwable throwable)
    {
        throwable.printStackTrace();
        throwable = map.get(throwable, false);
        if (throwable == null)
        {
            return;
        }
        throwable;
        JVM INSTR monitorenter ;
        Throwable throwable1;
        for (Iterator iterator = throwable.iterator(); iterator.hasNext(); throwable1.printStackTrace())
        {
            throwable1 = (Throwable)iterator.next();
            System.err.print("Suppressed: ");
        }

        break MISSING_BLOCK_LABEL_67;
        Exception exception;
        exception;
        throwable;
        JVM INSTR monitorexit ;
        throw exception;
        throwable;
        JVM INSTR monitorexit ;
    }

    public final void printStackTrace(Throwable throwable, PrintStream printstream)
    {
        throwable.printStackTrace(printstream);
        throwable = map.get(throwable, false);
        if (throwable == null)
        {
            return;
        }
        throwable;
        JVM INSTR monitorenter ;
        Throwable throwable1;
        for (Iterator iterator = throwable.iterator(); iterator.hasNext(); throwable1.printStackTrace(printstream))
        {
            throwable1 = (Throwable)iterator.next();
            printstream.print("Suppressed: ");
        }

        break MISSING_BLOCK_LABEL_69;
        printstream;
        throwable;
        JVM INSTR monitorexit ;
        throw printstream;
        throwable;
        JVM INSTR monitorexit ;
    }

    public final void printStackTrace(Throwable throwable, PrintWriter printwriter)
    {
        throwable.printStackTrace(printwriter);
        throwable = map.get(throwable, false);
        if (throwable == null)
        {
            return;
        }
        throwable;
        JVM INSTR monitorenter ;
        Throwable throwable1;
        for (Iterator iterator = throwable.iterator(); iterator.hasNext(); throwable1.printStackTrace(printwriter))
        {
            throwable1 = (Throwable)iterator.next();
            printwriter.print("Suppressed: ");
        }

        break MISSING_BLOCK_LABEL_69;
        printwriter;
        throwable;
        JVM INSTR monitorexit ;
        throw printwriter;
        throwable;
        JVM INSTR monitorexit ;
    }

    shMap()
    {
    }
}
