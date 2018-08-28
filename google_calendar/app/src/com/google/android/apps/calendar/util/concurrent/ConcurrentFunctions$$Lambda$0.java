// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Map;

final class arg._cls3
    implements Supplier
{

    private final Supplier arg$1;
    private final Map arg$2;
    private final Object arg$3;

    public final Object get()
    {
        Object obj1 = arg$1;
        Map map = arg$2;
        Object obj = arg$3;
        obj1 = (ListenableFuture)((Supplier) (obj1)).get();
        ((ListenableFuture) (obj1)).addListener(new <init>(map, obj), com.google.common.util.concurrent.NSTANCE);
        if (((ListenableFuture) (obj1)).isDone())
        {
            return obj1;
        } else
        {
            com.google.common.util.concurrent.tingFuture tingfuture = new com.google.common.util.concurrent.tingFuture(((ListenableFuture) (obj1)));
            ((ListenableFuture) (obj1)).addListener(tingfuture, com.google.common.util.concurrent.NSTANCE);
            return tingfuture;
        }
    }

    (Supplier supplier, Map map, Object obj)
    {
        arg$1 = supplier;
        arg$2 = map;
        arg$3 = obj;
    }
}
