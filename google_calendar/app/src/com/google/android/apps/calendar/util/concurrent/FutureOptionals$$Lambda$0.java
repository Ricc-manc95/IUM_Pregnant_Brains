// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;

public final class arg._cls1
    implements Function
{

    private final Supplier arg$1;

    public final Object apply(Object obj)
    {
        Supplier supplier = arg$1;
        return ((Optional)obj).or(supplier);
    }

    public (Supplier supplier)
    {
        arg$1 = supplier;
    }
}
