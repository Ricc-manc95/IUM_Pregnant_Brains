// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            AgendaScrollCallback

public interface DayViewConfig
{

    public abstract boolean canDrawBackgroundImage();

    public abstract AgendaScrollCallback getAgendaScrollCallback();

    public abstract boolean inGridMode();

    public abstract boolean inListView();

    public abstract boolean isChipClickable();

    public abstract boolean shouldDrawDayHeader();

    public abstract boolean shouldDrawExtraHeaders();

    public abstract boolean shouldDrawMonthInDayHeader();

    public abstract boolean shouldDrawNoEventsView();

    public abstract boolean shouldDrawYearHeader();

    public abstract boolean shouldNeverDrawMonthHeader();

    public abstract boolean shouldNeverDrawNowLine();

    public abstract boolean supportsSwipe();
}
