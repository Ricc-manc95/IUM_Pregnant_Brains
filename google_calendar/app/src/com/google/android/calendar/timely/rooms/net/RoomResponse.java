// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import android.text.TextUtils;
import com.google.android.calendar.timely.rooms.data.RoomFlatList;
import com.google.android.calendar.timely.rooms.data.RoomHierarchy;
import com.google.android.calendar.timely.rooms.data.RoomRecommendations;
import com.google.android.calendar.timely.rooms.data.RoomSuggestion;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;

public abstract class RoomResponse
{

    public RoomResponse()
    {
    }

    public abstract ImmutableList getResolvedSelectedRooms();

    public abstract String getResponseId();

    public abstract RoomFlatList getRoomFlatList();

    public abstract RoomHierarchy getRoomHierarchy();

    public abstract RoomRecommendations getRoomRecommendations();

    public ImmutableList getSelectedRooms()
    {
        com.google.common.collect.ImmutableList.Builder builder = ImmutableList.builder();
        ImmutableList immutablelist = (ImmutableList)getResolvedSelectedRooms();
        int j = immutablelist.size();
        int i = 0;
        for (Object obj = (UnmodifiableIterator)null; i < j; obj = (com.google.common.collect.ImmutableList.Builder)builder.add(((RoomSuggestion)obj).getRoom()))
        {
            obj = immutablelist.get(i);
            i++;
        }

        builder.forceCopy = true;
        return ImmutableList.asImmutableList(builder.contents, builder.size);
    }

    public final boolean hasFlatList()
    {
        if (getRoomFlatList() != null)
        {
            java.util.List list = getRoomFlatList().rooms;
            boolean flag;
            if (list == null || list.isEmpty())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag || !TextUtils.isEmpty(getRoomFlatList().pageToken))
            {
                return true;
            }
        }
        return false;
    }

    public abstract boolean queryMatchesRooms();
}
