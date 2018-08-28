// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.Constants;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry:
//            HorizontalChipGeometry, GeometryUtils, PositionOnGrid

public final class OverlappingChipGeometry
    implements HorizontalChipGeometry
{

    public static final long DELTA_FP16_THRESHOLD;
    private final HorizontalChipGeometry geometry;
    private final TimeUtils timeUtils;

    public OverlappingChipGeometry(HorizontalChipGeometry horizontalchipgeometry, TimeUtils timeutils)
    {
        geometry = horizontalchipgeometry;
        timeUtils = timeutils;
    }

    public final void layoutChipsHorizontally(List list)
    {
        Iterator iterator;
        geometry.layoutChipsHorizontally(list);
        Collections.sort(list, GeometryUtils.START_FRACTION_COMPARATOR);
        for (int i = 0; i < list.size(); i++)
        {
            com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder builder = (com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(i);
            float f = 0.0F;
            int l = 0;
            while (l < i) 
            {
                com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder builder2 = (com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(l);
                if (GeometryUtils.intersectsTime(builder, builder2))
                {
                    TimeUtils timeutils = timeUtils;
                    float f2;
                    if (GeometryUtils.clampStartToDayFp16(builder, timeutils) - GeometryUtils.clampStartToDayFp16(builder2, timeutils) >= DELTA_FP16_THRESHOLD)
                    {
                        f2 = builder2.getGridTimedPosition().startFraction + 0.07F;
                    } else
                    {
                        f2 = builder2.getGridTimedPosition().endFraction;
                    }
                    f = Math.max(f, f2);
                }
                l++;
            }
            builder.getGridTimedPosition().startFraction = Math.min(builder.getGridTimedPosition().startFraction, f);
        }

        for (int j = list.size() - 1; j >= 0; j--)
        {
            com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder builder1 = (com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(j);
            float f1 = 1.0F;
            for (int i1 = j + 1; i1 < list.size();)
            {
                com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder builder3 = (com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(i1);
                float f3 = f1;
                if (GeometryUtils.intersectsTime(builder1, builder3))
                {
                    TimeUtils timeutils1 = timeUtils;
                    f3 = f1;
                    if (GeometryUtils.clampStartToDayFp16(builder3, timeutils1) - GeometryUtils.clampStartToDayFp16(builder1, timeutils1) < DELTA_FP16_THRESHOLD)
                    {
                        f3 = Math.min(f1, builder3.getGridTimedPosition().startFraction);
                    }
                }
                i1++;
                f1 = f3;
            }

            builder1.getGridTimedPosition().endFraction = Math.max(builder1.getGridTimedPosition().endFraction, f1);
        }

        for (int k = 0; k < list.size(); k++)
        {
            ((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(k)).getGridTimedPosition().z = k;
        }

        iterator = list.iterator();
_L9:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder builder4;
        Iterator iterator1;
        builder4 = (com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)iterator.next();
        iterator1 = list.iterator();
_L6:
        if (!iterator1.hasNext()) goto _L4; else goto _L3
_L3:
        com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder builder5 = (com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)iterator1.next();
        if (builder4.getPosition() == builder5.getPosition() || !GeometryUtils.overlap(builder4, builder5) || builder4.getGridTimedPosition().z <= builder5.getGridTimedPosition().z) goto _L6; else goto _L5
_L5:
        boolean flag = true;
_L7:
        if (!flag)
        {
            builder4.getGridTimedPosition().z = 0;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        flag = false;
        if (true) goto _L7; else goto _L2
_L2:
        return;
        if (true) goto _L9; else goto _L8
_L8:
    }

    static 
    {
        DELTA_FP16_THRESHOLD = (3L * Constants.MIN_CHIP_HEIGHT_FP16) / 2L;
    }
}
