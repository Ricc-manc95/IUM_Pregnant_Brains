// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.backgroundjobs;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import com.google.android.libraries.performance.primes.PrimesLog;

public final class PrimesJobScheduler
{

    public static boolean isJobEnabled(Application application, String s)
    {
        PackageManager packagemanager = application.getPackageManager();
        application = packagemanager.getPackageInfo(application.getPackageName(), 4).services;
        if (application == null)
        {
            return false;
        }
        int j = application.length;
        int i = 0;
_L2:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        boolean flag = ((ServiceInfo) (application[i])).name.equals(s);
        if (flag)
        {
            return true;
        }
        i++;
        if (true) goto _L2; else goto _L1
        application;
        PrimesLog.log(3, "PrimesJobScheduler", application, "Failed to find application package", new Object[0]);
_L1:
        return false;
    }
}
