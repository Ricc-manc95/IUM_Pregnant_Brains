// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.conference;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public final class AccessCode
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public String number;
    public int type;

    AccessCode(Parcel parcel)
    {
        number = parcel.readString();
        type = parcel.readInt();
    }

    public AccessCode(String s, int i)
    {
        number = s;
        type = i;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (AccessCode)obj;
            String s = number;
            String s1 = ((AccessCode) (obj)).number;
            boolean flag;
            if (s == s1 || s != null && s.equals(s1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag || type != ((AccessCode) (obj)).type)
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            number, Integer.valueOf(type)
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(number);
        parcel.writeInt(type);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AccessCode(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new AccessCode[i];
        }

        _cls1()
        {
        }
    }

}
