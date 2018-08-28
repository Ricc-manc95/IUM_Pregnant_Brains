// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.userstatus;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event.userstatus:
//            $AutoValue_OutOfOffice, OutOfOffice, AutoReply

final class AutoValue_OutOfOffice extends $AutoValue_OutOfOffice
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_OutOfOffice(boolean flag, String s, AutoReply autoreply)
    {
        super(flag, s, autoreply);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        int j;
        if (isAutoDeclineEnabled())
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        if (getCalendarDeclineMessage() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getCalendarDeclineMessage());
        }
        parcel.writeParcelable(getAutoReply(), i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            String s;
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
                s = parcel.readString();
            } else
            {
                s = null;
            }
            return new AutoValue_OutOfOffice(flag, s, (AutoReply)parcel.readParcelable(com/google/android/calendar/api/event/userstatus/AutoReply.getClassLoader()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_OutOfOffice[i];
        }

        _cls1()
        {
        }
    }

}
