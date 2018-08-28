// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            ExpandedMeetingLocation

abstract class $AutoValue_ExpandedMeetingLocation extends ExpandedMeetingLocation
{

    private final ImmutableSet addedRoomEmails;
    private final ImmutableList attendees;
    private final String buildingId;
    private final String buildingName;
    private final ImmutableList roomSuggestions;

    $AutoValue_ExpandedMeetingLocation(String s, String s1, ImmutableList immutablelist, ImmutableList immutablelist1, ImmutableSet immutableset)
    {
        buildingName = s;
        buildingId = s1;
        if (immutablelist == null)
        {
            throw new NullPointerException("Null roomSuggestions");
        }
        roomSuggestions = immutablelist;
        if (immutablelist1 == null)
        {
            throw new NullPointerException("Null attendees");
        } else
        {
            attendees = immutablelist1;
            addedRoomEmails = immutableset;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof ExpandedMeetingLocation))
            {
                break MISSING_BLOCK_LABEL_141;
            }
            obj = (ExpandedMeetingLocation)obj;
            if ((buildingName != null ? buildingName.equals(((ExpandedMeetingLocation) (obj)).getBuildingName()) : ((ExpandedMeetingLocation) (obj)).getBuildingName() == null) && (buildingId != null ? buildingId.equals(((ExpandedMeetingLocation) (obj)).getBuildingId()) : ((ExpandedMeetingLocation) (obj)).getBuildingId() == null) && (roomSuggestions.equals(((ExpandedMeetingLocation) (obj)).getRoomSuggestions()) && attendees.equals(((ExpandedMeetingLocation) (obj)).getAttendees())))
            {
                break label0;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (addedRoomEmails != null) goto _L4; else goto _L3
_L3:
        if (((ExpandedMeetingLocation) (obj)).getAddedRoomEmails() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!addedRoomEmails.equals(((ExpandedMeetingLocation) (obj)).getAddedRoomEmails())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final ImmutableSet getAddedRoomEmails()
    {
        return addedRoomEmails;
    }

    public final ImmutableList getAttendees()
    {
        return attendees;
    }

    public final String getBuildingId()
    {
        return buildingId;
    }

    public final String getBuildingName()
    {
        return buildingName;
    }

    public final ImmutableList getRoomSuggestions()
    {
        return roomSuggestions;
    }

    public int hashCode()
    {
        int k = 0;
        int i;
        int j;
        int l;
        int i1;
        if (buildingName == null)
        {
            i = 0;
        } else
        {
            i = buildingName.hashCode();
        }
        if (buildingId == null)
        {
            j = 0;
        } else
        {
            j = buildingId.hashCode();
        }
        l = roomSuggestions.hashCode();
        i1 = attendees.hashCode();
        if (addedRoomEmails != null)
        {
            k = addedRoomEmails.hashCode();
        }
        return (((j ^ (i ^ 0xf4243) * 0xf4243) * 0xf4243 ^ l) * 0xf4243 ^ i1) * 0xf4243 ^ k;
    }

    public String toString()
    {
        String s = buildingName;
        String s1 = buildingId;
        String s2 = String.valueOf(roomSuggestions);
        String s3 = String.valueOf(attendees);
        String s4 = String.valueOf(addedRoomEmails);
        return (new StringBuilder(String.valueOf(s).length() + 99 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length())).append("ExpandedMeetingLocation{buildingName=").append(s).append(", buildingId=").append(s1).append(", roomSuggestions=").append(s2).append(", attendees=").append(s3).append(", addedRoomEmails=").append(s4).append("}").toString();
    }
}
