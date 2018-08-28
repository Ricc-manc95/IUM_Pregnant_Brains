// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            $AutoValue_AdapterMonthDay

final class AutoValue_AdapterMonthDay extends $AutoValue_AdapterMonthDay
{

    private volatile int getNumRows;
    private volatile boolean getNumRows$Memoized;

    AutoValue_AdapterMonthDay(int i, ImmutableList immutablelist, boolean flag)
    {
        super(i, immutablelist, flag);
    }

    public final int getNumRows()
    {
        if (getNumRows$Memoized) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if (!getNumRows$Memoized)
        {
            getNumRows = super.getNumRows();
            getNumRows$Memoized = true;
        }
        this;
        JVM INSTR monitorexit ;
_L2:
        return getNumRows;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
