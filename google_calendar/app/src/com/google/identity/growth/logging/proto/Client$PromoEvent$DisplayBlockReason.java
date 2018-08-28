// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.logging.proto;


// Referenced classes of package com.google.identity.growth.logging.proto:
//            Client

public static final class value extends Enum
    implements com.google.protobuf.t.DisplayBlockReason
{

    private static final forNumber $VALUES[];
    public static final forNumber DISPLAY_BLOCK_CLIENT_ERROR;
    public static final forNumber DISPLAY_BLOCK_CLIENT_REJECT;
    public static final forNumber DISPLAY_BLOCK_LEGACY_USER;
    private static final forNumber DISPLAY_BLOCK_NEXT_LAUNCH;
    public static final forNumber DISPLAY_BLOCK_TRY_AGAIN_LATER;
    public static final forNumber DISPLAY_BLOCK_UNKNOWN;
    public static final com.google.protobuf.t.DisplayBlockReason internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return DISPLAY_BLOCK_UNKNOWN;

        case 1: // '\001'
            return DISPLAY_BLOCK_CLIENT_REJECT;

        case 2: // '\002'
            return DISPLAY_BLOCK_CLIENT_ERROR;

        case 3: // '\003'
            return DISPLAY_BLOCK_NEXT_LAUNCH;

        case 4: // '\004'
            return DISPLAY_BLOCK_TRY_AGAIN_LATER;

        case 5: // '\005'
            return DISPLAY_BLOCK_LEGACY_USER;
        }
    }

    public static DISPLAY_BLOCK_LEGACY_USER[] values()
    {
        return (DISPLAY_BLOCK_LEGACY_USER[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        DISPLAY_BLOCK_UNKNOWN = new <init>("DISPLAY_BLOCK_UNKNOWN", 0, 0);
        DISPLAY_BLOCK_CLIENT_REJECT = new <init>("DISPLAY_BLOCK_CLIENT_REJECT", 1, 1);
        DISPLAY_BLOCK_CLIENT_ERROR = new <init>("DISPLAY_BLOCK_CLIENT_ERROR", 2, 2);
        DISPLAY_BLOCK_NEXT_LAUNCH = new <init>("DISPLAY_BLOCK_NEXT_LAUNCH", 3, 3);
        DISPLAY_BLOCK_TRY_AGAIN_LATER = new <init>("DISPLAY_BLOCK_TRY_AGAIN_LATER", 4, 4);
        DISPLAY_BLOCK_LEGACY_USER = new <init>("DISPLAY_BLOCK_LEGACY_USER", 5, 5);
        $VALUES = (new .VALUES[] {
            DISPLAY_BLOCK_UNKNOWN, DISPLAY_BLOCK_CLIENT_REJECT, DISPLAY_BLOCK_CLIENT_ERROR, DISPLAY_BLOCK_NEXT_LAUNCH, DISPLAY_BLOCK_TRY_AGAIN_LATER, DISPLAY_BLOCK_LEGACY_USER
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Client.PromoEvent.DisplayBlockReason.forNumber(i) != null;
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
