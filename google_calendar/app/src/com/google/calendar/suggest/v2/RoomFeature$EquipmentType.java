// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.suggest.v2;


// Referenced classes of package com.google.calendar.suggest.v2:
//            RoomFeature

public static final class value extends Enum
    implements com.google.protobuf.ipmentType
{

    private static final _cls2 $VALUES[];
    public static final _cls2 AUDIO;
    public static final _cls2 UNRECOGNIZED;
    public static final _cls2 UNSPECIFIED_EQUIPMENT_TYPE;
    public static final _cls2 VIDEO;
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNSPECIFIED_EQUIPMENT_TYPE;

        case 1: // '\001'
            return VIDEO;

        case 2: // '\002'
            return AUDIO;
        }
    }

    public static AUDIO[] values()
    {
        return (AUDIO[])$VALUES.clone();
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
        UNSPECIFIED_EQUIPMENT_TYPE = new <init>("UNSPECIFIED_EQUIPMENT_TYPE", 0, 0);
        VIDEO = new <init>("VIDEO", 1, 1);
        AUDIO = new <init>("AUDIO", 2, 2);
        UNRECOGNIZED = new <init>("UNRECOGNIZED", 3, -1);
        $VALUES = (new .VALUES[] {
            UNSPECIFIED_EQUIPMENT_TYPE, VIDEO, AUDIO, UNRECOGNIZED
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return RoomFeature.EquipmentType.forNumber(i) != null;
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
