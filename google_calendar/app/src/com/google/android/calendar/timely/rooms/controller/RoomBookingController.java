// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import android.accounts.Account;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.apps.calendar.loggers.CalendarClientLogger;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.common.view.edittexttoolbar.EditTextToolbarPresenter;
import com.google.android.calendar.timely.net.BaseClient;
import com.google.android.calendar.timely.net.BaseClientFragment;
import com.google.android.calendar.timely.net.Client;
import com.google.android.calendar.timely.net.pagination.PageableResult;
import com.google.android.calendar.timely.net.pagination.PaginatingClient;
import com.google.android.calendar.timely.rooms.data.AutoValue_RoomBookingFilterParams;
import com.google.android.calendar.timely.rooms.data.ExpandedMeetingLocation;
import com.google.android.calendar.timely.rooms.data.RecurringTimes;
import com.google.android.calendar.timely.rooms.data.Room;
import com.google.android.calendar.timely.rooms.data.RoomBookingFilterParams;
import com.google.android.calendar.timely.rooms.data.RoomHierarchyNode;
import com.google.android.calendar.timely.rooms.net.RoomListingParams;
import com.google.android.calendar.timely.rooms.net.RoomRequest;
import com.google.android.calendar.timely.rooms.net.RoomResponse;
import com.google.android.calendar.timely.rooms.net.RoomsRendezvousClient;
import com.google.android.calendar.timely.rooms.ui.ErrorViewController;
import com.google.android.calendar.timely.rooms.ui.ExpandedAttendeesViewController;
import com.google.android.calendar.timely.rooms.ui.ExpandedLocationViewController;
import com.google.android.calendar.timely.rooms.ui.FiltersViewController;
import com.google.android.calendar.timely.rooms.ui.RoomBookingHeaderAdapter;
import com.google.android.calendar.timely.rooms.ui.RoomListView;
import com.google.android.calendar.timely.rooms.ui.RoomListViewController;
import com.google.android.calendar.timely.rooms.ui.RoomUiItemFactory;
import com.google.android.calendar.timely.rooms.ui.roomtile.meetings.StructuredRoomTileFactoryImpl;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.Adapter;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.MeetingSuggestionsViewController;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.UiItemManager;
import com.google.android.calendar.timely.rooms.util.Rooms;
import com.google.android.calendar.timely.widgets.fullscreenerror.FullScreenErrorPage;
import com.google.android.calendar.timely.widgets.spinner.LabeledSpinner;
import com.google.android.calendar.utils.fragment.FragmentUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.timely.rooms.controller:
//            ClientAnalytics, RoomRequestFactory, GAnalytics

public final class RoomBookingController
    implements com.google.android.calendar.timely.rooms.ui.RoomListViewController.Listener
{

    public final ToolbarCallback actionBarCallback = new ToolbarCallback();
    public final FragmentActivity activity;
    public final PaginatingClient client;
    public final ClientAnalytics clientAnalytics;
    private final ViewGroup contentView;
    public final Delegate _flddelegate;
    public final ErrorViewController errorViewController;
    private final ExpandedAttendeesViewController expandedAttendeesViewController;
    public final RoomListViewController expandedHierarchyNodeViewController;
    public RoomRequest expandedLocationRoomRequest;
    public ExpandedMeetingLocation expandedMeetingLocation;
    public final ExpandedLocationViewController expandedSuggestionsViewController;
    private final FiltersViewController filtersViewController;
    public boolean isHierarchyExpanded;
    public final MeetingSuggestionsViewController meetingSuggestionsViewController;
    public final Set nonRemovableRoomEmails;
    public RoomRequest request;
    public final RoomListViewController roomListViewController;
    public final RunningFuturesPool runningFutures = new RunningFuturesPool();
    public int state;
    public final RoomsRendezvousClient suggestRoomClient;
    public RoomRequest suggestRoomRequest;
    public final EditTextToolbarPresenter toolbar;

    RoomBookingController(FragmentActivity fragmentactivity, Delegate delegate1, Account account, int i, ViewGroup viewgroup, EditTextToolbarPresenter edittexttoolbarpresenter, int j, 
            RoomRequest roomrequest, Set set, RoomRequest roomrequest1, boolean flag, ExpandedMeetingLocation expandedmeetinglocation, RoomRequest roomrequest2, int k)
    {
        isHierarchyExpanded = false;
        activity = fragmentactivity;
        clientAnalytics = new ClientAnalytics(activity, account, roomrequest.getCalendarEventReference());
        _flddelegate = delegate1;
        request = roomrequest;
        suggestRoomRequest = roomrequest1;
        toolbar = edittexttoolbarpresenter;
        contentView = viewgroup;
        nonRemovableRoomEmails = set;
        class .Lambda._cls0
            implements Predicate
        {

            private final RoomBookingController arg$1;

            public final boolean apply(Object obj)
            {
                RoomBookingController roombookingcontroller = arg$1;
                obj = (Room)obj;
                return obj != null && !roombookingcontroller.nonRemovableRoomEmails.contains(((Room) (obj)).getEmail());
            }

            .Lambda._cls0()
            {
                arg$1 = RoomBookingController.this;
            }
        }

        fragmentactivity = new .Lambda._cls0();
        state = j;
        isHierarchyExpanded = flag;
        expandedMeetingLocation = expandedmeetinglocation;
        expandedLocationRoomRequest = roomrequest2;
        errorViewController = new ErrorViewController(activity, contentView, fragmentactivity);
        errorViewController.listener = new _cls4();
        roomListViewController = new RoomListViewController(activity, fragmentactivity, contentView, false);
        roomListViewController.listener = this;
        expandedHierarchyNodeViewController = new RoomListViewController(activity, null, contentView, true);
        expandedHierarchyNodeViewController.listener = this;
        filtersViewController = new FiltersViewController(activity, contentView);
        client = new PaginatingClient((BaseClient)FragmentUtils.attachFragment(activity, activity.mFragments.mHost.mFragmentManager, com/google/android/calendar/timely/rooms/net/RoomsRendezvousClient, null, RoomsRendezvousClient.createArguments(account.name, i)));
        if (roomrequest1 == null)
        {
            meetingSuggestionsViewController = null;
            expandedAttendeesViewController = null;
            suggestRoomClient = null;
            expandedSuggestionsViewController = null;
            return;
        } else
        {
            delegate1 = contentView;
            viewgroup = account.name;
            viewgroup = delegate1.getContext();
            edittexttoolbarpresenter = LayoutInflater.from(viewgroup).inflate(0x7f050148, delegate1, false);
            roomrequest = new Adapter(new StructuredRoomTileFactoryImpl(viewgroup, true));
            set = (RecyclerView)edittexttoolbarpresenter.findViewById(0x7f10034d);
            set.setAdapter(roomrequest);
            meetingSuggestionsViewController = new MeetingSuggestionsViewController(delegate1, edittexttoolbarpresenter, set, new UiItemManager(viewgroup, roomrequest, fragmentactivity), (LabeledSpinner)edittexttoolbarpresenter.findViewById(0x7f1001a7), edittexttoolbarpresenter.findViewById(0x7f100340), k);
            meetingSuggestionsViewController.listener = new _cls5();
            expandedAttendeesViewController = new ExpandedAttendeesViewController(contentView, account.name);
            suggestRoomClient = (RoomsRendezvousClient)FragmentUtils.attachFragment(activity, activity.mFragments.mHost.mFragmentManager, com/google/android/calendar/timely/rooms/net/RoomsRendezvousClient, null, RoomsRendezvousClient.createArguments(account.name, i));
            expandedSuggestionsViewController = new ExpandedLocationViewController(activity, contentView);
            expandedSuggestionsViewController.listener = new _cls6();
            return;
        }
    }

    static RoomBookingFilterParams getFilterParams(RoomRequest roomrequest)
    {
        Integer integer;
        boolean flag;
        if (roomrequest.getRecurringTimes() == null)
        {
            integer = null;
        } else
        {
            integer = Integer.valueOf(roomrequest.getRecurringTimes().getRecurrenceOption());
        }
        if (!roomrequest.getListingParams().isShowUnavailable())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return new AutoValue_RoomBookingFilterParams(flag, integer);
    }

    private final void setRoomsRequestCallback(ListenableFuture listenablefuture, final boolean restoreScrollPosition)
    {
        _cls2 _lcls2;
        com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0 _lcls0;
        final boolean isSearchResult;
        if (!TextUtils.isEmpty(request.getQuery()))
        {
            isSearchResult = true;
        } else
        {
            isSearchResult = false;
        }
        runningFutures.add(listenablefuture);
        _lcls2 = new _cls2();
        _lcls0 = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
        if (_lcls2 == null)
        {
            throw new NullPointerException();
        } else
        {
            listenablefuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuture, _lcls2), _lcls0);
            return;
        }
    }

    private final void updateRoomListSelectedRooms()
    {
        RoomListViewController roomlistviewcontroller = roomListViewController;
        Object obj;
        boolean flag;
        if (!TextUtils.isEmpty(request.getQuery()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag || suggestRoomRequest != null)
        {
            obj = Collections.emptyList();
        } else
        {
            obj = request.getSelectedRooms();
        }
        if (roomlistviewcontroller.isRoomRemovable == null)
        {
            throw new NullPointerException();
        } else
        {
            roomlistviewcontroller.headerAdapter.updateWithItems(RoomUiItemFactory.fromAddedRooms(((List) (obj)), roomlistviewcontroller.isRoomRemovable));
            return;
        }
    }

    final void addRoom(Room room, boolean flag)
    {
        Object obj = request;
        Object obj1 = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)ImmutableList.builder().addAll(((RoomRequest) (obj)).getSelectedRooms())).add(room);
        obj1.forceCopy = true;
        obj1 = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj1)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj1)).size);
        Object obj2 = ((RoomRequest) (obj)).getSelectedRooms();
        CalendarClientLogger calendarclientlogger;
        String s;
        boolean flag1;
        boolean flag2;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            obj = ((RoomRequest) (obj)).toBuilder().setSelectedRooms(((ImmutableList) (obj1))).build();
        }
        request = ((RoomRequest) (obj));
        updateSelectedRoomState(request.getSelectedRooms());
        obj2 = clientAnalytics;
        calendarclientlogger = ((ClientAnalytics) (obj2)).logger;
        s = ((ClientAnalytics) (obj2)).calendarEventReference;
        if (((ClientAnalytics) (obj2)).lastResponse$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TP6URRDECNM6RREEHP6UR3CCLP2UGRCD5IMST21DPGMOUBKD5HN692ICLPN0RREEDIL8UBGCKTG____0 == android.support.v4.content.ModernAsyncTask.Status.ROOM$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TP6URRDECNM6RREEHP6UR3CCLP2UGRCD5IMST21DPGMOUBKD5HN692ICLPN0RREEDIL8UBGCKTG____0)
        {
            obj = ((ClientAnalytics) (obj2)).currentRoomResponse.getResponseId();
        } else
        {
            obj = null;
        }
        if (((ClientAnalytics) (obj2)).lastResponse$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TP6URRDECNM6RREEHP6UR3CCLP2UGRCD5IMST21DPGMOUBKD5HN692ICLPN0RREEDIL8UBGCKTG____0 == android.support.v4.content.ModernAsyncTask.Status.MEETING$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TP6URRDECNM6RREEHP6UR3CCLP2UGRCD5IMST21DPGMOUBKD5HN692ICLPN0RREEDIL8UBGCKTG____0)
        {
            obj1 = ((ClientAnalytics) (obj2)).currentRoomResponse.getResponseId();
        } else
        {
            obj1 = null;
        }
        room = room.getEmail();
        if (!flag)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        calendarclientlogger.log(((ClientAnalytics) (obj2)).account, calendarclientlogger.getRoomBookingProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.ROOM_ADDED, ((String) (obj)), ((String) (obj1)), s, null, null, null, null, room, Boolean.valueOf(flag), Boolean.valueOf(flag2), null, Collections.emptyList()));
    }

    final void attachView()
    {
        boolean flag1 = true;
        int i = 1;
        int j = 1;
        boolean flag = false;
        switch (state)
        {
        case 3: // '\003'
        default:
            i = state;
            throw new IllegalStateException((new StringBuilder(26)).append("Unknown state: ").append(i).toString());

        case 0: // '\0'
            Object obj = meetingSuggestionsViewController;
            ((MeetingSuggestionsViewController) (obj)).container.addView(((MeetingSuggestionsViewController) (obj)).contentView);
            ((MeetingSuggestionsViewController) (obj)).roomsView.setVisibility(8);
            ((MeetingSuggestionsViewController) (obj)).loadingSpinner.setVisibility(0);
            toolbar.changeToEditMode();
            obj = toolbar;
            if (((EditTextToolbarPresenter) (obj)).editContainer.getVisibility() == 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                throw new IllegalStateException();
            }
            ((EditTextToolbarPresenter) (obj)).editText.removeTextChangedListener(((EditTextToolbarPresenter) (obj)).queryTextWatcher);
            ((EditTextToolbarPresenter) (obj)).editText.setText(null);
            ((EditTextToolbarPresenter) (obj)).editText.clearFocus();
            ((EditTextToolbarPresenter) (obj)).updateButtonVisibilities();
            ((EditTextToolbarPresenter) (obj)).editText.addTextChangedListener(((EditTextToolbarPresenter) (obj)).queryTextWatcher);
            obj = toolbar;
            Object obj6 = activity.getString(0x7f130083);
            ((EditTextToolbarPresenter) (obj)).rightButton.setText(((CharSequence) (obj6)));
            ((EditTextToolbarPresenter) (obj)).updateButtonVisibilities();
            obj = meetingSuggestionsViewController;
            if (suggestRoomRequest.getRecurringTimes() != null)
            {
                i = j;
            } else
            {
                i = 0;
            }
            j = Rooms.getFilterDescription(getFilterParams(request));
            obj6 = ((MeetingSuggestionsViewController) (obj)).filterBar;
            if (obj6 != null)
            {
                if (i != 0)
                {
                    i = ((flag) ? 1 : 0);
                } else
                {
                    i = 8;
                }
                ((View) (obj6)).setVisibility(i);
            }
            ((MeetingSuggestionsViewController) (obj)).filterText.setText(j);
            sendRoomRequest();
            return;

        case 1: // '\001'
            Object obj1 = expandedSuggestionsViewController;
            ((ExpandedLocationViewController) (obj1)).container.addView(((ExpandedLocationViewController) (obj1)).contentView);
            toolbar.changeToDisplayMode(expandedMeetingLocation.getBuildingName());
            obj1 = toolbar;
            ((EditTextToolbarPresenter) (obj1)).rightButton.setText(null);
            ((EditTextToolbarPresenter) (obj1)).updateButtonVisibilities();
            Object obj7 = expandedSuggestionsViewController;
            Object obj10 = expandedMeetingLocation;
            obj1 = ((ExpandedMeetingLocation) (obj10)).getRoomSuggestions();
            com.google.common.base.Function function = com.google.android.calendar.timely.rooms.data.ExpandedMeetingLocation..Lambda._cls0.$instance;
            if (obj1 instanceof RandomAccess)
            {
                obj1 = new com.google.common.collect.Lists.TransformingRandomAccessList(((List) (obj1)), function);
            } else
            {
                obj1 = new com.google.common.collect.Lists.TransformingSequentialList(((List) (obj1)), function);
            }
            ((ExpandedLocationViewController) (obj7)).setRooms(((List) (obj1)), Collections.emptyList(), true, ((ExpandedMeetingLocation) (obj10)).getAddedRoomEmails());
            if (expandedLocationRoomRequest == null)
            {
                expandedLocationRoomRequest = RoomRequestFactory.roomsInLocationRequest(request, expandedMeetingLocation.getBuildingId());
            }
            obj1 = client.sendRequest(expandedLocationRoomRequest);
            runningFutures.add(((ListenableFuture) (obj1)));
            obj7 = new _cls3();
            obj10 = CalendarExecutor.MAIN;
            if (obj7 == null)
            {
                throw new NullPointerException();
            } else
            {
                ((ListenableFuture) (obj1)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj1)), ((FutureCallback) (obj7))), ((Executor) (obj10)));
                return;
            }

        case 2: // '\002'
            Object obj2 = expandedAttendeesViewController;
            ((ExpandedAttendeesViewController) (obj2)).container.addView(((ExpandedAttendeesViewController) (obj2)).contentView);
            Object obj8 = toolbar;
            obj2 = activity.getResources();
            ExpandedMeetingLocation expandedmeetinglocation = expandedMeetingLocation;
            String s = expandedmeetinglocation.getBuildingName();
            if (s == null)
            {
                i = expandedmeetinglocation.getAttendees().size();
                obj2 = String.format(((Resources) (obj2)).getQuantityString(0x7f120021, i), new Object[] {
                    Integer.valueOf(i)
                });
            } else
            {
                obj2 = ((Resources) (obj2)).getString(0x7f1301e5, new Object[] {
                    s
                });
            }
            ((EditTextToolbarPresenter) (obj8)).changeToDisplayMode(((String) (obj2)));
            obj2 = toolbar;
            ((EditTextToolbarPresenter) (obj2)).rightButton.setText(null);
            ((EditTextToolbarPresenter) (obj2)).updateButtonVisibilities();
            obj8 = expandedAttendeesViewController;
            obj2 = expandedMeetingLocation.getAttendees();
            obj8 = ((ExpandedAttendeesViewController) (obj8)).listAdapter;
            obj8.attendees = ImmutableList.copyOf(((Collection) (obj2)));
            ((android.support.v7.widget.RecyclerView.Adapter) (obj8)).mObservable.notifyChanged();
            return;

        case 5: // '\005'
        case 6: // '\006'
        case 8: // '\b'
            Object obj3 = filtersViewController;
            ((FiltersViewController) (obj3)).recurrenceOptionListener.outer = ((FiltersViewController) (obj3));
            ((FiltersViewController) (obj3)).container.addView(((FiltersViewController) (obj3)).contentView);
            toolbar.changeToDisplayMode(activity.getString(0x7f13040f));
            obj3 = toolbar;
            Object obj9 = activity.getString(0x7f13007f);
            ((EditTextToolbarPresenter) (obj3)).rightButton.setText(((CharSequence) (obj9)));
            ((EditTextToolbarPresenter) (obj3)).updateButtonVisibilities();
            obj3 = filtersViewController;
            obj9 = getFilterParams(request);
            if (request.getRecurringTimes() == null)
            {
                flag1 = false;
            }
            obj3.params = ((RoomBookingFilterParams) (obj9));
            obj3.allowChangeRecurrenceOption = flag1;
            ((FiltersViewController) (obj3)).updateUi();
            return;

        case 4: // '\004'
            Object obj4 = roomListViewController;
            ((RoomListViewController) (obj4)).container.addView(((RoomListViewController) (obj4)).contentView);
            toolbar.changeToEditMode();
            obj4 = toolbar;
            ((EditTextToolbarPresenter) (obj4)).rightButton.setText(null);
            ((EditTextToolbarPresenter) (obj4)).updateButtonVisibilities();
            updateRoomListSelectedRooms();
            obj4 = roomListViewController;
            i = Rooms.getFilterDescription(getFilterParams(request));
            ((RoomListViewController) (obj4)).filterText.setText(i);
            obj4 = roomListViewController;
            ((RoomListViewController) (obj4)).mainView.setVisibility(0);
            ((RoomListViewController) (obj4)).emptyStateView.setVisibility(8);
            ((RoomListViewController) (obj4)).roomList.setItems(Collections.emptyList());
            obj4.hasShowMoreItem = false;
            ((RoomListViewController) (obj4)).roomList.setFooterMode(2);
            sendRoomsRequest(false);
            return;

        case 7: // '\007'
            RoomListViewController roomlistviewcontroller = expandedHierarchyNodeViewController;
            roomlistviewcontroller.container.addView(roomlistviewcontroller.contentView);
            break;
        }
        if (request.getHierarchyNode() == null)
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new IllegalStateException();
        }
        EditTextToolbarPresenter edittexttoolbarpresenter = toolbar;
        Object obj5 = activity.getResources();
        RoomHierarchyNode roomhierarchynode = request.getHierarchyNode();
        if (roomhierarchynode.getType() == 0)
        {
            obj5 = roomhierarchynode.getName();
        } else
        {
            obj5 = ((Resources) (obj5)).getString(0x7f130410);
        }
        edittexttoolbarpresenter.changeToDisplayMode(((String) (obj5)));
        obj5 = toolbar;
        ((EditTextToolbarPresenter) (obj5)).rightButton.setText(null);
        ((EditTextToolbarPresenter) (obj5)).updateButtonVisibilities();
        obj5 = expandedHierarchyNodeViewController;
        i = Rooms.getFilterDescription(getFilterParams(request));
        ((RoomListViewController) (obj5)).filterText.setText(i);
        obj5 = expandedHierarchyNodeViewController;
        ((RoomListViewController) (obj5)).mainView.setVisibility(0);
        ((RoomListViewController) (obj5)).emptyStateView.setVisibility(8);
        ((RoomListViewController) (obj5)).roomList.setItems(Collections.emptyList());
        obj5.hasShowMoreItem = false;
        ((RoomListViewController) (obj5)).roomList.setFooterMode(2);
        sendRoomsRequest(false);
    }

    final void detachView()
    {
        ErrorViewController errorviewcontroller = errorViewController;
        errorviewcontroller.container.removeView(errorviewcontroller.contentView);
        switch (state)
        {
        case 3: // '\003'
        default:
            int i = state;
            throw new IllegalStateException((new StringBuilder(26)).append("Unknown state: ").append(i).toString());

        case 0: // '\0'
            meetingSuggestionsViewController.container.removeAllViews();
            return;

        case 1: // '\001'
            expandedSuggestionsViewController.container.removeAllViews();
            return;

        case 2: // '\002'
            expandedAttendeesViewController.container.removeAllViews();
            return;

        case 4: // '\004'
            roomListViewController.container.removeAllViews();
            return;

        case 7: // '\007'
            expandedHierarchyNodeViewController.container.removeAllViews();
            return;

        case 5: // '\005'
        case 6: // '\006'
        case 8: // '\b'
            FiltersViewController filtersviewcontroller = filtersViewController;
            filtersviewcontroller.container.removeAllViews();
            filtersviewcontroller.recurrenceOptionListener.outer = null;
            return;
        }
    }

    public final void onBack()
    {
        switch (state)
        {
        case 3: // '\003'
        default:
            int i = state;
            throw new IllegalStateException((new StringBuilder(26)).append("Unknown state: ").append(i).toString());

        case 5: // '\005'
            GAnalytics.logApplyFilter(activity, filtersViewController.params, false);
            persistViewControllerState();
            detachView();
            state = 4;
            attachView();
            _flddelegate.onWindowStateChanged();
            return;

        case 1: // '\001'
        case 2: // '\002'
        case 6: // '\006'
            persistViewControllerState();
            detachView();
            state = 0;
            attachView();
            return;

        case 0: // '\0'
            _flddelegate.onFinish(suggestRoomRequest.getSelectedRooms());
            return;

        case 7: // '\007'
            request = RoomRequestFactory.hierarchyNodesRequest(request);
            persistViewControllerState();
            detachView();
            state = 4;
            attachView();
            _flddelegate.onWindowStateChanged();
            GAnalytics.logSelected(activity, "node");
            GAnalytics.logBack(activity, 6);
            return;

        case 8: // '\b'
            persistViewControllerState();
            detachView();
            state = 7;
            attachView();
            return;

        case 4: // '\004'
            break;
        }
        if (suggestRoomRequest == null)
        {
            _flddelegate.onFinish(request.getSelectedRooms());
            GAnalytics.logBack(activity, 0);
            return;
        } else
        {
            persistViewControllerState();
            detachView();
            state = 0;
            attachView();
            return;
        }
    }

    public final void onFilterBarClicked()
    {
        state;
        JVM INSTR tableswitch 4 7: default 36
    //                   4 71
    //                   5 36
    //                   6 36
    //                   7 149;
           goto _L1 _L2 _L1 _L1 _L3
_L1:
        int i = state;
        throw new IllegalStateException((new StringBuilder(47)).append("Should not invoke listener in state ").append(i).toString());
_L2:
        boolean flag;
        if (!TextUtils.isEmpty(request.getQuery()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            RoomListViewController roomlistviewcontroller = roomListViewController;
            roomlistviewcontroller.savedScrollPosition = roomlistviewcontroller.roomList.listView.getFirstVisiblePosition();
        }
        persistViewControllerState();
        detachView();
        state = 5;
        attachView();
_L5:
        GAnalytics.logScreenShown(activity, 1);
        _flddelegate.onWindowStateChanged();
        return;
_L3:
        persistViewControllerState();
        detachView();
        state = 8;
        attachView();
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final void onHierarchyNodeSelected(RoomHierarchyNode roomhierarchynode)
    {
        ClientAnalytics clientanalytics;
        boolean flag;
        if (!TextUtils.isEmpty(request.getQuery()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            RoomListViewController roomlistviewcontroller = roomListViewController;
            roomlistviewcontroller.savedScrollPosition = roomlistviewcontroller.roomList.listView.getFirstVisiblePosition();
        }
        request = RoomRequestFactory.hierarchyViewRequest(request, roomhierarchynode);
        persistViewControllerState();
        detachView();
        state = 7;
        attachView();
        _flddelegate.onWindowStateChanged();
        GAnalytics.logSelected(activity, "node");
        clientanalytics = clientAnalytics;
        if (clientanalytics.currentRoomResponse != null)
        {
            CalendarClientLogger calendarclientlogger = clientanalytics.logger;
            String s = clientanalytics.calendarEventReference;
            String s1 = clientanalytics.currentRoomResponse.getResponseId();
            roomhierarchynode = roomhierarchynode.getId();
            calendarclientlogger.log(clientanalytics.account, calendarclientlogger.getRoomBookingProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.HIERARCHY_NODE_EXPANDED, s1, null, s, null, null, null, null, null, null, null, roomhierarchynode, Collections.emptyList()));
        }
    }

    public final void onNextPageRequested()
    {
        PaginatingClient paginatingclient = client;
        boolean flag;
        if (paginatingclient.extendableResult != null && paginatingclient.extendableResult.hasNextPage())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return;
        }
        state;
        JVM INSTR tableswitch 4 7: default 72
    //                   4 107
    //                   5 72
    //                   6 72
    //                   7 139;
           goto _L1 _L2 _L1 _L1 _L3
_L1:
        int i = state;
        throw new IllegalStateException((new StringBuilder(47)).append("Should not invoke listener in state ").append(i).toString());
_L2:
        roomListViewController.roomList.setFooterMode(2);
_L5:
        setRoomsRequestCallback(client.getNextPage(), false);
        GAnalytics.logScreenShown(activity, 5);
        return;
_L3:
        expandedHierarchyNodeViewController.roomList.setFooterMode(2);
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final void onRoomRemoved(Room room)
    {
        Object obj = request;
        Object obj1 = ImmutableList.builder();
        Object obj2 = (ImmutableList)((RoomRequest) (obj)).getSelectedRooms();
        int j = ((ImmutableList) (obj2)).size();
        int i = 0;
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
        while (i < j) 
        {
            Object obj3 = (Room)((ImmutableList) (obj2)).get(i);
            String s = ((Room) (obj3)).getEmail();
            String s1 = room.getEmail();
            boolean flag;
            if (s == s1 || s != null && s.equals(s1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                obj3 = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj1)).add(obj3);
            }
            i++;
        }
        obj1.forceCopy = true;
        obj1 = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj1)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj1)).size);
        obj2 = ((RoomRequest) (obj)).getSelectedRooms();
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (!i)
        {
            obj = ((RoomRequest) (obj)).toBuilder().setSelectedRooms(((ImmutableList) (obj1))).build();
        }
        request = ((RoomRequest) (obj));
        updateSelectedRoomState(request.getSelectedRooms());
        obj = clientAnalytics;
        obj1 = ((ClientAnalytics) (obj)).logger;
        obj2 = ((ClientAnalytics) (obj)).calendarEventReference;
        room = room.getEmail();
        ((CalendarClientLogger) (obj1)).log(((ClientAnalytics) (obj)).account, ((CalendarClientLogger) (obj1)).getRoomBookingProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.ROOM_REMOVED, null, null, ((String) (obj2)), null, null, null, null, room, null, null, null, Collections.emptyList()));
        updateRoomListSelectedRooms();
        GAnalytics.logRemovedRoom(activity);
    }

    public final void onRoomSelected(Room room, boolean flag)
    {
        addRoom(room, flag);
        FragmentActivity fragmentactivity;
        if (suggestRoomRequest == null)
        {
            _flddelegate.onFinish(request.getSelectedRooms());
        } else
        {
            persistViewControllerState();
            detachView();
            state = 0;
            attachView();
        }
        fragmentactivity = activity;
        if (flag)
        {
            room = "suggestion";
        } else
        {
            room = "room";
        }
        GAnalytics.logSelected(fragmentactivity, room);
    }

    public final void onShowMoreClicked()
    {
        isHierarchyExpanded = true;
        RoomListViewController roomlistviewcontroller = roomListViewController;
        roomlistviewcontroller.mainView.setVisibility(0);
        roomlistviewcontroller.emptyStateView.setVisibility(8);
        roomlistviewcontroller.roomList.setItems(Collections.emptyList());
        roomlistviewcontroller.hasShowMoreItem = false;
        roomlistviewcontroller.roomList.setFooterMode(2);
        sendRoomsRequest(true);
        GAnalytics.logSelected(activity, "show_all_rooms");
        clientAnalytics.logRoomsShown(false);
    }

    final void persistViewControllerState()
    {
        switch (state)
        {
        case 3: // '\003'
        default:
            int i = state;
            throw new IllegalStateException((new StringBuilder(26)).append("Unknown state: ").append(i).toString());

        case 5: // '\005'
        case 6: // '\006'
        case 8: // '\b'
            RoomBookingFilterParams roombookingfilterparams = filtersViewController.params;
            RoomRequest roomrequest = RoomRequestFactory.applyFilterParams(request, roombookingfilterparams);
            RoomRequest roomrequest1 = request;
            boolean flag;
            if (roomrequest1 == roomrequest || roomrequest1 != null && roomrequest1.equals(roomrequest))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                ClientAnalytics clientanalytics = clientAnalytics;
                CalendarClientLogger calendarclientlogger = clientanalytics.logger;
                String s = clientanalytics.calendarEventReference;
                calendarclientlogger.log(clientanalytics.account, calendarclientlogger.getRoomBookingProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.ROOM_BOOKING_FILTERS_CHANGED, null, null, s, null, null, null, null, null, null, null, null, Collections.emptyList()));
            }
            request = roomrequest;
            if (suggestRoomRequest != null)
            {
                suggestRoomRequest = RoomRequestFactory.applyFilterParams(suggestRoomRequest, roombookingfilterparams);
            }
            // fall through

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 4: // '\004'
        case 7: // '\007'
            return;
        }
    }

    final void removeRoom(Room room)
    {
        Object obj = request;
        Object obj1 = ImmutableList.builder();
        Object obj2 = (ImmutableList)((RoomRequest) (obj)).getSelectedRooms();
        int j = ((ImmutableList) (obj2)).size();
        int i = 0;
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
        while (i < j) 
        {
            Object obj3 = (Room)((ImmutableList) (obj2)).get(i);
            String s = ((Room) (obj3)).getEmail();
            String s1 = room.getEmail();
            boolean flag;
            if (s == s1 || s != null && s.equals(s1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                obj3 = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj1)).add(obj3);
            }
            i++;
        }
        obj1.forceCopy = true;
        obj1 = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj1)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj1)).size);
        obj2 = ((RoomRequest) (obj)).getSelectedRooms();
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (!i)
        {
            obj = ((RoomRequest) (obj)).toBuilder().setSelectedRooms(((ImmutableList) (obj1))).build();
        }
        request = ((RoomRequest) (obj));
        updateSelectedRoomState(request.getSelectedRooms());
        obj = clientAnalytics;
        obj1 = ((ClientAnalytics) (obj)).logger;
        obj2 = ((ClientAnalytics) (obj)).calendarEventReference;
        room = room.getEmail();
        ((CalendarClientLogger) (obj1)).log(((ClientAnalytics) (obj)).account, ((CalendarClientLogger) (obj1)).getRoomBookingProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.ROOM_REMOVED, null, null, ((String) (obj2)), null, null, null, null, room, null, null, null, Collections.emptyList()));
    }

    final void sendRoomRequest()
    {
        Object obj = suggestRoomClient;
        Object obj1 = suggestRoomRequest;
        obj = ((BaseClientFragment) (obj)).client.sendRequest(obj1);
        runningFutures.add(((ListenableFuture) (obj)));
        obj1 = new _cls1();
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        if (obj1 == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), ((FutureCallback) (obj1))), calendarexecutor);
            return;
        }
    }

    final void sendRoomsRequest(boolean flag)
    {
        if (state == 4 && !flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setRoomsRequestCallback(client.sendRequest(request), flag);
    }

    final void setExpandedLocationRequestCabllback(ListenableFuture listenablefuture)
    {
        runningFutures.add(listenablefuture);
        _cls3 _lcls3 = new _cls3();
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        if (_lcls3 == null)
        {
            throw new NullPointerException();
        } else
        {
            listenablefuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuture, _lcls3), calendarexecutor);
            return;
        }
    }

    final void showError(int i)
    {
        ErrorViewController errorviewcontroller;
        persistViewControllerState();
        detachView();
        errorviewcontroller = errorViewController;
        ImmutableList immutablelist = request.getSelectedRooms();
        errorviewcontroller.addedRoomsList.updateWithItems(RoomUiItemFactory.fromAddedRooms(immutablelist, errorviewcontroller.isRoomRemovable));
        errorviewcontroller = errorViewController;
        i;
        JVM INSTR tableswitch 1 2: default 64
    //                   1 94
    //                   2 131;
           goto _L1 _L2 _L3
_L1:
        throw new IllegalArgumentException((new StringBuilder(31)).append("Invalid error type: ").append(i).toString());
_L2:
        errorviewcontroller.errorPage.setTitle(0x7f130414);
        errorviewcontroller.errorPage.setSubtitle(0x7f130413);
_L5:
        errorviewcontroller = errorViewController;
        errorviewcontroller.container.addView(errorviewcontroller.contentView);
        return;
_L3:
        errorviewcontroller.errorPage.setTitle(0x7f1301d1);
        errorviewcontroller.errorPage.setSubtitle(0x7f1301d0);
        if (true) goto _L5; else goto _L4
_L4:
    }

    final void updateRequestAndLog(String s)
    {
        if (TextUtils.isEmpty(s))
        {
            request = RoomRequestFactory.hierarchyNodesRequest(request);
            GAnalytics.logBack(activity, 2);
            return;
        } else
        {
            request = RoomRequestFactory.searchRequest(request, s);
            GAnalytics.logScreenShown(activity, 2);
            s = clientAnalytics;
            CalendarClientLogger calendarclientlogger = ((ClientAnalytics) (s)).logger;
            String s1 = ((ClientAnalytics) (s)).calendarEventReference;
            calendarclientlogger.log(((ClientAnalytics) (s)).account, calendarclientlogger.getRoomBookingProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.SEARCH_QUERY_ENTERED, null, null, s1, null, null, null, null, null, null, null, null, Collections.emptyList()));
            return;
        }
    }

    final void updateSelectedRoomState(List list)
    {
label0:
        {
            ImmutableList immutablelist;
label1:
            {
                boolean flag1 = false;
                immutablelist = ImmutableList.copyOf(list);
                list = request;
                ImmutableList immutablelist1 = list.getSelectedRooms();
                boolean flag;
                if (immutablelist == immutablelist1 || immutablelist != null && immutablelist.equals(immutablelist1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    list = list.toBuilder().setSelectedRooms(immutablelist).build();
                }
                request = list;
                if (suggestRoomRequest == null)
                {
                    break label0;
                }
                list = suggestRoomRequest;
                immutablelist1 = list.getSelectedRooms();
                if (immutablelist != immutablelist1)
                {
                    flag = flag1;
                    if (immutablelist == null)
                    {
                        break label1;
                    }
                    flag = flag1;
                    if (!immutablelist.equals(immutablelist1))
                    {
                        break label1;
                    }
                }
                flag = true;
            }
            if (!flag)
            {
                list = list.toBuilder().setSelectedRooms(immutablelist).build();
            }
            suggestRoomRequest = list;
        }
    }

    private class RunningFuturesPool
    {

        public final Set futures = Collections.synchronizedSet(new HashSet());

        final void add(ListenableFuture listenablefuture)
        {
            if (!listenablefuture.isDone())
            {
                futures.add(listenablefuture);
                class .Lambda._cls0
                    implements Runnable
                {

                    private final RunningFuturesPool arg$1;
                    private final ListenableFuture arg$2;

                    public final void run()
                    {
                        RunningFuturesPool runningfuturespool = arg$1;
                        ListenableFuture listenablefuture1 = arg$2;
                        runningfuturespool.futures.remove(listenablefuture1);
                    }

                .Lambda._cls0(ListenableFuture listenablefuture)
                {
                    arg$1 = RunningFuturesPool.this;
                    arg$2 = listenablefuture;
                }
                }

                listenablefuture.addListener(new .Lambda._cls0(listenablefuture), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            }
        }

        RunningFuturesPool()
        {
        }
    }


    private class ToolbarCallback
        implements com.google.android.calendar.common.view.edittexttoolbar.EditTextToolbarPresenter.Callback
    {

        public final RoomBookingController this$0;
        private final Executor throttlingExecutor;

        public final void backPressed()
        {
            onBack();
        }

        public final void onFocus(String s)
        {
            if (state == 0)
            {
                updateRequestAndLog(s);
                s = RoomBookingController.this;
                s.persistViewControllerState();
                s.detachView();
                s.state = 4;
                s.attachView();
            }
        }

        public final void onRightButtonPressed()
        {
            onBack();
        }

        public final void searchStringChanged(String s)
        {
            class .Lambda._cls0
                implements Runnable
            {

                private final ToolbarCallback arg$1;
                private final String arg$2;

                public final void run()
                {
                    Object obj1 = arg$1;
                    Object obj = arg$2;
                    obj1 = ((ToolbarCallback) (obj1))._fld0;
                    if (((RoomBookingController) (obj1)).state == 0 && !TextUtils.isEmpty(((CharSequence) (obj))))
                    {
                        ((RoomBookingController) (obj1)).persistViewControllerState();
                        ((RoomBookingController) (obj1)).detachView();
                        obj1.state = 4;
                        ((RoomBookingController) (obj1)).attachView();
                    }
                    if (((RoomBookingController) (obj1)).state == 4)
                    {
                        boolean flag;
                        if (!TextUtils.isEmpty(((RoomBookingController) (obj1)).request.getQuery()))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (!flag)
                        {
                            RoomListViewController roomlistviewcontroller = ((RoomBookingController) (obj1)).roomListViewController;
                            roomlistviewcontroller.savedScrollPosition = roomlistviewcontroller.roomList.listView.getFirstVisiblePosition();
                        }
                    }
                    ((RoomBookingController) (obj1)).updateRequestAndLog(((String) (obj)));
                    obj = ((RoomBookingController) (obj1)).roomListViewController;
                    ((RoomListViewController) (obj)).mainView.setVisibility(0);
                    ((RoomListViewController) (obj)).emptyStateView.setVisibility(8);
                    ((RoomListViewController) (obj)).roomList.setItems(Collections.emptyList());
                    obj.hasShowMoreItem = false;
                    ((RoomListViewController) (obj)).roomList.setFooterMode(2);
                    ((RoomBookingController) (obj1)).sendRoomsRequest(false);
                }

                .Lambda._cls0(String s)
                {
                    arg$1 = ToolbarCallback.this;
                    arg$2 = s;
                }
            }

            throttlingExecutor.execute(new .Lambda._cls0(s));
        }

        ToolbarCallback()
        {
            this$0 = RoomBookingController.this;
            super();
            throttlingExecutor = new ThrottlingExecutor(CalendarExecutor.MAIN, 500L);
        }
    }


    private class _cls4
        implements com.google.android.calendar.timely.rooms.ui.ErrorViewController.Listener
    {

        private final RoomBookingController this$0;

        public final void onRoomRemoved(Room room)
        {
            removeRoom(room);
            room = errorViewController;
            ImmutableList immutablelist = request.getSelectedRooms();
            ((ErrorViewController) (room)).addedRoomsList.updateWithItems(RoomUiItemFactory.fromAddedRooms(immutablelist, ((ErrorViewController) (room)).isRoomRemovable));
        }

        public final void onTryAgainClicked()
        {
            RoomBookingController roombookingcontroller = RoomBookingController.this;
            int i = state;
            roombookingcontroller.persistViewControllerState();
            roombookingcontroller.detachView();
            roombookingcontroller.state = i;
            roombookingcontroller.attachView();
        }

        _cls4()
        {
            this$0 = RoomBookingController.this;
            super();
        }
    }


    private class _cls5
        implements com.google.android.calendar.timely.rooms.ui.suggestionsview.MeetingSuggestionsViewController.Listener
    {

        private final RoomBookingController this$0;

        public final void onAcceptRoom(Room room)
        {
            addRoom(room, true);
        }

        public final void onExpandMeetingLocation(AttendeeGroup attendeegroup, ImmutableSet immutableset)
        {
            RoomBookingController roombookingcontroller = RoomBookingController.this;
            boolean flag;
            if (roombookingcontroller.state == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            }
            if (attendeegroup.getRoomSuggestions().size() <= 5)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException();
            }
            roombookingcontroller.expandedMeetingLocation = new AutoValue_ExpandedMeetingLocation(attendeegroup.getCriteria().getPreferredBuildingName(), attendeegroup.getCriteria().getPreferredBuildingId(), attendeegroup.getRoomSuggestions(), attendeegroup.getCriteria().getAttendees(), immutableset);
            roombookingcontroller.expandedLocationRoomRequest = null;
            roombookingcontroller.persistViewControllerState();
            roombookingcontroller.detachView();
            roombookingcontroller.state = 1;
            roombookingcontroller.attachView();
            Object obj = roombookingcontroller.clientAnalytics;
            if (((ClientAnalytics) (obj)).currentRoomResponse != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            } else
            {
                attendeegroup = ((ClientAnalytics) (obj)).logger;
                immutableset = ((ClientAnalytics) (obj)).account;
                String s = ((ClientAnalytics) (obj)).calendarEventReference;
                obj = ((ClientAnalytics) (obj)).currentRoomResponse.getResponseId();
                attendeegroup.log(immutableset, attendeegroup.getRoomBookingProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.LOCATION_CARD_EXPANDED, null, ((String) (obj)), s, null, null, null, null, null, null, null, null, Collections.emptyList()));
                return;
            }
        }

        public final void onExpandSection()
        {
            sendRoomRequest();
        }

        public final void onFilterBarClicked()
        {
            RoomBookingController roombookingcontroller = RoomBookingController.this;
            roombookingcontroller.persistViewControllerState();
            roombookingcontroller.detachView();
            roombookingcontroller.state = 6;
            roombookingcontroller.attachView();
        }

        public final void onRemoveRoom(Room room)
        {
            removeRoom(room);
            sendRoomRequest();
        }

        public final void onToggleAudioRequirement$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NN4RRFDLPIUP31EHGIUGBKEHIMSP35CL3N4RRLE0TIILG_0()
        {
        }

        public final void onToggleVideoRequirement$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NN4RRFDLPIUP31EHGIUGBKEHIMSP35CL3N4RRLE0TIILG_0()
        {
        }

        _cls5()
        {
            this$0 = RoomBookingController.this;
            super();
        }
    }


    private class _cls6
        implements com.google.android.calendar.timely.rooms.ui.ExpandedLocationViewController.Listener
    {

        private final RoomBookingController this$0;

        public final void onNextPageRequested()
        {
            PaginatingClient paginatingclient = client;
            boolean flag;
            if (paginatingclient.extendableResult != null && paginatingclient.extendableResult.hasNextPage())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                setExpandedLocationRequestCabllback(client.getNextPage());
            }
        }

        public final void onRoomRemoved(Room room)
        {
            RoomBookingController roombookingcontroller = RoomBookingController.this;
            boolean flag;
            if (roombookingcontroller.state == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            }
            Object obj = roombookingcontroller.request;
            Object obj1 = ImmutableList.builder();
            Object obj2 = (ImmutableList)((RoomRequest) (obj)).getSelectedRooms();
            int j = ((ImmutableList) (obj2)).size();
            int i = 0;
            UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
            while (i < j) 
            {
                Object obj3 = (Room)((ImmutableList) (obj2)).get(i);
                String s = ((Room) (obj3)).getEmail();
                String s1 = room.getEmail();
                boolean flag1;
                if (s == s1 || s != null && s.equals(s1))
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (!flag1)
                {
                    obj3 = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj1)).add(obj3);
                }
                i++;
            }
            obj1.forceCopy = true;
            obj1 = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj1)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj1)).size);
            obj2 = ((RoomRequest) (obj)).getSelectedRooms();
            if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (!i)
            {
                obj = ((RoomRequest) (obj)).toBuilder().setSelectedRooms(((ImmutableList) (obj1))).build();
            }
            roombookingcontroller.request = ((RoomRequest) (obj));
            roombookingcontroller.updateSelectedRoomState(roombookingcontroller.request.getSelectedRooms());
            obj = roombookingcontroller.clientAnalytics;
            obj1 = ((ClientAnalytics) (obj)).logger;
            obj2 = ((ClientAnalytics) (obj)).calendarEventReference;
            room = room.getEmail();
            ((CalendarClientLogger) (obj1)).log(((ClientAnalytics) (obj)).account, ((CalendarClientLogger) (obj1)).getRoomBookingProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.ROOM_REMOVED, null, null, ((String) (obj2)), null, null, null, null, room, null, null, null, Collections.emptyList()));
            roombookingcontroller.persistViewControllerState();
            roombookingcontroller.detachView();
            roombookingcontroller.state = 0;
            roombookingcontroller.attachView();
        }

        public final void onRoomSelected(Room room)
        {
            boolean flag = true;
            RoomBookingController roombookingcontroller = RoomBookingController.this;
            if (roombookingcontroller.state != 1)
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            } else
            {
                roombookingcontroller.addRoom(room, false);
                roombookingcontroller.persistViewControllerState();
                roombookingcontroller.detachView();
                roombookingcontroller.state = 0;
                roombookingcontroller.attachView();
                return;
            }
        }

        _cls6()
        {
            this$0 = RoomBookingController.this;
            super();
        }
    }


    private class _cls2
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
        //                       4 87
        //                       5 80
        //                       6 80
        //                       7 99;
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
            FragmentActivity fragmentactivity = roombookingcontroller.activity;
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
            obj1 = (com.google.android.calendar.timely.rooms.net.RoomsRendezvousClient.PageableRoomResponse)obj;
            roombookingcontroller = RoomBookingController.this;
            flag1 = restoreScrollPosition;
            flag2 = isSearchResult;
            obj5 = ((com.google.android.calendar.timely.rooms.net.RoomsRendezvousClient.PageableRoomResponse) (obj1)).roomResponse;
            obj = roombookingcontroller.clientAnalytics;
            obj.currentRoomResponse = ((RoomResponse) (obj5));
            obj.lastResponse$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TP6URRDECNM6RREEHP6UR3CCLP2UGRCD5IMST21DPGMOUBKD5HN692ICLPN0RREEDIL8UBGCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.ROOM$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TP6URRDECNM6RREEHP6UR3CCLP2UGRCD5IMST21DPGMOUBKD5HN692ICLPN0RREEDIL8UBGCKTG____0;
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
        //                       1 326
        //                       2 281;
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
            if (((com.google.android.calendar.timely.rooms.net.RoomsRendezvousClient.PageableRoomResponse) (obj1)).roomResponse.getRoomFlatList() != null && !TextUtils.isEmpty(((com.google.android.calendar.timely.rooms.net.RoomsRendezvousClient.PageableRoomResponse) (obj1)).roomResponse.getRoomFlatList().pageToken))
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
            obj2 = ((RoomListViewController) (obj)).roomUiItemFactory.fromResult(((List) (obj2)), null, ((List) (obj4)), ((String) (obj7)), ((List) (obj5)), flag3, ((ImmutableSet) (obj6)));
            ((RoomListViewController) (obj)).roomList.setItems(((Collection) (obj2)));
            obj4 = com.google.android.calendar.timely.rooms.ui.RoomListViewController..Lambda._cls3.$instance;
            if (Iterators.indexOf(((Iterable) (obj2)).iterator(), ((Predicate) (obj4))) == -1)
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

        _cls2()
        {
            this$0 = RoomBookingController.this;
            restoreScrollPosition = flag;
            isSearchResult = flag1;
            super();
        }
    }


    private class _cls3
        implements FutureCallback
    {

        private final RoomBookingController this$0;

        public final void onFailure(Throwable throwable)
        {
            if (throwable instanceof CancellationException)
            {
                return;
            }
            ExpandedLocationViewController expandedlocationviewcontroller = expandedSuggestionsViewController;
            Object obj = expandedMeetingLocation.getRoomSuggestions();
            com.google.common.base.Function function = com.google.android.calendar.timely.rooms.data.ExpandedMeetingLocation..Lambda._cls0.$instance;
            if (obj instanceof RandomAccess)
            {
                obj = new com.google.common.collect.Lists.TransformingRandomAccessList(((List) (obj)), function);
            } else
            {
                obj = new com.google.common.collect.Lists.TransformingSequentialList(((List) (obj)), function);
            }
            expandedlocationviewcontroller.setRooms(((List) (obj)), Collections.emptyList(), false, RegularImmutableSet.EMPTY);
            _flddelegate.onConnectionError(throwable);
        }

        public final void onSuccess(Object obj)
        {
            com.google.android.calendar.timely.rooms.net.RoomsRendezvousClient.PageableRoomResponse pageableroomresponse = (com.google.android.calendar.timely.rooms.net.RoomsRendezvousClient.PageableRoomResponse)obj;
            ExpandedLocationViewController expandedlocationviewcontroller = expandedSuggestionsViewController;
            obj = expandedMeetingLocation.getRoomSuggestions();
            Object obj1 = com.google.android.calendar.timely.rooms.data.ExpandedMeetingLocation..Lambda._cls0.$instance;
            boolean flag;
            if (obj instanceof RandomAccess)
            {
                obj = new com.google.common.collect.Lists.TransformingRandomAccessList(((List) (obj)), ((com.google.common.base.Function) (obj1)));
            } else
            {
                obj = new com.google.common.collect.Lists.TransformingSequentialList(((List) (obj)), ((com.google.common.base.Function) (obj1)));
            }
            obj1 = pageableroomresponse.roomResponse.getRoomFlatList().rooms;
            if (pageableroomresponse.roomResponse.getRoomFlatList() != null && !TextUtils.isEmpty(pageableroomresponse.roomResponse.getRoomFlatList().pageToken))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            expandedlocationviewcontroller.setRooms(((List) (obj)), ((List) (obj1)), flag, expandedMeetingLocation.getAddedRoomEmails());
        }

        _cls3()
        {
            this$0 = RoomBookingController.this;
            super();
        }
    }


    private class Delegate
    {

        public abstract boolean isOnline();

        public abstract void onConnectionError(Throwable throwable);

        public abstract void onFinish(List list);

        public abstract void onWindowStateChanged();
    }


    private class _cls1
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
            obj1 = ((com.google.android.calendar.timely.rooms.net.RoomsRendezvousClient.PageableRoomResponse)obj).roomResponse;
            obj = clientAnalytics;
            obj.currentRoomResponse = ((RoomResponse) (obj1));
            obj.lastResponse$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TP6URRDECNM6RREEHP6UR3CCLP2UGRCD5IMST21DPGMOUBKD5HN692ICLPN0RREEDIL8UBGCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.ROOM$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TP6URRDECNM6RREEHP6UR3CCLP2UGRCD5IMST21DPGMOUBKD5HN692ICLPN0RREEDIL8UBGCKTG____0;
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
            RoomHierarchy roomhierarchy = ((RoomResponse) (obj1)).getRoomHierarchy();
            RoomRecommendations roomrecommendations = ((RoomResponse) (obj1)).getRoomRecommendations();
            obj1 = ((RoomResponse) (obj1)).getResolvedSelectedRooms();
            obj.suggestRoomRequest = null;
            obj5 = new AutoValue_RoomResponse(((RoomFlatList) (obj5)), roomhierarchy, roomrecommendations, ((String) (obj4)), true, ((ImmutableList) (obj1)));
            obj1 = ((RoomBookingController) (obj)).client;
            obj4 = ((RoomBookingController) (obj)).request;
            obj5 = new com.google.android.calendar.timely.rooms.net.RoomsRendezvousClient.PageableRoomResponse(((RoomResponse) (obj5)));
            ((PaginatingClient) (obj1)).underlying.injectResponse(obj4, obj5);
            obj1.lastRequest = ((com.google.android.calendar.timely.net.pagination.PageableRequest) (obj4));
            obj1.extendableResult = ((PageableResult) (obj5));
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
                obj = new MoreInLocationButton(new com.google.android.calendar.timely.rooms.ui.suggestionsview.UiItemManager..Lambda._cls6(uiitemmanager, attendeegroup1), uiitemmanager.context.getResources());
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
                        obj2 = new SuggestedRoom(room1, new com.google.android.calendar.timely.rooms.ui.suggestionsview.UiItemManager..Lambda._cls5(uiitemmanager, attendeegroup1), attendeegroup1.getCriteria());
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
                com.google.common.base.Function function = com.google.android.calendar.timely.rooms.ui.suggestionsview.UiItemManager..Lambda._cls0.$instance;
                boolean flag1;
                if (obj instanceof RandomAccess)
                {
                    obj = new com.google.common.collect.Lists.TransformingRandomAccessList(((List) (obj)), function);
                } else
                {
                    obj = new com.google.common.collect.Lists.TransformingSequentialList(((List) (obj)), function);
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
                    Account account = ((ClientAnalytics) (obj3)).account;
                    String s = ((ClientAnalytics) (obj3)).calendarEventReference;
                    obj3 = ((ClientAnalytics) (obj3)).currentRoomResponse.getResponseId();
                    calendarclientlogger.log(account, calendarclientlogger.getRoomBookingProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.STRUCTURED_ROOMS_VIEW_UPDATED, null, ((String) (obj3)), s, null, null, null, null, null, null, null, null, ((List) (obj))));
                    return;
                }
            }
            if (true) goto _L13; else goto _L12
_L12:
        }

        _cls1()
        {
            this$0 = RoomBookingController.this;
            super();
        }
    }

}
