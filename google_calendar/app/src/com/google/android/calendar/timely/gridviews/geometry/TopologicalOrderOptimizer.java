// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.geometry;

import com.google.android.calendar.utils.datatypes.Graph;
import java.util.BitSet;

final class TopologicalOrderOptimizer
{

    private final int degree[];
    private final Graph graph;
    private final boolean isUsed[];
    public final int orderedNodes[];

    TopologicalOrderOptimizer(Graph graph1)
    {
        graph = graph1;
        degree = new int[graph1.adjacencyMatrix.length];
        isUsed = new boolean[graph1.adjacencyMatrix.length];
        orderedNodes = new int[graph1.adjacencyMatrix.length];
        for (int i = 0; i < graph.adjacencyMatrix.length; i++)
        {
            for (int l = i + 1; l < graph.adjacencyMatrix.length; l++)
            {
                if (graph.adjacencyMatrix[i].get(l))
                {
                    graph1 = degree;
                    graph1[i] = graph1[i] + 1;
                    graph1 = degree;
                    graph1[l] = graph1[l] + 1;
                }
            }

        }

        graph1 = new BitSet(graph.adjacencyMatrix.length);
        int i1 = 0;
        do
        {
            if (i1 >= graph.adjacencyMatrix.length)
            {
                break;
            }
            int j = 0;
            int j1;
            int k1;
            for (j1 = -1; j < graph.adjacencyMatrix.length; j1 = k1)
            {
label0:
                {
                    k1 = j1;
                    if (isUsed[j])
                    {
                        break label0;
                    }
                    k1 = j1;
                    if (graph.getMask(j, graph1))
                    {
                        break label0;
                    }
                    if (j1 != -1)
                    {
                        k1 = j1;
                        if (degree[j1] >= degree[j])
                        {
                            break label0;
                        }
                    }
                    k1 = j;
                }
                j++;
            }

            if (j1 != -1)
            {
                orderedNodes[i1] = j1;
                isUsed[j1] = true;
                for (int k = 0; k < graph.adjacencyMatrix.length; k++)
                {
                    if (j1 != k && graph.adjacencyMatrix[j1].get(k))
                    {
                        int ai[] = degree;
                        ai[k] = ai[k] - 1;
                    }
                }

                graph1.set(j1);
                i1++;
            } else
            {
                graph1.clear();
            }
        } while (true);
    }
}
