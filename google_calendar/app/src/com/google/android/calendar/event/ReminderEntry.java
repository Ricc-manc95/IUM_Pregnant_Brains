// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public final class ReminderEntry
    implements Parcelable, Serializable, Comparable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final int method;
    public final int minutes;

    public ReminderEntry(int i, int j)
    {
        minutes = i;
        method = j;
    }

    ReminderEntry(Parcel parcel)
    {
        minutes = parcel.readInt();
        method = parcel.readInt();
    }

    public final int compareTo(Object obj)
    {
        obj = (ReminderEntry)obj;
        if (((ReminderEntry) (obj)).minutes != minutes)
        {
            return minutes - ((ReminderEntry) (obj)).minutes;
        }
        if (((ReminderEntry) (obj)).method != method)
        {
            return method - ((ReminderEntry) (obj)).method;
        } else
        {
            return 0;
        }
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof ReminderEntry))
            {
                return false;
            }
            obj = (ReminderEntry)obj;
            if (((ReminderEntry) (obj)).minutes != minutes)
            {
                return false;
            }
            if (((ReminderEntry) (obj)).method != method && (((ReminderEntry) (obj)).method != 0 || method != 1) && (((ReminderEntry) (obj)).method != 1 || method != 0))
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return minutes * 10 + method;
    }

    public final String toString()
    {
        int i = minutes;
        int j = method;
        return (new StringBuilder(34)).append("min=").append(i).append(" method=").append(j).toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(minutes);
        parcel.writeInt(method);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new ReminderEntry(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new ReminderEntry[i];
        }

        _cls1()
        {
        }
    }

}
