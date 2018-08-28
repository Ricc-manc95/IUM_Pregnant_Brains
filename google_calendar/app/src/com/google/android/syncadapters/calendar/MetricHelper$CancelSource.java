// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;


// Referenced classes of package com.google.android.syncadapters.calendar:
//            DeviceIdleAndNetworkStatus

final class  extends Enum
{

    private static final OFFLINE $VALUES[];
    private static final OFFLINE DOZE;
    private static final OFFLINE DOZE_LIGHT;
    private static final OFFLINE OFFLINE;
    private static final OFFLINE UNKNOWN;

    static  fromStatus(DeviceIdleAndNetworkStatus deviceidleandnetworkstatus)
    {
        if (Boolean.TRUE.equals(deviceidleandnetworkstatus.isDeviceIdleLight))
        {
            return DOZE_LIGHT;
        }
        if (Boolean.TRUE.equals(deviceidleandnetworkstatus.isDeviceIdle))
        {
            return DOZE;
        }
        if (Boolean.FALSE.equals(deviceidleandnetworkstatus.hasNetwork))
        {
            return OFFLINE;
        } else
        {
            return UNKNOWN;
        }
    }

    public static UNKNOWN[] values()
    {
        return (UNKNOWN[])$VALUES.clone();
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0);
        DOZE = new <init>("DOZE", 1);
        DOZE_LIGHT = new <init>("DOZE_LIGHT", 2);
        OFFLINE = new <init>("OFFLINE", 3);
        $VALUES = (new .VALUES[] {
            UNKNOWN, DOZE, DOZE_LIGHT, OFFLINE
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
