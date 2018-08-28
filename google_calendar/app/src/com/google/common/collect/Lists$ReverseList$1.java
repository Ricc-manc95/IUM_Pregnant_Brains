// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.ListIterator;
import java.util.NoSuchElementException;

final class val.forwardIterator
    implements ListIterator
{

    private boolean canRemoveOrSet;
    private final val.forwardIterator this$0;
    private final ListIterator val$forwardIterator;

    public final void add(Object obj)
    {
        val$forwardIterator.add(obj);
        val$forwardIterator.previous();
        canRemoveOrSet = false;
    }

    public final boolean hasNext()
    {
        return val$forwardIterator.hasPrevious();
    }

    public final boolean hasPrevious()
    {
        return val$forwardIterator.hasNext();
    }

    public final Object next()
    {
        if (!hasNext())
        {
            throw new NoSuchElementException();
        } else
        {
            canRemoveOrSet = true;
            return val$forwardIterator.previous();
        }
    }

    public final int nextIndex()
    {
        val.forwardIterator forwarditerator = this._cls0.this;
        int i = val$forwardIterator.nextIndex();
        int j = forwarditerator.ze();
        Preconditions.checkPositionIndex(i, j);
        return j - i;
    }

    public final Object previous()
    {
        if (!hasPrevious())
        {
            throw new NoSuchElementException();
        } else
        {
            canRemoveOrSet = true;
            return val$forwardIterator.next();
        }
    }

    public final int previousIndex()
    {
        return nextIndex() - 1;
    }

    public final void remove()
    {
        if (!canRemoveOrSet)
        {
            throw new IllegalStateException(String.valueOf("no calls to next() since the last call to remove()"));
        } else
        {
            val$forwardIterator.remove();
            canRemoveOrSet = false;
            return;
        }
    }

    public final void set(Object obj)
    {
        if (!canRemoveOrSet)
        {
            throw new IllegalStateException();
        } else
        {
            val$forwardIterator.set(obj);
            return;
        }
    }

    ()
    {
        this$0 = final_;
        val$forwardIterator = ListIterator.this;
        super();
    }
}
