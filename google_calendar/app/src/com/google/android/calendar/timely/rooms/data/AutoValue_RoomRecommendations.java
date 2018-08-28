// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            $AutoValue_RoomRecommendations

public final class AutoValue_RoomRecommendations extends $AutoValue_RoomRecommendations
{

    private volatile ImmutableList getRoomFlatList;

    public AutoValue_RoomRecommendations(ImmutableList immutablelist, ImmutableList immutablelist1)
    {
        super(immutablelist, immutablelist1);
    }

    public final ImmutableList getRoomFlatList()
    {
        if (getRoomFlatList != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if (getRoomFlatList == null)
        {
            getRoomFlatList = super.getRoomFlatList();
            if (getRoomFlatList == null)
            {
                throw new NullPointerException("getRoomFlatList() cannot return null");
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
        return getRoomFlatList;
    }
}
