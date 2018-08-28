// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.battery;


public final class  extends Enum
{

    private static final SENSOR $VALUES[];
    public static final SENSOR JOB;
    public static final SENSOR PROCESS;
    public static final SENSOR SENSOR;
    public static final SENSOR SYNC;
    public static final SENSOR WAKELOCK;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        WAKELOCK = new <init>("WAKELOCK", 0);
        SYNC = new <init>("SYNC", 1);
        JOB = new <init>("JOB", 2);
        PROCESS = new <init>("PROCESS", 3);
        SENSOR = new <init>("SENSOR", 4);
        $VALUES = (new .VALUES[] {
            WAKELOCK, SYNC, JOB, PROCESS, SENSOR
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
