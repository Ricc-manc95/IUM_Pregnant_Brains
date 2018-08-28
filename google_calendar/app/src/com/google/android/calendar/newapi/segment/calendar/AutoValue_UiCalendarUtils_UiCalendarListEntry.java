// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import android.os.Parcel;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;

// Referenced classes of package com.google.android.calendar.newapi.segment.calendar:
//            $AutoValue_UiCalendarUtils_UiCalendarListEntry

final class AutoValue_UiCalendarUtils_UiCalendarListEntry extends $AutoValue_UiCalendarUtils_UiCalendarListEntry
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_UiCalendarUtils_UiCalendarListEntry(String s, String s1, int i, CalendarListEntry calendarlistentry)
    {
        super(s, s1, i, calendarlistentry);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(super.displayName);
        parcel.writeString(super.accountName);
        parcel.writeInt(super.color);
        parcel.writeParcelable(value(), i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_UiCalendarUtils_UiCalendarListEntry(parcel.readString(), parcel.readString(), parcel.readInt(), (CalendarListEntry)parcel.readParcelable(com/google/android/calendar/api/calendarlist/CalendarListEntry.getClassLoader()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_UiCalendarUtils_UiCalendarListEntry[i];
        }

        _cls1()
        {
        }
    }

}
