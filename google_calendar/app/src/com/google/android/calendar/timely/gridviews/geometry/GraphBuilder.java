// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.geometry;

import com.google.android.calendar.utils.datatypes.Graph;
import java.util.ArrayList;
import java.util.BitSet;

// Referenced classes of package com.google.android.calendar.timely.gridviews.geometry:
//            PositionOnGrid

final class GraphBuilder
{

    static Graph buildGraphFromGridPositions(ArrayList arraylist, boolean flag)
    {
        BitSet abitset[] = new BitSet[arraylist.size()];
        for (int i = 0; i < abitset.length; i++)
        {
            abitset[i] = new BitSet(abitset.length);
            int j = 0;
            while (j < i) 
            {
                PositionOnGrid positionongrid = (PositionOnGrid)arraylist.get(j);
                PositionOnGrid positionongrid1 = (PositionOnGrid)arraylist.get(i);
                boolean flag1;
                if (positionongrid.topMinutes < positionongrid1.bottomMinutes && positionongrid.bottomMinutes > positionongrid1.topMinutes)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (!flag1)
                {
                    continue;
                }
                abitset[j].set(i);
                if (!flag)
                {
                    abitset[i].set(j);
                }
                j++;
            }
        }

        return new Graph(abitset);
    }
}
