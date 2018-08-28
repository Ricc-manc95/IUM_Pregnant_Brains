// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            $AutoValue_SingleEventTime, SingleEventTime

public final class AutoValue_SingleEventTime extends $AutoValue_SingleEventTime
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_SingleEventTime(long l, Long long1, Boolean boolean1)
    {
        super(l, long1, boolean1);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        i = 1;
        parcel.writeLong(getStart());
        if (getEnd() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeLong(getEnd().longValue());
        }
        if (getAllDay() != null) goto _L2; else goto _L1
_L1:
        parcel.writeInt(i);
        return;
_L2:
        parcel.writeInt(0);
        if (!getAllDay().booleanValue())
        {
            i = 0;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            Boolean boolean1 = null;
            long l = parcel.readLong();
            Long long1;
            if (parcel.readInt() == 0)
            {
                long1 = Long.valueOf(parcel.readLong());
            } else
            {
                long1 = null;
            }
            if (parcel.readInt() == 0)
            {
                boolean flag;
                if (parcel.readInt() == 1)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                boolean1 = Boolean.valueOf(flag);
            }
            return new AutoValue_SingleEventTime(l, long1, boolean1);
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_SingleEventTime[i];
        }

        _cls1()
        {
        }
    }

}
