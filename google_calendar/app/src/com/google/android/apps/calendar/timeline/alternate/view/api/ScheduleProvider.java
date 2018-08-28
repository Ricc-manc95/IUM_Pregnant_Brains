// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.api;

import com.google.common.util.concurrent.ListenableFuture;

public interface ScheduleProvider
{

    public abstract int getBackgroundColor(int i);

    public abstract int getItemHeight(Object obj, int i);

    public abstract String getMonthText(long l);

    public abstract String getNothingPlannedText();

    public abstract String getWeekText(int ai[], Integer integer);

    public abstract ListenableFuture loadBannerBitmap(int i);

    public abstract boolean shouldShowMonthImages();
}
