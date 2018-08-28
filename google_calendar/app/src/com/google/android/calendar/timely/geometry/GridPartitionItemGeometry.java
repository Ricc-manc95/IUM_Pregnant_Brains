// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.geometry;

import android.content.Context;
import android.content.res.Resources;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.timely.geometry:
//            PartitionItem

public final class GridPartitionItemGeometry
{

    public int cellWidth;
    public final int chipHorizontalSpacing;
    private int gridStartPadding;

    public GridPartitionItemGeometry(Context context)
    {
        gridStartPadding = 0;
        cellWidth = 0;
        chipHorizontalSpacing = context.getResources().getDimensionPixelSize(0x7f0e0094);
    }

    public static void doComputePositions(Iterable iterable, long l, boolean flag, boolean flag1, boolean flag2)
    {
        Object obj;
        ArrayList arraylist;
        int i;
        long l2;
        obj = new ArrayList();
        arraylist = new ArrayList();
        l2 = l;
        if (l < 0L)
        {
            l2 = 0L;
        }
        iterable = iterable.iterator();
        l = 0L;
        i = 0;
_L5:
        PartitionItem partitionitem;
        int k;
        long l1;
        if (!iterable.hasNext())
        {
            break MISSING_BLOCK_LABEL_468;
        }
        partitionitem = (PartitionItem)iterable.next();
        if (!flag1 && partitionitem.spansAtLeastOneDay() != flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!flag1 && !flag)
        {
            Iterator iterator = ((ArrayList) (obj)).iterator();
            long l3 = partitionitem.getStartMillis();
            do
            {
                l1 = l;
                if (!iterator.hasNext())
                {
                    break;
                }
                PartitionItem partitionitem1 = (PartitionItem)iterator.next();
                l1 = partitionitem1.getStartMillis();
                if (l1 + Math.max(partitionitem1.getEndMillis() - l1, l2) <= l3)
                {
                    l &= ~(1L << partitionitem1.getPartition());
                    iterator.remove();
                }
            } while (true);
        } else
        {
            Iterator iterator1 = ((ArrayList) (obj)).iterator();
            do
            {
                l1 = l;
                if (!iterator1.hasNext())
                {
                    break;
                }
                PartitionItem partitionitem2 = (PartitionItem)iterator1.next();
                int j;
                if (partitionitem2.spansAtLeastOneDay())
                {
                    j = partitionitem2.getEndDay();
                } else
                {
                    j = partitionitem2.getStartDay();
                }
                if (j < partitionitem.getStartDay())
                {
                    l &= ~(1L << partitionitem2.getPartition());
                    iterator1.remove();
                }
            } while (true);
        }
        k = i;
        if (((ArrayList) (obj)).isEmpty())
        {
            ArrayList arraylist1 = (ArrayList)arraylist;
            int i1 = arraylist1.size();
            for (k = 0; k < i1;)
            {
                Object obj1 = arraylist1.get(k);
                k++;
                ((PartitionItem)obj1).setMaxPartitions(i);
            }

            k = 0;
            l1 = 0L;
            arraylist.clear();
        }
        i = 0;
_L3:
        if (i >= 64)
        {
            break MISSING_BLOCK_LABEL_461;
        }
        if ((1L << i & l1) != 0L) goto _L2; else goto _L1
_L1:
        int j1 = i;
        if (i == 64)
        {
            j1 = 63;
        }
        l = l1 | 1L << j1;
        partitionitem.setPartition(j1);
        ((ArrayList) (obj)).add(partitionitem);
        arraylist.add(partitionitem);
        i = ((ArrayList) (obj)).size();
        if (k >= i)
        {
            i = k;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        i++;
          goto _L3
        i = 64;
          goto _L1
        iterable = (ArrayList)arraylist;
        j1 = iterable.size();
        for (k = 0; k < j1;)
        {
            obj = iterable.get(k);
            k++;
            ((PartitionItem)obj).setMaxPartitions(i);
        }

        return;
        if (true) goto _L5; else goto _L4
_L4:
    }
}
