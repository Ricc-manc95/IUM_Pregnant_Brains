// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            Room

abstract class $AutoValue_Room extends Room
{

    private final int availability;
    private final String buildingName;
    private final Integer capacity;
    private final int category;
    private final String description;
    private final String email;
    private final ImmutableList features;
    private final String floorName;
    private final String floorSectionName;
    private final String hierarchyNodeId;
    private final String name;
    private final String shortName;

    $AutoValue_Room(String s, String s1, String s2, String s3, int i, Integer integer, String s4, 
            String s5, String s6, String s7, ImmutableList immutablelist, int j)
    {
        if (s == null)
        {
            throw new NullPointerException("Null email");
        }
        email = s;
        if (s1 == null)
        {
            throw new NullPointerException("Null name");
        }
        name = s1;
        shortName = s2;
        description = s3;
        availability = i;
        capacity = integer;
        buildingName = s4;
        hierarchyNodeId = s5;
        floorName = s6;
        floorSectionName = s7;
        if (immutablelist == null)
        {
            throw new NullPointerException("Null features");
        } else
        {
            features = immutablelist;
            category = j;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof Room)
            {
                if (!email.equals(((Room) (obj = (Room)obj)).getEmail()) || !name.equals(((Room) (obj)).getName()) || (shortName != null ? !shortName.equals(((Room) (obj)).getShortName()) : ((Room) (obj)).getShortName() != null) || (description != null ? !description.equals(((Room) (obj)).getDescription()) : ((Room) (obj)).getDescription() != null) || availability != ((Room) (obj)).getAvailability() || (capacity != null ? !capacity.equals(((Room) (obj)).getCapacity()) : ((Room) (obj)).getCapacity() != null) || (buildingName != null ? !buildingName.equals(((Room) (obj)).getBuildingName()) : ((Room) (obj)).getBuildingName() != null) || (hierarchyNodeId != null ? !hierarchyNodeId.equals(((Room) (obj)).getHierarchyNodeId()) : ((Room) (obj)).getHierarchyNodeId() != null) || (floorName != null ? !floorName.equals(((Room) (obj)).getFloorName()) : ((Room) (obj)).getFloorName() != null) || (floorSectionName != null ? !floorSectionName.equals(((Room) (obj)).getFloorSectionName()) : ((Room) (obj)).getFloorSectionName() != null) || (!features.equals(((Room) (obj)).getFeatures()) || category != ((Room) (obj)).getCategory()))
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

    public final int getAvailability()
    {
        return availability;
    }

    public final String getBuildingName()
    {
        return buildingName;
    }

    public final Integer getCapacity()
    {
        return capacity;
    }

    public final int getCategory()
    {
        return category;
    }

    public final String getDescription()
    {
        return description;
    }

    public final String getEmail()
    {
        return email;
    }

    public final ImmutableList getFeatures()
    {
        return features;
    }

    public final String getFloorName()
    {
        return floorName;
    }

    public final String getFloorSectionName()
    {
        return floorSectionName;
    }

    public final String getHierarchyNodeId()
    {
        return hierarchyNodeId;
    }

    public final String getName()
    {
        return name;
    }

    public final String getShortName()
    {
        return shortName;
    }

    public int hashCode()
    {
        int k1 = 0;
        int l1 = email.hashCode();
        int i2 = name.hashCode();
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int j2;
        if (shortName == null)
        {
            i = 0;
        } else
        {
            i = shortName.hashCode();
        }
        if (description == null)
        {
            j = 0;
        } else
        {
            j = description.hashCode();
        }
        j2 = availability;
        if (capacity == null)
        {
            k = 0;
        } else
        {
            k = capacity.hashCode();
        }
        if (buildingName == null)
        {
            l = 0;
        } else
        {
            l = buildingName.hashCode();
        }
        if (hierarchyNodeId == null)
        {
            i1 = 0;
        } else
        {
            i1 = hierarchyNodeId.hashCode();
        }
        if (floorName == null)
        {
            j1 = 0;
        } else
        {
            j1 = floorName.hashCode();
        }
        if (floorSectionName != null)
        {
            k1 = floorSectionName.hashCode();
        }
        return (((j1 ^ (i1 ^ (l ^ (k ^ ((j ^ (i ^ ((l1 ^ 0xf4243) * 0xf4243 ^ i2) * 0xf4243) * 0xf4243) * 0xf4243 ^ j2) * 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243 ^ k1) * 0xf4243 ^ features.hashCode()) * 0xf4243 ^ category;
    }

    public String toString()
    {
        String s = email;
        String s1 = name;
        String s2 = shortName;
        String s3 = description;
        int i = availability;
        String s4 = String.valueOf(capacity);
        String s5 = buildingName;
        String s6 = hierarchyNodeId;
        String s7 = floorName;
        String s8 = floorSectionName;
        String s9 = String.valueOf(features);
        int j = category;
        return (new StringBuilder(String.valueOf(s).length() + 179 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length() + String.valueOf(s6).length() + String.valueOf(s7).length() + String.valueOf(s8).length() + String.valueOf(s9).length())).append("Room{email=").append(s).append(", name=").append(s1).append(", shortName=").append(s2).append(", description=").append(s3).append(", availability=").append(i).append(", capacity=").append(s4).append(", buildingName=").append(s5).append(", hierarchyNodeId=").append(s6).append(", floorName=").append(s7).append(", floorSectionName=").append(s8).append(", features=").append(s9).append(", category=").append(j).append("}").toString();
    }
}
