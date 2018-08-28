// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.AlternateTimelineChipViewModelFactory;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.calendar.timebox.adapter.TimeBoxToTimelineAdapter;
import com.google.android.calendar.timeline.chip.ChipViewModel;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfoFactory;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipViewModelFactory;

final class arg._cls3
    implements AlternateTimelineChipViewModelFactory
{

    private final ChipViewModelFactory arg$1;
    private final TimeBoxToTimelineAdapter arg$2;
    private final ChipFragmentInfoFactory arg$3;

    public final ChipViewModel apply(ViewMode viewmode, TimeRangeEntry timerangeentry, int i)
    {
        ChipViewModelFactory chipviewmodelfactory = arg$1;
        TimeBoxToTimelineAdapter timeboxtotimelineadapter = arg$2;
        Object obj = arg$3;
        timerangeentry = timeboxtotimelineadapter.createTimelineItem(timerangeentry);
        obj = ((ChipFragmentInfoFactory) (obj)).infos[viewmode.ordinal()];
        if (viewmode == ViewMode.MONTH)
        {
            i = 0;
        }
        return chipviewmodelfactory.buildViewModel(timerangeentry, ((com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo) (obj)), i);
    }

    pFragmentInfoFactory(ChipViewModelFactory chipviewmodelfactory, TimeBoxToTimelineAdapter timeboxtotimelineadapter, ChipFragmentInfoFactory chipfragmentinfofactory)
    {
        arg$1 = chipviewmodelfactory;
        arg$2 = timeboxtotimelineadapter;
        arg$3 = chipfragmentinfofactory;
    }
}
