// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;


// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            RoomSuggestion, Room

abstract class $AutoValue_RoomSuggestion extends RoomSuggestion
{

    private final Room room;

    $AutoValue_RoomSuggestion(Room room1)
    {
        if (room1 == null)
        {
            throw new NullPointerException("Null room");
        } else
        {
            room = room1;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj instanceof RoomSuggestion)
        {
            obj = (RoomSuggestion)obj;
            return room.equals(((RoomSuggestion) (obj)).getRoom());
        } else
        {
            return false;
        }
    }

    public final Room getRoom()
    {
        return room;
    }

    public int hashCode()
    {
        return 0xf4243 ^ room.hashCode();
    }

    public String toString()
    {
        String s = String.valueOf(room);
        return (new StringBuilder(String.valueOf(s).length() + 21)).append("RoomSuggestion{room=").append(s).append("}").toString();
    }
}
