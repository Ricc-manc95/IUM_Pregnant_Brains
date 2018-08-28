// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            FluentIterable

public final class nit> extends FluentIterable
{

    private final Iterable val$iterable;

    public final Iterator iterator()
    {
        return val$iterable.iterator();
    }

    public (Iterable iterable2)
    {
        val$iterable = iterable2;
        super(final_iterable1);
    }
}
