// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.util;

import java.util.Objects;

public final class Pair
{

    public final Object first;
    public final Object second;

    public Pair(Object obj, Object obj1)
    {
        first = obj;
        second = obj1;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof Pair)
        {
            if (Objects.equals(((Pair) (obj = (Pair)obj)).first, first) && Objects.equals(((Pair) (obj)).second, second))
            {
                return true;
            }
        }
        return false;
    }

    public final int hashCode()
    {
        int j = 0;
        int i;
        if (first == null)
        {
            i = 0;
        } else
        {
            i = first.hashCode();
        }
        if (second != null)
        {
            j = second.hashCode();
        }
        return i ^ j;
    }

    public final String toString()
    {
        return (new StringBuilder("Pair{")).append(String.valueOf(first)).append(" ").append(String.valueOf(second)).append("}").toString();
    }
}
