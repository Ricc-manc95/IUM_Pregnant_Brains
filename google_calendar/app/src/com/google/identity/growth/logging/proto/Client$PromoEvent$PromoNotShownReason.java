// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.logging.proto;


// Referenced classes of package com.google.identity.growth.logging.proto:
//            Client

public static final class value extends Enum
    implements com.google.protobuf..PromoNotShownReason
{

    private static final forNumber $VALUES[];
    public static final forNumber PROMO_NOT_SHOWN_CLIENT_BLOCK;
    public static final forNumber PROMO_NOT_SHOWN_CONTROL_GROUP;
    private static final forNumber PROMO_NOT_SHOWN_INTERNAL_ERROR;
    private static final forNumber PROMO_NOT_SHOWN_KEYBOARD_PRESENT;
    private static final forNumber PROMO_NOT_SHOWN_UNKNOWN;
    private static final forNumber PROMO_NOT_SHOWN_VIEW_NOT_IN_SCREEN;
    private static final forNumber PROMO_NOT_SHOWN_VOICE_OVER_ENABLED;
    public static final com.google.protobuf..PromoNotShownReason internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return PROMO_NOT_SHOWN_UNKNOWN;

        case 1: // '\001'
            return PROMO_NOT_SHOWN_INTERNAL_ERROR;

        case 2: // '\002'
            return PROMO_NOT_SHOWN_CLIENT_BLOCK;

        case 3: // '\003'
            return PROMO_NOT_SHOWN_CONTROL_GROUP;

        case 4: // '\004'
            return PROMO_NOT_SHOWN_VIEW_NOT_IN_SCREEN;

        case 5: // '\005'
            return PROMO_NOT_SHOWN_KEYBOARD_PRESENT;

        case 6: // '\006'
            return PROMO_NOT_SHOWN_VOICE_OVER_ENABLED;
        }
    }

    public static PROMO_NOT_SHOWN_VOICE_OVER_ENABLED[] values()
    {
        return (PROMO_NOT_SHOWN_VOICE_OVER_ENABLED[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        PROMO_NOT_SHOWN_UNKNOWN = new <init>("PROMO_NOT_SHOWN_UNKNOWN", 0, 0);
        PROMO_NOT_SHOWN_INTERNAL_ERROR = new <init>("PROMO_NOT_SHOWN_INTERNAL_ERROR", 1, 1);
        PROMO_NOT_SHOWN_CLIENT_BLOCK = new <init>("PROMO_NOT_SHOWN_CLIENT_BLOCK", 2, 2);
        PROMO_NOT_SHOWN_CONTROL_GROUP = new <init>("PROMO_NOT_SHOWN_CONTROL_GROUP", 3, 3);
        PROMO_NOT_SHOWN_VIEW_NOT_IN_SCREEN = new <init>("PROMO_NOT_SHOWN_VIEW_NOT_IN_SCREEN", 4, 4);
        PROMO_NOT_SHOWN_KEYBOARD_PRESENT = new <init>("PROMO_NOT_SHOWN_KEYBOARD_PRESENT", 5, 5);
        PROMO_NOT_SHOWN_VOICE_OVER_ENABLED = new <init>("PROMO_NOT_SHOWN_VOICE_OVER_ENABLED", 6, 6);
        $VALUES = (new .VALUES[] {
            PROMO_NOT_SHOWN_UNKNOWN, PROMO_NOT_SHOWN_INTERNAL_ERROR, PROMO_NOT_SHOWN_CLIENT_BLOCK, PROMO_NOT_SHOWN_CONTROL_GROUP, PROMO_NOT_SHOWN_VIEW_NOT_IN_SCREEN, PROMO_NOT_SHOWN_KEYBOARD_PRESENT, PROMO_NOT_SHOWN_VOICE_OVER_ENABLED
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Client.PromoEvent.PromoNotShownReason.forNumber(i) != null;
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
