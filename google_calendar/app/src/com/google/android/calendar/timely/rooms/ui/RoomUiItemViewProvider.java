// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.timely.rooms.data.RoomHierarchyNode;
import com.google.android.calendar.timely.rooms.ui.roomtile.RoomTileFactory;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui:
//            RoomUiItem

final class RoomUiItemViewProvider
{

    private final Context activity;
    public final LayoutInflater inflator;
    private final Typeface robotoMedium;
    private final RoomTileFactory roomTileFactory;

    RoomUiItemViewProvider(Context context, RoomTileFactory roomtilefactory)
    {
        activity = context;
        inflator = LayoutInflater.from(activity);
        if (Material.robotoMedium != null)
        {
            context = Material.robotoMedium;
        } else
        {
            context = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = context;
        }
        robotoMedium = context;
        roomTileFactory = roomtilefactory;
    }

    public final View getView(RoomUiItem roomuiitem, View view, ViewGroup viewgroup)
    {
        roomuiitem.type;
        JVM INSTR tableswitch 0 5: default 44
    //                   0 80
    //                   1 124
    //                   2 207
    //                   3 165
    //                   4 290
    //                   5 308;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        int i = roomuiitem.type;
        throw new IllegalArgumentException((new StringBuilder(30)).append("Unknown item type: ").append(i).toString());
_L2:
        view = (TextView)view;
        if (view == null)
        {
            view = (TextView)inflator.inflate(0x7f05014f, viewgroup, false);
            view.setTypeface(robotoMedium);
        }
        view.setText(roomuiitem.header);
        roomuiitem = view;
_L9:
        return roomuiitem;
_L3:
        RoomTileFactory roomtilefactory = roomTileFactory;
        com.google.android.calendar.timely.rooms.data.Room room = roomuiitem.room;
        if (view instanceof TileView)
        {
            roomuiitem = (TileView)view;
        } else
        {
            roomuiitem = null;
        }
        return roomtilefactory.getRoomView(room, viewgroup, roomuiitem);
_L5:
        RoomTileFactory roomtilefactory1 = roomTileFactory;
        com.google.android.calendar.timely.rooms.data.Room room1 = roomuiitem.room;
        if (view instanceof TileView)
        {
            roomuiitem = (TileView)view;
        } else
        {
            roomuiitem = null;
        }
        return roomtilefactory1.getAddedRoomView(room1, viewgroup, roomuiitem, true);
_L4:
        Object obj = (FrameLayout)view;
        view = ((View) (obj));
        if (obj == null)
        {
            view = (FrameLayout)inflator.inflate(0x7f050150, viewgroup, false);
        }
        viewgroup = (TextView)view.findViewById(0x7f100042);
        obj = activity.getResources();
        roomuiitem = roomuiitem.hierarchyNode;
        if (roomuiitem.getType() == 0)
        {
            roomuiitem = roomuiitem.getName();
        } else
        {
            roomuiitem = ((Resources) (obj)).getString(0x7f130410);
        }
        viewgroup.setText(roomuiitem);
        return view;
_L6:
        roomuiitem = view;
        if (view != null) goto _L9; else goto _L8
_L8:
        return inflator.inflate(0x7f050151, viewgroup, false);
_L7:
        roomuiitem = view;
        if (view == null)
        {
            roomuiitem = inflator.inflate(0x7f05014e, viewgroup, false);
        }
        ((TextView)roomuiitem.findViewById(0x7f100042)).setText(0x7f130444);
        return roomuiitem;
    }
}
