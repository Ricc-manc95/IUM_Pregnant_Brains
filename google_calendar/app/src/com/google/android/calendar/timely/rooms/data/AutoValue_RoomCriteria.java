// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            RoomCriteria

public final class AutoValue_RoomCriteria extends RoomCriteria
{

    private final ImmutableList attendees;
    private final int numSeats;
    private final String preferredBuildingId;
    private final String preferredBuildingName;
    private final String preferredFloor;

    public AutoValue_RoomCriteria(ImmutableList immutablelist, String s, String s1, String s2, int i)
    {
        if (immutablelist == null)
        {
            throw new NullPointerException("Null attendees");
        } else
        {
            attendees = immutablelist;
            preferredBuildingName = s;
            preferredBuildingId = s1;
            preferredFloor = s2;
            numSeats = i;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof RoomCriteria)
            {
                if (!attendees.equals(((RoomCriteria) (obj = (RoomCriteria)obj)).getAttendees()) || (preferredBuildingName != null ? !preferredBuildingName.equals(((RoomCriteria) (obj)).getPreferredBuildingName()) : ((RoomCriteria) (obj)).getPreferredBuildingName() != null) || (preferredBuildingId != null ? !preferredBuildingId.equals(((RoomCriteria) (obj)).getPreferredBuildingId()) : ((RoomCriteria) (obj)).getPreferredBuildingId() != null) || (preferredFloor != null ? !preferredFloor.equals(((RoomCriteria) (obj)).getPreferredFloor()) : ((RoomCriteria) (obj)).getPreferredFloor() != null) || numSeats != ((RoomCriteria) (obj)).getNumSeats())
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final ImmutableList getAttendees()
    {
        return attendees;
    }

    public final int getNumSeats()
    {
        return numSeats;
    }

    public final String getPreferredBuildingId()
    {
        return preferredBuildingId;
    }

    public final String getPreferredBuildingName()
    {
        return preferredBuildingName;
    }

    public final String getPreferredFloor()
    {
        return preferredFloor;
    }

    public final int hashCode()
    {
        int k = 0;
        int l = attendees.hashCode();
        int i;
        int j;
        if (preferredBuildingName == null)
        {
            i = 0;
        } else
        {
            i = preferredBuildingName.hashCode();
        }
        if (preferredBuildingId == null)
        {
            j = 0;
        } else
        {
            j = preferredBuildingId.hashCode();
        }
        if (preferredFloor != null)
        {
            k = preferredFloor.hashCode();
        }
        return ((j ^ (i ^ (l ^ 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243 ^ k) * 0xf4243 ^ numSeats;
    }

    public final String toString()
    {
        String s = String.valueOf(attendees);
        String s1 = preferredBuildingName;
        String s2 = preferredBuildingId;
        String s3 = preferredFloor;
        int i = numSeats;
        return (new StringBuilder(String.valueOf(s).length() + 109 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length())).append("RoomCriteria{attendees=").append(s).append(", preferredBuildingName=").append(s1).append(", preferredBuildingId=").append(s2).append(", preferredFloor=").append(s3).append(", numSeats=").append(i).append("}").toString();
    }
}
