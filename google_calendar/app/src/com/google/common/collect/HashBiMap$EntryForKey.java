// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


// Referenced classes of package com.google.common.collect:
//            AbstractMapEntry, HashBiMap

final class index extends AbstractMapEntry
{

    private int index;
    private final Object key;
    private final HashBiMap this$0;

    private final void updateIndex()
    {
label0:
        {
            if (index != -1 && index <= size)
            {
                Object obj = keys[index];
                Object obj1 = key;
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
            index = findEntryByKey(key);
        }
    }

    public final Object getKey()
    {
        return key;
    }

    public final Object getValue()
    {
        updateIndex();
        if (index == -1)
        {
            return null;
        } else
        {
            return values[index];
        }
    }

    public final Object setValue(Object obj)
    {
        updateIndex();
        Object obj1;
        if (index == -1)
        {
            obj1 = put(key, obj);
        } else
        {
            Object obj2 = values[index];
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
                replaceValueInEntry(index, obj, false);
                return obj2;
            }
        }
        return obj1;
    }

    (int i)
    {
        this$0 = HashBiMap.this;
        super();
        key = keys[i];
        index = i;
    }
}
