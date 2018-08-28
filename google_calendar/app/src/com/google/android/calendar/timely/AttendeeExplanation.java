// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeAttendee, TimelineAttendeeEvent

public final class AttendeeExplanation
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final FindTimeAttendee attendee;
    public final int conflictType;
    public List conflictingEvents;

    public AttendeeExplanation(int i, FindTimeAttendee findtimeattendee, List list)
    {
        conflictType = i;
        attendee = findtimeattendee;
        conflictingEvents = list;
    }

    public AttendeeExplanation(Parcel parcel)
    {
        conflictType = parcel.readInt();
        attendee = (FindTimeAttendee)parcel.readParcelable(com/google/android/calendar/timely/FindTimeAttendee.getClassLoader());
        conflictingEvents = ImmutableList.copyOf(parcel.createTypedArrayList(TimelineAttendeeEvent.CREATOR));
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof AttendeeExplanation))
        {
            return false;
        }
        obj = (AttendeeExplanation)obj;
        if (conflictType != ((AttendeeExplanation) (obj)).conflictType)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj1 = attendee;
        FindTimeAttendee findtimeattendee = ((AttendeeExplanation) (obj)).attendee;
        boolean flag;
        if (obj1 == findtimeattendee || obj1 != null && obj1.equals(findtimeattendee))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = conflictingEvents;
        obj = ((AttendeeExplanation) (obj)).conflictingEvents;
        if (obj1 == obj || obj1 != null && obj1.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(conflictType), attendee, conflictingEvents
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(conflictType);
        parcel.writeParcelable(attendee, i);
        parcel.writeTypedList(conflictingEvents);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AttendeeExplanation(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new AttendeeExplanation[i];
        }

        _cls1()
        {
        }
    }

}
