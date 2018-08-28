// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.api;


public final class ViewMode extends Enum
{

    private static final ViewMode $VALUES[];
    public static final ViewMode EMPTY;
    public static final ViewMode MONTH;
    public static final ViewMode MULTI_DAY_GRID;
    public static final ViewMode ONE_DAY_GRID;
    public static final ViewMode SCHEDULE;

    private ViewMode(String s, int i)
    {
        super(s, i);
    }

    public static ViewMode[] values()
    {
        return (ViewMode[])$VALUES.clone();
    }

    static 
    {
        EMPTY = new ViewMode("EMPTY", 0);
        SCHEDULE = new ViewMode("SCHEDULE", 1);
        ONE_DAY_GRID = new ViewMode("ONE_DAY_GRID", 2);
        MULTI_DAY_GRID = new ViewMode("MULTI_DAY_GRID", 3);
        MONTH = new ViewMode("MONTH", 4);
        $VALUES = (new ViewMode[] {
            EMPTY, SCHEDULE, ONE_DAY_GRID, MULTI_DAY_GRID, MONTH
        });
    }
}
