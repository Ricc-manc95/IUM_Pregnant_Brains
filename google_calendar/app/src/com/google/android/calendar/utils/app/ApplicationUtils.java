// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.android.calendarcommon2.LogUtils;

public class ApplicationUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/utils/app/ApplicationUtils);
    private static Boolean isSystemApp;

    public ApplicationUtils()
    {
    }

    public static boolean isSystemApp(Context context)
    {
        boolean flag1 = false;
        com/google/android/calendar/utils/app/ApplicationUtils;
        JVM INSTR monitorenter ;
        if (isSystemApp == null) goto _L2; else goto _L1
_L1:
        boolean flag = isSystemApp.booleanValue();
_L4:
        com/google/android/calendar/utils/app/ApplicationUtils;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        PackageManager packagemanager = context.getPackageManager();
        if ((packagemanager.getApplicationInfo(context.getPackageName(), 0).flags & 1) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        try
        {
            context = Boolean.valueOf(flag);
            isSystemApp = context;
            flag = context.booleanValue();
            continue; /* Loop/switch isn't completed */
        }
        // Misplaced declaration of an exception variable
        catch (Context context) { }
        finally
        {
            com/google/android/calendar/utils/app/ApplicationUtils;
        }
        LogUtils.e(TAG, "Cannot find Calendar app in package manager", new Object[0]);
        flag = flag1;
        if (true) goto _L4; else goto _L3
_L3:
        throw context;
    }

}
