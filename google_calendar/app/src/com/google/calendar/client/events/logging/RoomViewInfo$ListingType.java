// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.client.events.logging;


// Referenced classes of package com.google.calendar.client.events.logging:
//            RoomViewInfo

public static final class value extends Enum
    implements com.google.protobuf.mViewInfo.ListingType
{

    private static final forNumber $VALUES[];
    public static final forNumber FLAT;
    public static final forNumber HIERARCHICAL;
    public static final forNumber UNKNOWN;
    public static final com.google.protobuf.mViewInfo.ListingType internalVerifier = new _cls2();
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
            return FLAT;

        case 2: // '\002'
            return HIERARCHICAL;
        }
    }

    public static HIERARCHICAL[] values()
    {
        return (HIERARCHICAL[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0, 0);
        FLAT = new <init>("FLAT", 1, 1);
        HIERARCHICAL = new <init>("HIERARCHICAL", 2, 2);
        $VALUES = (new .VALUES[] {
            UNKNOWN, FLAT, HIERARCHICAL
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return RoomViewInfo.ListingType.forNumber(i) != null;
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
