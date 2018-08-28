// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.communication.meetings.proto;


// Referenced classes of package com.google.protos.communication.meetings.proto:
//            RegionConfig

public static final class value extends Enum
    implements com.google.protobuf.mberForRegion.Availability
{

    private static final _cls2 $VALUES[];
    private static final _cls2 AVAILABILITY_UNSPECIFIED;
    public static final _cls2 NONE;
    public static final _cls2 PSTN_DOGFOOD;
    public static final _cls2 PSTN_FISHFOOD;
    public static final _cls2 PUBLIC;
    public static final _cls2 UNRECOGNIZED;
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return AVAILABILITY_UNSPECIFIED;

        case 1: // '\001'
            return NONE;

        case 2: // '\002'
            return PSTN_FISHFOOD;

        case 3: // '\003'
            return PSTN_DOGFOOD;

        case 4: // '\004'
            return PUBLIC;
        }
    }

    public static PUBLIC[] values()
    {
        return (PUBLIC[])$VALUES.clone();
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
        AVAILABILITY_UNSPECIFIED = new <init>("AVAILABILITY_UNSPECIFIED", 0, 0);
        NONE = new <init>("NONE", 1, 1);
        PSTN_FISHFOOD = new <init>("PSTN_FISHFOOD", 2, 2);
        PSTN_DOGFOOD = new <init>("PSTN_DOGFOOD", 3, 3);
        PUBLIC = new <init>("PUBLIC", 4, 4);
        UNRECOGNIZED = new <init>("UNRECOGNIZED", 5, -1);
        $VALUES = (new .VALUES[] {
            AVAILABILITY_UNSPECIFIED, NONE, PSTN_FISHFOOD, PSTN_DOGFOOD, PUBLIC, UNRECOGNIZED
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return RegionConfig.UniversalPstnNumberForRegion.Availability.forNumber(i) != null;
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
