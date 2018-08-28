// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.ListIterator;

// Referenced classes of package com.google.protobuf:
//            UnmodifiableLazyStringList, LazyStringList

final class val.index
    implements ListIterator
{

    private ListIterator iter;
    private final UnmodifiableLazyStringList this$0;
    private final int val$index;

    public final void add(Object obj)
    {
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext()
    {
        return iter.hasNext();
    }

    public final boolean hasPrevious()
    {
        return iter.hasPrevious();
    }

    public final Object next()
    {
        return (String)iter.next();
    }

    public final int nextIndex()
    {
        return iter.nextIndex();
    }

    public final Object previous()
    {
        return (String)iter.previous();
    }

    public final int previousIndex()
    {
        return iter.previousIndex();
    }

    public final void remove()
    {
        throw new UnsupportedOperationException();
    }

    public final void set(Object obj)
    {
        throw new UnsupportedOperationException();
    }

    ()
    {
        this$0 = final_unmodifiablelazystringlist;
        val$index = I.this;
        super();
        iter = list.listIterator(val$index);
    }
}
