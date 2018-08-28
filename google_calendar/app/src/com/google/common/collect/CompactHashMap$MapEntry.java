// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


// Referenced classes of package com.google.common.collect:
//            AbstractMapEntry, CompactHashMap

final class lastKnownIndex extends AbstractMapEntry
{

    private final Object key;
    private int lastKnownIndex;
    private final CompactHashMap this$0;

    private final void updateLastKnownIndex()
    {
label0:
        {
            if (lastKnownIndex != -1 && lastKnownIndex < size())
            {
                Object obj = key;
                Object obj1 = keys[lastKnownIndex];
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
            lastKnownIndex = indexOf(key);
        }
    }

    public final Object getKey()
    {
        return key;
    }

    public final Object getValue()
    {
        updateLastKnownIndex();
        if (lastKnownIndex == -1)
        {
            return null;
        } else
        {
            return values[lastKnownIndex];
        }
    }

    public final Object setValue(Object obj)
    {
        updateLastKnownIndex();
        if (lastKnownIndex == -1)
        {
            put(key, obj);
            return null;
        } else
        {
            Object obj1 = values[lastKnownIndex];
            values[lastKnownIndex] = obj;
            return obj1;
        }
    }

    (int i)
    {
        this$0 = CompactHashMap.this;
        super();
        key = keys[i];
        lastKnownIndex = i;
    }
}
