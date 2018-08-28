// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            Organization

public final class EmailMessage
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final Organization publisher;

    EmailMessage(Parcel parcel)
    {
        this((Organization)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/Organization.getClassLoader()));
    }

    public EmailMessage(Organization organization)
    {
        publisher = organization;
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
            obj = (EmailMessage)obj;
            if (publisher != null)
            {
                return publisher.equals(((EmailMessage) (obj)).publisher);
            }
            if (((EmailMessage) (obj)).publisher != null)
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        if (publisher != null)
        {
            return publisher.hashCode();
        } else
        {
            return 0;
        }
    }

    public final String toString()
    {
        return String.format("EmailMessage{publisher=%s}", new Object[] {
            publisher
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(publisher, i);
    }

    static 
    {
        new EmailMessage(((Organization) (null)));
    }

    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new EmailMessage(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new EmailMessage[i];
        }

        _cls1()
        {
        }
    }

}
