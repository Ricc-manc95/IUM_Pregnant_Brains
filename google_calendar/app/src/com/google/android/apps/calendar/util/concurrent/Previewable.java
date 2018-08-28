// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.base.Supplier;
import java.io.Serializable;

public final class Previewable
{

    public final Object preview;
    public final Supplier resultRequest;

    public Previewable(Object obj, Supplier supplier)
    {
        preview = obj;
        obj = supplier;
        if (!(supplier instanceof com.google.common.base.Suppliers.NonSerializableMemoizingSupplier))
        {
            if (supplier instanceof com.google.common.base.Suppliers.MemoizingSupplier)
            {
                obj = supplier;
            } else
            if (supplier instanceof Serializable)
            {
                obj = new com.google.common.base.Suppliers.MemoizingSupplier(supplier);
            } else
            {
                obj = new com.google.common.base.Suppliers.NonSerializableMemoizingSupplier(supplier);
            }
        }
        resultRequest = ((Supplier) (obj));
    }
}
