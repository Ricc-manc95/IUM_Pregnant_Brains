// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry:
//            HorizontalChipGeometry, PositionOnGrid, GeometryUtils, OverlappingChipGeometry

public final class SimpleChipGeometry
    implements HorizontalChipGeometry
{

    private final HorizontalChipGeometry geometry;
    private final TimeUtils timeUtils;

    public SimpleChipGeometry(HorizontalChipGeometry horizontalchipgeometry, TimeUtils timeutils)
    {
        geometry = horizontalchipgeometry;
        timeUtils = timeutils;
    }

    public final void layoutChipsHorizontally(List list)
    {
        list.size();
        JVM INSTR tableswitch 0 3: default 36
    //                   0 46
    //                   1 47
    //                   2 72
    //                   3 126;
           goto _L1 _L2 _L3 _L4 _L5
_L2:
        break MISSING_BLOCK_LABEL_46;
_L1:
        geometry.layoutChipsHorizontally(list);
_L10:
        return;
_L3:
        list = ((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(0)).getGridTimedPosition();
        list.startFraction = 0.0F;
        list.endFraction = 1.0F;
        return;
_L4:
        PositionOnGrid positionongrid = ((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(0)).getGridTimedPosition();
        positionongrid.startFraction = 0.0F;
        positionongrid.endFraction = 0.5F;
        list = ((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(1)).getGridTimedPosition();
        list.startFraction = 0.5F;
        list.endFraction = 1.0F;
        return;
_L5:
        long al[];
        int j;
        long l;
        long l1;
        Iterator iterator = list.iterator();
        l1 = 0x7fffffffffffffffL;
        com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder builder;
        for (l = 0x8000000000000000L; iterator.hasNext(); l = Math.max(l, builder.getDisplayStartFp16()))
        {
            builder = (com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)iterator.next();
            l1 = Math.min(l1, builder.getDisplayEndFp16());
        }

        al = new long[3];
        for (int i = 0; i < 3; i++)
        {
            al[i] = GeometryUtils.clampStartToDayFp16((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(i), timeUtils);
        }

        Arrays.sort(al);
        j = 0;
_L11:
        if (j + 1 >= 3) goto _L7; else goto _L6
_L6:
        if (al[j] + OverlappingChipGeometry.DELTA_FP16_THRESHOLD <= al[j + 1]) goto _L9; else goto _L8
_L8:
        j = 0;
_L12:
        if (j == 0 && l1 <= l)
        {
            PositionOnGrid positionongrid1 = ((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(0)).getGridTimedPosition();
            positionongrid1.startFraction = 0.0F;
            positionongrid1.endFraction = 0.5F;
            j = 1;
            while (j < 3) 
            {
                if (GeometryUtils.intersectsTime((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(0), (com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(j)))
                {
                    PositionOnGrid positionongrid2 = ((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(j)).getGridTimedPosition();
                    positionongrid2.startFraction = 0.5F;
                    positionongrid2.endFraction = 1.0F;
                } else
                {
                    PositionOnGrid positionongrid3 = ((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(j)).getGridTimedPosition();
                    positionongrid3.startFraction = 0.0F;
                    positionongrid3.endFraction = 0.5F;
                }
                j++;
            }
        } else
        {
            int k = 0;
            while (k < 3) 
            {
                PositionOnGrid positionongrid4 = ((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(k)).getGridTimedPosition();
                float f = k;
                float f1 = k + 1;
                positionongrid4.startFraction = f * 0.3333333F;
                positionongrid4.endFraction = f1 * 0.3333333F;
                k++;
            }
        }
          goto _L10
_L9:
        j++;
          goto _L11
_L7:
        j = 1;
          goto _L12
    }
}
