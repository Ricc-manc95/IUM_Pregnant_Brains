// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;

import com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.libraries.performance.primes.hprof:
//            HprofObject, HprofClassInstance, HprofClass, ParseContext

public final class HprofTraverser
{

    public static void bfs(ParseContext parsecontext, IntObjectMap intobjectmap, IntObjectMap intobjectmap1, Deque deque, BfsCallback bfscallback)
    {
        while (!deque.isEmpty()) 
        {
            HprofObject hprofobject = (HprofObject)deque.removeFirst();
            int k = hprofobject.getChildCount(parsecontext);
            int i = 0;
            while (i < k) 
            {
                int j = hprofobject.getChildValue(parsecontext, i);
                Object obj1 = intobjectmap.values[intobjectmap.findKeyIndex(j)];
                Object obj = obj1;
                if (obj1 == IntObjectMap.DELETED)
                {
                    obj = null;
                }
                obj = (HprofObject)obj;
                if (obj == null)
                {
                    Object obj2 = intobjectmap1.values[intobjectmap1.findKeyIndex(j)];
                    obj = obj2;
                    if (obj2 == IntObjectMap.DELETED)
                    {
                        obj = null;
                    }
                    obj = (HprofObject)obj;
                }
                if (obj != null)
                {
                    if (!((HprofObject) (obj)).visited)
                    {
                        if ((((HprofObject) (obj)).flags & 1) != 0)
                        {
                            j = 1;
                        } else
                        {
                            j = 0;
                        }
                        if (j == 0)
                        {
                            if ((obj instanceof HprofClassInstance) && (((HprofClassInstance)obj).clazz.flags & 2) != 0)
                            {
                                j = 1;
                            } else
                            {
                                j = 0;
                            }
                            if (j == 0)
                            {
                                deque.addLast(obj);
                            }
                        }
                    }
                    obj.visited = true;
                    bfscallback.edgeExplored(hprofobject, ((HprofObject) (obj)));
                }
                i++;
            }
        }
    }

    static void clearTraversal(IntObjectMap intobjectmap, IntObjectMap intobjectmap1)
    {
        for (intobjectmap = new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator(intobjectmap.keys, intobjectmap.values); intobjectmap.next();)
        {
            ((HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (intobjectmap)).value).visited = false;
        }

        for (intobjectmap = new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator(intobjectmap1.keys, intobjectmap1.values); intobjectmap.next();)
        {
            ((HprofClass)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (intobjectmap)).value).visited = false;
        }

    }

    public static Deque getRootsQueue(List list)
    {
        ArrayDeque arraydeque = new ArrayDeque();
        list = list.iterator();
        do
        {
            if (!list.hasNext())
            {
                break;
            }
            HprofObject hprofobject = (HprofObject)list.next();
            boolean flag;
            if ((hprofobject instanceof HprofClassInstance) && (((HprofClassInstance)hprofobject).clazz.flags & 2) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                arraydeque.addLast(hprofobject);
            }
        } while (true);
        return arraydeque;
    }

    private class BfsCallback
    {

        public abstract void edgeExplored(HprofObject hprofobject, HprofObject hprofobject1);
    }

}
