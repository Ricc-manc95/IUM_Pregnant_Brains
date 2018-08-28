// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.gridviews.geometry.PositionOnGrid;

final class finalPosition
{

    public final Chip chip;
    public final PositionOnGrid finalPosition;
    public final PositionOnGrid nonOverlappingPosition;

    (Chip chip1, PositionOnGrid positionongrid, PositionOnGrid positionongrid1)
    {
        chip = chip1;
        nonOverlappingPosition = positionongrid;
        finalPosition = positionongrid1;
    }
}
