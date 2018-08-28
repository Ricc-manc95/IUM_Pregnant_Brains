// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event:
//            $AutoValue_CpEventKey, CpEventKey

final class AutoValue_CpEventKey extends $AutoValue_CpEventKey
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_CpEventKey(boolean flag, long l, long l1)
    {
        super(flag, l, l1);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        if (hasStartMillis())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        parcel.writeLong(startMillis());
        parcel.writeLong(localId());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            boolean flag = true;
            if (parcel.readInt() != 1)
            {
                flag = false;
            }
            return new AutoValue_CpEventKey(flag, parcel.readLong(), parcel.readLong());
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_CpEventKey[i];
        }

        _cls1()
        {
        }
    }

}
