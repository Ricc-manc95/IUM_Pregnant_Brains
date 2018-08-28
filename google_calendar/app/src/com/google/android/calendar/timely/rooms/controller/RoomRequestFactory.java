// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import android.content.Intent;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.calendar.timely.rooms.data.AutoValue_CalendarEvent;
import com.google.android.calendar.timely.rooms.data.AutoValue_SingleEventTime;
import com.google.android.calendar.timely.rooms.data.RecurringTimes;
import com.google.android.calendar.timely.rooms.data.RoomBookingFilterParams;
import com.google.android.calendar.timely.rooms.data.RoomHierarchyNode;
import com.google.android.calendar.timely.rooms.net.AutoValue_RoomListingParams;
import com.google.android.calendar.timely.rooms.net.AutoValue_RoomRecommendationsParams;
import com.google.android.calendar.timely.rooms.net.RoomListingParams;
import com.google.android.calendar.timely.rooms.net.RoomRequest;
import com.google.common.collect.ImmutableList;
import java.util.List;

public class RoomRequestFactory
{

    private static final String DEFAULT_PAGE_TOKEN = null;
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/rooms/controller/RoomRequestFactory);

    public RoomRequestFactory()
    {
    }

    static RoomRequest applyFilterParams(RoomRequest roomrequest, RoomBookingFilterParams roombookingfilterparams)
    {
        com.google.android.calendar.timely.rooms.net.RoomRequest.Builder builder = roomrequest.toBuilder();
        RoomListingParams roomlistingparams = roomrequest.getListingParams();
        boolean flag;
        if (!roombookingfilterparams.showOnlyAvailable())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        builder = builder.setListingParams(roomlistingparams.toBuilder().setShowUnavailable(flag).build());
        if (roombookingfilterparams.getRecurrenceOption() == null)
        {
            roomrequest = null;
        } else
        {
            roomrequest = roomrequest.getRecurringTimes();
            int i = roombookingfilterparams.getRecurrenceOption().intValue();
            roomrequest = roomrequest.toBuilder().setRecurrenceOption(i).build();
        }
        return builder.setRecurringTimes(roomrequest).build();
    }

    public static RoomRequest fromIntent(Intent intent)
    {
        Object obj;
        Object obj1;
        java.util.ArrayList arraylist;
        java.util.ArrayList arraylist1;
        java.util.ArrayList arraylist2;
        AutoValue_RoomListingParams autovalue_roomlistingparams = new AutoValue_RoomListingParams(true, Integer.valueOf(50), false, null);
        if (!intent.hasExtra("key_start_time") || !intent.hasExtra("key_all_day"))
        {
            throw new IllegalArgumentException("Intent must contain start time and all day.");
        }
        AutoValue_SingleEventTime autovalue_singleeventtime;
        if (intent.hasExtra("key_end_time"))
        {
            obj = Long.valueOf(intent.getLongExtra("key_end_time", 0L));
        } else
        {
            obj = null;
        }
        if (intent.hasExtra("key_all_day"))
        {
            obj1 = Boolean.valueOf(intent.getBooleanExtra("key_all_day", false));
        } else
        {
            obj1 = null;
        }
        autovalue_singleeventtime = new AutoValue_SingleEventTime(intent.getLongExtra("key_start_time", 0L), ((Long) (obj)), ((Boolean) (obj1)));
        obj1 = intent.getStringExtra("key_event_id");
        obj = intent.getStringExtra("key_recurrence_rule");
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = (new com.google.android.calendar.timely.rooms.data..AutoValue_RecurringTimes.Builder()).setFirstEventTime(autovalue_singleeventtime).setRecurrenceRule(((String) (obj))).setTimezone(intent.getStringExtra("key_timezone"));
            int i;
            boolean flag;
            if (obj1 == null)
            {
                i = 1;
            } else
            {
                i = 2;
            }
            obj = ((com.google.android.calendar.timely.rooms.data.RecurringTimes.Builder) (obj)).setRecurrenceOption(i);
            if (obj1 != null && intent.getBooleanExtra("key_consider_exceptions", false))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            obj = ((com.google.android.calendar.timely.rooms.data.RecurringTimes.Builder) (obj)).setConsiderExceptions(flag).build();
        }
        if (!intent.hasExtra("key_calendar_id"))
        {
            throw new IllegalArgumentException("Intent must contain calendar ID.");
        }
        AutoValue_CalendarEvent autovalue_calendarevent = new AutoValue_CalendarEvent(intent.getStringExtra("key_calendar_id"), ((String) (obj1)));
        ImmutableList immutablelist = ImmutableList.copyOf(intent.getParcelableArrayListExtra("key_attendees"));
        arraylist = intent.getStringArrayListExtra("key_selected_room_emails");
        arraylist1 = intent.getStringArrayListExtra("key_selected_room_names");
        arraylist2 = intent.getIntegerArrayListExtra("key_selected_room_availabilities");
        obj1 = intent.getStringExtra("key_calendar_event_reference");
        obj = (new com.google.android.calendar.timely.rooms.net..AutoValue_RoomRequest.Builder()).setQuery("").setRecommendationsParams(new AutoValue_RoomRecommendationsParams(5, false, RemoteFeatureConfig.SRB.enabled(), ImmutableList.of())).setListingParams(autovalue_roomlistingparams).setSingleEventTime(autovalue_singleeventtime).setRecurringTimes(((RecurringTimes) (obj))).setCalendarEvent(autovalue_calendarevent).setAttendees(immutablelist);
        if (arraylist != null || arraylist1 != null || arraylist2 != null) goto _L2; else goto _L1
_L1:
        intent = null;
_L8:
        return ((com.google.android.calendar.timely.rooms.net.RoomRequest.Builder) (obj)).setSelectedRooms(intent).setHierarchyNode(null).setCalendarEventReference(((String) (obj1))).build();
_L2:
        int j;
        if (arraylist == null || arraylist1 == null || arraylist2 == null)
        {
            LogUtils.wtf(TAG, "Intent must contain either none or all of the following: selected room emails, names and availabilities.", new Object[0]);
            intent = null;
            continue; /* Loop/switch isn't completed */
        }
        intent = ImmutableList.builder();
        j = 0;
_L4:
        Object obj2;
        Integer integer;
        int k;
        if (j >= arraylist.size())
        {
            break MISSING_BLOCK_LABEL_571;
        }
        obj2 = (new com.google.android.calendar.timely.rooms.data..AutoValue_Room.Builder()).setAvailability(0).setFeatures(ImmutableList.of()).setCategory(0).setEmail((String)arraylist.get(j)).setName((String)arraylist1.get(j));
        integer = (Integer)arraylist2.get(j);
        switch (integer.intValue())
        {
        default:
            k = 0;
            break;

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
            break; /* Loop/switch isn't completed */
        }
_L5:
        obj2 = (com.google.common.collect.ImmutableList.Builder)intent.add(((com.google.android.calendar.timely.rooms.data.Room.Builder) (obj2)).setAvailability(k).build());
        j++;
        if (true) goto _L4; else goto _L3
_L3:
        k = integer.intValue();
          goto _L5
        if (true) goto _L4; else goto _L6
_L6:
        intent.forceCopy = true;
        intent = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (intent)).contents, ((com.google.common.collect.ImmutableList.Builder) (intent)).size);
        if (true) goto _L8; else goto _L7
_L7:
    }

    static RoomRequest hierarchyNodesRequest(RoomRequest roomrequest)
    {
        boolean flag;
        if (true)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return withParams(roomrequest, "", null, null, flag, null);
    }

    static RoomRequest hierarchyViewRequest(RoomRequest roomrequest, RoomHierarchyNode roomhierarchynode)
    {
        boolean flag;
        if (roomhierarchynode == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return withParams(roomrequest, "", roomhierarchynode, null, flag, null);
    }

    public static RoomRequest roomsInLocationRequest(RoomRequest roomrequest, String s)
    {
        roomrequest = roomrequest.toBuilder().setSelectedRooms(ImmutableList.of()).build();
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            return withParams(roomrequest, "", null, (String)s, false, Boolean.valueOf(true));
        }
    }

    static RoomRequest searchRequest(RoomRequest roomrequest, String s)
    {
        return withParams(roomrequest, s, null, null, false, null);
    }

    private static RoomRequest withParams(RoomRequest roomrequest, String s, RoomHierarchyNode roomhierarchynode, String s1, boolean flag, Boolean boolean1)
    {
        com.google.android.calendar.timely.rooms.net.RoomListingParams.Builder builder = roomrequest.getListingParams().toBuilder().setPreferHierarchy(flag);
        if (boolean1 != null)
        {
            builder.setShowUnavailable(boolean1.booleanValue());
        }
        return roomrequest.toBuilder().setQuery(s).setHierarchyNode(roomhierarchynode).setBuildingId(s1).setListingParams(builder.build()).build();
    }

}
