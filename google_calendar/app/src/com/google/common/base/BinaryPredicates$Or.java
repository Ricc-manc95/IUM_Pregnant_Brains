// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.io.Serializable;
import java.util.Iterator;

// Referenced classes of package com.google.common.base:
//            BinaryPredicate

public final class predicates
    implements BinaryPredicate, Serializable
{

    public static final long serialVersionUID = 0xed3b10c96aaa0d98L;
    private final Iterable predicates;

    public final boolean apply(Object obj, Object obj1)
    {
        for (Iterator iterator = predicates.iterator(); iterator.hasNext();)
        {
            if (((BinaryPredicate)iterator.next()).apply(obj, obj1))
            {
                return true;
            }
        }

        return false;
    }

    public final boolean equals(Object obj)
    {
        if (!(obj instanceof y)) goto _L2; else goto _L1
_L1:
        Object obj1;
        obj1 = predicates;
        obj = ((predicates)obj).predicates;
        obj1 = ((Iterable) (obj1)).iterator();
        obj = ((Iterable) (obj)).iterator();
_L5:
        if (!((Iterator) (obj1)).hasNext()) goto _L4; else goto _L3
_L3:
        if (((Iterator) (obj)).hasNext() && ((Iterator) (obj1)).next().equals(((Iterator) (obj)).next())) goto _L5; else goto _L2
_L2:
        return false;
_L4:
        if (!((Iterator) (obj)).hasNext())
        {
            return true;
        }
        if (true) goto _L2; else goto _L6
_L6:
    }

    public final int hashCode()
    {
        Iterator iterator = predicates.iterator();
        int i;
        for (i = 1; iterator.hasNext(); i = i * 31 + iterator.next().hashCode()) { }
        return i;
    }

    public (Iterable iterable)
    {
        for (Iterator iterator = iterable.iterator(); iterator.hasNext();)
        {
            if ((BinaryPredicate)iterator.next() == null)
            {
                throw new NullPointerException();
            }
        }

        predicates = iterable;
    }
}
