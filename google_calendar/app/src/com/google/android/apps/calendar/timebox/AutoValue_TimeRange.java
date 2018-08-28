// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            $AutoValue_TimeRange

final class AutoValue_TimeRange extends $AutoValue_TimeRange
{

    private volatile long getLocalEndMillis;
    private volatile boolean getLocalEndMillis$Memoized;
    private volatile long getLocalStartMillis;
    private volatile boolean getLocalStartMillis$Memoized;

    AutoValue_TimeRange(TimeZone timezone, boolean flag, long l, long l1, int i, 
            int j, int k, int i1)
    {
        super(timezone, flag, l, l1, i, j, k, i1);
    }

    public final long getLocalEndMillis()
    {
        if (getLocalEndMillis$Memoized) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if (!getLocalEndMillis$Memoized)
        {
            getLocalEndMillis = super.getLocalEndMillis();
            getLocalEndMillis$Memoized = true;
        }
        this;
        JVM INSTR monitorexit ;
_L2:
        return getLocalEndMillis;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final long getLocalStartMillis()
    {
        if (getLocalStartMillis$Memoized) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if (!getLocalStartMillis$Memoized)
        {
            getLocalStartMillis = super.getLocalStartMillis();
            getLocalStartMillis$Memoized = true;
        }
        this;
        JVM INSTR monitorexit ;
_L2:
        return getLocalStartMillis;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
