// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import android.os.Parcel;
import com.google.android.calendar.timely.rooms.data.CalendarEvent;
import com.google.android.calendar.timely.rooms.data.RecurringTimes;
import com.google.android.calendar.timely.rooms.data.RoomHierarchyNode;
import com.google.android.calendar.timely.rooms.data.SingleEventTime;
import com.google.android.calendar.utils.datatypes.ImmutableListAdapter;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.net:
//            $AutoValue_RoomRequest, RoomRequest, RoomRecommendationsParams, RoomListingParams

final class AutoValue_RoomRequest extends $AutoValue_RoomRequest
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public static final ImmutableListAdapter IMMUTABLE_LIST_ADAPTER = new ImmutableListAdapter();

    AutoValue_RoomRequest(String s, RoomRecommendationsParams roomrecommendationsparams, RoomListingParams roomlistingparams, SingleEventTime singleeventtime, RecurringTimes recurringtimes, CalendarEvent calendarevent, ImmutableList immutablelist, 
            ImmutableList immutablelist1, RoomHierarchyNode roomhierarchynode, String s1, String s2)
    {
        super(s, roomrecommendationsparams, roomlistingparams, singleeventtime, recurringtimes, calendarevent, immutablelist, immutablelist1, roomhierarchynode, s1, s2);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(getQuery());
        parcel.writeParcelable(getRecommendationsParams(), i);
        parcel.writeParcelable(getListingParams(), i);
        parcel.writeParcelable(getSingleEventTime(), i);
        parcel.writeParcelable(getRecurringTimes(), i);
        parcel.writeParcelable(getCalendarEvent(), i);
        parcel.writeList(getAttendees());
        parcel.writeList(getSelectedRooms());
        parcel.writeParcelable(getHierarchyNode(), i);
        if (getBuildingId() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getBuildingId());
        }
        if (getCalendarEventReference() == null)
        {
            parcel.writeInt(1);
            return;
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getCalendarEventReference());
            return;
        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            String s1 = null;
            String s2 = parcel.readString();
            RoomRecommendationsParams roomrecommendationsparams = (RoomRecommendationsParams)parcel.readParcelable(com/google/android/calendar/timely/rooms/net/RoomRecommendationsParams.getClassLoader());
            RoomListingParams roomlistingparams = (RoomListingParams)parcel.readParcelable(com/google/android/calendar/timely/rooms/net/RoomListingParams.getClassLoader());
            SingleEventTime singleeventtime = (SingleEventTime)parcel.readParcelable(com/google/android/calendar/timely/rooms/data/SingleEventTime.getClassLoader());
            RecurringTimes recurringtimes = (RecurringTimes)parcel.readParcelable(com/google/android/calendar/timely/rooms/data/RecurringTimes.getClassLoader());
            CalendarEvent calendarevent = (CalendarEvent)parcel.readParcelable(com/google/android/calendar/timely/rooms/data/CalendarEvent.getClassLoader());
            ImmutableList immutablelist = ImmutableList.copyOf(parcel.readArrayList(com/google/android/calendar/utils/datatypes/ImmutableListAdapter.getClassLoader()));
            ImmutableList immutablelist1 = ImmutableList.copyOf(parcel.readArrayList(com/google/android/calendar/utils/datatypes/ImmutableListAdapter.getClassLoader()));
            RoomHierarchyNode roomhierarchynode = (RoomHierarchyNode)parcel.readParcelable(com/google/android/calendar/timely/rooms/data/RoomHierarchyNode.getClassLoader());
            String s;
            if (parcel.readInt() == 0)
            {
                s = parcel.readString();
            } else
            {
                s = null;
            }
            if (parcel.readInt() == 0)
            {
                s1 = parcel.readString();
            }
            return new AutoValue_RoomRequest(s2, roomrecommendationsparams, roomlistingparams, singleeventtime, recurringtimes, calendarevent, immutablelist, immutablelist1, roomhierarchynode, s, s1);
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_RoomRequest[i];
        }

        _cls1()
        {
        }
    }

}
