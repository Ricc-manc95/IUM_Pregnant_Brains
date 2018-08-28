// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            SmartMailTime, Organization, SmartMailImage

public final class LodgingReservation
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final SmartMailTime checkinDate;
    public final SmartMailTime checkoutDate;
    public final SmartMailImage image;
    public final Organization lodging;

    LodgingReservation(Parcel parcel)
    {
        this((SmartMailTime)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailTime.getClassLoader()), (SmartMailTime)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailTime.getClassLoader()), (Organization)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/Organization.getClassLoader()), (SmartMailImage)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailImage.getClassLoader()));
    }

    public LodgingReservation(SmartMailTime smartmailtime, SmartMailTime smartmailtime1, Organization organization, SmartMailImage smartmailimage)
    {
        checkinDate = smartmailtime;
        checkoutDate = smartmailtime1;
        lodging = organization;
        image = smartmailimage;
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
            obj = (LodgingReservation)obj;
            if (checkinDate == null ? ((LodgingReservation) (obj)).checkinDate != null : !checkinDate.equals(((LodgingReservation) (obj)).checkinDate))
            {
                return false;
            }
            if (checkoutDate == null ? ((LodgingReservation) (obj)).checkoutDate != null : !checkoutDate.equals(((LodgingReservation) (obj)).checkoutDate))
            {
                return false;
            }
            if (lodging == null ? ((LodgingReservation) (obj)).lodging != null : !lodging.equals(((LodgingReservation) (obj)).lodging))
            {
                return false;
            }
            if (image == null ? ((LodgingReservation) (obj)).image != null : !image.equals(((LodgingReservation) (obj)).image))
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        int l = 0;
        int i;
        int j;
        int k;
        if (checkinDate != null)
        {
            i = checkinDate.hashCode();
        } else
        {
            i = 0;
        }
        if (checkoutDate != null)
        {
            j = checkoutDate.hashCode();
        } else
        {
            j = 0;
        }
        if (lodging != null)
        {
            k = lodging.hashCode();
        } else
        {
            k = 0;
        }
        if (image != null)
        {
            l = image.hashCode();
        }
        return (k + (j + i * 31) * 31) * 31 + l;
    }

    public final String toString()
    {
        return String.format("LodgingReservation{checkinDate=%s, checkoutDate=%s, lodging=%s, image=%s}", new Object[] {
            checkinDate, checkoutDate, lodging, image
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(checkinDate, i);
        parcel.writeParcelable(checkoutDate, i);
        parcel.writeParcelable(lodging, i);
        parcel.writeParcelable(image, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new LodgingReservation(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new LodgingReservation[i];
        }

        _cls1()
        {
        }
    }

}
