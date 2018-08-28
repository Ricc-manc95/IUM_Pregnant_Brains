// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Set;

// Referenced classes of package com.google.common.collect:
//            Sets, AbstractMultimap

final class init> extends init>
    implements Set
{

    public final boolean equals(Object obj)
    {
        return Sets.equalsImpl(this, obj);
    }

    public final int hashCode()
    {
        return Sets.hashCodeImpl(this);
    }

    _cls9(AbstractMultimap abstractmultimap)
    {
        super(abstractmultimap);
    }
}
