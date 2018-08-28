// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            $AutoValue_CalendarEvent, CalendarEvent

public final class AutoValue_CalendarEvent extends $AutoValue_CalendarEvent
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_CalendarEvent(String s, String s1)
    {
        super(s, s1);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(getCalendarId());
        if (getEventId() == null)
        {
            parcel.writeInt(1);
            return;
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getEventId());
            return;
        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            String s = parcel.readString();
            if (parcel.readInt() == 0)
            {
                parcel = parcel.readString();
            } else
            {
                parcel = null;
            }
            return new AutoValue_CalendarEvent(s, parcel);
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_CalendarEvent[i];
        }

        _cls1()
        {
        }
    }

}
