// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.logging.proto;


// Referenced classes of package com.google.identity.growth.logging.proto:
//            Client

public static final class value extends Enum
    implements com.google.protobuf.romoEvent.UserAction
{

    private static final forNumber $VALUES[];
    private static final forNumber ACTION_ACKNOWLEDGE;
    public static final forNumber ACTION_DISMISS;
    public static final forNumber ACTION_NEGATIVE;
    public static final forNumber ACTION_POSITIVE;
    public static final forNumber ACTION_UNKNOWN;
    public static final com.google.protobuf.romoEvent.UserAction internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return ACTION_UNKNOWN;

        case 1: // '\001'
            return ACTION_POSITIVE;

        case 2: // '\002'
            return ACTION_NEGATIVE;

        case 3: // '\003'
            return ACTION_DISMISS;

        case 4: // '\004'
            return ACTION_ACKNOWLEDGE;
        }
    }

    public static ACTION_ACKNOWLEDGE[] values()
    {
        return (ACTION_ACKNOWLEDGE[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        ACTION_UNKNOWN = new <init>("ACTION_UNKNOWN", 0, 0);
        ACTION_POSITIVE = new <init>("ACTION_POSITIVE", 1, 1);
        ACTION_NEGATIVE = new <init>("ACTION_NEGATIVE", 2, 2);
        ACTION_DISMISS = new <init>("ACTION_DISMISS", 3, 3);
        ACTION_ACKNOWLEDGE = new <init>("ACTION_ACKNOWLEDGE", 4, 4);
        $VALUES = (new .VALUES[] {
            ACTION_UNKNOWN, ACTION_POSITIVE, ACTION_NEGATIVE, ACTION_DISMISS, ACTION_ACKNOWLEDGE
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Client.PromoEvent.UserAction.forNumber(i) != null;
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
