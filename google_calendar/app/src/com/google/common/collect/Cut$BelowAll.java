// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


// Referenced classes of package com.google.common.collect:
//            Cut

final class  extends Cut
{

    public static final  INSTANCE = new <init>();
    public static final long serialVersionUID = 0L;

    private final Object readResolve()
    {
        return INSTANCE;
    }

    public final int compareTo(Cut cut)
    {
        return cut != this ? -1 : 0;
    }

    public final volatile int compareTo(Object obj)
    {
        return compareTo((Cut)obj);
    }

    final void describeAsLowerBound(StringBuilder stringbuilder)
    {
        stringbuilder.append("(-\u221E");
    }

    final void describeAsUpperBound(StringBuilder stringbuilder)
    {
        throw new AssertionError();
    }

    final Comparable endpoint()
    {
        throw new IllegalStateException("range unbounded on this side");
    }

    public final int hashCode()
    {
        return System.identityHashCode(this);
    }

    final boolean isLessThan(Comparable comparable)
    {
        return true;
    }

    public final String toString()
    {
        return "-\u221E";
    }


    private ()
    {
        super(null);
    }
}
