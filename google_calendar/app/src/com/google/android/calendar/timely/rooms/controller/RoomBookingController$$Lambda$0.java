// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import com.google.android.calendar.timely.rooms.data.Room;
import com.google.common.base.Predicate;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.timely.rooms.controller:
//            RoomBookingController

final class arg._cls1
    implements Predicate
{

    private final RoomBookingController arg$1;

    public final boolean apply(Object obj)
    {
        RoomBookingController roombookingcontroller = arg$1;
        obj = (Room)obj;
        return obj != null && !roombookingcontroller.nonRemovableRoomEmails.contains(((Room) (obj)).getEmail());
    }

    Y(RoomBookingController roombookingcontroller)
    {
        arg$1 = roombookingcontroller;
    }
}
