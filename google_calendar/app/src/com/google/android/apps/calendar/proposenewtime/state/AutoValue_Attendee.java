// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.state;

import android.os.Parcel;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.state:
//            $AutoValue_Attendee, Attendee, TimeProposal

final class AutoValue_Attendee extends $AutoValue_Attendee
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_Attendee(String s, String s1, String s2, boolean flag, TimeProposal timeproposal)
    {
        super(s, s1, s2, flag, timeproposal);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(getSourceAccount());
        parcel.writeString(getEmail());
        parcel.writeString(getDisplayName());
        int j;
        if (isDisabled())
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        parcel.writeParcelable(getProposal(), i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            boolean flag = true;
            String s = parcel.readString();
            String s1 = parcel.readString();
            String s2 = parcel.readString();
            if (parcel.readInt() != 1)
            {
                flag = false;
            }
            return new AutoValue_Attendee(s, s1, s2, flag, (TimeProposal)parcel.readParcelable(com/google/android/apps/calendar/proposenewtime/state/TimeProposal.getClassLoader()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_Attendee[i];
        }

        _cls1()
        {
        }
    }

}
