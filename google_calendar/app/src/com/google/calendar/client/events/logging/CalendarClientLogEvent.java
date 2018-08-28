// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.client.events.logging;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Protobuf;
import com.google.protobuf.ProtobufArrayList;
import com.google.protobuf.RawMessageInfo;
import com.google.protobuf.Schema;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

// Referenced classes of package com.google.calendar.client.events.logging:
//            CalendarEventInfo, CustomTimeInfo, RoomViewInfo, RoomInfo, 
//            ServiceResponseId, TimeSuggestionViewInfo, UssClientState

public final class CalendarClientLogEvent extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(CalendarClientLogEvent.DEFAULT_INSTANCE);
        }
    }

    public static final class Type extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Type $VALUES[];
        private static final Type ALL_STRUCTURED_ROOMS_ADDED;
        public static final Type EVENT_CREATED;
        private static final Type EVENT_CREATION_REQUEST_SUCCESSFUL;
        public static final Type EVENT_DETAILS_SESSION_ENDED;
        public static final Type EVENT_DETAILS_SESSION_STARTED;
        public static final Type EVENT_TIME_CHANGED;
        public static final Type EVENT_UPDATED;
        public static final Type FIND_TIME_BUTTON_CLICKED;
        public static final Type FIND_TIME_BUTTON_HIDDEN;
        public static final Type FIND_TIME_BUTTON_SHOWN;
        public static final Type FIND_TIME_FILTERS_CHANGED;
        public static final Type FIND_TIME_GRID_VIEW_CUSTOM_TIME_ACCEPTED;
        public static final Type FIND_TIME_GRID_VIEW_OPENED;
        private static final Type FIND_TIME_GRID_VIEW_PREVIEWED;
        private static final Type FIND_TIME_GRID_VIEW_PREVIEW_SUGGESTION_ACCEPTED;
        public static final Type FIND_TIME_GRID_VIEW_SUGGESTION_ACCEPTED;
        private static final Type FIND_TIME_GUEST_HIDDEN;
        private static final Type FIND_TIME_GUEST_LIST_CHANGED;
        private static final Type FIND_TIME_GUEST_UNHIDDEN;
        private static final Type FIND_TIME_GUEST_VISIBILITY_TOGGLED;
        private static final Type FIND_TIME_INITIAL_LOADING_SCREEN_SHOWN;
        private static final Type FIND_TIME_ORIGINAL_TIME_SELECTED;
        private static final Type FIND_TIME_ROOM_HIDDEN;
        private static final Type FIND_TIME_ROOM_UNHIDDEN;
        private static final Type FIND_TIME_ROOM_VISIBILITY_TOGGLED;
        public static final Type FIND_TIME_SHOW_MORE_CLICKED;
        public static final Type FIND_TIME_SUGGESTION_VIEW_SHOWN;
        public static final Type FIND_TIME_SUGGESTION_VIEW_SUGGESTION_ACCEPTED;
        private static final Type FIND_TIME_WEB_CANCEL_CLICKED;
        private static final Type FIND_TIME_WEB_DARK_LAUNCH_RESPONSE_RECEIVED;
        private static final Type FIND_TIME_WEB_SUBMIT_CLICKED;
        public static final Type HIERARCHY_NODE_EXPANDED;
        public static final Type LOCATION_CARD_EXPANDED;
        public static final Type ROOMS_SHOWN;
        public static final Type ROOM_ADDED;
        public static final Type ROOM_BOOKING_ENTRY_POINT_CLICKED;
        public static final Type ROOM_BOOKING_ENTRY_POINT_SHOWN;
        public static final Type ROOM_BOOKING_FILTERS_CHANGED;
        public static final Type ROOM_REMOVED;
        public static final Type SEARCH_QUERY_ENTERED;
        public static final Type STRUCTURED_ROOMS_VIEW_UPDATED;
        private static final Type UNKNOWN;
        private static final Type USS_READINESS_CHECKED;
        public static final Type USS_STATE_INITIALIZED;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static Type forNumber(int i)
        {
            switch (i)
            {
            case 25: // '\031'
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN;

            case 41: // ')'
                return EVENT_DETAILS_SESSION_STARTED;

            case 1: // '\001'
                return EVENT_CREATED;

            case 2: // '\002'
                return EVENT_UPDATED;

            case 40: // '('
                return EVENT_CREATION_REQUEST_SUCCESSFUL;

            case 26: // '\032'
                return EVENT_TIME_CHANGED;

            case 3: // '\003'
                return FIND_TIME_BUTTON_SHOWN;

            case 16: // '\020'
                return FIND_TIME_BUTTON_HIDDEN;

            case 4: // '\004'
                return FIND_TIME_BUTTON_CLICKED;

            case 5: // '\005'
                return FIND_TIME_SUGGESTION_VIEW_SHOWN;

            case 6: // '\006'
                return FIND_TIME_SUGGESTION_VIEW_SUGGESTION_ACCEPTED;

            case 7: // '\007'
                return FIND_TIME_SHOW_MORE_CLICKED;

            case 8: // '\b'
                return FIND_TIME_GRID_VIEW_OPENED;

            case 28: // '\034'
                return FIND_TIME_GRID_VIEW_PREVIEWED;

            case 9: // '\t'
                return FIND_TIME_GRID_VIEW_SUGGESTION_ACCEPTED;

            case 29: // '\035'
                return FIND_TIME_GRID_VIEW_PREVIEW_SUGGESTION_ACCEPTED;

            case 10: // '\n'
                return FIND_TIME_GRID_VIEW_CUSTOM_TIME_ACCEPTED;

            case 27: // '\033'
                return FIND_TIME_ORIGINAL_TIME_SELECTED;

            case 11: // '\013'
                return FIND_TIME_FILTERS_CHANGED;

            case 12: // '\f'
                return FIND_TIME_WEB_SUBMIT_CLICKED;

            case 13: // '\r'
                return FIND_TIME_WEB_CANCEL_CLICKED;

            case 14: // '\016'
                return FIND_TIME_GUEST_LIST_CHANGED;

            case 15: // '\017'
                return FIND_TIME_INITIAL_LOADING_SCREEN_SHOWN;

            case 30: // '\036'
                return FIND_TIME_GUEST_HIDDEN;

            case 31: // '\037'
                return FIND_TIME_ROOM_HIDDEN;

            case 32: // ' '
                return FIND_TIME_GUEST_VISIBILITY_TOGGLED;

            case 33: // '!'
                return FIND_TIME_ROOM_VISIBILITY_TOGGLED;

            case 34: // '"'
                return FIND_TIME_GUEST_UNHIDDEN;

            case 35: // '#'
                return FIND_TIME_ROOM_UNHIDDEN;

            case 39: // '\''
                return FIND_TIME_WEB_DARK_LAUNCH_RESPONSE_RECEIVED;

            case 38: // '&'
                return LOCATION_CARD_EXPANDED;

            case 17: // '\021'
                return ROOM_BOOKING_ENTRY_POINT_SHOWN;

            case 18: // '\022'
                return ROOM_BOOKING_ENTRY_POINT_CLICKED;

            case 19: // '\023'
                return ROOMS_SHOWN;

            case 20: // '\024'
                return ROOM_ADDED;

            case 21: // '\025'
                return ROOM_REMOVED;

            case 22: // '\026'
                return HIERARCHY_NODE_EXPANDED;

            case 23: // '\027'
                return SEARCH_QUERY_ENTERED;

            case 24: // '\030'
                return ROOM_BOOKING_FILTERS_CHANGED;

            case 36: // '$'
                return STRUCTURED_ROOMS_VIEW_UPDATED;

            case 37: // '%'
                return ALL_STRUCTURED_ROOMS_ADDED;

            case 42: // '*'
                return EVENT_DETAILS_SESSION_ENDED;

            case 43: // '+'
                return USS_STATE_INITIALIZED;

            case 44: // ','
                return USS_READINESS_CHECKED;
            }
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN = new Type("UNKNOWN", 0, 0);
            EVENT_DETAILS_SESSION_STARTED = new Type("EVENT_DETAILS_SESSION_STARTED", 1, 41);
            EVENT_CREATED = new Type("EVENT_CREATED", 2, 1);
            EVENT_UPDATED = new Type("EVENT_UPDATED", 3, 2);
            EVENT_CREATION_REQUEST_SUCCESSFUL = new Type("EVENT_CREATION_REQUEST_SUCCESSFUL", 4, 40);
            EVENT_TIME_CHANGED = new Type("EVENT_TIME_CHANGED", 5, 26);
            FIND_TIME_BUTTON_SHOWN = new Type("FIND_TIME_BUTTON_SHOWN", 6, 3);
            FIND_TIME_BUTTON_HIDDEN = new Type("FIND_TIME_BUTTON_HIDDEN", 7, 16);
            FIND_TIME_BUTTON_CLICKED = new Type("FIND_TIME_BUTTON_CLICKED", 8, 4);
            FIND_TIME_SUGGESTION_VIEW_SHOWN = new Type("FIND_TIME_SUGGESTION_VIEW_SHOWN", 9, 5);
            FIND_TIME_SUGGESTION_VIEW_SUGGESTION_ACCEPTED = new Type("FIND_TIME_SUGGESTION_VIEW_SUGGESTION_ACCEPTED", 10, 6);
            FIND_TIME_SHOW_MORE_CLICKED = new Type("FIND_TIME_SHOW_MORE_CLICKED", 11, 7);
            FIND_TIME_GRID_VIEW_OPENED = new Type("FIND_TIME_GRID_VIEW_OPENED", 12, 8);
            FIND_TIME_GRID_VIEW_PREVIEWED = new Type("FIND_TIME_GRID_VIEW_PREVIEWED", 13, 28);
            FIND_TIME_GRID_VIEW_SUGGESTION_ACCEPTED = new Type("FIND_TIME_GRID_VIEW_SUGGESTION_ACCEPTED", 14, 9);
            FIND_TIME_GRID_VIEW_PREVIEW_SUGGESTION_ACCEPTED = new Type("FIND_TIME_GRID_VIEW_PREVIEW_SUGGESTION_ACCEPTED", 15, 29);
            FIND_TIME_GRID_VIEW_CUSTOM_TIME_ACCEPTED = new Type("FIND_TIME_GRID_VIEW_CUSTOM_TIME_ACCEPTED", 16, 10);
            FIND_TIME_ORIGINAL_TIME_SELECTED = new Type("FIND_TIME_ORIGINAL_TIME_SELECTED", 17, 27);
            FIND_TIME_FILTERS_CHANGED = new Type("FIND_TIME_FILTERS_CHANGED", 18, 11);
            FIND_TIME_WEB_SUBMIT_CLICKED = new Type("FIND_TIME_WEB_SUBMIT_CLICKED", 19, 12);
            FIND_TIME_WEB_CANCEL_CLICKED = new Type("FIND_TIME_WEB_CANCEL_CLICKED", 20, 13);
            FIND_TIME_GUEST_LIST_CHANGED = new Type("FIND_TIME_GUEST_LIST_CHANGED", 21, 14);
            FIND_TIME_INITIAL_LOADING_SCREEN_SHOWN = new Type("FIND_TIME_INITIAL_LOADING_SCREEN_SHOWN", 22, 15);
            FIND_TIME_GUEST_HIDDEN = new Type("FIND_TIME_GUEST_HIDDEN", 23, 30);
            FIND_TIME_ROOM_HIDDEN = new Type("FIND_TIME_ROOM_HIDDEN", 24, 31);
            FIND_TIME_GUEST_VISIBILITY_TOGGLED = new Type("FIND_TIME_GUEST_VISIBILITY_TOGGLED", 25, 32);
            FIND_TIME_ROOM_VISIBILITY_TOGGLED = new Type("FIND_TIME_ROOM_VISIBILITY_TOGGLED", 26, 33);
            FIND_TIME_GUEST_UNHIDDEN = new Type("FIND_TIME_GUEST_UNHIDDEN", 27, 34);
            FIND_TIME_ROOM_UNHIDDEN = new Type("FIND_TIME_ROOM_UNHIDDEN", 28, 35);
            FIND_TIME_WEB_DARK_LAUNCH_RESPONSE_RECEIVED = new Type("FIND_TIME_WEB_DARK_LAUNCH_RESPONSE_RECEIVED", 29, 39);
            LOCATION_CARD_EXPANDED = new Type("LOCATION_CARD_EXPANDED", 30, 38);
            ROOM_BOOKING_ENTRY_POINT_SHOWN = new Type("ROOM_BOOKING_ENTRY_POINT_SHOWN", 31, 17);
            ROOM_BOOKING_ENTRY_POINT_CLICKED = new Type("ROOM_BOOKING_ENTRY_POINT_CLICKED", 32, 18);
            ROOMS_SHOWN = new Type("ROOMS_SHOWN", 33, 19);
            ROOM_ADDED = new Type("ROOM_ADDED", 34, 20);
            ROOM_REMOVED = new Type("ROOM_REMOVED", 35, 21);
            HIERARCHY_NODE_EXPANDED = new Type("HIERARCHY_NODE_EXPANDED", 36, 22);
            SEARCH_QUERY_ENTERED = new Type("SEARCH_QUERY_ENTERED", 37, 23);
            ROOM_BOOKING_FILTERS_CHANGED = new Type("ROOM_BOOKING_FILTERS_CHANGED", 38, 24);
            STRUCTURED_ROOMS_VIEW_UPDATED = new Type("STRUCTURED_ROOMS_VIEW_UPDATED", 39, 36);
            ALL_STRUCTURED_ROOMS_ADDED = new Type("ALL_STRUCTURED_ROOMS_ADDED", 40, 37);
            EVENT_DETAILS_SESSION_ENDED = new Type("EVENT_DETAILS_SESSION_ENDED", 41, 42);
            USS_STATE_INITIALIZED = new Type("USS_STATE_INITIALIZED", 42, 43);
            USS_READINESS_CHECKED = new Type("USS_READINESS_CHECKED", 43, 44);
            $VALUES = (new Type[] {
                UNKNOWN, EVENT_DETAILS_SESSION_STARTED, EVENT_CREATED, EVENT_UPDATED, EVENT_CREATION_REQUEST_SUCCESSFUL, EVENT_TIME_CHANGED, FIND_TIME_BUTTON_SHOWN, FIND_TIME_BUTTON_HIDDEN, FIND_TIME_BUTTON_CLICKED, FIND_TIME_SUGGESTION_VIEW_SHOWN, 
                FIND_TIME_SUGGESTION_VIEW_SUGGESTION_ACCEPTED, FIND_TIME_SHOW_MORE_CLICKED, FIND_TIME_GRID_VIEW_OPENED, FIND_TIME_GRID_VIEW_PREVIEWED, FIND_TIME_GRID_VIEW_SUGGESTION_ACCEPTED, FIND_TIME_GRID_VIEW_PREVIEW_SUGGESTION_ACCEPTED, FIND_TIME_GRID_VIEW_CUSTOM_TIME_ACCEPTED, FIND_TIME_ORIGINAL_TIME_SELECTED, FIND_TIME_FILTERS_CHANGED, FIND_TIME_WEB_SUBMIT_CLICKED, 
                FIND_TIME_WEB_CANCEL_CLICKED, FIND_TIME_GUEST_LIST_CHANGED, FIND_TIME_INITIAL_LOADING_SCREEN_SHOWN, FIND_TIME_GUEST_HIDDEN, FIND_TIME_ROOM_HIDDEN, FIND_TIME_GUEST_VISIBILITY_TOGGLED, FIND_TIME_ROOM_VISIBILITY_TOGGLED, FIND_TIME_GUEST_UNHIDDEN, FIND_TIME_ROOM_UNHIDDEN, FIND_TIME_WEB_DARK_LAUNCH_RESPONSE_RECEIVED, 
                LOCATION_CARD_EXPANDED, ROOM_BOOKING_ENTRY_POINT_SHOWN, ROOM_BOOKING_ENTRY_POINT_CLICKED, ROOMS_SHOWN, ROOM_ADDED, ROOM_REMOVED, HIERARCHY_NODE_EXPANDED, SEARCH_QUERY_ENTERED, ROOM_BOOKING_FILTERS_CHANGED, STRUCTURED_ROOMS_VIEW_UPDATED, 
                ALL_STRUCTURED_ROOMS_ADDED, EVENT_DETAILS_SESSION_ENDED, USS_STATE_INITIALIZED, USS_READINESS_CHECKED
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return Type.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private Type(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final CalendarClientLogEvent DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public com.google.protobuf.Internal.ProtobufList buildingIdList_;
    public CalendarEventInfo calendarEvent_;
    public CustomTimeInfo customTimeInfo_;
    public String hierarchyNodeId_;
    public int logEventType_;
    public RoomViewInfo roomViewInfo_;
    public RoomInfo room_;
    public ServiceResponseId serviceResponseId_;
    public TimeSuggestionViewInfo timeSuggestionViewInfo_;
    public UssClientState ussClientState_;

    private CalendarClientLogEvent()
    {
        hierarchyNodeId_ = "";
        buildingIdList_ = ProtobufArrayList.EMPTY_LIST;
    }

    public static CalendarClientLogEvent parseFrom(InputStream inputstream)
        throws IOException
    {
        Object obj = DEFAULT_INSTANCE;
        if (inputstream == null)
        {
            inputstream = Internal.EMPTY_BYTE_ARRAY;
            inputstream = CodedInputStream.newInstance(inputstream, 0, inputstream.length, false);
        } else
        {
            inputstream = new com.google.protobuf.CodedInputStream.StreamDecoder(inputstream, 4096);
        }
        obj = GeneratedMessageLite.parsePartialFrom(((GeneratedMessageLite) (obj)), inputstream, ExtensionRegistryLite.getEmptyRegistry());
        if (obj != null)
        {
            boolean flag1 = Boolean.TRUE.booleanValue();
            byte byte0 = ((Byte)((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
            boolean flag;
            if (byte0 == 1)
            {
                flag = true;
            } else
            if (byte0 == 0)
            {
                flag = false;
            } else
            {
                flag = Protobuf.INSTANCE.schemaFor(obj.getClass()).isInitialized(obj);
                if (flag1)
                {
                    int i = android.support.v4.content.ModernAsyncTask.Status.SET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
                    if (flag)
                    {
                        inputstream = ((InputStream) (obj));
                    } else
                    {
                        inputstream = null;
                    }
                    ((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(i, inputstream);
                }
            }
            if (!flag)
            {
                inputstream = new InvalidProtocolBufferException((new UninitializedMessageException()).getMessage());
                if (inputstream == null)
                {
                    throw null;
                } else
                {
                    throw inputstream;
                }
            }
        }
        return (CalendarClientLogEvent)obj;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 210
    //                   1 215
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 157
    //                   6 161;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new CalendarClientLogEvent();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        obj = Type.internalVerifier;
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\n\000\001\001\025\n\000\001\000\001\f\000\002\t\002\003\t\003\005\t\005\007\t\b\b\t\t\t\b\n\017\t\007\022\032\025\t\021", new Object[] {
            "bitField0_", "logEventType_", obj, "calendarEvent_", "serviceResponseId_", "timeSuggestionViewInfo_", "roomViewInfo_", "room_", "hierarchyNodeId_", "customTimeInfo_", 
            "buildingIdList_", "ussClientState_"
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/calendar/client/events/logging/CalendarClientLogEvent;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/calendar/client/events/logging/CalendarClientLogEvent;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/calendar/client/events/logging/CalendarClientLogEvent;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf((byte)1);
_L3:
        return null;
    }

    static 
    {
        DEFAULT_INSTANCE = new CalendarClientLogEvent();
        CalendarClientLogEvent calendarclientlogevent = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/calendar/client/events/logging/CalendarClientLogEvent, calendarclientlogevent);
    }
}
