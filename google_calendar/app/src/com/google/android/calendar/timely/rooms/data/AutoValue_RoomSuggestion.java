// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            $AutoValue_RoomSuggestion, RoomSuggestion, Room

public final class AutoValue_RoomSuggestion extends $AutoValue_RoomSuggestion
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_RoomSuggestion(Room room)
    {
        super(room);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(getRoom(), i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_RoomSuggestion((Room)parcel.readParcelable(com/google/android/calendar/timely/rooms/data/Room.getClassLoader()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_RoomSuggestion[i];
        }

        _cls1()
        {
        }
    }

}
