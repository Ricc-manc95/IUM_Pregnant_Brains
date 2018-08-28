// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry:
//            HorizontalChipGeometry, TopologicalOrderOptimizer, GraphBuilder, GraphBoundsSolver, 
//            PositionOnGrid

public final class GraphChipGeometry
    implements HorizontalChipGeometry
{

    public GraphChipGeometry()
    {
    }

    public final void layoutChipsHorizontally(List list)
    {
        TopologicalOrderOptimizer topologicalorderoptimizer = new TopologicalOrderOptimizer(GraphBuilder.buildGraphFromGridPositions(list, false));
        ArrayList arraylist = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++)
        {
            arraylist.add((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)list.get(topologicalorderoptimizer.orderedNodes[i]));
        }

        list = new GraphBoundsSolver(GraphBuilder.buildGraphFromGridPositions(arraylist, true));
        for (int j = 0; j < arraylist.size(); j++)
        {
            PositionOnGrid positionongrid = ((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)arraylist.get(j)).getGridTimedPosition();
            float f = ((GraphBoundsSolver) (list)).lowerBound[j];
            float f1 = ((GraphBoundsSolver) (list)).upperBound[j];
            positionongrid.startFraction = f;
            positionongrid.endFraction = f1;
            ((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)arraylist.get(j)).getGridTimedPosition().z = 0;
        }

    }
}
