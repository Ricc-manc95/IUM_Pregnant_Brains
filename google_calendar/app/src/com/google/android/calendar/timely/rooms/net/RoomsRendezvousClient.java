// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.text.TextUtils;
import com.google.android.calendar.timely.net.BaseClientFragment;
import com.google.android.calendar.timely.net.pagination.PageableResult;
import com.google.android.calendar.timely.rooms.data.Attendee;
import com.google.android.calendar.timely.rooms.data.AutoValue_Attendee;
import com.google.android.calendar.timely.rooms.data.AutoValue_AttendeeGroup;
import com.google.android.calendar.timely.rooms.data.AutoValue_RoomCriteria;
import com.google.android.calendar.timely.rooms.data.AutoValue_RoomHierarchyNode;
import com.google.android.calendar.timely.rooms.data.AutoValue_RoomRecommendations;
import com.google.android.calendar.timely.rooms.data.RecurringTimes;
import com.google.android.calendar.timely.rooms.data.Room;
import com.google.android.calendar.timely.rooms.data.RoomCriteria;
import com.google.android.calendar.timely.rooms.data.RoomFlatList;
import com.google.android.calendar.timely.rooms.data.RoomHierarchy;
import com.google.android.calendar.timely.rooms.data.RoomHierarchyNode;
import com.google.calendar.suggest.v2.AttendeeGroup;
import com.google.calendar.suggest.v2.CalendarEvent;
import com.google.calendar.suggest.v2.RecurringEventTimes;
import com.google.calendar.suggest.v2.ResponseStatus;
import com.google.calendar.suggest.v2.RoomAttendee;
import com.google.calendar.suggest.v2.RoomListingParams;
import com.google.calendar.suggest.v2.RoomListingResults;
import com.google.calendar.suggest.v2.RoomRecommendationsParams;
import com.google.calendar.suggest.v2.RoomRecommendationsResults;
import com.google.calendar.suggest.v2.RoomSuggestion;
import com.google.calendar.suggest.v2.SuggestRoomRequest;
import com.google.calendar.suggest.v2.SuggestRoomResponse;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.PrimitiveNonBoxingCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.rooms.net:
//            AutoValue_RoomResponse, RoomsRequestExecutor, RoomRequest, AutoValue_ResolveInfo, 
//            RoomRecommendationsParams, ConvertUtils, RoomListingParams, ResolveInfo, 
//            RoomResponse

public class RoomsRendezvousClient extends BaseClientFragment
{
    public static final class PageableRoomResponse
        implements PageableResult
    {

        public final RoomResponse roomResponse;

        public final boolean hasNextPage()
        {
            return roomResponse.getRoomFlatList() != null && !TextUtils.isEmpty(roomResponse.getRoomFlatList().pageToken);
        }

        public final Object merge(Object obj)
        {
            Object obj1 = (PageableRoomResponse)obj;
            if (roomResponse.getRoomFlatList() == null || ((PageableRoomResponse) (obj1)).roomResponse.getRoomFlatList() == null)
            {
                return this;
            } else
            {
                obj = roomResponse;
                obj1 = ((PageableRoomResponse) (obj1)).roomResponse;
                Object obj2 = ((RoomResponse) (obj)).getRoomFlatList();
                RoomFlatList roomflatlist = ((RoomResponse) (obj1)).getRoomFlatList();
                obj2 = new ArrayList(((RoomFlatList) (obj2)).rooms);
                ((ArrayList) (obj2)).addAll(roomflatlist.rooms);
                return new PageableRoomResponse(new AutoValue_RoomResponse(new RoomFlatList(((List) (obj2)), roomflatlist.pageToken), ((RoomResponse) (obj)).getRoomHierarchy(), ((RoomResponse) (obj)).getRoomRecommendations(), ((RoomResponse) (obj1)).getResponseId(), ((RoomResponse) (obj1)).queryMatchesRooms(), ((RoomResponse) (obj1)).getResolvedSelectedRooms()));
            }
        }

        public PageableRoomResponse(RoomResponse roomresponse)
        {
            if (roomresponse == null)
            {
                throw new NullPointerException();
            } else
            {
                roomResponse = (RoomResponse)roomresponse;
                return;
            }
        }
    }


    private RoomsRequestExecutor requestExecutor;

    public RoomsRendezvousClient()
    {
    }

    public static Bundle createArguments(String s, int i)
    {
        Bundle bundle = new Bundle();
        bundle.putString("account_email", s);
        bundle.putInt("rendezvous_target_environment", i);
        return bundle;
    }

    private static RoomResponse emptyResponse(SuggestRoomResponse suggestroomresponse)
    {
        RoomFlatList roomflatlist = new RoomFlatList(new ArrayList(), null);
        String s;
        boolean flag;
        if (suggestroomresponse == null)
        {
            s = null;
        } else
        {
            s = Platform.emptyToNull(suggestroomresponse.responseId_);
        }
        if (suggestroomresponse != null && suggestroomresponse.matchingRoomsCount_ > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return new AutoValue_RoomResponse(roomflatlist, null, null, s, flag, ImmutableList.of());
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (requestExecutor == null)
        {
            if (super.mHost == null)
            {
                bundle = null;
            } else
            {
                bundle = (FragmentActivity)super.mHost.mActivity;
            }
            requestExecutor = new RoomsRequestExecutor(bundle.getApplicationContext(), getArguments().getString("account_email", null), getArguments().getInt("rendezvous_target_environment"));
        }
    }

    protected final Object retrieveData(Object obj)
        throws Exception
    {
        Object obj1;
        Object obj2;
        Object obj3;
        AutoValue_ResolveInfo autovalue_resolveinfo;
        obj1 = (RoomRequest)obj;
        obj2 = ((RoomRequest) (obj1)).getAttendees();
        obj = new com.google.common.collect.ImmutableMap.Builder();
        Attendee attendee;
        for (obj2 = ((Iterable) (obj2)).iterator(); ((Iterator) (obj2)).hasNext(); ((com.google.common.collect.ImmutableMap.Builder) (obj)).put(attendee.getEmail(), attendee))
        {
            attendee = (Attendee)((Iterator) (obj2)).next();
        }

        obj = ((com.google.common.collect.ImmutableMap.Builder) (obj)).build();
        obj3 = ((RoomRequest) (obj1)).getSelectedRooms();
        obj2 = new com.google.common.collect.ImmutableMap.Builder();
        Room room;
        for (obj3 = ((Iterable) (obj3)).iterator(); ((Iterator) (obj3)).hasNext(); ((com.google.common.collect.ImmutableMap.Builder) (obj2)).put(room.getEmail(), Integer.valueOf(room.getAvailability())))
        {
            room = (Room)((Iterator) (obj3)).next();
        }

        autovalue_resolveinfo = new AutoValue_ResolveInfo(((ImmutableMap) (obj)), ((com.google.common.collect.ImmutableMap.Builder) (obj2)).build());
        obj2 = requestExecutor;
        obj3 = (com.google.calendar.suggest.v2.SuggestRoomRequest.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)SuggestRoomRequest.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        obj = ((RoomRequest) (obj1)).getQuery();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
        SuggestRoomRequest suggestroomrequest = (SuggestRoomRequest)((com.google.calendar.suggest.v2.SuggestRoomRequest.Builder) (obj3)).instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        suggestroomrequest.query_ = ((String) (obj));
        obj = ((RoomRequest) (obj1)).getRecommendationsParams();
        if (obj != null) goto _L2; else goto _L1
_L1:
        obj = RoomRecommendationsParams.DEFAULT_INSTANCE;
_L4:
        Object obj4;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
        obj4 = (SuggestRoomRequest)((com.google.calendar.suggest.v2.SuggestRoomRequest.Builder) (obj3)).instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        break; /* Loop/switch isn't completed */
_L2:
        Object obj7;
        obj4 = (com.google.calendar.suggest.v2.RoomRecommendationsParams.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)RoomRecommendationsParams.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        int i = ((com.google.android.calendar.timely.rooms.net.RoomRecommendationsParams) (obj)).getMaxSuggestions();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
        ((RoomRecommendationsParams)((com.google.calendar.suggest.v2.RoomRecommendationsParams.Builder) (obj4)).instance).maxSuggestions_ = i;
        boolean flag3 = ((com.google.android.calendar.timely.rooms.net.RoomRecommendationsParams) (obj)).preferLocationBasedSuggestions();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
        ((RoomRecommendationsParams)((com.google.calendar.suggest.v2.RoomRecommendationsParams.Builder) (obj4)).instance).preferLocationBasedSuggestions_ = flag3;
        flag3 = ((com.google.android.calendar.timely.rooms.net.RoomRecommendationsParams) (obj)).showUnavailable();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
        ((RoomRecommendationsParams)((com.google.calendar.suggest.v2.RoomRecommendationsParams.Builder) (obj4)).instance).showUnavailable_ = flag3;
        obj7 = ((com.google.android.calendar.timely.rooms.net.RoomRecommendationsParams) (obj)).getRoomCriteria();
        if (obj7 == null)
        {
            obj = new ArrayList();
        } else
        {
            obj = new ArrayList();
            obj7 = (ImmutableList)obj7;
            int j5 = ((ImmutableList) (obj7)).size();
            int l = 0;
            obj8 = (UnmodifiableIterator)null;
            while (l < j5) 
            {
                Object obj11 = (RoomCriteria)((ImmutableList) (obj7)).get(l);
                obj8 = (com.google.calendar.suggest.v2.RoomCriteria.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.calendar.suggest.v2.RoomCriteria.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                Object obj13 = ((RoomCriteria) (obj11)).getPreferredBuildingId();
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).copyOnWrite();
                com.google.calendar.suggest.v2.RoomCriteria roomcriteria = (com.google.calendar.suggest.v2.RoomCriteria)((com.google.calendar.suggest.v2.RoomCriteria.Builder) (obj8)).instance;
                if (obj13 == null)
                {
                    throw new NullPointerException();
                }
                roomcriteria.preferredBuildingId_ = ((String) (obj13));
                obj13 = ((RoomCriteria) (obj11)).getPreferredFloor();
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).copyOnWrite();
                roomcriteria = (com.google.calendar.suggest.v2.RoomCriteria)((com.google.calendar.suggest.v2.RoomCriteria.Builder) (obj8)).instance;
                if (obj13 == null)
                {
                    throw new NullPointerException();
                }
                roomcriteria.preferredFloor_ = ((String) (obj13));
                int l6 = ((RoomCriteria) (obj11)).getNumSeats();
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).copyOnWrite();
                ((com.google.calendar.suggest.v2.RoomCriteria)((com.google.calendar.suggest.v2.RoomCriteria.Builder) (obj8)).instance).numSeats_ = l6;
                obj13 = ConvertUtils.toGrpcAttendees(((RoomCriteria) (obj11)).getAttendees());
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).copyOnWrite();
                obj11 = (com.google.calendar.suggest.v2.RoomCriteria)((com.google.calendar.suggest.v2.RoomCriteria.Builder) (obj8)).instance;
                if (!((com.google.calendar.suggest.v2.RoomCriteria) (obj11)).attendees_.isModifiable())
                {
                    obj11.attendees_ = GeneratedMessageLite.mutableCopy(((com.google.calendar.suggest.v2.RoomCriteria) (obj11)).attendees_);
                }
                obj11 = ((com.google.calendar.suggest.v2.RoomCriteria) (obj11)).attendees_;
                Internal.checkNotNull(obj13);
                if (obj13 instanceof LazyStringList)
                {
                    List list1 = ((LazyStringList)obj13).getUnderlyingElements();
                    obj13 = (LazyStringList)obj11;
                    int i7 = ((List) (obj11)).size();
                    for (obj11 = list1.iterator(); ((Iterator) (obj11)).hasNext();)
                    {
                        Object obj14 = ((Iterator) (obj11)).next();
                        if (obj14 == null)
                        {
                            l = ((LazyStringList) (obj13)).size();
                            obj = (new StringBuilder(37)).append("Element at index ").append(l - i7).append(" is null.").toString();
                            for (l = ((LazyStringList) (obj13)).size() - 1; l >= i7; l--)
                            {
                                ((LazyStringList) (obj13)).remove(l);
                            }

                            throw new NullPointerException(((String) (obj)));
                        }
                        if (obj14 instanceof ByteString)
                        {
                            ((LazyStringList) (obj13)).add((ByteString)obj14);
                        } else
                        {
                            ((LazyStringList) (obj13)).add((String)obj14);
                        }
                    }

                } else
                if (obj13 instanceof PrimitiveNonBoxingCollection)
                {
                    ((List) (obj11)).addAll((Collection)obj13);
                } else
                {
                    if ((obj11 instanceof ArrayList) && (obj13 instanceof Collection))
                    {
                        ArrayList arraylist2 = (ArrayList)obj11;
                        int j7 = ((List) (obj11)).size();
                        arraylist2.ensureCapacity(((Collection)obj13).size() + j7);
                    }
                    int k7 = ((List) (obj11)).size();
                    obj13 = ((Iterable) (obj13)).iterator();
                    while (((Iterator) (obj13)).hasNext()) 
                    {
                        Object obj15 = ((Iterator) (obj13)).next();
                        if (obj15 == null)
                        {
                            int i1 = ((List) (obj11)).size();
                            obj = (new StringBuilder(37)).append("Element at index ").append(i1 - k7).append(" is null.").toString();
                            for (int j1 = ((List) (obj11)).size() - 1; j1 >= k7; j1--)
                            {
                                ((List) (obj11)).remove(j1);
                            }

                            throw new NullPointerException(((String) (obj)));
                        }
                        ((List) (obj11)).add(obj15);
                    }
                }
                ((List) (obj)).add((com.google.calendar.suggest.v2.RoomCriteria)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).build());
                l++;
            }
        }
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
        obj7 = (RoomRecommendationsParams)((com.google.calendar.suggest.v2.RoomRecommendationsParams.Builder) (obj4)).instance;
        if (!((RoomRecommendationsParams) (obj7)).criteria_.isModifiable())
        {
            obj7.criteria_ = GeneratedMessageLite.mutableCopy(((RoomRecommendationsParams) (obj7)).criteria_);
        }
        obj7 = ((RoomRecommendationsParams) (obj7)).criteria_;
        Internal.checkNotNull(obj);
        if (obj instanceof LazyStringList)
        {
            List list = ((LazyStringList)obj).getUnderlyingElements();
            obj = (LazyStringList)obj7;
            int i5 = ((List) (obj7)).size();
            for (obj7 = list.iterator(); ((Iterator) (obj7)).hasNext();)
            {
                Object obj8 = ((Iterator) (obj7)).next();
                if (obj8 == null)
                {
                    int j = ((LazyStringList) (obj)).size();
                    obj1 = (new StringBuilder(37)).append("Element at index ").append(j - i5).append(" is null.").toString();
                    for (int k = ((LazyStringList) (obj)).size() - 1; k >= i5; k--)
                    {
                        ((LazyStringList) (obj)).remove(k);
                    }

                    throw new NullPointerException(((String) (obj1)));
                }
                if (obj8 instanceof ByteString)
                {
                    ((LazyStringList) (obj)).add((ByteString)obj8);
                } else
                {
                    ((LazyStringList) (obj)).add((String)obj8);
                }
            }

        } else
        {
            if (!(obj instanceof PrimitiveNonBoxingCollection))
            {
                break; /* Loop/switch isn't completed */
            }
            ((List) (obj7)).addAll((Collection)obj);
        }
_L5:
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
        ((RoomRecommendationsParams)((com.google.calendar.suggest.v2.RoomRecommendationsParams.Builder) (obj4)).instance).smartRestrictMax_ = true;
        obj = (RoomRecommendationsParams)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).build();
        if (true) goto _L4; else goto _L3
_L3:
        if ((obj7 instanceof ArrayList) && (obj instanceof Collection))
        {
            ArrayList arraylist1 = (ArrayList)obj7;
            int k1 = ((List) (obj7)).size();
            arraylist1.ensureCapacity(((Collection)obj).size() + k1);
        }
        int k5 = ((List) (obj7)).size();
        obj = ((Iterable) (obj)).iterator();
        while (((Iterator) (obj)).hasNext()) 
        {
            Object obj9 = ((Iterator) (obj)).next();
            if (obj9 == null)
            {
                int l1 = ((List) (obj7)).size();
                obj = (new StringBuilder(37)).append("Element at index ").append(l1 - k5).append(" is null.").toString();
                for (int i2 = ((List) (obj7)).size() - 1; i2 >= k5; i2--)
                {
                    ((List) (obj7)).remove(i2);
                }

                throw new NullPointerException(((String) (obj)));
            }
            ((List) (obj7)).add(obj9);
        }
          goto _L5
        if (true) goto _L4; else goto _L6
_L6:
        Object obj10;
        Object obj12;
        RoomAttendee roomattendee;
        int i3;
        obj4.recommendationsParams_ = ((RoomRecommendationsParams) (obj));
        obj4 = ((RoomRequest) (obj1)).getCalendarEvent();
        obj = (com.google.calendar.suggest.v2.CalendarEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)CalendarEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        if (((com.google.android.calendar.timely.rooms.data.CalendarEvent) (obj4)).getCalendarId() != null)
        {
            obj7 = ((com.google.android.calendar.timely.rooms.data.CalendarEvent) (obj4)).getCalendarId();
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
            CalendarEvent calendarevent = (CalendarEvent)((com.google.calendar.suggest.v2.CalendarEvent.Builder) (obj)).instance;
            if (obj7 == null)
            {
                throw new NullPointerException();
            }
            calendarevent.calendarId_ = ((String) (obj7));
        }
        if (((com.google.android.calendar.timely.rooms.data.CalendarEvent) (obj4)).getEventId() != null)
        {
            obj4 = ((com.google.android.calendar.timely.rooms.data.CalendarEvent) (obj4)).getEventId();
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
            obj7 = (CalendarEvent)((com.google.calendar.suggest.v2.CalendarEvent.Builder) (obj)).instance;
            if (obj4 == null)
            {
                throw new NullPointerException();
            }
            obj7.eventId_ = ((String) (obj4));
        }
        obj = (CalendarEvent)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
        obj4 = (SuggestRoomRequest)((com.google.calendar.suggest.v2.SuggestRoomRequest.Builder) (obj3)).instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        obj4.existingEvent_ = ((CalendarEvent) (obj));
        obj4 = ((RoomRequest) (obj1)).getListingParams();
        if (obj4 == null)
        {
            obj = RoomListingParams.DEFAULT_INSTANCE;
        } else
        {
            obj = (com.google.calendar.suggest.v2.RoomListingParams.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)RoomListingParams.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            boolean flag4 = ((com.google.android.calendar.timely.rooms.net.RoomListingParams) (obj4)).isShowUnavailable();
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
            ((RoomListingParams)((com.google.calendar.suggest.v2.RoomListingParams.Builder) (obj)).instance).showUnavailable_ = flag4;
            flag4 = ((com.google.android.calendar.timely.rooms.net.RoomListingParams) (obj4)).isPreferHierarchy();
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
            ((RoomListingParams)((com.google.calendar.suggest.v2.RoomListingParams.Builder) (obj)).instance).preferHierarchy_ = flag4;
            if (((com.google.android.calendar.timely.rooms.net.RoomListingParams) (obj4)).getMaxPageSize() != null)
            {
                int j2 = ((com.google.android.calendar.timely.rooms.net.RoomListingParams) (obj4)).getMaxPageSize().intValue();
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                ((RoomListingParams)((com.google.calendar.suggest.v2.RoomListingParams.Builder) (obj)).instance).maxResultsPerPage_ = j2;
            }
            if (((com.google.android.calendar.timely.rooms.net.RoomListingParams) (obj4)).getPageToken() != null)
            {
                obj4 = ((com.google.android.calendar.timely.rooms.net.RoomListingParams) (obj4)).getPageToken();
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                obj7 = (RoomListingParams)((com.google.calendar.suggest.v2.RoomListingParams.Builder) (obj)).instance;
                if (obj4 == null)
                {
                    throw new NullPointerException();
                }
                obj7.pageToken_ = ((String) (obj4));
            }
            obj = (RoomListingParams)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build();
        }
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
        obj4 = (SuggestRoomRequest)((com.google.calendar.suggest.v2.SuggestRoomRequest.Builder) (obj3)).instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        obj4.listingParams_ = ((RoomListingParams) (obj));
        obj4 = ConvertUtils.toGrpcAttendees(((RoomRequest) (obj1)).getAttendees());
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
        obj = (SuggestRoomRequest)((com.google.calendar.suggest.v2.SuggestRoomRequest.Builder) (obj3)).instance;
        if (!((SuggestRoomRequest) (obj)).attendees_.isModifiable())
        {
            obj.attendees_ = GeneratedMessageLite.mutableCopy(((SuggestRoomRequest) (obj)).attendees_);
        }
        obj = ((SuggestRoomRequest) (obj)).attendees_;
        Internal.checkNotNull(obj4);
        if (obj4 instanceof LazyStringList)
        {
            obj7 = ((LazyStringList)obj4).getUnderlyingElements();
            obj4 = (LazyStringList)obj;
            int l5 = ((List) (obj)).size();
            for (obj = ((List) (obj7)).iterator(); ((Iterator) (obj)).hasNext();)
            {
                obj7 = ((Iterator) (obj)).next();
                if (obj7 == null)
                {
                    int k2 = ((LazyStringList) (obj4)).size();
                    obj = (new StringBuilder(37)).append("Element at index ").append(k2 - l5).append(" is null.").toString();
                    for (int l2 = ((LazyStringList) (obj4)).size() - 1; l2 >= l5; l2--)
                    {
                        ((LazyStringList) (obj4)).remove(l2);
                    }

                    throw new NullPointerException(((String) (obj)));
                }
                if (obj7 instanceof ByteString)
                {
                    ((LazyStringList) (obj4)).add((ByteString)obj7);
                } else
                {
                    ((LazyStringList) (obj4)).add((String)obj7);
                }
            }

        } else
        if (obj4 instanceof PrimitiveNonBoxingCollection)
        {
            ((List) (obj)).addAll((Collection)obj4);
        } else
        {
            if ((obj instanceof ArrayList) && (obj4 instanceof Collection))
            {
                obj7 = (ArrayList)obj;
                i3 = ((List) (obj)).size();
                ((ArrayList) (obj7)).ensureCapacity(((Collection)obj4).size() + i3);
            }
            int i6 = ((List) (obj)).size();
            obj4 = ((Iterable) (obj4)).iterator();
            while (((Iterator) (obj4)).hasNext()) 
            {
                obj7 = ((Iterator) (obj4)).next();
                if (obj7 == null)
                {
                    i3 = ((List) (obj)).size();
                    obj1 = (new StringBuilder(37)).append("Element at index ").append(i3 - i6).append(" is null.").toString();
                    for (i3 = ((List) (obj)).size() - 1; i3 >= i6; i3--)
                    {
                        ((List) (obj)).remove(i3);
                    }

                    throw new NullPointerException(((String) (obj1)));
                }
                ((List) (obj)).add(obj7);
            }
        }
        obj4 = ((RoomRequest) (obj1)).getSelectedRooms();
        obj7 = ImmutableList.builder();
        i3 = 0;
_L13:
        if (i3 >= ((List) (obj4)).size())
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (Room)((List) (obj4)).get(i3);
        obj10 = (com.google.calendar.suggest.v2.RoomAttendee.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)RoomAttendee.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        obj12 = ((Room) (obj)).getEmail();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj10)).copyOnWrite();
        roomattendee = (RoomAttendee)((com.google.calendar.suggest.v2.RoomAttendee.Builder) (obj10)).instance;
        if (obj12 == null)
        {
            throw new NullPointerException();
        }
        roomattendee.email_ = ((String) (obj12));
        ((Room) (obj)).getAvailability();
        JVM INSTR tableswitch 1 2: default 2468
    //                   1 2499
    //                   2 2506;
           goto _L7 _L8 _L9
_L7:
        obj = ResponseStatus.NEEDS_ACTION;
_L11:
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj10)).copyOnWrite();
        obj12 = (RoomAttendee)((com.google.calendar.suggest.v2.RoomAttendee.Builder) (obj10)).instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        break; /* Loop/switch isn't completed */
_L8:
        obj = ResponseStatus.ACCEPTED;
        continue; /* Loop/switch isn't completed */
_L9:
        obj = ResponseStatus.DECLINED;
        if (true) goto _L11; else goto _L10
_L10:
        if (obj == ResponseStatus.UNRECOGNIZED)
        {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        obj12.responseStatus_ = ((ResponseStatus) (obj)).value;
        obj = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj7)).add((RoomAttendee)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj10)).build());
        i3++;
        if (true) goto _L13; else goto _L12
_L12:
        obj7.forceCopy = true;
        Object obj5 = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj7)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj7)).size);
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
        obj = (SuggestRoomRequest)((com.google.calendar.suggest.v2.SuggestRoomRequest.Builder) (obj3)).instance;
        if (!((SuggestRoomRequest) (obj)).selectedRooms_.isModifiable())
        {
            obj.selectedRooms_ = GeneratedMessageLite.mutableCopy(((SuggestRoomRequest) (obj)).selectedRooms_);
        }
        obj = ((SuggestRoomRequest) (obj)).selectedRooms_;
        Internal.checkNotNull(obj5);
        if (obj5 instanceof LazyStringList)
        {
            obj7 = ((LazyStringList)obj5).getUnderlyingElements();
            obj5 = (LazyStringList)obj;
            int j6 = ((List) (obj)).size();
            for (obj = ((List) (obj7)).iterator(); ((Iterator) (obj)).hasNext();)
            {
                obj7 = ((Iterator) (obj)).next();
                if (obj7 == null)
                {
                    int j3 = ((LazyStringList) (obj5)).size();
                    obj = (new StringBuilder(37)).append("Element at index ").append(j3 - j6).append(" is null.").toString();
                    for (int k3 = ((LazyStringList) (obj5)).size() - 1; k3 >= j6; k3--)
                    {
                        ((LazyStringList) (obj5)).remove(k3);
                    }

                    throw new NullPointerException(((String) (obj)));
                }
                if (obj7 instanceof ByteString)
                {
                    ((LazyStringList) (obj5)).add((ByteString)obj7);
                } else
                {
                    ((LazyStringList) (obj5)).add((String)obj7);
                }
            }

        } else
        if (obj5 instanceof PrimitiveNonBoxingCollection)
        {
            ((List) (obj)).addAll((Collection)obj5);
        } else
        {
            if ((obj instanceof ArrayList) && (obj5 instanceof Collection))
            {
                obj7 = (ArrayList)obj;
                int l3 = ((List) (obj)).size();
                ((ArrayList) (obj7)).ensureCapacity(((Collection)obj5).size() + l3);
            }
            int k6 = ((List) (obj)).size();
            obj5 = ((Iterable) (obj5)).iterator();
            while (((Iterator) (obj5)).hasNext()) 
            {
                obj7 = ((Iterator) (obj5)).next();
                if (obj7 == null)
                {
                    int i4 = ((List) (obj)).size();
                    obj1 = (new StringBuilder(37)).append("Element at index ").append(i4 - k6).append(" is null.").toString();
                    for (int j4 = ((List) (obj)).size() - 1; j4 >= k6; j4--)
                    {
                        ((List) (obj)).remove(j4);
                    }

                    throw new NullPointerException(((String) (obj1)));
                }
                ((List) (obj)).add(obj7);
            }
        }
        if (((RoomRequest) (obj1)).getBuildingId() != null)
        {
            obj = ((RoomRequest) (obj1)).getBuildingId();
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
            obj5 = (SuggestRoomRequest)((com.google.calendar.suggest.v2.SuggestRoomRequest.Builder) (obj3)).instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            obj5.buildingId_ = ((String) (obj));
        }
        if (((RoomRequest) (obj1)).getRecurringTimes() == null || ((RoomRequest) (obj1)).getRecurringTimes().getRecurrenceOption() == 2)
        {
            obj = ConvertUtils.toGrpcSingleEventTime(((RoomRequest) (obj1)).getSingleEventTime());
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
            SuggestRoomRequest suggestroomrequest1 = (SuggestRoomRequest)((com.google.calendar.suggest.v2.SuggestRoomRequest.Builder) (obj3)).instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            suggestroomrequest1.times_ = obj;
            suggestroomrequest1.timesCase_ = 2;
        } else
        {
            obj6 = ((RoomRequest) (obj1)).getRecurringTimes();
            obj7 = (com.google.calendar.suggest.v2.RecurringEventTimes.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)RecurringEventTimes.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            if (((RecurringTimes) (obj6)).getFirstEventTime() != null)
            {
                obj = ConvertUtils.toGrpcSingleEventTime(((RecurringTimes) (obj6)).getFirstEventTime());
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj7)).copyOnWrite();
                obj10 = (RecurringEventTimes)((com.google.calendar.suggest.v2.RecurringEventTimes.Builder) (obj7)).instance;
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                obj10.firstEventTime_ = ((com.google.calendar.suggest.v2.SingleEventTime) (obj));
            }
            if (((RecurringTimes) (obj6)).getTimezone() != null)
            {
                obj = ((RecurringTimes) (obj6)).getTimezone();
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj7)).copyOnWrite();
                obj10 = (RecurringEventTimes)((com.google.calendar.suggest.v2.RecurringEventTimes.Builder) (obj7)).instance;
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                obj10.timezone_ = ((String) (obj));
            }
            obj10 = ((RecurringTimes) (obj6)).getRecurrenceRule();
            if (obj10 != null)
            {
                boolean flag;
                if (!((String) (obj10)).startsWith("RRULE:"))
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
                obj = String.valueOf("RRULE:");
                obj10 = String.valueOf(obj10);
                if (((String) (obj10)).length() != 0)
                {
                    obj = ((String) (obj)).concat(((String) (obj10)));
                } else
                {
                    obj = new String(((String) (obj)));
                }
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj7)).copyOnWrite();
                obj10 = (RecurringEventTimes)((com.google.calendar.suggest.v2.RecurringEventTimes.Builder) (obj7)).instance;
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                obj10.recurrenceSpecCase_ = 3;
                obj10.recurrenceSpec_ = obj;
            }
            boolean flag5 = ((RecurringTimes) (obj6)).getConsiderExceptions();
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj7)).copyOnWrite();
            ((RecurringEventTimes)((com.google.calendar.suggest.v2.RecurringEventTimes.Builder) (obj7)).instance).considerExceptions_ = flag5;
            obj = (RecurringEventTimes)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj7)).build();
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
            obj6 = (SuggestRoomRequest)((com.google.calendar.suggest.v2.SuggestRoomRequest.Builder) (obj3)).instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            obj6.times_ = obj;
            obj6.timesCase_ = 3;
        }
        if (((RoomRequest) (obj1)).getHierarchyNode() != null)
        {
            obj = ((RoomRequest) (obj1)).getHierarchyNode().getId();
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
            Object obj6 = (SuggestRoomRequest)((com.google.calendar.suggest.v2.SuggestRoomRequest.Builder) (obj3)).instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            obj6.hierarchyNodeId_ = ((String) (obj));
        }
        if (!TextUtils.isEmpty(((RoomRequest) (obj1)).getCalendarEventReference()))
        {
            obj = ((RoomRequest) (obj1)).getCalendarEventReference();
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
            obj1 = (SuggestRoomRequest)((com.google.calendar.suggest.v2.SuggestRoomRequest.Builder) (obj3)).instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            obj1.eventReference_ = ((String) (obj));
        }
        obj = (SuggestRoomRequest)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).build();
        obj7 = (SuggestRoomResponse)((RoomsRequestExecutor) (obj2)).handleCall(((RoomsRequestExecutor) (obj2)).suggestRoomCall, obj);
        if (obj7 != null) goto _L15; else goto _L14
_L14:
        obj = emptyResponse(((SuggestRoomResponse) (obj7)));
_L40:
        return new PageableRoomResponse(((RoomResponse) (obj)));
_L15:
        AttendeeGroup attendeegroup;
        ImmutableList immutablelist;
        Object obj16;
        boolean flag1;
        if (((SuggestRoomResponse) (obj7)).listingResults_ != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            obj = emptyResponse(((SuggestRoomResponse) (obj7)));
            continue; /* Loop/switch isn't completed */
        }
        Iterator iterator1;
        ImmutableMap immutablemap;
        Iterator iterator2;
        if (((SuggestRoomResponse) (obj7)).listingResults_ == null)
        {
            obj = RoomListingResults.DEFAULT_INSTANCE;
        } else
        {
            obj = ((SuggestRoomResponse) (obj7)).listingResults_;
        }
        if (!((RoomListingResults) (obj)).rooms_.isEmpty()) goto _L17; else goto _L16
_L16:
        obj1 = null;
_L27:
        ArrayList arraylist;
        if (((SuggestRoomResponse) (obj7)).listingResults_ == null)
        {
            obj = RoomListingResults.DEFAULT_INSTANCE;
        } else
        {
            obj = ((SuggestRoomResponse) (obj7)).listingResults_;
        }
        obj = ((RoomListingResults) (obj)).hierarchyNodes_;
        if (!((List) (obj)).isEmpty()) goto _L19; else goto _L18
_L18:
        obj2 = null;
_L31:
        Iterator iterator;
        if (((SuggestRoomResponse) (obj7)).recommendationsResults_ == null)
        {
            obj = RoomRecommendationsResults.DEFAULT_INSTANCE;
        } else
        {
            obj = ((SuggestRoomResponse) (obj7)).recommendationsResults_;
        }
        obj10 = ConvertUtils.fromGrpcRoomSuggestions(((RoomRecommendationsResults) (obj)).recommendedRooms_);
        obj = ((RoomRecommendationsResults) (obj)).locations_;
        obj12 = ImmutableList.builder();
        iterator1 = ((List) (obj)).iterator();
_L38:
        do
        {
            if (!iterator1.hasNext())
            {
                break MISSING_BLOCK_LABEL_4506;
            }
            attendeegroup = (AttendeeGroup)iterator1.next();
            int k4;
            if (attendeegroup.criteria_ != null)
            {
                k4 = 1;
            } else
            {
                k4 = 0;
            }
        } while (k4 == 0);
        immutablelist = ConvertUtils.fromGrpcRoomSuggestions(attendeegroup.roomSuggestions_);
        if (attendeegroup.criteria_ == null)
        {
            obj3 = com.google.calendar.suggest.v2.RoomCriteria.DEFAULT_INSTANCE;
        } else
        {
            obj3 = attendeegroup.criteria_;
        }
        obj = ((com.google.calendar.suggest.v2.RoomCriteria) (obj3)).attendees_;
        immutablemap = autovalue_resolveinfo.getOriginalAttendees();
        obj16 = ImmutableList.builder();
        iterator2 = ((List) (obj)).iterator();
_L21:
        if (!iterator2.hasNext())
        {
            break MISSING_BLOCK_LABEL_4381;
        }
        obj = (com.google.calendar.suggest.v2.Attendee)iterator2.next();
        if (!immutablemap.containsKey(((com.google.calendar.suggest.v2.Attendee) (obj)).email_))
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (Attendee)immutablemap.get(((com.google.calendar.suggest.v2.Attendee) (obj)).email_);
_L36:
        obj = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj16)).add(obj);
        if (true) goto _L21; else goto _L20
_L17:
        obj1 = ((RoomListingResults) (obj)).rooms_;
        arraylist = new ArrayList();
        obj10 = ((List) (obj1)).iterator();
_L25:
        do
        {
            if (!((Iterator) (obj10)).hasNext())
            {
                break MISSING_BLOCK_LABEL_4050;
            }
            obj2 = (RoomSuggestion)((Iterator) (obj10)).next();
            if (((RoomSuggestion) (obj2)).room_ != null)
            {
                k4 = 1;
            } else
            {
                k4 = 0;
            }
        } while (k4 == 0);
        if (((RoomSuggestion) (obj2)).room_ == null)
        {
            obj1 = com.google.calendar.suggest.v2.Room.DEFAULT_INSTANCE;
        } else
        {
            obj1 = ((RoomSuggestion) (obj2)).room_;
        }
        obj3 = com.google.calendar.suggest.v2.RoomSuggestion.Availability.forNumber(((RoomSuggestion) (obj2)).availability_);
        obj2 = obj3;
        if (obj3 == null)
        {
            obj2 = com.google.calendar.suggest.v2.RoomSuggestion.Availability.UNRECOGNIZED;
        }
        ((com.google.calendar.suggest.v2.RoomSuggestion.Availability) (obj2)).ordinal();
        JVM INSTR tableswitch 1 2: default 4004
    //                   1 4038
    //                   2 4044;
           goto _L22 _L23 _L24
_L24:
        break MISSING_BLOCK_LABEL_4044;
_L22:
        k4 = 0;
_L26:
        arraylist.add(ConvertUtils.fromGrpcRoom(((com.google.calendar.suggest.v2.Room) (obj1)), k4));
          goto _L25
_L23:
        k4 = 1;
          goto _L26
        k4 = 2;
          goto _L26
        obj1 = new RoomFlatList(arraylist, ((RoomListingResults) (obj)).pageToken_);
          goto _L27
_L19:
        obj3 = new ArrayList();
        iterator = ((List) (obj)).iterator();
_L29:
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_4227;
        }
        obj10 = (com.google.calendar.suggest.v2.RoomListingResults.RoomHierarchyNode)iterator.next();
        obj12 = ((com.google.calendar.suggest.v2.RoomListingResults.RoomHierarchyNode) (obj10)).nodeId_;
        obj2 = com.google.calendar.suggest.v2.RoomListingResults.RoomHierarchyNode.NodeType.forNumber(((com.google.calendar.suggest.v2.RoomListingResults.RoomHierarchyNode) (obj10)).type_);
        obj = obj2;
        if (obj2 == null)
        {
            obj = com.google.calendar.suggest.v2.RoomListingResults.RoomHierarchyNode.NodeType.UNRECOGNIZED;
        }
        obj2 = ((com.google.calendar.suggest.v2.RoomListingResults.RoomHierarchyNode) (obj10)).name_;
        switch (((com.google.calendar.suggest.v2.RoomListingResults.RoomHierarchyNode.NodeType) (obj)).ordinal())
        {
        default:
            if (TextUtils.isEmpty(((CharSequence) (obj2))))
            {
                k4 = 1;
            } else
            {
                k4 = 0;
            }
            break;

        case 0: // '\0'
            break; /* Loop/switch isn't completed */

        case 1: // '\001'
            break MISSING_BLOCK_LABEL_4215;
        }
_L30:
        ((List) (obj3)).add(new AutoValue_RoomHierarchyNode(((String) (obj12)), k4, ((com.google.calendar.suggest.v2.RoomListingResults.RoomHierarchyNode) (obj10)).name_));
        if (true) goto _L29; else goto _L28
_L28:
        k4 = 0;
          goto _L30
        k4 = 1;
          goto _L30
        obj2 = new RoomHierarchy(((List) (obj3)));
          goto _L31
_L20:
        String s3;
        boolean flag6;
        s3 = ((com.google.calendar.suggest.v2.Attendee) (obj)).email_;
        flag6 = Boolean.TRUE.equals(Boolean.valueOf(((com.google.calendar.suggest.v2.Attendee) (obj)).organizer_));
        ResponseStatus responsestatus = ResponseStatus.forNumber(((com.google.calendar.suggest.v2.Attendee) (obj)).responseStatus_);
        obj = responsestatus;
        if (responsestatus == null)
        {
            obj = ResponseStatus.UNRECOGNIZED;
        }
        ((ResponseStatus) (obj)).ordinal();
        JVM INSTR tableswitch 1 3: default 4336
    //                   1 4360
    //                   2 4367
    //                   3 4374;
           goto _L32 _L33 _L34 _L35
_L35:
        break MISSING_BLOCK_LABEL_4374;
_L32:
        obj = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.NEEDS_ACTION;
_L37:
        obj = new AutoValue_Attendee(s3, Platform.emptyToNull(null), flag6, ((com.google.android.calendar.api.event.attendee.Response.ResponseStatus) (obj)));
          goto _L36
_L33:
        obj = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED;
          goto _L37
_L34:
        obj = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.TENTATIVE;
          goto _L37
        obj = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.ACCEPTED;
          goto _L37
        obj16.forceCopy = true;
        obj = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj16)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj16)).size);
        String s1 = Platform.emptyToNull(((com.google.calendar.suggest.v2.RoomCriteria) (obj3)).preferredBuildingName_);
        String s2 = Platform.emptyToNull(((com.google.calendar.suggest.v2.RoomCriteria) (obj3)).preferredBuildingId_);
        obj16 = Platform.emptyToNull(((com.google.calendar.suggest.v2.RoomCriteria) (obj3)).preferredFloor_);
        obj3 = Integer.valueOf(((com.google.calendar.suggest.v2.RoomCriteria) (obj3)).numSeats_);
        int l4;
        if (obj3 == null)
        {
            l4 = 0;
        } else
        {
            l4 = ((Integer) (obj3)).intValue();
        }
        obj = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj12)).add(new AutoValue_AttendeeGroup(immutablelist, new AutoValue_RoomCriteria(((ImmutableList) (obj)), s1, s2, ((String) (obj16)), l4), attendeegroup.displayName_, attendeegroup.hierarchyNodeId_));
          goto _L38
        obj12.forceCopy = true;
        obj = new AutoValue_RoomRecommendations(((ImmutableList) (obj10)), ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj12)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj12)).size));
        String s = Platform.emptyToNull(((SuggestRoomResponse) (obj7)).responseId_);
        Integer integer = Integer.valueOf(((SuggestRoomResponse) (obj7)).matchingRoomsCount_);
        boolean flag2;
        boolean flag7;
        if (integer == null || integer.intValue() == 0)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!flag2)
        {
            flag7 = true;
        } else
        {
            flag7 = false;
        }
        obj = new AutoValue_RoomResponse(((RoomFlatList) (obj1)), ((RoomHierarchy) (obj2)), ((com.google.android.calendar.timely.rooms.data.RoomRecommendations) (obj)), s, flag7, ConvertUtils.fromGrpcRoomSuggestions(((SuggestRoomResponse) (obj7)).resolvedSelectedRooms_));
        if (true) goto _L40; else goto _L39
_L39:
    }
}
