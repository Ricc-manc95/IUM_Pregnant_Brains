// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry;

import com.google.android.calendar.utils.datatypes.Graph;
import java.util.Arrays;
import java.util.BitSet;

final class GraphBoundsSolver
{

    private final Graph graph;
    private final boolean isBounded[];
    public final float lowerBound[];
    private final float maxLength[];
    private final float maxUpperBound[];
    private final float minLowerBound[];
    private final int pathLength[];
    public final float upperBound[];

    GraphBoundsSolver(Graph graph1)
    {
        graph = graph1;
        isBounded = new boolean[graph1.adjacencyMatrix.length];
        lowerBound = new float[graph1.adjacencyMatrix.length];
        upperBound = new float[graph1.adjacencyMatrix.length];
        Arrays.fill(upperBound, 1.0F);
        pathLength = new int[graph1.adjacencyMatrix.length];
        minLowerBound = new float[graph1.adjacencyMatrix.length];
        maxUpperBound = new float[graph1.adjacencyMatrix.length];
        maxLength = new float[graph1.adjacencyMatrix.length];
        int l = 0;
        do
        {
            if (l >= graph.adjacencyMatrix.length)
            {
                break;
            }
            for (int i = 0; i < graph.adjacencyMatrix.length; i++)
            {
                if (isBounded[i])
                {
                    continue;
                }
                minLowerBound[i] = 0.0F;
                for (int i1 = i - 1; i1 >= 0; i1--)
                {
                    if (graph.adjacencyMatrix[i1].get(i))
                    {
                        minLowerBound[i] = Math.max(minLowerBound[i], minLowerBound[i1]);
                    }
                }

            }

            for (int j = graph.adjacencyMatrix.length - 1; j >= 0; j--)
            {
                if (isBounded[j])
                {
                    continue;
                }
                pathLength[j] = 1;
                maxUpperBound[j] = 1.0F;
                maxLength[j] = maxUpperBound[j] - minLowerBound[j];
                for (int j1 = j + 1; j1 < graph.adjacencyMatrix.length; j1++)
                {
                    if (!graph.adjacencyMatrix[j].get(j1))
                    {
                        continue;
                    }
                    float f = (maxUpperBound[j1] - minLowerBound[j]) / (float)(pathLength[j1] + 1);
                    if (f < maxLength[j])
                    {
                        pathLength[j] = pathLength[j1] + 1;
                        maxUpperBound[j] = Math.min(maxUpperBound[j], maxUpperBound[j1]);
                        maxLength[j] = f;
                    }
                }

            }

            int k = 0;
            int k1;
            int l1;
            for (k1 = -1; k < graph.adjacencyMatrix.length; k1 = l1)
            {
label0:
                {
                    l1 = k1;
                    if (isBounded[k])
                    {
                        break label0;
                    }
                    if (k1 != -1)
                    {
                        l1 = k1;
                        if (maxLength[k] >= maxLength[k1])
                        {
                            break label0;
                        }
                    }
                    l1 = k;
                }
                k++;
            }

            isBounded[k1] = true;
            lowerBound[k1] = minLowerBound[k1];
            upperBound[k1] = lowerBound[k1] + maxLength[k1];
            pathLength[k1] = 0;
            minLowerBound[k1] = upperBound[k1];
            maxUpperBound[k1] = lowerBound[k1];
            l++;
        } while (true);
    }
}
