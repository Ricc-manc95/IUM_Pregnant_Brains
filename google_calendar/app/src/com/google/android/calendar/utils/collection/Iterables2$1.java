// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.collection;

import android.support.v4.util.Pair;
import java.util.Iterator;

final class val.pairs
    implements Iterator
{

    private final Iterator pairIterator;
    private final Iterable val$pairs;

    public final boolean hasNext()
    {
        return pairIterator.hasNext();
    }

    public final Object next()
    {
        return ((Pair)pairIterator.next()).first;
    }

    ()
    {
        val$pairs = iterable;
        super();
        pairIterator = val$pairs.iterator();
    }
}
