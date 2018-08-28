// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.shared.sync;


// Referenced classes of package com.google.calendar.v2a.shared.sync:
//            SyncRequestTracker

public static final class  extends Enum
{

    private static final FAILURE $VALUES[];
    public static final FAILURE FAILURE;
    public static final FAILURE PENDING;
    public static final FAILURE RUNNING;
    public static final FAILURE SUCCESS;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        PENDING = new <init>("PENDING", 0);
        RUNNING = new <init>("RUNNING", 1);
        SUCCESS = new <init>("SUCCESS", 2);
        FAILURE = new <init>("FAILURE", 3);
        $VALUES = (new .VALUES[] {
            PENDING, RUNNING, SUCCESS, FAILURE
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
