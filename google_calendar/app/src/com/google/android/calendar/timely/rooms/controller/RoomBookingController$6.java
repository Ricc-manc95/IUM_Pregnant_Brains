// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import com.google.android.apps.calendar.loggers.CalendarClientLogger;
import com.google.android.calendar.timely.net.pagination.PageableResult;
import com.google.android.calendar.timely.net.pagination.PaginatingClient;
import com.google.android.calendar.timely.rooms.data.Room;
import com.google.android.calendar.timely.rooms.net.RoomRequest;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collections;

// Referenced classes of package com.google.android.calendar.timely.rooms.controller:
//            RoomBookingController, ClientAnalytics

final class this._cls0
    implements com.google.android.calendar.timely.rooms.ui.roller.Listener
{

    private final RoomBookingController this$0;

    public final void onNextPageRequested()
    {
        PaginatingClient paginatingclient = client;
        boolean flag;
        if (paginatingclient.extendableResult != null && paginatingclient.extendableResult.hasNextPage())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            setExpandedLocationRequestCabllback(client.getNextPage());
        }
    }

    public final void onRoomRemoved(Room room)
    {
        RoomBookingController roombookingcontroller = RoomBookingController.this;
        boolean flag;
        if (roombookingcontroller.state == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        Object obj = roombookingcontroller.request;
        Object obj1 = ImmutableList.builder();
        Object obj2 = (ImmutableList)((RoomRequest) (obj)).getSelectedRooms();
        int j = ((ImmutableList) (obj2)).size();
        int i = 0;
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
        while (i < j) 
        {
            Object obj3 = (Room)((ImmutableList) (obj2)).get(i);
            String s = ((Room) (obj3)).getEmail();
            String s1 = room.getEmail();
            boolean flag1;
            if (s == s1 || s != null && s.equals(s1))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                obj3 = (com.google.common.collect..getEmail)((com.google.common.collect.der) (obj1)).add(obj3);
            }
            i++;
        }
        obj1.rceCopy = true;
        obj1 = ImmutableList.asImmutableList(((com.google.common.collect.eList) (obj1)).ntents, ((com.google.common.collect.ntents) (obj1)).ze);
        obj2 = ((RoomRequest) (obj)).getSelectedRooms();
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (!i)
        {
            obj = ((RoomRequest) (obj)).toBuilder().electedRooms(((ImmutableList) (obj1))).d();
        }
        roombookingcontroller.request = ((RoomRequest) (obj));
        roombookingcontroller.updateSelectedRoomState(roombookingcontroller.request.getSelectedRooms());
        obj = roombookingcontroller.clientAnalytics;
        obj1 = ((ClientAnalytics) (obj)).logger;
        obj2 = ((ClientAnalytics) (obj)).calendarEventReference;
        room = room.getEmail();
        ((CalendarClientLogger) (obj1)).log(((ClientAnalytics) (obj)).account, ((CalendarClientLogger) (obj1)).getRoomBookingProto(com.google.calendar.client.events.logging.ype.ROOM_REMOVED, null, null, ((String) (obj2)), null, null, null, null, room, null, null, null, Collections.emptyList()));
        roombookingcontroller.persistViewControllerState();
        roombookingcontroller.detachView();
        roombookingcontroller.state = 0;
        roombookingcontroller.attachView();
    }

    public final void onRoomSelected(Room room)
    {
        boolean flag = true;
        RoomBookingController roombookingcontroller = RoomBookingController.this;
        if (roombookingcontroller.state != 1)
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            roombookingcontroller.addRoom(room, false);
            roombookingcontroller.persistViewControllerState();
            roombookingcontroller.detachView();
            roombookingcontroller.state = 0;
            roombookingcontroller.attachView();
            return;
        }
    }

    istener()
    {
        this$0 = RoomBookingController.this;
        super();
    }
}
