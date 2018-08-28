// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.geometry;


public final class PositionOnGrid
{

    public int bottomMinutes;
    public float endFraction;
    public float startFraction;
    public int topMinutes;
    public int z;

    public PositionOnGrid()
    {
    }

    private PositionOnGrid(int i, int j, float f, float f1, int k)
    {
        topMinutes = i;
        bottomMinutes = j;
        startFraction = f;
        endFraction = f1;
        z = k;
    }

    public PositionOnGrid(PositionOnGrid positionongrid)
    {
        this(positionongrid.topMinutes, positionongrid.bottomMinutes, positionongrid.startFraction, positionongrid.endFraction, positionongrid.z);
    }

    public final boolean overlaps(PositionOnGrid positionongrid)
    {
        boolean flag;
        if (topMinutes < positionongrid.bottomMinutes && bottomMinutes > positionongrid.topMinutes)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return flag && startFraction < positionongrid.endFraction && endFraction > positionongrid.startFraction;
    }
}
