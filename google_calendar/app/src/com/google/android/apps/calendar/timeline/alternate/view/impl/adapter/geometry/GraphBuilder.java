// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry;

import com.google.android.calendar.utils.datatypes.Graph;
import java.util.BitSet;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry:
//            GeometryUtils

final class GraphBuilder
{

    static Graph buildGraphFromGridPositions(List list, boolean flag)
    {
        BitSet abitset[] = new BitSet[list.size()];
        for (int i = 0; i < abitset.length; i++)
        {
            abitset[i] = new BitSet(abitset.length);
            for (int j = 0; j < i; j++)
            {
                if (!GeometryUtils.intersectsTime((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(j), (com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(i)))
                {
                    continue;
                }
                abitset[j].set(i);
                if (!flag)
                {
                    abitset[i].set(j);
                }
            }

        }

        return new Graph(abitset);
    }
}
