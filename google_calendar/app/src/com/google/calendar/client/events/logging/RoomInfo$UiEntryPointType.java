// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.client.events.logging;


// Referenced classes of package com.google.calendar.client.events.logging:
//            RoomInfo

public static final class value extends Enum
    implements com.google.protobuf.Info.UiEntryPointType
{

    private static final forNumber $VALUES[];
    public static final forNumber LISTING;
    private static final forNumber SRB_LISTING;
    private static final forNumber SRB_LOCATION_CARD;
    private static final forNumber SRB_LOCATION_CARD_LISTING;
    private static final forNumber SRB_SUGGESTIONS;
    private static final forNumber STRUCTURED_ROOM_BOOKING;
    public static final forNumber SUGGESTIONS;
    public static final forNumber UNKNOWN;
    public static final com.google.protobuf.Info.UiEntryPointType internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN;

        case 1: // '\001'
            return SUGGESTIONS;

        case 2: // '\002'
            return LISTING;

        case 3: // '\003'
            return STRUCTURED_ROOM_BOOKING;

        case 4: // '\004'
            return SRB_LOCATION_CARD;

        case 7: // '\007'
            return SRB_LOCATION_CARD_LISTING;

        case 5: // '\005'
            return SRB_SUGGESTIONS;

        case 6: // '\006'
            return SRB_LISTING;
        }
    }

    public static SRB_LISTING[] values()
    {
        return (SRB_LISTING[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0, 0);
        SUGGESTIONS = new <init>("SUGGESTIONS", 1, 1);
        LISTING = new <init>("LISTING", 2, 2);
        STRUCTURED_ROOM_BOOKING = new <init>("STRUCTURED_ROOM_BOOKING", 3, 3);
        SRB_LOCATION_CARD = new <init>("SRB_LOCATION_CARD", 4, 4);
        SRB_LOCATION_CARD_LISTING = new <init>("SRB_LOCATION_CARD_LISTING", 5, 7);
        SRB_SUGGESTIONS = new <init>("SRB_SUGGESTIONS", 6, 5);
        SRB_LISTING = new <init>("SRB_LISTING", 7, 6);
        $VALUES = (new .VALUES[] {
            UNKNOWN, SUGGESTIONS, LISTING, STRUCTURED_ROOM_BOOKING, SRB_LOCATION_CARD, SRB_LOCATION_CARD_LISTING, SRB_SUGGESTIONS, SRB_LISTING
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return RoomInfo.UiEntryPointType.forNumber(i) != null;
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
