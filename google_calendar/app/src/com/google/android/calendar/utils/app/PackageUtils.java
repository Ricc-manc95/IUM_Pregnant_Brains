// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.android.calendarcommon2.LogUtils;

public class PackageUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/utils/app/PackageUtils);
    private static Integer versionCode = null;

    public PackageUtils()
    {
    }

    public static int getVersionCode(Context context)
    {
        if (versionCode == null)
        {
            try
            {
                versionCode = Integer.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
            }
            catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                LogUtils.wtf(TAG, "Error finding package %s", new Object[] {
                    context.getApplicationInfo().packageName
                });
                return 0;
            }
        }
        return versionCode.intValue();
    }

}
