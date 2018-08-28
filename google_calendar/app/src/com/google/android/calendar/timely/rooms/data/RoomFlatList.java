// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class RoomFlatList
{

    public final String pageToken;
    public final List rooms;

    public RoomFlatList(List list, String s)
    {
        rooms = Collections.unmodifiableList(list);
        pageToken = s;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof RoomFlatList)
        {
            obj = (RoomFlatList)obj;
            List list = rooms;
            List list1 = ((RoomFlatList) (obj)).rooms;
            boolean flag;
            if (list == list1 || list != null && list.equals(list1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                String s = pageToken;
                obj = ((RoomFlatList) (obj)).pageToken;
                if (s == obj || s != null && s.equals(obj))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            rooms, pageToken
        });
    }
}
