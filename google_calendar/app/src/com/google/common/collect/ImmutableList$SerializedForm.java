// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.io.Serializable;

// Referenced classes of package com.google.common.collect:
//            ImmutableList

final class elements
    implements Serializable
{

    public static final long serialVersionUID = 0L;
    private final Object elements[];

    final Object readResolve()
    {
        return ImmutableList.copyOf(elements);
    }

    (Object aobj[])
    {
        elements = aobj;
    }
}
