// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            Organization, SmartMailImage

public class Restaurant
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final SmartMailImage image;
    public final Organization organization;

    Restaurant(Parcel parcel)
    {
        this((Organization)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/Organization.getClassLoader()), (SmartMailImage)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailImage.getClassLoader()));
    }

    public Restaurant(Organization organization1, SmartMailImage smartmailimage)
    {
        organization = organization1;
        image = smartmailimage;
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
            obj = (Restaurant)obj;
            if (organization == null ? ((Restaurant) (obj)).organization != null : !organization.equals(((Restaurant) (obj)).organization))
            {
                return false;
            }
            if (image != null)
            {
                return image.equals(((Restaurant) (obj)).image);
            }
            if (((Restaurant) (obj)).image != null)
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        int j = 0;
        int i;
        if (organization != null)
        {
            i = organization.hashCode();
        } else
        {
            i = 0;
        }
        if (image != null)
        {
            j = image.hashCode();
        }
        return i * 31 + j;
    }

    public String toString()
    {
        return String.format("Restaurant{organization=%s, image=%s}", new Object[] {
            organization, image
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(organization, i);
        parcel.writeParcelable(image, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new Restaurant(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new Restaurant[i];
        }

        _cls1()
        {
        }
    }

}
