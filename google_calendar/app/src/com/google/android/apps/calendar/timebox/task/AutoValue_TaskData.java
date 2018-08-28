// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.android.apps.calendar.timebox.TimeRange;

// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            $AutoValue_TaskData

final class AutoValue_TaskData extends $AutoValue_TaskData
{

    private volatile byte getTaskAssistanceProtoBytes[];
    private volatile boolean getTaskAssistanceProtoBytes$Memoized;

    AutoValue_TaskData(String s, String s1, int i, String s2, boolean flag, boolean flag1, boolean flag2, 
            String s3, Long long1, Long long2, Long long3, TimeRange timerange, byte abyte0[])
    {
        super(s, s1, i, s2, flag, flag1, flag2, s3, long1, long2, long3, timerange, abyte0);
    }

    public final byte[] getTaskAssistanceProtoBytes()
    {
        if (getTaskAssistanceProtoBytes$Memoized) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if (!getTaskAssistanceProtoBytes$Memoized)
        {
            getTaskAssistanceProtoBytes = super.getTaskAssistanceProtoBytes();
            getTaskAssistanceProtoBytes$Memoized = true;
        }
        this;
        JVM INSTR monitorexit ;
_L2:
        return getTaskAssistanceProtoBytes;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
