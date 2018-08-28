// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.timely.rooms.net:
//            $AutoValue_RoomListingParams, RoomListingParams

public final class AutoValue_RoomListingParams extends $AutoValue_RoomListingParams
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_RoomListingParams(boolean flag, Integer integer, boolean flag1, String s)
    {
        super(flag, integer, flag1, s);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        if (isPreferHierarchy())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (getMaxPageSize() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeInt(getMaxPageSize().intValue());
        }
        if (isShowUnavailable())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (getPageToken() == null)
        {
            parcel.writeInt(1);
            return;
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getPageToken());
            return;
        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            String s = null;
            boolean flag1 = true;
            Integer integer;
            boolean flag;
            if (parcel.readInt() == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (parcel.readInt() == 0)
            {
                integer = Integer.valueOf(parcel.readInt());
            } else
            {
                integer = null;
            }
            if (parcel.readInt() != 1)
            {
                flag1 = false;
            }
            if (parcel.readInt() == 0)
            {
                s = parcel.readString();
            }
            return new AutoValue_RoomListingParams(flag, integer, flag1, s);
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_RoomListingParams[i];
        }

        _cls1()
        {
        }
    }

}
