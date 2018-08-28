// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;


// Referenced classes of package com.google.identity.growth.proto:
//            Promotion

public static final class value extends Enum
    implements com.google.protobuf.Event.Action
{

    private static final forNumber $VALUES[];
    private static final forNumber DISPLAYED;
    private static final forNumber TAPPED;
    public static final forNumber UNKNOWN;
    public static final com.google.protobuf.Event.Action internalVerifier = new _cls2();
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
            return DISPLAYED;

        case 2: // '\002'
            return TAPPED;
        }
    }

    public static TAPPED[] values()
    {
        return (TAPPED[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0, 0);
        DISPLAYED = new <init>("DISPLAYED", 1, 1);
        TAPPED = new <init>("TAPPED", 2, 2);
        $VALUES = (new .VALUES[] {
            UNKNOWN, DISPLAYED, TAPPED
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Promotion.VisualElementEvent.Action.forNumber(i) != null;
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
