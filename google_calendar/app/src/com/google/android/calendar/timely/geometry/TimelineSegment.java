// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.geometry;


public interface TimelineSegment
{

    public abstract int getEndDay();

    public abstract long getEndMillis();

    public abstract int getEndTime();

    public abstract int getStartDay();

    public abstract long getStartMillis();

    public abstract int getStartTime();

    public abstract boolean isAllDay();

    public abstract boolean spansAtLeastOneDay();
}
