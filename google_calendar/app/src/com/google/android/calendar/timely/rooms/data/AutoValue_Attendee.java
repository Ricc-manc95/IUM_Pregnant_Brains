// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            $AutoValue_Attendee, Attendee

public final class AutoValue_Attendee extends $AutoValue_Attendee
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_Attendee(String s, String s1, boolean flag, com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus)
    {
        super(s, s1, flag, responsestatus);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        i = 1;
        parcel.writeString(getEmail());
        if (getDisplayName() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getDisplayName());
        }
        if (!isOrganizer())
        {
            i = 0;
        }
        parcel.writeInt(i);
        parcel.writeString(getResponseStatus().name());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            boolean flag = true;
            String s1 = parcel.readString();
            String s;
            if (parcel.readInt() == 0)
            {
                s = parcel.readString();
            } else
            {
                s = null;
            }
            if (parcel.readInt() != 1)
            {
                flag = false;
            }
            return new AutoValue_Attendee(s1, s, flag, com.google.android.calendar.api.event.attendee.Response.ResponseStatus.valueOf(parcel.readString()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_Attendee[i];
        }

        _cls1()
        {
        }
    }

}
