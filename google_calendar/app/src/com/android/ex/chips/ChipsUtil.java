// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.ex.chips;

import android.content.Context;
import android.os.Process;

public final class ChipsUtil
{

    public static final String REQUIRED_PERMISSIONS[] = {
        "android.permission.READ_CONTACTS"
    };

    public static boolean hasPermissions(Context context, PermissionsCheckListener permissionschecklistener)
    {
        String as[] = REQUIRED_PERMISSIONS;
        int k = as.length;
        int j;
        for (int i = 0; i < k; i++)
        {
            String s = as[i];
            if (android.os.Build.VERSION.SDK_INT >= 23)
            {
                j = context.checkPermission(s, Process.myPid(), Process.myUid());
            } else
            {
                j = 0;
            }
            if (j == 0)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (permissionschecklistener != null)
            {
                permissionschecklistener._mth5166KOBMC4NMOOBECSNL6T3ID5N6EEQQ55B0____0();
            }
            if (j == 0)
            {
                return false;
            }
        }

        return true;
    }


    private class PermissionsCheckListener
    {

        public abstract void onPermissionCheck$5166KOBMC4NMOOBECSNL6T3ID5N6EEQQ55B0____0();
    }

}
