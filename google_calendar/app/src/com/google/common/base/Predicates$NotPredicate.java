// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.io.Serializable;

// Referenced classes of package com.google.common.base:
//            Predicate

public final class predicate
    implements Predicate, Serializable
{

    public static final long serialVersionUID = 0L;
    private final Predicate predicate;

    public final boolean apply(Object obj)
    {
        return !predicate.apply(obj);
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof predicate)
        {
            obj = (predicate)obj;
            return predicate.equals(((predicate) (obj)).predicate);
        } else
        {
            return false;
        }
    }

    public final int hashCode()
    {
        return ~predicate.hashCode();
    }

    public final String toString()
    {
        String s = String.valueOf(predicate);
        return (new StringBuilder(String.valueOf(s).length() + 16)).append("Predicates.not(").append(s).append(")").toString();
    }

    public (Predicate predicate1)
    {
        if (predicate1 == null)
        {
            throw new NullPointerException();
        } else
        {
            predicate = (Predicate)predicate1;
            return;
        }
    }
}
