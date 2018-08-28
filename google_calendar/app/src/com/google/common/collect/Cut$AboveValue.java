// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


// Referenced classes of package com.google.common.collect:
//            Cut, Range

final class  extends Cut
{

    public static final long serialVersionUID = 0L;

    final void describeAsLowerBound(StringBuilder stringbuilder)
    {
        stringbuilder.append('(').append(endpoint);
    }

    final void describeAsUpperBound(StringBuilder stringbuilder)
    {
        stringbuilder.append(endpoint).append(']');
    }

    public final int hashCode()
    {
        return ~endpoint.hashCode();
    }

    final boolean isLessThan(Comparable comparable)
    {
        return Range.compareOrThrow(endpoint, comparable) < 0;
    }

    public final String toString()
    {
        String s = String.valueOf(endpoint);
        return (new StringBuilder(String.valueOf(s).length() + 2)).append("/").append(s).append("\\").toString();
    }

    (Comparable comparable)
    {
        if (comparable == null)
        {
            throw new NullPointerException();
        } else
        {
            super((Comparable)comparable);
            return;
        }
    }
}
