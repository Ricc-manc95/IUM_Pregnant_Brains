// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.android.calendar.timely.rooms.ui.roomtile.meetings.StructuredRoomTileFactoryImpl;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui:
//            RoomListView, RoomUiItemViewProvider, RoomUiItemFactory

public final class ExpandedLocationViewController
{

    public final ViewGroup container;
    public final View contentView;
    private final Context context;
    public Listener listener;
    private final RoomListView roomList;
    private final RoomUiItemFactory roomUiItemFactory;

    public ExpandedLocationViewController(Context context1, ViewGroup viewgroup)
    {
        context = context1;
        container = viewgroup;
        contentView = LayoutInflater.from(context).inflate(0x7f0500ac, container, false);
        context1 = (ListView)contentView.findViewById(0x102000a);
        viewgroup = context;
        roomList = new RoomListView(context1, new RoomUiItemViewProvider(viewgroup, new StructuredRoomTileFactoryImpl(viewgroup, true)));
        roomList.listener = new _cls1();
        roomUiItemFactory = new RoomUiItemFactory(context);
    }

    public final void setRooms(List list, List list1, boolean flag, ImmutableSet immutableset)
    {
        RoomListView roomlistview = roomList;
        RoomUiItemFactory roomuiitemfactory = roomUiItemFactory;
        class .Lambda._cls0
            implements Predicate
        {

            private final List arg$1;

            public final boolean apply(Object obj)
            {
                return !arg$1.contains((Room)obj);
            }

            .Lambda._cls0(List list)
            {
                arg$1 = list;
            }
        }

        .Lambda._cls0 _lcls0 = new .Lambda._cls0(list);
        if (list1 == null)
        {
            throw new NullPointerException();
        }
        if (_lcls0 == null)
        {
            throw new NullPointerException();
        }
        list1 = new ArrayList(Lists.newArrayList(new com.google.common.collect.Iterables._cls4(list1, _lcls0)));
        Collections.sort(list1, new RoomComparator(immutableset));
        roomlistview.setItems(roomuiitemfactory.fromResult(list, null, list1, null, Collections.emptyList(), false, immutableset));
        list = roomList;
        byte byte0;
        if (flag)
        {
            byte0 = 2;
        } else
        {
            byte0 = 1;
        }
        list.setFooterMode(byte0);
    }

    private class _cls1
        implements RoomListView.Listener
    {

        private final ExpandedLocationViewController this$0;

        public final void onItemClicked(RoomUiItem roomuiitem)
        {
label0:
            {
                if (listener != null)
                {
                    if (roomuiitem.type != 3)
                    {
                        break label0;
                    }
                    listener.onRoomRemoved(roomuiitem.room);
                }
                return;
            }
            listener.onRoomSelected(roomuiitem.room);
        }

        public final void onNextPageRequested()
        {
            if (listener != null)
            {
                listener.onNextPageRequested();
            }
        }

        _cls1()
        {
            this$0 = ExpandedLocationViewController.this;
            super();
        }

        private class Listener
        {

            public abstract void onNextPageRequested();

            public abstract void onRoomRemoved(Room room);

            public abstract void onRoomSelected(Room room);
        }

    }


    private class RoomComparator
        implements Comparator
    {

        private ImmutableSet addedRoomEmails;

        public final int compare(Object obj, Object obj1)
        {
            obj = (Room)obj;
            obj1 = (Room)obj1;
            return ComparisonChain.ACTIVE.compareTrueFirst(addedRoomEmails.contains(((Room) (obj)).getEmail()), addedRoomEmails.contains(((Room) (obj1)).getEmail())).compare(((Room) (obj)).getAvailability(), ((Room) (obj1)).getAvailability()).compare(((Room) (obj)).getShortName(), ((Room) (obj1)).getShortName()).result();
        }

        RoomComparator(ImmutableSet immutableset)
        {
            addedRoomEmails = immutableset;
        }
    }

}
