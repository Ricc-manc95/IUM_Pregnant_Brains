// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.caribou.smartmail;


// Referenced classes of package com.google.caribou.smartmail:
//            FlightReservation

public static final class value extends Enum
    implements com.google.protobuf.atusCode
{

    private static final forNumber $VALUES[];
    public static final forNumber CANCELLED;
    public static final forNumber DELAYED;
    public static final forNumber LANDED;
    public static final forNumber ON_TIME;
    public static final forNumber REDIRECTED;
    public static final forNumber SCHEDULED;
    public static final forNumber UNKNOWN;
    public static final com.google.protobuf.atusCode internalVerifier = new _cls2();
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
            return SCHEDULED;

        case 2: // '\002'
            return ON_TIME;

        case 3: // '\003'
            return LANDED;

        case 4: // '\004'
            return DELAYED;

        case 5: // '\005'
            return CANCELLED;

        case 6: // '\006'
            return REDIRECTED;
        }
    }

    public static REDIRECTED[] values()
    {
        return (REDIRECTED[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0, 0);
        SCHEDULED = new <init>("SCHEDULED", 1, 1);
        ON_TIME = new <init>("ON_TIME", 2, 2);
        LANDED = new <init>("LANDED", 3, 3);
        DELAYED = new <init>("DELAYED", 4, 4);
        CANCELLED = new <init>("CANCELLED", 5, 5);
        REDIRECTED = new <init>("REDIRECTED", 6, 6);
        $VALUES = (new .VALUES[] {
            UNKNOWN, SCHEDULED, ON_TIME, LANDED, DELAYED, CANCELLED, REDIRECTED
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return FlightReservation.FlightSegment.StatusCode.forNumber(i) != null;
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
