// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import com.google.common.collect.ImmutableMap;

// Referenced classes of package com.google.android.calendar.timely.rooms.net:
//            ResolveInfo

final class AutoValue_ResolveInfo extends ResolveInfo
{

    private final ImmutableMap originalAttendees;
    private final ImmutableMap selectedRoomsAvailabilities;

    AutoValue_ResolveInfo(ImmutableMap immutablemap, ImmutableMap immutablemap1)
    {
        if (immutablemap == null)
        {
            throw new NullPointerException("Null originalAttendees");
        }
        originalAttendees = immutablemap;
        if (immutablemap1 == null)
        {
            throw new NullPointerException("Null selectedRoomsAvailabilities");
        } else
        {
            selectedRoomsAvailabilities = immutablemap1;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof ResolveInfo)
            {
                if (!originalAttendees.equals(((ResolveInfo) (obj = (ResolveInfo)obj)).getOriginalAttendees()) || !selectedRoomsAvailabilities.equals(((ResolveInfo) (obj)).getSelectedRoomsAvailabilities()))
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

    final ImmutableMap getOriginalAttendees()
    {
        return originalAttendees;
    }

    final ImmutableMap getSelectedRoomsAvailabilities()
    {
        return selectedRoomsAvailabilities;
    }

    public final int hashCode()
    {
        return (originalAttendees.hashCode() ^ 0xf4243) * 0xf4243 ^ selectedRoomsAvailabilities.hashCode();
    }

    public final String toString()
    {
        String s = String.valueOf(originalAttendees);
        String s1 = String.valueOf(selectedRoomsAvailabilities);
        return (new StringBuilder(String.valueOf(s).length() + 61 + String.valueOf(s1).length())).append("ResolveInfo{originalAttendees=").append(s).append(", selectedRoomsAvailabilities=").append(s1).append("}").toString();
    }
}
