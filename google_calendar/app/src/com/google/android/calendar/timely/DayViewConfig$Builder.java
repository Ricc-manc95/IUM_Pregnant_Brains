// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            ImmutableDayViewConfig

public final class 
{

    public boolean canDrawBackgroundImage;
    public Boolean inGridMode;
    public boolean isChipClickable;
    public boolean neverDrawNowLine;
    public boolean shouldDrawDayHeader;
    public boolean shouldDrawMonthInDayHeader;
    public boolean shouldDrawNoEventsView;
    public boolean shouldDrawYearHeader;
    public boolean supportsSwipe;

    public final ImmutableDayViewConfig build()
    {
        if (inGridMode == null)
        {
            throw new IllegalStateException("gridMode or listMode must be called before build()!");
        } else
        {
            return new ImmutableDayViewConfig(inGridMode.booleanValue(), false, shouldDrawDayHeader, shouldDrawYearHeader, neverDrawNowLine, shouldDrawNoEventsView, shouldDrawMonthInDayHeader, canDrawBackgroundImage, isChipClickable, supportsSwipe, false);
        }
    }

    public ()
    {
    }
}
