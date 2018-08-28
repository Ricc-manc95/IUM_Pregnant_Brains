// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import android.text.TextUtils;
import com.google.android.calendar.timely.rooms.data.ExpandedMeetingLocation;
import com.google.android.calendar.timely.rooms.data.RoomFlatList;
import com.google.android.calendar.timely.rooms.net.RoomResponse;
import com.google.android.calendar.timely.rooms.ui.ExpandedLocationViewController;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.util.concurrent.FutureCallback;
import java.util.Collections;
import java.util.RandomAccess;
import java.util.concurrent.CancellationException;

// Referenced classes of package com.google.android.calendar.timely.rooms.controller:
//            RoomBookingController

final class this._cls0
    implements FutureCallback
{

    private final RoomBookingController this$0;

    public final void onFailure(Throwable throwable)
    {
        if (throwable instanceof CancellationException)
        {
            return;
        }
        ExpandedLocationViewController expandedlocationviewcontroller = expandedSuggestionsViewController;
        Object obj = expandedMeetingLocation.getRoomSuggestions();
        com.google.common.base.Function function = com.google.android.calendar.timely.rooms.data..Lambda._cls0..instance;
        if (obj instanceof RandomAccess)
        {
            obj = new com.google.common.collect.AccessList(((java.util.List) (obj)), function);
        } else
        {
            obj = new com.google.common.collect.tialList(((java.util.List) (obj)), function);
        }
        expandedlocationviewcontroller.setRooms(((java.util.List) (obj)), Collections.emptyList(), false, RegularImmutableSet.EMPTY);
        _flddelegate.onConnectionError(throwable);
    }

    public final void onSuccess(Object obj)
    {
        com.google.android.calendar.timely.rooms.net.geableRoomResponse geableroomresponse = (com.google.android.calendar.timely.rooms.net.geableRoomResponse)obj;
        ExpandedLocationViewController expandedlocationviewcontroller = expandedSuggestionsViewController;
        obj = expandedMeetingLocation.getRoomSuggestions();
        Object obj1 = com.google.android.calendar.timely.rooms.data..Lambda._cls0..instance;
        boolean flag;
        if (obj instanceof RandomAccess)
        {
            obj = new com.google.common.collect.AccessList(((java.util.List) (obj)), ((com.google.common.base.Function) (obj1)));
        } else
        {
            obj = new com.google.common.collect.tialList(((java.util.List) (obj)), ((com.google.common.base.Function) (obj1)));
        }
        obj1 = geableroomresponse.roomResponse.getRoomFlatList().rooms;
        if (geableroomresponse.roomResponse.getRoomFlatList() != null && !TextUtils.isEmpty(geableroomresponse.roomResponse.getRoomFlatList().pageToken))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        expandedlocationviewcontroller.setRooms(((java.util.List) (obj)), ((java.util.List) (obj1)), flag, expandedMeetingLocation.getAddedRoomEmails());
    }

    oomResponse()
    {
        this$0 = RoomBookingController.this;
        super();
    }
}
