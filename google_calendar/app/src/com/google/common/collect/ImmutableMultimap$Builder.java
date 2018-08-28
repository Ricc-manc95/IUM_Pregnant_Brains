// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package com.google.common.collect:
//            ImmutableMultimap, CompactHashMap, EmptyImmutableListMultimap, ImmutableList, 
//            ImmutableListMultimap, CollectPreconditions

public static class builderMap
{

    private Map builderMap;

    public ImmutableMultimap build()
    {
        java.util.Set set = builderMap.entrySet();
        if (set.isEmpty())
        {
            return EmptyImmutableListMultimap.INSTANCE;
        }
        builderMap buildermap = new >(set.size());
        Iterator iterator = set.iterator();
        int i = 0;
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj = (java.util.ap.Builder)iterator.next();
            Object obj1 = ((java.util.ap.Builder) (obj)).Builder();
            obj = (Collection)((java.util.ap.Builder) (obj)).Builder();
            if (true)
            {
                obj = ImmutableList.copyOf(((Collection) (obj)));
            } else
            {
                obj = ImmutableList.sortedCopyOf(null, ((Iterable) (obj)));
            }
            if (!((ImmutableList) (obj)).isEmpty())
            {
                buildermap.(obj1, obj);
                i = ((ImmutableList) (obj)).size() + i;
            }
        } while (true);
        return new ImmutableListMultimap(buildermap.(), i);
    }

    public  put(Object obj, Object obj1)
    {
        CollectPreconditions.checkEntryNotNull(obj, obj1);
        Collection collection = (Collection)builderMap.get(obj);
        Object obj2 = collection;
        if (collection == null)
        {
            Map map = builderMap;
            obj2 = new ArrayList();
            map.put(obj, obj2);
        }
        ((Collection) (obj2)).add(obj1);
        return this;
    }

    public ()
    {
        builderMap = new CompactHashMap();
    }
}
