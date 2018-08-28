// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.licenses;

import android.os.Parcel;
import android.os.Parcelable;

public final class License
    implements Parcelable, Comparable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final String libraryName;
    public final int licenseLength;
    public final long licenseOffset;
    public final String path;

    License(Parcel parcel)
    {
        libraryName = parcel.readString();
        licenseOffset = parcel.readLong();
        licenseLength = parcel.readInt();
        path = parcel.readString();
    }

    License(String s, long l, int i, String s1)
    {
        libraryName = s;
        licenseOffset = l;
        licenseLength = i;
        path = s1;
    }

    public final int compareTo(Object obj)
    {
        obj = (License)obj;
        return libraryName.compareToIgnoreCase(((License) (obj)).libraryName);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final String toString()
    {
        return libraryName;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(libraryName);
        parcel.writeLong(licenseOffset);
        parcel.writeInt(licenseLength);
        parcel.writeString(path);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new License(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new License[i];
        }

        _cls1()
        {
        }
    }

}
