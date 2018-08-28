// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attendee;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.Platform;

// Referenced classes of package com.google.android.calendar.api.event.attendee:
//            AttendeeDescriptor, Response

public class Attendee
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final AttendeeDescriptor attendeeDescriptor;
    public final String displayName;
    public final int relationship;
    public final Response response;
    public final int role;
    public final int type;

    Attendee(Parcel parcel)
    {
        AttendeeDescriptor attendeedescriptor = (AttendeeDescriptor)parcel.readParcelable(com/google/android/calendar/api/event/attendee/AttendeeDescriptor.getClassLoader());
        String s = parcel.readString();
        int i = parcel.readInt();
        int j;
        switch (i)
        {
        default:
            throw new IllegalStateException("Invalid role value");

        case 1: // '\001'
        case 2: // '\002'
            j = parcel.readInt();
            break;
        }
        switch (j)
        {
        default:
            throw new IllegalStateException("Invalid type value");

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            this(attendeedescriptor, s, i, j, parcel.readInt(), (Response)parcel.readParcelable(com/google/android/calendar/api/event/attendee/Response.getClassLoader()));
            break;
        }
    }

    Attendee(AttendeeDescriptor attendeedescriptor, String s, int i, int j, int k, Response response1)
    {
        if (attendeedescriptor == null)
        {
            throw new NullPointerException();
        }
        attendeeDescriptor = (AttendeeDescriptor)attendeedescriptor;
        displayName = Platform.nullToEmpty(s);
        role = i;
        type = j;
        relationship = k;
        if (response1 == null)
        {
            throw new NullPointerException();
        } else
        {
            response = (Response)response1;
            return;
        }
    }

    public Attendee(AttendeeDescriptor attendeedescriptor, String s, int i, int j, Response response1)
    {
        this(attendeedescriptor, s, i, j, 1, response1);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag1 = false;
        boolean flag;
        if (this == obj)
        {
            flag = true;
        } else
        {
            flag = flag1;
            if (obj instanceof Attendee)
            {
                obj = (Attendee)obj;
                flag = flag1;
                if (role == ((Attendee) (obj)).role)
                {
                    flag = flag1;
                    if (type == ((Attendee) (obj)).type)
                    {
                        flag = flag1;
                        if (relationship == ((Attendee) (obj)).relationship)
                        {
                            flag = flag1;
                            if (attendeeDescriptor.equals(((Attendee) (obj)).attendeeDescriptor))
                            {
                                flag = flag1;
                                if (displayName.equals(((Attendee) (obj)).displayName))
                                {
                                    return response.equals(((Attendee) (obj)).response);
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    public int hashCode()
    {
        return ((((attendeeDescriptor.hashCode() * 31 + displayName.hashCode()) * 31 + role) * 31 + type) * 31 + relationship) * 31 + response.hashCode();
    }

    public String toString()
    {
        Object obj = new StringBuilder(String.valueOf(getClass().getSimpleName()).concat("{"));
        Object obj1 = String.valueOf(attendeeDescriptor);
        obj1 = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 11)).append("descriptor=").append(((String) (obj1))).toString());
        obj = String.valueOf(displayName);
        int i;
        if (((String) (obj)).length() != 0)
        {
            obj = ", displayName=".concat(((String) (obj)));
        } else
        {
            obj = new String(", displayName=");
        }
        obj = ((StringBuilder) (obj1)).append(((String) (obj)));
        i = role;
        obj = ((StringBuilder) (obj)).append((new StringBuilder(18)).append(", role=").append(i).toString());
        i = type;
        obj = ((StringBuilder) (obj)).append((new StringBuilder(18)).append(", type=").append(i).toString());
        i = relationship;
        obj = ((StringBuilder) (obj)).append((new StringBuilder(26)).append(", relationship=").append(i).toString());
        obj1 = String.valueOf(response);
        return ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 11)).append(", response=").append(((String) (obj1))).toString()).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(attendeeDescriptor, i);
        parcel.writeString(displayName);
        parcel.writeInt(role);
        parcel.writeInt(type);
        parcel.writeInt(relationship);
        parcel.writeParcelable(response, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new Attendee(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new Attendee[i];
        }

        _cls1()
        {
        }
    }

}
