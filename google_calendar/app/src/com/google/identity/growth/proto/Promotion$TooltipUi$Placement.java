// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;


// Referenced classes of package com.google.identity.growth.proto:
//            Promotion

public static final class value extends Enum
    implements com.google.protobuf.Ui.Placement
{

    private static final forNumber $VALUES[];
    public static final forNumber ABOVE;
    public static final forNumber BELOW;
    public static final forNumber UNKNOWN;
    public static final com.google.protobuf.Ui.Placement internalVerifier = new _cls2();
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
            return ABOVE;

        case 2: // '\002'
            return BELOW;
        }
    }

    public static BELOW[] values()
    {
        return (BELOW[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0, 0);
        ABOVE = new <init>("ABOVE", 1, 1);
        BELOW = new <init>("BELOW", 2, 2);
        $VALUES = (new .VALUES[] {
            UNKNOWN, ABOVE, BELOW
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Promotion.TooltipUi.Placement.forNumber(i) != null;
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
