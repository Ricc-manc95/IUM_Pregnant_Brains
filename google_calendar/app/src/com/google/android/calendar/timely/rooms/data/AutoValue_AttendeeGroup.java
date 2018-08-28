// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            AttendeeGroup, RoomCriteria

public final class AutoValue_AttendeeGroup extends AttendeeGroup
{

    private final RoomCriteria criteria;
    private final String displayName;
    private final String hierarchyNodeId;
    private final ImmutableList roomSuggestions;

    public AutoValue_AttendeeGroup(ImmutableList immutablelist, RoomCriteria roomcriteria, String s, String s1)
    {
        if (immutablelist == null)
        {
            throw new NullPointerException("Null roomSuggestions");
        }
        roomSuggestions = immutablelist;
        if (roomcriteria == null)
        {
            throw new NullPointerException("Null criteria");
        }
        criteria = roomcriteria;
        displayName = s;
        if (s1 == null)
        {
            throw new NullPointerException("Null hierarchyNodeId");
        } else
        {
            hierarchyNodeId = s1;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof AttendeeGroup)
            {
                if (!roomSuggestions.equals(((AttendeeGroup) (obj = (AttendeeGroup)obj)).getRoomSuggestions()) || !criteria.equals(((AttendeeGroup) (obj)).getCriteria()) || (displayName != null ? !displayName.equals(((AttendeeGroup) (obj)).getDisplayName()) : ((AttendeeGroup) (obj)).getDisplayName() != null) || !hierarchyNodeId.equals(((AttendeeGroup) (obj)).getHierarchyNodeId()))
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

    public final RoomCriteria getCriteria()
    {
        return criteria;
    }

    public final String getDisplayName()
    {
        return displayName;
    }

    public final String getHierarchyNodeId()
    {
        return hierarchyNodeId;
    }

    public final ImmutableList getRoomSuggestions()
    {
        return roomSuggestions;
    }

    public final int hashCode()
    {
        int j = roomSuggestions.hashCode();
        int k = criteria.hashCode();
        int i;
        if (displayName == null)
        {
            i = 0;
        } else
        {
            i = displayName.hashCode();
        }
        return (i ^ ((j ^ 0xf4243) * 0xf4243 ^ k) * 0xf4243) * 0xf4243 ^ hierarchyNodeId.hashCode();
    }

    public final String toString()
    {
        String s = String.valueOf(roomSuggestions);
        String s1 = String.valueOf(criteria);
        String s2 = displayName;
        String s3 = hierarchyNodeId;
        return (new StringBuilder(String.valueOf(s).length() + 74 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length())).append("AttendeeGroup{roomSuggestions=").append(s).append(", criteria=").append(s1).append(", displayName=").append(s2).append(", hierarchyNodeId=").append(s3).append("}").toString();
    }
}
