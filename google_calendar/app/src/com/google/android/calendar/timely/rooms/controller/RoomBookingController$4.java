// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import com.google.android.calendar.timely.rooms.data.Room;
import com.google.android.calendar.timely.rooms.net.RoomRequest;
import com.google.android.calendar.timely.rooms.ui.ErrorViewController;
import com.google.android.calendar.timely.rooms.ui.RoomBookingHeaderAdapter;
import com.google.android.calendar.timely.rooms.ui.RoomUiItemFactory;

// Referenced classes of package com.google.android.calendar.timely.rooms.controller:
//            RoomBookingController

final class this._cls0
    implements com.google.android.calendar.timely.rooms.ui.ener
{

    private final RoomBookingController this$0;

    public final void onRoomRemoved(Room room)
    {
        removeRoom(room);
        room = errorViewController;
        com.google.common.collect.ImmutableList immutablelist = request.getSelectedRooms();
        ((ErrorViewController) (room)).addedRoomsList.updateWithItems(RoomUiItemFactory.fromAddedRooms(immutablelist, ((ErrorViewController) (room)).isRoomRemovable));
    }

    public final void onTryAgainClicked()
    {
        RoomBookingController roombookingcontroller = RoomBookingController.this;
        int i = state;
        roombookingcontroller.persistViewControllerState();
        roombookingcontroller.detachView();
        roombookingcontroller.state = i;
        roombookingcontroller.attachView();
    }

    Q()
    {
        this$0 = RoomBookingController.this;
        super();
    }
}
