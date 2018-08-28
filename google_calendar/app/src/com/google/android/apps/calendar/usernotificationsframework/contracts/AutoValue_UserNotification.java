// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.contracts;

import android.os.Parcel;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.contracts:
//            $AutoValue_UserNotification, UserNotification

public final class AutoValue_UserNotification extends $AutoValue_UserNotification
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_UserNotification(int i, String s, int j, long l, long l1)
    {
        super(i, s, j, l, l1);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(getPluginId());
        parcel.writeString(getEntityFingerprint());
        parcel.writeInt(getType());
        parcel.writeLong(getTriggerMillis());
        parcel.writeLong(getExpirationMillis());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_UserNotification(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readLong());
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_UserNotification[i];
        }

        _cls1()
        {
        }
    }

}
