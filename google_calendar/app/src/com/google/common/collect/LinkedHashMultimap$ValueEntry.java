// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


// Referenced classes of package com.google.common.collect:
//            ImmutableEntry, AbstractMapEntry

final class nextInValueBucket extends ImmutableEntry
    implements k
{

    public successorInValueSet nextInValueBucket;
    public successorInValueSet predecessorInMultimap;
    private k predecessorInValueSet;
    public final int smearedValueHash;
    public k successorInMultimap;
    public k successorInValueSet;

    public final k getPredecessorInValueSet()
    {
        return predecessorInValueSet;
    }

    public final k getSuccessorInValueSet()
    {
        return successorInValueSet;
    }

    final boolean matchesValue(Object obj, int i)
    {
        if (smearedValueHash == i)
        {
            Object obj1 = getValue();
            if (obj1 == obj || obj1 != null && obj1.equals(obj))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                return true;
            }
        }
        return false;
    }

    public final void setPredecessorInValueSet(k k)
    {
        predecessorInValueSet = k;
    }

    public final void setSuccessorInValueSet(k k)
    {
        successorInValueSet = k;
    }

    k(Object obj, Object obj1, int i, k k)
    {
        super(obj, obj1);
        smearedValueHash = i;
        nextInValueBucket = k;
    }
}
