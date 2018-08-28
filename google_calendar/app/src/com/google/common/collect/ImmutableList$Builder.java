// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Iterator;

public final class rayBasedBuilder extends rayBasedBuilder
{

    public final volatile rayBasedBuilder add(Object obj)
    {
        return (rayBasedBuilder)add(obj);
    }

    public final ilder add(Object obj)
    {
        super.add(obj);
        return this;
    }

    public final ilder addAll(Iterable iterable)
    {
        super.addAll(iterable);
        return this;
    }

    public final ilder addAll(Iterator iterator)
    {
        super.addAll(iterator);
        return this;
    }

    public ilder()
    {
        this(4);
    }

    <init>(int i)
    {
        super(i);
    }
}
