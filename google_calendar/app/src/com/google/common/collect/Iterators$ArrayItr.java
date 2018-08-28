// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


// Referenced classes of package com.google.common.collect:
//            AbstractIndexedListIterator, UnmodifiableListIterator

final class array extends AbstractIndexedListIterator
{

    public static final UnmodifiableListIterator EMPTY = new <init>(new Object[0], 0, 0, 0);
    private final Object array[];
    private final int offset = 0;

    protected final Object get(int i)
    {
        return array[offset + i];
    }


    private rator(Object aobj[], int i, int j, int k)
    {
        super(0, 0);
        array = aobj;
    }
}
