// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

// Referenced classes of package com.google.common.collect:
//            Lists

class forwardList extends AbstractList
{

    public final List forwardList;

    public void add(int i, Object obj)
    {
        List list = forwardList;
        int j = size();
        Preconditions.checkPositionIndex(i, j);
        list.add(j - i, obj);
    }

    public void clear()
    {
        forwardList.clear();
    }

    public Object get(int i)
    {
        List list = forwardList;
        int j = size();
        Preconditions.checkElementIndex(i, j);
        return list.get(j - 1 - i);
    }

    public Iterator iterator()
    {
        return listIterator();
    }

    public ListIterator listIterator(int i)
    {
        int j = size();
        Preconditions.checkPositionIndex(i, j);
        class _cls1
            implements ListIterator
        {

            private boolean canRemoveOrSet;
            private final Lists.ReverseList this$0;
            private final ListIterator val$forwardIterator;

            public final void add(Object obj)
            {
                forwardIterator.add(obj);
                forwardIterator.previous();
                canRemoveOrSet = false;
            }

            public final boolean hasNext()
            {
                return forwardIterator.hasPrevious();
            }

            public final boolean hasPrevious()
            {
                return forwardIterator.hasNext();
            }

            public final Object next()
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException();
                } else
                {
                    canRemoveOrSet = true;
                    return forwardIterator.previous();
                }
            }

            public final int nextIndex()
            {
                Lists.ReverseList reverselist = Lists.ReverseList.this;
                int k = forwardIterator.nextIndex();
                int l = reverselist.size();
                Preconditions.checkPositionIndex(k, l);
                return l - k;
            }

            public final Object previous()
            {
                if (!hasPrevious())
                {
                    throw new NoSuchElementException();
                } else
                {
                    canRemoveOrSet = true;
                    return forwardIterator.next();
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
                    forwardIterator.remove();
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
                    forwardIterator.set(obj);
                    return;
                }
            }

            _cls1()
            {
                this$0 = Lists.ReverseList.this;
                forwardIterator = listiterator;
                super();
            }
        }

        return new _cls1();
    }

    public Object remove(int i)
    {
        List list = forwardList;
        int j = size();
        Preconditions.checkElementIndex(i, j);
        return list.remove(j - 1 - i);
    }

    protected void removeRange(int i, int j)
    {
        subList(i, j).clear();
    }

    public Object set(int i, Object obj)
    {
        List list = forwardList;
        int j = size();
        Preconditions.checkElementIndex(i, j);
        return list.set(j - 1 - i, obj);
    }

    public int size()
    {
        return forwardList.size();
    }

    public List subList(int i, int j)
    {
        Preconditions.checkPositionIndexes(i, j, size());
        List list = forwardList;
        int k = size();
        Preconditions.checkPositionIndex(j, k);
        int l = size();
        Preconditions.checkPositionIndex(i, l);
        return Lists.reverse(list.subList(k - j, l - i));
    }

    _cls1(List list)
    {
        if (list == null)
        {
            throw new NullPointerException();
        } else
        {
            forwardList = (List)list;
            return;
        }
    }
}
