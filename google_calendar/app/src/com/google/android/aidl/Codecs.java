// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.aidl;

import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

public class Codecs
{

    private Codecs()
    {
    }

    public static boolean createBoolean(Parcel parcel)
    {
        return parcel.readInt() != 0;
    }

    public static Parcelable createParcelable(Parcel parcel, android.os.Parcelable.Creator creator)
    {
        if (parcel.readInt() == 0)
        {
            return null;
        } else
        {
            return (Parcelable)creator.createFromParcel(parcel);
        }
    }

    public static void writeBoolean(Parcel parcel, boolean flag)
    {
        int i;
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
    }

    public static void writeParcelable(Parcel parcel, Parcelable parcelable)
    {
        if (parcelable == null)
        {
            parcel.writeInt(0);
            return;
        } else
        {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
            return;
        }
    }

    public static void writeStrongBinder(Parcel parcel, IInterface iinterface)
    {
        if (iinterface == null)
        {
            parcel.writeStrongBinder(null);
            return;
        } else
        {
            parcel.writeStrongBinder(iinterface.asBinder());
            return;
        }
    }

    static 
    {
        com/google/android/aidl/Codecs.getClassLoader();
    }
}
