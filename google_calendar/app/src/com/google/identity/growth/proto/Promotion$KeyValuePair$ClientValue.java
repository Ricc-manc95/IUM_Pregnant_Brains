// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;


// Referenced classes of package com.google.identity.growth.proto:
//            Promotion

public static final class value extends Enum
    implements com.google.protobuf..ClientValue
{

    private static final forNumber $VALUES[];
    public static final forNumber CLIENT_VALUE_ACCOUNT_NAME;
    public static final forNumber CLIENT_VALUE_UNKNOWN;
    public static final com.google.protobuf..ClientValue internalVerifier = new _cls2();
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return CLIENT_VALUE_UNKNOWN;

        case 1: // '\001'
            return CLIENT_VALUE_ACCOUNT_NAME;
        }
    }

    public static CLIENT_VALUE_ACCOUNT_NAME[] values()
    {
        return (CLIENT_VALUE_ACCOUNT_NAME[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        CLIENT_VALUE_UNKNOWN = new <init>("CLIENT_VALUE_UNKNOWN", 0, 0);
        CLIENT_VALUE_ACCOUNT_NAME = new <init>("CLIENT_VALUE_ACCOUNT_NAME", 1, 1);
        $VALUES = (new .VALUES[] {
            CLIENT_VALUE_UNKNOWN, CLIENT_VALUE_ACCOUNT_NAME
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Promotion.KeyValuePair.ClientValue.forNumber(i) != null;
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
