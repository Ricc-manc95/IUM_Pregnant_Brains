// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.newapi.screen.event:
//            $AutoValue_EventDuration, EventDuration

public final class AutoValue_EventDuration extends $AutoValue_EventDuration
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_EventDuration(long l, boolean flag)
    {
        super(l, flag);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(getMillis());
        if (isEndTimeUnspecified())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            boolean flag = true;
            long l = parcel.readLong();
            if (parcel.readInt() != 1)
            {
                flag = false;
            }
            return new AutoValue_EventDuration(l, flag);
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_EventDuration[i];
        }

        _cls1()
        {
        }
    }

}
