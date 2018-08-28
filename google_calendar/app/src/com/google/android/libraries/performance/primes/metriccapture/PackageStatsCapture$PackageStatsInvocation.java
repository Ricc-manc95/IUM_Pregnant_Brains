// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.metriccapture;

import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import com.google.android.libraries.performance.primes.PrimesLog;
import java.lang.reflect.Method;
import java.util.Arrays;

// Referenced classes of package com.google.android.libraries.performance.primes.metriccapture:
//            PackageStatsCapture

static abstract class paramTypes
{

    private final String methodName;
    private final Class paramTypes[];

    boolean invoke(PackageManager packagemanager, String s, int i, IPackageStatsObserver ipackagestatsobserver)
    {
        packagemanager.getClass().getMethod(methodName, paramTypes).invoke(packagemanager, params(s, i, ipackagestatsobserver));
        return true;
        packagemanager;
        PrimesLog.log(3, "PackageStatsCapture", packagemanager, "PackageStats getter not found", new Object[0]);
_L2:
        return false;
        packagemanager;
_L3:
        s = new StringBuilder();
        s.append(packagemanager.getClass().getSimpleName()).append(" for ").append(methodName).append('(').append(Arrays.asList(paramTypes)).append(") invocation");
        PrimesLog.log(4, "PackageStatsCapture", s.toString(), new Object[0]);
        if (true) goto _L2; else goto _L1
_L1:
        packagemanager;
          goto _L3
    }

    abstract Object[] params(String s, int i, IPackageStatsObserver ipackagestatsobserver);

    (String s, Class aclass[])
    {
        methodName = s;
        paramTypes = aclass;
    }
}
