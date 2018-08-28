// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class A extends Enum
    implements Iterator
{

    private static final INSTANCE $VALUES[];
    public static final INSTANCE INSTANCE;

    public static A[] values()
    {
        return (A[])$VALUES.clone();
    }

    public final boolean hasNext()
    {
        return false;
    }

    public final Object next()
    {
        throw new NoSuchElementException();
    }

    public final void remove()
    {
        throw new IllegalStateException(String.valueOf("no calls to next() since the last call to remove()"));
    }

    static 
    {
        INSTANCE = new <init>("INSTANCE", 0);
        $VALUES = (new .VALUES[] {
            INSTANCE
        });
    }

    private A(String s, int i)
    {
        super(s, 0);
    }
}
