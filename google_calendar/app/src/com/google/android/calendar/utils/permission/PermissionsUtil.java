// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.permission;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.utils.version.MncUtil;
import java.util.HashSet;
import java.util.Set;

public class PermissionsUtil
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/utils/permission/PermissionsUtil);
    public static final Set grantedPermissions = new HashSet();

    public PermissionsUtil()
    {
    }

    public static boolean canRequestPermissions()
    {
        return MncUtil.isMnc();
    }

    public static int checkSelfPermission(Context context, String s)
    {
        if (!MncUtil.isMnc())
        {
            return 0;
        }
        if (grantedPermissions.contains(s))
        {
            LogUtils.v(TAG, "%s found in cache", new Object[] {
                s
            });
            return 0;
        }
        int i = context.checkSelfPermission(s);
        if (i == 0)
        {
            LogUtils.v(TAG, "%s granted, adding to cache", new Object[] {
                s
            });
            grantedPermissions.add(s);
        } else
        {
            LogUtils.v(TAG, "%s denied", new Object[] {
                s
            });
            grantedPermissions.remove(s);
        }
        return i;
    }

    public static void requestPermissions(Activity activity, String as[], int i)
    {
        if (MncUtil.isMnc())
        {
            activity.requestPermissions(as, i);
            return;
        } else
        {
            throw new UnsupportedOperationException("Check canRequestPermissions().");
        }
    }

    public static boolean shouldShowRationale(Activity activity, String s)
    {
        return MncUtil.isMnc() && ActivityCompat.shouldShowRequestPermissionRationale(activity, s);
    }

}
