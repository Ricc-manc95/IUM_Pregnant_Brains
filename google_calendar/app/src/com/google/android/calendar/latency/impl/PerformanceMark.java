// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.latency.impl;

import android.os.Debug;
import android.os.SystemClock;
import android.util.SparseArray;

final class PerformanceMark
{

    public static SparseArray markNames;
    public final long cpuTimeNanos;
    public final String mark;
    public final int markId;
    public final String tag;
    public final long wallTimeMillis;

    public PerformanceMark(int i, String s)
    {
        cpuTimeNanos = Debug.threadCpuTimeNanos();
        wallTimeMillis = SystemClock.uptimeMillis();
        mark = getMarkName(i);
        tag = s;
        markId = i;
    }

    public PerformanceMark(String s)
    {
        cpuTimeNanos = Debug.threadCpuTimeNanos();
        wallTimeMillis = SystemClock.uptimeMillis();
        mark = "null";
        tag = s;
        markId = -1;
    }

    static String getMarkName(int i)
    {
        String s;
        if (markNames == null)
        {
            s = Integer.toString(i);
        } else
        {
            String s1 = (String)markNames.get(i);
            s = s1;
            if (s1 == null)
            {
                return Integer.toString(i);
            }
        }
        return s;
    }
}
