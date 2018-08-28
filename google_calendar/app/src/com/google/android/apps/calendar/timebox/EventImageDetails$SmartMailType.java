// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;


public final class  extends Enum
{

    private static final INVITATION $VALUES[];
    public static final INVITATION FLIGHT;
    public static final INVITATION HOTEL_RESERVATION;
    public static final INVITATION INVITATION;
    public static final INVITATION RESTAURANT_RESERVATION;
    public static final INVITATION TICKET;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        TICKET = new <init>("TICKET", 0);
        FLIGHT = new <init>("FLIGHT", 1);
        HOTEL_RESERVATION = new <init>("HOTEL_RESERVATION", 2);
        RESTAURANT_RESERVATION = new <init>("RESTAURANT_RESERVATION", 3);
        INVITATION = new <init>("INVITATION", 4);
        $VALUES = (new .VALUES[] {
            TICKET, FLIGHT, HOTEL_RESERVATION, RESTAURANT_RESERVATION, INVITATION
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
