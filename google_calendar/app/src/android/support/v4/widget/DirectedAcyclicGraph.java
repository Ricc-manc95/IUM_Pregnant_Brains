// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.support.v4.util.SimpleArrayMap;
import java.util.ArrayList;
import java.util.HashSet;

public final class DirectedAcyclicGraph
{

    public final SimpleArrayMap mGraph = new SimpleArrayMap();
    public final android.support.v4.util.Pools.Pool mListPool = new android.support.v4.util.Pools.SimplePool(10);
    public final ArrayList mSortResult = new ArrayList();
    public final HashSet mSortTmpMarked = new HashSet();

    public DirectedAcyclicGraph()
    {
    }

    public final void dfs(Object obj, ArrayList arraylist, HashSet hashset)
    {
        if (arraylist.contains(obj))
        {
            return;
        }
        if (hashset.contains(obj))
        {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashset.add(obj);
        ArrayList arraylist1 = (ArrayList)mGraph.get(obj);
        if (arraylist1 != null)
        {
            int i = 0;
            for (int j = arraylist1.size(); i < j; i++)
            {
                dfs(arraylist1.get(i), arraylist, hashset);
            }

        }
        hashset.remove(obj);
        arraylist.add(obj);
    }
}
