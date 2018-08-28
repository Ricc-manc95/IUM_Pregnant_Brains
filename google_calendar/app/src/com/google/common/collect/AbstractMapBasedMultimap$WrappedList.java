// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

// Referenced classes of package com.google.common.collect:
//            AbstractMapBasedMultimap

class ction extends ction
    implements List
{

    public final AbstractMapBasedMultimap this$0;

    public void add(int i, Object obj)
    {
        refreshIfEmpty();
        boolean flag = super._flddelegate.isEmpty();
        ((List)super._flddelegate).add(i, obj);
        obj = AbstractMapBasedMultimap.this;
        obj.totalSize = ((AbstractMapBasedMultimap) (obj)).totalSize + 1;
        if (flag)
        {
            addToMap();
        }
    }

    public boolean addAll(int i, Collection collection)
    {
        boolean flag;
        if (collection.isEmpty())
        {
            flag = false;
        } else
        {
            int j = size();
            boolean flag1 = ((List)super._flddelegate).addAll(i, collection);
            flag = flag1;
            if (flag1)
            {
                i = super._flddelegate.size();
                collection = AbstractMapBasedMultimap.this;
                collection.totalSize = (i - j) + ((AbstractMapBasedMultimap) (collection)).totalSize;
                flag = flag1;
                if (j == 0)
                {
                    addToMap();
                    return flag1;
                }
            }
        }
        return flag;
    }

    public Object get(int i)
    {
        refreshIfEmpty();
        return ((List)super._flddelegate).get(i);
    }

    public int indexOf(Object obj)
    {
        refreshIfEmpty();
        return ((List)super._flddelegate).indexOf(obj);
    }

    public int lastIndexOf(Object obj)
    {
        refreshIfEmpty();
        return ((List)super._flddelegate).lastIndexOf(obj);
    }

    public ListIterator listIterator()
    {
        refreshIfEmpty();
        class WrappedListIterator extends AbstractMapBasedMultimap.WrappedCollection.WrappedIterator
            implements ListIterator
        {

            private final AbstractMapBasedMultimap.WrappedList this$1;

            private final ListIterator getDelegateListIterator()
            {
                refreshIfEmpty();
                if (_flddelegate != super.originalDelegate)
                {
                    throw new ConcurrentModificationException();
                } else
                {
                    return (ListIterator)super.delegateIterator;
                }
            }

            public final void add(Object obj)
            {
                boolean flag = isEmpty();
                getDelegateListIterator().add(obj);
                obj = this$0;
                obj.totalSize = ((AbstractMapBasedMultimap) (obj)).totalSize + 1;
                if (flag)
                {
                    addToMap();
                }
            }

            public final boolean hasPrevious()
            {
                return getDelegateListIterator().hasPrevious();
            }

            public final int nextIndex()
            {
                return getDelegateListIterator().nextIndex();
            }

            public final Object previous()
            {
                return getDelegateListIterator().previous();
            }

            public final int previousIndex()
            {
                return getDelegateListIterator().previousIndex();
            }

            public final void set(Object obj)
            {
                getDelegateListIterator().set(obj);
            }

            WrappedListIterator()
            {
                this$1 = AbstractMapBasedMultimap.WrappedList.this;
                super(AbstractMapBasedMultimap.WrappedList.this);
            }

            public WrappedListIterator(int i)
            {
                this$1 = AbstractMapBasedMultimap.WrappedList.this;
                super(AbstractMapBasedMultimap.WrappedList.this, ((List)_flddelegate).listIterator(i));
            }
        }

        return new WrappedListIterator();
    }

    public ListIterator listIterator(int i)
    {
        refreshIfEmpty();
        return new WrappedListIterator(i);
    }

    public Object remove(int i)
    {
        refreshIfEmpty();
        Object obj = ((List)super._flddelegate).remove(i);
        AbstractMapBasedMultimap abstractmapbasedmultimap = AbstractMapBasedMultimap.this;
        abstractmapbasedmultimap.totalSize = abstractmapbasedmultimap.totalSize - 1;
        removeIfEmpty();
        return obj;
    }

    public Object set(int i, Object obj)
    {
        refreshIfEmpty();
        return ((List)super._flddelegate).set(i, obj);
    }

    public List subList(int i, int j)
    {
        refreshIfEmpty();
        AbstractMapBasedMultimap abstractmapbasedmultimap = AbstractMapBasedMultimap.this;
        Object obj1 = super.key;
        List list = ((List)super._flddelegate).subList(i, j);
        Object obj;
        if (super.ancestor == null)
        {
            obj = this;
        } else
        {
            obj = super.ancestor;
        }
        return abstractmapbasedmultimap.wrapList(obj1, list, ((ction) (obj)));
    }

    ction(Object obj, List list, ction ction)
    {
        this$0 = AbstractMapBasedMultimap.this;
        super(AbstractMapBasedMultimap.this, obj, list, ction);
    }
}
