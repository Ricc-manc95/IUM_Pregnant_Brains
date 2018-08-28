// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap;

import android.util.SparseArray;
import java.util.ArrayDeque;
import java.util.Queue;

public class ContiguousFIFOAggregator
{

    public final Queue expected = new ArrayDeque();
    public final SparseArray tasks = new SparseArray();

    public ContiguousFIFOAggregator()
    {
    }

    static 
    {
        com/android/bitmap/ContiguousFIFOAggregator.getSimpleName();
    }
}
