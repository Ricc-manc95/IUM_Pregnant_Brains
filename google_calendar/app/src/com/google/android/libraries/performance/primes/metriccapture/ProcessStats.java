// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.metriccapture;

import android.app.ActivityManager;
import android.content.Context;
import android.os.PowerManager;
import android.os.Process;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ProcessStats
{

    private static volatile ActivityManager activityManager = null;

    public ProcessStats()
    {
    }

    public static ActivityManager getActivityManager(Context context)
    {
        if (activityManager != null) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/performance/primes/metriccapture/ProcessStats;
        JVM INSTR monitorenter ;
        if (activityManager == null)
        {
            activityManager = (ActivityManager)context.getSystemService("activity");
        }
        com/google/android/libraries/performance/primes/metriccapture/ProcessStats;
        JVM INSTR monitorexit ;
_L2:
        return activityManager;
        context;
        com/google/android/libraries/performance/primes/metriccapture/ProcessStats;
        JVM INSTR monitorexit ;
        throw context;
    }

    public static String getProcessNameFromProcFile(int i)
    {
        if (i > 0) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        Object obj = new BufferedReader(new FileReader((new StringBuilder(25)).append("/proc/").append(i).append("/cmdline").toString()));
        String s = ((BufferedReader) (obj)).readLine().trim();
        try
        {
            ((BufferedReader) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            return s;
        }
        return s;
        obj;
        obj = null;
_L6:
        if (obj == null) goto _L1; else goto _L3
_L3:
        try
        {
            ((BufferedReader) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            return null;
        }
        return null;
        Object obj1;
        obj1;
        obj = null;
_L5:
        if (obj != null)
        {
            try
            {
                ((BufferedReader) (obj)).close();
            }
            catch (IOException ioexception) { }
        }
        throw obj1;
        obj1;
        if (true) goto _L5; else goto _L4
_L4:
        obj1;
          goto _L6
    }

    public static String getShortProcessName(Context context)
    {
        context = context.getPackageName();
        String s;
        for (s = getProcessNameFromProcFile(Process.myPid()); s == null || context == null || !s.startsWith(context);)
        {
            return s;
        }

        int i = context.length();
        if (s.length() == i)
        {
            return null;
        } else
        {
            return s.substring(i + 1);
        }
    }

    public static boolean isAppInForeground(Context context)
    {
label0:
        {
            Object obj = ((ActivityManager)context.getSystemService("activity")).getRunningAppProcesses();
            if (obj == null)
            {
                break label0;
            }
            obj = ((List) (obj)).iterator();
            do
            {
                android.app.ActivityManager.RunningAppProcessInfo runningappprocessinfo;
                do
                {
                    if (!((Iterator) (obj)).hasNext())
                    {
                        break label0;
                    }
                    runningappprocessinfo = (android.app.ActivityManager.RunningAppProcessInfo)((Iterator) (obj)).next();
                } while (runningappprocessinfo.importance != 100 || !runningappprocessinfo.processName.contains(context.getPackageName()));
                boolean flag;
                if (android.os.Build.VERSION.SDK_INT < 23)
                {
                    flag = ((PowerManager)context.getSystemService("power")).isInteractive();
                } else
                {
                    flag = true;
                }
            } while (!flag);
            return true;
        }
        return false;
    }

    public static boolean isMyProcessInForeground(Context context)
    {
        context = getActivityManager(context).getRunningAppProcesses();
        if (context == null)
        {
            return false;
        }
        for (context = context.iterator(); context.hasNext();)
        {
            android.app.ActivityManager.RunningAppProcessInfo runningappprocessinfo = (android.app.ActivityManager.RunningAppProcessInfo)context.next();
            if (runningappprocessinfo.pid == Process.myPid())
            {
                return runningappprocessinfo.importance == 100;
            }
        }

        return false;
    }

}
