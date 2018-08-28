// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import android.accounts.Account;
import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.newapi.segment.calendar:
//            $AutoValue_UiCalendarUtils_UiReminderCalendar

final class AutoValue_UiCalendarUtils_UiReminderCalendar extends $AutoValue_UiCalendarUtils_UiReminderCalendar
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_UiCalendarUtils_UiReminderCalendar(String s, String s1, int i, Account account)
    {
        super(s, s1, i, account);
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
            return new AutoValue_UiCalendarUtils_UiReminderCalendar(parcel.readString(), parcel.readString(), parcel.readInt(), (Account)parcel.readParcelable(android/accounts/Account.getClassLoader()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_UiCalendarUtils_UiReminderCalendar[i];
        }

        _cls1()
        {
        }
    }

}
