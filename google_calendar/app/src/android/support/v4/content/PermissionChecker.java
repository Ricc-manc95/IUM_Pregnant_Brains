// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;

public final class PermissionChecker
{

    public static int checkSelfPermission(Context context, String s)
    {
        int i = Process.myPid();
        int k = Process.myUid();
        Object obj = context.getPackageName();
        if (context.checkPermission(s, i, k) == -1)
        {
            return -1;
        }
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            s = AppOpsManager.permissionToOp(s);
        } else
        {
            s = null;
        }
        if (s != null)
        {
            if (obj == null)
            {
                obj = context.getPackageManager().getPackagesForUid(k);
                if (obj == null || obj.length <= 0)
                {
                    return -1;
                }
                obj = obj[0];
            }
            int j;
            if (android.os.Build.VERSION.SDK_INT >= 23)
            {
                j = ((AppOpsManager)context.getSystemService(android/app/AppOpsManager)).noteProxyOpNoThrow(s, ((String) (obj)));
            } else
            {
                j = 1;
            }
            if (j != 0)
            {
                return -2;
            }
        }
        return 0;
    }
}
