// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.NoSuchElementException;

// Referenced classes of package com.google.common.collect:
//            UnmodifiableIterator

final class Iterator extends UnmodifiableIterator
{

    private boolean done;
    private final Object val$value;

    public final boolean hasNext()
    {
        return !done;
    }

    public final Object next()
    {
        if (done)
        {
            throw new NoSuchElementException();
        } else
        {
            done = true;
            return val$value;
        }
    }

    Iterator()
    {
        val$value = obj;
        super();
    }
}
