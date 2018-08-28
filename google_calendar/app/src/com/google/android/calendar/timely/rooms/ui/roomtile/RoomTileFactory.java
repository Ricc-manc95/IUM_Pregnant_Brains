// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.roomtile;

import android.view.ViewGroup;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.timely.rooms.data.Room;
import com.google.android.calendar.timely.rooms.data.RoomCriteria;

public interface RoomTileFactory
{

    public abstract TileView getAddedRoomView(Room room, ViewGroup viewgroup, TileView tileview, boolean flag);

    public abstract TileView getAddedRoomView(Room room, ViewGroup viewgroup, TileView tileview, boolean flag, RoomCriteria roomcriteria);

    public abstract TileView getRoomView(Room room, ViewGroup viewgroup, TileView tileview);

    public abstract TileView getRoomView(Room room, ViewGroup viewgroup, TileView tileview, RoomCriteria roomcriteria);
}
