// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class ArrayMap extends AbstractMap
    implements Cloneable
{
    final class Entry
        implements java.util.Map.Entry
    {

        private int index;
        private final ArrayMap this$0;

        public final boolean equals(Object obj)
        {
            if (this != obj) goto _L2; else goto _L1
_L1:
            return true;
_L2:
            if (!(obj instanceof java.util.Map.Entry))
            {
                return false;
            }
            obj = (java.util.Map.Entry)obj;
            Object obj1 = getKey();
            Object obj2 = ((java.util.Map.Entry) (obj)).getKey();
            boolean flag;
            if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                break; /* Loop/switch isn't completed */
            }
            obj1 = getValue();
            obj = ((java.util.Map.Entry) (obj)).getValue();
            if (obj1 == obj || obj1 != null && obj1.equals(obj))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag) goto _L1; else goto _L3
_L3:
            return false;
        }

        public final Object getKey()
        {
            ArrayMap arraymap = ArrayMap.this;
            int i = index;
            if (i < 0 || i >= arraymap.size)
            {
                return null;
            } else
            {
                return arraymap.data[i << 1];
            }
        }

        public final Object getValue()
        {
            return ArrayMap.this.getValue(index);
        }

        public final int hashCode()
        {
            int j = 0;
            Object obj = getKey();
            Object obj1 = getValue();
            int i;
            if (obj != null)
            {
                i = obj.hashCode();
            } else
            {
                i = 0;
            }
            if (obj1 != null)
            {
                j = obj1.hashCode();
            }
            return j ^ i;
        }

        public final Object setValue(Object obj)
        {
            return set(index, obj);
        }

        Entry(int i)
        {
            this$0 = ArrayMap.this;
            super();
            index = i;
        }
    }

    final class EntryIterator
        implements Iterator
    {

        private int nextIndex;
        private boolean removed;
        private final ArrayMap this$0;

        public final boolean hasNext()
        {
            return nextIndex < size;
        }

        public final Object next()
        {
            int i = nextIndex;
            if (i == size)
            {
                throw new NoSuchElementException();
            } else
            {
                nextIndex = nextIndex + 1;
                return new ArrayMap.Entry(i);
            }
        }

        public final void remove()
        {
            int i = nextIndex - 1;
            if (removed || i < 0)
            {
                throw new IllegalArgumentException();
            } else
            {
                removeFromDataIndexOfKey(i << 1);
                removed = true;
                return;
            }
        }

        EntryIterator()
        {
            this$0 = ArrayMap.this;
            super();
        }
    }

    final class EntrySet extends AbstractSet
    {

        private final ArrayMap this$0;

        public final Iterator iterator()
        {
            return new EntryIterator();
        }

        public final int size()
        {
            return ArrayMap.this.size;
        }

        EntrySet()
        {
            this$0 = ArrayMap.this;
            super();
        }
    }


    public Object data[];
    public int size;

    public ArrayMap()
    {
    }

    private final int getDataIndexOfKey(Object obj)
    {
        int j = size;
        Object aobj[] = data;
        for (int i = 0; i < j << 1; i += 2)
        {
            Object obj1 = aobj[i];
            if (obj != null ? obj.equals(obj1) : obj1 == null)
            {
                return i;
            }
        }

        return -2;
    }

    public void clear()
    {
        size = 0;
        data = null;
    }

    public final ArrayMap clone()
    {
        ArrayMap arraymap;
        Object aobj[];
        Object aobj1[];
        int i;
        try
        {
            arraymap = (ArrayMap)super.clone();
            aobj = data;
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            return null;
        }
        if (aobj == null)
        {
            break MISSING_BLOCK_LABEL_41;
        }
        i = aobj.length;
        aobj1 = new Object[i];
        arraymap.data = aobj1;
        System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj1)), 0, i);
        return arraymap;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public final boolean containsKey(Object obj)
    {
        return -2 != getDataIndexOfKey(obj);
    }

    public final boolean containsValue(Object obj)
    {
        int j = size;
        Object aobj[] = data;
        for (int i = 1; i < j << 1; i += 2)
        {
            Object obj1 = aobj[i];
            if (obj != null ? obj.equals(obj1) : obj1 == null)
            {
                return true;
            }
        }

        return false;
    }

    public final Set entrySet()
    {
        return new EntrySet();
    }

    public final Object get(Object obj)
    {
        int i = getDataIndexOfKey(obj) + 1;
        if (i < 0)
        {
            return null;
        } else
        {
            return data[i];
        }
    }

    public final Object getValue(int i)
    {
        if (i >= 0 && i < size)
        {
            if ((i = (i << 1) + 1) >= 0)
            {
                return data[i];
            }
        }
        return null;
    }

    public final Object put(Object obj, Object obj1)
    {
        Object obj2 = null;
        int j = getDataIndexOfKey(obj) >> 1;
        if (j == -1)
        {
            j = size;
        }
        if (j < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        int j1 = j + 1;
        if (j1 < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        Object aobj[] = data;
        int k = j1 << 1;
        int i;
        if (aobj == null)
        {
            i = 0;
        } else
        {
            i = aobj.length;
        }
        if (k > i)
        {
            int i1 = (i / 2) * 3 + 1;
            i = i1;
            if (i1 % 2 != 0)
            {
                i = i1 + 1;
            }
            if (i < k)
            {
                i = k;
            }
            if (i == 0)
            {
                data = null;
            } else
            {
                int l = size;
                Object aobj1[] = data;
                if (l == 0 || i != aobj1.length)
                {
                    Object aobj2[] = new Object[i];
                    data = aobj2;
                    if (l != 0)
                    {
                        System.arraycopy(((Object) (aobj1)), 0, ((Object) (aobj2)), 0, l << 1);
                    }
                }
            }
        }
        i = j << 1;
        j = i + 1;
        if (j >= 0)
        {
            obj2 = data[j];
        }
        aobj = data;
        aobj[i] = obj;
        aobj[i + 1] = obj1;
        if (j1 > size)
        {
            size = j1;
        }
        return obj2;
    }

    public final Object remove(Object obj)
    {
        return removeFromDataIndexOfKey(getDataIndexOfKey(obj));
    }

    final Object removeFromDataIndexOfKey(int i)
    {
        int j = size << 1;
        if (i < 0 || i >= j)
        {
            return null;
        }
        int k = i + 1;
        Object obj;
        Object aobj[];
        if (k < 0)
        {
            obj = null;
        } else
        {
            obj = data[k];
        }
        aobj = data;
        k = j - i - 2;
        if (k != 0)
        {
            System.arraycopy(((Object) (aobj)), i + 2, ((Object) (aobj)), i, k);
        }
        size = size - 1;
        i = j - 2;
        aobj = data;
        aobj[i] = null;
        aobj[i + 1] = null;
        return obj;
    }

    public final Object set(int i, Object obj)
    {
        int j = size;
        if (i < 0 || i >= j)
        {
            throw new IndexOutOfBoundsException();
        }
        i = (i << 1) + 1;
        Object obj1;
        if (i < 0)
        {
            obj1 = null;
        } else
        {
            obj1 = data[i];
        }
        data[i] = obj;
        return obj1;
    }

    public final int size()
    {
        return size;
    }
}
