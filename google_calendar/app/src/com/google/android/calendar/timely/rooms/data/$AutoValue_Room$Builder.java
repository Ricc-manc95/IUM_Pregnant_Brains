// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            AutoValue_Room, Room

public final class  extends 
{

    private Integer availability;
    private String buildingName;
    private Integer capacity;
    private Integer category;
    private String description;
    private String email;
    private ImmutableList features;
    private String floorName;
    private String floorSectionName;
    private String hierarchyNodeId;
    private String name;
    private String shortName;

    public final Room build()
    {
        String s2 = "";
        if (email == null)
        {
            s2 = String.valueOf("").concat(" email");
        }
        String s = s2;
        if (name == null)
        {
            s = String.valueOf(s2).concat(" name");
        }
        s2 = s;
        if (availability == null)
        {
            s2 = String.valueOf(s).concat(" availability");
        }
        s = s2;
        if (features == null)
        {
            s = String.valueOf(s2).concat(" features");
        }
        s2 = s;
        if (category == null)
        {
            s2 = String.valueOf(s).concat(" category");
        }
        if (!s2.isEmpty())
        {
            String s1 = String.valueOf(s2);
            if (s1.length() != 0)
            {
                s1 = "Missing required properties:".concat(s1);
            } else
            {
                s1 = new String("Missing required properties:");
            }
            throw new IllegalStateException(s1);
        } else
        {
            return new AutoValue_Room(email, name, shortName, description, availability.intValue(), capacity, buildingName, hierarchyNodeId, floorName, floorSectionName, features, category.intValue());
        }
    }

    public final category setAvailability(int i)
    {
        availability = Integer.valueOf(i);
        return this;
    }

    public final availability setBuildingName(String s)
    {
        buildingName = s;
        return this;
    }

    public final buildingName setCapacity(Integer integer)
    {
        capacity = integer;
        return this;
    }

    public final capacity setCategory(int i)
    {
        category = Integer.valueOf(i);
        return this;
    }

    public final category setDescription(String s)
    {
        description = s;
        return this;
    }

    public final description setEmail(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null email");
        } else
        {
            email = s;
            return this;
        }
    }

    public final email setFeatures(ImmutableList immutablelist)
    {
        if (immutablelist == null)
        {
            throw new NullPointerException("Null features");
        } else
        {
            features = immutablelist;
            return this;
        }
    }

    public final features setFloorName(String s)
    {
        floorName = s;
        return this;
    }

    public final floorName setFloorSectionName(String s)
    {
        floorSectionName = s;
        return this;
    }

    public final floorSectionName setHierarchyNodeId(String s)
    {
        hierarchyNodeId = s;
        return this;
    }

    public final hierarchyNodeId setName(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null name");
        } else
        {
            name = s;
            return this;
        }
    }

    public final name setShortName(String s)
    {
        shortName = s;
        return this;
    }

    public ()
    {
    }
}
