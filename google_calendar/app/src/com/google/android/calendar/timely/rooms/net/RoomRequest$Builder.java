// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import com.google.android.calendar.timely.rooms.data.CalendarEvent;
import com.google.android.calendar.timely.rooms.data.RecurringTimes;
import com.google.android.calendar.timely.rooms.data.RoomHierarchyNode;
import com.google.android.calendar.timely.rooms.data.SingleEventTime;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.net:
//            RoomRequest, RoomListingParams, RoomRecommendationsParams

public abstract class arams
{

    public abstract RoomRequest build();

    public abstract arams setAttendees(ImmutableList immutablelist);

    public abstract arams setBuildingId(String s);

    public abstract arams setCalendarEvent(CalendarEvent calendarevent);

    public abstract arams setCalendarEventReference(String s);

    public abstract arams setHierarchyNode(RoomHierarchyNode roomhierarchynode);

    public abstract arams setListingParams(RoomListingParams roomlistingparams);

    public abstract arams setQuery(String s);

    public abstract arams setRecommendationsParams(RoomRecommendationsParams roomrecommendationsparams);

    public abstract arams setRecurringTimes(RecurringTimes recurringtimes);

    public abstract arams setSelectedRooms(ImmutableList immutablelist);

    public abstract arams setSingleEventTime(SingleEventTime singleeventtime);

    public arams()
    {
    }
}
