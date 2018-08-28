// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import com.google.android.calendar.timely.net.pagination.PageableResult;
import com.google.android.calendar.timely.net.pagination.PaginatingClient;
import com.google.android.calendar.timely.rooms.data.RoomFlatList;
import com.google.android.calendar.timely.rooms.data.RoomHierarchy;
import com.google.android.calendar.timely.rooms.data.RoomRecommendations;
import com.google.android.calendar.timely.rooms.net.RoomResponse;
import com.google.android.calendar.timely.rooms.ui.RoomListView;
import com.google.android.calendar.timely.rooms.ui.RoomListViewController;
import com.google.android.calendar.timely.rooms.ui.RoomUiItemFactory;
import com.google.android.calendar.timely.widgets.fullscreenerror.FullScreenErrorPage;
import com.google.common.collect.Iterators;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.util.concurrent.FutureCallback;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CancellationException;

// Referenced classes of package com.google.android.calendar.timely.rooms.controller:
//            RoomBookingController, GAnalytics, ClientAnalytics

final class val.isSearchResult
    implements FutureCallback
{

    private final RoomBookingController this$0;
    private final boolean val$isSearchResult;
    private final boolean val$restoreScrollPosition;

    public final void onFailure(Throwable throwable)
    {
        byte byte1 = 3;
        if (throwable instanceof CancellationException) goto _L2; else goto _L1
_L1:
        RoomBookingController roombookingcontroller;
        roombookingcontroller = RoomBookingController.this;
        PaginatingClient paginatingclient = roombookingcontroller.client;
        boolean flag;
        if (paginatingclient.extendableResult != null && paginatingclient.extendableResult.hasNextPage())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L4; else goto _L3
_L3:
        roombookingcontroller.state;
        JVM INSTR tableswitch 4 7: default 80
    //                   4 87
    //                   5 80
    //                   6 80
    //                   7 99;
           goto _L2 _L5 _L2 _L2 _L6
_L2:
        return;
_L5:
        roombookingcontroller.roomListViewController.roomList.setFooterMode(3);
        return;
_L6:
        roombookingcontroller.expandedHierarchyNodeViewController.roomList.setFooterMode(3);
        return;
_L4:
        android.support.v4.app.FragmentActivity fragmentactivity = roombookingcontroller.activity;
        byte byte0 = byte1;
        if (roombookingcontroller._flddelegate.isOnline())
        {
            byte0 = 7;
        }
        GAnalytics.logScreenShown(fragmentactivity, byte0);
        if (roombookingcontroller._flddelegate.isOnline())
        {
            byte0 = 2;
        } else
        {
            byte0 = 1;
        }
        roombookingcontroller.showError(byte0);
        roombookingcontroller._flddelegate.onConnectionError(throwable);
        return;
    }

    public final void onSuccess(Object obj)
    {
        Object obj1;
        Object obj5;
        RoomBookingController roombookingcontroller;
        int i;
        byte byte0;
        boolean flag;
        boolean flag1;
        boolean flag2;
        byte0 = 2;
        flag = true;
        obj1 = (com.google.android.calendar.timely.rooms.net.geableRoomResponse)obj;
        roombookingcontroller = RoomBookingController.this;
        flag1 = val$restoreScrollPosition;
        flag2 = val$isSearchResult;
        obj5 = ((com.google.android.calendar.timely.rooms.net.geableRoomResponse) (obj1)).roomResponse;
        obj = roombookingcontroller.clientAnalytics;
        obj.currentRoomResponse = ((RoomResponse) (obj5));
        obj.lastResponse$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TP6URRDECNM6RREEHP6UR3CCLP2UGRCD5IMST21DPGMOUBKD5HN692ICLPN0RREEDIL8UBGCKTG____0 = android.support.v4.content.OOM._fld9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TP6URRDECNM6RREEHP6UR3CCLP2UGRCD5IMST21DPGMOUBKD5HN692ICLPN0RREEDIL8UBGCKTG____0;
        Object obj3;
        if (roombookingcontroller.state == 4)
        {
            obj = roombookingcontroller.roomListViewController;
        } else
        {
            obj = roombookingcontroller.expandedHierarchyNodeViewController;
        }
        if (((RoomResponse) (obj5)).getRoomRecommendations() == null) goto _L2; else goto _L1
_L1:
        obj3 = ((RoomResponse) (obj5)).getRoomRecommendations().getRoomSuggestions();
        if (obj3 == null || ((Collection) (obj3)).isEmpty())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L2; else goto _L3
_L3:
        i = 1;
_L15:
        if (i != 0 || ((RoomResponse) (obj5)).hasFlatList()) goto _L5; else goto _L4
_L4:
        if (((RoomResponse) (obj5)).getRoomHierarchy() == null) goto _L7; else goto _L6
_L6:
        obj3 = ((RoomResponse) (obj5)).getRoomHierarchy().nodes;
        if (obj3 == null || ((Collection) (obj3)).isEmpty())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L7; else goto _L8
_L8:
        i = 1;
_L16:
        if (i != 0) goto _L5; else goto _L9
_L9:
        i = 1;
_L17:
        if (i == 0) goto _L11; else goto _L10
_L10:
        if (((RoomResponse) (obj5)).queryMatchesRooms())
        {
            i = 2;
        } else
        {
            i = 1;
        }
        ((RoomListViewController) (obj)).mainView.setVisibility(8);
        ((RoomListViewController) (obj)).emptyStateView.setVisibility(0);
        i;
        JVM INSTR tableswitch 1 2: default 228
    //                   1 326
    //                   2 281;
           goto _L12 _L13 _L14
_L12:
        throw new IllegalArgumentException();
_L2:
        i = 0;
          goto _L15
_L7:
        i = 0;
          goto _L16
_L5:
        i = 0;
          goto _L17
_L14:
        ((RoomListViewController) (obj)).emptyStateView.setTitle(0x7f130403);
        ((RoomListViewController) (obj)).emptyStateView.setSubtitle(0x7f130402);
_L18:
        ((RoomListViewController) (obj)).roomList.setFooterMode(1);
        GAnalytics.logScreenShown(roombookingcontroller.activity, 4);
        roombookingcontroller.clientAnalytics.logRoomsShown(false);
        return;
_L13:
        ((RoomListViewController) (obj)).emptyStateView.setTitle(0x7f130412);
        ((RoomListViewController) (obj)).emptyStateView.setSubtitle(0x7f130411);
        if (true) goto _L18; else goto _L11
_L11:
        Object obj6;
        Object obj7;
        boolean flag3;
        if (((com.google.android.calendar.timely.rooms.net.geableRoomResponse) (obj1)).roomResponse.getRoomFlatList() != null && !TextUtils.isEmpty(((com.google.android.calendar.timely.rooms.net.geableRoomResponse) (obj1)).roomResponse.getRoomFlatList().pageToken))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = byte0;
        } else
        {
            i = 1;
        }
        ((RoomListViewController) (obj)).roomList.setFooterMode(i);
        if (((RoomResponse) (obj5)).getRoomRecommendations() == null) goto _L20; else goto _L19
_L19:
        obj1 = ((RoomResponse) (obj5)).getRoomRecommendations().getRoomSuggestions();
        if (obj1 == null || ((Collection) (obj1)).isEmpty())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L20; else goto _L21
_L21:
        i = 1;
_L25:
        Object obj2;
        Object obj4;
        if (i != 0)
        {
            obj2 = ((RoomResponse) (obj5)).getRoomRecommendations().getRoomFlatList();
        } else
        {
            obj2 = Collections.emptyList();
        }
        if (((RoomResponse) (obj5)).hasFlatList())
        {
            obj4 = ((RoomResponse) (obj5)).getRoomFlatList().rooms;
        } else
        {
            obj4 = Collections.emptyList();
        }
        if (((RoomResponse) (obj5)).getRoomHierarchy() == null) goto _L23; else goto _L22
_L22:
        obj6 = ((RoomResponse) (obj5)).getRoomHierarchy().nodes;
        if (obj6 == null || ((Collection) (obj6)).isEmpty())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L23; else goto _L24
_L24:
        i = 1;
_L26:
        if (i != 0)
        {
            obj5 = ((RoomResponse) (obj5)).getRoomHierarchy().nodes;
        } else
        {
            obj5 = Collections.emptyList();
        }
        flag3 = roombookingcontroller.isHierarchyExpanded;
        obj6 = RegularImmutableSet.EMPTY;
        ((RoomListViewController) (obj)).mainView.setVisibility(0);
        ((RoomListViewController) (obj)).emptyStateView.setVisibility(8);
        obj7 = ((RoomListViewController) (obj)).context;
        if (flag2)
        {
            i = 0x7f130401;
        } else
        {
            i = 0x7f1300a0;
        }
        obj7 = ((Context) (obj7)).getString(i);
        obj2 = ((RoomListViewController) (obj)).roomUiItemFactory.fromResult(((java.util.List) (obj2)), null, ((java.util.List) (obj4)), ((String) (obj7)), ((java.util.List) (obj5)), flag3, ((com.google.common.collect.ImmutableSet) (obj6)));
        ((RoomListViewController) (obj)).roomList.setItems(((Collection) (obj2)));
        obj4 = com.google.android.calendar.timely.rooms.ui.Lambda._cls3..instance;
        if (Iterators.indexOf(((Iterable) (obj2)).iterator(), ((com.google.common.base.Predicate) (obj4))) == -1)
        {
            flag = false;
        }
        obj.hasShowMoreItem = flag;
        if (flag1)
        {
            obj2 = ((RoomListViewController) (obj)).roomList;
            i = ((RoomListViewController) (obj)).savedScrollPosition;
            ((RoomListView) (obj2)).listView.setSelection(i);
        }
        roombookingcontroller.clientAnalytics.logRoomsShown(((RoomListViewController) (obj)).hasShowMoreItem);
        return;
_L20:
        i = 0;
          goto _L25
_L23:
        i = 0;
          goto _L26
    }

    ()
    {
        this$0 = final_roombookingcontroller;
        val$restoreScrollPosition = flag;
        val$isSearchResult = Z.this;
        super();
    }
}
