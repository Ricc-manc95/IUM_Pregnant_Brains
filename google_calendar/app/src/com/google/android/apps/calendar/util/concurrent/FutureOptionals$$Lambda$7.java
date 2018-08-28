// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.base.Function;

final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        if (obj == null)
        {
            return com.google.common.util.concurrent.uccessfulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.uccessfulFuture(obj);
        }
    }


    private ()
    {
    }
}
