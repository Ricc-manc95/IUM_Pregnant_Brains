// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class RoomHierarchy
{

    public final List nodes;

    public RoomHierarchy(List list)
    {
        nodes = Collections.unmodifiableList(list);
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof RoomHierarchy)
        {
            Object obj1 = (RoomHierarchy)obj;
            obj = nodes;
            obj1 = ((RoomHierarchy) (obj1)).nodes;
            if (obj == obj1 || obj != null && obj.equals(obj1))
            {
                return true;
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            nodes
        });
    }
}
