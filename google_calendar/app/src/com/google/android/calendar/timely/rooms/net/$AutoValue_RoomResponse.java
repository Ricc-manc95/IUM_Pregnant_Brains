// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import com.google.android.calendar.timely.rooms.data.RoomFlatList;
import com.google.android.calendar.timely.rooms.data.RoomHierarchy;
import com.google.android.calendar.timely.rooms.data.RoomRecommendations;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.net:
//            RoomResponse

class $AutoValue_RoomResponse extends RoomResponse
{

    private final ImmutableList getResolvedSelectedRooms;
    private final String getResponseId;
    private final RoomFlatList getRoomFlatList;
    private final RoomHierarchy getRoomHierarchy;
    private final RoomRecommendations getRoomRecommendations;
    private final boolean queryMatchesRooms;

    $AutoValue_RoomResponse(RoomFlatList roomflatlist, RoomHierarchy roomhierarchy, RoomRecommendations roomrecommendations, String s, boolean flag, ImmutableList immutablelist)
    {
        getRoomFlatList = roomflatlist;
        getRoomHierarchy = roomhierarchy;
        getRoomRecommendations = roomrecommendations;
        getResponseId = s;
        queryMatchesRooms = flag;
        if (immutablelist == null)
        {
            throw new NullPointerException("Null getResolvedSelectedRooms");
        } else
        {
            getResolvedSelectedRooms = immutablelist;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof RoomResponse)
            {
                obj = (RoomResponse)obj;
                if ((getRoomFlatList != null ? !getRoomFlatList.equals(((RoomResponse) (obj)).getRoomFlatList()) : ((RoomResponse) (obj)).getRoomFlatList() != null) || (getRoomHierarchy != null ? !getRoomHierarchy.equals(((RoomResponse) (obj)).getRoomHierarchy()) : ((RoomResponse) (obj)).getRoomHierarchy() != null) || (getRoomRecommendations != null ? !getRoomRecommendations.equals(((RoomResponse) (obj)).getRoomRecommendations()) : ((RoomResponse) (obj)).getRoomRecommendations() != null) || (getResponseId != null ? !getResponseId.equals(((RoomResponse) (obj)).getResponseId()) : ((RoomResponse) (obj)).getResponseId() != null) || (queryMatchesRooms != ((RoomResponse) (obj)).queryMatchesRooms() || !getResolvedSelectedRooms.equals(((RoomResponse) (obj)).getResolvedSelectedRooms())))
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

    public final ImmutableList getResolvedSelectedRooms()
    {
        return getResolvedSelectedRooms;
    }

    public final String getResponseId()
    {
        return getResponseId;
    }

    public final RoomFlatList getRoomFlatList()
    {
        return getRoomFlatList;
    }

    public final RoomHierarchy getRoomHierarchy()
    {
        return getRoomHierarchy;
    }

    public final RoomRecommendations getRoomRecommendations()
    {
        return getRoomRecommendations;
    }

    public int hashCode()
    {
        int l = 0;
        int i;
        int j;
        int k;
        char c;
        if (getRoomFlatList == null)
        {
            i = 0;
        } else
        {
            i = getRoomFlatList.hashCode();
        }
        if (getRoomHierarchy == null)
        {
            j = 0;
        } else
        {
            j = getRoomHierarchy.hashCode();
        }
        if (getRoomRecommendations == null)
        {
            k = 0;
        } else
        {
            k = getRoomRecommendations.hashCode();
        }
        if (getResponseId != null)
        {
            l = getResponseId.hashCode();
        }
        if (queryMatchesRooms)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return (c ^ ((k ^ (j ^ (i ^ 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243 ^ l) * 0xf4243) * 0xf4243 ^ getResolvedSelectedRooms.hashCode();
    }

    public final boolean queryMatchesRooms()
    {
        return queryMatchesRooms;
    }

    public String toString()
    {
        String s = String.valueOf(getRoomFlatList);
        String s1 = String.valueOf(getRoomHierarchy);
        String s2 = String.valueOf(getRoomRecommendations);
        String s3 = getResponseId;
        boolean flag = queryMatchesRooms;
        String s4 = String.valueOf(getResolvedSelectedRooms);
        return (new StringBuilder(String.valueOf(s).length() + 142 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length())).append("RoomResponse{getRoomFlatList=").append(s).append(", getRoomHierarchy=").append(s1).append(", getRoomRecommendations=").append(s2).append(", getResponseId=").append(s3).append(", queryMatchesRooms=").append(flag).append(", getResolvedSelectedRooms=").append(s4).append("}").toString();
    }
}
