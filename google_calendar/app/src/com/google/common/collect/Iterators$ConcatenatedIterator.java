// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class topMetaIterator
    implements Iterator
{

    private Iterator iterator;
    private Deque metaIterators;
    private Iterator toRemove;
    private Iterator topMetaIterator;

    public final boolean hasNext()
    {
        iterator1 = null;
_L2:
        topMetaIterator = iterator1;
        if (topMetaIterator == null)
        {
            return false;
        }
        break; /* Loop/switch isn't completed */
_L4:
        Iterator iterator1 = iterator;
        if (iterator1 == null)
        {
            throw new NullPointerException();
        }
        if (((Iterator)iterator1).hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        for (; topMetaIterator == null || !topMetaIterator.hasNext(); topMetaIterator = (Iterator)metaIterators.removeFirst())
        {
            if (metaIterators == null || metaIterators.isEmpty())
            {
                break MISSING_BLOCK_LABEL_86;
            }
        }

        iterator1 = topMetaIterator;
        if (true) goto _L2; else goto _L1
_L1:
        iterator = (Iterator)topMetaIterator.next();
        if (iterator instanceof iterator)
        {
            topMetaIterator topmetaiterator = (iterator)iterator;
            iterator = topmetaiterator.iterator;
            if (metaIterators == null)
            {
                metaIterators = new ArrayDeque();
            }
            metaIterators.addFirst(topMetaIterator);
            if (topmetaiterator.metaIterators != null)
            {
                for (; !topmetaiterator.metaIterators.isEmpty(); metaIterators.addFirst((Iterator)topmetaiterator.metaIterators.removeLast())) { }
            }
            topMetaIterator = topmetaiterator.topMetaIterator;
        }
        if (true) goto _L4; else goto _L3
_L3:
        return true;
    }

    public final Object next()
    {
        if (hasNext())
        {
            toRemove = iterator;
            return iterator.next();
        } else
        {
            throw new NoSuchElementException();
        }
    }

    public final void remove()
    {
        boolean flag;
        if (toRemove != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("no calls to next() since the last call to remove()"));
        } else
        {
            toRemove.remove();
            toRemove = null;
            return;
        }
    }

    (Iterator iterator1)
    {
        iterator = iterator;
        if (iterator1 == null)
        {
            throw new NullPointerException();
        } else
        {
            topMetaIterator = (Iterator)iterator1;
            return;
        }
    }
}
