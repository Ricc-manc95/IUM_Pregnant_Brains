// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.version;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import com.android.calendarcommon2.LogUtils;

public class MncUtil
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/utils/version/MncUtil);

    public MncUtil()
    {
    }

    public static boolean isGetAccountsIssuePresent(Context context)
    {
        String as[];
        boolean flag1;
        flag1 = false;
        context = context.getPackageManager();
        as = context.getPackagesForUid(Process.myUid());
        int j = as.length;
        int i = 0;
_L2:
        boolean flag;
        flag = flag1;
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        context.getPackageInfo(as[i], 64);
        i++;
        if (true) goto _L2; else goto _L1
        context;
        LogUtils.w(TAG, context, "One of the packages with the same ID as ours is not installed.", new Object[0]);
        flag = true;
_L1:
        return flag;
    }

    public static boolean isMnc()
    {
        return android.os.Build.VERSION.SDK_INT >= 23;
    }

}
