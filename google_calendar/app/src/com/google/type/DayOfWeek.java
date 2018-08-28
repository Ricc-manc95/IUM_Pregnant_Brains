// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.type;


public final class DayOfWeek extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final DayOfWeek $VALUES[];
    public static final DayOfWeek DAY_OF_WEEK_UNSPECIFIED;
    public static final DayOfWeek FRIDAY;
    public static final DayOfWeek MONDAY;
    public static final DayOfWeek SATURDAY;
    public static final DayOfWeek SUNDAY;
    public static final DayOfWeek THURSDAY;
    public static final DayOfWeek TUESDAY;
    private static final DayOfWeek UNRECOGNIZED;
    public static final DayOfWeek WEDNESDAY;
    public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
    private final int value;

    private DayOfWeek(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static DayOfWeek forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return DAY_OF_WEEK_UNSPECIFIED;

        case 1: // '\001'
            return MONDAY;

        case 2: // '\002'
            return TUESDAY;

        case 3: // '\003'
            return WEDNESDAY;

        case 4: // '\004'
            return THURSDAY;

        case 5: // '\005'
            return FRIDAY;

        case 6: // '\006'
            return SATURDAY;

        case 7: // '\007'
            return SUNDAY;
        }
    }

    public static DayOfWeek[] values()
    {
        return (DayOfWeek[])$VALUES.clone();
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
        DAY_OF_WEEK_UNSPECIFIED = new DayOfWeek("DAY_OF_WEEK_UNSPECIFIED", 0, 0);
        MONDAY = new DayOfWeek("MONDAY", 1, 1);
        TUESDAY = new DayOfWeek("TUESDAY", 2, 2);
        WEDNESDAY = new DayOfWeek("WEDNESDAY", 3, 3);
        THURSDAY = new DayOfWeek("THURSDAY", 4, 4);
        FRIDAY = new DayOfWeek("FRIDAY", 5, 5);
        SATURDAY = new DayOfWeek("SATURDAY", 6, 6);
        SUNDAY = new DayOfWeek("SUNDAY", 7, 7);
        UNRECOGNIZED = new DayOfWeek("UNRECOGNIZED", 8, -1);
        $VALUES = (new DayOfWeek[] {
            DAY_OF_WEEK_UNSPECIFIED, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY, UNRECOGNIZED
        });
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return DayOfWeek.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
