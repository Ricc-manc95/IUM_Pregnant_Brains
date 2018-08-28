// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.notification;

import android.os.Parcel;
import android.os.Parcelable;

public class Notification
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final int method;
    public final int minutesBefore;

    public Notification(int i, int j)
    {
        method = checkMethod(i);
        minutesBefore = j;
    }

    Notification(Parcel parcel)
    {
        method = checkMethod(parcel.readInt());
        minutesBefore = parcel.readInt();
    }

    public static int checkMethod(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException("Invalid notification method value");

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
            return i;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (Notification)obj;
            if (method != ((Notification) (obj)).method || minutesBefore != ((Notification) (obj)).minutesBefore)
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        return (method + 527) * 31 + minutesBefore;
    }

    public String toString()
    {
        method;
        JVM INSTR tableswitch 1 4: default 36
    //                   1 63
    //                   2 69
    //                   3 75
    //                   4 81;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        String s = "INVALID_METHOD";
_L7:
        return String.format("Notification{mMethod=%s, mMinutesBefore=%d}", new Object[] {
            s, Integer.valueOf(minutesBefore)
        });
_L2:
        s = "ALERT";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "EMAIL";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "SMS";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "ALARM";
        if (true) goto _L7; else goto _L6
_L6:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(method);
        parcel.writeInt(minutesBefore);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new Notification(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new Notification[i];
        }

        _cls1()
        {
        }
    }

}
