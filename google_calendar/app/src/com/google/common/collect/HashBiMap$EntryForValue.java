// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


// Referenced classes of package com.google.common.collect:
//            AbstractMapEntry, HashBiMap

final class index extends AbstractMapEntry
{

    private final HashBiMap biMap;
    private int index;
    private final Object value;

    private final void updateIndex()
    {
label0:
        {
            if (index != -1 && index <= biMap.size)
            {
                Object obj = value;
                Object obj1 = biMap.values[index];
                boolean flag;
                if (obj == obj1 || obj != null && obj.equals(obj1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    break label0;
                }
            }
            index = biMap.findEntryByValue(value);
        }
    }

    public final Object getKey()
    {
        return value;
    }

    public final Object getValue()
    {
        updateIndex();
        if (index == -1)
        {
            return null;
        } else
        {
            return biMap.keys[index];
        }
    }

    public final Object setValue(Object obj)
    {
        updateIndex();
        Object obj1;
        if (index == -1)
        {
            obj1 = biMap.putInverse(value, obj, false);
        } else
        {
            Object obj2 = biMap.keys[index];
            boolean flag;
            if (obj2 == obj || obj2 != null && obj2.equals(obj))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            obj1 = obj;
            if (!flag)
            {
                biMap.replaceKeyInEntry(index, obj, false);
                return obj2;
            }
        }
        return obj1;
    }

    (HashBiMap hashbimap, int i)
    {
        biMap = hashbimap;
        value = hashbimap.values[i];
        index = i;
    }
}
