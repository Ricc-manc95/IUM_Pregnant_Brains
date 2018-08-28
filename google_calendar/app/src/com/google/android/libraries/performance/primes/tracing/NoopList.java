// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.tracing;

import java.util.AbstractList;

final class NoopList extends AbstractList
{

    public static final NoopList NOOP_LIST = new NoopList();

    NoopList()
    {
    }

    public final boolean add(Object obj)
    {
        return true;
    }

    public final Object get(int i)
    {
        return null;
    }

    public final int size()
    {
        return 0;
    }

}
