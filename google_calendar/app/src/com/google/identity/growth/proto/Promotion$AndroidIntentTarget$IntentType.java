// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;


// Referenced classes of package com.google.identity.growth.proto:
//            Promotion

public static final class value extends Enum
    implements com.google.protobuf.t.IntentType
{

    private static final forNumber $VALUES[];
    public static final forNumber ACTIVITY;
    private static final forNumber ACTIVITY_WITH_RESULT;
    public static final forNumber BROADCAST;
    public static final forNumber SERVICE;
    public static final forNumber UNKNOWN;
    public static final com.google.protobuf.t.IntentType internalVerifier = new _cls2();
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN;

        case 1: // '\001'
            return ACTIVITY;

        case 2: // '\002'
            return SERVICE;

        case 3: // '\003'
            return BROADCAST;

        case 4: // '\004'
            return ACTIVITY_WITH_RESULT;
        }
    }

    public static ACTIVITY_WITH_RESULT[] values()
    {
        return (ACTIVITY_WITH_RESULT[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0, 0);
        ACTIVITY = new <init>("ACTIVITY", 1, 1);
        SERVICE = new <init>("SERVICE", 2, 2);
        BROADCAST = new <init>("BROADCAST", 3, 3);
        ACTIVITY_WITH_RESULT = new <init>("ACTIVITY_WITH_RESULT", 4, 4);
        $VALUES = (new .VALUES[] {
            UNKNOWN, ACTIVITY, SERVICE, BROADCAST, ACTIVITY_WITH_RESULT
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Promotion.AndroidIntentTarget.IntentType.forNumber(i) != null;
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
