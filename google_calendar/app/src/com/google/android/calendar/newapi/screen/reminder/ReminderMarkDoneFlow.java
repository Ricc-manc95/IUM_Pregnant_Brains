// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.utils.flow.Flow;

public class ReminderMarkDoneFlow extends Flow
{
    public static final class Factory
    {

        public Factory()
        {
        }
    }

    public static interface Listener
    {

        public abstract void onTaskDoneStateChanged(boolean flag);
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/screen/reminder/ReminderMarkDoneFlow);

    public ReminderMarkDoneFlow()
    {
    }

}
