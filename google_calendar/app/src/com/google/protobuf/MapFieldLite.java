// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.protobuf:
//            Internal

public final class MapFieldLite extends LinkedHashMap
{

    public static final MapFieldLite EMPTY_MAP_FIELD;
    public boolean isMutable;

    MapFieldLite()
    {
        isMutable = true;
    }

    MapFieldLite(Map map)
    {
        super(map);
        isMutable = true;
    }

    private static int calculateHashCodeForObject(Object obj)
    {
        if (obj instanceof byte[])
        {
            return Internal.hashCode((byte[])obj);
        }
        if (obj instanceof Internal.EnumLite)
        {
            throw new UnsupportedOperationException();
        } else
        {
            return obj.hashCode();
        }
    }

    public final void clear()
    {
        if (!isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            super.clear();
            return;
        }
    }

    public final Set entrySet()
    {
        if (isEmpty())
        {
            return Collections.emptySet();
        } else
        {
            return super.entrySet();
        }
    }

    public final boolean equals(Object obj)
    {
        if (!(obj instanceof Map)) goto _L2; else goto _L1
_L1:
        obj = (Map)obj;
        if (this == obj) goto _L4; else goto _L3
_L3:
        if (size() == ((Map) (obj)).size()) goto _L6; else goto _L5
_L5:
        boolean flag = false;
_L10:
        if (flag)
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
_L6:
        Iterator iterator = entrySet().iterator();
_L9:
        if (!iterator.hasNext()) goto _L4; else goto _L7
_L7:
        Object obj2 = (java.util.Map.Entry)iterator.next();
        if (!((Map) (obj)).containsKey(((java.util.Map.Entry) (obj2)).getKey()))
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        Object obj1 = ((java.util.Map.Entry) (obj2)).getValue();
        obj2 = ((Map) (obj)).get(((java.util.Map.Entry) (obj2)).getKey());
        boolean flag1;
        if ((obj1 instanceof byte[]) && (obj2 instanceof byte[]))
        {
            flag1 = Arrays.equals((byte[])obj1, (byte[])obj2);
        } else
        {
            flag1 = obj1.equals(obj2);
        }
        if (flag1) goto _L9; else goto _L8
_L8:
        flag = false;
        continue; /* Loop/switch isn't completed */
_L4:
        flag = true;
        if (true) goto _L10; else goto _L2
_L2:
        return false;
    }

    public final int hashCode()
    {
        Iterator iterator = entrySet().iterator();
        java.util.Map.Entry entry;
        int i;
        int j;
        for (i = 0; iterator.hasNext(); i = (calculateHashCodeForObject(entry.getValue()) ^ j) + i)
        {
            entry = (java.util.Map.Entry)iterator.next();
            j = calculateHashCodeForObject(entry.getKey());
        }

        return i;
    }

    public final Object put(Object obj, Object obj1)
    {
        if (!isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            Internal.checkNotNull(obj);
            Internal.checkNotNull(obj1);
            return super.put(obj, obj1);
        }
    }

    public final void putAll(Map map)
    {
        if (!isMutable)
        {
            throw new UnsupportedOperationException();
        }
        Object obj;
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext(); Internal.checkNotNull(map.get(obj)))
        {
            obj = iterator.next();
            Internal.checkNotNull(obj);
        }

        super.putAll(map);
    }

    public final Object remove(Object obj)
    {
        if (!isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            return super.remove(obj);
        }
    }

    static 
    {
        MapFieldLite mapfieldlite = new MapFieldLite();
        EMPTY_MAP_FIELD = mapfieldlite;
        mapfieldlite.isMutable = false;
    }
}
