// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.api;

import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.calendar.timeline.chip.ChipViewModel;

public interface AlternateTimelineChipViewModelFactory
{

    public abstract ChipViewModel apply(ViewMode viewmode, TimeRangeEntry timerangeentry, int i);
}
