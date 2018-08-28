// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.metric;


public final class SyncMemoryRecording extends Enum
    implements MetricUtils.MemoryRecording
{

    private static final SyncMemoryRecording $VALUES[];
    public static final SyncMemoryRecording APIARY_PERIODIC;
    public static final SyncMemoryRecording UNIFIED_CATCHUP;
    private final String name;

    private SyncMemoryRecording(String s, int i, String s1)
    {
        super(s, i);
        name = s1;
    }

    public static SyncMemoryRecording[] values()
    {
        return (SyncMemoryRecording[])$VALUES.clone();
    }

    public final String getName()
    {
        return name;
    }

    static 
    {
        APIARY_PERIODIC = new SyncMemoryRecording("APIARY_PERIODIC", 0, "PeriodicSync");
        UNIFIED_CATCHUP = new SyncMemoryRecording("UNIFIED_CATCHUP", 1, "CatchupSync");
        $VALUES = (new SyncMemoryRecording[] {
            APIARY_PERIODIC, UNIFIED_CATCHUP
        });
    }
}
