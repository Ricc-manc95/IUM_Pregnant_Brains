// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timebox.adapter;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;

final class items
{

    public final SparseArray birthdays = new SparseArray();
    public final com.google.android.calendar.timely.Bucketer holidayFilter = new com.google.android.calendar.timely.Bucketer();
    public final List items;

    (int i)
    {
        items = new ArrayList(i);
    }
}
