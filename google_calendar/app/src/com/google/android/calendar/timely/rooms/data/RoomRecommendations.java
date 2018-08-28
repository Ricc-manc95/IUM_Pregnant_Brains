// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            RoomSuggestion

public abstract class RoomRecommendations
{

    public RoomRecommendations()
    {
    }

    public abstract ImmutableList getAttendeeGroups();

    public ImmutableList getRoomFlatList()
    {
        com.google.common.collect.ImmutableList.Builder builder = ImmutableList.builder();
        ImmutableList immutablelist = (ImmutableList)getRoomSuggestions();
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

    public abstract ImmutableList getRoomSuggestions();
}
