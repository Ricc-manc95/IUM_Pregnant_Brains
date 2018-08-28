// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.content.Context;
import android.os.Process;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import java.io.File;

final class PrimesHprofFile
{

    static File getHprofFile(Context context)
    {
        if (context == null)
        {
            throw new NullPointerException();
        } else
        {
            File file = context.getCacheDir();
            context = preparePrefix(context);
            return new File(file, (new StringBuilder(String.valueOf(context).length() + 12)).append(context).append("_primeshprof").toString());
        }
    }

    static File getMiniHeapDumpHprofFile(Context context)
    {
        if (context == null)
        {
            throw new NullPointerException();
        } else
        {
            File file = context.getCacheDir();
            context = preparePrefix(context);
            return new File(file, (new StringBuilder(String.valueOf(context).length() + 17)).append(context).append("_primes_mhd.hprof").toString());
        }
    }

    private static String preparePrefix(Context context)
    {
        String s1 = context.getPackageName();
        String s = ProcessStats.getProcessNameFromProcFile(Process.myPid());
        context = s;
        if (s != null)
        {
            if (s1 == null)
            {
                context = s;
            } else
            {
                context = s;
                if (s.startsWith(s1))
                {
                    int i = s1.length();
                    if (s.length() == i)
                    {
                        context = null;
                    } else
                    {
                        context = s.substring(i + 1);
                    }
                }
            }
        }
        if (context != null)
        {
            context = context.replaceAll("[^a-zA-Z0-9\\._]", "_");
            return context.substring(0, Math.min(32, context.length()));
        } else
        {
            return "";
        }
    }
}
