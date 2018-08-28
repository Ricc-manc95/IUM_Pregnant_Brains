// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


// Referenced classes of package com.google.common.collect:
//            ImmutableListMultimap, RegularImmutableMap

final class EmptyImmutableListMultimap extends ImmutableListMultimap
{

    public static final EmptyImmutableListMultimap INSTANCE = new EmptyImmutableListMultimap();
    public static final long serialVersionUID = 0L;

    private EmptyImmutableListMultimap()
    {
        super(RegularImmutableMap.EMPTY, 0);
    }

    private final Object readResolve()
    {
        return INSTANCE;
    }

}
