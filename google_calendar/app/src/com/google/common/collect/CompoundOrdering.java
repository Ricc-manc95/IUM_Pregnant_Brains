// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

// Referenced classes of package com.google.common.collect:
//            Ordering

public final class CompoundOrdering extends Ordering
    implements Serializable
{

    public static final long serialVersionUID = 0L;
    private final Comparator comparators[];

    public CompoundOrdering(Comparator comparator, Comparator comparator1)
    {
        comparators = (new Comparator[] {
            comparator, comparator1
        });
    }

    public final int compare(Object obj, Object obj1)
    {
        boolean flag = false;
        int i = 0;
        do
        {
label0:
            {
                int j = ((flag) ? 1 : 0);
                if (i < comparators.length)
                {
                    j = comparators[i].compare(obj, obj1);
                    if (j == 0)
                    {
                        break label0;
                    }
                }
                return j;
            }
            i++;
        } while (true);
    }

    public final boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj instanceof CompoundOrdering)
        {
            obj = (CompoundOrdering)obj;
            return Arrays.equals(comparators, ((CompoundOrdering) (obj)).comparators);
        } else
        {
            return false;
        }
    }

    public final int hashCode()
    {
        return Arrays.hashCode(comparators);
    }

    public final String toString()
    {
        String s = Arrays.toString(comparators);
        return (new StringBuilder(String.valueOf(s).length() + 19)).append("Ordering.compound(").append(s).append(")").toString();
    }
}
