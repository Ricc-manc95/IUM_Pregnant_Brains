// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            $AutoValue_RecurringTimes, RecurringTimes, SingleEventTime

final class AutoValue_RecurringTimes extends $AutoValue_RecurringTimes
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_RecurringTimes(SingleEventTime singleeventtime, String s, String s1, boolean flag, int i)
    {
        super(singleeventtime, s, s1, flag, i);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeParcelable(getFirstEventTime(), i);
        if (getTimezone() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getTimezone());
        }
        parcel.writeString(getRecurrenceRule());
        if (getConsiderExceptions())
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        parcel.writeInt(getRecurrenceOption());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            boolean flag = true;
            SingleEventTime singleeventtime = (SingleEventTime)parcel.readParcelable(com/google/android/calendar/timely/rooms/data/SingleEventTime.getClassLoader());
            String s;
            String s1;
            if (parcel.readInt() == 0)
            {
                s = parcel.readString();
            } else
            {
                s = null;
            }
            s1 = parcel.readString();
            if (parcel.readInt() != 1)
            {
                flag = false;
            }
            return new AutoValue_RecurringTimes(singleeventtime, s, s1, flag, parcel.readInt());
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_RecurringTimes[i];
        }

        _cls1()
        {
        }
    }

}
