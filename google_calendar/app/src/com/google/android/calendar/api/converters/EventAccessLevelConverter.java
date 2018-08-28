// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.converters;

import com.android.calendarcommon2.LogUtils;

public class EventAccessLevelConverter
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/api/converters/EventAccessLevelConverter);

    public EventAccessLevelConverter()
    {
    }

    public static int apiToProvider(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException((new StringBuilder(37)).append("Invalid visibility value: ").append(i).toString());

        case 0: // '\0'
            return 0;

        case 1: // '\001'
            return 3;

        case 2: // '\002'
        case 3: // '\003'
            return 2;
        }
    }

    public static int providerToApi(int i)
    {
        switch (i)
        {
        default:
            LogUtils.w(TAG, "Defaulted to 'default' visibility for invalid visibility: %d", new Object[] {
                Integer.valueOf(i)
            });
            // fall through

        case 0: // '\0'
            return 0;

        case 3: // '\003'
            return 1;

        case 2: // '\002'
            return 2;

        case 1: // '\001'
            return 2;
        }
    }

}
