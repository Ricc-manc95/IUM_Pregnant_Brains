// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


// Referenced classes of package com.google.common.collect:
//            ImmutableBiMap

final class nit> extends nit>
{

    public static final long serialVersionUID = 0L;

    final Object readResolve()
    {
        return eateMap(new eateMap());
    }

    (ImmutableBiMap immutablebimap)
    {
        super(immutablebimap);
    }
}
