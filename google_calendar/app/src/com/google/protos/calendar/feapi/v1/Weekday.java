// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;


public final class Weekday extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final Weekday $VALUES[];
    public static final Weekday FR;
    public static final Weekday MO;
    public static final Weekday SA;
    public static final Weekday SU;
    public static final Weekday TH;
    public static final Weekday TU;
    public static final Weekday WE;
    public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
    public final int value;

    private Weekday(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static Weekday forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 1: // '\001'
            return MO;

        case 2: // '\002'
            return TU;

        case 3: // '\003'
            return WE;

        case 4: // '\004'
            return TH;

        case 5: // '\005'
            return FR;

        case 6: // '\006'
            return SA;

        case 7: // '\007'
            return SU;
        }
    }

    public static Weekday[] values()
    {
        return (Weekday[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        MO = new Weekday("MO", 0, 1);
        TU = new Weekday("TU", 1, 2);
        WE = new Weekday("WE", 2, 3);
        TH = new Weekday("TH", 3, 4);
        FR = new Weekday("FR", 4, 5);
        SA = new Weekday("SA", 5, 6);
        SU = new Weekday("SU", 6, 7);
        $VALUES = (new Weekday[] {
            MO, TU, WE, TH, FR, SA, SU
        });
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return Weekday.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
