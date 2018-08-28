// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.geometry;

import android.view.View;
import com.google.android.calendar.utils.Holder;
import java.util.ArrayList;
import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.timely.gridviews.geometry:
//            HorizontalChipGeometry, GridGeometryUtils, PositionOnGrid

public final class SimpleChipGeometry
    implements HorizontalChipGeometry
{

    private final HorizontalChipGeometry geometry;

    public SimpleChipGeometry(HorizontalChipGeometry horizontalchipgeometry)
    {
        geometry = horizontalchipgeometry;
    }

    public final void layoutChipsHorizontally(ArrayList arraylist)
    {
        arraylist.size();
        JVM INSTR tableswitch 0 3: default 36
    //                   0 46
    //                   1 47
    //                   2 61
    //                   3 90;
           goto _L1 _L2 _L3 _L4 _L5
_L2:
        break MISSING_BLOCK_LABEL_46;
_L1:
        geometry.layoutChipsHorizontally(arraylist);
_L10:
        return;
_L3:
        GridGeometryUtils.setX((View)arraylist.get(0), 0.0F, 1.0F);
        return;
_L4:
        GridGeometryUtils.setX((View)arraylist.get(0), 0.0F, 0.5F);
        GridGeometryUtils.setX((View)arraylist.get(1), 0.5F, 1.0F);
        return;
_L5:
        int ai[];
        int i;
        int l;
        int k1;
        ArrayList arraylist1 = (ArrayList)arraylist;
        int l1 = arraylist1.size();
        i = 0x80000000;
        l = 0x7fffffff;
        for (int i1 = 0; i1 < l1; i1++)
        {
            View view = (View)arraylist1.get(i1);
            l = Math.min(l, ((PositionOnGrid)((Holder)view.getLayoutParams()).get()).bottomMinutes);
            i = Math.max(i, ((PositionOnGrid)((Holder)view.getLayoutParams()).get()).topMinutes);
        }

        ai = new int[3];
        for (int j1 = 0; j1 < 3; j1++)
        {
            ai[j1] = ((PositionOnGrid)((Holder)((View)arraylist.get(j1)).getLayoutParams()).get()).topMinutes;
        }

        Arrays.sort(ai);
        k1 = 0;
_L11:
        if (k1 + 1 >= 3) goto _L7; else goto _L6
_L6:
        if (ai[k1] + 45 <= ai[k1 + 1]) goto _L9; else goto _L8
_L8:
        k1 = 0;
_L12:
        if (k1 == 0 && l <= i)
        {
            GridGeometryUtils.setX((View)arraylist.get(0), 0.0F, 0.5F);
            int j = 1;
            while (j < 3) 
            {
                if (GridGeometryUtils.intersectsTime((View)arraylist.get(0), (View)arraylist.get(j)))
                {
                    GridGeometryUtils.setX((View)arraylist.get(j), 0.5F, 1.0F);
                } else
                {
                    GridGeometryUtils.setX((View)arraylist.get(j), 0.0F, 0.5F);
                }
                j++;
            }
        } else
        {
            int k = 0;
            while (k < 3) 
            {
                GridGeometryUtils.setX((View)arraylist.get(k), (float)k * 0.3333333F, (float)(k + 1) * 0.3333333F);
                k++;
            }
        }
          goto _L10
_L9:
        k1++;
          goto _L11
_L7:
        k1 = 1;
          goto _L12
    }
}
