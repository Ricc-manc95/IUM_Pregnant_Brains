// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.latency.impl;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.latency.impl:
//            PerformanceSpan, PerformanceMark

final class IntraSpans extends PerformanceSpan
{

    public Map oneShots;

    public IntraSpans(String s, int i, PerformanceMark performancemark, String s1)
    {
        super(s, i, null, s1);
        if (marks != null)
        {
            oneShots = new HashMap();
        }
    }

    private final boolean putOneShot(int i, int j)
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if (marks != null)
        {
            flag = flag1;
            if (!oneShots.containsKey(Integer.valueOf(j)))
            {
                oneShots.put(Integer.valueOf(j), Integer.valueOf(i));
                flag = true;
            }
        }
        return flag;
    }

    public final void clearAt(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        super.clearAt(i, j);
        if (marks != null)
        {
            oneShots.remove(Integer.valueOf(i));
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    final PerformanceMark startSubSpanAt(int i, int j, PerformanceMark performancemark, String s)
    {
        this;
        JVM INSTR monitorenter ;
        PerformanceMark performancemark1 = performancemark;
        if (putOneShot(j, i))
        {
            performancemark1 = startSubSpanAt(i, performancemark, s);
        }
        this;
        JVM INSTR monitorexit ;
        return performancemark1;
        performancemark;
        throw performancemark;
    }

    final PerformanceMark stopSubSpanAt(int i, int j, PerformanceMark performancemark, String s, int k)
    {
        this;
        JVM INSTR monitorenter ;
        PerformanceMark performancemark1 = performancemark;
        if (putOneShot(j, i))
        {
            performancemark1 = stopSubSpanAt(i, performancemark, s, k);
        }
        this;
        JVM INSTR monitorexit ;
        return performancemark1;
        performancemark;
        throw performancemark;
    }
}
