// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package com.google.common.collect:
//            AbstractMapBasedMultimap

class ancestorDelegate extends AbstractCollection
{

    public final delegate ancestor;
    private final Collection ancestorDelegate;
    public Collection _flddelegate;
    public final Object key;
    public final AbstractMapBasedMultimap this$0;

    public boolean add(Object obj)
    {
        refreshIfEmpty();
        boolean flag = _flddelegate.isEmpty();
        boolean flag1 = _flddelegate.add(obj);
        if (flag1)
        {
            obj = AbstractMapBasedMultimap.this;
            obj.totalSize = ((AbstractMapBasedMultimap) (obj)).totalSize + 1;
            if (flag)
            {
                addToMap();
            }
        }
        return flag1;
    }

    public boolean addAll(Collection collection)
    {
        boolean flag;
        if (collection.isEmpty())
        {
            flag = false;
        } else
        {
            int i = size();
            boolean flag1 = _flddelegate.addAll(collection);
            flag = flag1;
            if (flag1)
            {
                int j = _flddelegate.size();
                collection = AbstractMapBasedMultimap.this;
                collection.totalSize = (j - i) + ((AbstractMapBasedMultimap) (collection)).totalSize;
                flag = flag1;
                if (i == 0)
                {
                    addToMap();
                    return flag1;
                }
            }
        }
        return flag;
    }

    final void addToMap()
    {
        if (ancestor != null)
        {
            ancestor.addToMap();
            return;
        } else
        {
            map.put(key, _flddelegate);
            return;
        }
    }

    public void clear()
    {
        int i = size();
        if (i == 0)
        {
            return;
        } else
        {
            _flddelegate.clear();
            AbstractMapBasedMultimap abstractmapbasedmultimap = AbstractMapBasedMultimap.this;
            abstractmapbasedmultimap.totalSize = abstractmapbasedmultimap.totalSize - i;
            removeIfEmpty();
            return;
        }
    }

    public boolean contains(Object obj)
    {
        refreshIfEmpty();
        return _flddelegate.contains(obj);
    }

    public boolean containsAll(Collection collection)
    {
        refreshIfEmpty();
        return _flddelegate.containsAll(collection);
    }

    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        } else
        {
            refreshIfEmpty();
            return _flddelegate.equals(obj);
        }
    }

    public int hashCode()
    {
        refreshIfEmpty();
        return _flddelegate.hashCode();
    }

    public Iterator iterator()
    {
        refreshIfEmpty();
        class WrappedIterator
            implements Iterator
        {

            public final Iterator delegateIterator;
            public final Collection originalDelegate;
            public final AbstractMapBasedMultimap.WrappedCollection this$1;

            public boolean hasNext()
            {
                refreshIfEmpty();
                if (_flddelegate != originalDelegate)
                {
                    throw new ConcurrentModificationException();
                } else
                {
                    return delegateIterator.hasNext();
                }
            }

            public Object next()
            {
                refreshIfEmpty();
                if (_flddelegate != originalDelegate)
                {
                    throw new ConcurrentModificationException();
                } else
                {
                    return delegateIterator.next();
                }
            }

            public void remove()
            {
                delegateIterator.remove();
                AbstractMapBasedMultimap abstractmapbasedmultimap = this$0;
                abstractmapbasedmultimap.totalSize = abstractmapbasedmultimap.totalSize - 1;
                removeIfEmpty();
            }

            WrappedIterator()
            {
                this$1 = AbstractMapBasedMultimap.WrappedCollection.this;
                super();
                originalDelegate = _flddelegate;
                wrappedcollection = _flddelegate;
                if (AbstractMapBasedMultimap.WrappedCollection.this instanceof List)
                {
                    wrappedcollection = ((List)AbstractMapBasedMultimap.WrappedCollection.this).listIterator();
                } else
                {
                    wrappedcollection = iterator();
                }
                delegateIterator = AbstractMapBasedMultimap.WrappedCollection.this;
            }

            WrappedIterator(Iterator iterator1)
            {
                this$1 = AbstractMapBasedMultimap.WrappedCollection.this;
                super();
                originalDelegate = _flddelegate;
                delegateIterator = iterator1;
            }
        }

        return new WrappedIterator();
    }

    final void refreshIfEmpty()
    {
        if (ancestor != null)
        {
            ancestor.refreshIfEmpty();
            if (ancestor._flddelegate != ancestorDelegate)
            {
                throw new ConcurrentModificationException();
            }
        } else
        if (_flddelegate.isEmpty())
        {
            Collection collection = (Collection)map.get(key);
            if (collection != null)
            {
                _flddelegate = collection;
            }
        }
    }

    public boolean remove(Object obj)
    {
        refreshIfEmpty();
        boolean flag = _flddelegate.remove(obj);
        if (flag)
        {
            obj = AbstractMapBasedMultimap.this;
            obj.totalSize = ((AbstractMapBasedMultimap) (obj)).totalSize - 1;
            removeIfEmpty();
        }
        return flag;
    }

    public boolean removeAll(Collection collection)
    {
        boolean flag;
        if (collection.isEmpty())
        {
            flag = false;
        } else
        {
            int i = size();
            boolean flag1 = _flddelegate.removeAll(collection);
            flag = flag1;
            if (flag1)
            {
                int j = _flddelegate.size();
                collection = AbstractMapBasedMultimap.this;
                collection.totalSize = (j - i) + ((AbstractMapBasedMultimap) (collection)).totalSize;
                removeIfEmpty();
                return flag1;
            }
        }
        return flag;
    }

    final void removeIfEmpty()
    {
        if (ancestor != null)
        {
            ancestor.removeIfEmpty();
        } else
        if (_flddelegate.isEmpty())
        {
            map.remove(key);
            return;
        }
    }

    public boolean retainAll(Collection collection)
    {
        if (collection == null)
        {
            throw new NullPointerException();
        }
        int i = size();
        boolean flag = _flddelegate.retainAll(collection);
        if (flag)
        {
            int j = _flddelegate.size();
            collection = AbstractMapBasedMultimap.this;
            collection.totalSize = (j - i) + ((AbstractMapBasedMultimap) (collection)).totalSize;
            removeIfEmpty();
        }
        return flag;
    }

    public int size()
    {
        refreshIfEmpty();
        return _flddelegate.size();
    }

    public String toString()
    {
        refreshIfEmpty();
        return _flddelegate.toString();
    }

    WrappedIterator(Object obj, Collection collection, WrappedIterator wrappediterator)
    {
        this$0 = AbstractMapBasedMultimap.this;
        super();
        key = obj;
        _flddelegate = collection;
        ancestor = wrappediterator;
        if (wrappediterator == null)
        {
            abstractmapbasedmultimap = null;
        } else
        {
            abstractmapbasedmultimap = wrappediterator._flddelegate;
        }
        ancestorDelegate = AbstractMapBasedMultimap.this;
    }
}
