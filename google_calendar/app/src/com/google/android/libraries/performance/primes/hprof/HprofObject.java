// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;

import com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.libraries.performance.primes.hprof:
//            ParseResult, HprofClassInstance, HprofClass, ParseContext

public abstract class HprofObject
    implements com.android.ahat.dominators.DominatorsComputation.Node
{

    public static ParseContext parseContext;
    public static ParseResult parseResult;
    public int flags;
    public String heapName;
    public HprofObject immediateDominator;
    public final List immediatelyDominated = new ArrayList();
    private Object intermediateDominatorsComputationState;
    public HprofObject parent;
    public Set parents;
    public int position;
    public int retainedHeapSize;
    public int rootTag;
    public boolean visited;

    protected HprofObject(int i)
    {
        parents = new HashSet();
        visited = false;
        retainedHeapSize = -1;
        position = i;
    }

    public static void computeRetainedSizes(HprofObject hprofobject, ParseContext parsecontext)
    {
        ArrayDeque arraydeque = new ArrayDeque();
        arraydeque.push(hprofobject);
        while (!arraydeque.isEmpty()) 
        {
            hprofobject = (HprofObject)arraydeque.pop();
            if (hprofobject.retainedHeapSize == -1)
            {
                hprofobject.retainedHeapSize = hprofobject.computeShallowSize(parsecontext);
                arraydeque.push(hprofobject);
                hprofobject = hprofobject.immediatelyDominated.iterator();
                while (hprofobject.hasNext()) 
                {
                    arraydeque.push((HprofObject)hprofobject.next());
                }
            } else
            {
                Iterator iterator = hprofobject.immediatelyDominated.iterator();
                while (iterator.hasNext()) 
                {
                    HprofObject hprofobject1 = (HprofObject)iterator.next();
                    int i = hprofobject.retainedHeapSize;
                    hprofobject.retainedHeapSize = hprofobject1.retainedHeapSize + i;
                }
            }
        }
    }

    public abstract String buildLeakSegment(ParseContext parsecontext, int i);

    public abstract int computeShallowSize(ParseContext parsecontext);

    public abstract int getChildCount(ParseContext parsecontext);

    public abstract String getChildName(ParseContext parsecontext, int i);

    public abstract int getChildValue(ParseContext parsecontext, int i);

    public final Object getDominatorsComputationState()
    {
        return intermediateDominatorsComputationState;
    }

    public Iterable getReferencesForDominators()
    {
        if (parseContext == null)
        {
            throw new NullPointerException();
        }
        if (parseResult == null)
        {
            throw new NullPointerException();
        }
        int k = getChildCount(parseContext);
        ArrayList arraylist = new ArrayList(k);
        int i = 0;
        while (i < k) 
        {
            int j = getChildValue(parseContext, i);
            Object obj = parseResult.classInstances;
            Object obj1 = ((IntObjectMap) (obj)).values[((IntObjectMap) (obj)).findKeyIndex(j)];
            obj = obj1;
            if (obj1 == IntObjectMap.DELETED)
            {
                obj = null;
            }
            obj = (HprofObject)obj;
            if (obj == null)
            {
                obj = parseResult.classes;
                Object obj2 = ((IntObjectMap) (obj)).values[((IntObjectMap) (obj)).findKeyIndex(j)];
                obj = obj2;
                if (obj2 == IntObjectMap.DELETED)
                {
                    obj = null;
                }
                obj = (HprofObject)obj;
            }
            if (obj == null)
            {
                continue;
            }
            boolean flag;
            if ((this instanceof HprofClassInstance) && (((HprofClassInstance)this).clazz.flags & 2) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                arraylist.add(obj);
            }
            i++;
        }
        return arraylist;
    }

    public final void setDominator(com.android.ahat.dominators.DominatorsComputation.Node node)
    {
        immediateDominator = (HprofObject)node;
        immediateDominator.immediatelyDominated.add(this);
    }

    public final void setDominatorsComputationState(Object obj)
    {
        intermediateDominatorsComputationState = obj;
    }
}
