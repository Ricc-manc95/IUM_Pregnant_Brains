// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.base.Supplier;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;

public final class arg._cls1
    implements Supplier
{

    private final Supplier arg$1;

    public final Object get()
    {
        return AbstractTransformFuture.create((ListenableFuture)arg$1.get(), ance, com.google.common.util.concurrent.STANCE);
    }

    public (Supplier supplier)
    {
        arg$1 = supplier;
    }
}
