// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.userstatus;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event.userstatus:
//            $AutoValue_UserStatus, UserStatus, OutOfOffice

public final class AutoValue_UserStatus extends $AutoValue_UserStatus
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_UserStatus(OutOfOffice outofoffice)
    {
        super(outofoffice);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(getOutOfOffice(), i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_UserStatus((OutOfOffice)parcel.readParcelable(com/google/android/calendar/api/event/userstatus/OutOfOffice.getClassLoader()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_UserStatus[i];
        }

        _cls1()
        {
        }
    }

}
