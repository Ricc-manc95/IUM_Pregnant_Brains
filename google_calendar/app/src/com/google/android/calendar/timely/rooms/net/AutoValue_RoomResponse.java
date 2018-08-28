// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import com.google.android.calendar.timely.rooms.data.RoomFlatList;
import com.google.android.calendar.timely.rooms.data.RoomHierarchy;
import com.google.android.calendar.timely.rooms.data.RoomRecommendations;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.net:
//            $AutoValue_RoomResponse

public final class AutoValue_RoomResponse extends $AutoValue_RoomResponse
{

    private volatile ImmutableList getSelectedRooms;

    public AutoValue_RoomResponse(RoomFlatList roomflatlist, RoomHierarchy roomhierarchy, RoomRecommendations roomrecommendations, String s, boolean flag, ImmutableList immutablelist)
    {
        super(roomflatlist, roomhierarchy, roomrecommendations, s, flag, immutablelist);
    }

    public final ImmutableList getSelectedRooms()
    {
        if (getSelectedRooms != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if (getSelectedRooms == null)
        {
            getSelectedRooms = super.getSelectedRooms();
            if (getSelectedRooms == null)
            {
                throw new NullPointerException("getSelectedRooms() cannot return null");
            }
        }
        break MISSING_BLOCK_LABEL_46;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        this;
        JVM INSTR monitorexit ;
_L2:
        return getSelectedRooms;
    }
}
