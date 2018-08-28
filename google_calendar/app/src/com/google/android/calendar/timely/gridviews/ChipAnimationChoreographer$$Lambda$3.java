// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import com.google.android.calendar.timely.gridviews.geometry.PositionOnGrid;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            ChipAnimationChoreographer

final class irProcessor
    implements irProcessor
{

    public static final irProcessor $instance = new <init>();

    public final void process(PositionOnGrid positionongrid, PositionOnGrid positionongrid1)
    {
        ChipAnimationChoreographer.bridge$lambda$1$ChipAnimationChoreographer(positionongrid, positionongrid1);
    }


    private irProcessor()
    {
    }
}
