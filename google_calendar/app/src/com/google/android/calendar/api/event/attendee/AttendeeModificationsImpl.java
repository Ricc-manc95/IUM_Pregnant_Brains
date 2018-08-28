// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attendee;

import android.os.Parcel;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.attendee:
//            AttendeeModifications, Attendee, AttendeeStoreUtils, Response

public class AttendeeModificationsImpl
    implements AttendeeModifications
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final List attendees;
    public final ImmutableList original;

    AttendeeModificationsImpl(Parcel parcel)
    {
        original = ImmutableList.copyOf(parcel.createTypedArrayList(Attendee.CREATOR));
        attendees = parcel.createTypedArrayList(Attendee.CREATOR);
    }

    public AttendeeModificationsImpl(ImmutableList immutablelist)
    {
        if (immutablelist == null)
        {
            throw new NullPointerException();
        } else
        {
            original = (ImmutableList)immutablelist;
            attendees = new ArrayList(immutablelist);
            return;
        }
    }

    private final int indexOfAttendee(Attendee attendee)
    {
        for (int i = 0; i < attendees.size(); i++)
        {
            AttendeeDescriptor attendeedescriptor = ((Attendee)attendees.get(i)).attendeeDescriptor;
            if (AttendeeStoreUtils.descriptorsEquivalent(attendee.attendeeDescriptor, attendeedescriptor))
            {
                return i;
            }
        }

        return -1;
    }

    public final void addAttendee(Attendee attendee)
    {
        if (attendee == null)
        {
            throw new NullPointerException();
        }
        if (indexOfAttendee(attendee) == -1)
        {
            attendees.add(attendee);
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof AttendeeModificationsImpl)
        {
            obj = (AttendeeModificationsImpl)obj;
            ImmutableList immutablelist = original;
            ImmutableList immutablelist1 = ((AttendeeModificationsImpl) (obj)).original;
            boolean flag;
            if (immutablelist == immutablelist1 || immutablelist != null && immutablelist.equals(immutablelist1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                List list = attendees;
                obj = ((AttendeeModificationsImpl) (obj)).attendees;
                if (list == obj || list != null && list.equals(obj))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    return true;
                }
            }
            return false;
        } else
        {
            return false;
        }
    }

    public final List getOriginal()
    {
        return original;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            original, attendees
        });
    }

    public final boolean isModified()
    {
        while (attendees.size() != original.size() || !attendees.containsAll(original)) 
        {
            return true;
        }
        return false;
    }

    public final void removeAttendee(Attendee attendee)
    {
        if (attendee == null)
        {
            throw new NullPointerException();
        }
        int i = indexOfAttendee(attendee);
        if (i != -1)
        {
            attendees.remove(i);
        }
    }

    public final void setAttendeeResponse(Attendee attendee, Response response)
    {
        int i = indexOfAttendee(attendee);
        if (i == -1)
        {
            return;
        } else
        {
            attendees.remove(i);
            attendee = new Attendee(attendee.attendeeDescriptor, attendee.displayName, attendee.role, attendee.type, attendee.relationship, response);
            attendees.add(i, attendee);
            return;
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(String.valueOf(getClass().getSimpleName()).concat("{"));
        String s = String.valueOf(original);
        stringbuilder = stringbuilder.append((new StringBuilder(String.valueOf(s).length() + 10)).append("mOriginal=").append(s).toString());
        s = String.valueOf(attendees);
        return stringbuilder.append((new StringBuilder(String.valueOf(s).length() + 13)).append(", mAttendees=").append(s).toString()).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedList(original);
        parcel.writeTypedList(attendees);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AttendeeModificationsImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new AttendeeModificationsImpl[i];
        }

        _cls1()
        {
        }
    }

}
