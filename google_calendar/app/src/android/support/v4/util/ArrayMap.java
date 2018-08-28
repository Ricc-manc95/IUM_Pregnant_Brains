// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package android.support.v4.util:
//            SimpleArrayMap, MapCollections

public class ArrayMap extends SimpleArrayMap
    implements Map
{

    private MapCollections mCollections;

    public ArrayMap()
    {
    }

    public ArrayMap(int i)
    {
        super(i);
    }

    public ArrayMap(SimpleArrayMap simplearraymap)
    {
        super(simplearraymap);
    }

    public Set entrySet()
    {
        if (mCollections == null)
        {
            mCollections = new _cls1();
        }
        MapCollections mapcollections = mCollections;
        if (mapcollections.mEntrySet == null)
        {
            mapcollections.mEntrySet = new MapCollections.EntrySet(mapcollections);
        }
        return mapcollections.mEntrySet;
    }

    public Set keySet()
    {
        if (mCollections == null)
        {
            mCollections = new _cls1();
        }
        MapCollections mapcollections = mCollections;
        if (mapcollections.mKeySet == null)
        {
            mapcollections.mKeySet = new MapCollections.KeySet(mapcollections);
        }
        return mapcollections.mKeySet;
    }

    public void putAll(Map map)
    {
        ensureCapacity(mSize + map.size());
        java.util.Map.Entry entry;
        for (map = map.entrySet().iterator(); map.hasNext(); put(entry.getKey(), entry.getValue()))
        {
            entry = (java.util.Map.Entry)map.next();
        }

    }

    public Collection values()
    {
        if (mCollections == null)
        {
            mCollections = new _cls1();
        }
        MapCollections mapcollections = mCollections;
        if (mapcollections.mValues == null)
        {
            mapcollections.mValues = new MapCollections.ValuesCollection(mapcollections);
        }
        return mapcollections.mValues;
    }

    private class _cls1 extends MapCollections
    {

        private final ArrayMap this$0;

        protected final void colClear()
        {
            clear();
        }

        protected final Object colGetEntry(int i, int j)
        {
            return mArray[(i << 1) + j];
        }

        protected final Map colGetMap()
        {
            return ArrayMap.this;
        }

        protected final int colGetSize()
        {
            return mSize;
        }

        protected final int colIndexOfKey(Object obj)
        {
            ArrayMap arraymap = ArrayMap.this;
            if (obj == null)
            {
                return arraymap.indexOfNull();
            } else
            {
                return arraymap.indexOf(obj, obj.hashCode());
            }
        }

        protected final int colIndexOfValue(Object obj)
        {
            return indexOfValue(obj);
        }

        protected final void colPut(Object obj, Object obj1)
        {
            put(obj, obj1);
        }

        protected final void colRemoveAt(int i)
        {
            removeAt(i);
        }

        protected final Object colSetValue(int i, Object obj)
        {
            ArrayMap arraymap = ArrayMap.this;
            i = (i << 1) + 1;
            Object obj1 = ((SimpleArrayMap) (arraymap)).mArray[i];
            ((SimpleArrayMap) (arraymap)).mArray[i] = obj;
            return obj1;
        }

        _cls1()
        {
            this$0 = ArrayMap.this;
            super();
        }
    }

}
