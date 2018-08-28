// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.res.Resources;

final class TimelyDayViewResources
{

    public final int chipLayoutStartX;
    public final int chipVerticalSpacing;
    public final int dayHeaderBottomMargin;
    public final int dayHeaderHeight;
    public final int dayHeaderWidth;
    public final int defaultMargin;
    public final int gridChipsAllDayTopMargin;
    public final int gridHourStartMargin;
    public final int gridHourTextColor;
    public final int gridHoursMaskHeight;
    public final int gridHoursTextSize;
    public final int gridLineColor;
    public final int horizontalMargin;
    public final int monthHeaderBottomMargin;
    public final int monthHeaderHeight;
    public final int monthHeaderLeftMargin;
    public final int monthHeaderTextSize;
    public final int monthHeaderTopMargin;
    private final int nowLineSpacing;
    public final int weekHeaderBackgroundColor;
    public final int weekHeaderBottomMargin;
    public final int weekHeaderHeight;
    public final int weekHeaderLeftMargin;
    public final int weekHeaderTextColor;
    public final int weekHeaderTextSize;
    public final int weekHeaderTextStrokeWidth;

    TimelyDayViewResources(Resources resources, boolean flag)
    {
        int i;
        int j;
        if (flag)
        {
            i = 0x7f0e03a3;
        } else
        {
            i = 0x7f0e03a4;
        }
        defaultMargin = resources.getDimensionPixelSize(0x7f0e00de);
        horizontalMargin = resources.getDimensionPixelSize(0x7f0e00df);
        dayHeaderWidth = resources.getDimensionPixelSize(0x7f0e03b3);
        dayHeaderHeight = resources.getDimensionPixelSize(0x7f0e03b0);
        dayHeaderBottomMargin = resources.getDimensionPixelSize(0x7f0e00d6);
        weekHeaderTextSize = resources.getDimensionPixelSize(0x7f0e03ad);
        weekHeaderTextStrokeWidth = resources.getDimensionPixelOffset(0x7f0e03e4);
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            j = resources.getColor(0x7f0d0311, null);
        } else
        {
            j = resources.getColor(0x7f0d0311);
        }
        weekHeaderBackgroundColor = j;
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            j = resources.getColor(0x7f0d0312, null);
        } else
        {
            j = resources.getColor(0x7f0d0312);
        }
        weekHeaderTextColor = j;
        weekHeaderHeight = resources.getDimensionPixelOffset(0x7f0e03aa);
        weekHeaderBottomMargin = resources.getDimensionPixelOffset(0x7f0e03a9);
        monthHeaderTextSize = resources.getDimensionPixelSize(0x7f0e03a6);
        monthHeaderTopMargin = resources.getDimensionPixelSize(0x7f0e03a7);
        monthHeaderBottomMargin = resources.getDimensionPixelSize(0x7f0e03a2);
        monthHeaderHeight = resources.getDimensionPixelSize(i);
        gridChipsAllDayTopMargin = resources.getDimensionPixelSize(0x7f0e0309);
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            i = resources.getColor(0x7f0d011a, null);
        } else
        {
            i = resources.getColor(0x7f0d011a);
        }
        gridLineColor = i;
        gridHourStartMargin = resources.getDimensionPixelOffset(0x7f0e03b7);
        gridHoursMaskHeight = resources.getDimensionPixelOffset(0x7f0e03b8);
        gridHoursTextSize = resources.getDimensionPixelSize(0x7f0e0259);
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            i = resources.getColor(0x7f0d030b, null);
        } else
        {
            i = resources.getColor(0x7f0d030b);
        }
        gridHourTextColor = i;
        nowLineSpacing = resources.getDimensionPixelSize(0x7f0e02f7);
        chipVerticalSpacing = resources.getDimensionPixelSize(0x7f0e00a9);
        chipLayoutStartX = dayHeaderWidth + resources.getDimensionPixelOffset(0x7f0e02f4) + nowLineSpacing;
        i = resources.getDimensionPixelSize(0x7f0e0093);
        i = chipLayoutStartX + i;
        if (flag)
        {
            j = resources.getDimensionPixelSize(0x7f0e03a5);
        } else
        {
            j = i;
        }
        monthHeaderLeftMargin = j;
        if (flag)
        {
            i = resources.getDimensionPixelOffset(0x7f0e03ab);
        }
        weekHeaderLeftMargin = i;
    }
}
