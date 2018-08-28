// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.logging.proto;


// Referenced classes of package com.google.identity.growth.logging.proto:
//            Client

public static final class value extends Enum
    implements com.google.protobuf.ent.ConditionsReason
{

    private static final forNumber $VALUES[];
    public static final forNumber CONDITION_BATTERY;
    public static final forNumber CONDITION_INSTALLED_APP;
    private static final forNumber CONDITION_KEYBOARD_PRESENT;
    public static final forNumber CONDITION_LOCALE;
    public static final forNumber CONDITION_NETWORK;
    private static final forNumber CONDITION_NETWORK_NOT_READY;
    private static final forNumber CONDITION_REGION;
    public static final forNumber CONDITION_TIME_CONSTRAINT;
    public static final forNumber CONDITION_UNKNOWN;
    private static final forNumber CONDITION_VIEW_NOT_IN_SCREEN;
    public static final com.google.protobuf.ent.ConditionsReason internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return CONDITION_UNKNOWN;

        case 1: // '\001'
            return CONDITION_NETWORK;

        case 2: // '\002'
            return CONDITION_NETWORK_NOT_READY;

        case 3: // '\003'
            return CONDITION_LOCALE;

        case 4: // '\004'
            return CONDITION_REGION;

        case 5: // '\005'
            return CONDITION_BATTERY;

        case 6: // '\006'
            return CONDITION_INSTALLED_APP;

        case 7: // '\007'
            return CONDITION_VIEW_NOT_IN_SCREEN;

        case 8: // '\b'
            return CONDITION_KEYBOARD_PRESENT;

        case 9: // '\t'
            return CONDITION_TIME_CONSTRAINT;
        }
    }

    public static CONDITION_TIME_CONSTRAINT[] values()
    {
        return (CONDITION_TIME_CONSTRAINT[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        CONDITION_UNKNOWN = new <init>("CONDITION_UNKNOWN", 0, 0);
        CONDITION_NETWORK = new <init>("CONDITION_NETWORK", 1, 1);
        CONDITION_NETWORK_NOT_READY = new <init>("CONDITION_NETWORK_NOT_READY", 2, 2);
        CONDITION_LOCALE = new <init>("CONDITION_LOCALE", 3, 3);
        CONDITION_REGION = new <init>("CONDITION_REGION", 4, 4);
        CONDITION_BATTERY = new <init>("CONDITION_BATTERY", 5, 5);
        CONDITION_INSTALLED_APP = new <init>("CONDITION_INSTALLED_APP", 6, 6);
        CONDITION_VIEW_NOT_IN_SCREEN = new <init>("CONDITION_VIEW_NOT_IN_SCREEN", 7, 7);
        CONDITION_KEYBOARD_PRESENT = new <init>("CONDITION_KEYBOARD_PRESENT", 8, 8);
        CONDITION_TIME_CONSTRAINT = new <init>("CONDITION_TIME_CONSTRAINT", 9, 9);
        $VALUES = (new .VALUES[] {
            CONDITION_UNKNOWN, CONDITION_NETWORK, CONDITION_NETWORK_NOT_READY, CONDITION_LOCALE, CONDITION_REGION, CONDITION_BATTERY, CONDITION_INSTALLED_APP, CONDITION_VIEW_NOT_IN_SCREEN, CONDITION_KEYBOARD_PRESENT, CONDITION_TIME_CONSTRAINT
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Client.PromoEvent.ConditionsReason.forNumber(i) != null;
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
