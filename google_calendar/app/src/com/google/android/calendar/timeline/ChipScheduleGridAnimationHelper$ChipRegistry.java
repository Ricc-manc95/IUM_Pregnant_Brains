// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline;

import android.graphics.Rect;
import com.google.android.calendar.timeline.chip.ChipViewModel;

// Referenced classes of package com.google.android.calendar.timeline:
//            ChipScheduleGridAnimationHelper

public static final class shouldScaleForegroundInGridMode
{

    public final Rect gridCoordinates = new Rect();
    public final ChipViewModel gridViewModel;
    public final Rect scheduleCoordinates = new Rect();
    public final ChipViewModel scheduleViewModel;
    public final boolean shouldScaleForegroundInGridMode;

    public (ChipViewModel chipviewmodel, ChipViewModel chipviewmodel1, boolean flag)
    {
        scheduleViewModel = chipviewmodel;
        gridViewModel = chipviewmodel1;
        shouldScaleForegroundInGridMode = flag;
    }
}
