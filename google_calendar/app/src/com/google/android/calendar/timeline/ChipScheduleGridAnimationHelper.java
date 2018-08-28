// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline;

import android.content.res.Resources;
import android.graphics.Rect;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.timeline.chip.ChipScalingRatios;
import com.google.android.calendar.timeline.chip.ChipViewModel;
import java.util.HashMap;
import java.util.Map;

public class ChipScheduleGridAnimationHelper
{
    public static final class ChipRegistry
    {

        public final Rect gridCoordinates = new Rect();
        public final ChipViewModel gridViewModel;
        public final Rect scheduleCoordinates = new Rect();
        public final ChipViewModel scheduleViewModel;
        public final boolean shouldScaleForegroundInGridMode;

        public ChipRegistry(ChipViewModel chipviewmodel, ChipViewModel chipviewmodel1, boolean flag)
        {
            scheduleViewModel = chipviewmodel;
            gridViewModel = chipviewmodel1;
            shouldScaleForegroundInGridMode = flag;
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timeline/ChipScheduleGridAnimationHelper);
    public final Map chips = new HashMap();
    public final ChipScalingRatios scalingRatios;

    public ChipScheduleGridAnimationHelper(Resources resources)
    {
        scalingRatios = new ChipScalingRatios(resources);
    }

}
