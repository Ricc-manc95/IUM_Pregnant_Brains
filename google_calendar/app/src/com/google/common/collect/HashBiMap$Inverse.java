// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Set;

// Referenced classes of package com.google.common.collect:
//            BiMap, HashBiMap

final class forward extends AbstractMap
    implements BiMap, Serializable
{

    private final HashBiMap forward;
    private transient Set inverseEntrySet;

    private final void readObject(ObjectInputStream objectinputstream)
        throws ClassNotFoundException, IOException
    {
        objectinputstream.defaultReadObject();
        forward.inverse = this;
    }

    public final void clear()
    {
        forward.clear();
    }

    public final boolean containsKey(Object obj)
    {
        return forward.containsValue(obj);
    }

    public final boolean containsValue(Object obj)
    {
        return forward.containsKey(obj);
    }

    public final Set entrySet()
    {
        Set set = inverseEntrySet;
        Object obj = set;
        if (set == null)
        {
            obj = new ntrySet(forward);
            inverseEntrySet = ((Set) (obj));
        }
        return ((Set) (obj));
    }

    public final Object get(Object obj)
    {
        HashBiMap hashbimap = forward;
        int i = hashbimap.findEntryByValue(obj);
        if (i == -1)
        {
            return null;
        } else
        {
            return hashbimap.keys[i];
        }
    }

    public final BiMap inverse()
    {
        return forward;
    }

    public final Set keySet()
    {
        return (Set)forward.values();
    }

    public final Object put(Object obj, Object obj1)
    {
        return forward.putInverse(obj, obj1, false);
    }

    public final Object remove(Object obj)
    {
        boolean flag = false;
        HashBiMap hashbimap = forward;
        int i;
        int j;
        int k;
        if (obj == null)
        {
            i = 0;
        } else
        {
            i = obj.hashCode();
        }
        j = (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L);
        k = hashbimap.findEntry(obj, j, hashbimap.hashTableVToK, hashbimap.nextInBucketVToK, hashbimap.values);
        if (k == -1)
        {
            return null;
        }
        obj = hashbimap.keys[k];
        Object obj1 = hashbimap.keys[k];
        if (obj1 == null)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = obj1.hashCode();
        }
        hashbimap.removeEntry(k, (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L), j);
        return obj;
    }

    public final int size()
    {
        return forward.size;
    }

    public final Collection values()
    {
        return forward.keySet();
    }

    ntrySet(HashBiMap hashbimap)
    {
        forward = hashbimap;
    }
}
