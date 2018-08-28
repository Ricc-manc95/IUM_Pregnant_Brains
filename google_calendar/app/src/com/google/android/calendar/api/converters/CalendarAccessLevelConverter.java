// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.converters;

import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;

public final class CalendarAccessLevelConverter
{

    public static CalendarAccessLevel providerToApi(Integer integer)
    {
        if (integer.intValue() >= 700)
        {
            return CalendarAccessLevel.OWNER;
        }
        if (integer.intValue() >= 500)
        {
            return CalendarAccessLevel.WRITER;
        }
        if (integer.intValue() >= 200)
        {
            return CalendarAccessLevel.READER;
        }
        if (integer.intValue() >= 100)
        {
            return CalendarAccessLevel.FREEBUSY;
        } else
        {
            return CalendarAccessLevel.NONE;
        }
    }
}
