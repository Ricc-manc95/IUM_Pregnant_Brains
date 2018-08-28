// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.a11y;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.AccessibilityUtils;

public class A11yHelper
{
    static final class InstanceHolder
    {

        public static final A11yHelper INSTANCE = new A11yHelper();

    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/utils/a11y/A11yHelper);

    A11yHelper()
    {
    }

    public static A11yHelper getInstance()
    {
        return InstanceHolder.INSTANCE;
    }

    public static boolean isAccessibilityEnabled(Context context)
    {
        Object obj = CalendarProperties.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        }
        obj = ((CalendarProperties)obj).getPropertyValue(6);
        if (obj instanceof Boolean)
        {
            return ((Boolean)obj).booleanValue();
        } else
        {
            LogUtils.wtf(TAG, "Value provided by GeneralSettingsManager was invalid: %s", new Object[] {
                obj
            });
            return AccessibilityUtils.isAccessibilityEnabled(context);
        }
    }

}
