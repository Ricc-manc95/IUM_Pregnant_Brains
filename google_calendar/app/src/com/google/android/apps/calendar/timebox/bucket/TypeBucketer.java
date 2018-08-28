// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.bucket;

import android.util.SparseArray;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;

abstract class TypeBucketer
{

    public final SparseArray items = new SparseArray();

    TypeBucketer()
    {
    }

    abstract void addToBucket(Object obj, TimeRangeEntry timerangeentry);

    abstract TimeRangeEntry finalizeBucket(Object obj);

    abstract int hashEntry(TimeRangeEntry timerangeentry);

    abstract Object newBucket(TimeRangeEntry timerangeentry);
}
