// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee.fullscreen;

import android.os.Parcel;
import com.google.android.calendar.avatar.ContactInfo;

// Referenced classes of package com.google.android.calendar.newapi.segment.attendee.fullscreen:
//            $AutoValue_AttendeeContact, AttendeeContact

final class AutoValue_AttendeeContact extends $AutoValue_AttendeeContact
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_AttendeeContact(ContactInfo contactinfo, AttendeeContact.Type type)
    {
        super(contactinfo, type);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(getContact(), i);
        parcel.writeString(getType().name());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_AttendeeContact((ContactInfo)parcel.readParcelable(com/google/android/calendar/avatar/ContactInfo.getClassLoader()), AttendeeContact.Type.valueOf(parcel.readString()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_AttendeeContact[i];
        }

        _cls1()
        {
        }
    }

}