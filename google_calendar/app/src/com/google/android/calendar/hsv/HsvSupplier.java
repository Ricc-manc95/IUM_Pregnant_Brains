// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.hsv;

import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;

public final class HsvSupplier
    implements Supplier
{

    private static final HsvSupplier INSTANCE = new HsvSupplier();

    public HsvSupplier()
    {
    }

    public static Optional get()
    {
        return Absent.INSTANCE;
    }

    public final Object get()
    {
        return Absent.INSTANCE;
    }

}
