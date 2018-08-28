// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import android.accounts.Account;
import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate:
//            $AutoValue_QuickCreateSession, QuickCreateSession, QuickCreateType

final class AutoValue_QuickCreateSession extends $AutoValue_QuickCreateSession
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_QuickCreateSession(Account account, String s, QuickCreateType quickcreatetype)
    {
        super(account, s, quickcreatetype);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(getAccount(), i);
        parcel.writeString(getId());
        parcel.writeString(getType().name());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_QuickCreateSession((Account)parcel.readParcelable(android/accounts/Account.getClassLoader()), parcel.readString(), QuickCreateType.valueOf(parcel.readString()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_QuickCreateSession[i];
        }

        _cls1()
        {
        }
    }

}
