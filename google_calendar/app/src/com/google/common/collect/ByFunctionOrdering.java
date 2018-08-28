// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Function;
import java.io.Serializable;
import java.util.Arrays;

// Referenced classes of package com.google.common.collect:
//            Ordering

public final class ByFunctionOrdering extends Ordering
    implements Serializable
{

    public static final long serialVersionUID = 0L;
    private final Function function;
    private final Ordering ordering;

    public ByFunctionOrdering(Function function1, Ordering ordering1)
    {
        if (function1 == null)
        {
            throw new NullPointerException();
        }
        function = (Function)function1;
        if (ordering1 == null)
        {
            throw new NullPointerException();
        } else
        {
            ordering = (Ordering)ordering1;
            return;
        }
    }

    public final int compare(Object obj, Object obj1)
    {
        return ordering.compare(function.apply(obj), function.apply(obj1));
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof ByFunctionOrdering)
            {
                if (!function.equals(((ByFunctionOrdering) (obj = (ByFunctionOrdering)obj)).function) || !ordering.equals(((ByFunctionOrdering) (obj)).ordering))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            function, ordering
        });
    }

    public final String toString()
    {
        String s = String.valueOf(ordering);
        String s1 = String.valueOf(function);
        return (new StringBuilder(String.valueOf(s).length() + 13 + String.valueOf(s1).length())).append(s).append(".onResultOf(").append(s1).append(")").toString();
    }
}
