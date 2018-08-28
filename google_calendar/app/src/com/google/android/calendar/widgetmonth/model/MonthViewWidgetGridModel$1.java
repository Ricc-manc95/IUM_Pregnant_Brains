// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth.model;

import com.google.android.calendar.timely.geometry.PartitionItem;
import java.util.Comparator;

final class 
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        obj = (PartitionItem)obj;
        obj1 = (PartitionItem)obj1;
        int i = ((PartitionItem) (obj)).getPartition();
        int j = ((PartitionItem) (obj1)).getPartition();
        if (i < j)
        {
            return -1;
        }
        return i <= j ? 0 : 1;
    }

    ()
    {
    }
}
