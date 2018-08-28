// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.AbstractList;
import java.util.List;

public final class converter extends AbstractList
{

    private final Converter converter;
    private final List fromList;

    public final Object get(int i)
    {
        class Converter
        {

            public abstract Object convert(Object obj);
        }

        return converter.convert(fromList.get(i));
    }

    public final int size()
    {
        return fromList.size();
    }

    public Converter(List list, Converter converter1)
    {
        fromList = list;
        converter = converter1;
    }
}
