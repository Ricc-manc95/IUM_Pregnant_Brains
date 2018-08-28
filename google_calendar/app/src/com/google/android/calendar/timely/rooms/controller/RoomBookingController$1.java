// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.google.android.apps.calendar.loggers.CalendarClientLogger;
import com.google.android.calendar.timely.net.BaseClient;
import com.google.android.calendar.timely.net.pagination.PaginatingClient;
import com.google.android.calendar.timely.rooms.data.AttendeeGroup;
import com.google.android.calendar.timely.rooms.data.Room;
import com.google.android.calendar.timely.rooms.data.RoomCriteria;
import com.google.android.calendar.timely.rooms.data.RoomRecommendations;
import com.google.android.calendar.timely.rooms.data.RoomSuggestion;
import com.google.android.calendar.timely.rooms.net.AutoValue_RoomResponse;
import com.google.android.calendar.timely.rooms.net.RoomResponse;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.MeetingSuggestionsViewController;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.UiItemList;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.UiItemManager;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.AddedRoom;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.DividerItem;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.MoreInLocationButton;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.NoSuggestionsItem;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.ShortDividerItem;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.SuggestedRoom;
import com.google.android.calendar.timely.widgets.spinner.LabeledSpinner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.util.concurrent.FutureCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.concurrent.CancellationException;

// Referenced classes of package com.google.android.calendar.timely.rooms.controller:
//            RoomBookingController, ClientAnalytics

final class this._cls0
    implements FutureCallback
{

    private final RoomBookingController this$0;

    public final void onFailure(Throwable throwable)
    {
        if (throwable instanceof CancellationException)
        {
            return;
        }
        if (state == 0)
        {
            RoomBookingController roombookingcontroller = RoomBookingController.this;
            byte byte0;
            if (_flddelegate.isOnline())
            {
                byte0 = 2;
            } else
            {
                byte0 = 1;
            }
            roombookingcontroller.showError(byte0);
        }
        _flddelegate.onConnectionError(throwable);
    }

    public final void onSuccess(Object obj)
    {
        Object obj1;
        obj1 = ((com.google.android.calendar.timely.rooms.net.geableRoomResponse)obj).roomResponse;
        obj = clientAnalytics;
        obj.currentRoomResponse = ((RoomResponse) (obj1));
        obj.lastResponse$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TP6URRDECNM6RREEHP6UR3CCLP2UGRCD5IMST21DPGMOUBKD5HN692ICLPN0RREEDIL8UBGCKTG____0 = android.support.v4.content.OOM._fld9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TP6URRDECNM6RREEHP6UR3CCLP2UGRCD5IMST21DPGMOUBKD5HN692ICLPN0RREEDIL8UBGCKTG____0;
        obj = ((RoomResponse) (obj1)).getRoomRecommendations();
        if (obj == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        if (((RoomRecommendations) (obj)).getAttendeeGroups() != null && !((RoomRecommendations) (obj)).getAttendeeGroups().isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L3; else goto _L2
_L2:
        obj = RoomBookingController.this;
        Object obj4 = ((RoomResponse) (obj1)).getResponseId();
        Object obj5 = ((RoomResponse) (obj1)).getRoomFlatList();
        com.google.android.calendar.timely.rooms.data.RoomHierarchy roomhierarchy = ((RoomResponse) (obj1)).getRoomHierarchy();
        RoomRecommendations roomrecommendations = ((RoomResponse) (obj1)).getRoomRecommendations();
        obj1 = ((RoomResponse) (obj1)).getResolvedSelectedRooms();
        obj.suggestRoomRequest = null;
        obj5 = new AutoValue_RoomResponse(((com.google.android.calendar.timely.rooms.data.RoomFlatList) (obj5)), roomhierarchy, roomrecommendations, ((String) (obj4)), true, ((ImmutableList) (obj1)));
        obj1 = ((RoomBookingController) (obj)).client;
        obj4 = ((RoomBookingController) (obj)).request;
        obj5 = new com.google.android.calendar.timely.rooms.net.geableRoomResponse(((RoomResponse) (obj5)));
        ((PaginatingClient) (obj1)).underlying.injectResponse(obj4, obj5);
        obj1.lastRequest = ((com.google.android.calendar.timely.net.pagination.PageableRequest) (obj4));
        obj1.extendableResult = ((com.google.android.calendar.timely.net.pagination.PageableResult) (obj5));
        ((RoomBookingController) (obj)).persistViewControllerState();
        ((RoomBookingController) (obj)).detachView();
        obj.state = 4;
        ((RoomBookingController) (obj)).attachView();
_L13:
        return;
_L3:
        MeetingSuggestionsViewController meetingsuggestionsviewcontroller;
        UiItemManager uiitemmanager;
        Iterator iterator1;
        meetingsuggestionsviewcontroller = meetingSuggestionsViewController;
        obj1 = ((RoomResponse) (obj1)).getSelectedRooms();
        obj = ((RoomRecommendations) (obj)).getAttendeeGroups();
        uiitemmanager = meetingsuggestionsviewcontroller.roomsUiManager;
        uiitemmanager.headerByLocation.clear();
        uiitemmanager.suggestedRoomTileByLocation.clear();
        uiitemmanager.addedRoomsByHierarchyNodeId.clear();
        uiitemmanager.decorationByLocation.clear();
        uiitemmanager.uiItemList.clear();
        AttendeeGroup attendeegroup;
        for (Iterator iterator = ((List) (obj)).iterator(); iterator.hasNext(); uiitemmanager.roomCriteriaByHierarchyNodeId.put(attendeegroup.getHierarchyNodeId(), attendeegroup.getCriteria()))
        {
            attendeegroup = (AttendeeGroup)iterator.next();
        }

        Room room;
        for (obj1 = ((List) (obj1)).iterator(); ((Iterator) (obj1)).hasNext(); uiitemmanager.addedRoomsByHierarchyNodeId.put(room.getHierarchyNodeId(), uiitemmanager.getAddedRoom(room, (RoomCriteria)uiitemmanager.roomCriteriaByHierarchyNodeId.get(room.getHierarchyNodeId()))))
        {
            room = (Room)((Iterator) (obj1)).next();
        }

        iterator1 = ((List) (obj)).iterator();
_L6:
        AttendeeGroup attendeegroup1;
        int i;
        if (!iterator1.hasNext())
        {
            break MISSING_BLOCK_LABEL_1084;
        }
        attendeegroup1 = (AttendeeGroup)iterator1.next();
        boolean flag2;
        if (!attendeegroup1.getRoomSuggestions().isEmpty())
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        obj = attendeegroup1.getHierarchyNodeId();
        if (uiitemmanager.nodeExpansionStateByHierarchyNodeId.get(obj) == null)
        {
            uiitemmanager.nodeExpansionStateByHierarchyNodeId.put(obj, Boolean.valueOf(uiitemmanager.addedRoomsByHierarchyNodeId.get(obj).isEmpty()));
        }
        if (!((Boolean)uiitemmanager.nodeExpansionStateByHierarchyNodeId.get(obj)).booleanValue())
        {
            i = 3;
        } else
        if (flag2)
        {
            i = 1;
        } else
        if (attendeegroup1.getCriteria().getPreferredBuildingName() == null)
        {
            i = 0;
        } else
        {
            i = 2;
        }
        obj = uiitemmanager.getHeader(attendeegroup1, flag2, i);
        uiitemmanager.headerByLocation.put(attendeegroup1, obj);
        uiitemmanager.uiItemList.addItem(((com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.UiItem) (obj)));
        if (attendeegroup1.getRoomSuggestions() != null && !attendeegroup1.getRoomSuggestions().isEmpty()) goto _L5; else goto _L4
_L4:
        if (attendeegroup1.getCriteria().getPreferredBuildingName() != null)
        {
            uiitemmanager.uiItemList.addItem(new NoSuggestionsItem(uiitemmanager.context.getResources()));
        }
_L11:
        obj = attendeegroup1.getCriteria();
        Object obj2;
        ImmutableList immutablelist;
        List list;
        Room room1;
        Iterator iterator2;
        if (((RoomCriteria) (obj)).getPreferredBuildingName() != null && ((RoomCriteria) (obj)).getPreferredBuildingId() != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0 && ((Boolean)uiitemmanager.nodeExpansionStateByHierarchyNodeId.get(attendeegroup1.getHierarchyNodeId())).booleanValue())
        {
            obj = new MoreInLocationButton(new com.google.android.calendar.timely.rooms.ui.suggestionsview.<init>(uiitemmanager, attendeegroup1), uiitemmanager.context.getResources());
            uiitemmanager.uiItemList.addItem(((com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.UiItem) (obj)));
            uiitemmanager.decorationByLocation.put(attendeegroup1, obj);
        }
        uiitemmanager.uiItemList.addItem(new DividerItem());
        if (true) goto _L6; else goto _L5
_L5:
        obj = new ShortDividerItem();
        uiitemmanager.uiItemList.addItem(((com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.UiItem) (obj)));
        uiitemmanager.decorationByLocation.put(attendeegroup1, obj);
        immutablelist = attendeegroup1.getRoomSuggestions();
        list = uiitemmanager.addedRoomsByHierarchyNodeId.get(attendeegroup1.getHierarchyNodeId());
        i = 0;
_L8:
        if (i >= immutablelist.size())
        {
            break MISSING_BLOCK_LABEL_1010;
        }
        room1 = ((RoomSuggestion)immutablelist.get(i)).getRoom();
        obj2 = room1.getEmail();
        iterator2 = list.iterator();
        do
        {
            if (!iterator2.hasNext())
            {
                break; /* Loop/switch isn't completed */
            }
            obj = (AddedRoom)iterator2.next();
        } while (!((AddedRoom) (obj)).room.getEmail().equals(obj2));
_L9:
        obj2 = obj;
        if (obj == null)
        {
            obj2 = obj;
            if (((Boolean)uiitemmanager.nodeExpansionStateByHierarchyNodeId.get(attendeegroup1.getHierarchyNodeId())).booleanValue())
            {
                obj2 = obj;
                if (i < 3)
                {
                    obj2 = new SuggestedRoom(room1, new com.google.android.calendar.timely.rooms.ui.suggestionsview.<init>(uiitemmanager, attendeegroup1), attendeegroup1.getCriteria());
                    uiitemmanager.suggestedRoomTileByLocation.put(attendeegroup1, (SuggestedRoom)obj2);
                }
            }
        }
        if (obj2 != null)
        {
            uiitemmanager.uiItemList.addItem(((com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.UiItem) (obj2)));
        }
        i++;
        if (true) goto _L8; else goto _L7
_L7:
        obj = null;
          goto _L9
        if (true) goto _L8; else goto _L10
_L10:
        obj = uiitemmanager.addedRoomsByHierarchyNodeId.get(attendeegroup1.getHierarchyNodeId()).iterator();
        while (((Iterator) (obj)).hasNext()) 
        {
            obj2 = (AddedRoom)((Iterator) (obj)).next();
            if (!uiitemmanager.uiItemList.contains(((com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.UiItem) (obj2))))
            {
                uiitemmanager.uiItemList.addItem(((com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.UiItem) (obj2)));
            }
        }
          goto _L11
        meetingsuggestionsviewcontroller.roomsView.setVisibility(0);
        meetingsuggestionsviewcontroller.loadingSpinner.setVisibility(8);
        meetingsuggestionsviewcontroller.loadingSpinner.setLabels(ImmutableList.of(meetingsuggestionsviewcontroller.contentView.getResources().getString(0x7f130327)));
        if (state == 0)
        {
            Object obj3 = clientAnalytics;
            obj = new ArrayList(meetingSuggestionsViewController.roomsUiManager.suggestedRoomTileByLocation.keySet());
            com.google.common.base.Function function = com.google.android.calendar.timely.rooms.ui.suggestionsview..instance;
            boolean flag1;
            if (obj instanceof RandomAccess)
            {
                obj = new com.google.common.collect.AccessList(((List) (obj)), function);
            } else
            {
                obj = new com.google.common.collect.tialList(((List) (obj)), function);
            }
            if (((ClientAnalytics) (obj3)).currentRoomResponse != null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                throw new IllegalStateException();
            } else
            {
                CalendarClientLogger calendarclientlogger = ((ClientAnalytics) (obj3)).logger;
                android.accounts.Account account = ((ClientAnalytics) (obj3)).account;
                String s = ((ClientAnalytics) (obj3)).calendarEventReference;
                obj3 = ((ClientAnalytics) (obj3)).currentRoomResponse.getResponseId();
                calendarclientlogger.log(account, calendarclientlogger.getRoomBookingProto(com.google.calendar.client.events.logging.ype.STRUCTURED_ROOMS_VIEW_UPDATED, null, ((String) (obj3)), s, null, null, null, null, null, null, null, null, ((List) (obj))));
                return;
            }
        }
        if (true) goto _L13; else goto _L12
_L12:
    }

    ambda._cls0()
    {
        this$0 = RoomBookingController.this;
        super();
    }
}
