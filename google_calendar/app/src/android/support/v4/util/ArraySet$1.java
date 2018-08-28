// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.util;

import java.util.Map;

// Referenced classes of package android.support.v4.util:
//            MapCollections, ArraySet

final class ons extends MapCollections
{

    private final ArraySet this$0;

    protected final void colClear()
    {
        clear();
    }

    protected final Object colGetEntry(int i, int j)
    {
        return mArray[i];
    }

    protected final Map colGetMap()
    {
        throw new UnsupportedOperationException("not a map");
    }

    protected final int colGetSize()
    {
        return mSize;
    }

    protected final int colIndexOfKey(Object obj)
    {
        ArraySet arrayset = ArraySet.this;
        if (obj == null)
        {
            return arrayset.indexOfNull();
        } else
        {
            return arrayset.indexOf(obj, obj.hashCode());
        }
    }

    protected final int colIndexOfValue(Object obj)
    {
        ArraySet arrayset = ArraySet.this;
        if (obj == null)
        {
            return arrayset.indexOfNull();
        } else
        {
            return arrayset.indexOf(obj, obj.hashCode());
        }
    }

    protected final void colPut(Object obj, Object obj1)
    {
        add(obj);
    }

    protected final void colRemoveAt(int i)
    {
        removeAt(i);
    }

    protected final Object colSetValue(int i, Object obj)
    {
        throw new UnsupportedOperationException("not a map");
    }

    tion()
    {
        this$0 = ArraySet.this;
        super();
    }
}
