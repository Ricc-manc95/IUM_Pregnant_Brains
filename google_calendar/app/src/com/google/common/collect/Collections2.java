// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collection;

public final class Collections2
{

    public static Collection filter(Collection collection, Predicate predicate)
    {
        if (collection instanceof FilteredCollection)
        {
            collection = (FilteredCollection)collection;
            return new FilteredCollection(((FilteredCollection) (collection)).unfiltered, Predicates.and(((FilteredCollection) (collection)).predicate, predicate));
        }
        if (collection == null)
        {
            throw new NullPointerException();
        }
        collection = (Collection)collection;
        if (predicate == null)
        {
            throw new NullPointerException();
        } else
        {
            return new FilteredCollection(collection, (Predicate)predicate);
        }
    }

    static boolean safeContains(Collection collection, Object obj)
    {
        if (collection == null)
        {
            throw new NullPointerException();
        }
        boolean flag = collection.contains(obj);
        return flag;
        collection;
_L2:
        return false;
        collection;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private class FilteredCollection extends AbstractCollection
    {

        public final Predicate predicate;
        public final Collection unfiltered;

        public boolean add(Object obj)
        {
            if (!predicate.apply(obj))
            {
                throw new IllegalArgumentException();
            } else
            {
                return unfiltered.add(obj);
            }
        }

        public boolean addAll(Collection collection)
        {
            for (Iterator iterator1 = collection.iterator(); iterator1.hasNext();)
            {
                Object obj = iterator1.next();
                if (!predicate.apply(obj))
                {
                    throw new IllegalArgumentException();
                }
            }

            return unfiltered.addAll(collection);
        }

        public void clear()
        {
            Iterables.removeIf(unfiltered, predicate);
        }

        public boolean contains(Object obj)
        {
            if (Collections2.safeContains(unfiltered, obj))
            {
                return predicate.apply(obj);
            } else
            {
                return false;
            }
        }

        public boolean containsAll(Collection collection)
        {
            for (collection = collection.iterator(); collection.hasNext();)
            {
                if (!contains(collection.next()))
                {
                    return false;
                }
            }

            return true;
        }

        public boolean isEmpty()
        {
            Collection collection = unfiltered;
            Predicate predicate1 = predicate;
            boolean flag;
            if (Iterators.indexOf(collection.iterator(), predicate1) != -1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            return !flag;
        }

        public Iterator iterator()
        {
            Iterator iterator1 = unfiltered.iterator();
            Predicate predicate1 = predicate;
            if (iterator1 == null)
            {
                throw new NullPointerException();
            }
            if (predicate1 == null)
            {
                throw new NullPointerException();
            } else
            {
                return new Iterators._cls5(iterator1, predicate1);
            }
        }

        public boolean remove(Object obj)
        {
            return contains(obj) && unfiltered.remove(obj);
        }

        public boolean removeAll(Collection collection)
        {
            boolean flag = false;
            Iterator iterator1 = unfiltered.iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                Object obj = iterator1.next();
                if (predicate.apply(obj) && collection.contains(obj))
                {
                    iterator1.remove();
                    flag = true;
                }
            } while (true);
            return flag;
        }

        public boolean retainAll(Collection collection)
        {
            boolean flag = false;
            Iterator iterator1 = unfiltered.iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                Object obj = iterator1.next();
                if (predicate.apply(obj) && !collection.contains(obj))
                {
                    iterator1.remove();
                    flag = true;
                }
            } while (true);
            return flag;
        }

        public int size()
        {
            int i = 0;
            Iterator iterator1 = unfiltered.iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                Object obj = iterator1.next();
                if (predicate.apply(obj))
                {
                    i++;
                }
            } while (true);
            return i;
        }

        public Object[] toArray()
        {
            Iterator iterator1 = iterator();
            ArrayList arraylist = new ArrayList();
            Iterators.addAll(arraylist, iterator1);
            return arraylist.toArray();
        }

        public Object[] toArray(Object aobj[])
        {
            Iterator iterator1 = iterator();
            ArrayList arraylist = new ArrayList();
            Iterators.addAll(arraylist, iterator1);
            return arraylist.toArray(aobj);
        }

        FilteredCollection(Collection collection, Predicate predicate1)
        {
            unfiltered = collection;
            predicate = predicate1;
        }
    }

}
