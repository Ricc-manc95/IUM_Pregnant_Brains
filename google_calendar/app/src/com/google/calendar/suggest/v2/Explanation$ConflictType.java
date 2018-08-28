// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.suggest.v2;


// Referenced classes of package com.google.calendar.suggest.v2:
//            Explanation

public static final class value extends Enum
    implements com.google.protobuf.nflictType
{

    private static final _cls2 $VALUES[];
    public static final _cls2 MIGHT_BE_BUSY;
    public static final _cls2 MIGHT_NOT_BE_AVAILABLE;
    private static final _cls2 NO_SEAT_FOUND;
    public static final _cls2 UNRECOGNIZED;
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return MIGHT_NOT_BE_AVAILABLE;

        case 1: // '\001'
            return MIGHT_BE_BUSY;

        case 2: // '\002'
            return NO_SEAT_FOUND;
        }
    }

    public static NO_SEAT_FOUND[] values()
    {
        return (NO_SEAT_FOUND[])$VALUES.clone();
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
        MIGHT_NOT_BE_AVAILABLE = new <init>("MIGHT_NOT_BE_AVAILABLE", 0, 0);
        MIGHT_BE_BUSY = new <init>("MIGHT_BE_BUSY", 1, 1);
        NO_SEAT_FOUND = new <init>("NO_SEAT_FOUND", 2, 2);
        UNRECOGNIZED = new <init>("UNRECOGNIZED", 3, -1);
        $VALUES = (new .VALUES[] {
            MIGHT_NOT_BE_AVAILABLE, MIGHT_BE_BUSY, NO_SEAT_FOUND, UNRECOGNIZED
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Explanation.ConflictType.forNumber(i) != null;
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
