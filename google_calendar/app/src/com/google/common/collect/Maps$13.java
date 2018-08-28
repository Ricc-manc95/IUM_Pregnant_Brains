// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Function;

final class val.transformer
    implements Function
{

    private final ryTransformer val$transformer;

    public final Object apply(Object obj)
    {
        obj = (java.util.y)obj;
        ryTransformer rytransformer = val$transformer;
        if (rytransformer == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            return new <init>(((java.util.y) (obj)), rytransformer);
        }
    }

    ryTransformer()
    {
        val$transformer = rytransformer;
        super();
    }
}
