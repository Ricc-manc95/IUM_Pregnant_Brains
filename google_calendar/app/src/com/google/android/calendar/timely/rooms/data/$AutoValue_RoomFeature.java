// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;


// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            RoomFeature

abstract class $AutoValue_RoomFeature extends RoomFeature
{

    private final int displayProminence;
    private final int equipmentType;
    private final String name;

    $AutoValue_RoomFeature(String s, int i, int j)
    {
        if (s == null)
        {
            throw new NullPointerException("Null name");
        } else
        {
            name = s;
            equipmentType = i;
            displayProminence = j;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof RoomFeature)
            {
                if (!name.equals(((RoomFeature) (obj = (RoomFeature)obj)).getName()) || equipmentType != ((RoomFeature) (obj)).getEquipmentType() || displayProminence != ((RoomFeature) (obj)).getDisplayProminence())
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

    public final int getDisplayProminence()
    {
        return displayProminence;
    }

    public final int getEquipmentType()
    {
        return equipmentType;
    }

    public final String getName()
    {
        return name;
    }

    public int hashCode()
    {
        return ((name.hashCode() ^ 0xf4243) * 0xf4243 ^ equipmentType) * 0xf4243 ^ displayProminence;
    }

    public String toString()
    {
        String s = name;
        int i = equipmentType;
        int j = displayProminence;
        return (new StringBuilder(String.valueOf(s).length() + 76)).append("RoomFeature{name=").append(s).append(", equipmentType=").append(i).append(", displayProminence=").append(j).append("}").toString();
    }
}
