// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import android.text.TextUtils;
import com.google.android.calendar.timely.net.pagination.PageableResult;
import com.google.android.calendar.timely.rooms.data.RoomFlatList;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.timely.rooms.net:
//            RoomsRendezvousClient, RoomResponse, AutoValue_RoomResponse

public static final class roomResponse
    implements PageableResult
{

    public final RoomResponse roomResponse;

    public final boolean hasNextPage()
    {
        return roomResponse.getRoomFlatList() != null && !TextUtils.isEmpty(roomResponse.getRoomFlatList().pageToken);
    }

    public final Object merge(Object obj)
    {
        Object obj1 = (roomResponse)obj;
        if (roomResponse.getRoomFlatList() == null || ((roomResponse) (obj1)).roomResponse.getRoomFlatList() == null)
        {
            return this;
        } else
        {
            obj = roomResponse;
            obj1 = ((roomResponse) (obj1)).roomResponse;
            Object obj2 = ((RoomResponse) (obj)).getRoomFlatList();
            RoomFlatList roomflatlist = ((RoomResponse) (obj1)).getRoomFlatList();
            obj2 = new ArrayList(((RoomFlatList) (obj2)).rooms);
            ((ArrayList) (obj2)).addAll(roomflatlist.rooms);
            return new <init>(new AutoValue_RoomResponse(new RoomFlatList(((java.util.List) (obj2)), roomflatlist.pageToken), ((RoomResponse) (obj)).getRoomHierarchy(), ((RoomResponse) (obj)).getRoomRecommendations(), ((RoomResponse) (obj1)).getResponseId(), ((RoomResponse) (obj1)).queryMatchesRooms(), ((RoomResponse) (obj1)).getResolvedSelectedRooms()));
        }
    }

    public (RoomResponse roomresponse)
    {
        if (roomresponse == null)
        {
            throw new NullPointerException();
        } else
        {
            roomResponse = (RoomResponse)roomresponse;
            return;
        }
    }
}
