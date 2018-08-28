// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils;

import android.content.Context;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import com.android.calendarcommon2.LogUtils;
import java.util.List;

public class AccessibilityUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/utils/AccessibilityUtils);

    public AccessibilityUtils()
    {
    }

    public static boolean isAccessibilityEnabled(Context context)
    {
        if (context == null)
        {
            LogUtils.e(TAG, "Null context passed to isAccessibilityEnabled", new Object[0]);
            return false;
        }
        context = (AccessibilityManager)context.getSystemService("accessibility");
        if (context != null && context.isEnabled())
        {
            return !context.getEnabledAccessibilityServiceList(1).isEmpty();
        } else
        {
            return false;
        }
    }

    public static void requestAccessibilityFocus(View view)
    {
        if (isAccessibilityEnabled(view.getContext()))
        {
            view.performAccessibilityAction(64, null);
        }
    }

}
