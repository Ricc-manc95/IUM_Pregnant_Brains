// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.contract;


public final class  extends Enum
{

    private static final UNSUBSCRIBE_CALENDAR $VALUES[];
    public static final UNSUBSCRIBE_CALENDAR SUBSCRIBE_CALENDAR;
    public static final UNSUBSCRIBE_CALENDAR UNSUBSCRIBE_CALENDAR;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        SUBSCRIBE_CALENDAR = new <init>("SUBSCRIBE_CALENDAR", 0);
        UNSUBSCRIBE_CALENDAR = new <init>("UNSUBSCRIBE_CALENDAR", 1);
        $VALUES = (new .VALUES[] {
            SUBSCRIBE_CALENDAR, UNSUBSCRIBE_CALENDAR
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
