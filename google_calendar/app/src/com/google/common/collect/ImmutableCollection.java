// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            ImmutableList, UnmodifiableIterator

public abstract class ImmutableCollection extends AbstractCollection
    implements Serializable
{

    private static final Object EMPTY_ARRAY[] = new Object[0];

    ImmutableCollection()
    {
    }

    public final boolean add(Object obj)
    {
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(Collection collection)
    {
        throw new UnsupportedOperationException();
    }

    public ImmutableList asList()
    {
        if (isEmpty())
        {
            return ImmutableList.of();
        } else
        {
            return ImmutableList.asImmutableList(toArray());
        }
    }

    public final void clear()
    {
        throw new UnsupportedOperationException();
    }

    public abstract boolean contains(Object obj);

    int copyIntoArray(Object aobj[], int i)
    {
        for (UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)iterator(); unmodifiableiterator.hasNext();)
        {
            aobj[i] = unmodifiableiterator.next();
            i++;
        }

        return i;
    }

    abstract boolean isPartialView();

    public abstract UnmodifiableIterator iterator();

    public volatile Iterator iterator()
    {
        return iterator();
    }

    public final boolean remove(Object obj)
    {
        throw new UnsupportedOperationException();
    }

    public final boolean removeAll(Collection collection)
    {
        throw new UnsupportedOperationException();
    }

    public final boolean retainAll(Collection collection)
    {
        throw new UnsupportedOperationException();
    }

    public final Object[] toArray()
    {
        int i = size();
        if (i == 0)
        {
            return EMPTY_ARRAY;
        } else
        {
            Object aobj[] = new Object[i];
            copyIntoArray(aobj, 0);
            return aobj;
        }
    }

    public final Object[] toArray(Object aobj[])
    {
        int i;
        if (aobj == null)
        {
            throw new NullPointerException();
        }
        i = size();
        if (aobj.length >= i) goto _L2; else goto _L1
_L1:
        Object aobj1[] = (Object[])Array.newInstance(((Object) (aobj)).getClass().getComponentType(), i);
_L4:
        copyIntoArray(aobj1, 0);
        return aobj1;
_L2:
        aobj1 = aobj;
        if (aobj.length > i)
        {
            aobj[i] = null;
            aobj1 = aobj;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    Object writeReplace()
    {
        return new ImmutableList.SerializedForm(toArray());
    }

}
