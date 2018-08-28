// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;


public final class Freq extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final Freq $VALUES[];
    public static final Freq DAILY;
    public static final Freq HOURLY;
    public static final Freq MINUTELY;
    public static final Freq MONTHLY;
    public static final Freq SECONDLY;
    public static final Freq WEEKLY;
    public static final Freq YEARLY;
    public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
    public final int value;

    private Freq(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static Freq forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return SECONDLY;

        case 1: // '\001'
            return MINUTELY;

        case 2: // '\002'
            return HOURLY;

        case 3: // '\003'
            return DAILY;

        case 4: // '\004'
            return WEEKLY;

        case 5: // '\005'
            return MONTHLY;

        case 6: // '\006'
            return YEARLY;
        }
    }

    public static Freq[] values()
    {
        return (Freq[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        SECONDLY = new Freq("SECONDLY", 0, 0);
        MINUTELY = new Freq("MINUTELY", 1, 1);
        HOURLY = new Freq("HOURLY", 2, 2);
        DAILY = new Freq("DAILY", 3, 3);
        WEEKLY = new Freq("WEEKLY", 4, 4);
        MONTHLY = new Freq("MONTHLY", 5, 5);
        YEARLY = new Freq("YEARLY", 6, 6);
        $VALUES = (new Freq[] {
            SECONDLY, MINUTELY, HOURLY, DAILY, WEEKLY, MONTHLY, YEARLY
        });
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return Freq.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
