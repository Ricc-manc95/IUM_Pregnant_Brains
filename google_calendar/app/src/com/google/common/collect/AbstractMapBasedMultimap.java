// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

// Referenced classes of package com.google.common.collect:
//            AbstractMultimap, SetMultimap

abstract class AbstractMapBasedMultimap extends AbstractMultimap
    implements Serializable
{

    public static final long serialVersionUID = 0x21f766b1f568c81dL;
    public transient Map map;
    public transient int totalSize;

    protected AbstractMapBasedMultimap(Map map1)
    {
        if (!map1.isEmpty())
        {
            throw new IllegalArgumentException();
        } else
        {
            map = map1;
            return;
        }
    }

    public void clear()
    {
        for (Iterator iterator = map.values().iterator(); iterator.hasNext(); ((Collection)iterator.next()).clear()) { }
        map.clear();
        totalSize = 0;
    }

    final Map createAsMap()
    {
        return new AsMap(map);
    }

    abstract Collection createCollection();

    Collection createCollection(Object obj)
    {
        return createCollection();
    }

    final Collection createEntries()
    {
        if (this instanceof SetMultimap)
        {
            return new AbstractMultimap.EntrySet(this);
        } else
        {
            return new AbstractMultimap.Entries(this);
        }
    }

    final Set createKeySet()
    {
        return new KeySet(map);
    }

    Collection createUnmodifiableEmptyCollection()
    {
        return unmodifiableCollectionSubclass(createCollection());
    }

    Iterator entryIterator()
    {
        return new _cls2();
    }

    public Collection get(Object obj)
    {
        Collection collection1 = (Collection)map.get(obj);
        Collection collection = collection1;
        if (collection1 == null)
        {
            collection = createCollection(obj);
        }
        return wrapCollection(obj, collection);
    }

    public boolean put(Object obj, Object obj1)
    {
        Collection collection = (Collection)map.get(obj);
        if (collection == null)
        {
            collection = createCollection(obj);
            if (collection.add(obj1))
            {
                totalSize = totalSize + 1;
                map.put(obj, collection);
                return true;
            } else
            {
                throw new AssertionError("New Collection violated the Collection spec");
            }
        }
        if (collection.add(obj1))
        {
            totalSize = totalSize + 1;
            return true;
        } else
        {
            return false;
        }
    }

    public Collection removeAll(Object obj)
    {
        obj = (Collection)map.remove(obj);
        if (obj == null)
        {
            return createUnmodifiableEmptyCollection();
        } else
        {
            Collection collection = createCollection();
            collection.addAll(((Collection) (obj)));
            totalSize = totalSize - ((Collection) (obj)).size();
            ((Collection) (obj)).clear();
            return unmodifiableCollectionSubclass(collection);
        }
    }

    final void setMap(Map map1)
    {
        map = map1;
        totalSize = 0;
        for (map1 = map1.values().iterator(); map1.hasNext();)
        {
            Collection collection = (Collection)map1.next();
            boolean flag;
            if (!collection.isEmpty())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException();
            }
            int i = totalSize;
            totalSize = collection.size() + i;
        }

    }

    public int size()
    {
        return totalSize;
    }

    Collection unmodifiableCollectionSubclass(Collection collection)
    {
        return Collections.unmodifiableCollection(collection);
    }

    Collection wrapCollection(Object obj, Collection collection)
    {
        return new WrappedCollection(obj, collection, null);
    }

    final List wrapList(Object obj, List list, WrappedCollection wrappedcollection)
    {
        if (list instanceof RandomAccess)
        {
            return new RandomAccessWrappedList(obj, list, wrappedcollection);
        } else
        {
            return new WrappedList(obj, list, wrappedcollection);
        }
    }

    private class AsMap extends Maps.ViewCachingAbstractMap
    {

        public final transient Map submap;
        public final AbstractMapBasedMultimap this$0;

        public final void clear()
        {
            if (submap == map)
            {
                AbstractMapBasedMultimap.this.clear();
            } else
            {
                class AsMapIterator
                    implements Iterator
                {

                    private Collection collection;
                    private final Iterator delegateIterator;
                    private final AsMap this$1;

                    public final boolean hasNext()
                    {
                        return delegateIterator.hasNext();
                    }

                    public final Object next()
                    {
                        java.util.Map.Entry entry = (java.util.Map.Entry)delegateIterator.next();
                        collection = (Collection)entry.getValue();
                        AsMap asmap = AsMap.this;
                        Object obj = entry.getKey();
                        return new ImmutableEntry(obj, asmap._fld0.wrapCollection(obj, (Collection)entry.getValue()));
                    }

                    public final void remove()
                    {
                        boolean flag;
                        if (collection != null)
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
                            delegateIterator.remove();
                            AbstractMapBasedMultimap abstractmapbasedmultimap = _fld0;
                            int i = collection.size();
                            abstractmapbasedmultimap.totalSize = abstractmapbasedmultimap.totalSize - i;
                            collection.clear();
                            collection = null;
                            return;
                        }
                    }

                AsMapIterator()
                {
                    this$1 = AsMap.this;
                    super();
                    delegateIterator = submap.entrySet().iterator();
                }
                }

                AsMapIterator asmapiterator = new AsMapIterator();
                if (asmapiterator == null)
                {
                    throw new NullPointerException();
                }
                while (asmapiterator.hasNext()) 
                {
                    asmapiterator.next();
                    asmapiterator.remove();
                }
            }
        }

        public final boolean containsKey(Object obj)
        {
            return Maps.safeContainsKey(submap, obj);
        }

        protected final Set createEntrySet()
        {
            class AsMapEntries extends Maps.EntrySet
            {

                private final AsMap this$1;

                public final boolean contains(Object obj)
                {
                    return Collections2.safeContains(submap.entrySet(), obj);
                }

                public final Iterator iterator()
                {
                    return new AsMapIterator();
                }

                final Map map()
                {
                    return AsMap.this;
                }

                public final boolean remove(Object obj)
                {
                    if (!contains(obj))
                    {
                        return false;
                    }
                    Object obj1 = (java.util.Map.Entry)obj;
                    obj = _fld0;
                    obj1 = ((java.util.Map.Entry) (obj1)).getKey();
                    obj1 = (Collection)Maps.safeRemove(((AbstractMapBasedMultimap) (obj)).map, obj1);
                    if (obj1 != null)
                    {
                        int i = ((Collection) (obj1)).size();
                        ((Collection) (obj1)).clear();
                        obj.totalSize = ((AbstractMapBasedMultimap) (obj)).totalSize - i;
                    }
                    return true;
                }

                AsMapEntries()
                {
                    this$1 = AsMap.this;
                    super();
                }
            }

            return new AsMapEntries();
        }

        public final boolean equals(Object obj)
        {
            return this == obj || submap.equals(obj);
        }

        public final Object get(Object obj)
        {
            Collection collection = (Collection)Maps.safeGet(submap, obj);
            if (collection == null)
            {
                return null;
            } else
            {
                return wrapCollection(obj, collection);
            }
        }

        public final int hashCode()
        {
            return submap.hashCode();
        }

        public final Set keySet()
        {
            return AbstractMultimap.this.keySet();
        }

        public final Object remove(Object obj)
        {
            obj = (Collection)submap.remove(obj);
            if (obj == null)
            {
                return null;
            } else
            {
                Collection collection = createCollection();
                collection.addAll(((Collection) (obj)));
                AbstractMapBasedMultimap abstractmapbasedmultimap = AbstractMapBasedMultimap.this;
                int i = ((Collection) (obj)).size();
                abstractmapbasedmultimap.totalSize = abstractmapbasedmultimap.totalSize - i;
                ((Collection) (obj)).clear();
                return collection;
            }
        }

        public final int size()
        {
            return submap.size();
        }

        public final String toString()
        {
            return submap.toString();
        }

        AsMap(Map map1)
        {
            this$0 = AbstractMapBasedMultimap.this;
            super();
            submap = map1;
        }
    }


    private class KeySet extends Maps.KeySet
    {

        public final AbstractMapBasedMultimap this$0;

        public final void clear()
        {
            Iterator iterator1 = iterator();
            if (iterator1 == null)
            {
                throw new NullPointerException();
            }
            for (; iterator1.hasNext(); iterator1.remove())
            {
                iterator1.next();
            }

        }

        public final boolean containsAll(Collection collection)
        {
            return super.map.keySet().containsAll(collection);
        }

        public final boolean equals(Object obj)
        {
            return this == obj || super.map.keySet().equals(obj);
        }

        public final int hashCode()
        {
            return super.map.keySet().hashCode();
        }

        public final Iterator iterator()
        {
            class _cls1
                implements Iterator
            {

                private java.util.Map.Entry entry;
                private final KeySet this$1;
                private final Iterator val$entryIterator;

                public final boolean hasNext()
                {
                    return entryIterator.hasNext();
                }

                public final Object next()
                {
                    entry = (java.util.Map.Entry)entryIterator.next();
                    return entry.getKey();
                }

                public final void remove()
                {
                    boolean flag;
                    if (entry != null)
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
                        Collection collection = (Collection)entry.getValue();
                        entryIterator.remove();
                        AbstractMapBasedMultimap abstractmapbasedmultimap = _fld0;
                        int i = collection.size();
                        abstractmapbasedmultimap.totalSize = abstractmapbasedmultimap.totalSize - i;
                        collection.clear();
                        entry = null;
                        return;
                    }
                }

                _cls1()
                {
                    this$1 = KeySet.this;
                    entryIterator = iterator1;
                    super();
                }
            }

            return new _cls1();
        }

        public final boolean remove(Object obj)
        {
            obj = (Collection)super.map.remove(obj);
            int i;
            if (obj != null)
            {
                i = ((Collection) (obj)).size();
                ((Collection) (obj)).clear();
                obj = AbstractMapBasedMultimap.this;
                obj.totalSize = ((AbstractMapBasedMultimap) (obj)).totalSize - i;
            } else
            {
                i = 0;
            }
            return i > 0;
        }

        KeySet(Map map1)
        {
            this$0 = AbstractMapBasedMultimap.this;
            super(map1);
        }
    }


    private class _cls2 extends Itr
    {
        private class Itr
            implements Iterator
        {

            private Collection collection;
            private Object key;
            private final Iterator keyIterator;
            private final AbstractMapBasedMultimap this$0;
            private Iterator valueIterator;

            public boolean hasNext()
            {
                return keyIterator.hasNext() || valueIterator.hasNext();
            }

            public Object next()
            {
                if (!valueIterator.hasNext())
                {
                    java.util.Map.Entry entry = (java.util.Map.Entry)keyIterator.next();
                    key = entry.getKey();
                    collection = (Collection)entry.getValue();
                    valueIterator = collection.iterator();
                }
                return output(key, valueIterator.next());
            }

            abstract Object output(Object obj, Object obj1);

            public void remove()
            {
                valueIterator.remove();
                if (collection.isEmpty())
                {
                    keyIterator.remove();
                }
                AbstractMapBasedMultimap abstractmapbasedmultimap = AbstractMapBasedMultimap.this;
                abstractmapbasedmultimap.totalSize = abstractmapbasedmultimap.totalSize - 1;
            }

            Itr()
            {
                this$0 = AbstractMapBasedMultimap.this;
                super();
                keyIterator = map.entrySet().iterator();
                key = null;
                collection = null;
                valueIterator = Iterators.EmptyModifiableIterator.INSTANCE;
            }
        }


        final Object output(Object obj, Object obj1)
        {
            return new ImmutableEntry(obj, obj1);
        }

        _cls2()
        {
        }
    }


    private class WrappedCollection extends AbstractCollection
    {

        public final WrappedCollection ancestor;
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
                public final WrappedCollection this$1;

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
                    AbstractMapBasedMultimap abstractmapbasedmultimap = _fld0;
                    abstractmapbasedmultimap.totalSize = abstractmapbasedmultimap.totalSize - 1;
                    removeIfEmpty();
                }

                WrappedIterator()
                {
                    this$1 = WrappedCollection.this;
                    super();
                    originalDelegate = _flddelegate;
                    wrappedcollection = _flddelegate;
                    if (WrappedCollection.this instanceof List)
                    {
                        wrappedcollection = ((List)WrappedCollection.this).listIterator();
                    } else
                    {
                        wrappedcollection = iterator();
                    }
                    delegateIterator = WrappedCollection.this;
                }

                WrappedIterator(Iterator iterator1)
                {
                    this$1 = WrappedCollection.this;
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

        WrappedCollection(Object obj, Collection collection, WrappedCollection wrappedcollection)
        {
            this$0 = AbstractMapBasedMultimap.this;
            super();
            key = obj;
            _flddelegate = collection;
            ancestor = wrappedcollection;
            if (wrappedcollection == null)
            {
                abstractmapbasedmultimap = null;
            } else
            {
                abstractmapbasedmultimap = wrappedcollection._flddelegate;
            }
            ancestorDelegate = AbstractMapBasedMultimap.this;
        }
    }


    private class RandomAccessWrappedList extends WrappedList
        implements RandomAccess
    {
        private class WrappedList extends WrappedCollection
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
                    int j = ((WrappedCollection)this).size();
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
                class WrappedListIterator extends WrappedCollection.WrappedIterator
                    implements ListIterator
                {

                    private final WrappedList this$1;

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
                        obj = _fld0;
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
                        this$1 = WrappedList.this;
                        super();
                    }

                    public WrappedListIterator(int i)
                    {
                        this$1 = WrappedList.this;
                        super(((List)_flddelegate).listIterator(i));
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
                return abstractmapbasedmultimap.wrapList(obj1, list, ((WrappedCollection) (obj)));
            }

            WrappedList(Object obj, List list, WrappedCollection wrappedcollection)
            {
                this$0 = AbstractMapBasedMultimap.this;
                super(obj, list, wrappedcollection);
            }
        }


        RandomAccessWrappedList(Object obj, List list, WrappedCollection wrappedcollection)
        {
            super(obj, list, wrappedcollection);
        }
    }

}
