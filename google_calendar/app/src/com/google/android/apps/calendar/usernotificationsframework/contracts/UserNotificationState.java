// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.contracts;


public final class UserNotificationState extends Enum
{

    private static final UserNotificationState $VALUES[];
    public static final UserNotificationState ACCEPTED;
    public static final UserNotificationState DISMISSED;
    public static final UserNotificationState EXPIRED;
    public static final UserNotificationState FIRED;
    public static final UserNotificationState NOT_FIRED;
    public static final UserNotificationState OBSOLETE;
    public static final UserNotificationState SHOWN;

    private UserNotificationState(String s, int i)
    {
        super(s, i);
    }

    public static UserNotificationState[] values()
    {
        return (UserNotificationState[])$VALUES.clone();
    }

    static 
    {
        NOT_FIRED = new UserNotificationState("NOT_FIRED", 0);
        FIRED = new UserNotificationState("FIRED", 1);
        SHOWN = new UserNotificationState("SHOWN", 2);
        ACCEPTED = new UserNotificationState("ACCEPTED", 3);
        DISMISSED = new UserNotificationState("DISMISSED", 4);
        OBSOLETE = new UserNotificationState("OBSOLETE", 5);
        EXPIRED = new UserNotificationState("EXPIRED", 6);
        $VALUES = (new UserNotificationState[] {
            NOT_FIRED, FIRED, SHOWN, ACCEPTED, DISMISSED, OBSOLETE, EXPIRED
        });
    }
}
