// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline;

import com.google.android.calendar.timeline.chip.ChipViewModel;
import com.google.android.calendar.timeline.chip.GhostChipUtils;

public final class GhostChipModification
    implements com.google.android.calendar.timely.TimelineItemModifications.TimelineItemModification
{

    public GhostChipModification()
    {
    }

    public final ChipViewModel apply(ChipViewModel chipviewmodel)
    {
        return GhostChipUtils.toGhostChip(chipviewmodel, true);
    }
}
