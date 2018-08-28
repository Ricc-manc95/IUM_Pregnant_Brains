// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.common.collect:
//            Multiset

public final class Sets
{

    static boolean equalsImpl(Set set, Object obj)
    {
        if (set != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof Set)) goto _L4; else goto _L3
_L3:
        obj = (Set)obj;
        boolean flag;
        if (set.size() != ((Set) (obj)).size())
        {
            break; /* Loop/switch isn't completed */
        }
        flag = set.containsAll(((Collection) (obj)));
        if (flag) goto _L1; else goto _L5
_L5:
        return false;
        set;
_L7:
        return false;
_L4:
        return false;
        set;
        if (true) goto _L7; else goto _L6
_L6:
    }

    static int hashCodeImpl(Set set)
    {
        set = set.iterator();
        int i = 0;
        while (set.hasNext()) 
        {
            Object obj = set.next();
            int j;
            if (obj != null)
            {
                j = obj.hashCode();
            } else
            {
                j = 0;
            }
            i = ~~(i + j);
        }
        return i;
    }

    static boolean removeAllImpl(Set set, Collection collection)
    {
        boolean flag2 = false;
        boolean flag = false;
        if (collection == null)
        {
            throw new NullPointerException();
        }
        Object obj = collection;
        if (collection instanceof Multiset)
        {
            obj = ((Multiset)collection).elementSet();
        }
        if ((obj instanceof Set) && ((Collection) (obj)).size() > set.size())
        {
            set = set.iterator();
            if (obj == null)
            {
                throw new NullPointerException();
            }
            do
            {
                flag2 = flag;
                if (!set.hasNext())
                {
                    break;
                }
                if (((Collection) (obj)).contains(set.next()))
                {
                    set.remove();
                    flag = true;
                }
            } while (true);
        } else
        {
            collection = ((Collection) (obj)).iterator();
            boolean flag1 = flag2;
            do
            {
                flag2 = flag1;
                if (!collection.hasNext())
                {
                    break;
                }
                flag1 |= set.remove(collection.next());
            } while (true);
        }
        return flag2;
    }
}
