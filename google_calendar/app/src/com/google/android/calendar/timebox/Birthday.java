// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timebox;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.event.EventKey;

public abstract class Birthday
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public Birthday()
    {
    }

    public static Builder builder()
    {
        return new AutoValue_Birthday.Builder();
    }

    public abstract CalendarKey calendarId();

    public int describeContents()
    {
        return 0;
    }

    public abstract String email();

    public abstract EventKey eventId();

    public abstract String fullName();

    public abstract boolean isBirthday();

    public abstract boolean isContactAvailable();

    public abstract boolean isGPlusUser();

    public abstract boolean isSelfBirthday();

    public abstract String originalTitle();

    public abstract String photoUrl();

    public abstract String profileId();

    public abstract String sourceAccount();

    public abstract Builder toBuilder();

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeParcelable(eventId(), i);
        parcel.writeParcelable(calendarId(), i);
        parcel.writeString(originalTitle());
        parcel.writeString(fullName());
        parcel.writeString(email());
        if (isContactAvailable())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        if (isSelfBirthday())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        if (isBirthday())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        if (isGPlusUser())
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        parcel.writeString(profileId());
        parcel.writeString(photoUrl());
        parcel.writeString(sourceAccount());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            boolean flag1 = true;
            Builder builder1 = Birthday.builder().eventId((EventKey)parcel.readParcelable(com/google/android/calendar/api/event/EventKey.getClassLoader())).calendarId((CalendarKey)parcel.readParcelable(com/google/android/calendar/api/calendarlist/CalendarKey.getClassLoader())).originalTitle(parcel.readString()).fullName(parcel.readString()).email(parcel.readString());
            boolean flag;
            if (parcel.readByte() != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            builder1 = builder1.isContactAvailable(flag);
            if (parcel.readByte() != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            builder1 = builder1.isSelfBirthday(flag);
            if (parcel.readByte() != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            builder1 = builder1.isBirthday(flag);
            if (parcel.readByte() != 0)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            return builder1.isGPlusUser(flag).profileId(parcel.readString()).photoUrl(parcel.readString()).sourceAccount(parcel.readString()).build();
        }

        public final Object[] newArray(int i)
        {
            return new Birthday[i];
        }

        _cls1()
        {
        }

        private class Builder
        {

            public abstract Birthday build();

            public abstract Builder calendarId(CalendarKey calendarkey);

            public abstract Builder email(String s);

            public abstract Builder eventId(EventKey eventkey);

            public abstract Builder fullName(String s);

            public abstract Builder isBirthday(boolean flag);

            public abstract Builder isContactAvailable(boolean flag);

            public abstract Builder isGPlusUser(boolean flag);

            public abstract Builder isSelfBirthday(boolean flag);

            public abstract Builder originalTitle(String s);

            public abstract Builder photoUrl(String s);

            public abstract Builder profileId(String s);

            public abstract Builder sourceAccount(String s);

            public Builder()
            {
            }
        }

    }

}
