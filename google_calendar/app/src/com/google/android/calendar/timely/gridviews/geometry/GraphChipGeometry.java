// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.geometry;

import android.view.View;
import java.util.ArrayList;
import java.util.RandomAccess;

// Referenced classes of package com.google.android.calendar.timely.gridviews.geometry:
//            HorizontalChipGeometry, TopologicalOrderOptimizer, GraphBuilder, GraphBoundsSolver, 
//            PositionOnGrid

public final class GraphChipGeometry
    implements HorizontalChipGeometry
{

    public GraphChipGeometry()
    {
    }

    public final void layoutChipsHorizontally(ArrayList arraylist)
    {
        boolean flag = false;
        Object obj = TopologicalOrderOptimizer..Lambda._cls0.$instance;
        TopologicalOrderOptimizer topologicalorderoptimizer;
        if (arraylist instanceof RandomAccess)
        {
            obj = new com.google.common.collect.Lists.TransformingRandomAccessList(arraylist, ((Function) (obj)));
        } else
        {
            obj = new com.google.common.collect.Lists.TransformingSequentialList(arraylist, ((Function) (obj)));
        }
        topologicalorderoptimizer = new TopologicalOrderOptimizer(GraphBuilder.buildGraphFromGridPositions(new ArrayList(((java.util.Collection) (obj))), false));
        obj = new ArrayList(arraylist.size());
        for (int i = 0; i < arraylist.size(); i++)
        {
            ((ArrayList) (obj)).add((View)arraylist.get(topologicalorderoptimizer.orderedNodes[i]));
        }

        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj1)
            {
                return (PositionOnGrid)((Holder)((View)(View)obj1).getLayoutParams()).get();
            }


            private .Lambda._cls0()
            {
            }
        }

        arraylist = .Lambda._cls0..instance;
        if (obj instanceof RandomAccess)
        {
            arraylist = new com.google.common.collect.Lists.TransformingRandomAccessList(((java.util.List) (obj)), arraylist);
        } else
        {
            arraylist = new com.google.common.collect.Lists.TransformingSequentialList(((java.util.List) (obj)), arraylist);
        }
        arraylist = new ArrayList(arraylist);
        obj = new GraphBoundsSolver(GraphBuilder.buildGraphFromGridPositions(arraylist, true));
        for (int j = ((flag) ? 1 : 0); j < arraylist.size(); j++)
        {
            PositionOnGrid positionongrid = (PositionOnGrid)arraylist.get(j);
            float f = ((GraphBoundsSolver) (obj)).lowerBound[j];
            float f1 = ((GraphBoundsSolver) (obj)).upperBound[j];
            positionongrid.startFraction = f;
            positionongrid.endFraction = f1;
        }

    }
}
