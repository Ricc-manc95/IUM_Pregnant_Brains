// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attendee;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.Platform;

// Referenced classes of package com.google.android.calendar.api.event.attendee:
//            ContactId

public class AttendeeDescriptor
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final ContactId contactId;
    public final String email;
    public final int type;

    private AttendeeDescriptor(int i, String s, ContactId contactid)
    {
        type = i;
        email = Platform.nullToEmpty(s);
        contactId = contactid;
    }

    AttendeeDescriptor(Parcel parcel)
    {
        int i = parcel.readInt();
        switch (i)
        {
        default:
            throw new IllegalStateException("Invalid attendeeDescriptor type value");

        case 1: // '\001'
        case 2: // '\002'
            this(i, parcel.readString(), (ContactId)parcel.readParcelable(com/google/android/calendar/api/event/attendee/ContactId.getClassLoader()));
            break;
        }
    }

    public AttendeeDescriptor(String s)
    {
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            this(1, (String)s, null);
            return;
        }
    }

    public AttendeeDescriptor(String s, ContactId contactid)
    {
        if (contactid == null)
        {
            throw new NullPointerException();
        } else
        {
            this(2, s, (ContactId)contactid);
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
            if (!(obj instanceof AttendeeDescriptor))
            {
                return false;
            }
            obj = (AttendeeDescriptor)obj;
            if (type != ((AttendeeDescriptor) (obj)).type)
            {
                return false;
            }
            if (!email.equals(((AttendeeDescriptor) (obj)).email))
            {
                return false;
            }
            if (contactId != null)
            {
                return contactId.equals(((AttendeeDescriptor) (obj)).contactId);
            }
            if (((AttendeeDescriptor) (obj)).contactId != null)
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        int j = type;
        int k = email.hashCode();
        int i;
        if (contactId != null)
        {
            i = contactId.hashCode();
        } else
        {
            i = 0;
        }
        return i + (j * 31 + k) * 31;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(type);
        parcel.writeString(email);
        parcel.writeParcelable(contactId, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AttendeeDescriptor(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new AttendeeDescriptor[i];
        }

        _cls1()
        {
        }
    }

}
