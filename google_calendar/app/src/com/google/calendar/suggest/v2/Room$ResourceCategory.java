// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.suggest.v2;


// Referenced classes of package com.google.calendar.suggest.v2:
//            Room

public static final class value extends Enum
    implements com.google.protobuf.ceCategory
{

    private static final _cls2 $VALUES[];
    public static final _cls2 CATEGORY_UNKNOWN;
    public static final _cls2 CONFERENCE_ROOM;
    public static final _cls2 OTHER;
    public static final _cls2 UNRECOGNIZED;
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return CATEGORY_UNKNOWN;

        case 1: // '\001'
            return CONFERENCE_ROOM;

        case 2: // '\002'
            return OTHER;
        }
    }

    public static OTHER[] values()
    {
        return (OTHER[])$VALUES.clone();
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
        CATEGORY_UNKNOWN = new <init>("CATEGORY_UNKNOWN", 0, 0);
        CONFERENCE_ROOM = new <init>("CONFERENCE_ROOM", 1, 1);
        OTHER = new <init>("OTHER", 2, 2);
        UNRECOGNIZED = new <init>("UNRECOGNIZED", 3, -1);
        $VALUES = (new .VALUES[] {
            CATEGORY_UNKNOWN, CONFERENCE_ROOM, OTHER, UNRECOGNIZED
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Room.ResourceCategory.forNumber(i) != null;
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
