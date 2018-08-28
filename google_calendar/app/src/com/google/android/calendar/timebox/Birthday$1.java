// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timebox;

import android.os.Parcel;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.event.EventKey;

// Referenced classes of package com.google.android.calendar.timebox:
//            Birthday

final class lendarKey
    implements android.os.Creator
{

    public final Object createFromParcel(Parcel parcel)
    {
        boolean flag1 = true;
        ilder ilder = Birthday.builder().eventId((EventKey)parcel.readParcelable(com/google/android/calendar/api/event/EventKey.getClassLoader())).calendarId((CalendarKey)parcel.readParcelable(com/google/android/calendar/api/calendarlist/CalendarKey.getClassLoader())).originalTitle(parcel.readString()).fullName(parcel.readString()).email(parcel.readString());
        boolean flag;
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ilder = ilder.isContactAvailable(flag);
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ilder = ilder.isSelfBirthday(flag);
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ilder = ilder.isBirthday(flag);
        if (parcel.readByte() != 0)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        return ilder.isGPlusUser(flag).profileId(parcel.readString()).photoUrl(parcel.readString()).sourceAccount(parcel.readString()).build();
    }

    public final Object[] newArray(int i)
    {
        return new Birthday[i];
    }

    lendarKey()
    {
    }
}
