// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import com.google.android.calendar.timely.SuggestionRow;
import com.google.android.calendar.timely.TimelineSuggestion;
import com.google.android.calendar.timely.findatime.ui.DurationTimeframe;

public interface e
{

    public abstract void onCancelled();

    public abstract void onFiltersOpen(DurationTimeframe durationtimeframe);

    public abstract void onQuery(DurationTimeframe durationtimeframe);

    public abstract void onShowMore();

    public abstract void onTimeSlotMoreSelected(TimelineSuggestion timelinesuggestion);

    public abstract void onTimeSlotSelected(SuggestionRow suggestionrow);
}
