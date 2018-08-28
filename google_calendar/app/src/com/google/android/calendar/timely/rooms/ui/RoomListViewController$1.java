// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui:
//            RoomUiItem, RoomListViewController

final class this._cls0
    implements this._cls0
{

    private final RoomListViewController this$0;

    public final void onItemClicked(RoomUiItem roomuiitem)
    {
        RoomListViewController roomlistviewcontroller = RoomListViewController.this;
        switch (roomuiitem.type)
        {
        case 3: // '\003'
        case 4: // '\004'
        default:
            LogUtils.wtf(RoomListViewController.TAG, "Non clickable item got clicked", new Object[0]);
            return;

        case 1: // '\001'
            roomlistviewcontroller.listener.onRoomSelected(roomuiitem.room, roomuiitem.isSuggestion);
            return;

        case 2: // '\002'
            roomlistviewcontroller.listener.onHierarchyNodeSelected(roomuiitem.hierarchyNode);
            return;

        case 5: // '\005'
            roomlistviewcontroller.listener.onShowMoreClicked();
            break;
        }
    }

    public final void onNextPageRequested()
    {
        listener.onNextPageRequested();
    }

    stener()
    {
        this$0 = RoomListViewController.this;
        super();
    }
}
