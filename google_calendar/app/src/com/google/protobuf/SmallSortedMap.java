// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class SmallSortedMap extends AbstractMap
{

    public List entryList;
    public boolean isImmutable;
    public volatile DescendingEntrySet lazyDescendingEntrySet;
    private volatile EntrySet lazyEntrySet;
    private final int maxArraySize;
    public Map overflowEntries;
    public Map overflowEntriesDescending;

    private SmallSortedMap(int i)
    {
        maxArraySize = i;
        entryList = Collections.emptyList();
        overflowEntries = Collections.emptyMap();
        overflowEntriesDescending = Collections.emptyMap();
    }

    SmallSortedMap(int i, byte byte0)
    {
        this(i);
    }

    private final int binarySearchInArray(Comparable comparable)
    {
_L2:
        int k;
        while (k <= i) 
        {
            int l = (k + i) / 2;
            int i1 = comparable.compareTo((Comparable)((Entry)entryList.get(l)).getKey());
            if (i1 < 0)
            {
                i = l - 1;
            } else
            if (i1 > 0)
            {
                k = l + 1;
            } else
            {
                return l;
            }
        }
        return -(k + 1);
        int i = entryList.size() - 1;
        if (i >= 0)
        {
            int j = comparable.compareTo((Comparable)((Entry)entryList.get(i)).getKey());
            if (j > 0)
            {
                return -(i + 2);
            }
            if (j == 0)
            {
                return i;
            }
        }
        k = 0;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private final SortedMap getOverflowEntriesMutable()
    {
        if (isImmutable)
        {
            throw new UnsupportedOperationException();
        }
        if (overflowEntries.isEmpty() && !(overflowEntries instanceof TreeMap))
        {
            overflowEntries = new TreeMap();
            overflowEntriesDescending = ((TreeMap)overflowEntries).descendingMap();
        }
        return (SortedMap)overflowEntries;
    }

    public void clear()
    {
        if (isImmutable)
        {
            throw new UnsupportedOperationException();
        }
        if (!entryList.isEmpty())
        {
            entryList.clear();
        }
        if (!overflowEntries.isEmpty())
        {
            overflowEntries.clear();
        }
    }

    public boolean containsKey(Object obj)
    {
        obj = (Comparable)obj;
        return binarySearchInArray(((Comparable) (obj))) >= 0 || overflowEntries.containsKey(obj);
    }

    public Set entrySet()
    {
        if (lazyEntrySet == null)
        {
            lazyEntrySet = new EntrySet();
        }
        return lazyEntrySet;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof SmallSortedMap))
        {
            return super.equals(obj);
        }
        obj = (SmallSortedMap)obj;
        int j = size();
        if (j != ((SmallSortedMap) (obj)).size())
        {
            return false;
        }
        int k = entryList.size();
        if (k != ((SmallSortedMap) (obj)).entryList.size())
        {
            return entrySet().equals(((SmallSortedMap) (obj)).entrySet());
        }
        for (int i = 0; i < k; i++)
        {
            if (!((java.util.Map.Entry)entryList.get(i)).equals((java.util.Map.Entry)((SmallSortedMap) (obj)).entryList.get(i)))
            {
                return false;
            }
        }

        if (k != j)
        {
            return overflowEntries.equals(((SmallSortedMap) (obj)).overflowEntries);
        } else
        {
            return true;
        }
    }

    public Object get(Object obj)
    {
        obj = (Comparable)obj;
        int i = binarySearchInArray(((Comparable) (obj)));
        if (i >= 0)
        {
            return ((Entry)entryList.get(i)).getValue();
        } else
        {
            return overflowEntries.get(obj);
        }
    }

    public int hashCode()
    {
        int k = entryList.size();
        int i = 0;
        int j = 0;
        for (; i < k; i++)
        {
            j += ((Entry)entryList.get(i)).hashCode();
        }

        if (overflowEntries.size() > 0)
        {
            return overflowEntries.hashCode() + j;
        } else
        {
            return j;
        }
    }

    public void makeImmutable()
    {
        if (!isImmutable)
        {
            Map map;
            if (overflowEntries.isEmpty())
            {
                map = Collections.emptyMap();
            } else
            {
                map = Collections.unmodifiableMap(overflowEntries);
            }
            overflowEntries = map;
            if (overflowEntriesDescending.isEmpty())
            {
                map = Collections.emptyMap();
            } else
            {
                map = Collections.unmodifiableMap(overflowEntriesDescending);
            }
            overflowEntriesDescending = map;
            isImmutable = true;
        }
    }

    public final Object put(Comparable comparable, Object obj)
    {
        if (isImmutable)
        {
            throw new UnsupportedOperationException();
        }
        int i = binarySearchInArray(comparable);
        if (i >= 0)
        {
            return ((Entry)entryList.get(i)).setValue(obj);
        }
        if (isImmutable)
        {
            throw new UnsupportedOperationException();
        }
        if (entryList.isEmpty() && !(entryList instanceof ArrayList))
        {
            entryList = new ArrayList(maxArraySize);
        }
        i = -(i + 1);
        if (i >= maxArraySize)
        {
            return getOverflowEntriesMutable().put(comparable, obj);
        }
        if (entryList.size() == maxArraySize)
        {
            Entry entry = (Entry)entryList.remove(maxArraySize - 1);
            getOverflowEntriesMutable().put((Comparable)entry.getKey(), entry.getValue());
        }
        entryList.add(i, new Entry(comparable, obj));
        return null;
    }

    public volatile Object put(Object obj, Object obj1)
    {
        return put((Comparable)obj, obj1);
    }

    public Object remove(Object obj)
    {
        if (isImmutable)
        {
            throw new UnsupportedOperationException();
        }
        obj = (Comparable)obj;
        int i = binarySearchInArray(((Comparable) (obj)));
        if (i >= 0)
        {
            return removeArrayEntryAt(i);
        }
        if (overflowEntries.isEmpty())
        {
            return null;
        } else
        {
            return overflowEntries.remove(obj);
        }
    }

    final Object removeArrayEntryAt(int i)
    {
        if (isImmutable)
        {
            throw new UnsupportedOperationException();
        }
        Object obj = ((Entry)entryList.remove(i)).getValue();
        if (!overflowEntries.isEmpty())
        {
            Iterator iterator = getOverflowEntriesMutable().entrySet().iterator();
            entryList.add(new Entry((java.util.Map.Entry)iterator.next()));
            iterator.remove();
        }
        return obj;
    }

    public int size()
    {
        return entryList.size() + overflowEntries.size();
    }

    private class Entry
        implements Comparable, java.util.Map.Entry
    {

        private final Comparable key;
        private final SmallSortedMap this$0;
        private Object value;

        public final int compareTo(Object obj)
        {
            obj = (Entry)obj;
            return ((Comparable)getKey()).compareTo((Comparable)((Entry) (obj)).getKey());
        }

        public final boolean equals(Object obj)
        {
            if (obj != this) goto _L2; else goto _L1
_L1:
            return true;
_L2:
            if (!(obj instanceof java.util.Map.Entry))
            {
                return false;
            }
            obj = (java.util.Map.Entry)obj;
            Object obj1 = key;
            Object obj2 = ((java.util.Map.Entry) (obj)).getKey();
            boolean flag;
            if (obj1 == null)
            {
                if (obj2 == null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            } else
            {
                flag = obj1.equals(obj2);
            }
            if (!flag)
            {
                break; /* Loop/switch isn't completed */
            }
            obj1 = value;
            obj = ((java.util.Map.Entry) (obj)).getValue();
            if (obj1 == null)
            {
                if (obj == null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            } else
            {
                flag = obj1.equals(obj);
            }
            if (flag) goto _L1; else goto _L3
_L3:
            return false;
        }

        public final Object getKey()
        {
            return key;
        }

        public final Object getValue()
        {
            return value;
        }

        public final int hashCode()
        {
            int j = 0;
            int i;
            if (key == null)
            {
                i = 0;
            } else
            {
                i = key.hashCode();
            }
            if (value != null)
            {
                j = value.hashCode();
            }
            return i ^ j;
        }

        public final Object setValue(Object obj)
        {
            if (isImmutable)
            {
                throw new UnsupportedOperationException();
            } else
            {
                Object obj1 = value;
                value = obj;
                return obj1;
            }
        }

        public final String toString()
        {
            String s = String.valueOf(key);
            String s1 = String.valueOf(value);
            return (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s1).length())).append(s).append("=").append(s1).toString();
        }

        Entry(Comparable comparable, Object obj)
        {
            this$0 = SmallSortedMap.this;
            super();
            key = comparable;
            value = obj;
        }

        Entry(java.util.Map.Entry entry)
        {
            this((Comparable)entry.getKey(), entry.getValue());
        }
    }


    private class EntrySet extends AbstractSet
    {

        private final SmallSortedMap this$0;

        public boolean add(Object obj)
        {
            obj = (java.util.Map.Entry)obj;
            if (!contains(obj))
            {
                put((Comparable)((java.util.Map.Entry) (obj)).getKey(), ((java.util.Map.Entry) (obj)).getValue());
                return true;
            } else
            {
                return false;
            }
        }

        public void clear()
        {
            SmallSortedMap.this.clear();
        }

        public boolean contains(Object obj)
        {
            Object obj1 = (java.util.Map.Entry)obj;
            obj = get(((java.util.Map.Entry) (obj1)).getKey());
            obj1 = ((java.util.Map.Entry) (obj1)).getValue();
            return obj == obj1 || obj != null && obj.equals(obj1);
        }

        public Iterator iterator()
        {
            return new EntryIterator();
        }

        public boolean remove(Object obj)
        {
            obj = (java.util.Map.Entry)obj;
            if (contains(obj))
            {
                SmallSortedMap.this.remove(((java.util.Map.Entry) (obj)).getKey());
                return true;
            } else
            {
                return false;
            }
        }

        public int size()
        {
            return SmallSortedMap.this.size();
        }

        EntrySet()
        {
            this$0 = SmallSortedMap.this;
            super();
        }

        private class EntryIterator
            implements Iterator
        {

            private Iterator lazyOverflowIterator;
            private boolean nextCalledBeforeRemove;
            private int pos;
            private final SmallSortedMap this$0;

            private final Iterator getOverflowIterator()
            {
                if (lazyOverflowIterator == null)
                {
                    lazyOverflowIterator = overflowEntries.entrySet().iterator();
                }
                return lazyOverflowIterator;
            }

            public final boolean hasNext()
            {
                return pos + 1 < entryList.size() || !overflowEntries.isEmpty() && getOverflowIterator().hasNext();
            }

            public final Object next()
            {
                nextCalledBeforeRemove = true;
                int i = pos + 1;
                pos = i;
                if (i < entryList.size())
                {
                    return (java.util.Map.Entry)entryList.get(pos);
                } else
                {
                    return (java.util.Map.Entry)getOverflowIterator().next();
                }
            }

            public final void remove()
            {
                if (!nextCalledBeforeRemove)
                {
                    throw new IllegalStateException("remove() was called before next()");
                }
                nextCalledBeforeRemove = false;
                if (isImmutable)
                {
                    throw new UnsupportedOperationException();
                }
                if (pos < entryList.size())
                {
                    SmallSortedMap smallsortedmap = SmallSortedMap.this;
                    int i = pos;
                    pos = i - 1;
                    smallsortedmap.removeArrayEntryAt(i);
                    return;
                } else
                {
                    getOverflowIterator().remove();
                    return;
                }
            }

            EntryIterator()
            {
                this$0 = SmallSortedMap.this;
                super();
                pos = -1;
            }
        }

    }

}
