// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Comparator;

// Referenced classes of package com.google.common.collect:
//            NullsFirstOrdering

public abstract class Ordering
    implements Comparator
{

    protected Ordering()
    {
    }

    public abstract int compare(Object obj, Object obj1);

    public Ordering nullsFirst()
    {
        return new NullsFirstOrdering(this);
    }
}
