// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth.model;

import android.util.SparseArray;
import com.google.android.calendar.timely.MonthViewUtil;
import com.google.android.calendar.timely.geometry.GridPartitionItemGeometry;
import com.google.android.calendar.widgetmonth.MonthViewWidgetUtils;
import java.util.ArrayList;
import java.util.Collections;

public final class MonthViewWidgetGridModel
{

    private final GridPartitionItemGeometry chipGeometry;
    public final int endJulianDay;
    public final SparseArray itemsForJulianDay = new SparseArray();
    public final int startJulianDay;

    public MonthViewWidgetGridModel(int i, int j, SparseArray sparsearray, GridPartitionItemGeometry gridpartitionitemgeometry)
    {
        startJulianDay = i;
        endJulianDay = j;
        chipGeometry = gridpartitionitemgeometry;
        for (i = startJulianDay; i < endJulianDay; i += 7)
        {
            gridpartitionitemgeometry = new ArrayList();
            MonthViewUtil.computePartitionItemsInWeek(sparsearray, i, gridpartitionitemgeometry);
            GridPartitionItemGeometry.doComputePositions(gridpartitionitemgeometry, 0L, true, true, false);
            Collections.sort(gridpartitionitemgeometry, new _cls1());
            MonthViewWidgetUtils.addPartitionItemsToJulianDays(gridpartitionitemgeometry, i, (i + 7) - 1, itemsForJulianDay);
        }

    }

    private class _cls1
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

        _cls1()
        {
        }
    }

}
