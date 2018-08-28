// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.emailcommon.provider;

import android.os.Parcel;
import android.os.Parcelable;

public final class RecipientAvailability
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public String displayName;
    public String emailAddress;
    public String mergedFreeBusy;
    public int type;

    public RecipientAvailability()
    {
    }

    public final int describeContents()
    {
        return 0;
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("RecipientAvailability{type=").append(type).append(" emailAddress=").append(emailAddress).append(" displayName=").append(displayName).append(" mergedFreeBusy=").append(mergedFreeBusy).append("}");
        return stringbuilder.toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(type);
        parcel.writeString(emailAddress);
        parcel.writeString(displayName);
        parcel.writeString(mergedFreeBusy);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            RecipientAvailability recipientavailability = new RecipientAvailability();
            recipientavailability.type = parcel.readInt();
            recipientavailability.emailAddress = parcel.readString();
            recipientavailability.displayName = parcel.readString();
            recipientavailability.mergedFreeBusy = parcel.readString();
            return recipientavailability;
        }

        public final Object[] newArray(int i)
        {
            return new RecipientAvailability[i];
        }

        _cls1()
        {
        }
    }

}
