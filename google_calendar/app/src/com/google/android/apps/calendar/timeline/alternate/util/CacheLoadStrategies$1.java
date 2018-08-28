// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class min
    implements Iterator
{

    private int max;
    private int min;
    private int next;
    private int remaining;
    private final int val$count;
    private final int val$initialBegin;
    private final int val$initialEnd;

    private final Integer next()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        i = remaining - 1;
        remaining = i;
        if (i >= 0)
        {
            break MISSING_BLOCK_LABEL_31;
        }
        throw new NoSuchElementException();
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        i = next;
        if (i <= min || i >= max) goto _L2; else goto _L1
_L1:
        next = next + 1;
_L3:
        this;
        JVM INSTR monitorexit ;
        return Integer.valueOf(i);
_L2:
label0:
        {
            if (i != max)
            {
                break label0;
            }
            max = max + 1;
            next = min;
        }
          goto _L3
label1:
        {
            if (i != min)
            {
                break label1;
            }
            min = min - 1;
            next = max;
        }
          goto _L3
        int j = min;
        int k = max;
        throw new IllegalStateException((new StringBuilder(54)).append("current: ").append(i).append(" min: ").append(j).append(" max: ").append(k).toString());
    }

    public final boolean hasNext()
    {
        this;
        JVM INSTR monitorenter ;
        int i = remaining;
        boolean flag;
        if (i > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public final volatile Object next()
    {
        return next();
    }

    public ()
    {
        val$count = i;
        val$initialBegin = j;
        val$initialEnd = k;
        super();
        remaining = val$count;
        min = Math.min(val$initialBegin, val$initialEnd) - 1;
        max = Math.max(val$initialBegin, val$initialEnd) + 1;
        next = min + 1;
    }
}
