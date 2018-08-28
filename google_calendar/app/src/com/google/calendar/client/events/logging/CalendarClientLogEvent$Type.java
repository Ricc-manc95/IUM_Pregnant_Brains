// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.client.events.logging;


// Referenced classes of package com.google.calendar.client.events.logging:
//            CalendarClientLogEvent

public static final class value extends Enum
    implements com.google.protobuf.arClientLogEvent.Type
{

    private static final forNumber $VALUES[];
    private static final forNumber ALL_STRUCTURED_ROOMS_ADDED;
    public static final forNumber EVENT_CREATED;
    private static final forNumber EVENT_CREATION_REQUEST_SUCCESSFUL;
    public static final forNumber EVENT_DETAILS_SESSION_ENDED;
    public static final forNumber EVENT_DETAILS_SESSION_STARTED;
    public static final forNumber EVENT_TIME_CHANGED;
    public static final forNumber EVENT_UPDATED;
    public static final forNumber FIND_TIME_BUTTON_CLICKED;
    public static final forNumber FIND_TIME_BUTTON_HIDDEN;
    public static final forNumber FIND_TIME_BUTTON_SHOWN;
    public static final forNumber FIND_TIME_FILTERS_CHANGED;
    public static final forNumber FIND_TIME_GRID_VIEW_CUSTOM_TIME_ACCEPTED;
    public static final forNumber FIND_TIME_GRID_VIEW_OPENED;
    private static final forNumber FIND_TIME_GRID_VIEW_PREVIEWED;
    private static final forNumber FIND_TIME_GRID_VIEW_PREVIEW_SUGGESTION_ACCEPTED;
    public static final forNumber FIND_TIME_GRID_VIEW_SUGGESTION_ACCEPTED;
    private static final forNumber FIND_TIME_GUEST_HIDDEN;
    private static final forNumber FIND_TIME_GUEST_LIST_CHANGED;
    private static final forNumber FIND_TIME_GUEST_UNHIDDEN;
    private static final forNumber FIND_TIME_GUEST_VISIBILITY_TOGGLED;
    private static final forNumber FIND_TIME_INITIAL_LOADING_SCREEN_SHOWN;
    private static final forNumber FIND_TIME_ORIGINAL_TIME_SELECTED;
    private static final forNumber FIND_TIME_ROOM_HIDDEN;
    private static final forNumber FIND_TIME_ROOM_UNHIDDEN;
    private static final forNumber FIND_TIME_ROOM_VISIBILITY_TOGGLED;
    public static final forNumber FIND_TIME_SHOW_MORE_CLICKED;
    public static final forNumber FIND_TIME_SUGGESTION_VIEW_SHOWN;
    public static final forNumber FIND_TIME_SUGGESTION_VIEW_SUGGESTION_ACCEPTED;
    private static final forNumber FIND_TIME_WEB_CANCEL_CLICKED;
    private static final forNumber FIND_TIME_WEB_DARK_LAUNCH_RESPONSE_RECEIVED;
    private static final forNumber FIND_TIME_WEB_SUBMIT_CLICKED;
    public static final forNumber HIERARCHY_NODE_EXPANDED;
    public static final forNumber LOCATION_CARD_EXPANDED;
    public static final forNumber ROOMS_SHOWN;
    public static final forNumber ROOM_ADDED;
    public static final forNumber ROOM_BOOKING_ENTRY_POINT_CLICKED;
    public static final forNumber ROOM_BOOKING_ENTRY_POINT_SHOWN;
    public static final forNumber ROOM_BOOKING_FILTERS_CHANGED;
    public static final forNumber ROOM_REMOVED;
    public static final forNumber SEARCH_QUERY_ENTERED;
    public static final forNumber STRUCTURED_ROOMS_VIEW_UPDATED;
    private static final forNumber UNKNOWN;
    private static final forNumber USS_READINESS_CHECKED;
    public static final forNumber USS_STATE_INITIALIZED;
    public static final com.google.protobuf.arClientLogEvent.Type internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
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

    public static USS_READINESS_CHECKED[] values()
    {
        return (USS_READINESS_CHECKED[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0, 0);
        EVENT_DETAILS_SESSION_STARTED = new <init>("EVENT_DETAILS_SESSION_STARTED", 1, 41);
        EVENT_CREATED = new <init>("EVENT_CREATED", 2, 1);
        EVENT_UPDATED = new <init>("EVENT_UPDATED", 3, 2);
        EVENT_CREATION_REQUEST_SUCCESSFUL = new <init>("EVENT_CREATION_REQUEST_SUCCESSFUL", 4, 40);
        EVENT_TIME_CHANGED = new <init>("EVENT_TIME_CHANGED", 5, 26);
        FIND_TIME_BUTTON_SHOWN = new <init>("FIND_TIME_BUTTON_SHOWN", 6, 3);
        FIND_TIME_BUTTON_HIDDEN = new <init>("FIND_TIME_BUTTON_HIDDEN", 7, 16);
        FIND_TIME_BUTTON_CLICKED = new <init>("FIND_TIME_BUTTON_CLICKED", 8, 4);
        FIND_TIME_SUGGESTION_VIEW_SHOWN = new <init>("FIND_TIME_SUGGESTION_VIEW_SHOWN", 9, 5);
        FIND_TIME_SUGGESTION_VIEW_SUGGESTION_ACCEPTED = new <init>("FIND_TIME_SUGGESTION_VIEW_SUGGESTION_ACCEPTED", 10, 6);
        FIND_TIME_SHOW_MORE_CLICKED = new <init>("FIND_TIME_SHOW_MORE_CLICKED", 11, 7);
        FIND_TIME_GRID_VIEW_OPENED = new <init>("FIND_TIME_GRID_VIEW_OPENED", 12, 8);
        FIND_TIME_GRID_VIEW_PREVIEWED = new <init>("FIND_TIME_GRID_VIEW_PREVIEWED", 13, 28);
        FIND_TIME_GRID_VIEW_SUGGESTION_ACCEPTED = new <init>("FIND_TIME_GRID_VIEW_SUGGESTION_ACCEPTED", 14, 9);
        FIND_TIME_GRID_VIEW_PREVIEW_SUGGESTION_ACCEPTED = new <init>("FIND_TIME_GRID_VIEW_PREVIEW_SUGGESTION_ACCEPTED", 15, 29);
        FIND_TIME_GRID_VIEW_CUSTOM_TIME_ACCEPTED = new <init>("FIND_TIME_GRID_VIEW_CUSTOM_TIME_ACCEPTED", 16, 10);
        FIND_TIME_ORIGINAL_TIME_SELECTED = new <init>("FIND_TIME_ORIGINAL_TIME_SELECTED", 17, 27);
        FIND_TIME_FILTERS_CHANGED = new <init>("FIND_TIME_FILTERS_CHANGED", 18, 11);
        FIND_TIME_WEB_SUBMIT_CLICKED = new <init>("FIND_TIME_WEB_SUBMIT_CLICKED", 19, 12);
        FIND_TIME_WEB_CANCEL_CLICKED = new <init>("FIND_TIME_WEB_CANCEL_CLICKED", 20, 13);
        FIND_TIME_GUEST_LIST_CHANGED = new <init>("FIND_TIME_GUEST_LIST_CHANGED", 21, 14);
        FIND_TIME_INITIAL_LOADING_SCREEN_SHOWN = new <init>("FIND_TIME_INITIAL_LOADING_SCREEN_SHOWN", 22, 15);
        FIND_TIME_GUEST_HIDDEN = new <init>("FIND_TIME_GUEST_HIDDEN", 23, 30);
        FIND_TIME_ROOM_HIDDEN = new <init>("FIND_TIME_ROOM_HIDDEN", 24, 31);
        FIND_TIME_GUEST_VISIBILITY_TOGGLED = new <init>("FIND_TIME_GUEST_VISIBILITY_TOGGLED", 25, 32);
        FIND_TIME_ROOM_VISIBILITY_TOGGLED = new <init>("FIND_TIME_ROOM_VISIBILITY_TOGGLED", 26, 33);
        FIND_TIME_GUEST_UNHIDDEN = new <init>("FIND_TIME_GUEST_UNHIDDEN", 27, 34);
        FIND_TIME_ROOM_UNHIDDEN = new <init>("FIND_TIME_ROOM_UNHIDDEN", 28, 35);
        FIND_TIME_WEB_DARK_LAUNCH_RESPONSE_RECEIVED = new <init>("FIND_TIME_WEB_DARK_LAUNCH_RESPONSE_RECEIVED", 29, 39);
        LOCATION_CARD_EXPANDED = new <init>("LOCATION_CARD_EXPANDED", 30, 38);
        ROOM_BOOKING_ENTRY_POINT_SHOWN = new <init>("ROOM_BOOKING_ENTRY_POINT_SHOWN", 31, 17);
        ROOM_BOOKING_ENTRY_POINT_CLICKED = new <init>("ROOM_BOOKING_ENTRY_POINT_CLICKED", 32, 18);
        ROOMS_SHOWN = new <init>("ROOMS_SHOWN", 33, 19);
        ROOM_ADDED = new <init>("ROOM_ADDED", 34, 20);
        ROOM_REMOVED = new <init>("ROOM_REMOVED", 35, 21);
        HIERARCHY_NODE_EXPANDED = new <init>("HIERARCHY_NODE_EXPANDED", 36, 22);
        SEARCH_QUERY_ENTERED = new <init>("SEARCH_QUERY_ENTERED", 37, 23);
        ROOM_BOOKING_FILTERS_CHANGED = new <init>("ROOM_BOOKING_FILTERS_CHANGED", 38, 24);
        STRUCTURED_ROOMS_VIEW_UPDATED = new <init>("STRUCTURED_ROOMS_VIEW_UPDATED", 39, 36);
        ALL_STRUCTURED_ROOMS_ADDED = new <init>("ALL_STRUCTURED_ROOMS_ADDED", 40, 37);
        EVENT_DETAILS_SESSION_ENDED = new <init>("EVENT_DETAILS_SESSION_ENDED", 41, 42);
        USS_STATE_INITIALIZED = new <init>("USS_STATE_INITIALIZED", 42, 43);
        USS_READINESS_CHECKED = new <init>("USS_READINESS_CHECKED", 43, 44);
        $VALUES = (new .VALUES[] {
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
                return CalendarClientLogEvent.Type.forNumber(i) != null;
            }

            _cls2()
            {
            }
        }

    }

    private _cls2(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
