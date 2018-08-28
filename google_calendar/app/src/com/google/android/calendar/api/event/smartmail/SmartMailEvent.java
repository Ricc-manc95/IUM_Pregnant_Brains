// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            SmartMailTime, SmartMailAddress, SmartMailImage

public class SmartMailEvent
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final SmartMailAddress address;
    private final SmartMailTime endTime;
    public final SmartMailImage image;
    public final String name;
    public final SmartMailTime startTime;

    SmartMailEvent(Parcel parcel)
    {
        this(parcel.readString(), (SmartMailTime)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailTime.getClassLoader()), (SmartMailTime)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailTime.getClassLoader()), (SmartMailAddress)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailAddress.getClassLoader()), (SmartMailImage)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailImage.getClassLoader()));
    }

    public SmartMailEvent(String s, SmartMailTime smartmailtime, SmartMailTime smartmailtime1, SmartMailAddress smartmailaddress, SmartMailImage smartmailimage)
    {
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            name = (String)s;
            startTime = smartmailtime;
            endTime = smartmailtime1;
            address = smartmailaddress;
            image = smartmailimage;
            return;
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
            obj = (SmartMailEvent)obj;
            if (!name.equals(((SmartMailEvent) (obj)).name))
            {
                return false;
            }
            if (startTime == null ? ((SmartMailEvent) (obj)).startTime != null : !startTime.equals(((SmartMailEvent) (obj)).startTime))
            {
                return false;
            }
            if (endTime == null ? ((SmartMailEvent) (obj)).endTime != null : !endTime.equals(((SmartMailEvent) (obj)).endTime))
            {
                return false;
            }
            if (address == null ? ((SmartMailEvent) (obj)).address != null : !address.equals(((SmartMailEvent) (obj)).address))
            {
                return false;
            }
            if (image != null)
            {
                return image.equals(((SmartMailEvent) (obj)).image);
            }
            if (((SmartMailEvent) (obj)).image != null)
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        int l = 0;
        int i1 = name.hashCode();
        int i;
        int j;
        int k;
        if (startTime != null)
        {
            i = startTime.hashCode();
        } else
        {
            i = 0;
        }
        if (endTime != null)
        {
            j = endTime.hashCode();
        } else
        {
            j = 0;
        }
        if (address != null)
        {
            k = address.hashCode();
        } else
        {
            k = 0;
        }
        if (image != null)
        {
            l = image.hashCode();
        }
        return (k + (j + (i + i1 * 31) * 31) * 31) * 31 + l;
    }

    public String toString()
    {
        return String.format("SmartMailEvent{name='%s', startTime=%s, endTime=%s, address=%s, image=%s}", new Object[] {
            name, startTime, endTime, address, image
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(name);
        parcel.writeParcelable(startTime, i);
        parcel.writeParcelable(endTime, i);
        parcel.writeParcelable(address, i);
        parcel.writeParcelable(image, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new SmartMailEvent(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new SmartMailEvent[i];
        }

        _cls1()
        {
        }
    }

}
