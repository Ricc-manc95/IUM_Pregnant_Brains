// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.v2a;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;

public final class SideSyncUtils
{

    private static boolean isEnabled;

    public static boolean isActive(Context context)
    {
        if (!isEnabled)
        {
            return false;
        }
        Boolean boolean1;
        if (LogUtils.isLoggableFixed("uss_sidesync_active", 2))
        {
            boolean1 = Boolean.valueOf(true);
        } else
        if (!LogUtils.isLoggableFixed("uss_sidesync_active", 4))
        {
            boolean1 = Boolean.valueOf(false);
        } else
        {
            boolean1 = null;
        }
        if (boolean1 != null)
        {
            return boolean1.booleanValue();
        } else
        {
            return context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("uss_sidesync_active", false);
        }
    }

    public static boolean shouldActivate(Context context)
    {
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)context).uss_provider_sync();
            return false;
        }
    }
}
