// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.calendar.timely.net.pagination.PageableRequest;
import com.google.android.calendar.timely.rooms.data.CalendarEvent;
import com.google.android.calendar.timely.rooms.data.RecurringTimes;
import com.google.android.calendar.timely.rooms.data.RoomFlatList;
import com.google.android.calendar.timely.rooms.data.RoomHierarchyNode;
import com.google.android.calendar.timely.rooms.data.SingleEventTime;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.net:
//            RoomResponse, RoomListingParams, RoomRecommendationsParams

public abstract class RoomRequest
    implements Parcelable, PageableRequest
{

    public RoomRequest()
    {
    }

    public abstract ImmutableList getAttendees();

    public abstract String getBuildingId();

    public abstract CalendarEvent getCalendarEvent();

    public abstract String getCalendarEventReference();

    public abstract RoomHierarchyNode getHierarchyNode();

    public abstract RoomListingParams getListingParams();

    public abstract String getQuery();

    public abstract RoomRecommendationsParams getRecommendationsParams();

    public abstract RecurringTimes getRecurringTimes();

    public abstract ImmutableList getSelectedRooms();

    public abstract SingleEventTime getSingleEventTime();

    public abstract Builder toBuilder();

    public final Object withTokenFrom(Object obj)
    {
        Object obj1 = (RoomsRendezvousClient.PageableRoomResponse)obj;
        boolean flag;
        if (((RoomsRendezvousClient.PageableRoomResponse) (obj1)).roomResponse.getRoomFlatList() != null && !TextUtils.isEmpty(((RoomsRendezvousClient.PageableRoomResponse) (obj1)).roomResponse.getRoomFlatList().pageToken))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return this;
        } else
        {
            obj = toBuilder();
            RoomListingParams roomlistingparams = getListingParams();
            obj1 = ((RoomsRendezvousClient.PageableRoomResponse) (obj1)).roomResponse.getRoomFlatList().pageToken;
            return ((Builder) (obj)).setListingParams(roomlistingparams.toBuilder().setPageToken(((String) (obj1))).build()).build();
        }
    }

    private class Builder
    {

        public abstract RoomRequest build();

        public abstract Builder setAttendees(ImmutableList immutablelist);

        public abstract Builder setBuildingId(String s);

        public abstract Builder setCalendarEvent(CalendarEvent calendarevent);

        public abstract Builder setCalendarEventReference(String s);

        public abstract Builder setHierarchyNode(RoomHierarchyNode roomhierarchynode);

        public abstract Builder setListingParams(RoomListingParams roomlistingparams);

        public abstract Builder setQuery(String s);

        public abstract Builder setRecommendationsParams(RoomRecommendationsParams roomrecommendationsparams);

        public abstract Builder setRecurringTimes(RecurringTimes recurringtimes);

        public abstract Builder setSelectedRooms(ImmutableList immutablelist);

        public abstract Builder setSingleEventTime(SingleEventTime singleeventtime);

        public Builder()
        {
        }
    }

}
