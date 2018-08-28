// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import com.google.android.calendar.timely.rooms.data.Room;
import com.google.android.calendar.timely.rooms.data.RoomHierarchyNode;
import com.google.common.collect.ImmutableSet;
import java.util.Arrays;

public final class RoomUiItem
{

    public static final ImmutableSet RB_ACTIONABLE_TYPES = ImmutableSet.construct(3, new Object[] {
        Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(5)
    });
    public static final ImmutableSet SRB_ACTIONABLE_TYPES = ImmutableSet.construct(4, new Object[] {
        Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(5)
    });
    public final String header;
    public final RoomHierarchyNode hierarchyNode;
    public final boolean isSuggestion;
    public final Room room;
    public final int type;

    RoomUiItem(String s, Room room1, int i, RoomHierarchyNode roomhierarchynode, boolean flag)
    {
        header = s;
        type = i;
        room = room1;
        hierarchyNode = roomhierarchynode;
        isSuggestion = flag;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof RoomUiItem)
        {
            obj = (RoomUiItem)obj;
            Room room1 = room;
            Room room2 = ((RoomUiItem) (obj)).room;
            boolean flag;
            if (room1 == room2 || room1 != null && room1.equals(room2))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                RoomHierarchyNode roomhierarchynode = hierarchyNode;
                RoomHierarchyNode roomhierarchynode1 = ((RoomUiItem) (obj)).hierarchyNode;
                if (roomhierarchynode == roomhierarchynode1 || roomhierarchynode != null && roomhierarchynode.equals(roomhierarchynode1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && type == ((RoomUiItem) (obj)).type)
                {
                    String s = header;
                    String s1 = ((RoomUiItem) (obj)).header;
                    if (s == s1 || s != null && s.equals(s1))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag && isSuggestion == ((RoomUiItem) (obj)).isSuggestion)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            room, hierarchyNode, Integer.valueOf(type), header, Boolean.valueOf(isSuggestion)
        });
    }

    public final String toString()
    {
        return String.format(null, "type=%d, header=%s, isSuggestion=%s, room=%s, hierarchyNode=%s", new Object[] {
            Integer.valueOf(type), header, Boolean.valueOf(isSuggestion), room, hierarchyNode
        });
    }

}
