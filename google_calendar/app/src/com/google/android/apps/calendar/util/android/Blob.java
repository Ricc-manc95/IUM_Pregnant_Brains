// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.android;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public final class Blob
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final byte byteArray[];

    Blob(byte abyte0[])
    {
        byteArray = abyte0;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        return (obj instanceof Blob) && Arrays.equals(byteArray, byteArray);
    }

    public final int hashCode()
    {
        return Arrays.hashCode(byteArray);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(byteArray.length);
        parcel.writeByteArray(byteArray);
    }

    static 
    {
        new Blob(new byte[0]);
    }

    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            byte abyte0[] = new byte[parcel.readInt()];
            parcel.readByteArray(abyte0);
            return new Blob(abyte0);
        }

        public final Object[] newArray(int i)
        {
            return new Blob[i];
        }

        _cls1()
        {
        }
    }

}
