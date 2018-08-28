// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.suggest.v2;


// Referenced classes of package com.google.calendar.suggest.v2:
//            RoomSuggestion

public static final class value extends Enum
    implements com.google.protobuf.ailability
{

    private static final _cls2 $VALUES[];
    public static final _cls2 AVAILABLE;
    public static final _cls2 UNAVAILABLE;
    public static final _cls2 UNKNOWN;
    public static final _cls2 UNRECOGNIZED;
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
            return AVAILABLE;

        case 2: // '\002'
            return UNAVAILABLE;
        }
    }

    public static UNAVAILABLE[] values()
    {
        return (UNAVAILABLE[])$VALUES.clone();
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
        UNKNOWN = new <init>("UNKNOWN", 0, 0);
        AVAILABLE = new <init>("AVAILABLE", 1, 1);
        UNAVAILABLE = new <init>("UNAVAILABLE", 2, 2);
        UNRECOGNIZED = new <init>("UNRECOGNIZED", 3, -1);
        $VALUES = (new .VALUES[] {
            UNKNOWN, AVAILABLE, UNAVAILABLE, UNRECOGNIZED
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return RoomSuggestion.Availability.forNumber(i) != null;
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
