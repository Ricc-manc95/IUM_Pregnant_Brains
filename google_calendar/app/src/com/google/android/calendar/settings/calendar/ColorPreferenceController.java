// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.calendar;

import android.support.v7.widget.RecyclerView;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.base.Supplier;

final class ColorPreferenceController
{

    public final Consumer colorSelectedListener;
    public final Supplier layoutInflaterSupplier;
    public final RecyclerView recyclerView;

    ColorPreferenceController(RecyclerView recyclerview, Supplier supplier, Consumer consumer)
    {
        recyclerView = recyclerview;
        layoutInflaterSupplier = supplier;
        colorSelectedListener = consumer;
    }
}
