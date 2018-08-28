// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry;


public final class PositionOnGrid
{

    public float endFraction;
    public float startFraction;
    public int z;

    public PositionOnGrid()
    {
    }

    public PositionOnGrid(float f, float f1, int i)
    {
        startFraction = 0.0F;
        endFraction = 1.0F;
        z = 0;
    }
}
