// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.timely.rooms.data.Room;
import com.google.android.calendar.timely.rooms.data.RoomHierarchyNode;
import com.google.android.calendar.timely.rooms.ui.roomtile.meetings.StructuredRoomTileFactoryImpl;
import com.google.android.calendar.timely.widgets.fullscreenerror.FullScreenErrorPage;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui:
//            RoomUiItemFactory, RoomUiItemViewProvider, RoomListView, RoomBookingHeaderAdapter, 
//            RoomUiItem

public class RoomListViewController
{
    public static interface Listener
    {

        public abstract void onFilterBarClicked();

        public abstract void onHierarchyNodeSelected(RoomHierarchyNode roomhierarchynode);

        public abstract void onNextPageRequested();

        public abstract void onRoomRemoved(Room room);

        public abstract void onRoomSelected(Room room, boolean flag);

        public abstract void onShowMoreClicked();
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/rooms/ui/RoomListViewController);
    public final ViewGroup container;
    public final View contentView;
    public final Context context;
    public final FullScreenErrorPage emptyStateView;
    public final TextView filterText;
    public boolean hasShowMoreItem;
    public final RoomBookingHeaderAdapter headerAdapter;
    public final Predicate isRoomRemovable;
    public Listener listener;
    public final View mainView;
    public final RoomListView roomList;
    public final RoomUiItemFactory roomUiItemFactory;
    public int savedScrollPosition;

    public RoomListViewController(Context context1, Predicate predicate, ViewGroup viewgroup, boolean flag)
    {
        context = context1;
        roomUiItemFactory = new RoomUiItemFactory(context);
        isRoomRemovable = predicate;
        container = viewgroup;
        contentView = LayoutInflater.from(context).inflate(0x7f050145, container, false);
        mainView = contentView.findViewById(0x102000a);
        context1 = context;
        context1 = new RoomUiItemViewProvider(context1, new StructuredRoomTileFactoryImpl(context1, flag));
        roomList = new RoomListView((ListView)mainView, context1);
        roomList.listener = new _cls1();
        emptyStateView = (FullScreenErrorPage)contentView.findViewById(0x1020004);
        class .Lambda._cls0
            implements Consumer
        {

            private final RoomListViewController arg$1;

            public final void accept(Object obj)
            {
                RoomListViewController roomlistviewcontroller = arg$1;
                obj = (Room)obj;
                roomlistviewcontroller.listener.onRoomRemoved(((Room) (obj)));
            }

            .Lambda._cls0()
            {
                arg$1 = RoomListViewController.this;
            }
        }

        headerAdapter = new RoomBookingHeaderAdapter((LinearLayout)contentView.findViewById(0x7f10033f), context1, new .Lambda._cls0());
        predicate = contentView.findViewById(0x7f100340);
        class .Lambda._cls1
            implements android.view.View.OnClickListener
        {

            private final RoomListViewController arg$1;

            public final void onClick(View view)
            {
                arg$1.listener.onFilterBarClicked();
            }

            .Lambda._cls1()
            {
                arg$1 = RoomListViewController.this;
            }
        }

        predicate.setOnClickListener(new .Lambda._cls1());
        viewgroup = (TextView)predicate.findViewById(0x7f100342);
        class .Lambda._cls2
            implements android.view.View.OnClickListener
        {

            private final RoomListViewController arg$1;

            public final void onClick(View view)
            {
                arg$1.listener.onFilterBarClicked();
            }

            .Lambda._cls2()
            {
                arg$1 = RoomListViewController.this;
            }
        }

        viewgroup.setOnClickListener(new .Lambda._cls2());
        if (Material.robotoMedium != null)
        {
            context1 = Material.robotoMedium;
        } else
        {
            context1 = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = context1;
        }
        viewgroup.setTypeface(context1);
        filterText = (TextView)predicate.findViewById(0x7f100341);
    }

    static final boolean lambda$setRooms$3$RoomListViewController(RoomUiItem roomuiitem)
    {
        return roomuiitem.type == 5;
    }


    private class _cls1
        implements RoomListView.Listener
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

        _cls1()
        {
            this$0 = RoomListViewController.this;
            super();
        }
    }

}
