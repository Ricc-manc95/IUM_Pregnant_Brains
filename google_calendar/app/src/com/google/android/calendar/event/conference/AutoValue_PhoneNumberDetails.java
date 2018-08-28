// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.conference;

import android.os.Parcel;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.event.conference:
//            $AutoValue_PhoneNumberDetails, PhoneNumberDetails, Availability, LocalPhoneSource

public final class AutoValue_PhoneNumberDetails extends $AutoValue_PhoneNumberDetails
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_PhoneNumberDetails(String s, String s1, Locale locale, Availability availability, LocalPhoneSource localphonesource)
    {
        super(s, s1, locale, availability, localphonesource);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(phoneNumber());
        if (passCode() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(passCode());
        }
        parcel.writeSerializable(region());
        parcel.writeString(availability().name());
        if (source() == null)
        {
            parcel.writeInt(1);
            return;
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(source().name());
            return;
        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            LocalPhoneSource localphonesource = null;
            String s1 = parcel.readString();
            String s;
            Locale locale;
            Availability availability;
            if (parcel.readInt() == 0)
            {
                s = parcel.readString();
            } else
            {
                s = null;
            }
            locale = (Locale)parcel.readSerializable();
            availability = Availability.valueOf(parcel.readString());
            if (parcel.readInt() == 0)
            {
                localphonesource = LocalPhoneSource.valueOf(parcel.readString());
            }
            return new AutoValue_PhoneNumberDetails(s1, s, locale, availability, localphonesource);
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_PhoneNumberDetails[i];
        }

        _cls1()
        {
        }
    }

}
