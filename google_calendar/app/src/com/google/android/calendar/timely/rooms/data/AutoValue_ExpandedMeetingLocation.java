// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import android.os.Parcel;
import com.google.android.calendar.utils.datatypes.ImmutableListAdapter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            $AutoValue_ExpandedMeetingLocation, ExpandedMeetingLocation

public final class AutoValue_ExpandedMeetingLocation extends $AutoValue_ExpandedMeetingLocation
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public static final ImmutableListAdapter IMMUTABLE_LIST_ADAPTER = new ImmutableListAdapter();

    public AutoValue_ExpandedMeetingLocation(String s, String s1, ImmutableList immutablelist, ImmutableList immutablelist1, ImmutableSet immutableset)
    {
        super(s, s1, immutablelist, immutablelist1, immutableset);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        if (getBuildingName() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getBuildingName());
        }
        if (getBuildingId() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getBuildingId());
        }
        parcel.writeList(getRoomSuggestions());
        parcel.writeList(getAttendees());
        if (getAddedRoomEmails() == null)
        {
            parcel.writeInt(1);
            return;
        } else
        {
            parcel.writeInt(0);
            parcel.writeSerializable(getAddedRoomEmails());
            return;
        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            ImmutableSet immutableset = null;
            String s;
            String s1;
            ImmutableList immutablelist;
            ImmutableList immutablelist1;
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
            } else
            {
                s1 = null;
            }
            immutablelist = ImmutableList.copyOf(parcel.readArrayList(com/google/android/calendar/utils/datatypes/ImmutableListAdapter.getClassLoader()));
            immutablelist1 = ImmutableList.copyOf(parcel.readArrayList(com/google/android/calendar/utils/datatypes/ImmutableListAdapter.getClassLoader()));
            if (parcel.readInt() == 0)
            {
                immutableset = (ImmutableSet)parcel.readSerializable();
            }
            return new AutoValue_ExpandedMeetingLocation(s, s1, immutablelist, immutablelist1, immutableset);
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_ExpandedMeetingLocation[i];
        }

        _cls1()
        {
        }
    }

}
