// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            $AutoValue_CalendarKey, CalendarKey

final class AutoValue_CalendarKey extends $AutoValue_CalendarKey
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_CalendarKey(long l)
    {
        super(l);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(getLocalId());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_CalendarKey(parcel.readLong());
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_CalendarKey[i];
        }

        _cls1()
        {
        }
    }

}
