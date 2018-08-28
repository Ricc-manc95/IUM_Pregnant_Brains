// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.tags;

import java.util.HashMap;
import java.util.Iterator;

// Referenced classes of package io.opencensus.tags:
//            Tag

public abstract class TagContext
{

    public TagContext()
    {
    }

    public boolean equals(Object obj)
    {
        Object obj1;
        HashMap hashmap;
        if (!(obj instanceof TagContext))
        {
            return false;
        }
        obj1 = (TagContext)obj;
        obj = getIterator();
        obj1 = ((TagContext) (obj1)).getIterator();
        hashmap = new HashMap();
        while (obj != null && ((Iterator) (obj)).hasNext()) 
        {
            Tag tag = (Tag)((Iterator) (obj)).next();
            if (hashmap.containsKey(tag))
            {
                hashmap.put(tag, Integer.valueOf(((Integer)hashmap.get(tag)).intValue() + 1));
            } else
            {
                hashmap.put(tag, Integer.valueOf(1));
            }
        }
_L3:
        if (obj1 != null && ((Iterator) (obj1)).hasNext())
        {
            if (!hashmap.containsKey(obj = (Tag)((Iterator) (obj1)).next()))
            {
                return false;
            }
        } else
        {
            return hashmap.isEmpty();
        }
        if (true) goto _L2; else goto _L1
_L2:
        int i = ((Integer)hashmap.get(obj)).intValue();
        if (i > 1)
        {
            hashmap.put(obj, Integer.valueOf(i - 1));
        } else
        {
            hashmap.remove(obj);
        }
        if (true) goto _L3; else goto _L1
_L1:
    }

    protected abstract Iterator getIterator();

    public final int hashCode()
    {
_L2:
        int i;
        if (iterator.hasNext())
        {
            Tag tag = (Tag)iterator.next();
            if (tag != null)
            {
                i = tag.hashCode() + i;
            }
        } else
        {
            return i;
        }
        continue; /* Loop/switch isn't completed */
        Iterator iterator = getIterator();
        if (iterator == null)
        {
            return 0;
        }
        i = 0;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public String toString()
    {
        return "TagContext";
    }
}
