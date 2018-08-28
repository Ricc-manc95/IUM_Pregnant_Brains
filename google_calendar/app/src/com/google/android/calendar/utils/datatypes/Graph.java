// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.datatypes;

import java.util.BitSet;

public final class Graph
{

    public final BitSet adjacencyMatrix[];
    private final BitSet edgesMaskRecycle;

    public Graph(BitSet abitset[])
    {
        adjacencyMatrix = abitset;
        edgesMaskRecycle = new BitSet(abitset.length);
    }

    public final boolean getMask(int i, BitSet bitset)
    {
        edgesMaskRecycle.clear();
        edgesMaskRecycle.or(adjacencyMatrix[i]);
        edgesMaskRecycle.and(bitset);
        return !edgesMaskRecycle.isEmpty();
    }
}
