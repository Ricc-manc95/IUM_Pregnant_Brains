// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;

public final class SeatingInformation
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final String row;
    public final String seat;
    public final String section;

    SeatingInformation(Parcel parcel)
    {
        this(parcel.readString(), parcel.readString(), parcel.readString());
    }

    public SeatingInformation(String s, String s1, String s2)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        section = (String)s;
        if (s1 == null)
        {
            throw new NullPointerException();
        }
        row = (String)s1;
        if (s2 == null)
        {
            throw new NullPointerException();
        } else
        {
            seat = (String)s2;
            return;
        }
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        boolean flag1 = false;
        boolean flag;
        if (this == obj)
        {
            flag = true;
        } else
        {
            flag = flag1;
            if (obj != null)
            {
                flag = flag1;
                if (getClass() == obj.getClass())
                {
                    obj = (SeatingInformation)obj;
                    flag = flag1;
                    if (section.equals(((SeatingInformation) (obj)).section))
                    {
                        flag = flag1;
                        if (row.equals(((SeatingInformation) (obj)).row))
                        {
                            return seat.equals(((SeatingInformation) (obj)).seat);
                        }
                    }
                }
            }
        }
        return flag;
    }

    public final int hashCode()
    {
        return (section.hashCode() * 31 + row.hashCode()) * 31 + seat.hashCode();
    }

    public final String toString()
    {
        return String.format("SeatingInformation{section='%s', row='%s', seat='%s'}", new Object[] {
            section, row, seat
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(section);
        parcel.writeString(row);
        parcel.writeString(seat);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new SeatingInformation(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new SeatingInformation[i];
        }

        _cls1()
        {
        }
    }

}
