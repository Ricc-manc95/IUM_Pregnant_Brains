// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms;

import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.settings.SettingsClient;

public class RoomServiceStatusTask
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/rooms/RoomServiceStatusTask);
    public SettingsClient settingsClient;

    public RoomServiceStatusTask()
    {
        settingsClient = CalendarApi.Settings;
    }

}
