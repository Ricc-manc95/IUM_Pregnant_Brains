// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event:
//            $AutoValue_GooglePrivateData, GooglePrivateData

final class AutoValue_GooglePrivateData extends $AutoValue_GooglePrivateData
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_GooglePrivateData(GooglePrivateData.GuestNotification guestnotification, boolean flag, boolean flag1)
    {
        super(guestnotification, flag, flag1);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeString(getGuestNotification().name());
        if (hasEveryoneDeclined())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (isEveryoneDeclinedDismissed())
        {
            i = ((flag) ? 1 : 0);
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
            boolean flag1 = true;
            GooglePrivateData.GuestNotification guestnotification = GooglePrivateData.GuestNotification.valueOf(parcel.readString());
            boolean flag;
            if (parcel.readInt() == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (parcel.readInt() != 1)
            {
                flag1 = false;
            }
            return new AutoValue_GooglePrivateData(guestnotification, flag, flag1);
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_GooglePrivateData[i];
        }

        _cls1()
        {
        }
    }

}
