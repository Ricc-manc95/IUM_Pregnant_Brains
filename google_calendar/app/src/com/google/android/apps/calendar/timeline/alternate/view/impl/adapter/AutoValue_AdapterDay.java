// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            $AutoValue_AdapterDay

final class AutoValue_AdapterDay extends $AutoValue_AdapterDay
{

    private volatile int getNumAllDayRows;
    private volatile boolean getNumAllDayRows$Memoized;

    AutoValue_AdapterDay(int i, int j, int k, boolean flag, ImmutableList immutablelist, ImmutableList immutablelist1)
    {
        super(i, j, k, flag, immutablelist, immutablelist1);
    }

    public final int getNumAllDayRows()
    {
        if (getNumAllDayRows$Memoized) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if (!getNumAllDayRows$Memoized)
        {
            getNumAllDayRows = super.getNumAllDayRows();
            getNumAllDayRows$Memoized = true;
        }
        this;
        JVM INSTR monitorexit ;
_L2:
        return getNumAllDayRows;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
