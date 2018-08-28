// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.io.Serializable;

// Referenced classes of package com.google.common.collect:
//            Ordering

public final class NaturalOrdering extends Ordering
    implements Serializable
{

    public static final NaturalOrdering INSTANCE = new NaturalOrdering();
    public static final long serialVersionUID = 0L;
    private transient Ordering nullsFirst;

    private NaturalOrdering()
    {
    }

    private final Object readResolve()
    {
        return INSTANCE;
    }

    public final int compare(Object obj, Object obj1)
    {
        obj = (Comparable)obj;
        obj1 = (Comparable)obj1;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (obj1 == null)
        {
            throw new NullPointerException();
        } else
        {
            return ((Comparable) (obj)).compareTo(obj1);
        }
    }

    public final Ordering nullsFirst()
    {
        Ordering ordering1 = nullsFirst;
        Ordering ordering = ordering1;
        if (ordering1 == null)
        {
            ordering = super.nullsFirst();
            nullsFirst = ordering;
        }
        return ordering;
    }

    public final String toString()
    {
        return "Ordering.natural()";
    }

}
