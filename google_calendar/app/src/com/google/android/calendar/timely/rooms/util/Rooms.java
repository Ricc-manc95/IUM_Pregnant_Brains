// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.util;

import com.google.android.calendar.timely.rooms.data.RoomBookingFilterParams;

public final class Rooms
{

    public static int getFilterDescription(RoomBookingFilterParams roombookingfilterparams)
    {
        boolean flag = roombookingfilterparams.showOnlyAvailable();
        roombookingfilterparams = roombookingfilterparams.getRecurrenceOption();
        if (roombookingfilterparams == null)
        {
            return !flag ? 0x7f130405 : 0x7f130408;
        }
        switch (roombookingfilterparams.intValue())
        {
        default:
            throw new IllegalArgumentException("Wrong recurrenceOption");

        case 2: // '\002'
            return !flag ? 0x7f130407 : 0x7f13040a;

        case 1: // '\001'
            break;
        }
        return !flag ? 0x7f130406 : 0x7f130409;
    }
}
