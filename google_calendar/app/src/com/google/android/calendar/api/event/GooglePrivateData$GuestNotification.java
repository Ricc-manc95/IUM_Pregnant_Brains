// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;


// Referenced classes of package com.google.android.calendar.api.event:
//            GooglePrivateData

public static final class  extends Enum
{

    private static final ENABLED $VALUES[];
    public static final ENABLED DISABLED;
    public static final ENABLED ENABLED;
    public static final ENABLED UNDECIDED;

    public static  getGuestNotificationFromInteger(int i)
    {
        if (i < 0 || i >= values().length)
        {
            return UNDECIDED;
        } else
        {
            return values()[i];
        }
    }

    public static values valueOf(String s)
    {
        return (values)Enum.valueOf(com/google/android/calendar/api/event/GooglePrivateData$GuestNotification, s);
    }

    public static values[] values()
    {
        return (values[])$VALUES.clone();
    }

    static 
    {
        UNDECIDED = new <init>("UNDECIDED", 0);
        DISABLED = new <init>("DISABLED", 1);
        ENABLED = new <init>("ENABLED", 2);
        $VALUES = (new .VALUES[] {
            UNDECIDED, DISABLED, ENABLED
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
