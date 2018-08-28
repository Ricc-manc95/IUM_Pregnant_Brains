// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics.internal;

import android.os.Parcel;
import android.os.Parcelable;

public class Command
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public String id;
    public String urlParam;
    public String value;

    public Command()
    {
    }

    Command(Parcel parcel)
    {
        id = parcel.readString();
        urlParam = parcel.readString();
        value = parcel.readString();
    }

    public Command(String s, String s1, String s2)
    {
        id = s;
        urlParam = s1;
        value = s2;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(id);
        parcel.writeString(urlParam);
        parcel.writeString(value);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new Command(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new Command[i];
        }

        _cls1()
        {
        }
    }

}
