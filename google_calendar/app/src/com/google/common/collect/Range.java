// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Predicate;
import java.io.Serializable;

// Referenced classes of package com.google.common.collect:
//            RangeGwtSerializationDependencies, Cut

public final class Range extends RangeGwtSerializationDependencies
    implements Predicate, Serializable
{

    private static final Range ALL;
    public static final long serialVersionUID = 0L;
    public final Cut lowerBound;
    public final Cut upperBound;

    private Range(Cut cut, Cut cut1)
    {
        if (cut == null)
        {
            throw new NullPointerException();
        }
        lowerBound = (Cut)cut;
        if (cut1 == null)
        {
            throw new NullPointerException();
        }
        upperBound = (Cut)cut1;
        if (cut.compareTo(cut1) > 0 || cut == Cut.AboveAll.INSTANCE || cut1 == Cut.BelowAll.INSTANCE)
        {
            StringBuilder stringbuilder = new StringBuilder(16);
            cut.describeAsLowerBound(stringbuilder);
            stringbuilder.append("..");
            cut1.describeAsUpperBound(stringbuilder);
            cut = String.valueOf(stringbuilder.toString());
            if (cut.length() != 0)
            {
                cut = "Invalid range: ".concat(cut);
            } else
            {
                cut = new String("Invalid range: ");
            }
            throw new IllegalArgumentException(cut);
        } else
        {
            return;
        }
    }

    public static Range closed(Comparable comparable, Comparable comparable1)
    {
        return new Range(new Cut.BelowValue(comparable), new Cut.AboveValue(comparable1));
    }

    public static Range closedOpen(Comparable comparable, Comparable comparable1)
    {
        return new Range(new Cut.BelowValue(comparable), new Cut.BelowValue(comparable1));
    }

    static int compareOrThrow(Comparable comparable, Comparable comparable1)
    {
        return comparable.compareTo(comparable1);
    }

    public static Range singleton(Comparable comparable)
    {
        return new Range(new Cut.BelowValue(comparable), new Cut.AboveValue(comparable));
    }

    public final boolean apply(Object obj)
    {
        return contains((Comparable)obj);
    }

    public final boolean contains(Comparable comparable)
    {
        if (comparable == null)
        {
            throw new NullPointerException();
        }
        return lowerBound.isLessThan(comparable) && !upperBound.isLessThan(comparable);
    }

    public final boolean equals(Object obj)
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if (obj instanceof Range)
        {
            obj = (Range)obj;
            flag = flag1;
            if (lowerBound.equals(((Range) (obj)).lowerBound))
            {
                flag = flag1;
                if (upperBound.equals(((Range) (obj)).upperBound))
                {
                    flag = true;
                }
            }
        }
        return flag;
    }

    public final int hashCode()
    {
        return lowerBound.hashCode() * 31 + upperBound.hashCode();
    }

    final Object readResolve()
    {
        Range range = this;
        if (equals(ALL))
        {
            range = ALL;
        }
        return range;
    }

    public final String toString()
    {
        Cut cut = lowerBound;
        Cut cut1 = upperBound;
        StringBuilder stringbuilder = new StringBuilder(16);
        cut.describeAsLowerBound(stringbuilder);
        stringbuilder.append("..");
        cut1.describeAsUpperBound(stringbuilder);
        return stringbuilder.toString();
    }

    static 
    {
        ALL = new Range(Cut.BelowAll.INSTANCE, Cut.AboveAll.INSTANCE);
    }
}
