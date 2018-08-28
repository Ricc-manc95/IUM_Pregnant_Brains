// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth.model;


final class val.rangedData
    implements com.google.android.calendar.task.eryHandler._cls1
{

    private final RangedData val$rangedData;
    private final EventResults val$taskResults;

    public final int getEndDay()
    {
        return val$rangedData.endDay;
    }

    public final int getStartDay()
    {
        return val$rangedData.startDay;
    }

    public final com.google.android.calendar.timely.yHandler._cls1 getStorage()
    {
        return val$taskResults;
    }

    RangedData()
    {
        val$taskResults = eventresults;
        val$rangedData = rangeddata;
        super();
    }
}
