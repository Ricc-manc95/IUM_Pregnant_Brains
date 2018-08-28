// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            RoomRecommendations

class $AutoValue_RoomRecommendations extends RoomRecommendations
{

    private final ImmutableList attendeeGroups;
    private final ImmutableList roomSuggestions;

    $AutoValue_RoomRecommendations(ImmutableList immutablelist, ImmutableList immutablelist1)
    {
        if (immutablelist == null)
        {
            throw new NullPointerException("Null roomSuggestions");
        }
        roomSuggestions = immutablelist;
        if (immutablelist1 == null)
        {
            throw new NullPointerException("Null attendeeGroups");
        } else
        {
            attendeeGroups = immutablelist1;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof RoomRecommendations)
            {
                if (!roomSuggestions.equals(((RoomRecommendations) (obj = (RoomRecommendations)obj)).getRoomSuggestions()) || !attendeeGroups.equals(((RoomRecommendations) (obj)).getAttendeeGroups()))
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

    public final ImmutableList getAttendeeGroups()
    {
        return attendeeGroups;
    }

    public final ImmutableList getRoomSuggestions()
    {
        return roomSuggestions;
    }

    public int hashCode()
    {
        return (roomSuggestions.hashCode() ^ 0xf4243) * 0xf4243 ^ attendeeGroups.hashCode();
    }

    public String toString()
    {
        String s = String.valueOf(roomSuggestions);
        String s1 = String.valueOf(attendeeGroups);
        return (new StringBuilder(String.valueOf(s).length() + 54 + String.valueOf(s1).length())).append("RoomRecommendations{roomSuggestions=").append(s).append(", attendeeGroups=").append(s1).append("}").toString();
    }
}
