// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.contracts;


public final class UserNotificationCheckOrigin extends Enum
{

    private static final UserNotificationCheckOrigin $VALUES[];
    public static final UserNotificationCheckOrigin EXPLICIT_CALL;
    public static final UserNotificationCheckOrigin NON_WAKING_BROADCAST;
    public static final UserNotificationCheckOrigin WAKING_BROADCAST;
    public final int value;

    private UserNotificationCheckOrigin(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static UserNotificationCheckOrigin[] values()
    {
        return (UserNotificationCheckOrigin[])$VALUES.clone();
    }

    static 
    {
        EXPLICIT_CALL = new UserNotificationCheckOrigin("EXPLICIT_CALL", 0, 1);
        WAKING_BROADCAST = new UserNotificationCheckOrigin("WAKING_BROADCAST", 1, 2);
        NON_WAKING_BROADCAST = new UserNotificationCheckOrigin("NON_WAKING_BROADCAST", 2, 3);
        $VALUES = (new UserNotificationCheckOrigin[] {
            EXPLICIT_CALL, WAKING_BROADCAST, NON_WAKING_BROADCAST
        });
    }
}
