// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;


public final class value extends Enum
    implements com.google.protobuf.t.UserAction
{

    private static final _cls2 $VALUES[];
    private static final _cls2 ACKNOWLEDGE_RESPONSE;
    public static final _cls2 CONTROL_NOT_SEEN;
    public static final _cls2 DISMISSED;
    private static final _cls2 LEGACY_NOT_SEEN;
    public static final _cls2 NEGATIVE_RESPONSE;
    public static final _cls2 POSITIVE_RESPONSE;
    public static final _cls2 UNKNOWN_ACTION;
    public static final _cls2 UNRECOGNIZED;
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN_ACTION;

        case 1: // '\001'
            return DISMISSED;

        case 2: // '\002'
            return POSITIVE_RESPONSE;

        case 3: // '\003'
            return NEGATIVE_RESPONSE;

        case 4: // '\004'
            return CONTROL_NOT_SEEN;

        case 5: // '\005'
            return LEGACY_NOT_SEEN;

        case 6: // '\006'
            return ACKNOWLEDGE_RESPONSE;
        }
    }

    public static ACKNOWLEDGE_RESPONSE[] values()
    {
        return (ACKNOWLEDGE_RESPONSE[])$VALUES.clone();
    }

    public final int getNumber()
    {
        if (this == UNRECOGNIZED)
        {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        } else
        {
            return value;
        }
    }

    static 
    {
        UNKNOWN_ACTION = new <init>("UNKNOWN_ACTION", 0, 0);
        DISMISSED = new <init>("DISMISSED", 1, 1);
        POSITIVE_RESPONSE = new <init>("POSITIVE_RESPONSE", 2, 2);
        NEGATIVE_RESPONSE = new <init>("NEGATIVE_RESPONSE", 3, 3);
        CONTROL_NOT_SEEN = new <init>("CONTROL_NOT_SEEN", 4, 4);
        LEGACY_NOT_SEEN = new <init>("LEGACY_NOT_SEEN", 5, 5);
        ACKNOWLEDGE_RESPONSE = new <init>("ACKNOWLEDGE_RESPONSE", 6, 6);
        UNRECOGNIZED = new <init>("UNRECOGNIZED", 7, -1);
        $VALUES = (new .VALUES[] {
            UNKNOWN_ACTION, DISMISSED, POSITIVE_RESPONSE, NEGATIVE_RESPONSE, CONTROL_NOT_SEEN, LEGACY_NOT_SEEN, ACKNOWLEDGE_RESPONSE, UNRECOGNIZED
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return CampaignManagement.UserAction.forNumber(i) != null;
            }

            _cls2()
            {
            }
        }

        new _cls2();
    }

    private _cls2(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
