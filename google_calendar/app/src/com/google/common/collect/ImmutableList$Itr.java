// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


// Referenced classes of package com.google.common.collect:
//            AbstractIndexedListIterator, ImmutableList

final class list extends AbstractIndexedListIterator
{

    private final ImmutableList list;

    protected final Object get(int i)
    {
        return list.get(i);
    }

    tIterator(ImmutableList immutablelist, int i)
    {
        super(immutablelist.size(), i);
        list = immutablelist;
    }
}
