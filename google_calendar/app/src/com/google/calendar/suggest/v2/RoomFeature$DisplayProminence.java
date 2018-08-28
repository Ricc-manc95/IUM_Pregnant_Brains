// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.suggest.v2;


// Referenced classes of package com.google.calendar.suggest.v2:
//            RoomFeature

public static final class value extends Enum
    implements com.google.protobuf.Prominence
{

    private static final _cls2 $VALUES[];
    public static final _cls2 DEFAULT_PROMINENCE;
    public static final _cls2 PROMINENT;
    public static final _cls2 UNRECOGNIZED;
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return DEFAULT_PROMINENCE;

        case 1: // '\001'
            return PROMINENT;
        }
    }

    public static PROMINENT[] values()
    {
        return (PROMINENT[])$VALUES.clone();
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
        DEFAULT_PROMINENCE = new <init>("DEFAULT_PROMINENCE", 0, 0);
        PROMINENT = new <init>("PROMINENT", 1, 1);
        UNRECOGNIZED = new <init>("UNRECOGNIZED", 2, -1);
        $VALUES = (new .VALUES[] {
            DEFAULT_PROMINENCE, PROMINENT, UNRECOGNIZED
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return RoomFeature.DisplayProminence.forNumber(i) != null;
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
