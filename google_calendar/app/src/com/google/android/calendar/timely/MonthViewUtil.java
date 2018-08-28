// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.util.SparseArray;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineItem, MonthViewPartitionItem

public final class MonthViewUtil
{

    public static void computePartitionItemsInWeek(SparseArray sparsearray, int i, List list)
    {
        boolean flag;
        for (int j = 0; j < 7; j++)
        {
            int l = i + j;
            List list1 = (List)sparsearray.get(l);
            if (list1 == null)
            {
                continue;
            }
            int k;
            if (j != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            for (k = 0; k < list1.size(); k++)
            {
                TimelineItem timelineitem = (TimelineItem)list1.get(k);
                if ((timelineitem.getStartDay() >= l || !flag) && (timelineitem.getStartDay() >= l || timelineitem.spansAtLeastOneDay()))
                {
                    list.add(new MonthViewPartitionItem(timelineitem));
                }
            }

        }

    }
}
