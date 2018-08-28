// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.timely.gridviews.attendees.AttendeeInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineAttendeeEvent, TimelineEvent

public class FindTimeAttendee
    implements Parcelable, AttendeeInfo
{
    public static final class RequesterFirstComparator
        implements Comparator
    {

        private final String email;

        public final int compare(Object obj, Object obj1)
        {
            int j = 0;
            obj = (FindTimeAttendee)obj;
            obj1 = (FindTimeAttendee)obj1;
            int i;
            if (obj != null && email.equals(((FindTimeAttendee) (obj)).email))
            {
                i = 0;
            } else
            {
                i = 1;
            }
            if (obj1 == null || !email.equals(((FindTimeAttendee) (obj1)).email))
            {
                j = 1;
            }
            return i - j;
        }

        public RequesterFirstComparator(String s)
        {
            email = s;
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public SparseArray daysToEvents;
    public String displayName;
    public String email;
    public List events;
    public boolean isOrganizer;
    public String sourceAccount;

    FindTimeAttendee(Parcel parcel)
    {
        email = parcel.readString();
        displayName = parcel.readString();
        sourceAccount = parcel.readString();
        events = parcel.createTypedArrayList(TimelineAttendeeEvent.CREATOR);
    }

    public FindTimeAttendee(String s, String s1, boolean flag)
    {
        email = s1;
        isOrganizer = flag;
        sourceAccount = s;
        daysToEvents = new SparseArray();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof FindTimeAttendee))
        {
            return false;
        }
        obj = (FindTimeAttendee)obj;
        Object obj1 = email;
        Object obj2 = ((FindTimeAttendee) (obj)).email;
        boolean flag;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
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
        obj1 = displayName;
        obj2 = ((FindTimeAttendee) (obj)).displayName;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
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
        obj1 = events;
        obj2 = ((FindTimeAttendee) (obj)).events;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
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
        obj1 = sourceAccount;
        obj2 = ((FindTimeAttendee) (obj)).sourceAccount;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && isOrganizer == ((FindTimeAttendee) (obj)).isOrganizer) goto _L1; else goto _L3
_L3:
        return false;
    }

    public final String getDisplayName()
    {
        return displayName;
    }

    public final String getEmail()
    {
        return email;
    }

    public final String getSourceAccount()
    {
        return sourceAccount;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            email, events, sourceAccount
        });
    }

    public final boolean isDisabled()
    {
        return false;
    }

    public final FindTimeAttendee setEvents(List list)
    {
        daysToEvents.clear();
        events = list;
        if (events != null)
        {
            for (Iterator iterator = events.iterator(); iterator.hasNext();)
            {
                TimelineAttendeeEvent timelineattendeeevent = (TimelineAttendeeEvent)iterator.next();
                int i = ((TimelineEvent) (timelineattendeeevent)).timeRange.getStartDay();
                int j = ((TimelineEvent) (timelineattendeeevent)).timeRange.getEndDay();
                while (i <= j) 
                {
                    List list1 = (List)daysToEvents.get(i);
                    list = list1;
                    if (list1 == null)
                    {
                        list = new ArrayList();
                        daysToEvents.put(i, list);
                    }
                    list.add(timelineattendeeevent);
                    i++;
                }
            }

        }
        return this;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(email);
        parcel.writeString(displayName);
        parcel.writeString(sourceAccount);
        parcel.writeTypedList(events);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new FindTimeAttendee(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new FindTimeAttendee[i];
        }

        _cls1()
        {
        }
    }

}
